package be.fgov.ehealth.genericinsurability.core.v1;

import org.taktik.connector.technical.adapter.XmlDateAdapter;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.joda.time.DateTime;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "HospitalizedType",
   propOrder = {"hospital", "admissionDate", "admissionService"}
)
public class HospitalizedType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Hospital",
      required = true
   )
   protected String hospital;
   @XmlElement(
      name = "AdmissionDate",
      type = String.class
   )
   @XmlJavaTypeAdapter(XmlDateAdapter.class)
   @XmlSchemaType(
      name = "date"
   )
   protected DateTime admissionDate;
   @XmlElement(
      name = "AdmissionService"
   )
   protected String admissionService;

   public String getHospital() {
      return this.hospital;
   }

   public void setHospital(String value) {
      this.hospital = value;
   }

   public DateTime getAdmissionDate() {
      return this.admissionDate;
   }

   public void setAdmissionDate(DateTime value) {
      this.admissionDate = value;
   }

   public String getAdmissionService() {
      return this.admissionService;
   }

   public void setAdmissionService(String value) {
      this.admissionService = value;
   }
}
