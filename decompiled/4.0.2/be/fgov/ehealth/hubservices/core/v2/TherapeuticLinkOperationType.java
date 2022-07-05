package be.fgov.ehealth.hubservices.core.v2;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "TherapeuticLinkOperationType"
)
@XmlEnum
public enum TherapeuticLinkOperationType {
   @XmlEnumValue("declaration")
   DECLARATION("declaration"),
   @XmlEnumValue("revocation")
   REVOCATION("revocation");

   private final String value;

   private TherapeuticLinkOperationType(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static TherapeuticLinkOperationType fromValue(String v) {
      TherapeuticLinkOperationType[] var1 = values();
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         TherapeuticLinkOperationType c = var1[var3];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
