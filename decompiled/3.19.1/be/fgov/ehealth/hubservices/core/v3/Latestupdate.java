package be.fgov.ehealth.hubservices.core.v3;

import be.ehealth.technicalconnector.adapter.XmlDateTimeAdapter;
import be.fgov.ehealth.standards.kmehr.cd.v1.CDTRANSACTION;
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
   name = "",
   propOrder = {"patient", "cd", "version", "createdatetime", "updatedatetime"}
)
public class Latestupdate implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      required = true
   )
   protected PatientIdType patient;
   @XmlElement(
      required = true
   )
   protected CDTRANSACTION cd;
   protected String version;
   @XmlElement(
      type = String.class
   )
   @XmlJavaTypeAdapter(XmlDateTimeAdapter.class)
   @XmlSchemaType(
      name = "dateTime"
   )
   protected DateTime createdatetime;
   @XmlElement(
      required = true,
      type = String.class
   )
   @XmlJavaTypeAdapter(XmlDateTimeAdapter.class)
   @XmlSchemaType(
      name = "dateTime"
   )
   protected DateTime updatedatetime;

   public PatientIdType getPatient() {
      return this.patient;
   }

   public void setPatient(PatientIdType value) {
      this.patient = value;
   }

   public CDTRANSACTION getCd() {
      return this.cd;
   }

   public void setCd(CDTRANSACTION value) {
      this.cd = value;
   }

   public String getVersion() {
      return this.version;
   }

   public void setVersion(String value) {
      this.version = value;
   }

   public DateTime getCreatedatetime() {
      return this.createdatetime;
   }

   public void setCreatedatetime(DateTime value) {
      this.createdatetime = value;
   }

   public DateTime getUpdatedatetime() {
      return this.updatedatetime;
   }

   public void setUpdatedatetime(DateTime value) {
      this.updatedatetime = value;
   }
}
