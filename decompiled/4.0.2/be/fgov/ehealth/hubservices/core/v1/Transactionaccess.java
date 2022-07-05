package be.fgov.ehealth.hubservices.core.v1;

import be.ehealth.technicalconnector.adapter.XmlDateTimeAdapter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
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
   propOrder = {"patient", "transaction", "hcparties", "accessdatetime"}
)
public class Transactionaccess implements Serializable {
   private static final long serialVersionUID = 1L;
   protected PatientIdType patient;
   protected TransactionWithSpecificTime transaction;
   @XmlElement(
      name = "hcparty"
   )
   protected List<be.fgov.ehealth.standards.kmehr.schema.v1.HcpartyType> hcparties;
   @XmlElement(
      required = true,
      type = String.class
   )
   @XmlJavaTypeAdapter(XmlDateTimeAdapter.class)
   @XmlSchemaType(
      name = "dateTime"
   )
   protected DateTime accessdatetime;

   public Transactionaccess() {
   }

   public PatientIdType getPatient() {
      return this.patient;
   }

   public void setPatient(PatientIdType value) {
      this.patient = value;
   }

   public TransactionWithSpecificTime getTransaction() {
      return this.transaction;
   }

   public void setTransaction(TransactionWithSpecificTime value) {
      this.transaction = value;
   }

   public List<be.fgov.ehealth.standards.kmehr.schema.v1.HcpartyType> getHcparties() {
      if (this.hcparties == null) {
         this.hcparties = new ArrayList();
      }

      return this.hcparties;
   }

   public DateTime getAccessdatetime() {
      return this.accessdatetime;
   }

   public void setAccessdatetime(DateTime value) {
      this.accessdatetime = value;
   }
}
