package be.fgov.ehealth.rn.cbsspersonservice.core.v1;

import javax.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {
   public RegisterPersonDeclarationType createRegisterPersonDeclarationType() {
      return new RegisterPersonDeclarationType();
   }

   public RegisterPersonResultType createRegisterPersonResultType() {
      return new RegisterPersonResultType();
   }

   public ExistingPersons createExistingPersons() {
      return new ExistingPersons();
   }
}
