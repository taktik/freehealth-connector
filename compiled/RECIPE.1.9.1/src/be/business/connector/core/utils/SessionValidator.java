package be.business.connector.core.utils;

import be.business.connector.core.exceptions.IntegrationModuleException;
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
     * Checks if the provided sessionItem is valid and if not: throws a error
     * @param sessionItem the sessionItem to check
     */
    public static  void assertValidPharmacySession(final SessionItem sessionItem) throws IntegrationModuleException {
        if (!isValidPharmacySession(sessionItem)) {
            throw new IntegrationModuleException(I18nHelper.getLabel("error.invalid.session"));
        }
    }

    /**
	 * Checks if the provided sessionItem is valid and if not: throws a error
	 * 
	 * @param sessionItem
	 *            the sessionItem to check
	 */
	public static void assertValidHospitalPharmacySession(final SessionItem sessionItem) throws IntegrationModuleException {
		if (!isValidHospitalPharmacySession(sessionItem)) {
			throw new IntegrationModuleException(I18nHelper.getLabel("error.invalid.session"));
		}
	}

	/**
	 * Checks if the provided sessionItem is valid
	 * 
	 * @param sessionItem
	 *            the sessionItem to check
	 * @return true if the sessionItem is valid, false otherwise
	 */
    public static boolean isValidSession(final SessionItem sessionItem){
        return sessionItem != null && sessionItem.getSAMLToken() != null && SAMLTokenValidator.isValid(sessionItem.getSAMLToken().getAssertion());
    }
    
    /**
     * Checks if the provided sessionItem is valid
     * @param sessionItem the sessionItem to check
     * @return true if the sessionItem is valid, false otherwise
     */
    public static boolean isValidPharmacySession(final SessionItem sessionItem){
		return sessionItem != null && sessionItem.getSAMLToken() != null
				&& SAMLTokenValidator.isValidPharmacySession(sessionItem.getSAMLToken().getAssertion());
    }
    
	/**
	 * Checks if the provided sessionItem is valid
	 * 
	 * @param sessionItem
	 *            the sessionItem to check
	 * @return true if the sessionItem is valid, false otherwise
	 */
	public static boolean isValidHospitalPharmacySession(final SessionItem sessionItem) {
		return sessionItem != null && sessionItem.getSAMLToken() != null
				&& SAMLTokenValidator.isValidHospitalPharmacySession(sessionItem.getSAMLToken().getAssertion());
	}
    

}
