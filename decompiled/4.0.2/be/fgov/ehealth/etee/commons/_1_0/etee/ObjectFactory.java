package be.fgov.ehealth.etee.commons._1_0.etee;

import javax.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {
   public ObjectFactory() {
   }

   public ApplicationErrors createApplicationErrors() {
      return new ApplicationErrors();
   }

   public EteeResponseType createEteeResponseType() {
      return new EteeResponseType();
   }

   public ErrorType createErrorType() {
      return new ErrorType();
   }

   public Status createStatus() {
      return new Status();
   }
}
