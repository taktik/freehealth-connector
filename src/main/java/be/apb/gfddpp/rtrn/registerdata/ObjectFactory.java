package be.apb.gfddpp.rtrn.registerdata;

import javax.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {
   public Events createEvents() {
      return new Events();
   }

   public PharmaceuticalCareEventType createPharmaceuticalCareEventType() {
      return new PharmaceuticalCareEventType();
   }

   public ArchivePrescriptionEventType createArchivePrescriptionEventType() {
      return new ArchivePrescriptionEventType();
   }

   public ArchivePrescriptionCommentEventType createArchivePrescriptionCommentEventType() {
      return new ArchivePrescriptionCommentEventType();
   }

   public BvacEventType createBvacEventType() {
      return new BvacEventType();
   }

   public BvacDocument createBvacDocument() {
      return new BvacDocument();
   }

   public Dispensation createDispensation() {
      return new Dispensation();
   }

   public Product createProduct() {
      return new Product();
   }

   public Magistral createMagistral() {
      return new Magistral();
   }
}
