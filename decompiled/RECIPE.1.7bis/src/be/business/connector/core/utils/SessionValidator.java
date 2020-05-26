package org.taktik.connector.business.recipeprojects.core.utils;

import org.taktik.connector.business.recipeprojects.core.exceptions.IntegrationModuleException;
import be.ehealth.technicalconnector.session.SessionItem;

/**
 * @author Liesje Demuynck.
 */
public class SessionValidator {


    /**
     * Checks if the provided sessionItem is valid and if not: throws a error
     * @param sessionItem the sessionItem to check
     */
    public static  void assertValidSession(final SessionItem sessionItem) throws IntegrationModuleException {
        if (!isValidSession(sessionItem)) {
            throw new IntegrationModuleException(I18nHelper.getLabel("error.invalid.session"));
        }
    }

    /**
     * Checks if the provided sessionItem is valid
     * @param sessionItem the sessionItem to check
     * @return true if the sessionItem is valid, false otherwise
     */
    public static boolean isValidSession(final SessionItem sessionItem){
        return sessionItem != null && sessionItem.getSAMLToken() != null && SAMLTokenValidator.isValid(sessionItem.getSAMLToken().getAssertion());
    }

}
