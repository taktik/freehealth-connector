package be.ehealth.businessconnector.chapterIV.utils;

import be.ehealth.technicalconnector.config.ConfigFactory;
import be.ehealth.technicalconnector.config.ConfigValidator;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorExceptionValues;
import be.fgov.ehealth.etee.kgss._1_0.protocol.CredentialType;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class ACLUtils {
   private static Properties props = new Properties();
   private static final String ACL = ".ACL";
   private static final String CHAPTER_IV = "chapterIV.";
   private static final Logger LOG = LoggerFactory.getLogger(ACLUtils.class);
   private static ConfigValidator config = ConfigFactory.getConfigValidator();

   public static List<CredentialType> createAclChapterIV(String subTypeName) throws TechnicalConnectorException {
      List<CredentialType> allowedReaders = new ArrayList();
      String rootKey = "chapterIV." + subTypeName + ".ACL";
      List<String> defaultCredentialTypes = getMatchingProperties(rootKey);
      List<String> credentialTypes = config.getMatchingProperties(rootKey);
      if (credentialTypes.size() == 0) {
         LOG.debug("Using default properties");
         credentialTypes = defaultCredentialTypes;
      }

      LOG.debug("#of ACL's found in config: " + credentialTypes.size());

      CredentialType ct;
      for(Iterator i$ = credentialTypes.iterator(); i$.hasNext(); allowedReaders.add(ct)) {
         String credentialTypeStr = (String)i$.next();
         String[] atrs = credentialTypeStr.split(",");
         if (atrs.length != 3 && atrs.length != 2) {
            LOG.error("Incorrect attributes array length : throwing technical connector exception");
            throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.INVALID_PROPERTY, new Object[0]);
         }

         LOG.debug(" .ACL: " + atrs[0] + " # " + atrs[1]);
         ct = new CredentialType();
         ct.setNamespace(atrs[0]);
         ct.setName(atrs[1]);
         if (atrs.length == 3) {
            ct.getValues().add(atrs[2]);
         }
      }

      return allowedReaders;
   }

   private static List<String> getMatchingProperties(String rootKey) {
      int i = 1;
      ArrayList ret = new ArrayList();

      while(true) {
         String key = rootKey + "." + i;
         if (props.getProperty(key) == null) {
            return ret;
         }

         ret.add(props.getProperty(key));
         ++i;
      }
   }

   static {
      try {
         props.load(ACLUtils.class.getResourceAsStream("/be.fgov.ehealth.business.chapter4.properties"));
      } catch (IOException var1) {
         LOG.warn("Couldn't load chapterIV properties");
      }

   }
}
