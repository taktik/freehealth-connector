package be.cin.mycarenet._1_0.carenet.types;

import be.ehealth.technicalconnector.adapter.XmlDateNoTzAdapter;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.joda.time.DateTime;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "HospitalizedType",
   propOrder = {"hospital", "admissionDate", "admissionService"}
)
@XmlRootElement(
   name = "Hospitalized"
)
public class Hospitalized implements Serializable {
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
   @XmlJavaTypeAdapter(XmlDateNoTzAdapter.class)
   @XmlSchemaType(
      name = "date"
   )
   protected DateTime admissionDate;
   @XmlElement(
      name = "AdmissionService"
   )
   protected String admissionService;

   public Hospitalized() {
   }

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
