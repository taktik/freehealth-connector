package be.fgov.ehealth.dics.protocol.v5;

import be.ehealth.technicalconnector.adapter.XmlDateAdapter;
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
   name = "ConsultAmppType",
   propOrder = {"orphan", "leafletUrl", "spcUrl", "rmaPatientUrl", "rmaProfessionalUrl", "parallelCircuit", "parallelDistributor", "packMultiplier", "packAmount", "packDisplayValue", "authorisationNr", "singleUse", "speciallyRegulated", "abbreviatedName", "prescriptionName", "note", "posologyNote", "crmUrl", "exFactoryPrice", "reimbursementCode", "atcs", "deliveryModus", "deliveryModusSpecification", "noGenericPrescriptionReasons", "distributorCompanyActorNr", "status", "fmdProductCodes", "fmdInScope", "antiTemperingDevicePresent", "dhpcUrl", "definedDailyDose", "additionalFields", "amppComponents", "commercialization", "supplyProblem", "derogationImports", "dmpps"}
)
public class ConsultAmppType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Orphan"
   )
   protected boolean orphan;
   @XmlElement(
      name = "LeafletUrl"
   )
   protected ConsultTextType leafletUrl;
   @XmlElement(
      name = "SpcUrl"
   )
   protected ConsultTextType spcUrl;
   @XmlElement(
      name = "RmaPatientUrl"
   )
   protected ConsultTextType rmaPatientUrl;
   @XmlElement(
      name = "RmaProfessionalUrl"
   )
   protected ConsultTextType rmaProfessionalUrl;
   @XmlElement(
      name = "ParallelCircuit"
   )
   protected String parallelCircuit;
   @XmlElement(
      name = "ParallelDistributor"
   )
   protected String parallelDistributor;
   @XmlElement(
      name = "PackMultiplier"
   )
   protected Integer packMultiplier;
   @XmlElement(
      name = "PackAmount"
   )
   protected PackAmountType packAmount;
   @XmlElement(
      name = "PackDisplayValue"
   )
   protected String packDisplayValue;
   @XmlElement(
      name = "AuthorisationNr",
      required = true
   )
   protected String authorisationNr;
   @XmlElement(
      name = "SingleUse"
   )
   protected Boolean singleUse;
   @XmlElement(
      name = "SpeciallyRegulated"
   )
   protected String speciallyRegulated;
   @XmlElement(
      name = "AbbreviatedName"
   )
   protected ConsultTextType abbreviatedName;
   @XmlElement(
      name = "PrescriptionName"
   )
   protected ConsultTextType prescriptionName;
   @XmlElement(
      name = "Note"
   )
   protected ConsultTextType note;
   @XmlElement(
      name = "PosologyNote"
   )
   protected ConsultTextType posologyNote;
   @XmlElement(
      name = "CrmUrl"
   )
   protected ConsultTextType crmUrl;
   @XmlElement(
      name = "ExFactoryPrice"
   )
   protected BigDecimal exFactoryPrice;
   @XmlElement(
      name = "ReimbursementCode"
   )
   protected String reimbursementCode;
   @XmlElement(
      name = "Atc"
   )
   protected List<AtcClassificationType> atcs;
   @XmlElement(
      name = "DeliveryModus",
      required = true
   )
   protected DeliveryModusType deliveryModus;
   @XmlElement(
      name = "DeliveryModusSpecification"
   )
   protected DeliveryModusSpecificationType deliveryModusSpecification;
   @XmlElement(
      name = "NoGenericPrescriptionReason"
   )
   protected List<NoGenericPrescriptionReasonType> noGenericPrescriptionReasons;
   @XmlElement(
      name = "DistributorCompanyActorNr"
   )
   protected String distributorCompanyActorNr;
   @XmlElement(
      name = "Status",
      required = true
   )
   protected String status;
   @XmlElement(
      name = "FMDProductCode"
   )
   protected List<String> fmdProductCodes;
   @XmlElement(
      name = "FMDInScope"
   )
   protected Boolean fmdInScope;
   @XmlElement(
      name = "AntiTemperingDevicePresent"
   )
   protected Boolean antiTemperingDevicePresent;
   @XmlElement(
      name = "DHPCUrl"
   )
   protected ConsultTextType dhpcUrl;
   @XmlElement(
      name = "DefinedDailyDose"
   )
   protected QuantityType definedDailyDose;
   @XmlElement(
      name = "AdditionalFields"
   )
   protected List<AdditionalFieldsType> additionalFields;
   @XmlElement(
      name = "AmppComponent",
      required = true
   )
   protected List<ConsultAmppComponentType> amppComponents;
   @XmlElement(
      name = "Commercialization"
   )
   protected ConsultCommercializationType commercialization;
   @XmlElement(
      name = "SupplyProblem"
   )
   protected ConsultSupplyProblemType supplyProblem;
   @XmlElement(
      name = "DerogationImport"
   )
   protected List<ConsultDerogationImportType> derogationImports;
   @XmlElement(
      name = "Dmpp"
   )
   protected List<ConsultDmppType> dmpps;
   @XmlAttribute(
      name = "CtiExtended",
      required = true
   )
   protected String ctiExtended;
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

   public ConsultAmppType() {
   }

   public boolean isOrphan() {
      return this.orphan;
   }

   public void setOrphan(boolean value) {
      this.orphan = value;
   }

   public ConsultTextType getLeafletUrl() {
      return this.leafletUrl;
   }

   public void setLeafletUrl(ConsultTextType value) {
      this.leafletUrl = value;
   }

   public ConsultTextType getSpcUrl() {
      return this.spcUrl;
   }

   public void setSpcUrl(ConsultTextType value) {
      this.spcUrl = value;
   }

   public ConsultTextType getRmaPatientUrl() {
      return this.rmaPatientUrl;
   }

   public void setRmaPatientUrl(ConsultTextType value) {
      this.rmaPatientUrl = value;
   }

   public ConsultTextType getRmaProfessionalUrl() {
      return this.rmaProfessionalUrl;
   }

   public void setRmaProfessionalUrl(ConsultTextType value) {
      this.rmaProfessionalUrl = value;
   }

   public String getParallelCircuit() {
      return this.parallelCircuit;
   }

   public void setParallelCircuit(String value) {
      this.parallelCircuit = value;
   }

   public String getParallelDistributor() {
      return this.parallelDistributor;
   }

   public void setParallelDistributor(String value) {
      this.parallelDistributor = value;
   }

   public Integer getPackMultiplier() {
      return this.packMultiplier;
   }

   public void setPackMultiplier(Integer value) {
      this.packMultiplier = value;
   }

   public PackAmountType getPackAmount() {
      return this.packAmount;
   }

   public void setPackAmount(PackAmountType value) {
      this.packAmount = value;
   }

   public String getPackDisplayValue() {
      return this.packDisplayValue;
   }

   public void setPackDisplayValue(String value) {
      this.packDisplayValue = value;
   }

   public String getAuthorisationNr() {
      return this.authorisationNr;
   }

   public void setAuthorisationNr(String value) {
      this.authorisationNr = value;
   }

   public Boolean isSingleUse() {
      return this.singleUse;
   }

   public void setSingleUse(Boolean value) {
      this.singleUse = value;
   }

   public String getSpeciallyRegulated() {
      return this.speciallyRegulated;
   }

   public void setSpeciallyRegulated(String value) {
      this.speciallyRegulated = value;
   }

   public ConsultTextType getAbbreviatedName() {
      return this.abbreviatedName;
   }

   public void setAbbreviatedName(ConsultTextType value) {
      this.abbreviatedName = value;
   }

   public ConsultTextType getPrescriptionName() {
      return this.prescriptionName;
   }

   public void setPrescriptionName(ConsultTextType value) {
      this.prescriptionName = value;
   }

   public ConsultTextType getNote() {
      return this.note;
   }

   public void setNote(ConsultTextType value) {
      this.note = value;
   }

   public ConsultTextType getPosologyNote() {
      return this.posologyNote;
   }

   public void setPosologyNote(ConsultTextType value) {
      this.posologyNote = value;
   }

   public ConsultTextType getCrmUrl() {
      return this.crmUrl;
   }

   public void setCrmUrl(ConsultTextType value) {
      this.crmUrl = value;
   }

   public BigDecimal getExFactoryPrice() {
      return this.exFactoryPrice;
   }

   public void setExFactoryPrice(BigDecimal value) {
      this.exFactoryPrice = value;
   }

   public String getReimbursementCode() {
      return this.reimbursementCode;
   }

   public void setReimbursementCode(String value) {
      this.reimbursementCode = value;
   }

   public List<AtcClassificationType> getAtcs() {
      if (this.atcs == null) {
         this.atcs = new ArrayList();
      }

      return this.atcs;
   }

   public DeliveryModusType getDeliveryModus() {
      return this.deliveryModus;
   }

   public void setDeliveryModus(DeliveryModusType value) {
      this.deliveryModus = value;
   }

   public DeliveryModusSpecificationType getDeliveryModusSpecification() {
      return this.deliveryModusSpecification;
   }

   public void setDeliveryModusSpecification(DeliveryModusSpecificationType value) {
      this.deliveryModusSpecification = value;
   }

   public List<NoGenericPrescriptionReasonType> getNoGenericPrescriptionReasons() {
      if (this.noGenericPrescriptionReasons == null) {
         this.noGenericPrescriptionReasons = new ArrayList();
      }

      return this.noGenericPrescriptionReasons;
   }

   public String getDistributorCompanyActorNr() {
      return this.distributorCompanyActorNr;
   }

   public void setDistributorCompanyActorNr(String value) {
      this.distributorCompanyActorNr = value;
   }

   public String getStatus() {
      return this.status;
   }

   public void setStatus(String value) {
      this.status = value;
   }

   public List<String> getFMDProductCodes() {
      if (this.fmdProductCodes == null) {
         this.fmdProductCodes = new ArrayList();
      }

      return this.fmdProductCodes;
   }

   public Boolean isFMDInScope() {
      return this.fmdInScope;
   }

   public void setFMDInScope(Boolean value) {
      this.fmdInScope = value;
   }

   public Boolean isAntiTemperingDevicePresent() {
      return this.antiTemperingDevicePresent;
   }

   public void setAntiTemperingDevicePresent(Boolean value) {
      this.antiTemperingDevicePresent = value;
   }

   public ConsultTextType getDHPCUrl() {
      return this.dhpcUrl;
   }

   public void setDHPCUrl(ConsultTextType value) {
      this.dhpcUrl = value;
   }

   public QuantityType getDefinedDailyDose() {
      return this.definedDailyDose;
   }

   public void setDefinedDailyDose(QuantityType value) {
      this.definedDailyDose = value;
   }

   public List<AdditionalFieldsType> getAdditionalFields() {
      if (this.additionalFields == null) {
         this.additionalFields = new ArrayList();
      }

      return this.additionalFields;
   }

   public List<ConsultAmppComponentType> getAmppComponents() {
      if (this.amppComponents == null) {
         this.amppComponents = new ArrayList();
      }

      return this.amppComponents;
   }

   public ConsultCommercializationType getCommercialization() {
      return this.commercialization;
   }

   public void setCommercialization(ConsultCommercializationType value) {
      this.commercialization = value;
   }

   public ConsultSupplyProblemType getSupplyProblem() {
      return this.supplyProblem;
   }

   public void setSupplyProblem(ConsultSupplyProblemType value) {
      this.supplyProblem = value;
   }

   public List<ConsultDerogationImportType> getDerogationImports() {
      if (this.derogationImports == null) {
         this.derogationImports = new ArrayList();
      }

      return this.derogationImports;
   }

   public List<ConsultDmppType> getDmpps() {
      if (this.dmpps == null) {
         this.dmpps = new ArrayList();
      }

      return this.dmpps;
   }

   public String getCtiExtended() {
      return this.ctiExtended;
   }

   public void setCtiExtended(String value) {
      this.ctiExtended = value;
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
