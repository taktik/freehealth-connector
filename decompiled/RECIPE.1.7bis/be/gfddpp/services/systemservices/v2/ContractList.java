package be.gfddpp.services.systemservices.v2;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "contractList",
   propOrder = {"contractEntry"}
)
public class ContractList {
   @XmlElement(
      required = true
   )
   protected List<ContractEntry> contractEntry;

   public List<ContractEntry> getContractEntry() {
      if (this.contractEntry == null) {
         this.contractEntry = new ArrayList();
      }

      return this.contractEntry;
   }
}
