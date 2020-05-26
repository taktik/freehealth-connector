package be.business.connector.common.module;

import be.business.connector.core.exceptions.IntegrationModuleException;

public interface TipConfigModule {
   void getLatestProductFilter() throws IntegrationModuleException;

   void getLatestSystemServices() throws IntegrationModuleException;

   void getLatestTIPConfig() throws IntegrationModuleException;
}
