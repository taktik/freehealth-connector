package be.apb.gfddpp.common.utils;

import be.apb.gfddpp.common.exceptions.GFDDPPException;
import be.apb.gfddpp.domain.CareServiceIdType;
import be.apb.standards.smoa.schema.model.v1.MessageInformation;
import be.apb.standards.smoa.schema.model.v1.ObjectFactory;
import be.apb.standards.smoa.schema.model.v1.OriginatorType;
import be.apb.standards.smoa.schema.model.v1.StatusMessageType;
import be.apb.standards.smoa.schema.model.v1.SubjectReferenceType;
import be.apb.standards.smoa.schema.model.v1.TargetType;
import be.apb.standards.smoa.schema.v1.MessageType;
import be.apb.standards.smoa.schema.v1.ServiceType;
import be.apb.standards.smoa.schema.v1.TextType;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang.StringUtils;

public class StatusMessageFactory {
   private ObjectFactory modelObjectFactory = new ObjectFactory();
   private be.apb.standards.smoa.schema.v1.ObjectFactory schemaObjectFactory = new be.apb.standards.smoa.schema.v1.ObjectFactory();

   public StatusMessageType createStatusMessage(OriginatorType originator, TargetType target, SubjectReferenceType subjectRef, MessageInformation messageInfo) {
      StatusMessageType statusMessage = this.modelObjectFactory.createStatusMessageType();
      statusMessage.setOriginator(originator);
      statusMessage.setTarget(target);
      statusMessage.setSubjectReference(subjectRef);
      statusMessage.setMessageInformation(messageInfo);
      return statusMessage;
   }

   public OriginatorType createOriginator(CareServiceIdType type, String id, ServiceType service) {
      OriginatorType originator = this.modelObjectFactory.createOriginatorType();
      originator.setOriginatorId(id);
      originator.setOriginatorType(type.toString());
      originator.setServiceType(service.toString());
      return originator;
   }

   public OriginatorType createOriginator(CareServiceIdType type, String id, String service) {
      OriginatorType originator = this.modelObjectFactory.createOriginatorType();
      originator.setOriginatorId(id);
      originator.setOriginatorType(type.toString());
      originator.setServiceType(service);
      return originator;
   }

   public TargetType createTarget(String type, String id) {
      TargetType target = this.modelObjectFactory.createTargetType();
      target.setTargetId(id);
      target.setTargetType(type);
      return target;
   }

   public SubjectReferenceType createSubjectReference(String messageID, String sessionID, String dispensationID) {
      SubjectReferenceType subjectRef = this.modelObjectFactory.createSubjectReferenceType();
      subjectRef.setMessageGUID(messageID);
      subjectRef.setSessionGUID(sessionID);
      subjectRef.setDispensationGUID(dispensationID);
      return subjectRef;
   }

   public MessageInformation createMessageInformation(MessageType messageType, String messageSubType, List<TextType> messageTexts, String messageDetails) {
      MessageInformation messageInfo = this.modelObjectFactory.createMessageInformation();

      try {
         messageInfo.setDateTime(Utils.transformToXMLGregorianCalendar(new Date()));
      } catch (GFDDPPException var7) {
         var7.printStackTrace();
      }

      messageInfo.setMessageType(messageType);
      messageInfo.setMessageSubType(messageSubType);
      messageInfo.getMessageText().addAll(messageTexts);
      if (!StringUtils.isEmpty(messageDetails)) {
         messageInfo.setMessageDetails(messageDetails);
      }

      return messageInfo;
   }

   public TextType createTextType(String language, String value) {
      TextType text = this.schemaObjectFactory.createTextType();
      text.setLanguage(language);
      text.setValue(value);
      return text;
   }
}
