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
   name = "PalliativeCareResponseDetailType",
   propOrder = {"consultantDoctor", "decision", "invoiceStartDate"}
)
@XmlRootElement(
   name = "PalliativeCareResponseDetail"
)
public class PalliativeCareResponseDetail implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "ConsultantDoctor"
   )
   protected String consultantDoctor;
   @XmlElement(
      name = "Decision",
      required = true
   )
   protected DecisionType decision;
   @XmlElement(
      name = "InvoiceStartDate",
      type = String.class
   )
   @XmlJavaTypeAdapter(XmlDateNoTzAdapter.class)
   @XmlSchemaType(
      name = "date"
   )
   protected DateTime invoiceStartDate;

   public String getConsultantDoctor() {
      return this.consultantDoctor;
   }

   public void setConsultantDoctor(String value) {
      this.consultantDoctor = value;
   }

   public DecisionType getDecision() {
      return this.decision;
   }

   public void setDecision(DecisionType value) {
      this.decision = value;
   }

   public DateTime getInvoiceStartDate() {
      return this.invoiceStartDate;
   }

   public void setInvoiceStartDate(DateTime value) {
      this.invoiceStartDate = value;
   }
}
