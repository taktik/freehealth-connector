package be.fgov.ehealth.hubservices.core.v1;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "SelectRevokeTransactionType",
   propOrder = {"patient", "transaction"}
)
public class SelectRevokeTransactionType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      required = true
   )
   protected PatientIdType patient;
   @XmlElement(
      required = true
   )
   protected TransactionIdType transaction;

   public PatientIdType getPatient() {
      return this.patient;
   }

   public void setPatient(PatientIdType value) {
      this.patient = value;
   }

   public TransactionIdType getTransaction() {
      return this.transaction;
   }

   public void setTransaction(TransactionIdType value) {
      this.transaction = value;
   }
}
