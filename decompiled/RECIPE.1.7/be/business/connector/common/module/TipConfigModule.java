package be.business.connector.common.module;

import org.taktik.connector.business.recipeprojects.core.exceptions.IntegrationModuleException;

public interface TipConfigModule {
   void getLatestProductFilter() throws IntegrationModuleException;

   void getLatestSystemServices() throws IntegrationModuleException;

   void getLatestTIPConfig() throws IntegrationModuleException;
}
