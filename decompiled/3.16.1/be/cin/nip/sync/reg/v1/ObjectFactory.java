package be.cin.nip.sync.reg.v1;

import javax.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {
   public Registrations createRegistrations() {
      return new Registrations();
   }

   public RegistrantType createRegistrantType() {
      return new RegistrantType();
   }

   public RegistrationType createRegistrationType() {
      return new RegistrationType();
   }

   public RegistrationsAnswer createRegistrationsAnswer() {
      return new RegistrationsAnswer();
   }

   public RegistrationAnswerType createRegistrationAnswerType() {
      return new RegistrationAnswerType();
   }

   public BankAccountType createBankAccountType() {
      return new BankAccountType();
   }
}
