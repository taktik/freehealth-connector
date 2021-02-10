package be.fgov.ehealth.rn.baselegaldata.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "DatagroupStatusType"
)
@XmlEnum
public enum DatagroupStatusType {
   DATA_FOUND,
   NO_DATA_FOUND,
   NOT_SUPPORTED;

   public String value() {
      return this.name();
   }

   public static DatagroupStatusType fromValue(String v) {
      return valueOf(v);
   }
}
