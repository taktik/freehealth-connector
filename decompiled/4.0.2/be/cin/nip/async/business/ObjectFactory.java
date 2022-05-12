package be.cin.nip.async.business;

import javax.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {
   public ObjectFactory() {
   }

   public GenericRequestList createGenericRequestList() {
      return new GenericRequestList();
   }

   public GenericRequest createGenericRequest() {
      return new GenericRequest();
   }

   public SamlAttributeType createSamlAttributeType() {
      return new SamlAttributeType();
   }

   public GenericResponseList createGenericResponseList() {
      return new GenericResponseList();
   }

   public GenericResponse createGenericResponse() {
      return new GenericResponse();
   }
}
