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
   name = "ConsultVmpGroupType",
   propOrder = {"name", "noGenericPrescriptionReason", "noSwitchReason", "patientFrailtyIndicator", "singleAdministrationDose", "doseUnits", "standardDosages", "additionalFields"}
)
public class ConsultVmpGroupType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Name",
      required = true
   )
   protected ConsultTextType name;
   @XmlElement(
      name = "NoGenericPrescriptionReason"
   )
   protected NoGenericPrescriptionReasonType noGenericPrescriptionReason;
   @XmlElement(
      name = "NoSwitchReason"
   )
   protected NoSwitchReasonType noSwitchReason;
   @XmlElement(
      name = "PatientFrailtyIndicator"
   )
   protected Boolean patientFrailtyIndicator;
   @XmlElement(
      name = "SingleAdministrationDose"
   )
   protected QuantityType singleAdministrationDose;
   @XmlElement(
      name = "DoseUnits"
   )
   protected List<DoseUnitsType> doseUnits;
   @XmlElement(
      name = "StandardDosage"
   )
   protected List<ConsultStandardDosageType> standardDosages;
   @XmlElement(
      name = "AdditionalFields"
   )
   protected List<AdditionalFieldsType> additionalFields;
   @XmlAttribute(
      name = "Code",
      required = true
   )
   protected int code;
   @XmlAttribute(
      name = "ProductId",
      required = true
   )
   protected String productId;
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

   public ConsultVmpGroupType() {
   }

   public ConsultTextType getName() {
      return this.name;
   }

   public void setName(ConsultTextType value) {
      this.name = value;
   }

   public NoGenericPrescriptionReasonType getNoGenericPrescriptionReason() {
      return this.noGenericPrescriptionReason;
   }

   public void setNoGenericPrescriptionReason(NoGenericPrescriptionReasonType value) {
      this.noGenericPrescriptionReason = value;
   }

   public NoSwitchReasonType getNoSwitchReason() {
      return this.noSwitchReason;
   }

   public void setNoSwitchReason(NoSwitchReasonType value) {
      this.noSwitchReason = value;
   }

   public Boolean isPatientFrailtyIndicator() {
      return this.patientFrailtyIndicator;
   }

   public void setPatientFrailtyIndicator(Boolean value) {
      this.patientFrailtyIndicator = value;
   }

   public QuantityType getSingleAdministrationDose() {
      return this.singleAdministrationDose;
   }

   public void setSingleAdministrationDose(QuantityType value) {
      this.singleAdministrationDose = value;
   }

   public List<DoseUnitsType> getDoseUnits() {
      if (this.doseUnits == null) {
         this.doseUnits = new ArrayList();
      }

      return this.doseUnits;
   }

   public List<ConsultStandardDosageType> getStandardDosages() {
      if (this.standardDosages == null) {
         this.standardDosages = new ArrayList();
      }

      return this.standardDosages;
   }

   public List<AdditionalFieldsType> getAdditionalFields() {
      if (this.additionalFields == null) {
         this.additionalFields = new ArrayList();
      }

      return this.additionalFields;
   }

   public int getCode() {
      return this.code;
   }

   public void setCode(int value) {
      this.code = value;
   }

   public String getProductId() {
      return this.productId;
   }

   public void setProductId(String value) {
      this.productId = value;
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
