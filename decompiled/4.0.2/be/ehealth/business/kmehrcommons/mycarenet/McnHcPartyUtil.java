package be.ehealth.business.kmehrcommons.mycarenet;

import be.ehealth.business.kmehrcommons.builders.mycarenet.McnHcPartyBuilder;
import be.ehealth.business.kmehrcommons.util.KmehrIdGenerator;
import be.ehealth.technicalconnector.config.ConfigFactory;
import be.ehealth.technicalconnector.config.Configuration;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorExceptionValues;
import be.ehealth.technicalconnector.idgenerator.IdGeneratorFactory;
import be.ehealth.technicalconnector.utils.SessionUtil;
import be.fgov.ehealth.standards.kmehr.mycarenet.cd.v1.CDADDRESSschemes;
import be.fgov.ehealth.standards.kmehr.mycarenet.cd.v1.CDCOUNTRYschemes;
import be.fgov.ehealth.standards.kmehr.mycarenet.cd.v1.CDHCPARTY;
import be.fgov.ehealth.standards.kmehr.mycarenet.cd.v1.CDHCPARTYschemes;
import be.fgov.ehealth.standards.kmehr.mycarenet.id.v1.IDHCPARTY;
import be.fgov.ehealth.standards.kmehr.mycarenet.id.v1.IDHCPARTYschemes;
import be.fgov.ehealth.standards.kmehr.mycarenet.id.v1.IDKMEHR;
import be.fgov.ehealth.standards.kmehr.mycarenet.id.v1.IDKMEHRschemes;
import be.fgov.ehealth.standards.kmehr.mycarenet.schema.v1.AuthorType;
import be.fgov.ehealth.standards.kmehr.mycarenet.schema.v1.HcpartyType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang.Validate;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class McnHcPartyUtil {
   private static final Logger LOG = LoggerFactory.getLogger(McnHcPartyUtil.class);
   private static final String MAIN_KMEHR_QUALITY = "main.kmehr.quality";
   private static final String AUTHOR_PREFIX = "kmehr.";
   private static final String SINGLE_HCPARTY_CONFIG_PREFIX = "kmehr.single.hcparty.template.";
   private static final String MULTIPLE_HCPARTIES_CONFIG_PREFIX = "kmehr.multiple.hcparties.template.";
   private static final String IDVERSION = "1.0";
   private static final String CD_ENCRYPTION_ACTOR_PROPERTY_KEY = "encryption.actor.cd";
   private static final String ID_ENCRYPTION_ACTOR_PROPERTY_KEY = "encryption.actor.id";
   private static final String ID_ENCRYPTION_APPLICATION_PROPERTY_KEY = "encryption.application.id";

   private McnHcPartyUtil() {
   }

   private static HcpartyType buildHcpartyFromConfig(String hcpartyPropertyPrefix) throws TechnicalConnectorException {
      Validate.notEmpty(hcpartyPropertyPrefix, "Required parameter hcpartyPropertyPrefix is null.");
      McnHcPartyBuilder builder = new McnHcPartyBuilder();
      Configuration config = ConfigFactory.getConfigValidator();
      buildLocal(config, builder, hcpartyPropertyPrefix + ".id");
      buildLocal(config, builder, hcpartyPropertyPrefix + ".cd");
      String idHcPartyValue = config.getProperty(hcpartyPropertyPrefix + ".id.idhcparty.value");
      if (idHcPartyValue != null) {
         builder.idHcPartyId(idHcPartyValue, config.getProperty(hcpartyPropertyPrefix + ".id.idhcparty.sv"));
      }

      String inssIdValue = config.getProperty(hcpartyPropertyPrefix + ".id.inss.value");
      if (inssIdValue != null) {
         builder.inssId(inssIdValue, config.getProperty(hcpartyPropertyPrefix + ".id.inss.sv"));
      }

      String cdHcPartyValue = config.getProperty(hcpartyPropertyPrefix + ".cd.cdhcparty.value");
      if (cdHcPartyValue != null) {
         builder.cdHcPartyCd(cdHcPartyValue, config.getProperty(hcpartyPropertyPrefix + ".cd.cdhcparty.sv"));
      }

      String nameValue = config.getProperty(hcpartyPropertyPrefix + ".name");
      if (nameValue != null) {
         builder.name(nameValue);
      }

      String firstnameValue = config.getProperty(hcpartyPropertyPrefix + ".firstname");
      if (firstnameValue != null) {
         builder.firstname(firstnameValue);
      }

      String lastnameValue = config.getProperty(hcpartyPropertyPrefix + ".lastname");
      if (lastnameValue != null) {
         builder.lastname(lastnameValue);
      }

      String addressCdValue = config.getProperty(hcpartyPropertyPrefix + ".address.cd.value");
      if (addressCdValue != null) {
         String addressCdType = config.getProperty(hcpartyPropertyPrefix + ".address.cd.schemes");
         String countryType = config.getProperty(hcpartyPropertyPrefix + ".address.country.schemes");
         CDADDRESSschemes addressSchemes = null;
         CDCOUNTRYschemes countrySchemes = null;
         if (addressCdType != null) {
            if (addressCdType.equals("local")) {
               addressSchemes = CDADDRESSschemes.LOCAL;
            } else {
               addressSchemes = CDADDRESSschemes.CD_ADDRESS;
            }
         }

         if (countryType != null) {
            if (countryType.equals("cdcountry")) {
               countrySchemes = CDCOUNTRYschemes.CD_COUNTRY;
            } else {
               countrySchemes = CDCOUNTRYschemes.CD_FED_COUNTRY;
            }
         }

         builder.address(addressCdValue, addressSchemes, config.getProperty(hcpartyPropertyPrefix + ".address.cd.sv"), config.getProperty(hcpartyPropertyPrefix + ".address.city"), config.getProperty(hcpartyPropertyPrefix + ".address.district"), config.getProperty(hcpartyPropertyPrefix + ".address.houseNumber"), config.getProperty(hcpartyPropertyPrefix + ".address.nis"), config.getProperty(hcpartyPropertyPrefix + ".address.postbox"), config.getProperty(hcpartyPropertyPrefix + ".address.street"), config.getProperty(hcpartyPropertyPrefix + ".address.zip"), config.getProperty(hcpartyPropertyPrefix + ".address.country.value"), countrySchemes, config.getProperty(hcpartyPropertyPrefix + ".address.country.sv"));
      }

      return builder.build();
   }

   private static void buildLocal(Configuration config, McnHcPartyBuilder builder, String propertyConcerned) throws TechnicalConnectorException {
      String idSearchedPropertyValue = propertyConcerned + ".local.value";
      String idSearchedPropertySv = propertyConcerned + ".local.sv";
      String idSearchedPropertysl = propertyConcerned + ".local.sl";
      String elementType = StringUtils.substringAfterLast(propertyConcerned, ".");
      List<String> keys = config.getMatchingProperties(idSearchedPropertysl);
      List<String> keys2 = config.getMatchingProperties(idSearchedPropertyValue);
      if (keys2.size() != keys.size()) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_CONFIG, new Object[]{elementType + " local : number of type not equal of number of value"});
      } else {
         for(int i = 1; i <= keys.size(); ++i) {
            String localIdValue = config.getProperty(idSearchedPropertyValue + "." + i);
            if (localIdValue != null) {
               builder.localId(localIdValue, config.getProperty(idSearchedPropertySv + "." + i), config.getProperty(idSearchedPropertysl + "." + i));
            }
         }

      }
   }

   private static List<HcpartyType> buildHcpartiesFromConfig(String configPrefix) throws TechnicalConnectorException {
      String hcPartylist = ConfigFactory.getConfigValidator().getProperty(configPrefix + "hcpartylist");
      if (hcPartylist == null) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_CONFIG, new Object[]{configPrefix + "hcpartylist property not found"});
      } else {
         List<HcpartyType> kmehrlist = new ArrayList();
         List<String> elements = Arrays.asList(hcPartylist.split(","));
         Iterator var4 = elements.iterator();

         while(var4.hasNext()) {
            String element = (String)var4.next();
            kmehrlist.add(buildHcpartyFromConfig(configPrefix + element));
         }

         return kmehrlist;
      }
   }

   public static String createKmehrIdString(String projectName, String kmehrIdSuffix) throws TechnicalConnectorException {
      String tempKmehrIdSuffix = kmehrIdSuffix;
      if (kmehrIdSuffix == null) {
         tempKmehrIdSuffix = createKmehrIdSuffix();
      }

      return retrieveMainAuthorId(projectName) + "." + tempKmehrIdSuffix;
   }

   public static String createKmehrIdString(String projectName) throws TechnicalConnectorException {
      return createKmehrIdString(projectName, (String)null);
   }

   public static String retrieveMainAuthorId(String projectName) throws TechnicalConnectorException {
      String finalProjectName = determineProjectNameToUse(projectName);
      String mainAuthorIdProperty = "kmehr." + finalProjectName + ".identifier.id.idhcparty.value";
      return ConfigFactory.getConfigValidatorFor(mainAuthorIdProperty).getProperty(mainAuthorIdProperty);
   }

   /** @deprecated */
   @Deprecated
   public static String createKmehrIdPrefix() throws TechnicalConnectorException {
      return IdGeneratorFactory.getIdGenerator("kmehr").generateId();
   }

   public static String createKmehrIdSuffix() throws TechnicalConnectorException {
      return IdGeneratorFactory.getIdGenerator("kmehr").generateId();
   }

   public static IDKMEHR createKmehrId(String projectName, String kmehrIdSuffix) throws TechnicalConnectorException {
      IDKMEHR id = new IDKMEHR();
      id.setS(IDKMEHRschemes.ID_KMEHR);
      id.setSV("1.0");
      id.setValue(createKmehrIdString(projectName, kmehrIdSuffix));
      return id;
   }

   public static IDHCPARTY createInssId(String insz) {
      return buildId("1.0", insz, IDHCPARTYschemes.INSS);
   }

   public static IDHCPARTY createNihiiId(String nihii) {
      return buildId("1.0", nihii, IDHCPARTYschemes.ID_HCPARTY);
   }

   public static IDHCPARTY createCbeId(String cbe) {
      return buildId("1.0", cbe, IDHCPARTYschemes.ID_HCPARTY);
   }

   public static IDHCPARTY createApplicationId(String applicationId) {
      return buildId("1.0", applicationId, IDHCPARTYschemes.LOCAL, "application_ID");
   }

   public static IDHCPARTY createEhpId(String ehpId) {
      return buildId("1.0", ehpId, IDHCPARTYschemes.ID_HCPARTY);
   }

   public static IDHCPARTY buildId(String version, String value, IDHCPARTYschemes scheme, String sl) {
      IDHCPARTY id = new IDHCPARTY();
      id.setValue(value);
      id.setSV(version);
      if (sl != null) {
         id.setSL(sl);
      }

      id.setS(scheme);
      return id;
   }

   public static IDHCPARTY buildId(String version, String value, IDHCPARTYschemes scheme) {
      IDHCPARTY id = new IDHCPARTY();
      id.setValue(value);
      id.setSV(version);
      id.setS(scheme);
      return id;
   }

   public static IDHCPARTY buildId(String value, IDHCPARTYschemes scheme) {
      if (scheme == null) {
         throw new IllegalArgumentException("HcPartyUtil.buildId : parameter scheme was null");
      } else {
         String version = ConfigFactory.getConfigValidator().getProperty("kmehr.builder.idhcparty." + scheme.name() + ".version", "1.0");
         return buildId(version, value, scheme);
      }
   }

   public static CDHCPARTY buildCd(String sv, String value, CDHCPARTYschemes scheme, String sl) {
      CDHCPARTY cd = new CDHCPARTY();
      cd.setS(scheme);
      cd.setSV(sv);
      cd.setSL(sl);
      cd.setValue(value);
      return cd;
   }

   public static CDHCPARTY buildCd(String sv, String value, CDHCPARTYschemes scheme, String sl, String dn, String l) {
      CDHCPARTY cd = new CDHCPARTY();
      cd.setS(scheme);
      cd.setSV(sv);
      cd.setSL(sl);
      cd.setValue(value);
      cd.setDN(dn);
      cd.setL(l);
      return cd;
   }

   public static List<HcpartyType> createAuthorHcParties(String projectName) throws TechnicalConnectorException {
      String finalProjectName = determineProjectNameToUse(projectName);
      return buildHcpartiesFromConfig("kmehr." + finalProjectName + ".");
   }

   public static AuthorType createAuthor(String projectName) throws TechnicalConnectorException {
      AuthorType authorType = new AuthorType();
      authorType.getHcparties().addAll(createAuthorHcParties(projectName));
      return authorType;
   }

   private static String getNihiiFromSession() {
      try {
         return SessionUtil.getNihii();
      } catch (TechnicalConnectorException var1) {
         LOG.debug("Unable to obtain nihii. Reason: " + var1.getMessage(), var1);
         return null;
      }
   }

   private static String determineProjectNameToUse(String projectName) throws TechnicalConnectorException {
      Validate.notEmpty(projectName, "Required parameter projectName is null.");
      Configuration config = ConfigFactory.getConfigValidator().getConfig();
      String finalProjectName = projectName;
      if (config.getBooleanProperty("kmehr." + projectName + ".usedefaultproperties", true)) {
         finalProjectName = "default";
      }

      return finalProjectName;
   }

   static {
      IdGeneratorFactory.registerDefaultImplementation("kmehr", KmehrIdGenerator.class);
   }
}
