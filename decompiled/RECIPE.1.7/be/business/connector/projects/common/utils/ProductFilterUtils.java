package org.taktik.connector.business.recipeprojects.common.utils;

import be.apb.gfddpp.common.utils.SingleMessageWrapper;
import be.apb.gfddpp.domain.ProductIdType;
import be.apb.gfddpp.rtrn.registerdata.Magistral;
import be.apb.standards.smoa.schema.id.v1.AbstractMedicinalProductIdType;
import be.apb.standards.smoa.schema.model.v1.CompoundType;
import be.apb.standards.smoa.schema.model.v1.MagistralPreparationType;
import be.apb.standards.smoa.schema.model.v1.MaxSetProductType;
import be.apb.standards.smoa.schema.v1.PharmaceuticalCareEventType;
import be.apb.standards.smoa.schema.v1.SingleMessage;
import org.taktik.connector.business.recipeprojects.core.exceptions.IntegrationModuleException;
import org.taktik.connector.business.recipeprojects.core.utils.I18nHelper;
import org.taktik.connector.business.recipeprojects.core.utils.PropertyHandler;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

public class ProductFilterUtils {
   private static final Logger LOG = Logger.getLogger(ProductFilterUtils.class);
   // $FF: synthetic field
   private static int[] $SWITCH_TABLE$be$apb$gfddpp$domain$ProductIdType;

   public SingleMessage filterProducts(SingleMessage singleMessage, PropertyHandler property) throws IntegrationModuleException {
      SingleMessageWrapper wrapper = new SingleMessageWrapper(singleMessage);
      this.filterProductFromPrescription(wrapper, property);
      this.filterProductWithoutPrescription(wrapper, property);
      List<PharmaceuticalCareEventType.DispensedForSamePrescription> dispensedForSamePrescriptions = new ArrayList();

      int i;
      for(i = 0; i < wrapper.getAllDispensedForSamePrescriptions().size(); ++i) {
         if (((PharmaceuticalCareEventType.DispensedForSamePrescription)wrapper.getAllDispensedForSamePrescriptions().get(i)).getProduct().size() == 0) {
            dispensedForSamePrescriptions.add((PharmaceuticalCareEventType.DispensedForSamePrescription)wrapper.getAllDispensedForSamePrescriptions().get(i));
         }
      }

      for(i = 0; i < wrapper.getAllDispensedWithoutPrescriptions().size(); ++i) {
         if (((PharmaceuticalCareEventType.DispensedWithoutPrescription)wrapper.getAllDispensedWithoutPrescriptions().get(i)).getProduct().size() == 0) {
            ((PharmaceuticalCareEventType)wrapper.getAllEventsOfType(PharmaceuticalCareEventType.class).get(0)).setDispensedWithoutPrescription((PharmaceuticalCareEventType.DispensedWithoutPrescription)null);
         }
      }

      ((PharmaceuticalCareEventType)wrapper.getAllEventsOfType(PharmaceuticalCareEventType.class).get(0)).getDispensedForSamePrescription().removeAll(dispensedForSamePrescriptions);
      return wrapper.getWrappedMessage();
   }

   private void filterProductFromPrescription(SingleMessageWrapper wrapper, PropertyHandler property) throws IntegrationModuleException {
      Iterator var4 = wrapper.getAllDispensedForSamePrescriptions().iterator();

      while(var4.hasNext()) {
         PharmaceuticalCareEventType.DispensedForSamePrescription dispensedForSamePrescription = (PharmaceuticalCareEventType.DispensedForSamePrescription)var4.next();
         List<PharmaceuticalCareEventType.DispensedForSamePrescription.Product> products = dispensedForSamePrescription.getProduct();
         List<PharmaceuticalCareEventType.DispensedForSamePrescription.Product> newProductList = new ArrayList();
         Iterator var8 = products.iterator();

         PharmaceuticalCareEventType.DispensedForSamePrescription.Product product;
         while(var8.hasNext()) {
            product = (PharmaceuticalCareEventType.DispensedForSamePrescription.Product)var8.next();
            newProductList.add(product);
         }

         var8 = newProductList.iterator();

         while(var8.hasNext()) {
            product = (PharmaceuticalCareEventType.DispensedForSamePrescription.Product)var8.next();
            AbstractMedicinalProductIdType productCode = product.getDescription().getProductCode();
            if (productCode != null) {
               this.filterBasedOnProductId(productCode, products, product, property);
            } else {
               MagistralPreparationType magistralPreparation = product.getDescription().getMagistralPreparation();
               if (magistralPreparation != null) {
                  this.filterMagistralPreparationFromPrescription(magistralPreparation, products, product, property);
               } else {
                  LOG.info("Not supported product : " + product.getDispensationGUID());
               }
            }
         }
      }

   }

