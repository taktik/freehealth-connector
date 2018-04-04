package be.gfddpp.services.systemservices.v2;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "contractPartyEntry",
   propOrder = {"contractPartyType", "contractPartyID"}
)
public class ContractPartyEntry {
   @XmlElement(
      required = true
   )
   protected String contractPartyType;
   @XmlElement(
      required = true
   )
   protected String contractPartyID;

   public String getContractPartyType() {
      return this.contractPartyType;
   }

   public void setContractPartyType(String value) {
      this.contractPartyType = value;
   }

   public String getContractPartyID() {
      return this.contractPartyID;
   }

   public void setContractPartyID(String value) {
      this.contractPartyID = value;
   }
}
