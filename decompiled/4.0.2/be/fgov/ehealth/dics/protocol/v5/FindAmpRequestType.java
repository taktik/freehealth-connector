package be.fgov.ehealth.dics.protocol.v5;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "FindAmpRequestType",
   propOrder = {"findByProduct", "findByPackage", "findByDmpp", "findByIngredients", "findByVirtualProduct", "findByGenericPrescriptionGroup", "findByCompany", "hasComponentWiths"}
)
public class FindAmpRequestType extends DicsRequestType implements Serializable {
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
   protected FindByDmppType findByDmpp;
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

   public FindAmpRequestType() {
   }

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

   public FindByDmppType getFindByDmpp() {
      return this.findByDmpp;
   }

   public void setFindByDmpp(FindByDmppType value) {
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
}
