package be.fgov.ehealth.etee.ra.revoke._1_0.protocol;

import javax.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {
   public ObjectFactory() {
   }

   public RevocableCertificatesDataRequest createRevocableCertificatesDataRequest() {
      return new RevocableCertificatesDataRequest();
   }

   public RevocableCertificatesDataResponse createRevocableCertificatesDataResponse() {
      return new RevocableCertificatesDataResponse();
   }

   public RevocableCertificateType createRevocableCertificateType() {
      return new RevocableCertificateType();
   }

   public RevokeDataRequest createRevokeDataRequest() {
      return new RevokeDataRequest();
   }

   public RevokeDataResponse createRevokeDataResponse() {
      return new RevokeDataResponse();
   }
}
