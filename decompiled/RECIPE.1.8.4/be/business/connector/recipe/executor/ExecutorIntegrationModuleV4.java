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

public interface ExecutorIntegrationModuleV4 extends ExecutorIntegrationModule {
   ListOpenPrescriptionsResult getData(ListOpenPrescriptionsParam var1) throws IntegrationModuleException;

   GetPrescriptionStatusResult getData(GetPrescriptionStatusParam var1);

   ListRidsHistoryResult getData(ListRidsHistoryParam var1);

   ListReservationsResult getData(ListReservationsParam var1);

   ListRidsInProcessResult getData(ListRidsInProcessParam var1);

   PutRidsInProcessResult putData(PutRidsInProcessParam var1);

   GetPrescriptionForExecutorResult getAndMarkAsDelivered(String var1);

   ListRelationsResult getData(ListRelationsParam var1);

   GetOpenPrescriptionForExecutor decryptGetOpenPrescriptionForExecutor(GetOpenPrescriptionForExecutor var1) throws IntegrationModuleException;
}
