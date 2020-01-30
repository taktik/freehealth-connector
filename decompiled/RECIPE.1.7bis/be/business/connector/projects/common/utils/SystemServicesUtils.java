package be.business.connector.projects.common.utils;

import be.apb.gfddpp.common.xml.XmlBindingTool;
import be.business.connector.core.exceptions.IntegrationModuleException;
import be.business.connector.core.utils.ConfigUtils;
import be.business.connector.core.utils.I18nHelper;
import be.business.connector.core.utils.PropertyHandler;
import be.gfddpp.services.systemservices.v2.ContractEntry;
import be.gfddpp.services.systemservices.v2.ContractList;
import be.gfddpp.services.systemservices.v2.ContractPartyEntry;
import be.gfddpp.services.systemservices.v2.Service;
import be.gfddpp.services.systemservices.v2.SystemServices;
import be.gfddpp.services.systemservices.v2.SystemServicesEntry;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.xml.bind.JAXBException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

public class SystemServicesUtils {
   private static final Logger LOG = Logger.getLogger(SystemServicesUtils.class);
   private static final String SYTEMTYPE_TIP = "TIP";
   private static SystemServicesUtils instance = null;
   private File systemServicesXmlFile;
   private SystemServices systemServicesCache = null;

   public SystemServicesUtils(PropertyHandler propertyHandler) throws IntegrationModuleException {
      if (propertyHandler.hasProperty("SYSTEM_SERVICES_PATH")) {
         String systemServicesPath = propertyHandler.getProperty("SYSTEM_SERVICES_PATH");
         this.systemServicesXmlFile = ConfigUtils.getLatestSystemServicesFile(systemServicesPath);
         this.reloadCache();
      } else {
         throw new IntegrationModuleException(I18nHelper.getLabel("error.get.system.services.failed"));
      }
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
      Iterator var5 = this.systemServicesCache.getSystemServicesList().getSystemServicesEntry().iterator();

      while(true) {
         SystemServicesEntry systemServicesEntry;
         do {
            do {
               if (!var5.hasNext()) {
                  return null;
               }

               systemServicesEntry = (SystemServicesEntry)var5.next();
            } while(!systemServicesEntry.getSystem().getSystemType().equals(systemType));
         } while(!systemServicesEntry.getSystem().getSystemId().equals(tipId));

         for(int i = 0; i < systemServicesEntry.getService().size(); ++i) {
            Service service = (Service)systemServicesEntry.getService().get(i);
            if (service != null && StringUtils.isNotEmpty(service.getServiceName()) && service.getServiceName().equals(servicename)) {
               return ((Service)systemServicesEntry.getService().get(i)).getURI();
            }
         }
      }
   }

   public void reloadCache() throws IntegrationModuleException {
      if (this.systemServicesXmlFile == null) {
         throw new IntegrationModuleException(I18nHelper.getLabel("exception.creation.system.configuration"));
      } else {
         try {
            LOG.info("Getting system configuration from file: " + this.systemServicesXmlFile.getName());
            this.initEngine(FileUtils.readFileToByteArray(this.systemServicesXmlFile));
         } catch (IOException var2) {
            throw new IntegrationModuleException(I18nHelper.getLabel("exception.creation.system.configurationt"), var2);
         }
      }
   }

   private void initEngine(byte[] xml) throws IntegrationModuleException {
      try {
         XmlBindingTool<SystemServices> fromDisk = new XmlBindingTool(SystemServices.class);
         SystemServices loadedSS = (SystemServices)fromDisk.parseXML(xml);
         this.systemServicesCache = loadedSS;
      } catch (JAXBException var4) {
         throw new IntegrationModuleException(I18nHelper.getLabel("exception.creation.system.configuration"), var4);
      }
   }

   public List<String> getAllCbfas() {
      List<String> cbfas = new ArrayList();
      List<SystemServicesEntry> systemServicesEntries = this.systemServicesCache.getSystemServicesList().getSystemServicesEntry();
      Iterator var4 = systemServicesEntries.iterator();

      while(true) {
         ContractList contractList;
         do {
            do {
               do {
                  SystemServicesEntry systemServicesEntry;
                  do {
                     if (!var4.hasNext()) {
                        return cbfas;
                     }

                     systemServicesEntry = (SystemServicesEntry)var4.next();
                  } while(!systemServicesEntry.getSystem().getSystemType().equals("TIP"));

                  contractList = systemServicesEntry.getContractList();
               } while(contractList == null);
            } while(contractList.getContractEntry() == null);
         } while(contractList.getContractEntry().size() <= 0);

         List<ContractEntry> contractEntries = contractList.getContractEntry();
         Iterator var8 = contractEntries.iterator();

         while(var8.hasNext()) {
            ContractEntry contractEntry = (ContractEntry)var8.next();
            List<ContractPartyEntry> contractPartyEntries = contractEntry.getContractPartyList().getContractPartyEntry();
            Iterator var11 = contractPartyEntries.iterator();

            while(var11.hasNext()) {
               ContractPartyEntry contractPartyEntry = (ContractPartyEntry)var11.next();
               cbfas.add(contractPartyEntry.getContractPartyID());
            }
         }
      }
   }

   public File getSystemServicesXmlFile() {
      return this.systemServicesXmlFile;
   }

   public void setSystemServicesXmlFile(File systemServicesXmlFile) {
      this.systemServicesXmlFile = systemServicesXmlFile;
   }

   public SystemServices getSystemServicesCache() {
      return this.systemServicesCache;
   }

   public void setSystemServicesCache(SystemServices systemServicesCache) {
      this.systemServicesCache = systemServicesCache;
   }
}
