package be.recipe.services.executor;

import javax.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {
   public PrescriptionWithSecurityToken createPrescriptionWithSecurityToken() {
      return new PrescriptionWithSecurityToken();
   }

   public ID createID() {
      return new ID();
   }

   public Properties createProperties() {
      return new Properties();
   }

   public TimestampedPrescription createTimestampedPrescription() {
      return new TimestampedPrescription();
   }

   public ArchiveStandard createArchiveStandard() {
      return new ArchiveStandard();
   }

   public ValidationWarnings createValidationWarnings() {
      return new ValidationWarnings();
   }

   public Property createProperty() {
      return new Property();
   }

   public ValidationWarning createValidationWarning() {
      return new ValidationWarning();
   }

   public RuleId createRuleId() {
      return new RuleId();
   }

   public RuleMessage createRuleMessage() {
      return new RuleMessage();
   }

   public MessageText createMessageText() {
      return new MessageText();
   }
}
