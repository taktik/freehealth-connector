package be.business.connector.core.utils;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import be.business.connector.core.exceptions.IntegrationModuleEhealthException;

/**
 * @author Liesje Demuynck. Validates a SAML token
 */
public class SAMLTokenValidator {

	/** The Constant LOG. */
	private static final Logger LOG = Logger.getLogger(SAMLTokenValidator.class);

	/**
	 * Checks if expired.
	 * 
	 * @param assertion
	 *            the assertion to check
	 *
	 * @return true, if successful
	 */
	private static boolean hasExpired(final Element assertion) {
		if (assertion != null && STSHelper.getConditions(assertion).getLength() > 0) {
			// NotOnOrAfter
			final Calendar calNotOnOrAfter = Calendar.getInstance();
			calNotOnOrAfter.setTime(STSHelper.getNotOnOrAfterConditions(assertion).getTime());

			// now
			final Calendar now = Calendar.getInstance();

			// check validity
			return !(now.before(calNotOnOrAfter));
		}
		return true;
	}

	/**
	 * Checks for valid attributes.
	 * 
	 * @param assertion
	 *            the assertion to check
	 *
	 * @return true, if successful
	 */
	private static boolean hasValidAttributes(final Element assertion) {
		final NodeList attributes = STSHelper.getAttributes(assertion);
		if (attributes == null || attributes.getLength() == 0) {
			return false;
		}
		for (int i = 0; i < attributes.getLength(); i++) {
			final Element attribute = (Element) attributes.item(i);
			LOG.debug("SAML AttributeName : " + attribute.getAttribute("AttributeName") + " : TextContent : " + attribute.getTextContent());
			if ("".equals(attribute.getTextContent().trim())) {
				LOG.error("Empty SAML attribute designator, eHealth doesn't recognised you... contact eHealth");
				throw new IntegrationModuleEhealthException(
						I18nHelper.getLabel("error.saml.attribute", new String[] { attribute.getAttribute("AttributeName") }));
			}
		}
		return true;
	}

	/**
	 * Checks for valid attributes.
	 * 
	 * @param assertion
	 *            the assertion to check
	 *
	 * @return true, if successful
	 */
	private static boolean hasValidPharmacyAttributes(final Element assertion) {
		final NodeList attributes = STSHelper.getAttributes(assertion);
		if (attributes == null || attributes.getLength() == 0) {
			return false;
		}
		Map<String, String> failedAttributes = new HashMap<String, String>();
		for (int i = 0; i < attributes.getLength(); i++) {
			final Element attribute = (Element) attributes.item(i);
			LOG.debug("SAML AttributeName : " + attribute.getAttribute("AttributeName") + " : TextContent : " + attribute.getTextContent());
			if ("urn:be:fgov:ehealth:1.0:pharmacy:nihii-number:recognisedpharmacy:boolean".equals(attribute.getAttribute("AttributeName"))
					&& attribute.getTextContent().equals("false")) {
				failedAttributes.put(attribute.getAttribute("AttributeName"), attribute.getTextContent());
			}
			if ("urn:be:fgov:ehealth:1.0:pharmacy:nihii-number:person:ssin:ehealth:1.0:pharmacy-holder:boolean"
					.equals(attribute.getAttribute("AttributeName")) && attribute.getTextContent().equals("false")) {
				failedAttributes.put(attribute.getAttribute("AttributeName"), attribute.getTextContent());
			}

			if (failedAttributes.size() > 0) {
				LOG.error("Not a recognized , eHealth doesn't recognised you... contact eHealth");
				throw new IntegrationModuleEhealthException(
						I18nHelper.getLabel("error.false.saml.attribute", new Object[] { toString(failedAttributes) }));
			}
		}
		return true;
	}

	private static boolean hasValidHospitalPharmacyAttributes(final Element assertion) {
		final NodeList attributes = STSHelper.getAttributes(assertion);
		if (attributes == null || attributes.getLength() == 0) {
			return false;
		}
		Map<String, String> failedAttributes = new HashMap<String, String>();
		for (int i = 0; i < attributes.getLength(); i++) {
			final Element attribute = (Element) attributes.item(i);
			LOG.debug("SAML AttributeName : " + attribute.getAttribute("AttributeName") + " : TextContent : " + attribute.getTextContent());
			if ("urn:be:fgov:ehealth:1.0:pharmacy:nihii-number:recognisedhospitalpharmacy:boolean"
					.equals(attribute.getAttribute("AttributeName"))
					&& attribute.getTextContent().equals("false")) {
				failedAttributes.put(attribute.getAttribute("AttributeName"), attribute.getTextContent());
			}
			if ("urn:be:fgov:ehealth:1.0:certificateholder:hospital:nihii-number:recognisedhospital:boolean"
					.equals(attribute.getAttribute("AttributeName")) && attribute.getTextContent().equals("false")) {
				failedAttributes.put(attribute.getAttribute("AttributeName"), attribute.getTextContent());
			}

			if (failedAttributes.size() > 0) {
				LOG.error("Not a recognized , eHealth doesn't recognised you... contact eHealth");
				throw new IntegrationModuleEhealthException(
						I18nHelper.getLabel("error.false.saml.attribute", new Object[] { toString(failedAttributes) }));
			}
		}
		return true;
	}

	private static String toString(final Map<String, String> failedAttributes) {
		final StringBuilder sb = new StringBuilder();
		for (String key : failedAttributes.keySet()) {
			final String value = failedAttributes.get(key);
			sb.append(key + "[" + value + "];");
		}
		return sb.toString();
	}

	/**
	 * Checks whether the provided assertion is still valid
	 * 
	 * @param assertion
	 *            the assertion to check
	 *
	 * @return true, if successful
	 */
	public static boolean isValid(final Element assertion) {
		return assertion != null && !hasExpired(assertion) && hasValidAttributes(assertion);
	}

	/**
	 * Checks whether the provided assertion is still valid
	 * 
	 * @param assertion
	 *            the assertion to check
	 *
	 * @return true, if successful
	 */
	public static boolean isValidPharmacySession(final Element assertion) {
		return assertion != null && !hasExpired(assertion) && hasValidAttributes(assertion) && hasValidPharmacyAttributes(assertion);
	}

	/**
	 * Checks whether the provided assertion is still valid
	 * 
	 * @param assertion
	 *            the assertion to check
	 *
	 * @return true, if successful
	 */
	public static boolean isValidHospitalPharmacySession(final Element assertion) {
		return assertion != null && !hasExpired(assertion) && hasValidAttributes(assertion)
				&& hasValidHospitalPharmacyAttributes(assertion);
	}

}
