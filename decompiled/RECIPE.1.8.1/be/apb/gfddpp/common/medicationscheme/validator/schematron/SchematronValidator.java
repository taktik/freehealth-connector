package be.apb.gfddpp.common.medicationscheme.validator.schematron;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.Templates;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import net.sf.saxon.TransformerFactoryImpl;
import org.apache.commons.lang.Validate;
import org.apache.log4j.Logger;
import org.xml.sax.SAXException;

public class SchematronValidator {
   private static final Logger LOG = Logger.getLogger(SchematronValidator.class);
   private static volatile Map<String, Templates> cachedTemplates = new HashMap();
   private static volatile TransformerFactory transformerFactory = new TransformerFactoryImpl();
   private Transformer transformer;
   private SchematronResult validationResult;

   private SchematronValidator(Templates templates) throws TransformerException {
      this.transformer = templates.newTransformer();
      this.transformer.setOutputProperty("encoding", "UTF-8");
      this.transformer.setOutputProperty("indent", "yes");
   }

   public static synchronized SchematronValidator getInstance(String schematronLocation) throws TransformerException {
      Validate.notEmpty(schematronLocation, "Location for the Schematron schema needs to be specified.");
      LOG.info(String.format("Retrieving validator for schematron file [%s]", schematronLocation));
      if (!cachedTemplates.containsKey(schematronLocation)) {
         LOG.debug(String.format("Template for schematron file [%s] not in cache, creating it", schematronLocation));
         Source dataSource = new StreamSource(SchematronValidator.class.getResource(schematronLocation).toString());
         Templates templates = transformerFactory.newTemplates(dataSource);
         cachedTemplates.put(schematronLocation, templates);
         LOG.debug(String.format("Template for schematron file [%s] created and cached", schematronLocation));
      }

      Templates template = (Templates)cachedTemplates.get(schematronLocation);
      return new SchematronValidator(template);
   }

   public void validate(Source businessData) throws TransformerException, IOException, SAXException, ParserConfigurationException {
      StringWriter writer = new StringWriter();
      this.transformer.transform(businessData, new StreamResult(writer));
      this.validationResult = new SchematronResult(businessData.getSystemId());
      this.validationResult.setSVRL(writer.toString());
   }

   public List<String> getErrors() {
      List<String> errors = this.validationResult == null ? new ArrayList() : this.validationResult.getErrors();
      Iterator var2 = ((List)errors).iterator();

      while(var2.hasNext()) {
         String error = (String)var2.next();
         LOG.error("Schematron Error :" + error);
      }

      return (List)errors;
   }
}
