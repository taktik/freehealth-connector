package be.cin.mycarenet.esb.common.v2;

import javax.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {
   public ObjectFactory() {
   }

   public CommonInput createCommonInput() {
      return new CommonInput();
   }

   public RequestType createRequestType() {
      return new RequestType();
   }

   public OrigineType createOrigineType() {
      return new OrigineType();
   }

   public ValueRefString createValueRefString() {
      return new ValueRefString();
   }

   public RecordCommonInputType createRecordCommonInputType() {
      return new RecordCommonInputType();
   }

   public CommonOutputType createCommonOutputType() {
      return new CommonOutputType();
   }

   public RecordCommonOutputType createRecordCommonOutputType() {
      return new RecordCommonOutputType();
   }

   public RoutingType createRoutingType() {
      return new RoutingType();
   }

   public PackageType createPackageType() {
      return new PackageType();
   }

   public LicenseType createLicenseType() {
      return new LicenseType();
   }

   public PartyType createPartyType() {
      return new PartyType();
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
}
