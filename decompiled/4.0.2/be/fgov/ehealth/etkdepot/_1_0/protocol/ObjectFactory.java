package be.fgov.ehealth.etkdepot._1_0.protocol;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

@XmlRegistry
public class ObjectFactory {
   private static final QName _ETK_QNAME = new QName("urn:be:fgov:ehealth:etkdepot:1_0:protocol", "ETK");

   public ObjectFactory() {
   }

   public GetEtkRequest createGetEtkRequest() {
      return new GetEtkRequest();
   }

   public SearchCriteriaType createSearchCriteriaType() {
      return new SearchCriteriaType();
   }

   public GetEtkResponse createGetEtkResponse() {
      return new GetEtkResponse();
   }

   public ErrorType createErrorType() {
      return new ErrorType();
   }

   public MatchingEtk createMatchingEtk() {
      return new MatchingEtk();
   }

   public IdentifierType createIdentifierType() {
      return new IdentifierType();
   }

   @XmlElementDecl(
      namespace = "urn:be:fgov:ehealth:etkdepot:1_0:protocol",
      name = "ETK"
   )
   public JAXBElement<byte[]> createETK(byte[] value) {
      return new JAXBElement(_ETK_QNAME, byte[].class, (Class)null, (byte[])value);
   }
}
