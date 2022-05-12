package be.fgov.ehealth.hubservices.protocol.v2;

import be.fgov.ehealth.hubservices.core.v2.PutTherapeuticLinkResponseType;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

@XmlRegistry
public class ObjectFactory {
   private static final QName _PutTherapeuticLinkBulkResponse_QNAME = new QName("http://www.ehealth.fgov.be/hubservices/protocol/v2", "PutTherapeuticLinkBulkResponse");
   private static final QName _PutTherapeuticLinkResponse_QNAME = new QName("http://www.ehealth.fgov.be/hubservices/protocol/v2", "PutTherapeuticLinkResponse");

   public ObjectFactory() {
   }

   @XmlElementDecl(
      namespace = "http://www.ehealth.fgov.be/hubservices/protocol/v2",
      name = "PutTherapeuticLinkBulkResponse"
   )
   public JAXBElement<PutTherapeuticLinkResponseType> createPutTherapeuticLinkBulkResponse(PutTherapeuticLinkResponseType value) {
      return new JAXBElement(_PutTherapeuticLinkBulkResponse_QNAME, PutTherapeuticLinkResponseType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "http://www.ehealth.fgov.be/hubservices/protocol/v2",
      name = "PutTherapeuticLinkResponse"
   )
   public JAXBElement<PutTherapeuticLinkResponseType> createPutTherapeuticLinkResponse(PutTherapeuticLinkResponseType value) {
      return new JAXBElement(_PutTherapeuticLinkResponse_QNAME, PutTherapeuticLinkResponseType.class, (Class)null, value);
   }
}
