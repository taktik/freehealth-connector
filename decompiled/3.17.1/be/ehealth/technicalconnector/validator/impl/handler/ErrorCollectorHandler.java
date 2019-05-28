package be.ehealth.technicalconnector.validator.impl.handler;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class ErrorCollectorHandler implements ErrorHandler {
   private static final Logger LOG = LoggerFactory.getLogger(ErrorCollectorHandler.class);
   public static final String WARNING = "WARN";
   public static final String ERROR = "ERROR";
   public static final String FATAL = "FATAL";
   private XOPValidationHandler xopHandler;
   private List<String> exceptionWarningList = new ArrayList();
   private List<String> exceptionErrorList = new ArrayList();
   private List<String> exceptionFatalList = new ArrayList();

   public ErrorCollectorHandler() {
   }

   public ErrorCollectorHandler(XOPValidationHandler xopHandler) {
      this.xopHandler = xopHandler;
   }

   public void warning(SAXParseException exception) throws SAXException {
      String msg = "WARNING " + this.toString(exception);
      this.exceptionWarningList.add(msg);
   }

   public void error(SAXParseException exception) throws SAXException {
      if (this.accept(exception)) {
         String msg = "ERROR " + this.toString(exception);
         this.exceptionErrorList.add(msg);
      }

   }

   public void fatalError(SAXParseException exception) throws SAXException {
      if (this.accept(exception)) {
         String msg = "FATAL " + this.toString(exception);
         this.exceptionFatalList.add(msg);
      }

   }

   private String toString(SAXParseException exception) {
      return exception.getMessage();
   }

   public final List<String> getExceptionList(String... errorType) {
      List<String> exceptionList = new ArrayList();
      if (ArrayUtils.contains(errorType, "WARN")) {
         exceptionList.addAll(this.exceptionWarningList);
      }

      if (ArrayUtils.contains(errorType, "ERROR")) {
         exceptionList.addAll(this.exceptionErrorList);
      }

      if (ArrayUtils.contains(errorType, "FATAL")) {
         exceptionList.addAll(this.exceptionFatalList);
      }

      return exceptionList;
   }

   public final boolean hasExceptions(String... errorType) {
      if (ArrayUtils.contains(errorType, "WARN") && !this.isEmpty(this.exceptionWarningList)) {
         return true;
      } else if (ArrayUtils.contains(errorType, "ERROR") && !this.isEmpty(this.exceptionErrorList)) {
         return true;
      } else {
         return ArrayUtils.contains(errorType, "FATAL") && !this.isEmpty(this.exceptionFatalList);
      }
   }

   private boolean accept(SAXParseException ex) {
      if (this.xopHandler != null && this.xopHandler.isXop()) {
         LOG.debug("XOP element detected, skipping error [" + ex.getMessage() + "]");
         return false;
      } else {
         return true;
      }
   }

   private boolean isEmpty(List<?> inputList) {
      return inputList.size() <= 0;
   }
}
