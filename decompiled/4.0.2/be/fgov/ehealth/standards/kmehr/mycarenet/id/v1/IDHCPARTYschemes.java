package be.fgov.ehealth.standards.kmehr.mycarenet.id.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "ID-HCPARTYschemes"
)
@XmlEnum
public enum IDHCPARTYschemes {
   @XmlEnumValue("ID-HCPARTY")
   ID_HCPARTY("ID-HCPARTY"),
   INSS("INSS"),
   LOCAL("LOCAL"),
   @XmlEnumValue("ID-ENCRYPTION-APPLICATION")
   ID_ENCRYPTION_APPLICATION("ID-ENCRYPTION-APPLICATION"),
   @XmlEnumValue("ID-ENCRYPTION-ACTOR")
   ID_ENCRYPTION_ACTOR("ID-ENCRYPTION-ACTOR"),
   @XmlEnumValue("ID-ENCRYPTION-KEY")
   ID_ENCRYPTION_KEY("ID-ENCRYPTION-KEY"),
   @XmlEnumValue("ID-INSURANCE")
   ID_INSURANCE("ID-INSURANCE"),
   @XmlEnumValue("ID-CBE")
   ID_CBE("ID-CBE"),
   @XmlEnumValue("ID-EHP")
   ID_EHP("ID-EHP");

   private final String value;

   private IDHCPARTYschemes(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static IDHCPARTYschemes fromValue(String v) {
      IDHCPARTYschemes[] var1 = values();
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         IDHCPARTYschemes c = var1[var3];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
