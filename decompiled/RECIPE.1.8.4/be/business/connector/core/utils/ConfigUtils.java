package be.business.connector.core.utils;

import be.apb.gfddpp.common.exceptions.GFDDPPException;
import be.apb.gfddpp.common.utils.JaxContextCentralizer;
import be.apb.gfddpp.productfilter.ProductFilter;
import be.business.connector.core.exceptions.IntegrationModuleException;
import be.gfddpp.services.systemservices.v2.SystemServices;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

public class ConfigUtils {
   private static final Logger LOG = Logger.getLogger(ConfigUtils.class);

   public static XMLGregorianCalendar getLatestProductFilterVersion(String path) throws IntegrationModuleException {
      File latest = getLatestProductFilterFile(path);
      if (latest == null) {
         LOG.info("defaultVersion will be returned.");
         return getDefaultVersion();
      } else {
         return getProductFilterVersionFromFile(latest);
      }
   }

   public static File getLatestProductFilterFile(String path) throws IntegrationModuleException {
      File folder = new File(path);
      File[] listOfFiles = folder.listFiles();
      File latest = null;
      if (listOfFiles != null) {
         for(int i = 0; i < listOfFiles.length; ++i) {
            if (listOfFiles[i].isFile()) {
               String fileName = listOfFiles[i].getName();
               if (StringUtils.endsWith(StringUtils.lowerCase(fileName), ".xml")) {
                  if (isFileEmpty(listOfFiles[i])) {
                     return null;
                  }

                  if (latest == null) {
                     latest = listOfFiles[i];

                     try {
                        BufferedReader br = new BufferedReader(new FileReader(latest.getAbsolutePath()));
                        if (br.readLine() == null) {
                           latest = getProductFilterMostRecent(latest, listOfFiles[i]);
                        }

                        br.close();
                     } catch (FileNotFoundException var8) {
                        throw new IntegrationModuleException(var8.getLocalizedMessage());
                     } catch (IOException var9) {
                        throw new IntegrationModuleException(var9.getLocalizedMessage());
                     }
                  } else {
                     latest = getProductFilterMostRecent(latest, listOfFiles[i]);
                  }
               }
            }
         }
      }

      return latest;
   }

   private static File getProductFilterMostRecent(File file1, File file2) throws IntegrationModuleException {
      XMLGregorianCalendar version1 = getProductFilterVersionFromFile(file1);
      XMLGregorianCalendar version2 = getProductFilterVersionFromFile(file2);
      return compareFile(version1, file1, version2, file2);
   }

   public static XMLGregorianCalendar getProductFilterVersionFromFile(File file) throws IntegrationModuleException {
      Object var1 = null;

      byte[] xml;
      try {
         xml = FileUtils.readFileToByteArray(file);
      } catch (IOException var5) {
         throw new IntegrationModuleException(I18nHelper.getLabel("error.get.product.filter.failed"), var5);
      }

      ProductFilter productFilter = null;

      try {
         productFilter = (ProductFilter)JaxContextCentralizer.getInstance().toObject(ProductFilter.class, xml);
      } catch (GFDDPPException var4) {
         throw new IntegrationModuleException(var4.getLocalizedMessage(), var4);
      }

      return productFilter == null ? null : productFilter.getVersion();
   }

   public static XMLGregorianCalendar getLatestSystemServicesVersion(String path) throws IntegrationModuleException {
      File latest = getLatestSystemServicesFile(path);
      return latest == null ? getDefaultVersion() : getSystemServicesVersionFromFile(latest);
   }

   public static File getLatestSystemServicesFile(String path) throws IntegrationModuleException {
      File folder = new File(path);
      File[] listOfFiles = folder.listFiles();
      File latest = null;

      for(int i = 0; i < listOfFiles.length; ++i) {
         if (listOfFiles[i].isFile()) {
            String fileName = listOfFiles[i].getName();
            if (StringUtils.endsWith(StringUtils.lowerCase(fileName), ".xml")) {
               if (latest == null) {
                  latest = listOfFiles[i];
               } else {
                  latest = getSystemServicesMostRecent(latest, listOfFiles[i]);
               }
            }
         }
      }

      return latest;
   }

   private static File getSystemServicesMostRecent(File file1, File file2) throws IntegrationModuleException {
      XMLGregorianCalendar version1 = getSystemServicesVersionFromFile(file1);
      XMLGregorianCalendar version2 = getSystemServicesVersionFromFile(file2);
      return compareFile(version1, file1, version2, file2);
   }

   private static File compareFile(XMLGregorianCalendar version1, File file1, XMLGregorianCalendar version2, File file2) {
      if (version1 == null) {
         return file2;
      } else if (version2 == null) {
         return file1;
      } else {
         return version1.toGregorianCalendar().compareTo(version2.toGregorianCalendar()) > 0 ? file1 : file2;
      }
   }

   public static XMLGregorianCalendar getSystemServicesVersionFromFile(File file) throws IntegrationModuleException {
      Object var1 = null;

      byte[] xml;
      try {
         xml = FileUtils.readFileToByteArray(file);
      } catch (IOException var3) {
         throw new IntegrationModuleException(I18nHelper.getLabel("error.get.system.services.failed"));
      }

      XMLGregorianCalendar version = null;
      version = getVersionNewXml(xml);
      return version;
   }

   private static XMLGregorianCalendar getVersionNewXml(byte[] xml) throws IntegrationModuleException {
      SystemServices systemServices = null;

      try {
         systemServices = (SystemServices)JaxContextCentralizer.getInstance().toObject(SystemServices.class, xml);
      } catch (GFDDPPException var2) {
         throw new IntegrationModuleException(I18nHelper.getLabel("error.get.system.services.failed"));
      }

      return systemServices == null ? null : systemServices.getVersion();
   }

   public static XMLGregorianCalendar getDefaultVersion() {
      Calendar cal = Calendar.getInstance();
      cal.set(1980, 1, 1, 0, 0, 0);
      GregorianCalendar c = new GregorianCalendar();
      c.setTime(cal.getTime());

      try {
         XMLGregorianCalendar gc = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
         return gc;
      } catch (DatatypeConfigurationException var3) {
         var3.printStackTrace();
         return null;
      }
   }

   private static boolean isFileEmpty(File file) {
      try {
         if (FileUtils.readFileToByteArray(file).length == 0) {
            LOG.info("The file: " + file.getName() + " is empty");
            return true;
         }
      } catch (IOException var2) {
         var2.printStackTrace();
      }

      return false;
   }
}
