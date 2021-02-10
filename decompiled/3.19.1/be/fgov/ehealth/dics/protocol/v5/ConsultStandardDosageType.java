package be.fgov.ehealth.dics.protocol.v5;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ConsultStandardDosageType",
   propOrder = {"indications", "standardDosageParamBounds", "targetGroup", "kidneyFailureClass", "liverFailureClass", "routeOfAdministrationCodes", "routeSpecification", "treatmentDurationType", "temporaryDurationDetails", "dosageUnit", "administrationFrequency", "maximumAdministrationFrequency", "maximumDailyQuantity", "textualDosage", "supplementaryInfo"}
)
public class ConsultStandardDosageType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Indication",
      required = true
   )
   protected List<ConsultIndicationType> indications;
   @XmlElement(
      name = "StandardDosageParamBounds",
      required = true
   )
   protected List<ConsultBoundedParameterType> standardDosageParamBounds;
   @XmlElement(
      name = "TargetGroup",
      required = true
   )
   protected String targetGroup;
   @XmlElement(
      name = "KidneyFailureClass"
   )
   protected Integer kidneyFailureClass;
   @XmlElement(
      name = "LiverFailureClass"
   )
   protected Integer liverFailureClass;
   @XmlElement(
      name = "RouteOfAdministrationCode",
      required = true
   )
   protected List<String> routeOfAdministrationCodes;
   @XmlElement(
      name = "RouteSpecification"
   )
   protected ConsultTextType routeSpecification;
   @XmlElement(
      name = "TreatmentDurationType",
      required = true
   )
   protected String treatmentDurationType;
   @XmlElement(
      name = "TemporaryDurationDetails"
   )
   protected ConsultTemporaryDurationDetailsType temporaryDurationDetails;
   @XmlElement(
      name = "DosageUnit"
   )
   protected ConsultParameterizedQuantityType dosageUnit;
   @XmlElement(
      name = "AdministrationFrequency"
   )
   protected ConsultAdministrationFrequencyType administrationFrequency;
   @XmlElement(
      name = "MaximumAdministrationFrequency"
   )
   protected ConsultAdministrationFrequencyType maximumAdministrationFrequency;
   @XmlElement(
      name = "MaximumDailyQuantity"
   )
   protected ConsultParameterizedQuantityType maximumDailyQuantity;
   @XmlElement(
      name = "TextualDosage"
   )
   protected ConsultTextType textualDosage;
   @XmlElement(
      name = "SupplementaryInfo"
   )
   protected ConsultTextType supplementaryInfo;
   @XmlAttribute(
      name = "Code",
      required = true
   )
   protected int code;

   public List<ConsultIndicationType> getIndications() {
      if (this.indications == null) {
         this.indications = new ArrayList();
      }

      return this.indications;
   }

   public List<ConsultBoundedParameterType> getStandardDosageParamBounds() {
      if (this.standardDosageParamBounds == null) {
         this.standardDosageParamBounds = new ArrayList();
      }

      return this.standardDosageParamBounds;
   }

   public String getTargetGroup() {
      return this.targetGroup;
   }

   public void setTargetGroup(String value) {
      this.targetGroup = value;
   }

   public Integer getKidneyFailureClass() {
      return this.kidneyFailureClass;
   }

   public void setKidneyFailureClass(Integer value) {
      this.kidneyFailureClass = value;
   }

   public Integer getLiverFailureClass() {
      return this.liverFailureClass;
   }

   public void setLiverFailureClass(Integer value) {
      this.liverFailureClass = value;
   }

   public List<String> getRouteOfAdministrationCodes() {
      if (this.routeOfAdministrationCodes == null) {
         this.routeOfAdministrationCodes = new ArrayList();
      }

      return this.routeOfAdministrationCodes;
   }

   public ConsultTextType getRouteSpecification() {
      return this.routeSpecification;
   }

   public void setRouteSpecification(ConsultTextType value) {
      this.routeSpecification = value;
   }

   public String getTreatmentDurationType() {
      return this.treatmentDurationType;
   }

   public void setTreatmentDurationType(String value) {
      this.treatmentDurationType = value;
   }

   public ConsultTemporaryDurationDetailsType getTemporaryDurationDetails() {
      return this.temporaryDurationDetails;
   }

   public void setTemporaryDurationDetails(ConsultTemporaryDurationDetailsType value) {
      this.temporaryDurationDetails = value;
   }

   public ConsultParameterizedQuantityType getDosageUnit() {
      return this.dosageUnit;
   }

   public void setDosageUnit(ConsultParameterizedQuantityType value) {
      this.dosageUnit = value;
   }

   public ConsultAdministrationFrequencyType getAdministrationFrequency() {
      return this.administrationFrequency;
   }

   public void setAdministrationFrequency(ConsultAdministrationFrequencyType value) {
      this.administrationFrequency = value;
   }

   public ConsultAdministrationFrequencyType getMaximumAdministrationFrequency() {
      return this.maximumAdministrationFrequency;
   }

   public void setMaximumAdministrationFrequency(ConsultAdministrationFrequencyType value) {
      this.maximumAdministrationFrequency = value;
   }

   public ConsultParameterizedQuantityType getMaximumDailyQuantity() {
      return this.maximumDailyQuantity;
   }

   public void setMaximumDailyQuantity(ConsultParameterizedQuantityType value) {
      this.maximumDailyQuantity = value;
   }

   public ConsultTextType getTextualDosage() {
      return this.textualDosage;
   }

   public void setTextualDosage(ConsultTextType value) {
      this.textualDosage = value;
   }

   public ConsultTextType getSupplementaryInfo() {
      return this.supplementaryInfo;
   }

   public void setSupplementaryInfo(ConsultTextType value) {
      this.supplementaryInfo = value;
   }

   public int getCode() {
      return this.code;
   }

   public void setCode(int value) {
      this.code = value;
   }
}
