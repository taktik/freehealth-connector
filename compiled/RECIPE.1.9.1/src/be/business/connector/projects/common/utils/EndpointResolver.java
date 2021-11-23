package be.business.connector.projects.common.utils;

import org.apache.commons.lang.StringUtils;

import be.business.connector.core.exceptions.IntegrationModuleException;
import be.business.connector.core.utils.I18nHelper;
import be.business.connector.core.utils.PropertyHandler;

public class EndpointResolver {

	private EndpointResolver() {
	}

	public static String getEndpointUrlString(String endpointName) throws IntegrationModuleException {
		String endpoint = PropertyHandler.getInstance().getProperty(endpointName);
		if (StringUtils.isEmpty(endpoint)) {
			throw new IntegrationModuleException(I18nHelper.getLabel("technical.connector.error.endpoint.not.found", new Object[] { endpointName }));
		}
		return endpoint;
	}
}