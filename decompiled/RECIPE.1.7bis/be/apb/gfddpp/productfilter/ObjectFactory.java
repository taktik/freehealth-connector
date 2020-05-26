package be.apb.gfddpp.productfilter;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

@XmlRegistry
public class ObjectFactory {
   private static final QName _AbstractRange_QNAME = new QName("", "abstractRange");
   private static final QName _MedicinesRanges_QNAME = new QName("", "medicinesRanges");
   private static final QName _PreparationsRanges_QNAME = new QName("", "preparationsRanges");

   public ProductList createProductList() {
      return new ProductList();
   }

   public RangesType createRangesType() {
      return new RangesType();
   }

   public MedicinesRanges createMedicinesRanges() {
      return new MedicinesRanges();
   }

   public PreparationsRanges createPreparationsRanges() {
      return new PreparationsRanges();
   }

   public ProductFilter createProductFilter() {
      return new ProductFilter();
   }

   public Ranges createRanges() {
      return new Ranges();
   }

   public Medicines createMedicines() {
      return new Medicines();
   }

   public Medicine createMedicine() {
      return new Medicine();
   }

   public Preparations createPreparations() {
      return new Preparations();
   }

   public Preparation createPreparation() {
      return new Preparation();
   }

   public Range createRange() {
      return new Range();
   }

   public ProductList.WhiteList createProductListWhiteList() {
      return new ProductList.WhiteList();
   }

   public ProductList.BlackList createProductListBlackList() {
      return new ProductList.BlackList();
   }

   @XmlElementDecl(
      namespace = "",
      name = "abstractRange"
   )
   public JAXBElement<RangesType> createAbstractRange(RangesType value) {
      return new JAXBElement(_AbstractRange_QNAME, RangesType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "",
      name = "medicinesRanges",
      substitutionHeadNamespace = "",
      substitutionHeadName = "abstractRange"
   )
   public JAXBElement<MedicinesRanges> createMedicinesRanges(MedicinesRanges value) {
      return new JAXBElement(_MedicinesRanges_QNAME, MedicinesRanges.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "",
      name = "preparationsRanges",
      substitutionHeadNamespace = "",
      substitutionHeadName = "abstractRange"
   )
   public JAXBElement<PreparationsRanges> createPreparationsRanges(PreparationsRanges value) {
      return new JAXBElement(_PreparationsRanges_QNAME, PreparationsRanges.class, (Class)null, value);
   }
}
