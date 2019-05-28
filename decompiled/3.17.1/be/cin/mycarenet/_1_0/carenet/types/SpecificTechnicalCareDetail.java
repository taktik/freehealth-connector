package be.cin.mycarenet._1_0.carenet.types;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "SpecificTechnicalCareDetailType",
   propOrder = {"nurseReference", "requester", "treatmentPeriod", "prescriber", "administeredProduct"}
)
@XmlRootElement(
   name = "SpecificTechnicalCareDetail"
)
public class SpecificTechnicalCareDetail implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "NurseReference",
      required = true
   )
   protected String nurseReference;
   @XmlElement(
      name = "Requester",
      required = true
   )
   protected String requester;
   @XmlElement(
      name = "TreatmentPeriod",
      required = true
   )
   protected PeriodType treatmentPeriod;
   @XmlElement(
      name = "Prescriber",
      required = true
   )
   protected String prescriber;
   @XmlElement(
      name = "AdministeredProduct",
      required = true
   )
   protected String administeredProduct;
   @XmlAttribute(
      name = "type",
      required = true
   )
   protected SpecificTechnicalCareTypeType type;

   public String getNurseReference() {
      return this.nurseReference;
   }

   public void setNurseReference(String value) {
      this.nurseReference = value;
   }

   public String getRequester() {
      return this.requester;
   }

   public void setRequester(String value) {
      this.requester = value;
   }

   public PeriodType getTreatmentPeriod() {
      return this.treatmentPeriod;
   }

   public void setTreatmentPeriod(PeriodType value) {
      this.treatmentPeriod = value;
   }

   public String getPrescriber() {
      return this.prescriber;
   }

   public void setPrescriber(String value) {
      this.prescriber = value;
   }

   public String getAdministeredProduct() {
      return this.administeredProduct;
   }

   public void setAdministeredProduct(String value) {
      this.administeredProduct = value;
   }

   public SpecificTechnicalCareTypeType getType() {
      return this.type;
   }

   public void setType(SpecificTechnicalCareTypeType value) {
      this.type = value;
   }
}
