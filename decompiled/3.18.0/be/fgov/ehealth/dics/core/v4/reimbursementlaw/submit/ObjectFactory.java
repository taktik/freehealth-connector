package be.fgov.ehealth.dics.core.v4.reimbursementlaw.submit;

import javax.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {
   public LegalBasisKeyType createLegalBasisKeyType() {
      return new LegalBasisKeyType();
   }

   public FormalInterpretationKeyType createFormalInterpretationKeyType() {
      return new FormalInterpretationKeyType();
   }

   public LegalReferenceKeyType createLegalReferenceKeyType() {
      return new LegalReferenceKeyType();
   }

   public ReimbursementConditionKeyType createReimbursementConditionKeyType() {
      return new ReimbursementConditionKeyType();
   }

   public ReimbursementTermKeyType createReimbursementTermKeyType() {
      return new ReimbursementTermKeyType();
   }

   public ParameterValueType createParameterValueType() {
      return new ParameterValueType();
   }

   public LegalTextKeyType createLegalTextKeyType() {
      return new LegalTextKeyType();
   }

   public AttachmentKeyType createAttachmentKeyType() {
      return new AttachmentKeyType();
   }
}
