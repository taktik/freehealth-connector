package org.etsi.uri._01903.v1_3;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "QualifierType"
)
@XmlEnum
public enum QualifierType {
   @XmlEnumValue("OIDAsURI")
   OID_AS_URI("OIDAsURI"),
   @XmlEnumValue("OIDAsURN")
   OID_AS_URN("OIDAsURN");

   private final String value;

   private QualifierType(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static QualifierType fromValue(String v) {
      QualifierType[] var1 = values();
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         QualifierType c = var1[var3];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
