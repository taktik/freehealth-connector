package org.taktik.connector.technical.service.sts.security.impl.pkcs11;

import org.taktik.connector.technical.config.ConfigFactory;
import org.taktik.connector.technical.config.Configuration;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.exception.TechnicalConnectorExceptionValues;
import org.taktik.connector.technical.service.sts.security.ProviderAdaptor;
import org.taktik.connector.technical.utils.ConnectorIOUtils;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Constructor;
import java.security.Provider;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** @deprecated */
@Deprecated
public class SunPKCS11ProviderAdaptor implements ProviderAdaptor {
   private String eidDllLocation;
   private static final Logger LOG = LoggerFactory.getLogger(SunPKCS11ProviderAdaptor.class);
   private static final String PROP_EID_DLL = "eid.dll";
   private static Configuration config = ConfigFactory.getConfigValidator();

   public Provider getProvider() throws TechnicalConnectorException {
      try {
         Class<Provider> sunPKCS11 = (Class<Provider>) Class.forName("sun.security.pkcs11.SunPKCS11");
         Constructor<Provider> conSunPKCS11 = sunPKCS11.getDeclaredConstructor(String.class);
         return (Provider)conSunPKCS11.newInstance(this.generateAuthProviderConfig());
      } catch (Exception var3) {
         throw new IllegalArgumentException(var3);
      }
   }

   private String generateAuthProviderConfig() throws TechnicalConnectorException {
      File tmpConfigFile = null;
      PrintWriter configWriter = null;

      try {
         tmpConfigFile = File.createTempFile("pkcs11", "conf");
         tmpConfigFile.deleteOnExit();
         configWriter = new PrintWriter(new FileOutputStream(tmpConfigFile), true);
         configWriter.println("name=eIDCard");
         this.eidDllLocation = this.searchEidPath();
         configWriter.println("library=" + this.eidDllLocation);
         LOG.info("library=" + this.eidDllLocation);
         configWriter.println("slotListIndex=0");
         configWriter.println("showInfo=true");
      } catch (IOException var7) {
         LOG.error(var7.getClass().getSimpleName() + ": " + var7.getMessage());
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_IOEXCEPTION, var7, new Object[]{var7.getMessage()});
      } finally {
         ConnectorIOUtils.closeQuietly((Object)configWriter);
      }

      return tmpConfigFile.getAbsolutePath();
   }

   private String searchEidPath() throws TechnicalConnectorException {
      if (config.hasProperty("eid.dll")) {
         String dllPath = config.getProperty("eid.dll");
         File f = new File(dllPath);
         if (!f.exists()) {
            throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.DLL_NOT_FOUND, new Object[]{"eid.dll"});
         }

         try {
            return f.getCanonicalPath();
         } catch (IOException var4) {
            LOG.warn("searchEidPath", var4);
         }
      }

      return this.getDefaultEidDllLocation();
   }

   private String getDefaultEidDllLocation() throws TechnicalConnectorException {
      String osName = System.getProperty("os.name");
      String eidPath;
      if (osName.startsWith("Linux")) {
         eidPath = this.configureLinux();
      } else if (osName.startsWith("Mac")) {
         eidPath = this.configureMac();
      } else {
         eidPath = this.configureWindows();
      }

      if (eidPath == null) {
         TechnicalConnectorExceptionValues errorValue = TechnicalConnectorExceptionValues.ERROR_EID_RUNTIME;
         LOG.debug("Unable to determine eid.location" + errorValue.getMessage());
         throw new TechnicalConnectorException(errorValue, (Throwable)null, new Object[0]);
      } else {
         return eidPath;
      }
   }

   private String configureWindows() throws TechnicalConnectorException {
      List<String> locations = new ArrayList();
      locations.add("C:\\WINDOWS\\system32\\beidpkcs11.dll");
      locations.add("C:\\WINDOWS\\system32\\Belgium Identity Card PKCS11.dll");
      locations.add("C:\\WINNT\\system32\\Belgium Identity Card PKCS11.dll");
      locations.add("C:\\WINNT\\system32\\beidpkcs11.dll");
      locations.add("C:\\Windows\\SysWOW64\\beidpkcs11.dll");
      String javaLibraryPath = System.getProperty("java.library.path");
      String pathSeparator = System.getProperty("path.separator");
      String[] libraryDirectories = javaLibraryPath.split(pathSeparator);
      String[] arr$ = libraryDirectories;
      int len$ = libraryDirectories.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         String libraryDirectory = arr$[i$];
         locations.add(libraryDirectory + "\\beidpkcs11.dll");
      }

      return this.getLocation("Windows", locations);
   }

   private String configureMac() throws TechnicalConnectorException {
      List<String> locations = new ArrayList();
      locations.add("/usr/local/lib/libbeidpkcs11.3.5.1.dylib");
      locations.add("/usr/local/lib/libbeidpkcs11.3.5.0.dylib");
      locations.add("/usr/local/lib/beid-pkcs11.bundle/Contents/MacOS/libbeidpkcs11.2.1.0.dylib");
      locations.add("/usr/local/lib/libbeidpkcs11.dylib");
      locations.add("/usr/local/lib/beid-pkcs11.bundle/Contents/MacOS/libbeidpkcs11.dylib");
      return this.getLocation("Mac", locations);
   }

   private String configureLinux() throws TechnicalConnectorException {
      List<String> locations = new ArrayList();
      locations.add("/usr/local/lib/libbeidpkcs11.so");
      locations.add("/usr/lib/libbeidpkcs11.so");
      locations.add("/usr/local/lib/pkcs11/Belgium-EID-pkcs11.so");
      locations.add("/usr/lib/opensc-pkcs11.so");
      return this.getLocation("Linux", locations);
   }

   private String getLocation(String os, List<String> locations) throws TechnicalConnectorException {
      Iterator i$ = locations.iterator();

      File pkcs11File;
      do {
         if (!i$.hasNext()) {
            throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.NO_FILE_CONF, new Object[]{os});
         }

         String location = (String)i$.next();
         pkcs11File = new File(location);
      } while(!pkcs11File.exists());

      return pkcs11File.getAbsolutePath();
   }
}
