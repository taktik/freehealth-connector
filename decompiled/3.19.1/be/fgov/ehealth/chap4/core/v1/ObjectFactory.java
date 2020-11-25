package be.fgov.ehealth.chap4.core.v1;

import javax.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {
   public ValueRefString createValueRefString() {
      return new ValueRefString();
   }

   public CommonOutputType createCommonOutputType() {
      return new CommonOutputType();
   }

   public RecordCommonOutputType createRecordCommonOutputType() {
      return new RecordCommonOutputType();
   }

   public CommonInputType createCommonInputType() {
      return new CommonInputType();
   }

   public RecordCommonInputType createRecordCommonInputType() {
      return new RecordCommonInputType();
   }

   public RequestType createRequestType() {
      return new RequestType();
   }

   public OriginType createOriginType() {
      return new OriginType();
   }

   public PackageType createPackageType() {
      return new PackageType();
   }

   public LicenseType createLicenseType() {
      return new LicenseType();
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

   public CareReceiverIdType createCareReceiverIdType() {
      return new CareReceiverIdType();
   }

   public SecuredContentType createSecuredContentType() {
      return new SecuredContentType();
   }

   public FaultType createFaultType() {
      return new FaultType();
   }

   public DetailType createDetailType() {
      return new DetailType();
   }
}
