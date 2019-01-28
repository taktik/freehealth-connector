package org.taktik.connector.business.recipeprojects.common.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import be.apb.gfddpp.common.utils.SingleMessageWrapper;
import be.apb.gfddpp.domain.ProductIdType;
import be.apb.gfddpp.rtrn.registerdata.Magistral;
import be.apb.standards.smoa.schema.id.v1.AbstractMedicinalProductIdType;
import be.apb.standards.smoa.schema.model.v1.CompoundType;
import be.apb.standards.smoa.schema.model.v1.MagistralPreparationType;
import be.apb.standards.smoa.schema.model.v1.MaxSetProductType;
import be.apb.standards.smoa.schema.v1.PharmaceuticalCareEventType;
import be.apb.standards.smoa.schema.v1.PharmaceuticalCareEventType.DispensedForSamePrescription;
import be.apb.standards.smoa.schema.v1.PharmaceuticalCareEventType.DispensedForSamePrescription.Product;
import be.apb.standards.smoa.schema.v1.PharmaceuticalCareEventType.DispensedWithoutPrescription;
import be.apb.standards.smoa.schema.v1.SingleMessage;
import org.taktik.connector.business.recipeprojects.core.exceptions.IntegrationModuleException;
import org.taktik.connector.business.recipeprojects.core.utils.I18nHelper;
import org.taktik.connector.business.recipeprojects.core.utils.PropertyHandler;

public class ProductFilterUtils {
	private final static Logger LOG = LogManager.getLogger(ProductFilterUtils.class);

	public SingleMessage filterProducts(SingleMessage singleMessage, PropertyHandler property) throws IntegrationModuleException {

		SingleMessageWrapper wrapper = new SingleMessageWrapper(singleMessage);

		filterProductFromPrescription(wrapper, property);
		filterProductWithoutPrescription(wrapper, property);

		List<DispensedForSamePrescription> dispensedForSamePrescriptions = new ArrayList<DispensedForSamePrescription>();
		for (int i = 0; i < wrapper.getAllDispensedForSamePrescriptions().size(); i++) {
			if (wrapper.getAllDispensedForSamePrescriptions().get(i).getProduct().size() == 0) {
				dispensedForSamePrescriptions.add(wrapper.getAllDispensedForSamePrescriptions().get(i));
			}
		}

		for (int i = 0; i < wrapper.getAllDispensedWithoutPrescriptions().size(); i++) {
			if (wrapper.getAllDispensedWithoutPrescriptions().get(i).getProduct().size() == 0) {
				wrapper.getAllEventsOfType(PharmaceuticalCareEventType.class).get(0).setDispensedWithoutPrescription(null);
			}
		}

		wrapper.getAllEventsOfType(PharmaceuticalCareEventType.class).get(0).getDispensedForSamePrescription().removeAll(dispensedForSamePrescriptions);

		return wrapper.getWrappedMessage();
	}

	private void filterProductFromPrescription(SingleMessageWrapper wrapper, PropertyHandler property) throws IntegrationModuleException {
		for (DispensedForSamePrescription dispensedForSamePrescription : wrapper.getAllDispensedForSamePrescriptions()) {
			List<Product> products = dispensedForSamePrescription.getProduct();
			List<Product> newProductList = new ArrayList<Product>();

			for (Product product : products) {
				newProductList.add(product);
			}

			for (Product product : newProductList) {
				AbstractMedicinalProductIdType productCode = product.getDescription().getProductCode();
				if (productCode != null) {
					filterBasedOnProductId(productCode, products, product, property);
				} else {
					MagistralPreparationType magistralPreparation = product.getDescription().getMagistralPreparation();
					if (magistralPreparation != null) {
						filterMagistralPreparationFromPrescription(magistralPreparation, products, product, property);
					} else {
						LOG.info("Not supported product : " + product.getDispensationGUID());
					}
				}
			}
		}
	}

	private void filterMagistralPreparationFromPrescription(MagistralPreparationType magistralPreparation, List<Product> products, Product product, PropertyHandler property) throws IntegrationModuleException {
		String cnk = null;
		if (magistralPreparation.getCompound() != null && !magistralPreparation.getCompound().isEmpty()) {
			int i = 0;
			boolean removed = false;
			while (i < magistralPreparation.getCompound().size() && !removed) {
				CompoundType compound = magistralPreparation.getCompound().get(i);
				if (compound.getMedicinalproduct() != null) {
					cnk = compound.getMedicinalproduct().getIntendedProduct();
					removed = filterBasedOnProductIdCNK(cnk, products, product, property);
				} else if (compound.getSubstance() != null) {
					cnk = compound.getSubstance().getSubstancecode();
					removed = filterPreparation(cnk, products, product);
				}
				i++;
			}
		} else if (magistralPreparation.getFormularyReference() != null) {
			cnk = magistralPreparation.getFormularyReference().getFormulaCode();
			filterPreparation(cnk, products, product);
		} else if (magistralPreparation.getMagistralText() != null) {
			LOG.info(product.getDispensationGUID() + " is a magistralText : Not filtered out");
		}
	}

