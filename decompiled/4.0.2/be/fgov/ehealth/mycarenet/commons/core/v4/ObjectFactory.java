package be.fgov.ehealth.mycarenet.commons.core.v4;

import javax.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {
   public ObjectFactory() {
   }

   public CommonInputType createCommonInputType() {
      return new CommonInputType();
   }

   public RequestType createRequestType() {
      return new RequestType();
   }

   public OriginType createOriginType() {
      return new OriginType();
   }

   public ValueRefString createValueRefString() {
      return new ValueRefString();
   }

   public CareProviderType createCareProviderType() {
      return new CareProviderType();
   }

   public IdType createIdType() {
      return new IdType();
   }

   public NihiiType createNihiiType() {
      return new NihiiType();
   }

   public PackageType createPackageType() {
      return new PackageType();
   }

   public LicenseType createLicenseType() {
      return new LicenseType();
   }

   public ActorType createActorType() {
      return new ActorType();
   }

   public PartyType createPartyType() {
      return new PartyType();
   }

   public CommonOutputType createCommonOutputType() {
      return new CommonOutputType();
   }

   public RoutingType createRoutingType() {
      return new RoutingType();
   }

   public SubjectType createSubjectType() {
      return new SubjectType();
   }

   public CareReceiverIdType createCareReceiverIdType() {
      return new CareReceiverIdType();
   }

   public PeriodType createPeriodType() {
      return new PeriodType();
   }

   public BlobType createBlobType() {
      return new BlobType();
   }

   public ReferenceType createReferenceType() {
      return new ReferenceType();
   }

   public AttributeType createAttributeType() {
      return new AttributeType();
   }

   public Identifier createIdentifier() {
      return new Identifier();
   }
}
