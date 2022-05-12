package be.fgov.ehealth.rn.cbsspersonlegaldata.v1;

import javax.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {
   public ObjectFactory() {
   }

   public CbssPersonResponseType createCbssPersonResponseType() {
      return new CbssPersonResponseType();
   }

   public CbssUpdatePersonResponseType createCbssUpdatePersonResponseType() {
      return new CbssUpdatePersonResponseType();
   }

   public CbssPersonRequestType createCbssPersonRequestType() {
      return new CbssPersonRequestType();
   }
}
