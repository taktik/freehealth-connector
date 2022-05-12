package be.fgov.ehealth.globalmedicalfile.protocol.v1;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

@XmlRegistry
public class ObjectFactory {
   private static final QName _ConsultGlobalMedicalFileRequest_QNAME = new QName("urn:be:fgov:ehealth:globalmedicalfile:protocol:v1", "ConsultGlobalMedicalFileRequest");
   private static final QName _ConsultGlobalMedicalFileResponse_QNAME = new QName("urn:be:fgov:ehealth:globalmedicalfile:protocol:v1", "ConsultGlobalMedicalFileResponse");
   private static final QName _NotifyGlobalMedicalFileRequest_QNAME = new QName("urn:be:fgov:ehealth:globalmedicalfile:protocol:v1", "NotifyGlobalMedicalFileRequest");
   private static final QName _NotifyGlobalMedicalFileResponse_QNAME = new QName("urn:be:fgov:ehealth:globalmedicalfile:protocol:v1", "NotifyGlobalMedicalFileResponse");

   public ObjectFactory() {
   }

   public SendRequestType createSendRequestType() {
      return new SendRequestType();
   }

   public SendResponseType createSendResponseType() {
      return new SendResponseType();
   }

   public ResponseReturnType createResponseReturnType() {
      return new ResponseReturnType();
   }

   @XmlElementDecl(
      namespace = "urn:be:fgov:ehealth:globalmedicalfile:protocol:v1",
      name = "ConsultGlobalMedicalFileRequest"
   )
   public JAXBElement<SendRequestType> createConsultGlobalMedicalFileRequest(SendRequestType value) {
      return new JAXBElement(_ConsultGlobalMedicalFileRequest_QNAME, SendRequestType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "urn:be:fgov:ehealth:globalmedicalfile:protocol:v1",
      name = "ConsultGlobalMedicalFileResponse"
   )
   public JAXBElement<SendResponseType> createConsultGlobalMedicalFileResponse(SendResponseType value) {
      return new JAXBElement(_ConsultGlobalMedicalFileResponse_QNAME, SendResponseType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "urn:be:fgov:ehealth:globalmedicalfile:protocol:v1",
      name = "NotifyGlobalMedicalFileRequest"
   )
   public JAXBElement<SendRequestType> createNotifyGlobalMedicalFileRequest(SendRequestType value) {
      return new JAXBElement(_NotifyGlobalMedicalFileRequest_QNAME, SendRequestType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "urn:be:fgov:ehealth:globalmedicalfile:protocol:v1",
      name = "NotifyGlobalMedicalFileResponse"
   )
   public JAXBElement<SendResponseType> createNotifyGlobalMedicalFileResponse(SendResponseType value) {
      return new JAXBElement(_NotifyGlobalMedicalFileResponse_QNAME, SendResponseType.class, (Class)null, value);
   }
}
