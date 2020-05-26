package be.gfddpp.services.systemservices.v2;

import javax.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {
   public SystemServices createSystemServices() {
      return new SystemServices();
   }

   public SystemServicesList createSystemServicesList() {
      return new SystemServicesList();
   }

   public SystemServicesEntry createSystemServicesEntry() {
      return new SystemServicesEntry();
   }

   public System createSystem() {
      return new System();
   }

   public Service createService() {
      return new Service();
   }

   public ContractList createContractList() {
      return new ContractList();
   }

   public ContractEntry createContractEntry() {
      return new ContractEntry();
   }

   public ContractPartyList createContractPartyList() {
      return new ContractPartyList();
   }

   public ContractPartyEntry createContractPartyEntry() {
      return new ContractPartyEntry();
   }
}
