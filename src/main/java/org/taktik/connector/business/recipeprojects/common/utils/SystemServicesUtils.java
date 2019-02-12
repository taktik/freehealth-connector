package org.taktik.connector.business.recipeprojects.common.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import be.apb.gfddpp.common.xml.XmlBindingTool;
import be.gfddpp.services.systemservices.v2.SystemServices;
import be.gfddpp.services.systemservices.v2.SystemServicesEntry;
import be.gfddpp.services.systemservices.v2.ContractList;
import be.gfddpp.services.systemservices.v2.ContractEntry;
import be.gfddpp.services.systemservices.v2.ContractPartyEntry;
import org.taktik.connector.business.recipeprojects.core.exceptions.IntegrationModuleException;
import org.taktik.connector.business.recipeprojects.core.utils.ConfigUtils;
import org.taktik.connector.business.recipeprojects.core.utils.I18nHelper;
import org.taktik.connector.business.recipeprojects.core.utils.PropertyHandler;
import be.gfddpp.services.systemservices.v2.Service;

public class SystemServicesUtils {
	private final static Logger LOG = LogManager.getLogger(SystemServicesUtils.class);
	private static final String SYTEMTYPE_TIP = "TIP";
	private static SystemServicesUtils instance = null;
	private File systemServicesXmlFile;
	private SystemServices systemServicesCache = null;

	public SystemServicesUtils(PropertyHandler propertyHandler) throws IntegrationModuleException {
		String systemServicesPath;
		if (propertyHandler.hasProperty("SYSTEM_SERVICES_PATH")) {
			systemServicesPath = propertyHandler.getProperty("SYSTEM_SERVICES_PATH");
		} else {
			throw new IntegrationModuleException(I18nHelper.getLabel("error.get.system.services.failed"));
		}
		systemServicesXmlFile = ConfigUtils.getLatestSystemServicesFile(systemServicesPath);
		reloadCache();
	}

	public SystemServicesUtils() {

	}

	public static SystemServicesUtils getInstance(PropertyHandler propertyHandler2) throws IntegrationModuleException {
		if (instance == null) {
			instance = new SystemServicesUtils(propertyHandler2);
		}
		return instance;
	}

	public static SystemServicesUtils getInstance() {
		if (instance == null) {
			instance = new SystemServicesUtils();
		}
		return instance;
	}

	public String getEndpointOutOfSystemConfiguration(String tipId, String systemType, String servicename) {
		for (SystemServicesEntry systemServicesEntry : systemServicesCache.getSystemServicesList().getSystemServicesEntry()) {
			if (systemServicesEntry.getSystem().getSystemType().equals(systemType) && systemServicesEntry.getSystem().getSystemId().equals(tipId)) {
				for (int i = 0; i < systemServicesEntry.getService().size(); i++) {
					                               Service service = systemServicesEntry.getService().get(i);

					if (service != null && StringUtils.isNotEmpty(service.getServiceName()) && service.getServiceName().equals(servicename)) {
						return systemServicesEntry.getService().get(i).getURI();
					}
				}
			}
		}
		return null;
	}

	public void reloadCache() throws IntegrationModuleException {
		if (systemServicesXmlFile == null) {
			throw new IntegrationModuleException(I18nHelper.getLabel("exception.creation.system.configuration"));
		}
		try {
			LOG.info("Getting system configuration from file: " + systemServicesXmlFile.getName());
			initEngine(FileUtils.readFileToByteArray(systemServicesXmlFile));
		} catch (IOException e) {
			throw new IntegrationModuleException(I18nHelper.getLabel("exception.creation.system.configurationt"), e);
		}
	}

	private void initEngine(byte[] xml) throws IntegrationModuleException {
		XmlBindingTool<SystemServices> fromDisk;
		try {
			fromDisk = new XmlBindingTool<SystemServices>(SystemServices.class);
			SystemServices loadedSS = fromDisk.parseXML(xml);
			this.systemServicesCache = loadedSS;
		} catch (JAXBException e) {
			throw new IntegrationModuleException(I18nHelper.getLabel("exception.creation.system.configuration"), e);
		}
	}

	public List<String> getAllCbfas() {
		List<String> cbfas = new ArrayList<String>();
		List<SystemServicesEntry> systemServicesEntries = systemServicesCache.getSystemServicesList().getSystemServicesEntry();
		for (SystemServicesEntry systemServicesEntry : systemServicesEntries) {
			if (systemServicesEntry.getSystem().getSystemType().equals(SYTEMTYPE_TIP)) {
				ContractList contractList = systemServicesEntry.getContractList();
				if (contractList != null && contractList.getContractEntry() != null && contractList.getContractEntry().size() > 0) {
					List<ContractEntry> contractEntries = contractList.getContractEntry();
					for (ContractEntry contractEntry : contractEntries) {
						List<ContractPartyEntry> contractPartyEntries = contractEntry.getContractPartyList().getContractPartyEntry();
						for (ContractPartyEntry contractPartyEntry : contractPartyEntries) {
							cbfas.add(contractPartyEntry.getContractPartyID());
						}
					}
				}
			}
		}
		return cbfas;
	}

	public File getSystemServicesXmlFile() {
		return systemServicesXmlFile;
	}

	public void setSystemServicesXmlFile(File systemServicesXmlFile) {
		this.systemServicesXmlFile = systemServicesXmlFile;
	}

	public SystemServices getSystemServicesCache() {
		return systemServicesCache;
	}

	public void setSystemServicesCache(SystemServices systemServicesCache) {
		this.systemServicesCache = systemServicesCache;
	}
}
