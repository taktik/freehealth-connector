package be.apb.standards.smoa.schema.model.v1;

import be.apb.standards.smoa.schema.v1.PharmaceuticalCareEventType;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "MaxSetProductType",
   propOrder = {"pharmaceuticalCare", "localReference", "dispensationGUID", "motivation"}
)
@XmlSeeAlso({PharmaceuticalCareEventType.DispensedForSamePrescription.Product.class})
public class MaxSetProductType extends MinSetProductType {
   protected List<PharmaceuticalCare> pharmaceuticalCare;
   protected String localReference;
   @XmlElement(
      required = true
   )
   @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
   @XmlSchemaType(
      name = "token"
   )
   protected String dispensationGUID;
   protected Motivation motivation;

   public List<PharmaceuticalCare> getPharmaceuticalCare() {
      if (this.pharmaceuticalCare == null) {
         this.pharmaceuticalCare = new ArrayList();
      }

      return this.pharmaceuticalCare;
   }

   public String getLocalReference() {
      return this.localReference;
   }

   public void setLocalReference(String value) {
      this.localReference = value;
   }

   public String getDispensationGUID() {
      return this.dispensationGUID;
   }

   public void setDispensationGUID(String value) {
      this.dispensationGUID = value;
   }

   public Motivation getMotivation() {
      return this.motivation;
   }

   public void setMotivation(Motivation value) {
      this.motivation = value;
   }

   @XmlAccessorType(XmlAccessType.FIELD)
   @XmlType(
      name = "",
      propOrder = {"productInformation", "drugRelatedProblem"}
   )
   public static class PharmaceuticalCare {
      protected List<ProductInformation> productInformation;
      protected List<DrugRelatedProblemType> drugRelatedProblem;

      public List<ProductInformation> getProductInformation() {
         if (this.productInformation == null) {
            this.productInformation = new ArrayList();
         }

         return this.productInformation;
      }

      public List<DrugRelatedProblemType> getDrugRelatedProblem() {
         if (this.drugRelatedProblem == null) {
            this.drugRelatedProblem = new ArrayList();
         }

         return this.drugRelatedProblem;
      }

      @XmlAccessorType(XmlAccessType.FIELD)
      @XmlType(
         name = "",
         propOrder = {"infoFirstDispensation", "brochure"}
      )
      public static class ProductInformation {
         protected boolean infoFirstDispensation;
         protected String brochure;

         public boolean isInfoFirstDispensation() {
            return this.infoFirstDispensation;
         }

         public void setInfoFirstDispensation(boolean value) {
            this.infoFirstDispensation = value;
         }

         public String getBrochure() {
            return this.brochure;
         }

         public void setBrochure(String value) {
            this.brochure = value;
         }
      }
   }

   @XmlAccessorType(XmlAccessType.FIELD)
   @XmlType(
      name = "",
      propOrder = {"type", "freeText"}
   )
   public static class Motivation {
      @XmlElement(
         required = true
      )
      protected String type;
      protected String freeText;

      public String getType() {
         return this.type;
      }

      public void setType(String value) {
         this.type = value;
      }

      public String getFreeText() {
         return this.freeText;
      }

      public void setFreeText(String value) {
         this.freeText = value;
      }
   }
}
