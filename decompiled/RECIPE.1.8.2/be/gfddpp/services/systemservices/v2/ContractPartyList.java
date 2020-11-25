package be.gfddpp.services.systemservices.v2;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "contractPartyList",
   propOrder = {"contractPartyEntry"}
)
public class ContractPartyList {
   @XmlElement(
      required = true
   )
   protected List<ContractPartyEntry> contractPartyEntry;

   public List<ContractPartyEntry> getContractPartyEntry() {
      if (this.contractPartyEntry == null) {
         this.contractPartyEntry = new ArrayList();
      }

      return this.contractPartyEntry;
   }
}
