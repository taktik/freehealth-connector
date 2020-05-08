package org.taktik.connector.business.kmehrcommons.builders.mycarenet;

import be.fgov.ehealth.standards.kmehr.mycarenet.id.v1.IDHCPARTY;
import be.fgov.ehealth.standards.kmehr.mycarenet.id.v1.IDHCPARTYschemes;
import org.apache.commons.lang.Validate;

public class McnId {
   private IDHCPARTY id = new IDHCPARTY();

   public McnId value(String value) {
      this.id.setValue(value);
      return this;
   }

   public McnId sv(String sv) {
      this.id.setSV(sv);
      return this;
   }

   public McnId s(IDHCPARTYschemes s) {
      this.id.setS(s);
      return this;
   }

   public McnId sl(String sl) {
      this.id.setSL(sl);
      return this;
   }

   public IDHCPARTY build() {
      Validate.notNull(this.id.getS(), "schema may not be null!");
      Validate.notNull(this.id.getSV(), "sv may not be null!");
      if (IDHCPARTYschemes.LOCAL.equals(this.id.getS())) {
         Validate.notNull(this.id.getSL(), "sl may not be null when s==local");
      }

      return this.id;
   }
}
