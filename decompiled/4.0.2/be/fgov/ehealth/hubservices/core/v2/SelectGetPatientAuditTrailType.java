package be.fgov.ehealth.hubservices.core.v2;

import be.ehealth.technicalconnector.adapter.XmlDateAdapter;
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
   name = "SelectGetPatientAuditTrailType",
   propOrder = {"patient", "transaction", "hcparty", "begindate", "enddate", "searchtype"}
)
public class SelectGetPatientAuditTrailType implements Serializable {
   private static final long serialVersionUID = 1L;
   protected PatientIdType patient;
   protected BasicTransactionType transaction;
   protected HCPartyIdType hcparty;
   @XmlElement(
      type = String.class
   )
   @XmlJavaTypeAdapter(XmlDateAdapter.class)
   @XmlSchemaType(
      name = "date"
   )
   protected DateTime begindate;
   @XmlElement(
      type = String.class
   )
   @XmlJavaTypeAdapter(XmlDateAdapter.class)
   @XmlSchemaType(
      name = "date"
   )
   protected DateTime enddate;
   @XmlSchemaType(
      name = "string"
   )
   protected LocalSearchType searchtype;

   public SelectGetPatientAuditTrailType() {
   }

   public PatientIdType getPatient() {
      return this.patient;
   }

   public void setPatient(PatientIdType value) {
      this.patient = value;
   }

   public BasicTransactionType getTransaction() {
      return this.transaction;
   }

   public void setTransaction(BasicTransactionType value) {
      this.transaction = value;
   }

   public HCPartyIdType getHcparty() {
      return this.hcparty;
   }

   public void setHcparty(HCPartyIdType value) {
      this.hcparty = value;
   }

   public DateTime getBegindate() {
      return this.begindate;
   }

   public void setBegindate(DateTime value) {
      this.begindate = value;
   }

   public DateTime getEnddate() {
      return this.enddate;
   }

   public void setEnddate(DateTime value) {
      this.enddate = value;
   }

   public LocalSearchType getSearchtype() {
      return this.searchtype;
   }

   public void setSearchtype(LocalSearchType value) {
      this.searchtype = value;
   }
}
