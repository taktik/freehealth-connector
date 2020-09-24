package be.apb.gfddpp.common.medicationscheme.validator.xsd;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.transform.Source;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import org.apache.commons.lang.Validate;
import org.apache.log4j.Logger;
import org.xml.sax.SAXException;

public class XSDValidator {
   private static final Logger LOG = Logger.getLogger(XSDValidator.class);
   private static volatile Map<String, Schema> cachedSchemas = new HashMap();
   private Validator validator;

   private XSDValidator(Schema schema) {
      this.validator = schema.newValidator();
      this.validator.setErrorHandler(new XSDErrorHandler());
   }

   public static synchronized XSDValidator getInstance(String xsdLocation) throws SAXException {
      Validate.notEmpty(xsdLocation, "Location for the XSD schema needs to be specified.");
      LOG.info(String.format("Retrieving validator for xsd [%s]", xsdLocation));
      if (!cachedSchemas.containsKey(xsdLocation)) {
         LOG.debug(String.format("Schema for xsd [%s] not in cache, creating it", xsdLocation));
         SchemaFactory factory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
         Schema schema = factory.newSchema(XSDValidator.class.getResource(xsdLocation));
         cachedSchemas.put(xsdLocation, schema);
         LOG.debug(String.format("Schema for xsd [%s] created and cached", xsdLocation));
      }

      Schema schema = (Schema)cachedSchemas.get(xsdLocation);
      return new XSDValidator(schema);
   }

   public void validate(Source input) throws SAXException, IOException {
      this.validator.validate(input);
   }

   public List<String> getErrors() {
      return ((XSDErrorHandler)this.validator.getErrorHandler()).getErrors();
   }

   public List<String> getWarnings() {
      return ((XSDErrorHandler)this.validator.getErrorHandler()).getWarnings();
   }
}
