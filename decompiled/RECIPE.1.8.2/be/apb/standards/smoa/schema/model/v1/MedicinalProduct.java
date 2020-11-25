package be.apb.standards.smoa.schema.model.v1;

import be.apb.standards.smoa.schema.code.v1.AbstractActiveSubstanceCodeType;
import be.apb.standards.smoa.schema.code.v1.AbstractDiseaseCodeType;
import be.apb.standards.smoa.schema.code.v1.AbstractMedicinalProductCategoryCodeType;
import be.apb.standards.smoa.schema.code.v1.AbstractPharmFormCodeType;
import be.apb.standards.smoa.schema.id.v1.AbstractMedicinalProductIdType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "MedicinalProduct",
   propOrder = {"id", "productName", "disease", "category", "activeSubstance", "activeSubstanceQuantity", "licenseOwner", "licenseNumber", "pharmForm", "serialNumber", "uniqueBarcode", "batch", "isSubstitutionAllowed", "nationalLicensedMedicine", "emeaRegisteredMedicine", "narcotic", "psychotrope", "hypnotic", "adUsVetOnly"}
)
public class MedicinalProduct extends AbstractMedicinalProductType {
   protected AbstractMedicinalProductIdType id;
   protected String productName;
   protected AbstractDiseaseCodeType disease;
   protected AbstractMedicinalProductCategoryCodeType category;
   protected AbstractActiveSubstanceCodeType activeSubstance;
   protected ActiveSubstanceQuantityType activeSubstanceQuantity;
   protected String licenseOwner;
   protected String licenseNumber;
   protected AbstractPharmFormCodeType pharmForm;
   protected String serialNumber;
   protected Integer uniqueBarcode;
   protected String batch;
   protected Boolean isSubstitutionAllowed;
   protected Boolean nationalLicensedMedicine;
   protected Boolean emeaRegisteredMedicine;
   protected Boolean narcotic;
   protected Boolean psychotrope;
   protected Boolean hypnotic;
   protected Boolean adUsVetOnly;

   public AbstractMedicinalProductIdType getId() {
      return this.id;
   }

   public void setId(AbstractMedicinalProductIdType value) {
      this.id = value;
   }

   public String getProductName() {
      return this.productName;
   }

   public void setProductName(String value) {
      this.productName = value;
   }

   public AbstractDiseaseCodeType getDisease() {
      return this.disease;
   }

   public void setDisease(AbstractDiseaseCodeType value) {
      this.disease = value;
   }

   public AbstractMedicinalProductCategoryCodeType getCategory() {
      return this.category;
   }

   public void setCategory(AbstractMedicinalProductCategoryCodeType value) {
      this.category = value;
   }

   public AbstractActiveSubstanceCodeType getActiveSubstance() {
      return this.activeSubstance;
   }

   public void setActiveSubstance(AbstractActiveSubstanceCodeType value) {
      this.activeSubstance = value;
   }

   public ActiveSubstanceQuantityType getActiveSubstanceQuantity() {
      return this.activeSubstanceQuantity;
   }

   public void setActiveSubstanceQuantity(ActiveSubstanceQuantityType value) {
      this.activeSubstanceQuantity = value;
   }

   public String getLicenseOwner() {
      return this.licenseOwner;
   }

   public void setLicenseOwner(String value) {
      this.licenseOwner = value;
   }

   public String getLicenseNumber() {
      return this.licenseNumber;
   }

   public void setLicenseNumber(String value) {
      this.licenseNumber = value;
   }

   public AbstractPharmFormCodeType getPharmForm() {
      return this.pharmForm;
   }

   public void setPharmForm(AbstractPharmFormCodeType value) {
      this.pharmForm = value;
   }

   public String getSerialNumber() {
      return this.serialNumber;
   }

   public void setSerialNumber(String value) {
      this.serialNumber = value;
   }

   public Integer getUniqueBarcode() {
      return this.uniqueBarcode;
   }

   public void setUniqueBarcode(Integer value) {
      this.uniqueBarcode = value;
   }

   public String getBatch() {
      return this.batch;
   }

   public void setBatch(String value) {
      this.batch = value;
   }

   public Boolean isIsSubstitutionAllowed() {
      return this.isSubstitutionAllowed;
   }

   public void setIsSubstitutionAllowed(Boolean value) {
      this.isSubstitutionAllowed = value;
   }

   public Boolean isNationalLicensedMedicine() {
      return this.nationalLicensedMedicine;
   }

   public void setNationalLicensedMedicine(Boolean value) {
      this.nationalLicensedMedicine = value;
   }

   public Boolean isEmeaRegisteredMedicine() {
      return this.emeaRegisteredMedicine;
   }

   public void setEmeaRegisteredMedicine(Boolean value) {
      this.emeaRegisteredMedicine = value;
   }

   public Boolean isNarcotic() {
      return this.narcotic;
   }

   public void setNarcotic(Boolean value) {
      this.narcotic = value;
   }

   public Boolean isPsychotrope() {
      return this.psychotrope;
   }

   public void setPsychotrope(Boolean value) {
      this.psychotrope = value;
   }

   public Boolean isHypnotic() {
      return this.hypnotic;
   }

   public void setHypnotic(Boolean value) {
      this.hypnotic = value;
   }

   public Boolean isAdUsVetOnly() {
      return this.adUsVetOnly;
   }

   public void setAdUsVetOnly(Boolean value) {
      this.adUsVetOnly = value;
   }
}
