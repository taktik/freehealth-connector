package org.taktik.connector.business.recipeprojects.common.utils;



import org.apache.commons.lang.StringUtils;

import org.taktik.connector.business.recipeprojects.core.exceptions.IntegrationModuleException;
import org.taktik.connector.business.recipeprojects.core.utils.I18nHelper;
import org.taktik.connector.business.recipeprojects.core.utils.PropertyHandler;
import org.taktik.connector.business.recipeprojects.common.services.pcdh.PcdhServiceImpl;
import org.taktik.connector.business.recipeprojects.common.services.tipsystem.TipSystemServiceImpl;

public class EndpointResolver {

	public EndpointResolver() {

	}

	public static String getEndpointUrlString(String endpointName) throws IntegrationModuleException {
		if (StringUtils.equals(TipSystemServiceImpl.ENDPOINT_NAME, endpointName)) {
			String endpoint = null;
			if (PropertyHandler.getInstance().hasProperty("tipsystem.id")) {
				endpoint = PropertyHandler.getInstance().getProperty("tipsystem.id");
			} else {
				throw new IntegrationModuleException(I18nHelper.getLabel("error.validation.invalid.tip.id"));
			}
			if (StringUtils.isEmpty(endpoint)) {
				throw new IntegrationModuleException(I18nHelper.getLabel("error.validation.tipsystem.id"));
			}
			return SystemServicesUtils.getInstance(PropertyHandler.getInstance()).getEndpointOutOfSystemConfiguration(endpoint, "TIP", "TIPService");
		} else if (StringUtils.equals(PcdhServiceImpl.ENDPOINT_NAME, endpointName)) {
			String endpoint = null;
			if (PropertyHandler.getInstance().hasProperty("pcdhsystem.id")) {
				endpoint = PropertyHandler.getInstance().getProperty("pcdhsystem.id");
			} else {
				throw new IntegrationModuleException(I18nHelper.getLabel("error.technical.pcdh.id.not.found"));
			}
			if (StringUtils.isEmpty(endpoint)) {
				throw new IntegrationModuleException(I18nHelper.getLabel("error.validation.pcdhsystem.id"));
			}
			return SystemServicesUtils.getInstance(PropertyHandler.getInstance()).getEndpointOutOfSystemConfiguration(endpoint, "PCDH", "PCDHService");
		} else {
			String endpoint = PropertyHandler.getInstance().getProperty(endpointName);
			if (StringUtils.isEmpty(endpoint)) {
				throw new IntegrationModuleException(I18nHelper.getLabel("technical.connector.error.endpoint.not.found", new Object[] { endpointName }));
			}
			return endpoint;
		}
	}

}
