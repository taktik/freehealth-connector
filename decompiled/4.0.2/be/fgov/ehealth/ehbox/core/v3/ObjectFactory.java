package be.fgov.ehealth.ehbox.core.v3;

import javax.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {
   public ObjectFactory() {
   }

   public BoxIdType createBoxIdType() {
      return new BoxIdType();
   }

   public SenderType createSenderType() {
      return new SenderType();
   }

   public MessageInfoType createMessageInfoType() {
      return new MessageInfoType();
   }

   public FreeInformationsType createFreeInformationsType() {
      return new FreeInformationsType();
   }

   public ErrorType createErrorType() {
      return new ErrorType();
   }

   public AcknowledgmentType createAcknowledgmentType() {
      return new AcknowledgmentType();
   }

   public EhboxIdentifierType createEhboxIdentifierType() {
      return new EhboxIdentifierType();
   }

   public MetaType createMetaType() {
      return new MetaType();
   }

   public CustomMetaType createCustomMetaType() {
      return new CustomMetaType();
   }

   public ContentInfoType createContentInfoType() {
      return new ContentInfoType();
   }

   public ContentSpecificationType createContentSpecificationType() {
      return new ContentSpecificationType();
   }

   public MandateType createMandateType() {
      return new MandateType();
   }

   public User createUser() {
      return new User();
   }

   public EncryptableOldFreeInformation createEncryptableOldFreeInformation() {
      return new EncryptableOldFreeInformation();
   }

   public Table createTable() {
      return new Table();
   }

   public Row createRow() {
      return new Row();
   }
}
