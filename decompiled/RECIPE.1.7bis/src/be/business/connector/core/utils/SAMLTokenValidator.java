package org.taktik.connector.business.recipeprojects.core.utils;

import org.taktik.connector.business.recipeprojects.core.exceptions.IntegrationModuleEhealthException;
import org.apache.log4j.Logger;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.Calendar;

/**
 * @author Liesje Demuynck.
 * Validates a SAML token
 */
public class SAMLTokenValidator {

    /** The Constant LOG. */
    private static final Logger LOG = Logger.getLogger(SAMLTokenValidator.class);

    /**
     * Checks if expired.
     * @param assertion the assertion to check
     *
     * @return true, if successful
     */
    private static boolean hasExpired(final Element assertion) {
        if (assertion != null && STSHelper.getConditions(assertion).getLength() > 0) {
            // NotOnOrAfter
            Calendar calNotOnOrAfter = Calendar.getInstance();
            calNotOnOrAfter.setTime(STSHelper.getNotOnOrAfterConditions(assertion).getTime());

            // now
            Calendar now = Calendar.getInstance();

            // check validity
            return !(now.before(calNotOnOrAfter));
        }
        return true;
    }

    /**
     * Checks for valid attributes.
     * @param assertion the assertion to check
     *
     * @return true, if successful
     */
    private static boolean hasValidAttributes(final Element assertion) {
        NodeList attributes = STSHelper.getAttributes(assertion);
        if (attributes == null || attributes.getLength() == 0) {
            return false;
        }
        for (int i=0; i < attributes.getLength(); i++) {
            Element attribute = (Element) attributes.item(i);
            LOG.debug("SAML AttributeName : "+ attribute.getAttribute("AttributeName") + " : TextContent : "+ attribute.getTextContent());
            if ("".equals(attribute.getTextContent().trim())) {
                LOG.error("Empty SAML attribute designator, eHealth doesn't recognised you... contact eHealth");
                throw new IntegrationModuleEhealthException(I18nHelper.getLabel("error.saml.attribute",new String[]{attribute.getAttribute("AttributeName")}));
            }
        }
        return true;
    }

    /**
     * Checks whether the provided assertion is still valid
     * @param assertion the assertion to check
     *
     * @return true, if successful
     */
    public static boolean isValid(final Element assertion) {
        return assertion != null && !hasExpired(assertion) && hasValidAttributes(assertion);
    }


}
