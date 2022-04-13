package be.business.connector.core.services;

import javax.xml.soap.SOAPException;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.apache.log4j.Logger;

import be.apb.gfddpp.common.utils.JaxContextCentralizer;
import be.business.connector.core.exceptions.IntegrationModuleException;
import be.business.connector.core.handlers.InsurabilityHandler;
import be.business.connector.core.handlers.LoggingHandler;
import be.business.connector.core.handlers.MustUnderstandHandler;
import be.business.connector.core.handlers.SoapFaultHandler;
import be.business.connector.core.utils.I18nHelper;
import be.business.connector.core.utils.PropertyHandler;
import be.business.connector.core.utils.SessionValidator;
import be.ehealth.technicalconnector.exception.ConnectorException;
import be.ehealth.technicalconnector.session.Session;
import be.ehealth.technicalconnector.session.SessionItem;
import be.ehealth.technicalconnector.ws.GenericWsSender;
import be.ehealth.technicalconnector.ws.domain.GenericRequest;
import be.ehealth.technicalconnector.ws.domain.GenericResponse;
import be.ehealth.technicalconnector.ws.domain.HandlerChain;
import be.ehealth.technicalconnector.ws.domain.HandlerPosition;
import be.ehealth.technicalconnector.ws.impl.GenericWsSenderImpl;

/**
 * The Class GenericWebserviceCaller.
 */
public abstract class GenericWebserviceCaller {

	/** The Constant LOG. */
	private static final Logger LOG = Logger.getLogger(GenericWebserviceCaller.class);

	/** The sender. */
	private static GenericWsSender sender = new GenericWsSenderImpl();

	/**
	 * Call generic webservice.
	 *
	 * @param <T>
	 *            the generic type
	 * @param request
	 *            the request
	 * @param responseType
	 *            the response type
	 * @param endpointName
	 *            the endpoint name
	 * @param serviceName
	 *            the service name
	 * @param addLoggingHandler
	 *            the add logging handler
	 * @param addSoapFaultHandler
	 *            the add soap fault handler
	 * @param addMustUnderstandHandler
	 *            the add must understand handler
	 * @param addInsurabilityHandler
	 *            the add insurability handler
	 * @return the t
	 * @throws IntegrationModuleException
	 *             the integration module exception
	 */
	public static <T extends Object> T callGenericWebservice(final Object request, final Class<T> responseType, final String endpointName,
			final String serviceName, final boolean addLoggingHandler, final boolean addSoapFaultHandler, final boolean addMustUnderstandHandler,
			final boolean addInsurabilityHandler)
			throws IntegrationModuleException {
		return callGenericWebservice(request, request.getClass(), responseType, endpointName, serviceName, addLoggingHandler,
				addSoapFaultHandler, addMustUnderstandHandler, addInsurabilityHandler);
	}
	