	private void filterMagistralPreparationWithoutPrescription(MagistralPreparationType magistralPreparation, List<MaxSetProductType> products, MaxSetProductType product, PropertyHandler property) throws IntegrationModuleException {
		String cnk = null;
		if (magistralPreparation.getCompound() != null) {
			int i = 0;
			boolean removed = false;
			while (i < magistralPreparation.getCompound().size() && !removed) {
				CompoundType compound = magistralPreparation.getCompound().get(i);
				if (compound.getMedicinalproduct() != null) {
					cnk = compound.getMedicinalproduct().getIntendedProduct();
					removed = filterBasedOnMaxSetProductTypeId(cnk, products, product, property);
				} else if (compound.getSubstance() != null) {
					cnk = compound.getSubstance().getSubstancecode();
					removed = filterBasedOnMaxSetProductTypeId(cnk, products, product, property);
				}
				i++;
			}
		} else if (magistralPreparation.getFormularyReference() != null) {
			cnk = magistralPreparation.getFormularyReference().getFormulaCode();
			filterPreparationWithoutPrescription(cnk, products, product, property);
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
			if (pfs.isBlacklistedPreparation(cnk)) {
				if (!pfs.isWhitelistedPreparation(cnk)) {
					LOG.debug("Incoming into filter: " + cnk + " is blacklisted");
					removed = products.remove(product);
					LOG.info("Product with dguid " + product.getDispensationGUID() + " removed from SMC, ProductFilter");
				}
			}
		} else if (pfs.getPrecedence().toLowerCase().equals("white")) {
			if (!pfs.isWhitelistedPreparation(cnk) || pfs.isBlacklistedPreparation(cnk)) {
				LOG.debug("Incoming into filter: " + cnk + " is blacklisted or not whitelisted");
				removed = products.remove(product);
				LOG.info("Product with dguid " + product.getDispensationGUID() + " removed from SMC, ProductFilter");
			}
		} else {
			throw new IntegrationModuleException(I18nHelper.getLabel("prescencetype.unknown"));
		}
		LOG.debug("Outgoing filter: " + cnk);
		return removed;
	}

	private boolean filterPreparation(String cnk, List<Product> products, Product product) throws IntegrationModuleException {
		LOG.debug("Incoming into filter: " + cnk);
		boolean removed = false;
		ProductFilterSingleton pfs = ProductFilterSingleton.getInstance();

		if (StringUtils.isEmpty(pfs.getPrecedence())) {
			pfs.setPrecedence("black");
		}
		if (pfs.getPrecedence().toLowerCase().equals("black")) {
			if (pfs.isBlacklistedPreparation(cnk)) {
				if (!pfs.isWhitelistedPreparation(cnk)) {
					LOG.debug("Incoming into filter: " + cnk + " is blacklisted");
					removed = products.remove(product);
					LOG.info("Product with dguid " + product.getDispensationGUID() + " removed from SMC, ProductFilter");
				}
			}
		} else if (pfs.getPrecedence().toLowerCase().equals("white")) {
			if (!pfs.isWhitelistedPreparation(cnk) || pfs.isBlacklistedPreparation(cnk)) {
				LOG.debug("Incoming into filter: " + cnk + " is blacklisted or not whitelisted");
				removed = products.remove(product);
				LOG.info("Product with dguid " + product.getDispensationGUID() + " removed from SMC, ProductFilter");
			}
		} else {
			throw new IntegrationModuleException(I18nHelper.getLabel("prescencetype.unknown"));
		}
		LOG.debug("Outgoing filter: " + cnk);
		return removed;
	}

	private void filterBasedOnProductId(AbstractMedicinalProductIdType productCode, List<Product> products, Product product, PropertyHandler property) throws IntegrationModuleException {
		ProductIdType productType = ProductIdType.valueOf(productCode);
		String productKey = productType.getIdFrom(productCode);
		switch (productType) {
		case CNK:
			filterBasedOnProductIdCNK(productKey, products, product, property);
			break;
		default:
			break;
		}
	}

