package be.gfddpp.services.systemservices.v1;

import javax.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {
   public SystemServices createSystemServices() {
      return new SystemServices();
   }

   public SystemServices.SystemServicesList createSystemServicesSystemServicesList() {
      return new SystemServices.SystemServicesList();
   }

   public SystemServices.SystemServicesList.SystemServicesEntry createSystemServicesSystemServicesListSystemServicesEntry() {
      return new SystemServices.SystemServicesList.SystemServicesEntry();
   }

   public SystemServices.SystemServicesList.SystemServicesEntry.System createSystemServicesSystemServicesListSystemServicesEntrySystem() {
      return new SystemServices.SystemServicesList.SystemServicesEntry.System();
   }

   public SystemServices.SystemServicesList.SystemServicesEntry.Service createSystemServicesSystemServicesListSystemServicesEntryService() {
      return new SystemServices.SystemServicesList.SystemServicesEntry.Service();
   }
}
