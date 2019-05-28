package be.fgov.ehealth.chap4.protocol.v1;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

@XmlRegistry
public class ObjectFactory {
   private static final QName _AbstractChap4MedicalAdvisorAgreementRequest_QNAME = new QName("urn:be:fgov:ehealth:chap4:protocol:v1", "AbstractChap4MedicalAdvisorAgreementRequest");
   private static final QName _AbstractChap4MedicalAdvisorAgreementResponse_QNAME = new QName("urn:be:fgov:ehealth:chap4:protocol:v1", "AbstractChap4MedicalAdvisorAgreementResponse");

   public AbstractChap4MedicalAdvisorAgreementRequestType createAbstractChap4MedicalAdvisorAgreementRequestType() {
      return new AbstractChap4MedicalAdvisorAgreementRequestType();
   }

   public AbstractChap4MedicalAdvisorAgreementResponseType createAbstractChap4MedicalAdvisorAgreementResponseType() {
      return new AbstractChap4MedicalAdvisorAgreementResponseType();
   }

   public AskChap4MedicalAdvisorAgreementRequest createAskChap4MedicalAdvisorAgreementRequest() {
      return new AskChap4MedicalAdvisorAgreementRequest();
   }

   public AskChap4MedicalAdvisorAgreementResponse createAskChap4MedicalAdvisorAgreementResponse() {
      return new AskChap4MedicalAdvisorAgreementResponse();
   }

   public ConsultChap4MedicalAdvisorAgreementRequest createConsultChap4MedicalAdvisorAgreementRequest() {
      return new ConsultChap4MedicalAdvisorAgreementRequest();
   }

   public ConsultChap4MedicalAdvisorAgreementResponse createConsultChap4MedicalAdvisorAgreementResponse() {
      return new ConsultChap4MedicalAdvisorAgreementResponse();
   }

   public RequestType createRequestType() {
      return new RequestType();
   }

   @XmlElementDecl(
      namespace = "urn:be:fgov:ehealth:chap4:protocol:v1",
      name = "AbstractChap4MedicalAdvisorAgreementRequest"
   )
   public JAXBElement<AbstractChap4MedicalAdvisorAgreementRequestType> createAbstractChap4MedicalAdvisorAgreementRequest(AbstractChap4MedicalAdvisorAgreementRequestType value) {
      return new JAXBElement(_AbstractChap4MedicalAdvisorAgreementRequest_QNAME, AbstractChap4MedicalAdvisorAgreementRequestType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "urn:be:fgov:ehealth:chap4:protocol:v1",
      name = "AbstractChap4MedicalAdvisorAgreementResponse"
   )
   public JAXBElement<AbstractChap4MedicalAdvisorAgreementResponseType> createAbstractChap4MedicalAdvisorAgreementResponse(AbstractChap4MedicalAdvisorAgreementResponseType value) {
      return new JAXBElement(_AbstractChap4MedicalAdvisorAgreementResponse_QNAME, AbstractChap4MedicalAdvisorAgreementResponseType.class, (Class)null, value);
   }
}
