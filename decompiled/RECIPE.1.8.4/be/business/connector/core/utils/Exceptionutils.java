package be.business.connector.core.utils;

import be.apb.gfddpp.validation.exception.SingleMessageValidationException;
import be.business.connector.core.exceptions.IntegrationModuleEhealthException;
import be.business.connector.core.exceptions.IntegrationModuleException;
import be.business.connector.core.exceptions.IntegrationModuleRuntimeException;
import be.business.connector.core.exceptions.IntegrationModuleValidationException;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;

public class Exceptionutils {
   private static final Logger LOG = Logger.getLogger(Exceptionutils.class);

   private Exceptionutils() {
   }

   public static void errorHandler(Throwable t) {
      if (t instanceof IntegrationModuleRuntimeException) {
         LOG.error("", t);
         throw (IntegrationModuleRuntimeException)t;
      } else if (t instanceof IntegrationModuleEhealthException) {
         LOG.error("", t);
         throw (IntegrationModuleEhealthException)t;
      } else if (t.getCause() instanceof IntegrationModuleEhealthException) {
         LOG.error("", t);
         throw (IntegrationModuleEhealthException)t.getCause();
      } else if (!(t instanceof IntegrationModuleValidationException)) {
         if (t instanceof IntegrationModuleException) {
            LOG.error("", t);
            throw (IntegrationModuleException)t;
         } else if (t instanceof SingleMessageValidationException) {
            LOG.error("", t);
            throw new IntegrationModuleException(I18nHelper.getLabel("error.single.message.validation"), t);
         } else {
            LOG.error("", t);
            throw new IntegrationModuleException(I18nHelper.getLabel("error.technical"), t);
         }
      } else {
         List<String> list = ((IntegrationModuleValidationException)t).getValidationErrors();
         if (!CollectionUtils.isEmpty(list)) {
            LOG.info("***************** ValidationErrors begin *****************");
            Iterator var3 = list.iterator();

            while(var3.hasNext()) {
               String error = (String)var3.next();
               LOG.info("ValidationError occured: " + error);
            }

            LOG.info("***************** ValidationErrors  end  *****************");
         }

         throw (IntegrationModuleValidationException)t;
      }
   }

   public static void errorHandler(Throwable t, String errorMsg) {
      if (t instanceof IntegrationModuleRuntimeException) {
         LOG.error("", t);
         throw (IntegrationModuleRuntimeException)t;
      } else if (t instanceof IntegrationModuleEhealthException) {
         LOG.error("", t);
         throw (IntegrationModuleEhealthException)t;
      } else if (t.getCause() instanceof IntegrationModuleEhealthException) {
         LOG.error("", t);
         throw (IntegrationModuleEhealthException)t.getCause();
      } else if (!(t instanceof IntegrationModuleValidationException)) {
         if (t instanceof IntegrationModuleException) {
            LOG.error("", t);
            throw (IntegrationModuleException)t;
         } else if (t instanceof SingleMessageValidationException) {
            LOG.error("", t);
            throw new IntegrationModuleException(I18nHelper.getLabel("error.single.message.validation"), t);
         } else {
            LOG.error("", t);
            throw new IntegrationModuleException(I18nHelper.getLabel(errorMsg), t);
         }
      } else {
         List<String> list = ((IntegrationModuleValidationException)t).getValidationErrors();
         if (!CollectionUtils.isEmpty(list)) {
            LOG.info("***************** ValidationErrors begin *****************");
            Iterator var4 = list.iterator();

            while(var4.hasNext()) {
               String error = (String)var4.next();
               LOG.info("ValidationError occured: " + error);
            }

            LOG.info("***************** ValidationErrors  end  *****************");
         }

         throw (IntegrationModuleValidationException)t;
      }
   }
}
