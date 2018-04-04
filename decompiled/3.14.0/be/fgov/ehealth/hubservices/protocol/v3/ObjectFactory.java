package be.fgov.ehealth.hubservices.protocol.v3;

import be.fgov.ehealth.hubservices.core.v3.GetTransactionRequestType;
import be.fgov.ehealth.hubservices.core.v3.GetTransactionResponseType;
import be.fgov.ehealth.hubservices.core.v3.PutTransactionRequestType;
import be.fgov.ehealth.hubservices.core.v3.PutTransactionResponseType;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

@XmlRegistry
public class ObjectFactory {
   private static final QName _PutTransactionRequest_QNAME = new QName("http://www.ehealth.fgov.be/hubservices/protocol/v3", "PutTransactionRequest");
   private static final QName _PutTransactionResponse_QNAME = new QName("http://www.ehealth.fgov.be/hubservices/protocol/v3", "PutTransactionResponse");
   private static final QName _GetTransactionRequest_QNAME = new QName("http://www.ehealth.fgov.be/hubservices/protocol/v3", "GetTransactionRequest");
   private static final QName _GetTransactionResponse_QNAME = new QName("http://www.ehealth.fgov.be/hubservices/protocol/v3", "GetTransactionResponse");
   private static final QName _PutTransactionSetRequest_QNAME = new QName("http://www.ehealth.fgov.be/hubservices/protocol/v3", "PutTransactionSetRequest");
   private static final QName _PutTransactionSetResponse_QNAME = new QName("http://www.ehealth.fgov.be/hubservices/protocol/v3", "PutTransactionSetResponse");
   private static final QName _GetTransactionSetRequest_QNAME = new QName("http://www.ehealth.fgov.be/hubservices/protocol/v3", "GetTransactionSetRequest");
   private static final QName _GetTransactionSetResponse_QNAME = new QName("http://www.ehealth.fgov.be/hubservices/protocol/v3", "GetTransactionSetResponse");

   @XmlElementDecl(
      namespace = "http://www.ehealth.fgov.be/hubservices/protocol/v3",
      name = "PutTransactionRequest"
   )
   public JAXBElement<PutTransactionRequestType> createPutTransactionRequest(PutTransactionRequestType value) {
      return new JAXBElement(_PutTransactionRequest_QNAME, PutTransactionRequestType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "http://www.ehealth.fgov.be/hubservices/protocol/v3",
      name = "PutTransactionResponse"
   )
   public JAXBElement<PutTransactionResponseType> createPutTransactionResponse(PutTransactionResponseType value) {
      return new JAXBElement(_PutTransactionResponse_QNAME, PutTransactionResponseType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "http://www.ehealth.fgov.be/hubservices/protocol/v3",
      name = "GetTransactionRequest"
   )
   public JAXBElement<GetTransactionRequestType> createGetTransactionRequest(GetTransactionRequestType value) {
      return new JAXBElement(_GetTransactionRequest_QNAME, GetTransactionRequestType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "http://www.ehealth.fgov.be/hubservices/protocol/v3",
      name = "GetTransactionResponse"
   )
   public JAXBElement<GetTransactionResponseType> createGetTransactionResponse(GetTransactionResponseType value) {
      return new JAXBElement(_GetTransactionResponse_QNAME, GetTransactionResponseType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "http://www.ehealth.fgov.be/hubservices/protocol/v3",
      name = "PutTransactionSetRequest"
   )
   public JAXBElement<PutTransactionRequestType> createPutTransactionSetRequest(PutTransactionRequestType value) {
      return new JAXBElement(_PutTransactionSetRequest_QNAME, PutTransactionRequestType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "http://www.ehealth.fgov.be/hubservices/protocol/v3",
      name = "PutTransactionSetResponse"
   )
   public JAXBElement<PutTransactionResponseType> createPutTransactionSetResponse(PutTransactionResponseType value) {
      return new JAXBElement(_PutTransactionSetResponse_QNAME, PutTransactionResponseType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "http://www.ehealth.fgov.be/hubservices/protocol/v3",
      name = "GetTransactionSetRequest"
   )
   public JAXBElement<GetTransactionRequestType> createGetTransactionSetRequest(GetTransactionRequestType value) {
      return new JAXBElement(_GetTransactionSetRequest_QNAME, GetTransactionRequestType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "http://www.ehealth.fgov.be/hubservices/protocol/v3",
      name = "GetTransactionSetResponse"
   )
   public JAXBElement<GetTransactionResponseType> createGetTransactionSetResponse(GetTransactionResponseType value) {
      return new JAXBElement(_GetTransactionSetResponse_QNAME, GetTransactionResponseType.class, (Class)null, value);
   }
}
