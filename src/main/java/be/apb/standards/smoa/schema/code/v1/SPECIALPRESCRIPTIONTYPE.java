package be.apb.standards.smoa.schema.code.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "SPECIALPRESCRIPTIONTYPE"
)
@XmlEnum
public enum SPECIALPRESCRIPTIONTYPE {
   URGENCYTROUSSE,
   CENTRUMFORTOXICOMANEN,
   PRINSLEOPOLDINSTITUUR,
   PATIENTSGROUP,
   PENITENTIARY,
   CENTERFORASYLUMSEEKERS,
   VACCINATIONCAMPAIGNS;

   public String value() {
      return this.name();
   }

   public static SPECIALPRESCRIPTIONTYPE fromValue(String v) {
      return valueOf(v);
   }
}
