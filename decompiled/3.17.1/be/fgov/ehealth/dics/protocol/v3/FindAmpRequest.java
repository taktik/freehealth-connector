package be.fgov.ehealth.dics.protocol.v3;

import be.ehealth.technicalconnector.adapter.XmlDateAdapter;
import be.fgov.ehealth.commons.protocol.v2.RequestType;
import be.fgov.ehealth.dics.core.v3.actual.common.DmppKeyType;
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
   name = "FindAmpRequestType",
   propOrder = {"findByProduct", "findByPackage", "findByDmpp", "findByIngredients", "findByVirtualProduct", "findByGenericPrescriptionGroup", "findByCompany", "hasComponentWiths"}
)
@XmlRootElement(
   name = "FindAmpRequest"
)
public class FindAmpRequest extends RequestType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "FindByProduct"
   )
   protected FindByActualProductType findByProduct;
   @XmlElement(
      name = "FindByPackage"
   )
   protected FindByPackageType findByPackage;
   @XmlElement(
      name = "FindByDmpp"
   )
   protected DmppKeyType findByDmpp;
   @XmlElement(
      name = "FindByIngredient"
   )
   protected List<FindByIngredientType> findByIngredients;
   @XmlElement(
      name = "FindByVirtualProduct"
   )
   protected FindByVirtualProductType findByVirtualProduct;
   @XmlElement(
      name = "FindByGenericPrescriptionGroup"
   )
   protected FindByGenericPrescriptionGroupType findByGenericPrescriptionGroup;
   @XmlElement(
      name = "FindByCompany"
   )
   protected FindByCompanyType findByCompany;
   @XmlElement(
      name = "HasComponentWith"
   )
   protected List<HasActualComponentWithType> hasComponentWiths;
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

   public FindByActualProductType getFindByProduct() {
      return this.findByProduct;
   }

   public void setFindByProduct(FindByActualProductType value) {
      this.findByProduct = value;
   }

   public FindByPackageType getFindByPackage() {
      return this.findByPackage;
   }

   public void setFindByPackage(FindByPackageType value) {
      this.findByPackage = value;
   }

   public DmppKeyType getFindByDmpp() {
      return this.findByDmpp;
   }

   public void setFindByDmpp(DmppKeyType value) {
      this.findByDmpp = value;
   }

   public List<FindByIngredientType> getFindByIngredients() {
      if (this.findByIngredients == null) {
         this.findByIngredients = new ArrayList();
      }

      return this.findByIngredients;
   }

   public FindByVirtualProductType getFindByVirtualProduct() {
      return this.findByVirtualProduct;
   }

   public void setFindByVirtualProduct(FindByVirtualProductType value) {
      this.findByVirtualProduct = value;
   }

   public FindByGenericPrescriptionGroupType getFindByGenericPrescriptionGroup() {
      return this.findByGenericPrescriptionGroup;
   }

   public void setFindByGenericPrescriptionGroup(FindByGenericPrescriptionGroupType value) {
      this.findByGenericPrescriptionGroup = value;
   }

   public FindByCompanyType getFindByCompany() {
      return this.findByCompany;
   }

   public void setFindByCompany(FindByCompanyType value) {
      this.findByCompany = value;
   }

   public List<HasActualComponentWithType> getHasComponentWiths() {
      if (this.hasComponentWiths == null) {
         this.hasComponentWiths = new ArrayList();
      }

      return this.hasComponentWiths;
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
