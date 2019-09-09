package be.fgov.ehealth.dics.protocol.v4;

import org.taktik.connector.technical.adapter.XmlDateAdapter;
import be.fgov.ehealth.dics.core.v4.reimbursement.submit.ReimbursementContextKeyType;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.joda.time.DateTime;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ConsultReimbursementContextType",
   propOrder = {"multiple", "temporary", "reference", "flatRateSystem", "reimbursementBasePrice", "referenceBasePrice", "copaymentSupplement", "pricingUnit", "pricingSlice", "reimbursementCriterion", "copayments"}
)
public class ConsultReimbursementContextType extends ReimbursementContextKeyType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Multiple"
   )
   protected String multiple;
   @XmlElement(
      name = "Temporary"
   )
   protected boolean temporary;
   @XmlElement(
      name = "Reference"
   )
   protected boolean reference;
   @XmlElement(
      name = "FlatRateSystem"
   )
   protected boolean flatRateSystem;
   @XmlElement(
      name = "ReimbursementBasePrice",
      required = true
   )
   protected BigDecimal reimbursementBasePrice;
   @XmlElement(
      name = "ReferenceBasePrice",
      required = true
   )
   protected BigDecimal referenceBasePrice;
   @XmlElement(
      name = "CopaymentSupplement"
   )
   protected BigDecimal copaymentSupplement;
   @XmlElement(
      name = "PricingUnit",
      required = true
   )
   protected ConsultPricingUnitType pricingUnit;
   @XmlElement(
      name = "PricingSlice"
   )
   protected ConsultPricingUnitType pricingSlice;
   @XmlElement(
      name = "ReimbursementCriterion",
      required = true
   )
   protected ReimbursementCriterionType reimbursementCriterion;
   @XmlElement(
      name = "Copayment"
   )
   protected List<ConsultCopaymentType> copayments;
   @XmlAttribute(
      name = "StartDate",
      required = true
   )
   @XmlJavaTypeAdapter(XmlDateAdapter.class)
   @XmlSchemaType(
      name = "date"
   )
   protected DateTime startDate;
   @XmlAttribute(
      name = "EndDate"
   )
   @XmlJavaTypeAdapter(XmlDateAdapter.class)
   @XmlSchemaType(
      name = "date"
   )
   protected DateTime endDate;

   public String getMultiple() {
      return this.multiple;
   }

   public void setMultiple(String value) {
      this.multiple = value;
   }

   public boolean isTemporary() {
      return this.temporary;
   }

   public void setTemporary(boolean value) {
      this.temporary = value;
   }

   public boolean isReference() {
      return this.reference;
   }

   public void setReference(boolean value) {
      this.reference = value;
   }

   public boolean isFlatRateSystem() {
      return this.flatRateSystem;
   }

   public void setFlatRateSystem(boolean value) {
      this.flatRateSystem = value;
   }

   public BigDecimal getReimbursementBasePrice() {
      return this.reimbursementBasePrice;
   }

   public void setReimbursementBasePrice(BigDecimal value) {
      this.reimbursementBasePrice = value;
   }

   public BigDecimal getReferenceBasePrice() {
      return this.referenceBasePrice;
   }

   public void setReferenceBasePrice(BigDecimal value) {
      this.referenceBasePrice = value;
   }

   public BigDecimal getCopaymentSupplement() {
      return this.copaymentSupplement;
   }

   public void setCopaymentSupplement(BigDecimal value) {
      this.copaymentSupplement = value;
   }

   public ConsultPricingUnitType getPricingUnit() {
      return this.pricingUnit;
   }

   public void setPricingUnit(ConsultPricingUnitType value) {
      this.pricingUnit = value;
   }

   public ConsultPricingUnitType getPricingSlice() {
      return this.pricingSlice;
   }

   public void setPricingSlice(ConsultPricingUnitType value) {
      this.pricingSlice = value;
   }

   public ReimbursementCriterionType getReimbursementCriterion() {
      return this.reimbursementCriterion;
   }

   public void setReimbursementCriterion(ReimbursementCriterionType value) {
      this.reimbursementCriterion = value;
   }

   public List<ConsultCopaymentType> getCopayments() {
      if (this.copayments == null) {
         this.copayments = new ArrayList();
      }

      return this.copayments;
   }

   public DateTime getStartDate() {
      return this.startDate;
   }

   public void setStartDate(DateTime value) {
      this.startDate = value;
   }

   public DateTime getEndDate() {
      return this.endDate;
   }

   public void setEndDate(DateTime value) {
      this.endDate = value;
   }
}
