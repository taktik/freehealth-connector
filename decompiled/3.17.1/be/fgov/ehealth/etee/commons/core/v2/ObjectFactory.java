package be.fgov.ehealth.etee.commons.core.v2;

import javax.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {
   public EteeStatusDetail createEteeStatusDetail() {
      return new EteeStatusDetail();
   }

   public EteeErrorType createEteeErrorType() {
      return new EteeErrorType();
   }

   public EteeErrorParameterType createEteeErrorParameterType() {
      return new EteeErrorParameterType();
   }
}
