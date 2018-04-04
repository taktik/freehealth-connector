package org.taktik.connector.business.recipeprojects.common.utils;

import be.apb.gfddpp.common.exceptions.GFDDPPException;
import be.apb.gfddpp.common.productfilter.ProductFilterEngine;
import org.taktik.connector.business.recipeprojects.core.exceptions.IntegrationModuleException;
import org.taktik.connector.business.recipeprojects.core.utils.ConfigUtils;
import org.taktik.connector.business.recipeprojects.core.utils.I18nHelper;
import org.taktik.connector.business.recipeprojects.core.utils.PropertyHandler;
import java.io.File;
import java.io.IOException;
import org.apache.log4j.Logger;

public class ProductFilterSingleton {
   private static final Logger LOG = Logger.getLogger(ProductFilterSingleton.class);
   private ProductFilterEngine productFilterEngine = null;
   private static ProductFilterSingleton instance = null;
   private File productFilterXmlFile = null;
   private PropertyHandler propertyHandler;

   public ProductFilterSingleton() {
   }

   public ProductFilterSingleton(PropertyHandler propertyH) throws IntegrationModuleException {
      if (this.propertyHandler == null) {
         this.propertyHandler = propertyH;
      }

      if (this.propertyHandler.hasProperty("PRODUCT_FILTER_PATH")) {
         String productFilterPath = this.propertyHandler.getProperty("PRODUCT_FILTER_PATH");
         this.productFilterXmlFile = ConfigUtils.getLatestProductFilterFile(productFilterPath);
         if (this.productFilterXmlFile != null) {
            LOG.info("Loading productFilter : " + this.productFilterXmlFile.getAbsolutePath());
            this.reloadCache();
         }

      } else {
         throw new IntegrationModuleException(I18nHelper.getLabel("error.get.product.filter.failed"));
      }
   }

   public boolean isBlacklistedProduct(String id) {
      return this.productFilterEngine.isBlacklistedMedicine(id);
   }

   public boolean isWhitelistedProduct(String id) {
      return this.productFilterEngine.isWhitelistedMedicine(id);
   }

   public boolean isBlacklistedPreparation(String id) {
      return this.productFilterEngine.isBlacklistedPreparation(id);
   }

   public boolean isWhitelistedPreparation(String id) {
      return this.productFilterEngine.isWhitelistedPreparation(id);
   }

   public void reloadCache() throws IntegrationModuleException {
      if (this.productFilterXmlFile == null) {
         LOG.error("No productFilterXml found");
         throw new IntegrationModuleException(I18nHelper.getLabel("exception.creation.productlist"));
      } else {
         try {
            LOG.info("Getting product filter configuration from file: " + this.productFilterXmlFile.getName());
            this.productFilterEngine = new ProductFilterEngine(this.productFilterXmlFile.getParentFile().getCanonicalPath(), this.productFilterXmlFile.getName());
         } catch (IOException var2) {
            throw new IntegrationModuleException(I18nHelper.getLabel("exception.creation.productlist"), var2);
         } catch (GFDDPPException var3) {
            throw new IntegrationModuleException(var3.getLocalizedMessage());
         }
      }
   }

   public static ProductFilterSingleton getInstance(PropertyHandler propertyHandler2) throws IntegrationModuleException {
      if (instance == null) {
         instance = new ProductFilterSingleton(propertyHandler2);
      }

      return instance;
   }

   public static ProductFilterSingleton getInstance() {
      if (instance == null) {
         instance = new ProductFilterSingleton();
      }

      return instance;
   }

   public String getPrecedence() {
      return this.productFilterEngine.getPrecedence();
   }

   public void setPrecedence(String precedence) {
      this.productFilterEngine.setPrecedence(precedence);
   }

   public File getProductFilterXmlFile() {
      return this.productFilterXmlFile;
   }

   public void setProductFilterXmlFile(File productFilterXmlFile) {
      this.productFilterXmlFile = productFilterXmlFile;
   }
}
