package be.fgov.ehealth.messageservices.mycarenet.core.v1;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "SelectRetrieveTransactionType",
   propOrder = {"patient", "transaction"}
)
public class SelectRetrieveTransactionType implements Serializable {
   private static final long serialVersionUID = 1L;
   protected PatientType patient;
   @XmlElement(
      required = true
   )
   protected TransactionType transaction;

   public SelectRetrieveTransactionType() {
   }

   public PatientType getPatient() {
      return this.patient;
   }

   public void setPatient(PatientType value) {
      this.patient = value;
   }

   public TransactionType getTransaction() {
      return this.transaction;
   }

   public void setTransaction(TransactionType value) {
      this.transaction = value;
   }
}
