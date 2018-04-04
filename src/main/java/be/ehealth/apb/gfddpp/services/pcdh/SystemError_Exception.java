package be.ehealth.apb.gfddpp.services.pcdh;

import javax.xml.ws.WebFault;

@WebFault(
   name = "SystemError",
   targetNamespace = "urn:be:fgov:ehealth:errors:soa:v1"
)
public class SystemError_Exception extends Exception {
   private SystemError faultInfo;

   public SystemError_Exception(String var1, SystemError var2) {
      super(var1);
      this.faultInfo = var2;
   }

   public SystemError_Exception(String var1, SystemError var2, Throwable var3) {
      super(var1, var3);
      this.faultInfo = var2;
   }

   public SystemError getFaultInfo() {
      return this.faultInfo;
   }
}
