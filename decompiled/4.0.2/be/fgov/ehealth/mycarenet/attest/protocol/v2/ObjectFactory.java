package be.fgov.ehealth.mycarenet.attest.protocol.v2;

import be.fgov.ehealth.mycarenet.commons.protocol.v3.SendRequestType;
import be.fgov.ehealth.mycarenet.commons.protocol.v3.SendResponseType;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

@XmlRegistry
public class ObjectFactory {
   private static final QName _SendAttestationRequest_QNAME = new QName("urn:be:fgov:ehealth:mycarenet:attest:protocol:v2", "SendAttestationRequest");
   private static final QName _SendAttestationResponse_QNAME = new QName("urn:be:fgov:ehealth:mycarenet:attest:protocol:v2", "SendAttestationResponse");
   private static final QName _CancelAttestationRequest_QNAME = new QName("urn:be:fgov:ehealth:mycarenet:attest:protocol:v2", "CancelAttestationRequest");
   private static final QName _CancelAttestationResponse_QNAME = new QName("urn:be:fgov:ehealth:mycarenet:attest:protocol:v2", "CancelAttestationResponse");

   public ObjectFactory() {
   }

   @XmlElementDecl(
      namespace = "urn:be:fgov:ehealth:mycarenet:attest:protocol:v2",
      name = "SendAttestationRequest"
   )
   public JAXBElement<SendRequestType> createSendAttestationRequest(SendRequestType value) {
      return new JAXBElement(_SendAttestationRequest_QNAME, SendRequestType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "urn:be:fgov:ehealth:mycarenet:attest:protocol:v2",
      name = "SendAttestationResponse"
   )
   public JAXBElement<SendResponseType> createSendAttestationResponse(SendResponseType value) {
      return new JAXBElement(_SendAttestationResponse_QNAME, SendResponseType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "urn:be:fgov:ehealth:mycarenet:attest:protocol:v2",
      name = "CancelAttestationRequest"
   )
   public JAXBElement<SendRequestType> createCancelAttestationRequest(SendRequestType value) {
      return new JAXBElement(_CancelAttestationRequest_QNAME, SendRequestType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "urn:be:fgov:ehealth:mycarenet:attest:protocol:v2",
      name = "CancelAttestationResponse"
   )
   public JAXBElement<SendResponseType> createCancelAttestationResponse(SendResponseType value) {
      return new JAXBElement(_CancelAttestationResponse_QNAME, SendResponseType.class, (Class)null, value);
   }
}
