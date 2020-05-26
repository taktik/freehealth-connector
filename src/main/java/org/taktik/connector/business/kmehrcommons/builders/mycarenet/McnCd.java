package org.taktik.connector.business.kmehrcommons.builders.mycarenet;

import be.fgov.ehealth.standards.kmehr.mycarenet.cd.v1.CDHCPARTY;
import be.fgov.ehealth.standards.kmehr.mycarenet.cd.v1.CDHCPARTYschemes;
import org.apache.commons.lang.Validate;

public class McnCd {
   private CDHCPARTY cd = new CDHCPARTY();

   public McnCd sv(String sv) {
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

   public McnCd value(String value) {
      this.cd.setValue(value);
      return this;
   }

   public McnCd s(CDHCPARTYschemes scheme) {
      this.cd.setS(scheme);
      return this;
   }

   public McnCd l(String l) {
      this.cd.setL(l);
      return this;
   }

   public McnCd sl(String sl) {
      this.cd.setSL(sl);
      return this;
   }

   public McnCd dn(String dn) {
      this.cd.setDN(dn);
      return this;
   }
}
