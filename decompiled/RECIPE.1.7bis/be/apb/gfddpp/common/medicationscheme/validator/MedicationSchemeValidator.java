package be.apb.gfddpp.common.medicationscheme.validator;

import be.apb.gfddpp.common.medicationscheme.validator.schematron.SchematronValidator;
import be.apb.gfddpp.common.medicationscheme.validator.xsd.XSDValidator;
import be.apb.standards.smoa.schema.v1.FormatCode;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import javax.xml.transform.Source;
import javax.xml.transform.TransformerException;
import org.apache.commons.lang.StringUtils;
import org.xml.sax.SAXException;

public class MedicationSchemeValidator {
   public static final String VALIDATION_CONFIGURATION_FILE = "/medicationscheme/validation.properties";
   public static final String MEDICATION_SCHEME_PREFIX = "medication-scheme.";
   public static final String SCHEMATRON_PREFIX = "schematron.";
   public static final String XSD_PREFIX = "xsd.";
   private static Properties validationConfig;

   public static List<String> validateMedicationScheme(Source medicationScheme, FormatCode formatCode) throws ValidatorException {
      String schemaNames = getValidationConfig().getProperty("medication-scheme." + formatCode.name());
      List<String> schemas = Arrays.asList(StringUtils.split(schemaNames, ";"));
      List<String> validationErrors = new ArrayList();
      Iterator var5 = schemas.iterator();

      while(var5.hasNext()) {
         String schema = (String)var5.next();
         String xsdLocation = getValidationConfig().getProperty("xsd." + schema);
         if (StringUtils.isNotBlank(xsdLocation)) {
            validationErrors.addAll(validateXsd(xsdLocation, medicationScheme));
         }

         String schematronLocation = validationConfig.getProperty("schematron." + schema);
         if (StringUtils.isNotBlank(schematronLocation)) {
            validationErrors.addAll(validateSchematron(schematronLocation, medicationScheme));
         }
      }

      return validationErrors;
   }

   private static List<String> validateXsd(String xsdFileLocation, Source businessData) throws ValidatorException {
      XSDValidator xsdValidator = null;

      try {
         xsdValidator = XSDValidator.getInstance(xsdFileLocation);
      } catch (SAXException var5) {
         throw new ValidatorException("Could not instantiate xsd validator.", var5);
      }

      try {
         xsdValidator.validate(businessData);
      } catch (Exception var4) {
         throw new ValidatorException("Failed validate input document", var4);
      }

      return xsdValidator.getErrors();
   }

   private static List<String> validateSchematron(String schematronFileLocation, Source businessData) throws ValidatorException {
      SchematronValidator schematronValidator = null;

      try {
         schematronValidator = SchematronValidator.getInstance(schematronFileLocation);
      } catch (TransformerException var5) {
         throw new ValidatorException("Could not instantiate schematron validator.", var5);
      }

      try {
         schematronValidator.validate(businessData);
      } catch (Exception var4) {
         throw new ValidatorException("Failed validation of input document", var4);
      }

      return schematronValidator.getErrors();
   }

   private static Properties getValidationConfig() throws ValidatorException {
      if (validationConfig == null) {
         Properties properties = new Properties();

         try {
            properties.load(MedicationSchemeValidator.class.getResourceAsStream("/medicationscheme/validation.properties"));
         } catch (IOException var2) {
            throw new ValidatorException("Validation configuration file for medication schemes could not be loaded", var2);
         }

         validationConfig = properties;
      }

      return validationConfig;
   }
}
