package be.apb.gfddpp.common.utils;

import be.apb.standards.smoa.schema.id.v1.NihiiIdType;
import be.apb.standards.smoa.schema.model.v1.AbstractEntityType;
import be.apb.standards.smoa.schema.model.v1.MessageInformation;
import be.apb.standards.smoa.schema.model.v1.OriginatorType;
import be.apb.standards.smoa.schema.model.v1.ReferencePharmacyType;
import be.apb.standards.smoa.schema.model.v1.StatusMessageType;
import be.apb.standards.smoa.schema.model.v1.SubjectReferenceType;
import be.apb.standards.smoa.schema.model.v1.TargetType;
import be.apb.standards.smoa.schema.v1.DigitalSignatureType;
import be.apb.standards.smoa.schema.v1.DigitalSignedSmoaMessageType;
import be.apb.standards.smoa.schema.v1.EventFolderType;
import be.apb.standards.smoa.schema.v1.HeaderType;
import be.apb.standards.smoa.schema.v1.ObjectFactory;
import be.apb.standards.smoa.schema.v1.SenderType;
import be.apb.standards.smoa.schema.v1.ServiceType;
import be.apb.standards.smoa.schema.v1.SingleMessage;
import be.apb.standards.smoa.schema.v1.SmoaMessageType;
import java.util.GregorianCalendar;
import java.util.List;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;

public class SingleMessageBuilder {
   private DatatypeFactory dataTypeFactory;
   private ObjectFactory schemaObjectFactory = new ObjectFactory();
   private be.apb.standards.smoa.schema.model.v1.ObjectFactory modelObjectFactory = new be.apb.standards.smoa.schema.model.v1.ObjectFactory();

   public SingleMessageBuilder() {
      try {
         this.dataTypeFactory = DatatypeFactory.newInstance();
      } catch (DatatypeConfigurationException var2) {
         throw new RuntimeException(var2);
      }
   }

   public SingleMessage createSingleMessageUnsigned() {
      SingleMessage singleMessage = this.schemaObjectFactory.createSingleMessage();
      singleMessage.setUnsigned(this.createSmoaStructure());
      return singleMessage;
   }

   public SingleMessage createSingleMessageSigned() {
      SingleMessage singleMessage = this.schemaObjectFactory.createSingleMessage();
      DigitalSignedSmoaMessageType signedMessage = this.schemaObjectFactory.createDigitalSignedSmoaMessageType();
      signedMessage.setMessage(this.createSmoaStructure());
      signedMessage.setSignature(this.schemaObjectFactory.createDigitalSignatureType());
      singleMessage.setSigned(signedMessage);
      return singleMessage;
   }

   public SmoaMessageType createSmoaStructure() {
      SmoaMessageType message = this.schemaObjectFactory.createSmoaMessageType();
      EventFolderType eventFolderType = this.schemaObjectFactory.createEventFolderType();
      message.setAbstractFolder(this.schemaObjectFactory.createEventFolder(eventFolderType));
      EventFolderType.Events events = this.schemaObjectFactory.createEventFolderTypeEvents();
      eventFolderType.setEvents(events);
      EventFolderType.EntitySpace entities = this.schemaObjectFactory.createEventFolderTypeEntitySpace();
      eventFolderType.setEntitySpace(entities);
      HeaderType header = this.schemaObjectFactory.createHeaderType();
      SenderType sender = this.schemaObjectFactory.createSenderType();
      header.setSender(sender);
      message.setHeader(header);
      return message;
   }

   public void addEntities(List<? extends AbstractEntityType> entities, SingleMessage singleMessage) {
      (new SingleMessageWrapper(singleMessage)).getEntities().addAll(entities);
   }

   public void addEntity(AbstractEntityType entity, SingleMessage singleMessage) {
      SingleMessageWrapper smw = new SingleMessageWrapper(singleMessage);
      smw.getEntities().add(entity);
   }

   public void addPharmacistSender(SingleMessage singleMessage, String id) throws IllegalArgumentException {
      SingleMessageWrapper smw = new SingleMessageWrapper(singleMessage);
      HeaderType header = smw.getHeader();
      if (header == null) {
         SmoaMessageType message = smw.getSmoaMessage();
         if (message == null) {
            throw new IllegalArgumentException("Single Messsage doesn't containt SMOA");
         }

         header = this.schemaObjectFactory.createHeaderType();
         message.setHeader(header);
      }

      SenderType sender = this.schemaObjectFactory.createSenderType();
      ReferencePharmacyType referencePharmacyType = this.modelObjectFactory.createReferencePharmacyType();
      NihiiIdType nihiiIdType = new NihiiIdType();
      nihiiIdType.setNihiiPharmacyNumber(id);
      referencePharmacyType.setPharmacyId(nihiiIdType);
      sender.setAbstractPharmacy(this.modelObjectFactory.createRefPharmacy(referencePharmacyType));
      header.setSender(sender);
      header.setMessageCreateDate(this.dataTypeFactory.newXMLGregorianCalendar(new GregorianCalendar()));
   }

   public void addSmoaSigned(SingleMessage singleMessage, SmoaMessageType smoa) {
      if (singleMessage == null) {
         singleMessage = this.createSingleMessageSigned();
      }

      singleMessage.getSigned().setMessage(smoa);
   }

   public void addSignature(SingleMessage singleMessage, GregorianCalendar gregCal, String sign) throws DatatypeConfigurationException {
      if (singleMessage == null) {
         singleMessage = this.createSingleMessageSigned();
      }

      DigitalSignedSmoaMessageType digitalSmoaSign = singleMessage.getSigned();
      if (digitalSmoaSign == null) {
         digitalSmoaSign = this.schemaObjectFactory.createDigitalSignedSmoaMessageType();
         singleMessage.setSigned(digitalSmoaSign);
      }

      DigitalSignatureType signature = digitalSmoaSign.getSignature();
      if (signature == null) {
         signature = this.schemaObjectFactory.createDigitalSignatureType();
         digitalSmoaSign.setSignature(signature);
      }

      signature.setDateTime(DatatypeFactory.newInstance().newXMLGregorianCalendar(gregCal));
      signature.setSignature(sign);
   }

   public StatusMessageType createEmptyStatusMessage() {
      return this.modelObjectFactory.createStatusMessageType();
   }

   public StatusMessageType createStatusMessageType(SubjectReferenceType subRef, OriginatorType originator, TargetType target, MessageInformation messageInfo) {
      if (originator == null) {
         originator = this.modelObjectFactory.createOriginatorType();
         originator.setOriginatorId("");
         originator.setOriginatorType("");
         originator.setServiceType(ServiceType.UNCLASSIFIED.toString());
      }

      if (target == null) {
         target = this.modelObjectFactory.createTargetType();
         target.setTargetId("");
         target.setTargetType("");
      }

      StatusMessageType statusMessage = this.createEmptyStatusMessage();
      statusMessage.setMessageInformation(messageInfo);
      statusMessage.setSubjectReference(subRef);
      statusMessage.setOriginator(originator);
      statusMessage.setTarget(target);
      return statusMessage;
   }
}
