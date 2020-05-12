package be.business.connector.recipe.executor.mock;

import be.business.connector.core.exceptions.IntegrationModuleException;
import be.business.connector.recipe.executor.ExecutorIntegrationModuleDevV4Impl;
import be.business.connector.recipe.executor.domain.GetPrescriptionForExecutorResult;
import be.recipe.services.core.PrescriptionStatus;
import be.recipe.services.executor.GetOpenPrescriptionForExecutor;
import be.recipe.services.executor.GetPrescriptionStatusParam;
import be.recipe.services.executor.GetPrescriptionStatusResult;
import be.recipe.services.executor.ListOpenPrescriptionsParam;
import be.recipe.services.executor.ListOpenPrescriptionsResult;
import be.recipe.services.executor.ListReservationsParam;
import be.recipe.services.executor.ListReservationsResult;
import be.recipe.services.executor.ListReservationsResultItem;
import be.recipe.services.executor.ListRidsHistoryParam;
import be.recipe.services.executor.ListRidsHistoryResult;
import be.recipe.services.executor.ListRidsHistoryResultItem;
import be.recipe.services.executor.ListRidsInProcessParam;
import be.recipe.services.executor.ListRidsInProcessResult;
import be.recipe.services.executor.PutRidsInProcessParam;
import be.recipe.services.executor.PutRidsInProcessResult;
import be.recipe.services.executor.PutRidsInProcessResultItem;
import java.util.Calendar;
import java.util.Random;

public class ExecutorIntegrationModuleV4Mock extends ExecutorIntegrationModuleDevV4Impl {
   public ExecutorIntegrationModuleV4Mock() throws IntegrationModuleException {
   }

   public ListOpenPrescriptionsResult getData(ListOpenPrescriptionsParam request) throws IntegrationModuleException {
      System.out.println("Page for month: " + request.getPage().getMonth() + " and year " + request.getPage().getYear());
      ListOpenPrescriptionsResult result = new ListOpenPrescriptionsResult();
      int random = (new Random()).nextInt(10);

      for(int i = 1; i < random; ++i) {
         GetOpenPrescriptionForExecutor goplr = new GetOpenPrescriptionForExecutor();
         goplr.setRid("BEP0" + i);
         goplr.setCreationDate(Calendar.getInstance());
         goplr.setPatientId("patient" + i);
         result.getPrescriptions().add(goplr);
      }

      return result;
   }

   public GetPrescriptionStatusResult getData(GetPrescriptionStatusParam request) throws IntegrationModuleException {
      GetPrescriptionStatusResult result = new GetPrescriptionStatusResult();
      int pick = (new Random()).nextInt(PrescriptionStatus.values().length);
      result.setPrescriptionStatus(PrescriptionStatus.values()[pick]);
      return result;
   }

   public ListRidsHistoryResult getData(ListRidsHistoryParam request) throws IntegrationModuleException {
      System.out.println("Page for month: " + request.getPage().getMonth() + " and year " + request.getPage().getYear());
      ListRidsHistoryResult result = new ListRidsHistoryResult();
      int random = (new Random()).nextInt(10);

      for(int i = 1; i < random; ++i) {
         ListRidsHistoryResultItem item1 = new ListRidsHistoryResultItem();
         item1.setRid("BEP0JNT9191" + i);
         int pick = (new Random()).nextInt(PrescriptionStatus.values().length);
         item1.setPrescriptionStatus(PrescriptionStatus.values()[pick]);
         result.getItems().add(item1);
      }

      return result;
   }

   public ListReservationsResult getData(ListReservationsParam request) throws IntegrationModuleException {
      ListReservationsResult result = new ListReservationsResult();
      int random = (new Random()).nextInt(10);

      for(int i = 1; i < random; ++i) {
         ListReservationsResultItem item = new ListReservationsResultItem();
         item.setExecutorId("123456789");
         item.setRid("BEP0JNT9191" + i);
         result.getItems().add(item);
      }

      return result;
   }

   public ListRidsInProcessResult getData(ListRidsInProcessParam request) throws IntegrationModuleException {
      ListRidsInProcessResult result = new ListRidsInProcessResult();
      int random = (new Random()).nextInt(10);

      for(int i = 1; i < random; ++i) {
         result.getRids().add("BEP0JNT9191");
      }

      return result;
   }

   public PutRidsInProcessResult putData(PutRidsInProcessParam request) throws IntegrationModuleException {
      PutRidsInProcessResult result = new PutRidsInProcessResult();
      int random = (new Random()).nextInt(10);

      for(int i = 1; i < random; ++i) {
         PutRidsInProcessResultItem item = new PutRidsInProcessResultItem();
         item.setRid("123456789");
         int pick = (new Random()).nextInt(PrescriptionStatus.values().length);
         item.setPrescriptionStatus(PrescriptionStatus.values()[pick]);
         item.setStatusUpdater("987654321");
         result.getItems().add(item);
      }

      return result;
   }

   public GetPrescriptionForExecutorResult getAndMarkAsDelivered(String rid) throws IntegrationModuleException {
      throw new UnsupportedOperationException();
   }
}
