package be.apb.standards.smoa.schema.v1;

import be.apb.standards.smoa.schema.id.v1.NihiiIdType;
import be.apb.standards.smoa.schema.model.v1.DrugRelatedProblemType;
import be.apb.standards.smoa.schema.model.v1.MaxSetCareProviderType;
import be.apb.standards.smoa.schema.model.v1.MaxSetPatient;
import be.apb.standards.smoa.schema.model.v1.MaxSetProductType;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "PharmaceuticalCareEventType",
   propOrder = {"sessionDateTime", "pharmacyId", "maxPatient", "dispensedForSamePrescription", "dispensedWithoutPrescription", "pharmaceuticalCareActivities", "metaDataList"}
)
public class PharmaceuticalCareEventType extends AbstractEventType {
   @XmlElement(
      required = true
   )
   @XmlSchemaType(
      name = "dateTime"
   )
   protected XMLGregorianCalendar sessionDateTime;
   @XmlElement(
      required = true
   )
   protected NihiiIdType pharmacyId;
   @XmlElement(
      name = "max-Patient",
      namespace = "http://www.apb.be/standards/smoa/schema/model/v1",
      required = true
   )
   protected MaxSetPatient maxPatient;
   protected List<PharmaceuticalCareEventType.DispensedForSamePrescription> dispensedForSamePrescription;
   protected PharmaceuticalCareEventType.DispensedWithoutPrescription dispensedWithoutPrescription;
   protected PharmaceuticalCareEventType.PharmaceuticalCareActivities pharmaceuticalCareActivities;
   protected MetaDataListType metaDataList;

   public XMLGregorianCalendar getSessionDateTime() {
      return this.sessionDateTime;
   }

   public void setSessionDateTime(XMLGregorianCalendar value) {
      this.sessionDateTime = value;
   }

   public NihiiIdType getPharmacyId() {
      return this.pharmacyId;
   }

   public void setPharmacyId(NihiiIdType value) {
      this.pharmacyId = value;
   }

   public MaxSetPatient getMaxPatient() {
      return this.maxPatient;
   }

   public void setMaxPatient(MaxSetPatient value) {
      this.maxPatient = value;
   }

   public List<PharmaceuticalCareEventType.DispensedForSamePrescription> getDispensedForSamePrescription() {
      if (this.dispensedForSamePrescription == null) {
         this.dispensedForSamePrescription = new ArrayList();
      }

      return this.dispensedForSamePrescription;
   }

   public PharmaceuticalCareEventType.DispensedWithoutPrescription getDispensedWithoutPrescription() {
      return this.dispensedWithoutPrescription;
   }

   public void setDispensedWithoutPrescription(PharmaceuticalCareEventType.DispensedWithoutPrescription value) {
      this.dispensedWithoutPrescription = value;
   }

   public PharmaceuticalCareEventType.PharmaceuticalCareActivities getPharmaceuticalCareActivities() {
      return this.pharmaceuticalCareActivities;
   }

   public void setPharmaceuticalCareActivities(PharmaceuticalCareEventType.PharmaceuticalCareActivities value) {
      this.pharmaceuticalCareActivities = value;
   }

   public MetaDataListType getMetaDataList() {
      return this.metaDataList;
   }

   public void setMetaDataList(MetaDataListType value) {
      this.metaDataList = value;
   }

   @XmlAccessorType(XmlAccessType.FIELD)
   @XmlType(
      name = "",
      propOrder = {"drugRelatedProblem"}
   )
   public static class PharmaceuticalCareActivities {
      @XmlElement(
         required = true
      )
      protected List<DrugRelatedProblemType> drugRelatedProblem;

      public List<DrugRelatedProblemType> getDrugRelatedProblem() {
         if (this.drugRelatedProblem == null) {
            this.drugRelatedProblem = new ArrayList();
         }

         return this.drugRelatedProblem;
      }
   }

   @XmlAccessorType(XmlAccessType.FIELD)
   @XmlType(
      name = "",
      propOrder = {"product"}
   )
   public static class DispensedWithoutPrescription {
      @XmlElement(
         required = true
      )
      protected List<MaxSetProductType> product;

      public List<MaxSetProductType> getProduct() {
         if (this.product == null) {
            this.product = new ArrayList();
         }

         return this.product;
      }
   }

   @XmlAccessorType(XmlAccessType.FIELD)
   @XmlType(
      name = "",
      propOrder = {"hcparty", "product"}
   )
   public static class DispensedForSamePrescription {
      @XmlElement(
         namespace = "http://www.apb.be/standards/smoa/schema/model/v1",
         required = true
      )
      protected MaxSetCareProviderType hcparty;
      @XmlElement(
         required = true
      )
      protected List<PharmaceuticalCareEventType.DispensedForSamePrescription.Product> product;

      public MaxSetCareProviderType getHcparty() {
         return this.hcparty;
      }

      public void setHcparty(MaxSetCareProviderType value) {
         this.hcparty = value;
      }

      public List<PharmaceuticalCareEventType.DispensedForSamePrescription.Product> getProduct() {
         if (this.product == null) {
            this.product = new ArrayList();
         }

         return this.product;
      }

      @XmlAccessorType(XmlAccessType.FIELD)
      @XmlType(
         name = ""
      )
      public static class Product extends MaxSetProductType {
         @XmlAttribute(
            name = "onSubstanceName",
            required = true
         )
         protected boolean onSubstanceName;

         public boolean isOnSubstanceName() {
            return this.onSubstanceName;
         }

         public void setOnSubstanceName(boolean value) {
            this.onSubstanceName = value;
         }
      }
   }
}
