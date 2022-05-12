package be.fgov.ehealth.schematron.utils;

import javax.xml.transform.ErrorListener;
import javax.xml.transform.TransformerException;

public class ValidatorErrorListener implements ErrorListener {
   public ValidatorErrorListener() {
   }

   public void warning(TransformerException ex) {
      System.out.println(ex.getMessage());
   }

   public void error(TransformerException ex) throws TransformerException {
      throw ex;
   }

   public void fatalError(TransformerException ex) throws TransformerException {
      throw ex;
   }
}
