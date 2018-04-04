package be.fgov.ehealth.hubservices.core.v1;

import be.fgov.ehealth.standards.kmehr.schema.v1.PersonType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "folderTypeUnbounded",
   propOrder = {"patient", "transactions"}
)
public class FolderTypeUnbounded implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      required = true
   )
   protected PersonType patient;
   @XmlElement(
      name = "transaction",
      required = true
   )
   protected List<TransactionSummaryType> transactions;

   public PersonType getPatient() {
      return this.patient;
   }

   public void setPatient(PersonType value) {
      this.patient = value;
   }

   public List<TransactionSummaryType> getTransactions() {
      if (this.transactions == null) {
         this.transactions = new ArrayList();
      }

      return this.transactions;
   }
}
