package be.fgov.ehealth.technicalconnector.ra.enumaration;

public enum UsageType {
   TIME_STAMPING("tsa"),
   CONSULT_RN("consultrn"),
   CODAGE("codage"),
   OTHER("other");

   private String serviceName;

   private UsageType(String serviceName) {
      this.serviceName = serviceName;
   }

   public String getServiceName() {
      return this.serviceName;
   }
}
