/**
 * 
 */
package org.taktik.connector.business.recipeprojects.core.technical.connector.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import org.w3c.dom.Element;

import org.taktik.connector.business.recipeprojects.core.exceptions.IntegrationModuleException;
import org.taktik.connector.business.recipeprojects.core.utils.I18nHelper;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;

/**
 * @author yannick.landuyt
 *
 */
public class SAMLConverter {

	private static final Logger LOG = Logger.getLogger(SAMLConverter.class);

	/**
	 * 
	 */
	public SAMLConverter() {
		// TODO Auto-generated constructor stub
	}
	
	public static String toXMLString(Element assertion) throws IntegrationModuleException{
		if (assertion != null) {
			try {
				return be.ehealth.technicalconnector.service.sts.utils.SAMLConverter.toXMLString(assertion);
			} catch (TechnicalConnectorException e) {
				LOG.error("TechnicalConnectorException SAMLConverter webservice", e);
				String eHealthMessage = e.getLocalizedMessage();
				if (e.getCause() != null && StringUtils.isNotEmpty(e.getCause().getLocalizedMessage())) {
					eHealthMessage += " \nCause is: " + e.getCause().getLocalizedMessage();
				}
				throw new IntegrationModuleException(I18nHelper.getLabel("technical.connector.error.utils", new Object[] {"SAMLConverter", eHealthMessage }), e);
			}
		}
		return null;
	}

}
