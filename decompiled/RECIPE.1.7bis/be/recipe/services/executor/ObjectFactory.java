package be.recipe.services.executor;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;
import org.w3._2000._09.xmldsig_.SignatureType;

@XmlRegistry
public class ObjectFactory {
   private static final QName _TimestampedPrescriptionSignature_QNAME = new QName("", "Signature");

   public PrescriptionWithSecurityToken createPrescriptionWithSecurityToken() {
      return new PrescriptionWithSecurityToken();
   }

   public ID createID() {
      return new ID();
   }

   public TimestampedPrescription createTimestampedPrescription() {
      return new TimestampedPrescription();
   }

   public ArchiveStandard createArchiveStandard() {
      return new ArchiveStandard();
   }

   public Properties createProperties() {
      return new Properties();
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

   @XmlElementDecl(
      namespace = "",
      name = "Signature",
      scope = TimestampedPrescription.class
   )
   public JAXBElement<SignatureType> createTimestampedPrescriptionSignature(SignatureType value) {
      return new JAXBElement(_TimestampedPrescriptionSignature_QNAME, SignatureType.class, TimestampedPrescription.class, value);
   }
}
