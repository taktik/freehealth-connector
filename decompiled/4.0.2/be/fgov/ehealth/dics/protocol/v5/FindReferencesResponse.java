package be.fgov.ehealth.dics.protocol.v5;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "FindReferencesResponseType",
   propOrder = {"atcClassifications", "deliveryModuses", "deliveryModusSpecifications", "deviceTypes", "packagingClosures", "packagingMaterials", "packagingTypes", "pharmaceuticalForms", "routeOfAdministrations", "substances", "noSwitchReasons", "virtualForms", "wadas", "noGenericPrescriptionReasons", "appendixes", "formCategories", "parameters", "reimbursementCriterions", "standardForms", "standardRoutes", "standardSubstances", "standardUnits", "genericReferenceTable"}
)
@XmlRootElement(
   name = "FindReferencesResponse"
)
public class FindReferencesResponse extends DicsResponseType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "AtcClassification"
   )
   protected List<AtcClassification> atcClassifications;
   @XmlElement(
      name = "DeliveryModus"
   )
   protected List<DeliveryModus> deliveryModuses;
   @XmlElement(
      name = "DeliveryModusSpecification"
   )
   protected List<DeliveryModusSpecification> deliveryModusSpecifications;
   @XmlElement(
      name = "DeviceType"
   )
   protected List<DeviceType> deviceTypes;
   @XmlElement(
      name = "PackagingClosure"
   )
   protected List<PackagingClosure> packagingClosures;
   @XmlElement(
      name = "PackagingMaterial"
   )
   protected List<PackagingMaterial> packagingMaterials;
   @XmlElement(
      name = "PackagingType"
   )
   protected List<PackagingType> packagingTypes;
   @XmlElement(
      name = "PharmaceuticalForm"
   )
   protected List<PharmaceuticalForm> pharmaceuticalForms;
   @XmlElement(
      name = "RouteOfAdministration"
   )
   protected List<RouteOfAdministration> routeOfAdministrations;
   @XmlElement(
      name = "Substance"
   )
   protected List<Substance> substances;
   @XmlElement(
      name = "NoSwitchReason"
   )
   protected List<NoSwitchReason> noSwitchReasons;
   @XmlElement(
      name = "VirtualForm"
   )
   protected List<VirtualForm> virtualForms;
   @XmlElement(
      name = "Wada"
   )
   protected List<Wada> wadas;
   @XmlElement(
      name = "NoGenericPrescriptionReason"
   )
   protected List<NoGenericPrescriptionReason> noGenericPrescriptionReasons;
   @XmlElement(
      name = "Appendix"
   )
   protected List<Appendix> appendixes;
   @XmlElement(
      name = "FormCategory"
   )
   protected List<FormCategory> formCategories;
   @XmlElement(
      name = "Parameter"
   )
   protected List<Parameter> parameters;
   @XmlElement(
      name = "ReimbursementCriterion"
   )
   protected List<ReimbursementCriterion> reimbursementCriterions;
   @XmlElement(
      name = "StandardForm"
   )
   protected List<ConsultStandardFormAndPhFrmAndVtlFrmType> standardForms;
   @XmlElement(
      name = "StandardRoute"
   )
   protected List<ConsultStandardRouteAndRouteOfAdministrationType> standardRoutes;
   @XmlElement(
      name = "StandardSubstance"
   )
   protected List<ConsultStandardSubstanceAndSubstanceType> standardSubstances;
   @XmlElement(
      name = "StandardUnit"
   )
   protected List<StandardUnitFamhpType> standardUnits;
   @XmlElement(
      name = "GenericReferenceTable"
   )
   protected GenericReferenceTableType genericReferenceTable;

   public FindReferencesResponse() {
   }

   public List<AtcClassification> getAtcClassifications() {
      if (this.atcClassifications == null) {
         this.atcClassifications = new ArrayList();
      }

      return this.atcClassifications;
   }

   public List<DeliveryModus> getDeliveryModuses() {
      if (this.deliveryModuses == null) {
         this.deliveryModuses = new ArrayList();
      }

      return this.deliveryModuses;
   }

   public List<DeliveryModusSpecification> getDeliveryModusSpecifications() {
      if (this.deliveryModusSpecifications == null) {
         this.deliveryModusSpecifications = new ArrayList();
      }

      return this.deliveryModusSpecifications;
   }

   public List<DeviceType> getDeviceTypes() {
      if (this.deviceTypes == null) {
         this.deviceTypes = new ArrayList();
      }

      return this.deviceTypes;
   }

   public List<PackagingClosure> getPackagingClosures() {
      if (this.packagingClosures == null) {
         this.packagingClosures = new ArrayList();
      }

      return this.packagingClosures;
   }

   public List<PackagingMaterial> getPackagingMaterials() {
      if (this.packagingMaterials == null) {
         this.packagingMaterials = new ArrayList();
      }

      return this.packagingMaterials;
   }

   public List<PackagingType> getPackagingTypes() {
      if (this.packagingTypes == null) {
         this.packagingTypes = new ArrayList();
      }

      return this.packagingTypes;
   }

   public List<PharmaceuticalForm> getPharmaceuticalForms() {
      if (this.pharmaceuticalForms == null) {
         this.pharmaceuticalForms = new ArrayList();
      }

      return this.pharmaceuticalForms;
   }

   public List<RouteOfAdministration> getRouteOfAdministrations() {
      if (this.routeOfAdministrations == null) {
         this.routeOfAdministrations = new ArrayList();
      }

      return this.routeOfAdministrations;
   }

   public List<Substance> getSubstances() {
      if (this.substances == null) {
         this.substances = new ArrayList();
      }

      return this.substances;
   }

   public List<NoSwitchReason> getNoSwitchReasons() {
      if (this.noSwitchReasons == null) {
         this.noSwitchReasons = new ArrayList();
      }

      return this.noSwitchReasons;
   }

   public List<VirtualForm> getVirtualForms() {
      if (this.virtualForms == null) {
         this.virtualForms = new ArrayList();
      }

      return this.virtualForms;
   }

   public List<Wada> getWadas() {
      if (this.wadas == null) {
         this.wadas = new ArrayList();
      }

      return this.wadas;
   }

   public List<NoGenericPrescriptionReason> getNoGenericPrescriptionReasons() {
      if (this.noGenericPrescriptionReasons == null) {
         this.noGenericPrescriptionReasons = new ArrayList();
      }

      return this.noGenericPrescriptionReasons;
   }

   public List<Appendix> getAppendixes() {
      if (this.appendixes == null) {
         this.appendixes = new ArrayList();
      }

      return this.appendixes;
   }

   public List<FormCategory> getFormCategories() {
      if (this.formCategories == null) {
         this.formCategories = new ArrayList();
      }

      return this.formCategories;
   }

   public List<Parameter> getParameters() {
      if (this.parameters == null) {
         this.parameters = new ArrayList();
      }

      return this.parameters;
   }

   public List<ReimbursementCriterion> getReimbursementCriterions() {
      if (this.reimbursementCriterions == null) {
         this.reimbursementCriterions = new ArrayList();
      }

      return this.reimbursementCriterions;
   }

   public List<ConsultStandardFormAndPhFrmAndVtlFrmType> getStandardForms() {
      if (this.standardForms == null) {
         this.standardForms = new ArrayList();
      }

      return this.standardForms;
   }

   public List<ConsultStandardRouteAndRouteOfAdministrationType> getStandardRoutes() {
      if (this.standardRoutes == null) {
         this.standardRoutes = new ArrayList();
      }

      return this.standardRoutes;
   }

   public List<ConsultStandardSubstanceAndSubstanceType> getStandardSubstances() {
      if (this.standardSubstances == null) {
         this.standardSubstances = new ArrayList();
      }

      return this.standardSubstances;
   }

   public List<StandardUnitFamhpType> getStandardUnits() {
      if (this.standardUnits == null) {
         this.standardUnits = new ArrayList();
      }

      return this.standardUnits;
   }

   public GenericReferenceTableType getGenericReferenceTable() {
      return this.genericReferenceTable;
   }

   public void setGenericReferenceTable(GenericReferenceTableType value) {
      this.genericReferenceTable = value;
   }
}
