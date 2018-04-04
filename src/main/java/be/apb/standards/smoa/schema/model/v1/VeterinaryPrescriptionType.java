package be.apb.standards.smoa.schema.model.v1;

import be.apb.standards.smoa.schema.id.v1.AbstractPrescriptionIdType;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "VeterinaryPrescriptionType",
   propOrder = {"id", "responsible", "abstractCareProvider", "orderNumber", "recipeLines", "kmehrPrescription"}
)
public class VeterinaryPrescriptionType extends AbstractVeterinaryPrescriptionType {
   @XmlElement(
      required = true
   )
   protected AbstractPrescriptionIdType id;
   @XmlElement(
      required = true
   )
   protected Responsible responsible;
   @XmlElementRef(
      name = "abstract-CareProvider",
      namespace = "http://www.apb.be/standards/smoa/schema/model/v1",
      type = JAXBElement.class
   )
   protected JAXBElement<? extends AbstractCareProviderType> abstractCareProvider;
   @XmlElement(
      required = true
   )
   protected String orderNumber;
   protected RecipeLinesComplexType recipeLines;
   protected KmehrPrescriptionType kmehrPrescription;

   public AbstractPrescriptionIdType getId() {
      return this.id;
   }

   public void setId(AbstractPrescriptionIdType value) {
      this.id = value;
   }

   public Responsible getResponsible() {
      return this.responsible;
   }

   public void setResponsible(Responsible value) {
      this.responsible = value;
   }

   public JAXBElement<? extends AbstractCareProviderType> getAbstractCareProvider() {
      return this.abstractCareProvider;
   }

   public void setAbstractCareProvider(JAXBElement<? extends AbstractCareProviderType> value) {
      this.abstractCareProvider = value;
   }

   public String getOrderNumber() {
      return this.orderNumber;
   }

   public void setOrderNumber(String value) {
      this.orderNumber = value;
   }

   public RecipeLinesComplexType getRecipeLines() {
      return this.recipeLines;
   }

   public void setRecipeLines(RecipeLinesComplexType value) {
      this.recipeLines = value;
   }

   public KmehrPrescriptionType getKmehrPrescription() {
      return this.kmehrPrescription;
   }

   public void setKmehrPrescription(KmehrPrescriptionType value) {
      this.kmehrPrescription = value;
   }

   @XmlAccessorType(XmlAccessType.FIELD)
   @XmlType(
      name = "",
      propOrder = {"abstractPerson"}
   )
   public static class Responsible {
      @XmlElementRef(
         name = "abstract-Person",
         namespace = "http://www.apb.be/standards/smoa/schema/model/v1",
         type = JAXBElement.class
      )
      protected JAXBElement<? extends AbstractPersonType> abstractPerson;

      public JAXBElement<? extends AbstractPersonType> getAbstractPerson() {
         return this.abstractPerson;
      }

      public void setAbstractPerson(JAXBElement<? extends AbstractPersonType> value) {
         this.abstractPerson = value;
      }
   }
}
