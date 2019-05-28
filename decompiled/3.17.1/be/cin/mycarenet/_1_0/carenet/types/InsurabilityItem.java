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
   name = "InsurabilityItemType",
   propOrder = {"regNrWithMut", "mutuality", "period", "ct1", "ct2", "paymentApproval", "insurabilityDate"}
)
@XmlRootElement(
   name = "InsurabilityItem"
)
public class InsurabilityItem implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "RegNrWithMut"
   )
   protected String regNrWithMut;
   @XmlElement(
      name = "Mutuality",
      required = true
   )
   protected String mutuality;
   @XmlElement(
      name = "Period",
      required = true
   )
   protected PeriodType period;
   @XmlElement(
      name = "CT1"
   )
   protected String ct1;
   @XmlElement(
      name = "CT2"
   )
   protected String ct2;
   @XmlElement(
      name = "PaymentApproval"
   )
   protected String paymentApproval;
   @XmlElement(
      name = "InsurabilityDate",
      required = true,
      type = String.class
   )
   @XmlJavaTypeAdapter(XmlDateNoTzAdapter.class)
   @XmlSchemaType(
      name = "date"
   )
   protected DateTime insurabilityDate;

   public String getRegNrWithMut() {
      return this.regNrWithMut;
   }

   public void setRegNrWithMut(String value) {
      this.regNrWithMut = value;
   }

   public String getMutuality() {
      return this.mutuality;
   }

   public void setMutuality(String value) {
      this.mutuality = value;
   }

   public PeriodType getPeriod() {
      return this.period;
   }

   public void setPeriod(PeriodType value) {
      this.period = value;
   }

   public String getCT1() {
      return this.ct1;
   }

   public void setCT1(String value) {
      this.ct1 = value;
   }

   public String getCT2() {
      return this.ct2;
   }

   public void setCT2(String value) {
      this.ct2 = value;
   }

   public String getPaymentApproval() {
      return this.paymentApproval;
   }

   public void setPaymentApproval(String value) {
      this.paymentApproval = value;
   }

   public DateTime getInsurabilityDate() {
      return this.insurabilityDate;
   }

   public void setInsurabilityDate(DateTime value) {
      this.insurabilityDate = value;
   }
}
