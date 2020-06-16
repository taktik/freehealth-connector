package be.apb.standards.smoa.schema.model.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "StatusMessageType",
   propOrder = {"originator", "target", "subjectReference", "messageInformation"}
)
public class StatusMessageType extends AbstractStatusMessageType {
   @XmlElement(
      required = true
   )
   protected OriginatorType originator;
   @XmlElement(
      required = true
   )
   protected TargetType target;
   protected SubjectReferenceType subjectReference;
   protected MessageInformation messageInformation;

   public OriginatorType getOriginator() {
      return this.originator;
   }

   public void setOriginator(OriginatorType value) {
      this.originator = value;
   }

   public TargetType getTarget() {
      return this.target;
   }

   public void setTarget(TargetType value) {
      this.target = value;
   }

   public SubjectReferenceType getSubjectReference() {
      return this.subjectReference;
   }

   public void setSubjectReference(SubjectReferenceType value) {
      this.subjectReference = value;
   }

   public MessageInformation getMessageInformation() {
      return this.messageInformation;
   }

   public void setMessageInformation(MessageInformation value) {
      this.messageInformation = value;
   }
}
