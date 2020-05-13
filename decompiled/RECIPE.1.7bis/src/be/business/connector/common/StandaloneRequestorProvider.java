package be.business.connector.common;

import be.apb.gfddpp.domain.PharmacyIdType;
import org.taktik.connector.business.recipeprojects.core.exceptions.IntegrationModuleException;
import org.taktik.connector.business.recipeprojects.core.utils.I18nHelper;
import org.taktik.connector.business.recipeprojects.core.utils.PropertyHandler;
import org.taktik.connector.business.recipeprojects.core.utils.STSHelper;
import org.taktik.connector.business.recipeprojects.core.utils.SessionValidator;
import be.ehealth.technicalconnector.session.Session;
import be.ehealth.technicalconnector.session.SessionItem;
import org.apache.commons.lang3.StringUtils;
import org.w3c.dom.Element;

/**
 * @author Liesje Demuynck.
 *         same functionality as RequestorProvider, but usable as a standAlone provider with abstract methods
 */
public class StandaloneRequestorProvider {

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

        try {
            PharmacyIdType.valueOf(type);
        } catch (IllegalArgumentException e) {
            throw new IntegrationModuleException(I18nHelper.getLabel("error.validation.saml.type.not.found"), e);
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

    /**
     * Retrieves the requestor Id information either from the properties or (if not found in the properties) from the sts token in the session.
     *
     * @return the requestor Id information either from the properties or (if not found in the properties) from the sts token in the session.
     * @throws IntegrationModuleException
     */
    public static String getRequestorIdInformation() throws IntegrationModuleException {
        ApplicationConfig.getInstance().assertInitialized();
        return getRequestorIdInformation(Session.getInstance().getSession(), PropertyHandler.getInstance());
    }

}
