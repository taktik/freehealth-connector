package org.taktik.connector.business.recipeprojects.common.utils;

import java.io.File;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import be.apb.gfddpp.common.exceptions.GFDDPPException;
import be.apb.gfddpp.common.productfilter.ProductFilterEngine;
import org.taktik.connector.business.recipeprojects.core.exceptions.IntegrationModuleException;
import org.taktik.connector.business.recipeprojects.core.utils.ConfigUtils;
import org.taktik.connector.business.recipeprojects.core.utils.I18nHelper;
import org.taktik.connector.business.recipeprojects.core.utils.PropertyHandler;

public class ProductFilterSingleton {
	private final static Logger LOG = LogManager.getLogger(ProductFilterSingleton.class);
	private ProductFilterEngine productFilterEngine = null;
	private static ProductFilterSingleton instance = null;
	private File productFilterXmlFile = null;
	private PropertyHandler propertyHandler;

	public ProductFilterSingleton(){
		
	}
	public ProductFilterSingleton(PropertyHandler propertyH) throws IntegrationModuleException {
		String productFilterPath;

		if (propertyHandler == null)
			propertyHandler = propertyH;

		if (propertyHandler.hasProperty("PRODUCT_FILTER_PATH")) {
			productFilterPath = propertyHandler.getProperty("PRODUCT_FILTER_PATH");
		} else {
			throw new IntegrationModuleException(I18nHelper.getLabel("error.get.product.filter.failed"));
		}
		productFilterXmlFile = ConfigUtils.getLatestProductFilterFile(productFilterPath);
		if(productFilterXmlFile != null){
		    LOG.info("Loading productFilter : " + productFilterXmlFile.getAbsolutePath());
		    reloadCache();
		}
		
	}

	public boolean isBlacklistedProduct(String id) {
		return productFilterEngine.isBlacklistedMedicine(id);
	}

	public boolean isWhitelistedProduct(String id) {
		return productFilterEngine.isWhitelistedMedicine(id);
	}

	public boolean isBlacklistedPreparation(String id) {
		return productFilterEngine.isBlacklistedPreparation(id);
	}

	public boolean isWhitelistedPreparation(String id) {
		return productFilterEngine.isWhitelistedPreparation(id);
	}

	public void reloadCache() throws IntegrationModuleException {
		if (productFilterXmlFile == null) {
			LOG.error("No productFilterXml found");
			throw new IntegrationModuleException(I18nHelper.getLabel("exception.creation.productlist"));
		}
		try {
			LOG.info("Getting product filter configuration from file: " + productFilterXmlFile.getName());
			productFilterEngine = new ProductFilterEngine(productFilterXmlFile.getParentFile().getCanonicalPath(),productFilterXmlFile.getName());
		} catch (IOException e) {
			throw new IntegrationModuleException(I18nHelper.getLabel("exception.creation.productlist"), e);
		} catch (GFDDPPException e) {
			throw new IntegrationModuleException(e.getLocalizedMessage());
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
		return productFilterEngine.getPrecedence();
	}

	public void setPrecedence(String precedence) {
		productFilterEngine.setPrecedence(precedence);//listWrapper.setPrecedence(precedence);
	}

	public File getProductFilterXmlFile() {
		return productFilterXmlFile;
	}

	public void setProductFilterXmlFile(File productFilterXmlFile) {
		this.productFilterXmlFile = productFilterXmlFile;
	}

}
