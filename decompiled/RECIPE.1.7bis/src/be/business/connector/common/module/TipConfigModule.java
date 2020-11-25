package be.business.connector.common.module;

import org.taktik.connector.business.recipeprojects.core.exceptions.IntegrationModuleException;

/**
 * @author Liesje Demuynck.
 */
public interface TipConfigModule {

    /**
     * Retrieves the latest product filter from the TIP system
     *
     * @throws IntegrationModuleException
     */
    void getLatestProductFilter() throws IntegrationModuleException;

    /**
     * Retrieves the latest system services from the TIP system
     *
     * @throws IntegrationModuleException
     */
    void getLatestSystemServices() throws IntegrationModuleException;

    /**
     * Aggregate for the getLatestProductFilter and getLatestSystemServices operations.
     * @throws IntegrationModuleException
     */
    void getLatestTIPConfig() throws IntegrationModuleException;
}