	private void filterProductWithoutPrescription(SingleMessageWrapper wrapper, PropertyHandler property) throws IntegrationModuleException {
		for (DispensedWithoutPrescription dispensedWithoutPrescription : wrapper.getAllDispensedWithoutPrescriptions()) {
			List<MaxSetProductType> products = dispensedWithoutPrescription.getProduct();
			List<MaxSetProductType> newProductList = new ArrayList<MaxSetProductType>();

			for (MaxSetProductType product : products) {
				newProductList.add(product);
			}

			for (MaxSetProductType product : newProductList) {
				//				String productKey = getId(product.getDescription().getProductCode());
				AbstractMedicinalProductIdType productCode = product.getDescription().getProductCode();
				if (productCode != null) {
					ProductIdType productType = ProductIdType.valueOf(productCode);
					String productKey = productType.getIdFrom(productCode);
					//				filterBasedOnMaxSetPruductTypeId(productKey, products, product, property);
					switch (productType) {
					case CNK:
						filterBasedOnMaxSetProductTypeId(productKey, products, product, property);
						break;
					default:
						break;
					}
				} else {
					MagistralPreparationType magistralPreparation = product.getDescription().getMagistralPreparation();
					if (magistralPreparation != null) {
						filterMagistralPreparationWithoutPrescription(magistralPreparation, products, product, property);
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
			if (pfs.isBlacklistedProduct(productKey)) {
				if (!pfs.isWhitelistedProduct(productKey)) {
					LOG.info("Incoming into filter: " + productKey + " is blacklisted");
					removed = productList.remove(product);
				}
			}
		} else if (pfs.getPrecedence().toLowerCase().equals("white")) {
			if (!pfs.isWhitelistedProduct(productKey) || pfs.isBlacklistedProduct(productKey)) {
				LOG.info("Incoming into filter: " + productKey + " is blacklisted or not whitelisted");
				removed = productList.remove(product);
			}
		} else {
			throw new IntegrationModuleException(I18nHelper.getLabel("prescencetype.unknown"));
		}
		LOG.debug("Outgoing filter: " + productKey);
		return removed;
	}

	private boolean filterBasedOnProductIdCNK(String productKey, List<Product> productList, Product product, PropertyHandler property) throws IntegrationModuleException {
		LOG.debug("Incoming into filter: " + productKey);
		boolean removed = false;
		ProductFilterSingleton pfs = ProductFilterSingleton.getInstance();

		if (StringUtils.isEmpty(pfs.getPrecedence())) {
			pfs.setPrecedence("black");
		}
		if (pfs.getPrecedence().toLowerCase().equals("black")) {
			if (pfs.isBlacklistedProduct(productKey)) {
				if (!pfs.isWhitelistedProduct(productKey)) {
					LOG.debug("Incoming into filter: " + productKey + " is blacklisted");
					removed = productList.remove(product);
					LOG.info("Product with dguid " + product.getDispensationGUID() + " removed from SMC, ProductFilter");
				}
			}
		} else if (pfs.getPrecedence().toLowerCase().equals("white")) {
			if (!pfs.isWhitelistedProduct(productKey) || pfs.isBlacklistedProduct(productKey)) {
				LOG.debug("Incoming into filter: " + productKey + " is blacklisted or not whitelisted");
				removed = productList.remove(product);
				LOG.info("Product with dguid " + product.getDispensationGUID() + " removed from SMC, ProductFilter");
			}
		} else {
			throw new IntegrationModuleException(I18nHelper.getLabel("prescencetype.unknown"));
		}
		LOG.debug("Outgoing filter: " + productKey);
		return removed;
	}

	public static String getId(AbstractMedicinalProductIdType amp) throws IntegrationModuleException {
		//		if (CnkIdType.class.isInstance(amp)) {
		//			CnkIdType cit = (CnkIdType) amp;
		//			return "" + cit.getCnk();
		//		} else if (AtcDddSystemIdType.class.isInstance(amp)) {
		//			AtcDddSystemIdType atc = (AtcDddSystemIdType) amp;
		//			return "" + atc;
		//		} else if (BeRegistrationIdType.class.isInstance(amp)) {
		//			BeRegistrationIdType beRegistrationIdType = (BeRegistrationIdType) amp;
		//			return "" + beRegistrationIdType;
		//		} else {
		//			throw new IntegrationModuleException(I18nHelper.getLabel("productfilter.unknown.producttype"));
		//		}

		ProductIdType productType = ProductIdType.valueOf(amp);
		return productType.getIdFrom(amp);
	}

	public static Magistral getId(MagistralPreparationType mpt) {
		Magistral magistral = new Magistral();
		if (mpt.getFormularyReference() != null) {
			magistral.getMagistralIds().add(mpt.getFormularyReference().getFormulaCode());
		} else {
			for (CompoundType ct : mpt.getCompound()) {
				String id = null;
				if (ct.getMedicinalproduct() != null) {
					id = ct.getMedicinalproduct().getIntendedProduct();
				} else if (ct.getSubstance() != null) {
					id = ct.getSubstance().getSubstancecode();
				}
				magistral.getMagistralIds().add(id);
			}
		}
		return magistral;
	}
}
