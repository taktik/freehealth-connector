package be.fgov.ehealth.hubservices.core.v1;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "SelectRequestPublicationType",
   propOrder = {"patient", "transaction", "comment"}
)
public class SelectRequestPublicationType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      required = true
   )
   protected PatientIdType patient;
   @XmlElement(
      required = true
   )
   protected TransactionWithPeriodType transaction;
   protected String comment;

   public PatientIdType getPatient() {
      return this.patient;
   }

   public void setPatient(PatientIdType value) {
      this.patient = value;
   }

   public TransactionWithPeriodType getTransaction() {
      return this.transaction;
   }

   public void setTransaction(TransactionWithPeriodType value) {
      this.transaction = value;
   }

   public String getComment() {
      return this.comment;
   }

   public void setComment(String value) {
      this.comment = value;
   }
}
