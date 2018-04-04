package be.fgov.ehealth.technicalconnector.ra.enumaration;

public enum UsageType {
   TIME_STAMPING(be.fgov.ehealth.etee.ra.csr._1_0.protocol.UsageType.TIMESTAMPING),
   CONSULT_RN(be.fgov.ehealth.etee.ra.csr._1_0.protocol.UsageType.CONSULTATION_RN),
   CODAGE(be.fgov.ehealth.etee.ra.csr._1_0.protocol.UsageType.CODAGE);

   private be.fgov.ehealth.etee.ra.csr._1_0.protocol.UsageType type;

   private UsageType(be.fgov.ehealth.etee.ra.csr._1_0.protocol.UsageType type) {
      this.type = type;
   }

   public be.fgov.ehealth.etee.ra.csr._1_0.protocol.UsageType getType() {
      return this.type;
   }
}
