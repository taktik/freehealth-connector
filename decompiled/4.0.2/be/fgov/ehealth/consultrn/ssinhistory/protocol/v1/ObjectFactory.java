package be.fgov.ehealth.consultrn.ssinhistory.protocol.v1;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

@XmlRegistry
public class ObjectFactory {
   private static final QName _ConsultCurrentSsinRequest_QNAME = new QName("urn:be:fgov:ehealth:consultrn:ssinhistory:protocol:v1", "ConsultCurrentSsinRequest");
   private static final QName _ConsultRelatedSsinsRequest_QNAME = new QName("urn:be:fgov:ehealth:consultrn:ssinhistory:protocol:v1", "ConsultRelatedSsinsRequest");
   private static final QName _ConsultCurrentSsinResponse_QNAME = new QName("urn:be:fgov:ehealth:consultrn:ssinhistory:protocol:v1", "ConsultCurrentSsinResponse");

   public ObjectFactory() {
   }

   public ConsultSsinRequestType createConsultSsinRequestType() {
      return new ConsultSsinRequestType();
   }

   public ConsultCurrentSsinResponseType createConsultCurrentSsinResponseType() {
      return new ConsultCurrentSsinResponseType();
   }

   public ConsultRelatedSsinsResponse createConsultRelatedSsinsResponse() {
      return new ConsultRelatedSsinsResponse();
   }

   @XmlElementDecl(
      namespace = "urn:be:fgov:ehealth:consultrn:ssinhistory:protocol:v1",
      name = "ConsultCurrentSsinRequest"
   )
   public JAXBElement<ConsultSsinRequestType> createConsultCurrentSsinRequest(ConsultSsinRequestType value) {
      return new JAXBElement(_ConsultCurrentSsinRequest_QNAME, ConsultSsinRequestType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "urn:be:fgov:ehealth:consultrn:ssinhistory:protocol:v1",
      name = "ConsultRelatedSsinsRequest"
   )
   public JAXBElement<ConsultSsinRequestType> createConsultRelatedSsinsRequest(ConsultSsinRequestType value) {
      return new JAXBElement(_ConsultRelatedSsinsRequest_QNAME, ConsultSsinRequestType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "urn:be:fgov:ehealth:consultrn:ssinhistory:protocol:v1",
      name = "ConsultCurrentSsinResponse"
   )
   public JAXBElement<ConsultCurrentSsinResponseType> createConsultCurrentSsinResponse(ConsultCurrentSsinResponseType value) {
      return new JAXBElement(_ConsultCurrentSsinResponse_QNAME, ConsultCurrentSsinResponseType.class, (Class)null, value);
   }
}
