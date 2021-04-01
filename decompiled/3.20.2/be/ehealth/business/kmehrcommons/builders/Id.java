package be.ehealth.business.kmehrcommons.builders;

import be.fgov.ehealth.standards.kmehr.id.v1.IDHCPARTY;
import be.fgov.ehealth.standards.kmehr.id.v1.IDHCPARTYschemes;
import org.apache.commons.lang.Validate;

public class Id {
   private IDHCPARTY id = new IDHCPARTY();

   public Id value(String value) {
      this.id.setValue(value);
      return this;
   }

   public Id sv(String sv) {
      this.id.setSV(sv);
      return this;
   }

   public Id s(IDHCPARTYschemes s) {
      this.id.setS(s);
      return this;
   }

   public Id sl(String sl) {
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
