package be.business.connector.recipe.executor;

import be.business.connector.core.exceptions.IntegrationModuleException;
import be.business.connector.recipe.executor.domain.GetPrescriptionForExecutorResult;
import be.recipe.services.executor.GetOpenPrescriptionForExecutor;
import be.recipe.services.executor.GetPrescriptionStatusParam;
import be.recipe.services.executor.GetPrescriptionStatusResult;
import be.recipe.services.executor.ListOpenPrescriptionsParam;
import be.recipe.services.executor.ListOpenPrescriptionsResult;
import be.recipe.services.executor.ListRelationsParam;
import be.recipe.services.executor.ListRelationsResult;
import be.recipe.services.executor.ListReservationsParam;
import be.recipe.services.executor.ListReservationsResult;
import be.recipe.services.executor.ListRidsHistoryParam;
import be.recipe.services.executor.ListRidsHistoryResult;
import be.recipe.services.executor.ListRidsInProcessParam;
import be.recipe.services.executor.ListRidsInProcessResult;
import be.recipe.services.executor.PutRidsInProcessParam;
import be.recipe.services.executor.PutRidsInProcessResult;

public interface ExecutorIntegrationModuleDevV4 extends ExecutorIntegrationModuleV4 {
   ListOpenPrescriptionsResult getData(ListOpenPrescriptionsParam var1) throws IntegrationModuleException;

   GetOpenPrescriptionForExecutor decryptGetOpenPrescriptionForExecutor(GetOpenPrescriptionForExecutor var1) throws IntegrationModuleException;

   GetPrescriptionStatusResult getData(GetPrescriptionStatusParam var1) throws IntegrationModuleException;

   ListRidsHistoryResult getData(ListRidsHistoryParam var1) throws IntegrationModuleException;

   ListReservationsResult getData(ListReservationsParam var1) throws IntegrationModuleException;

   ListRidsInProcessResult getData(ListRidsInProcessParam var1) throws IntegrationModuleException;

   PutRidsInProcessResult putData(PutRidsInProcessParam var1) throws IntegrationModuleException;

   GetPrescriptionForExecutorResult getAndMarkAsDelivered(String var1) throws IntegrationModuleException;

   ListRelationsResult getData(ListRelationsParam var1);
}
