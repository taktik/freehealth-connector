package be.ehealth.business.kmehrcommons;

import be.ehealth.business.kmehrcommons.builders.HcPartyBuilder;
import be.ehealth.business.kmehrcommons.util.KmehrIdGenerator;
import be.ehealth.technicalconnector.config.ConfigFactory;
import be.ehealth.technicalconnector.config.Configuration;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorExceptionValues;
import be.ehealth.technicalconnector.idgenerator.IdGeneratorFactory;
import be.fgov.ehealth.standards.kmehr.cd.v1.CDHCPARTY;
import be.fgov.ehealth.standards.kmehr.cd.v1.CDHCPARTYschemes;
import be.fgov.ehealth.standards.kmehr.id.v1.IDHCPARTY;
import be.fgov.ehealth.standards.kmehr.id.v1.IDHCPARTYschemes;
import be.fgov.ehealth.standards.kmehr.id.v1.IDKMEHR;
import be.fgov.ehealth.standards.kmehr.id.v1.IDKMEHRschemes;
import be.fgov.ehealth.standards.kmehr.schema.v1.AuthorType;
import be.fgov.ehealth.standards.kmehr.schema.v1.HcpartyType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class HcPartyUtil {
   private static final Logger LOG = LoggerFactory.getLogger(HcPartyUtil.class);
   private static final String MAIN_KMEHR_QUALITY = "main.kmehr.quality";
   private static final String AUTHOR_PREFIX = "kmehr.";
   private static final String SINGLE_HCPARTY_CONFIG_PREFIX = "kmehr.single.hcparty.template.";
   private static final String MULTIPLE_HCPARTIES_CONFIG_PREFIX = "kmehr.multiple.hcparties.template.";
   private static final String IDVERSION = "1.0";

   public static String getAuthorKmehrQuality() {
      String quality = ConfigFactory.getConfigValidator().getProperty("main.kmehr.quality");
      if (quality != null && !quality.isEmpty()) {
         return quality;
      } else {
         throw new IllegalStateException("configuration did not contain required parameter main.kmehr.quality");
      }
   }

   public static List<HcpartyType> createHcpartiesFromConfig(String configName) throws TechnicalConnectorException {
      String prefix = "kmehr.multiple.hcparties.template." + configName + ".";
      LOG.debug("createHcpartiesFromConfig for prefix " + prefix);
      return buildHcpartiesFromConfig(prefix);
   }

   public static HcpartyType createSingleHcpartyFromConfig(String configName) throws TechnicalConnectorException {
      String prefix = "kmehr.single.hcparty.template." + configName;
      LOG.debug("createSingleHcpartyFromConfig for prefix " + prefix);
      return buildHcpartyFromConfig(prefix);
   }

   public static HcpartyType createSoftwareIdentifier(String softwareName, String appId) throws TechnicalConnectorException {
      HcPartyBuilder builder = new HcPartyBuilder();
      builder.cd("1.1", "application", CDHCPARTYschemes.CD_HCPARTY);
      builder.id("1.0", appId, IDHCPARTYschemes.LOCAL, "application_ID");
      builder.name(softwareName);
      return builder.build();
   }

   public static HcpartyType createProfessionalParty(String inss, String nihii, String professionType) throws TechnicalConnectorException {
      HcPartyBuilder builder = new HcPartyBuilder();
      builder.cd("1.1", professionType, CDHCPARTYschemes.CD_HCPARTY);
      builder.id("1.0", inss, IDHCPARTYschemes.INSS);
      builder.id("1.0", nihii, IDHCPARTYschemes.ID_HCPARTY);
      return builder.build();
   }

   private static HcpartyType buildHcpartyFromConfig(String hcpartyPropertyPrefix) throws TechnicalConnectorException {
      HcPartyBuilder builder = new HcPartyBuilder();
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

      return builder.build();
   }

   private static void buildLocal(Configuration config, HcPartyBuilder builder, String propertyConcerned) throws TechnicalConnectorException {
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
         Iterator i$ = elements.iterator();

         while(i$.hasNext()) {
            String element = (String)i$.next();
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

   private static String determineProjectNameToUse(String projectName) throws TechnicalConnectorException {
      Configuration config = ConfigFactory.getConfigValidator().getConfig();
      String finalProjectName = projectName;
      if (config.getBooleanProperty("kmehr." + projectName + ".usedefaultproperties", true).booleanValue()) {
         finalProjectName = "default";
      }

      return finalProjectName;
   }

   static {
      IdGeneratorFactory.registerDefaultImplementation("kmehr", KmehrIdGenerator.class);
   }
}
