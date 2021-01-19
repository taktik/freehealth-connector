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
      QualifierType[] arr$ = values();
      int len$ = arr$.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         QualifierType c = arr$[i$];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
