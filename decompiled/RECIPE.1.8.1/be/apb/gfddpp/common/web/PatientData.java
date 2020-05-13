package be.apb.gfddpp.common.web;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "PatientData"
)
public class PatientData {
   @XmlElement(
      name = "id"
   )
   private String id;
   @XmlElement(
      name = "patientid"
   )
   private String patientid;
   @XmlElement(
      name = "patienttype"
   )
   private String patienttype;

   public PatientData() {
   }

   public PatientData(String id, String patient_id, String patientType) {
      this.id = id;
      this.patientid = patient_id;
      this.patienttype = patientType;
   }

   public String getPatient_type() {
      return this.patienttype;
   }

   public void setPatient_type(String patient_type) {
      this.patienttype = patient_type;
   }

   public String getId() {
      return this.id;
   }

   public void setId(String id) {
      this.id = id;
   }

   public String getPatient_id() {
      return this.patientid;
   }

   public void setPatient_id(String patient_id) {
      this.patientid = patient_id;
   }
}
