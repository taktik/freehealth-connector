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
import org.taktik.connector.business.recipeprojects.core.utils.SessionValidator;
import be.ehealth.technicalconnector.exception.ConnectorException;
import be.ehealth.technicalconnector.session.Session;
import be.ehealth.technicalconnector.session.SessionItem;
import be.ehealth.technicalconnector.ws.GenericWsSender;
import be.ehealth.technicalconnector.ws.domain.GenericRequest;
import be.ehealth.technicalconnector.ws.domain.GenericResponse;
import be.ehealth.technicalconnector.ws.domain.HandlerChain;
import be.ehealth.technicalconnector.ws.domain.HandlerPosition;
import be.ehealth.technicalconnector.ws.impl.GenericWsSenderImpl;
import java.util.Iterator;
import javax.xml.soap.SOAPException;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.apache.log4j.Logger;

public abstract class GenericWebserviceCaller {
   private static final Logger LOG = Logger.getLogger(GenericWebserviceCaller.class);
   private static GenericWsSender sender = new GenericWsSenderImpl();

   public static <T> T callGenericWebservice(Object request, Class<T> responseType, String endpointName, String serviceName, boolean addLoggingHandler, boolean addSoapFaultHandler, boolean addMustUnderstandHandler, boolean addInsurabilityHandler) throws IntegrationModuleException {
      return callGenericWebservice(request, request.getClass(), responseType, endpointName, serviceName, addLoggingHandler, addSoapFaultHandler, addMustUnderstandHandler, addInsurabilityHandler);
   }

   public static <T> T callGenericWebservice(Object request, Class<?> requestType, Class<T> responseType, String endpoint, String serviceName, boolean addLoggingHandler, boolean addSoapFaultHandler, boolean addMustUnderstandHandler, boolean addInsurabilityHandler) throws IntegrationModuleException {
      GenericWebserviceRequest genericWebserviceRequest = new GenericWebserviceRequest();
      genericWebserviceRequest.setRequest(request);
      genericWebserviceRequest.setRequestType(requestType);
      genericWebserviceRequest.setEndpoint(endpoint);
      genericWebserviceRequest.setServiceName(serviceName);
      genericWebserviceRequest.setAddLoggingHandler(addLoggingHandler);
      genericWebserviceRequest.setAddSoapFaultHandler(addSoapFaultHandler);
      genericWebserviceRequest.setAddMustUnderstandHandler(addMustUnderstandHandler);
      genericWebserviceRequest.setAddInsurabilityHandler(addInsurabilityHandler);
      return callGenericWebservice(genericWebserviceRequest, responseType);
   }

   public static <T> T callGenericWebservice(GenericWebserviceRequest genericWebserviceRequest, Class<T> responseType) throws IntegrationModuleException {
      Validate.notNull(genericWebserviceRequest, "genericWebserviceRequest must be specified");
      Validate.notNull(genericWebserviceRequest.getRequest(), "request must be specified");
      Validate.notNull(genericWebserviceRequest.getEndpoint(), "endpoint must be specified");
      String serviceName = StringUtils.defaultString(genericWebserviceRequest.getServiceName());

      String eHealthMessage;
      try {
         Class<?> requestType = genericWebserviceRequest.getRequestType();
         if (requestType == null) {
            requestType = genericWebserviceRequest.getRequest().getClass();
         }

         eHealthMessage = JaxContextCentralizer.getInstance().toXml(requestType, genericWebserviceRequest.getRequest());
         GenericRequest genericRequest = new GenericRequest();
         genericRequest.setPayload(eHealthMessage);
         genericRequest.setEndpoint(genericWebserviceRequest.getEndpoint());
         HandlerChain handlerChain = new HandlerChain();
         if (genericWebserviceRequest.isAddLoggingHandler()) {
            LOG.info("LoggingHandler will be added");
            handlerChain.register(HandlerPosition.AFTER, new LoggingHandler());
         }

         if (genericWebserviceRequest.isAddSoapFaultHandler()) {
            LOG.info("SoapFaultHandler will be added");
            handlerChain.register(HandlerPosition.AFTER, new SoapFaultHandler());
         }

         if (genericWebserviceRequest.isAddMustUnderstandHandler()) {
            Iterator var8 = PropertyHandler.getInstance().getMatchingProperties("connector.defaulthandlerchain.aftersecurity").iterator();

            while(var8.hasNext()) {
               String property = (String)var8.next();
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
         SessionItem sessionItem = Session.getInstance().getSession();
         SessionValidator.assertValidSession(sessionItem);
         genericRequest.setSamlSecured(sessionItem.getSAMLToken().getAssertion(), sessionItem.getHolderOfKeyCredential());
         GenericResponse resp = sender.send(genericRequest);
         LOG.info(serviceName + "GenericWebservice received a response from service with endpoint:" + genericWebserviceRequest.getEndpoint());
         T response = JaxContextCentralizer.getInstance().toObject(responseType, resp.asString());
         return response;
      } catch (SOAPException | ConnectorException var10) {
         LOG.error(String.format("%s generic webservice", var10.getClass().getSimpleName()), var10);
         eHealthMessage = var10.getLocalizedMessage();
         if (var10.getCause() != null && StringUtils.isNotEmpty(var10.getCause().getLocalizedMessage())) {
            eHealthMessage = eHealthMessage + " \nCause is: " + var10.getCause().getLocalizedMessage();
         }

         throw new IntegrationModuleException(I18nHelper.getLabel("technical.connector.error.generic.webserive", new Object[]{serviceName, eHealthMessage}), var10);
      } catch (GFDDPPException var11) {
         LOG.error("Error generic webservice", var11);
         throw new IntegrationModuleException(I18nHelper.getLabel("technical.connector.error.marshall.unmarshall"), var11);
      }
   }
}
