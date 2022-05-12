package be.fgov.ehealth.dics.protocol.v4;

import be.ehealth.technicalconnector.adapter.XmlDateAdapter;
import be.fgov.ehealth.commons.protocol.v2.RequestType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.joda.time.DateTime;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "FindVmpRequestType",
   propOrder = {"findByGenericPrescriptionGroup", "findByTherapeuticMoiety", "findByProduct", "findByIngredients", "hasComponentWiths", "hasWadaClassification"}
)
@XmlRootElement(
   name = "FindVmpRequest"
)
public class FindVmpRequest extends RequestType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "FindByGenericPrescriptionGroup"
   )
   protected FindByGenericPrescriptionGroupType findByGenericPrescriptionGroup;
   @XmlElement(
      name = "FindByTherapeuticMoiety"
   )
   protected FindByTherapeuticMoietyType findByTherapeuticMoiety;
   @XmlElement(
      name = "FindByProduct"
   )
   protected FindByVirtualProductType findByProduct;
   @XmlElement(
      name = "FindByIngredient"
   )
   protected List<FindByIngredientType> findByIngredients;
   @XmlElement(
      name = "HasComponentWith"
   )
   protected List<HasVirtualComponentWithType> hasComponentWiths;
   @XmlElement(
      name = "HasWadaClassification"
   )
   protected HasWadaClassificationType hasWadaClassification;
   @XmlAttribute(
      name = "lang",
      namespace = "http://www.w3.org/XML/1998/namespace",
      required = true
   )
   protected String lang;
   @XmlAttribute(
      name = "SearchDate"
   )
   @XmlJavaTypeAdapter(XmlDateAdapter.class)
   @XmlSchemaType(
      name = "date"
   )
   protected DateTime searchDate;

   public FindVmpRequest() {
   }

   public FindByGenericPrescriptionGroupType getFindByGenericPrescriptionGroup() {
      return this.findByGenericPrescriptionGroup;
   }

   public void setFindByGenericPrescriptionGroup(FindByGenericPrescriptionGroupType value) {
      this.findByGenericPrescriptionGroup = value;
   }

   public FindByTherapeuticMoietyType getFindByTherapeuticMoiety() {
      return this.findByTherapeuticMoiety;
   }

   public void setFindByTherapeuticMoiety(FindByTherapeuticMoietyType value) {
      this.findByTherapeuticMoiety = value;
   }

   public FindByVirtualProductType getFindByProduct() {
      return this.findByProduct;
   }

   public void setFindByProduct(FindByVirtualProductType value) {
      this.findByProduct = value;
   }

   public List<FindByIngredientType> getFindByIngredients() {
      if (this.findByIngredients == null) {
         this.findByIngredients = new ArrayList();
      }

      return this.findByIngredients;
   }

   public List<HasVirtualComponentWithType> getHasComponentWiths() {
      if (this.hasComponentWiths == null) {
         this.hasComponentWiths = new ArrayList();
      }

      return this.hasComponentWiths;
   }

   public HasWadaClassificationType getHasWadaClassification() {
      return this.hasWadaClassification;
   }

   public void setHasWadaClassification(HasWadaClassificationType value) {
      this.hasWadaClassification = value;
   }

   public String getLang() {
      return this.lang;
   }

   public void setLang(String value) {
      this.lang = value;
   }

   public DateTime getSearchDate() {
      return this.searchDate;
   }

   public void setSearchDate(DateTime value) {
      this.searchDate = value;
   }
}
