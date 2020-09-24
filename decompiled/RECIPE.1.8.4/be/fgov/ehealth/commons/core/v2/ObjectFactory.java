package be.fgov.ehealth.commons.core.v2;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

@XmlRegistry
public class ObjectFactory {
   private static final QName _Patient_QNAME = new QName("urn:be:fgov:ehealth:commons:core:v2", "Patient");
   private static final QName _HcParty_QNAME = new QName("urn:be:fgov:ehealth:commons:core:v2", "HcParty");
   private static final QName _StatusMessage_QNAME = new QName("urn:be:fgov:ehealth:commons:core:v2", "StatusMessage");

   public Status createStatus() {
      return new Status();
   }

   public StatusCode createStatusCode() {
      return new StatusCode();
   }

   public StatusDetail createStatusDetail() {
      return new StatusDetail();
   }

   public Author createAuthor() {
      return new Author();
   }

   public ActorType createActorType() {
      return new ActorType();
   }

   public Id createId() {
      return new Id();
   }

   @XmlElementDecl(
      namespace = "urn:be:fgov:ehealth:commons:core:v2",
      name = "Patient"
   )
   public JAXBElement<ActorType> createPatient(ActorType value) {
      return new JAXBElement(_Patient_QNAME, ActorType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "urn:be:fgov:ehealth:commons:core:v2",
      name = "HcParty"
   )
   public JAXBElement<ActorType> createHcParty(ActorType value) {
      return new JAXBElement(_HcParty_QNAME, ActorType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "urn:be:fgov:ehealth:commons:core:v2",
      name = "StatusMessage"
   )
   public JAXBElement<String> createStatusMessage(String value) {
      return new JAXBElement(_StatusMessage_QNAME, String.class, (Class)null, value);
   }
}
