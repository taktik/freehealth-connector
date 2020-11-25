package be.fgov.ehealth.dics.protocol.v4;

import be.ehealth.technicalconnector.adapter.XmlDateAdapter;
import be.fgov.ehealth.dics.core.v4.actual.common.PackAmountType;
import be.fgov.ehealth.dics.core.v4.core.QuantityType;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
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
   propOrder = {"orphan", "leafletLink", "spcLink", "rmaPatientLink", "rmaProfessionalLink", "parallelCircuit", "parallelDistributor", "packMultiplier", "packAmount", "packDisplayValue", "authorisationNr", "singleUse", "speciallyRegulated", "abbreviatedName", "prescriptionName", "note", "posologyNote", "crmLink", "exFactoryPrice", "reimbursementCode", "atcs", "deliveryModus", "deliveryModusSpecification", "definedDailyDose", "noGenericPrescriptionReasons", "distributorCompanyActorNr", "amppComponents", "commercialization", "supplyProblem", "derogationImports", "dmpps"}
)
public class ConsultAmppType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Orphan"
   )
   protected boolean orphan;
   @XmlElement(
      name = "LeafletLink"
   )
   protected ConsultTextType leafletLink;
   @XmlElement(
      name = "SpcLink"
   )
   protected ConsultTextType spcLink;
   @XmlElement(
      name = "RmaPatientLink"
   )
   protected ConsultTextType rmaPatientLink;
   @XmlElement(
      name = "RmaProfessionalLink"
   )
   protected ConsultTextType rmaProfessionalLink;
   @XmlElement(
      name = "ParallelCircuit"
   )
   protected BigInteger parallelCircuit;
   @XmlElement(
      name = "ParallelDistributor"
   )
   protected String parallelDistributor;
   @XmlElement(
      name = "PackMultiplier"
   )
   @XmlSchemaType(
      name = "positiveInteger"
   )
   protected BigInteger packMultiplier;
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
   protected BigInteger speciallyRegulated;
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
      name = "CrmLink"
   )
   protected ConsultTextType crmLink;
   @XmlElement(
      name = "ExFactoryPrice"
   )
   protected BigDecimal exFactoryPrice;
   @XmlElement(
      name = "ReimbursementCode"
   )
   protected BigInteger reimbursementCode;
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
      name = "DefinedDailyDose"
   )
   protected QuantityType definedDailyDose;
   @XmlElement(
      name = "NoGenericPrescriptionReason"
   )
   protected List<NoGenericPrescriptionReasonType> noGenericPrescriptionReasons;
   @XmlElement(
      name = "DistributorCompanyActorNr"
   )
   protected String distributorCompanyActorNr;
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

   public boolean isOrphan() {
      return this.orphan;
   }

   public void setOrphan(boolean value) {
      this.orphan = value;
   }

   public ConsultTextType getLeafletLink() {
      return this.leafletLink;
   }

   public void setLeafletLink(ConsultTextType value) {
      this.leafletLink = value;
   }

   public ConsultTextType getSpcLink() {
      return this.spcLink;
   }

   public void setSpcLink(ConsultTextType value) {
      this.spcLink = value;
   }

   public ConsultTextType getRmaPatientLink() {
      return this.rmaPatientLink;
   }

   public void setRmaPatientLink(ConsultTextType value) {
      this.rmaPatientLink = value;
   }

   public ConsultTextType getRmaProfessionalLink() {
      return this.rmaProfessionalLink;
   }

   public void setRmaProfessionalLink(ConsultTextType value) {
      this.rmaProfessionalLink = value;
   }

   public BigInteger getParallelCircuit() {
      return this.parallelCircuit;
   }

   public void setParallelCircuit(BigInteger value) {
      this.parallelCircuit = value;
   }

   public String getParallelDistributor() {
      return this.parallelDistributor;
   }

   public void setParallelDistributor(String value) {
      this.parallelDistributor = value;
   }

   public BigInteger getPackMultiplier() {
      return this.packMultiplier;
   }

   public void setPackMultiplier(BigInteger value) {
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

   public BigInteger getSpeciallyRegulated() {
      return this.speciallyRegulated;
   }

   public void setSpeciallyRegulated(BigInteger value) {
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

   public ConsultTextType getCrmLink() {
      return this.crmLink;
   }

   public void setCrmLink(ConsultTextType value) {
      this.crmLink = value;
   }

   public BigDecimal getExFactoryPrice() {
      return this.exFactoryPrice;
   }

   public void setExFactoryPrice(BigDecimal value) {
      this.exFactoryPrice = value;
   }

   public BigInteger getReimbursementCode() {
      return this.reimbursementCode;
   }

   public void setReimbursementCode(BigInteger value) {
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

   public QuantityType getDefinedDailyDose() {
      return this.definedDailyDose;
   }

   public void setDefinedDailyDose(QuantityType value) {
      this.definedDailyDose = value;
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
