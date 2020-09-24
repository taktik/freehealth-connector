package be.apb.standards.smoa.schema.model.v1;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "RecipeLineType",
   propOrder = {"abstractMedicinalProduct", "abstractPreparation", "abstractSubstanceProduct", "packagingSize", "therapyDuration", "abstractPosology", "abstractRegimen"}
)
public class RecipeLineType extends AbstractRecipeLineType {
   @XmlElementRef(
      name = "abstract-MedicinalProduct",
      namespace = "http://www.apb.be/standards/smoa/schema/model/v1",
      type = JAXBElement.class,
      required = false
   )
   protected JAXBElement<? extends AbstractMedicinalProductType> abstractMedicinalProduct;
   @XmlElementRef(
      name = "abstract-Preparation",
      namespace = "http://www.apb.be/standards/smoa/schema/model/v1",
      type = JAXBElement.class,
      required = false
   )
   protected JAXBElement<? extends AbstractPreparationType> abstractPreparation;
   @XmlElementRef(
      name = "abstract-SubstanceProduct",
      namespace = "http://www.apb.be/standards/smoa/schema/model/v1",
      type = JAXBElement.class,
      required = false
   )
   protected JAXBElement<? extends AbstractSubstanceProductType> abstractSubstanceProduct;
   protected Integer packagingSize;
   protected String therapyDuration;
   @XmlElementRef(
      name = "abstract-Posology",
      namespace = "http://www.apb.be/standards/smoa/schema/model/v1",
      type = JAXBElement.class
   )
   protected JAXBElement<? extends AbstractPosologyType> abstractPosology;
   @XmlElementRef(
      name = "abstract-Regimen",
      namespace = "http://www.apb.be/standards/smoa/schema/model/v1",
      type = JAXBElement.class
   )
   protected JAXBElement<? extends AbstractRegimenType> abstractRegimen;

   public JAXBElement<? extends AbstractMedicinalProductType> getAbstractMedicinalProduct() {
      return this.abstractMedicinalProduct;
   }

   public void setAbstractMedicinalProduct(JAXBElement<? extends AbstractMedicinalProductType> value) {
      this.abstractMedicinalProduct = value;
   }

   public JAXBElement<? extends AbstractPreparationType> getAbstractPreparation() {
      return this.abstractPreparation;
   }

   public void setAbstractPreparation(JAXBElement<? extends AbstractPreparationType> value) {
      this.abstractPreparation = value;
   }

   public JAXBElement<? extends AbstractSubstanceProductType> getAbstractSubstanceProduct() {
      return this.abstractSubstanceProduct;
   }

   public void setAbstractSubstanceProduct(JAXBElement<? extends AbstractSubstanceProductType> value) {
      this.abstractSubstanceProduct = value;
   }

   public Integer getPackagingSize() {
      return this.packagingSize;
   }

   public void setPackagingSize(Integer value) {
      this.packagingSize = value;
   }

   public String getTherapyDuration() {
      return this.therapyDuration;
   }

   public void setTherapyDuration(String value) {
      this.therapyDuration = value;
   }

   public JAXBElement<? extends AbstractPosologyType> getAbstractPosology() {
      return this.abstractPosology;
   }

   public void setAbstractPosology(JAXBElement<? extends AbstractPosologyType> value) {
      this.abstractPosology = value;
   }

   public JAXBElement<? extends AbstractRegimenType> getAbstractRegimen() {
      return this.abstractRegimen;
   }

   public void setAbstractRegimen(JAXBElement<? extends AbstractRegimenType> value) {
      this.abstractRegimen = value;
   }
}
