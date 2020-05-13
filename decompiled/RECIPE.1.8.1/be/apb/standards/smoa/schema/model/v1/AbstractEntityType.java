package be.apb.standards.smoa.schema.model.v1;

import be.apb.standards.smoa.schema.id.v1.AbstractEntityIdType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "AbstractEntityType",
   propOrder = {"entityId"}
)
@XmlSeeAlso({AbstractSupplierType.class, AbstractPharmacistType.class, AbstractPosologyType.class, AbstractRegimenType.class, AbstractPatientType.class, AbstractCareProviderType.class, AbstractStatusMessageType.class, AbstractDataLocationType.class, AbstractIncomingMedicationType.class, AbstractRawMaterialType.class, AbstractSubstanceProductType.class, AbstractPreparationType.class, AbstractRecipeLineType.class, AbstractPharmacyType.class, AbstractCareServiceType.class, AbstractPersonType.class, AbstractMedicinalProductType.class, AbstractAttestType.class, AbstractDeliveredMedicationType.class, AbstractPrescriptionType.class, AbstractMedicationConsumerType.class, AbstractAddressType.class, AbstractTelecomType.class, AbstractMagistralCertificateType.class})
public abstract class AbstractEntityType {
   protected AbstractEntityIdType entityId;

   public AbstractEntityIdType getEntityId() {
      return this.entityId;
   }

   public void setEntityId(AbstractEntityIdType value) {
      this.entityId = value;
   }
}