   private void filterMagistralPreparationFromPrescription(MagistralPreparationType magistralPreparation, List<PharmaceuticalCareEventType.DispensedForSamePrescription.Product> products, PharmaceuticalCareEventType.DispensedForSamePrescription.Product product, PropertyHandler property) throws IntegrationModuleException {
      String cnk = null;
      if (magistralPreparation.getCompound() != null && !magistralPreparation.getCompound().isEmpty()) {
         int i = 0;

         for(boolean removed = false; i < magistralPreparation.getCompound().size() && !removed; ++i) {
            CompoundType compound = (CompoundType)magistralPreparation.getCompound().get(i);
            if (compound.getMedicinalproduct() != null) {
               cnk = compound.getMedicinalproduct().getIntendedProduct();
               removed = this.filterBasedOnProductIdCNK(cnk, products, product, property);
            } else if (compound.getSubstance() != null) {
               cnk = compound.getSubstance().getSubstancecode();
               removed = this.filterPreparation(cnk, products, product);
            }
         }
      } else if (magistralPreparation.getFormularyReference() != null) {
         cnk = magistralPreparation.getFormularyReference().getFormulaCode();
         this.filterPreparation(cnk, products, product);
      } else if (magistralPreparation.getMagistralText() != null) {
         LOG.info(product.getDispensationGUID() + " is a magistralText : Not filtered out");
      }

   }

   private void filterMagistralPreparationWithoutPrescription(MagistralPreparationType magistralPreparation, List<MaxSetProductType> products, MaxSetProductType product, PropertyHandler property) throws IntegrationModuleException {
      String cnk = null;
      if (magistralPreparation.getCompound() != null) {
         int i = 0;

         for(boolean removed = false; i < magistralPreparation.getCompound().size() && !removed; ++i) {
            CompoundType compound = (CompoundType)magistralPreparation.getCompound().get(i);
            if (compound.getMedicinalproduct() != null) {
               cnk = compound.getMedicinalproduct().getIntendedProduct();
               removed = this.filterBasedOnMaxSetProductTypeId(cnk, products, product, property);
            } else if (compound.getSubstance() != null) {
               cnk = compound.getSubstance().getSubstancecode();
               removed = this.filterBasedOnMaxSetProductTypeId(cnk, products, product, property);
            }
         }
      } else if (magistralPreparation.getFormularyReference() != null) {
         cnk = magistralPreparation.getFormularyReference().getFormulaCode();
         this.filterPreparationWithoutPrescription(cnk, products, product, property);
      } else if (magistralPreparation.getMagistralText() != null) {
         LOG.info(product.getDispensationGUID() + " is a magistralText : Not filtered out");
      }

   }

   private boolean filterPreparationWithoutPrescription(String cnk, List<MaxSetProductType> products, MaxSetProductType product, PropertyHandler property) throws IntegrationModuleException {
      LOG.debug("Incoming into filter: " + cnk);
      boolean removed = false;
      ProductFilterSingleton pfs = ProductFilterSingleton.getInstance();
      if (StringUtils.isEmpty(pfs.getPrecedence())) {
         pfs.setPrecedence("black");
      }

      if (pfs.getPrecedence().toLowerCase().equals("black")) {
         if (pfs.isBlacklistedPreparation(cnk) && !pfs.isWhitelistedPreparation(cnk)) {
            LOG.debug("Incoming into filter: " + cnk + " is blacklisted");
            removed = products.remove(product);
            LOG.info("Product with dguid " + product.getDispensationGUID() + " removed from SMC, ProductFilter");
         }
      } else {
         if (!pfs.getPrecedence().toLowerCase().equals("white")) {
            throw new IntegrationModuleException(I18nHelper.getLabel("prescencetype.unknown"));
         }

         if (!pfs.isWhitelistedPreparation(cnk) || pfs.isBlacklistedPreparation(cnk)) {
            LOG.debug("Incoming into filter: " + cnk + " is blacklisted or not whitelisted");
            removed = products.remove(product);
            LOG.info("Product with dguid " + product.getDispensationGUID() + " removed from SMC, ProductFilter");
         }
      }

      LOG.debug("Outgoing filter: " + cnk);
      return removed;
   }

