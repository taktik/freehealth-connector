package be.apb.standards.smoa.schema.id.v1;

import javax.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {
   public EntityIdType createEntityIdType() {
      return new EntityIdType();
   }

   public InssIdType createInssIdType() {
      return new InssIdType();
   }

   public LocalIdType createLocalIdType() {
      return new LocalIdType();
   }

   public RizivIdType createRizivIdType() {
      return new RizivIdType();
   }

   public NihiiIdType createNihiiIdType() {
      return new NihiiIdType();
   }

   public CbeIdType createCbeIdType() {
      return new CbeIdType();
   }

   public CnkIdType createCnkIdType() {
      return new CnkIdType();
   }

   public BeRegistrationIdType createBeRegistrationIdType() {
      return new BeRegistrationIdType();
   }

   public AtcDddSystemIdType createAtcDddSystemIdType() {
      return new AtcDddSystemIdType();
   }

   public RawMaterialAuthorizationNumber createRawMaterialAuthorizationNumber() {
      return new RawMaterialAuthorizationNumber();
   }

   public RawMaterialCNKNumber createRawMaterialCNKNumber() {
      return new RawMaterialCNKNumber();
   }

   public SubstanceProductId createSubstanceProductId() {
      return new SubstanceProductId();
   }

   public LocalPrescriptionIdType createLocalPrescriptionIdType() {
      return new LocalPrescriptionIdType();
   }

   public RecipeIdType createRecipeIdType() {
      return new RecipeIdType();
   }

   public AttestIdType createAttestIdType() {
      return new AttestIdType();
   }

   public MagistralCertificateIdType createMagistralCertificateIdType() {
      return new MagistralCertificateIdType();
   }
}
