package be.fgov.ehealth.rn.baselegaldata.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "UpdateDatagroupStatusType"
)
@XmlEnum
public enum UpdateDatagroupStatusType {
   OK,
   NOK,
   NO_UPDATE,
   DEFERRED;

   public String value() {
      return this.name();
   }

   public static UpdateDatagroupStatusType fromValue(String v) {
      return valueOf(v);
   }
}
