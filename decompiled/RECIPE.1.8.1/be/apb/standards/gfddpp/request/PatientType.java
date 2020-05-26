package be.apb.standards.gfddpp.request;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "patientType",
   propOrder = {"patientId", "patientIdType"}
)
public class PatientType {
   @XmlElement(
      required = true
   )
   @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
   @XmlSchemaType(
      name = "token"
   )
   protected String patientId;
   @XmlElement(
      required = true
   )
   @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
   @XmlSchemaType(
      name = "token"
   )
   protected String patientIdType;

   public String getPatientId() {
      return this.patientId;
   }

   public void setPatientId(String value) {
      this.patientId = value;
   }

   public String getPatientIdType() {
      return this.patientIdType;
   }

   public void setPatientIdType(String value) {
      this.patientIdType = value;
   }
}
