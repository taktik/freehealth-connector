package be.fgov.ehealth.rn.baselegaldata.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "SourceType"
)
@XmlEnum
public enum SourceType {
   NR,
   CBSS,
   BOTH;

   public String value() {
      return this.name();
   }

   public static SourceType fromValue(String v) {
      return valueOf(v);
   }
}
