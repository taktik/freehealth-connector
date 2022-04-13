package be.business.connector.common;

import org.apache.commons.lang3.StringUtils;

import be.business.connector.core.exceptions.IntegrationModuleException;
import be.business.connector.core.utils.I18nHelper;
import be.business.connector.core.utils.PropertyHandler;
import be.business.connector.core.utils.STSHelper;
import be.business.connector.core.utils.SessionValidator;
import be.ehealth.technicalconnector.session.Session;
import be.ehealth.technicalconnector.session.SessionItem;

/**
 * @author Liesje Demuynck.
 *         same functionality as RequestorProvider, but usable as a standAlone provider with abstract methods
 */
public class StandaloneRequestorProvider {

	private StandaloneRequestorProvider() {
	}

    /**
     * Retrieves the requestor Id information either from the properties or (if not found in the properties) from the sts token in the session.
     *
     * @return the requestor Id information either from the properties or (if not found in the properties) from the sts token in the session.
     * @throws IntegrationModuleException
     */
    public static String getRequestorTypeInformation() throws IntegrationModuleException {
        ApplicationConfig.getInstance().assertInitialized();
        return getRequestorTypeInformation(Session.getInstance().getSession(), PropertyHandler.getInstance());
    }

	/**
	 * Retrieves the requestor Id information either from the properties or (if not found in the properties) from the sts token in the
	 * session.
	 *
	 * @return the requestor Id information either from the properties or (if not found in the properties) from the sts token in the
	 *         session.
	 * @throws IntegrationModuleException
	 */
	public static String getRequestorIdInformation() throws IntegrationModuleException {
		ApplicationConfig.getInstance().assertInitialized();
		return getRequestorIdInformation(Session.getInstance().getSession(), PropertyHandler.getInstance());
	}

    /**
     * Retrieves the requestor Type information either from the properties or (if not found in the properties) from the sts token
     *
     * @param sessionItem     the sessionItem
     * @param propertyHandler the propertyHandler
     * @return the requestor Id information either from the properties or (if not found in the properties) from the sts token
     * @throws IntegrationModuleException
     */
    private static String getRequestorTypeInformation(final SessionItem sessionItem, final PropertyHandler propertyHandler) throws IntegrationModuleException {
        SessionValidator.assertValidSession(sessionItem);
        String type;
        if (propertyHandler != null && propertyHandler.hasProperty("requesttype")) {
            type = propertyHandler.getProperty("requesttype");
        } else {
            type = STSHelper.getType(sessionItem.getSAMLToken().getAssertion());
        }
        return type;
    }

    /**
     * Retrieves the requestor Id information either from the properties or (if not found in the properties) from the sts token
     *
     * @param sessionItem     the sessionItem
     * @param propertyHandler the propertyHandler
     * @return the requestor Id information either from the properties or (if not found in the properties) from the sts token
     * @throws IntegrationModuleException
     */
    private static String getRequestorIdInformation(final SessionItem sessionItem, final PropertyHandler propertyHandler) throws IntegrationModuleException {
        String nihii = null;
        if (propertyHandler != null && propertyHandler.hasProperty("requestid")) {
            nihii = propertyHandler.getProperty("requestid");
        } else {
            SessionValidator.assertValidSession(sessionItem);
            nihii = STSHelper.getNihii(sessionItem.getSAMLToken().getAssertion());
        }

        if (StringUtils.isBlank(nihii)) {
            throw new IntegrationModuleException(I18nHelper.getLabel("error.validation.saml.nihii.not.found"));
        }
        return nihii;
    }
}