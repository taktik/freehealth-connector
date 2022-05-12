package be.fgov.ehealth.genericinsurability.protocol.v1;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

@XmlRegistry
public class ObjectFactory {
   private static final QName _GetInsurabilityRequest_QNAME = new QName("urn:be:fgov:ehealth:genericinsurability:protocol:v1", "GetInsurabilityRequest");
   private static final QName _GetInsurabilityAsFlatRequest_QNAME = new QName("urn:be:fgov:ehealth:genericinsurability:protocol:v1", "GetInsurabilityAsFlatRequest");

   public ObjectFactory() {
   }

   public GetInsurabilityAsXmlOrFlatRequestType createGetInsurabilityAsXmlOrFlatRequestType() {
      return new GetInsurabilityAsXmlOrFlatRequestType();
   }

   public GetInsurabilityResponse createGetInsurabilityResponse() {
      return new GetInsurabilityResponse();
   }

   public GetInsurabilityAsFlatResponse createGetInsurabilityAsFlatResponse() {
      return new GetInsurabilityAsFlatResponse();
   }

   @XmlElementDecl(
      namespace = "urn:be:fgov:ehealth:genericinsurability:protocol:v1",
      name = "GetInsurabilityRequest"
   )
   public JAXBElement<GetInsurabilityAsXmlOrFlatRequestType> createGetInsurabilityRequest(GetInsurabilityAsXmlOrFlatRequestType value) {
      return new JAXBElement(_GetInsurabilityRequest_QNAME, GetInsurabilityAsXmlOrFlatRequestType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "urn:be:fgov:ehealth:genericinsurability:protocol:v1",
      name = "GetInsurabilityAsFlatRequest"
   )
   public JAXBElement<GetInsurabilityAsXmlOrFlatRequestType> createGetInsurabilityAsFlatRequest(GetInsurabilityAsXmlOrFlatRequestType value) {
      return new JAXBElement(_GetInsurabilityAsFlatRequest_QNAME, GetInsurabilityAsXmlOrFlatRequestType.class, (Class)null, value);
   }
}
