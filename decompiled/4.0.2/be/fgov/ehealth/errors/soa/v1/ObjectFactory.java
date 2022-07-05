package be.fgov.ehealth.errors.soa.v1;

import javax.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {
   public ObjectFactory() {
   }

   public SystemError createSystemError() {
      return new SystemError();
   }

   public SOAErrorType createSOAErrorType() {
      return new SOAErrorType();
   }

   public BusinessError createBusinessError() {
      return new BusinessError();
   }
}
