package be.fgov.ehealth.dics.protocol.v5;

import be.ehealth.technicalconnector.adapter.XmlDateAdapter;
import java.io.Serializable;
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
   name = "ConsultCommercializationType",
   propOrder = {"endOfCommercialization", "reason", "additionalInformation", "impact", "additionalFields"}
)
public class ConsultCommercializationType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "EndOfCommercialization"
   )
   protected ConsultTextType endOfCommercialization;
   @XmlElement(
      name = "Reason"
   )
   protected ConsultTextType reason;
   @XmlElement(
      name = "AdditionalInformation"
   )
   protected ConsultTextType additionalInformation;
   @XmlElement(
      name = "Impact"
   )
   protected ConsultTextType impact;
   @XmlElement(
      name = "AdditionalFields"
   )
   protected List<AdditionalFieldsType> additionalFields;
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

   public ConsultTextType getEndOfCommercialization() {
      return this.endOfCommercialization;
   }

   public void setEndOfCommercialization(ConsultTextType value) {
      this.endOfCommercialization = value;
   }

   public ConsultTextType getReason() {
      return this.reason;
   }

   public void setReason(ConsultTextType value) {
      this.reason = value;
   }

   public ConsultTextType getAdditionalInformation() {
      return this.additionalInformation;
   }

   public void setAdditionalInformation(ConsultTextType value) {
      this.additionalInformation = value;
   }

   public ConsultTextType getImpact() {
      return this.impact;
   }

   public void setImpact(ConsultTextType value) {
      this.impact = value;
   }

   public List<AdditionalFieldsType> getAdditionalFields() {
      if (this.additionalFields == null) {
         this.additionalFields = new ArrayList();
      }

      return this.additionalFields;
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
