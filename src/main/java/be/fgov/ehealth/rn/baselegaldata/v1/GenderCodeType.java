package be.fgov.ehealth.rn.baselegaldata.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "GenderCodeType"
)
@XmlEnum
public enum GenderCodeType {
   M,
   F;

   public String value() {
      return this.name();
   }

   public static GenderCodeType fromValue(String v) {
      return valueOf(v);
   }
}
