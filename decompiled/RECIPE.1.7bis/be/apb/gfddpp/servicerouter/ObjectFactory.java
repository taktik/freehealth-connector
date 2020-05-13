package be.apb.gfddpp.servicerouter;

import javax.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {
   public ContractTypes createContractTypes() {
      return new ContractTypes();
   }

   public ContractTypes.ContractType createContractTypesContractType() {
      return new ContractTypes.ContractType();
   }

   public ContractTypes.ContractType.ServiceRoutings createContractTypesContractTypeServiceRoutings() {
      return new ContractTypes.ContractType.ServiceRoutings();
   }

   public ContractTypes.ContractType.ServiceRoutings.ServiceRouting createContractTypesContractTypeServiceRoutingsServiceRouting() {
      return new ContractTypes.ContractType.ServiceRoutings.ServiceRouting();
   }
}
