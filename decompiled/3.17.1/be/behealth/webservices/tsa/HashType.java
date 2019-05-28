package be.behealth.webservices.tsa;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "HashType"
)
@XmlEnum
public enum HashType {
   @XmlEnumValue("SHA-256")
   SHA_256("SHA-256");

   private final String value;

   private HashType(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static HashType fromValue(String v) {
      HashType[] arr$ = values();
      int len$ = arr$.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         HashType c = arr$[i$];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
