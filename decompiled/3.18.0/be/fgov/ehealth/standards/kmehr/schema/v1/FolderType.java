package be.fgov.ehealth.standards.kmehr.schema.v1;

import be.fgov.ehealth.standards.kmehr.cd.v1.LnkType;
import be.fgov.ehealth.standards.kmehr.dt.v1.TextType;
import be.fgov.ehealth.standards.kmehr.id.v1.IDKMEHR;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "folderType",
   propOrder = {"confidentiality", "ids", "patient", "transactions", "texts", "lnks"}
)
@XmlRootElement(
   name = "folderType"
)
public class FolderType implements Serializable {
   private static final long serialVersionUID = 1L;
   protected ConfidentialityType confidentiality;
   @XmlElement(
      name = "id",
      required = true
   )
   protected List<IDKMEHR> ids;
   @XmlElement(
      required = true
   )
   protected PersonType patient;
   @XmlElement(
      name = "transaction",
      required = true
   )
   protected List<TransactionType> transactions;
   @XmlElement(
      name = "text"
   )
   protected List<TextType> texts;
   @XmlElement(
      name = "lnk"
   )
   protected List<LnkType> lnks;

   public ConfidentialityType getConfidentiality() {
      return this.confidentiality;
   }

   public void setConfidentiality(ConfidentialityType value) {
      this.confidentiality = value;
   }

   public List<IDKMEHR> getIds() {
      if (this.ids == null) {
         this.ids = new ArrayList();
      }

      return this.ids;
   }

   public PersonType getPatient() {
      return this.patient;
   }

   public void setPatient(PersonType value) {
      this.patient = value;
   }

   public List<TransactionType> getTransactions() {
      if (this.transactions == null) {
         this.transactions = new ArrayList();
      }

      return this.transactions;
   }

   public List<TextType> getTexts() {
      if (this.texts == null) {
         this.texts = new ArrayList();
      }

      return this.texts;
   }

   public List<LnkType> getLnks() {
      if (this.lnks == null) {
         this.lnks = new ArrayList();
      }

      return this.lnks;
   }
}
