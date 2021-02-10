package be.ehealth.businessconnector.dicsv5.exception;

import javax.xml.soap.SOAPFault;

public class DicsException extends Exception {
   private static final long serialVersionUID = 1L;
   private SOAPFault fault;

   public DicsException(SOAPFault fault) {
      this.fault = fault;
   }

   public SOAPFault getFault() {
      return this.fault;
   }
}
