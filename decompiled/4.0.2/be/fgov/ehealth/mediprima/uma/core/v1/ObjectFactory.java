package be.fgov.ehealth.mediprima.uma.core.v1;

import javax.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {
   public ObjectFactory() {
   }

   public RegistryStatus createRegistryStatus() {
      return new RegistryStatus();
   }

   public ErrorType createErrorType() {
      return new ErrorType();
   }

   public AttestationType createAttestationType() {
      return new AttestationType();
   }

   public CriteriaType createCriteriaType() {
      return new CriteriaType();
   }

   public NameType createNameType() {
      return new NameType();
   }

   public PeriodType createPeriodType() {
      return new PeriodType();
   }
}
