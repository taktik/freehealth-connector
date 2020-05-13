package be.apb.standards.smoa.schema.model.v1;

import be.apb.standards.smoa.schema.id.v1.AbstractMedicinalProductIdType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "MinSetProductType",
   propOrder = {"description", "dispensation"}
)
@XmlSeeAlso({MaxSetProductType.class, HistoryProductType.class})
public class MinSetProductType {
   @XmlElement(
      required = true
   )
   protected MinSetProductType.Description description;
   @XmlElement(
      required = true
   )
   protected MinSetProductType.Dispensation dispensation;

   public MinSetProductType.Description getDescription() {
      return this.description;
   }

   public void setDescription(MinSetProductType.Description value) {
      this.description = value;
   }

   public MinSetProductType.Dispensation getDispensation() {
      return this.dispensation;
   }

   public void setDispensation(MinSetProductType.Dispensation value) {
      this.dispensation = value;
   }

   @XmlAccessorType(XmlAccessType.FIELD)
   @XmlType(
      name = "",
      propOrder = {"numberOfUnits", "administrationInstructions"}
   )
   public static class Dispensation {
      protected Integer numberOfUnits;
      protected AdministrationType administrationInstructions;

      public Integer getNumberOfUnits() {
         return this.numberOfUnits;
      }

      public void setNumberOfUnits(Integer value) {
         this.numberOfUnits = value;
      }

      public AdministrationType getAdministrationInstructions() {
         return this.administrationInstructions;
      }

      public void setAdministrationInstructions(AdministrationType value) {
         this.administrationInstructions = value;
      }
   }

   @XmlAccessorType(XmlAccessType.FIELD)
   @XmlType(
      name = "",
      propOrder = {"productCode", "magistralPreparation", "unregisteredProduct"}
   )
   public static class Description {
      protected AbstractMedicinalProductIdType productCode;
      protected MagistralPreparationType magistralPreparation;
      protected String unregisteredProduct;

      public AbstractMedicinalProductIdType getProductCode() {
         return this.productCode;
      }

      public void setProductCode(AbstractMedicinalProductIdType value) {
         this.productCode = value;
      }

      public MagistralPreparationType getMagistralPreparation() {
         return this.magistralPreparation;
      }

      public void setMagistralPreparation(MagistralPreparationType value) {
         this.magistralPreparation = value;
      }

      public String getUnregisteredProduct() {
         return this.unregisteredProduct;
      }

      public void setUnregisteredProduct(String value) {
         this.unregisteredProduct = value;
      }
   }
}