   private boolean filterPreparation(String cnk, List<PharmaceuticalCareEventType.DispensedForSamePrescription.Product> products, PharmaceuticalCareEventType.DispensedForSamePrescription.Product product) throws IntegrationModuleException {
      LOG.debug("Incoming into filter: " + cnk);
      boolean removed = false;
      ProductFilterSingleton pfs = ProductFilterSingleton.getInstance();
      if (StringUtils.isEmpty(pfs.getPrecedence())) {
         pfs.setPrecedence("black");
      }

      if (pfs.getPrecedence().toLowerCase().equals("black")) {
         if (pfs.isBlacklistedPreparation(cnk) && !pfs.isWhitelistedPreparation(cnk)) {
            LOG.debug("Incoming into filter: " + cnk + " is blacklisted");
            removed = products.remove(product);
            LOG.info("Product with dguid " + product.getDispensationGUID() + " removed from SMC, ProductFilter");
         }
      } else {
         if (!pfs.getPrecedence().toLowerCase().equals("white")) {
            throw new IntegrationModuleException(I18nHelper.getLabel("prescencetype.unknown"));
         }

         if (!pfs.isWhitelistedPreparation(cnk) || pfs.isBlacklistedPreparation(cnk)) {
            LOG.debug("Incoming into filter: " + cnk + " is blacklisted or not whitelisted");
            removed = products.remove(product);
            LOG.info("Product with dguid " + product.getDispensationGUID() + " removed from SMC, ProductFilter");
         }
      }

      LOG.debug("Outgoing filter: " + cnk);
      return removed;
   }

   private void filterBasedOnProductId(AbstractMedicinalProductIdType productCode, List<PharmaceuticalCareEventType.DispensedForSamePrescription.Product> products, PharmaceuticalCareEventType.DispensedForSamePrescription.Product product, PropertyHandler property) throws IntegrationModuleException {
      ProductIdType productType = ProductIdType.valueOf(productCode);
      String productKey = productType.getIdFrom(productCode);
      switch($SWITCH_TABLE$be$apb$gfddpp$domain$ProductIdType()[productType.ordinal()]) {
      case 2:
         this.filterBasedOnProductIdCNK(productKey, products, product, property);
      default:
      }
   }

   private void filterProductWithoutPrescription(SingleMessageWrapper wrapper, PropertyHandler property) throws IntegrationModuleException {
      Iterator var4 = wrapper.getAllDispensedWithoutPrescriptions().iterator();

      while(var4.hasNext()) {
         PharmaceuticalCareEventType.DispensedWithoutPrescription dispensedWithoutPrescription = (PharmaceuticalCareEventType.DispensedWithoutPrescription)var4.next();
         List<MaxSetProductType> products = dispensedWithoutPrescription.getProduct();
         List<MaxSetProductType> newProductList = new ArrayList();
         Iterator var8 = products.iterator();

         MaxSetProductType product;
         while(var8.hasNext()) {
            product = (MaxSetProductType)var8.next();
            newProductList.add(product);
         }

         var8 = newProductList.iterator();

         while(var8.hasNext()) {
            product = (MaxSetProductType)var8.next();
            AbstractMedicinalProductIdType productCode = product.getDescription().getProductCode();
            if (productCode != null) {
               ProductIdType productType = ProductIdType.valueOf(productCode);
               String productKey = productType.getIdFrom(productCode);
               switch($SWITCH_TABLE$be$apb$gfddpp$domain$ProductIdType()[productType.ordinal()]) {
               case 2:
                  this.filterBasedOnMaxSetProductTypeId(productKey, products, product, property);
               }
            } else {
               MagistralPreparationType magistralPreparation = product.getDescription().getMagistralPreparation();
               if (magistralPreparation != null) {
                  this.filterMagistralPreparationWithoutPrescription(magistralPreparation, products, product, property);
               } else {
                  LOG.info("Not supported product : " + product.getDispensationGUID());
               }
            }
         }
      }

   }

