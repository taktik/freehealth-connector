package org.taktik.connector.business.recipeprojects.core.services;

import be.apb.gfddpp.common.exceptions.GFDDPPException;
import be.apb.gfddpp.common.utils.JaxContextCentralizer;
import org.taktik.connector.business.recipeprojects.core.exceptions.IntegrationModuleException;
import org.taktik.connector.business.recipeprojects.core.handlers.InsurabilityHandler;
import org.taktik.connector.business.recipeprojects.core.handlers.LoggingHandler;
import org.taktik.connector.business.recipeprojects.core.handlers.MustUnderstandHandler;
import org.taktik.connector.business.recipeprojects.core.handlers.SoapFaultHandler;
import org.taktik.connector.business.recipeprojects.core.utils.I18nHelper;
import org.taktik.connector.business.recipeprojects.core.utils.PropertyHandler;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.taktik.connector.technical.exception.ConnectorException;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.service.sts.security.Credential;
import org.taktik.connector.technical.service.sts.security.SAMLToken;
import org.taktik.connector.technical.ws.GenericWsSender;
import org.taktik.connector.technical.ws.domain.GenericRequest;
import org.taktik.connector.technical.ws.domain.GenericResponse;
import org.taktik.connector.technical.ws.domain.HandlerChain;
import org.taktik.connector.technical.ws.domain.HandlerPosition;
import org.taktik.connector.technical.ws.impl.GenericWsSenderImpl;

import javax.xml.soap.SOAPException;

public abstract class GenericWebserviceCaller {

    private final static Logger LOG = LogManager.getLogger(GenericWebserviceCaller.class);
    private static GenericWsSender sender = new GenericWsSenderImpl();

    public static <T extends Object> T callGenericWebservice(SAMLToken samlToken, Credential credential, Object request, Class<T> responseType, String endpointName, String serviceName, boolean addLoggingHandler, boolean addSoapFaultHandler, boolean addMustUnderstandHandler, boolean addInsurabilityHandler) throws IntegrationModuleException, TechnicalConnectorException {
        return callGenericWebservice(samlToken, credential, request, request.getClass(), responseType, endpointName, serviceName, addLoggingHandler, addSoapFaultHandler, addMustUnderstandHandler, addInsurabilityHandler);
    }

    public static <T extends Object> T callGenericWebservice(SAMLToken samlToken, Credential credential, Object request, Class<?> requestType, Class<T> responseType, String endpoint, String serviceName, boolean addLoggingHandler, boolean addSoapFaultHandler, boolean addMustUnderstandHandler, boolean addInsurabilityHandler) throws IntegrationModuleException, TechnicalConnectorException {
        final GenericWebserviceRequest genericWebserviceRequest = new GenericWebserviceRequest();
        genericWebserviceRequest.setRequest(request);
        genericWebserviceRequest.setRequestType(requestType);
        genericWebserviceRequest.setEndpoint(endpoint);
        genericWebserviceRequest.setServiceName(serviceName);
        genericWebserviceRequest.setAddLoggingHandler(addLoggingHandler);
        genericWebserviceRequest.setAddSoapFaultHandler(addSoapFaultHandler);
        genericWebserviceRequest.setAddMustUnderstandHandler(addMustUnderstandHandler);
        genericWebserviceRequest.setAddInsurabilityHandler(addInsurabilityHandler);

        return callGenericWebservice(samlToken, credential, genericWebserviceRequest, responseType);
    }

    public static <T> T callGenericWebservice(SAMLToken samlToken, Credential credential, Object request, Class<T> responseType, String endpointName, String serviceName, boolean addLoggingHandler, boolean addSoapFaultHandler, boolean addMustUnderstandHandler, boolean addInsurabilityHandler, String soapAction) throws IntegrationModuleException, TechnicalConnectorException {
        GenericWebserviceRequest genericWebserviceRequest = new GenericWebserviceRequest();
        genericWebserviceRequest.setRequest(request);
        genericWebserviceRequest.setRequestType(request.getClass());
        genericWebserviceRequest.setEndpoint(endpointName);
        genericWebserviceRequest.setServiceName(serviceName);
        genericWebserviceRequest.setAddLoggingHandler(addLoggingHandler);
        genericWebserviceRequest.setAddSoapFaultHandler(addSoapFaultHandler);
        genericWebserviceRequest.setAddMustUnderstandHandler(addMustUnderstandHandler);
        genericWebserviceRequest.setAddInsurabilityHandler(addInsurabilityHandler);
        genericWebserviceRequest.setSoapAction(soapAction);
        return callGenericWebservice(samlToken, credential, genericWebserviceRequest, responseType);
    }


