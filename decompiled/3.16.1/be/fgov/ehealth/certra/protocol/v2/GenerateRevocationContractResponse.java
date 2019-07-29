package be.fgov.ehealth.certra.protocol.v2;

import be.fgov.ehealth.certra.core.v2.RevocationContractType;
import be.fgov.ehealth.commons.protocol.v2.StatusResponseType;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "GenerateRevocationContractResponseType",
   propOrder = {"contract"}
)
@XmlRootElement(
   name = "GenerateRevocationContractResponse"
)
public class GenerateRevocationContractResponse extends StatusResponseType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Contract"
   )
   protected RevocationContractType contract;

   public RevocationContractType getContract() {
      return this.contract;
   }

   public void setContract(RevocationContractType value) {
      this.contract = value;
   }
}