   private boolean filterBasedOnMaxSetProductTypeId(String productKey, List<MaxSetProductType> productList, MaxSetProductType product, PropertyHandler property) throws IntegrationModuleException {
      LOG.debug("Incoming into filter: " + productKey);
      boolean removed = false;
      ProductFilterSingleton pfs = ProductFilterSingleton.getInstance();
      if (StringUtils.isEmpty(pfs.getPrecedence())) {
         pfs.setPrecedence("black");
      }

      if (pfs.getPrecedence().toLowerCase().equals("black")) {
         if (pfs.isBlacklistedProduct(productKey) && !pfs.isWhitelistedProduct(productKey)) {
            LOG.info("Incoming into filter: " + productKey + " is blacklisted");
            removed = productList.remove(product);
         }
      } else {
         if (!pfs.getPrecedence().toLowerCase().equals("white")) {
            throw new IntegrationModuleException(I18nHelper.getLabel("prescencetype.unknown"));
         }

         if (!pfs.isWhitelistedProduct(productKey) || pfs.isBlacklistedProduct(productKey)) {
            LOG.info("Incoming into filter: " + productKey + " is blacklisted or not whitelisted");
            removed = productList.remove(product);
         }
      }

      LOG.debug("Outgoing filter: " + productKey);
      return removed;
   }

   private boolean filterBasedOnProductIdCNK(String productKey, List<PharmaceuticalCareEventType.DispensedForSamePrescription.Product> productList, PharmaceuticalCareEventType.DispensedForSamePrescription.Product product, PropertyHandler property) throws IntegrationModuleException {
      LOG.debug("Incoming into filter: " + productKey);
      boolean removed = false;
      ProductFilterSingleton pfs = ProductFilterSingleton.getInstance();
      if (StringUtils.isEmpty(pfs.getPrecedence())) {
         pfs.setPrecedence("black");
      }

      if (pfs.getPrecedence().toLowerCase().equals("black")) {
         if (pfs.isBlacklistedProduct(productKey) && !pfs.isWhitelistedProduct(productKey)) {
            LOG.debug("Incoming into filter: " + productKey + " is blacklisted");
            removed = productList.remove(product);
            LOG.info("Product with dguid " + product.getDispensationGUID() + " removed from SMC, ProductFilter");
         }
      } else {
         if (!pfs.getPrecedence().toLowerCase().equals("white")) {
            throw new IntegrationModuleException(I18nHelper.getLabel("prescencetype.unknown"));
         }

         if (!pfs.isWhitelistedProduct(productKey) || pfs.isBlacklistedProduct(productKey)) {
            LOG.debug("Incoming into filter: " + productKey + " is blacklisted or not whitelisted");
            removed = productList.remove(product);
            LOG.info("Product with dguid " + product.getDispensationGUID() + " removed from SMC, ProductFilter");
         }
      }

      LOG.debug("Outgoing filter: " + productKey);
      return removed;
   }

   public static String getId(AbstractMedicinalProductIdType amp) throws IntegrationModuleException {
      ProductIdType productType = ProductIdType.valueOf(amp);
      return productType.getIdFrom(amp);
   }

   public static Magistral getId(MagistralPreparationType mpt) {
      Magistral magistral = new Magistral();
      String id;
      if (mpt.getFormularyReference() != null) {
         magistral.getMagistralIds().add(mpt.getFormularyReference().getFormulaCode());
      } else {
         for(Iterator var3 = mpt.getCompound().iterator(); var3.hasNext(); magistral.getMagistralIds().add(id)) {
            CompoundType ct = (CompoundType)var3.next();
            id = null;
            if (ct.getMedicinalproduct() != null) {
               id = ct.getMedicinalproduct().getIntendedProduct();
            } else if (ct.getSubstance() != null) {
               id = ct.getSubstance().getSubstancecode();
            }
         }
      }

      return magistral;
   }

   // $FF: synthetic method
   static int[] $SWITCH_TABLE$be$apb$gfddpp$domain$ProductIdType() {
      int[] var10000 = $SWITCH_TABLE$be$apb$gfddpp$domain$ProductIdType;
      if ($SWITCH_TABLE$be$apb$gfddpp$domain$ProductIdType != null) {
         return var10000;
      } else {
         int[] var0 = new int[ProductIdType.values().length];

         try {
            var0[ProductIdType.ATC.ordinal()] = 1;
         } catch (NoSuchFieldError var3) {
            ;
         }

         try {
            var0[ProductIdType.BEREGISTRATIONID.ordinal()] = 3;
         } catch (NoSuchFieldError var2) {
            ;
         }

         try {
            var0[ProductIdType.CNK.ordinal()] = 2;
         } catch (NoSuchFieldError var1) {
            ;
         }

         $SWITCH_TABLE$be$apb$gfddpp$domain$ProductIdType = var0;
         return var0;
      }
   }
}