	/**
	 * Call generic webservice.
	 *
	 * @param <T>
	 *            the generic type
	 * @param request
	 *            the request
	 * @param requestType
	 *            the request type
	 * @param responseType
	 *            the response type
	 * @param endpoint
	 *            the endpoint
	 * @param serviceName
	 *            the service name
	 * @param addLoggingHandler
	 *            the add logging handler
	 * @param addSoapFaultHandler
	 *            the add soap fault handler
	 * @param addMustUnderstandHandler
	 *            the add must understand handler
	 * @param addInsurabilityHandler
	 *            the add insurability handler
	 * @return the t
	 * @throws IntegrationModuleException
	 *             the integration module exception
	 */
	public static <T extends Object> T callGenericWebservice(final Object request, final Class<?> requestType, final Class<T> responseType,
			final String endpoint, final String serviceName, final boolean addLoggingHandler, final boolean addSoapFaultHandler,
			final boolean addMustUnderstandHandler, final boolean addInsurabilityHandler) throws IntegrationModuleException {
		final GenericWebserviceRequest genericWebserviceRequest = new GenericWebserviceRequest();
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
	
	/**
	 * Call generic webservice.
	 *
	 * @param <T>
	 *            the generic type
	 * @param request
	 *            the request
	 * @param responseType
	 *            the response type
	 * @param endpointName
	 *            the endpoint name
	 * @param serviceName
	 *            the service name
	 * @param addLoggingHandler
	 *            the add logging handler
	 * @param addSoapFaultHandler
	 *            the add soap fault handler
	 * @param addMustUnderstandHandler
	 *            the add must understand handler
	 * @param addInsurabilityHandler
	 *            the add insurability handler
	 * @param soapAction
	 *            the soap action
	 * @return the t
	 */
	public static <T extends Object> T callGenericWebservice(final Object request, final Class<T> responseType, final String endpointName,
			final String serviceName, final boolean addLoggingHandler, final boolean addSoapFaultHandler, final boolean addMustUnderstandHandler,
			final boolean addInsurabilityHandler, final String soapAction) {
		final GenericWebserviceRequest genericWebserviceRequest = new GenericWebserviceRequest();
		genericWebserviceRequest.setRequest(request);
		genericWebserviceRequest.setRequestType(request.getClass());
		genericWebserviceRequest.setEndpoint(endpointName);
		genericWebserviceRequest.setServiceName(serviceName);
		genericWebserviceRequest.setAddLoggingHandler(addLoggingHandler);
		genericWebserviceRequest.setAddSoapFaultHandler(addSoapFaultHandler);
		genericWebserviceRequest.setAddMustUnderstandHandler(addMustUnderstandHandler);
		genericWebserviceRequest.setAddInsurabilityHandler(addInsurabilityHandler);
		genericWebserviceRequest.setSoapAction(soapAction);
		return callGenericWebservice(genericWebserviceRequest, responseType);
	}

	/**
	 * Call generic webservice.
	 *
	 * @param <T>
	 *            the generic type
	 * @param genericWebserviceRequest
	 *            the generic webservice request
	 * @param responseType
	 *            the response type
	 * @return the t
	 * @throws IntegrationModuleException
	 *             the integration module exception
	 */
	public static <T extends Object> T callGenericWebservice(final GenericWebserviceRequest genericWebserviceRequest, final Class<T> responseType)
			throws IntegrationModuleException {
		Validate.notNull(genericWebserviceRequest, "genericWebserviceRequest must be specified");
		Validate.notNull(genericWebserviceRequest.getRequest(), "request must be specified");
		Validate.notNull(genericWebserviceRequest.getEndpoint(), "endpoint must be specified");

		final String serviceName = StringUtils.defaultString(genericWebserviceRequest.getServiceName());
		try {

			Class<?> requestType = genericWebserviceRequest.getRequestType();
			if (requestType == null) {
				requestType = genericWebserviceRequest.getRequest().getClass();
			}

			final String payload = JaxContextCentralizer.getInstance().toXml(requestType, genericWebserviceRequest.getRequest());

			final GenericRequest genericRequest = new GenericRequest();
			genericRequest.setPayload(payload);
			genericRequest.setEndpoint(genericWebserviceRequest.getEndpoint());
			genericRequest.setSoapAction(genericWebserviceRequest.getSoapAction());

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
				for (final String property : PropertyHandler.getInstance().getMatchingProperties("connector.defaulthandlerchain.aftersecurity")) {
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

			genericRequest.addHandlerChain(handlerChain);

			final GenericResponse resp;

			final SessionItem sessionItem = Session.getInstance().getSession();
			SessionValidator.assertValidSession(sessionItem);
			genericRequest.setSamlSecured(sessionItem.getSAMLToken().getAssertion(), sessionItem.getHolderOfKeyCredential());
			resp = sender.send(genericRequest);
			LOG.info(serviceName + "GenericWebservice received a response from service with endpoint:"
					+ genericWebserviceRequest.getEndpoint());
			final T response = JaxContextCentralizer.getInstance().toObject(responseType, resp.asString());
			return response;
		} catch (ConnectorException | SOAPException e) {
			LOG.error(String.format("%s generic webservice", e.getClass().getSimpleName()), e);
			String eHealthMessage = e.getLocalizedMessage();
			if (e.getCause() != null && StringUtils.isNotEmpty(e.getCause().getLocalizedMessage())) {
				eHealthMessage += " \nCause is: " + e.getCause().getLocalizedMessage();
			}
			throw new IntegrationModuleException(
					I18nHelper.getLabel("technical.connector.error.generic.webserive", new Object[] { serviceName, eHealthMessage }), e);
		} catch (final Exception e) {
			LOG.error("Error generic webservice", e);
			throw new IntegrationModuleException(e);
		}
	}
}