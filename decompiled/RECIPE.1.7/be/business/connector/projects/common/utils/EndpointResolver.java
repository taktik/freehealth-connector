package org.taktik.connector.business.recipeprojects.common.utils;

import org.taktik.connector.business.recipeprojects.core.exceptions.IntegrationModuleException;
import org.taktik.connector.business.recipeprojects.core.utils.I18nHelper;
import org.taktik.connector.business.recipeprojects.core.utils.PropertyHandler;
import org.apache.commons.lang.StringUtils;

public class EndpointResolver {
   public static String getEndpointUrlString(String endpointName) throws IntegrationModuleException {
      String endpoint;
      if (StringUtils.equals("endpoint.tipsystem", endpointName)) {
         endpoint = null;
         if (PropertyHandler.getInstance().hasProperty("tipsystem.id")) {
            endpoint = PropertyHandler.getInstance().getProperty("tipsystem.id");
            if (StringUtils.isEmpty(endpoint)) {
               throw new IntegrationModuleException(I18nHelper.getLabel("error.validation.tipsystem.id"));
            } else {
               return SystemServicesUtils.getInstance(PropertyHandler.getInstance()).getEndpointOutOfSystemConfiguration(endpoint, "TIP", "TIPService");
            }
         } else {
            throw new IntegrationModuleException(I18nHelper.getLabel("error.validation.invalid.tip.id"));
         }
      } else if (StringUtils.equals("endpoint.pcdh", endpointName)) {
         endpoint = null;
         if (PropertyHandler.getInstance().hasProperty("pcdhsystem.id")) {
            endpoint = PropertyHandler.getInstance().getProperty("pcdhsystem.id");
            if (StringUtils.isEmpty(endpoint)) {
               throw new IntegrationModuleException(I18nHelper.getLabel("error.validation.pcdhsystem.id"));
            } else {
               return SystemServicesUtils.getInstance(PropertyHandler.getInstance()).getEndpointOutOfSystemConfiguration(endpoint, "PCDH", "PCDHService");
            }
         } else {
            throw new IntegrationModuleException(I18nHelper.getLabel("error.technical.pcdh.id.not.found"));
         }
      } else {
         endpoint = PropertyHandler.getInstance().getProperty(endpointName);
         if (StringUtils.isEmpty(endpoint)) {
            throw new IntegrationModuleException(I18nHelper.getLabel("technical.connector.error.endpoint.not.found", new Object[]{endpointName}));
         } else {
            return endpoint;
         }
      }
   }
}
