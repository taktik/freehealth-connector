package be.apb.gfddpp.common.medicationscheme.validator.xsd;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class XSDErrorHandler implements ErrorHandler {
   private static final Logger LOG = Logger.getLogger(XSDErrorHandler.class);
   private List<String> errors = new ArrayList();
   private List<String> warnings = new ArrayList();

   public void warning(SAXParseException exception) throws SAXException {
      LOG.warn("XSD Warning: " + exception.getMessage());
      this.warnings.add(exception.getMessage());
   }

   public void error(SAXParseException exception) throws SAXException {
      LOG.error("XSD Error: " + exception.getMessage());
      this.errors.add(exception.getMessage());
   }

   public void fatalError(SAXParseException exception) throws SAXException {
      LOG.error("XSD Fatal Error: " + exception.getMessage());
      throw exception;
   }

   public List<String> getErrors() {
      return this.errors;
   }

   public List<String> getWarnings() {
      return this.warnings;
   }
}
