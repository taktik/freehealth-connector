package be.ehealth.business.kmehrcommons.builders;

import be.fgov.ehealth.standards.kmehr.cd.v1.CDHCPARTY;
import be.fgov.ehealth.standards.kmehr.cd.v1.CDHCPARTYschemes;
import org.apache.commons.lang.Validate;

public class Cd {
   private CDHCPARTY cd = new CDHCPARTY();

   public Cd sv(String sv) {
      this.cd.setSV(sv);
      return this;
   }

   public CDHCPARTY build() {
      this.validate();
      return this.cd;
   }

   private void validate() {
      Validate.notNull(this.cd.getS(), "s cannot be null on a CDHCPARTY object");
      Validate.notNull(this.cd.getSV(), "sv cannot be null on a CDHCPARTY object");
      if (this.cd.getS().equals(CDHCPARTYschemes.LOCAL)) {
         Validate.notNull(this.cd.getSL(), "when s==local, sl may not be null");
      }

   }

   public Cd value(String value) {
      this.cd.setValue(value);
      return this;
   }

   public Cd s(CDHCPARTYschemes scheme) {
      this.cd.setS(scheme);
      return this;
   }

   public Cd l(String l) {
      this.cd.setL(l);
      return this;
   }

   public Cd sl(String sl) {
      this.cd.setSL(sl);
      return this;
   }

   public Cd dn(String dn) {
      this.cd.setDN(dn);
      return this;
   }
}
