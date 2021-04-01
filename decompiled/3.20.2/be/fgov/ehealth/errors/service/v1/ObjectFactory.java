package be.fgov.ehealth.errors.service.v1;

import javax.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {
   public SystemError createSystemError() {
      return new SystemError();
   }

   public ServiceErrorType createServiceErrorType() {
      return new ServiceErrorType();
   }

   public TraceType createTraceType() {
      return new TraceType();
   }

   public BusinessError createBusinessError() {
      return new BusinessError();
   }
}
