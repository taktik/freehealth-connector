package be.gfddpp.services.systemservices.v2;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "contractEntry",
   propOrder = {"contractType", "contractPartyList"}
)
public class ContractEntry {
   @XmlElement(
      required = true
   )
   protected String contractType;
   @XmlElement(
      required = true
   )
   protected ContractPartyList contractPartyList;

   public String getContractType() {
      return this.contractType;
   }

   public void setContractType(String value) {
      this.contractType = value;
   }

   public ContractPartyList getContractPartyList() {
      return this.contractPartyList;
   }

   public void setContractPartyList(ContractPartyList value) {
      this.contractPartyList = value;
   }
}
