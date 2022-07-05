package be.fgov.ehealth.technicalconnector.services.validation.impl;

import be.ehealth.technicalconnector.config.ConfigFactory;
import be.ehealth.technicalconnector.config.ConfigValidator;
import be.ehealth.technicalconnector.exception.SilentInstantiationException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.utils.ConfigurableImplementation;
import be.ehealth.technicalconnector.utils.ConnectorIOUtils;
import be.ehealth.technicalconnector.utils.ConnectorXmlUtils;
import be.fgov.ehealth.schematron.exception.InitialisationException;
import be.fgov.ehealth.technicalconnector.services.schematron.SchematronValidationResult;
import be.fgov.ehealth.technicalconnector.services.schematron.SchematronValidator;
import be.fgov.ehealth.technicalconnector.services.validation.ProjectMessageValidator;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class ProjectMessageValidatorImpl implements ProjectMessageValidator, ConfigurableImplementation {
   private static ConfigValidator config = ConfigFactory.getConfigValidator();
   private SchematronValidator validator;
   private Schematron schematron;
   private static Map<String, Schematron> schematrons = new HashMap();
   public static final String URI = "urn:be:fgov:ehealth:standards:validation:v1";
   public static final String PROP_SCHEMATRONS_XML = "be.fgov.ehealth.technicalconnector.services.validation.schematron";

   public ProjectMessageValidatorImpl() {
   }

   private static String getDefaultLocation() {
      String env = config.getProperty("environment", "prd");
      String endpoint = config.getProperty("be.fgov.ehealth.technicalconnector.services.validation.schematron");
      if (StringUtils.isNotBlank(endpoint)) {
         return endpoint;
      } else if ("prd".equals(env)) {
         return "https://www.ehealth.fgov.be/standards/kmehr/sites/default/files/assets/schematrons/overview";
      } else if ("acc".equals(env)) {
         return "https://wwwacc.ehealth.fgov.be/standards/kmehr/sites/default/files/assets/schematrons/overview";
      } else {
         throw new IllegalArgumentException("Unsupported Environment [" + env + "]");
      }
   }

   public void initialize(Map<String, Object> parameterMap) throws TechnicalConnectorException {
      contains(parameterMap, "schematronProject");
      String project = parameterMap.get("schematronProject").toString();
      contains(schematrons, project);
      this.schematron = (Schematron)schematrons.get(project);
      contains(parameterMap, "schematronValidator");
      this.validator = (SchematronValidator)parameterMap.get("schematronValidator");
   }

   private static void contains(Map<String, ?> parameterMap, String parameter) throws SilentInstantiationException {
      if (!parameterMap.containsKey(parameter)) {
         throw new SilentInstantiationException(new IllegalArgumentException("Unable to find required param [" + parameter + "]"));
      }
   }

   public SchematronValidationResult validate(String inputLocation) throws TechnicalConnectorException, InitialisationException {
      return this.validate(ConnectorIOUtils.getResourceAsStream(inputLocation));
   }

   public SchematronValidationResult validate(InputStream input) throws TechnicalConnectorException, InitialisationException {
      return this.validator.validate(input, this.schematron.getContentAsStream());
   }

   static {
      try {
         String schematronsLocation = ConfigFactory.getConfigValidator().getProperty("be.fgov.ehealth.technicalconnector.services.validation.schematron", getDefaultLocation());
         Document doc = ConnectorXmlUtils.toDocument(ConnectorIOUtils.getResourceAsByteArray(schematronsLocation + ".xml"));
         NodeList schematronList = doc.getElementsByTagNameNS("urn:be:fgov:ehealth:standards:validation:v1", "schematron");

         for(int i = 0; i < schematronList.getLength(); ++i) {
            Element schematron = (Element)schematronList.item(i);
            String path = StringUtils.trim(schematron.getElementsByTagNameNS("urn:be:fgov:ehealth:standards:validation:v1", "path").item(0).getTextContent());
            String project = StringUtils.trim(schematron.getElementsByTagNameNS("urn:be:fgov:ehealth:standards:validation:v1", "name").item(0).getTextContent());
            String hash = StringUtils.trim(schematron.getElementsByTagNameNS("urn:be:fgov:ehealth:standards:validation:v1", "hash").item(0).getTextContent());
            if (!schematrons.containsKey(project) || !((Schematron)schematrons.get(project)).getHash().equals(hash)) {
               byte[] sch = ConnectorIOUtils.getResourceAsByteArray(path);
               schematrons.put(project, new Schematron(path, hash, sch));
            }
         }

      } catch (TechnicalConnectorException var9) {
         throw new IllegalArgumentException("Unable to load schematron directory", var9);
      }
   }

   private static class Schematron {
      private byte[] content;
      private String hash;
      private String name;

      public Schematron(String name, String hash, byte[] content) {
         this.content = content;
         this.hash = hash;
         this.name = name;
      }

      public String getHash() {
         return this.hash;
      }

      public String getName() {
         return this.name;
      }

      public byte[] getContent() {
         return ArrayUtils.clone(this.content);
      }

      public InputStream getContentAsStream() {
         return new ByteArrayInputStream(this.getContent());
      }
   }
}