    public static <T extends Object> T callGenericWebservice(SAMLToken samlToken, Credential credential, GenericWebserviceRequest genericWebserviceRequest, Class<T> responseType) throws IntegrationModuleException, TechnicalConnectorException {
        Validate.notNull(genericWebserviceRequest, "genericWebserviceRequest must be specified");
        Validate.notNull(genericWebserviceRequest.getRequest(), "request must be specified");
        Validate.notNull(genericWebserviceRequest.getEndpoint(), "endpoint must be specified");

        final String serviceName = StringUtils.defaultString(genericWebserviceRequest.getServiceName());
        try {

            Class<?> requestType = genericWebserviceRequest.getRequestType();
            if (requestType == null) {
                requestType = genericWebserviceRequest.getRequest().getClass();
            }

            String payload = JaxContextCentralizer.getInstance().toXml(requestType, genericWebserviceRequest.getRequest());

            GenericRequest genericRequest = new GenericRequest();
            genericRequest.setPayload(payload);
            genericRequest.setEndpoint(genericWebserviceRequest.getEndpoint());


            final HandlerChain handlerChain = new HandlerChain();
            if (genericWebserviceRequest.isAddLoggingHandler()) {
                LOG.info("LoggingHandler will be added");
                handlerChain.register(HandlerPosition.AFTER, new LoggingHandler());
            }
            if (genericWebserviceRequest.isAddSoapFaultHandler()) {
                LOG.info("SoapFaultHandler will be added");
                handlerChain.register(HandlerPosition.AFTER, new SoapFaultHandler());
            }
            if (genericWebserviceRequest.isAddMustUnderstandHandler()) {
                for (String property : PropertyHandler.getInstance().getMatchingProperties("connector.defaulthandlerchain.aftersecurity")) {
                    if (property.contains("MustUnderstandHandler")) {
                        LOG.info("MustUnderstandHandler will be added");
                        handlerChain.register(HandlerPosition.AFTER, new MustUnderstandHandler());
                    }
                }
            }
            if (genericWebserviceRequest.isAddInsurabilityHandler()) {
                LOG.info("InsurabilityHandler will be added");
                handlerChain.register(HandlerPosition.AFTER, new InsurabilityHandler());
            }

            genericRequest.setHandlerChain(handlerChain);

            final GenericResponse resp;

            genericRequest.setSamlSecured(samlToken.getAssertion(), credential);
            resp = sender.send(genericRequest);
            LOG.info(serviceName + "GenericWebservice received a response from service with endpoint:" + genericWebserviceRequest.getEndpoint());

            T response = JaxContextCentralizer.getInstance().toObject(responseType, resp.asString());
            return response;
        } catch (ConnectorException | SOAPException e) {
            LOG.error(String.format("%s generic webservice", e.getClass().getSimpleName()), e);
            String eHealthMessage = e.getLocalizedMessage();
            if (e.getCause() != null && StringUtils.isNotEmpty(e.getCause().getLocalizedMessage())) {
                eHealthMessage += " \nCause is: " + e.getCause().getLocalizedMessage();
            }
            throw new IntegrationModuleException(I18nHelper.getLabel("technical.connector.error.generic.webserive", new Object[]{serviceName, eHealthMessage}), e);
        } catch (GFDDPPException e) {
            LOG.error("Error generic webservice", e);
            throw new IntegrationModuleException(I18nHelper.getLabel("technical.connector.error.marshall.unmarshall"), e);
        }

    }
}
