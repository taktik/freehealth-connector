package be.cin.io.unsealed.medicaladvisoragreement.ask.v1;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "RequestType",
   propOrder = {"etkHcp", "kmehrRequest"}
)
@XmlRootElement(
   name = "Request"
)
public class Request implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "EtkHcp",
      required = true
   )
   protected byte[] etkHcp;
   @XmlElement(
      name = "KmehrRequest",
      required = true
   )
   protected byte[] kmehrRequest;

   public byte[] getEtkHcp() {
      return this.etkHcp;
   }

   public void setEtkHcp(byte[] value) {
      this.etkHcp = value;
   }

   public byte[] getKmehrRequest() {
      return this.kmehrRequest;
   }

   public void setKmehrRequest(byte[] value) {
      this.kmehrRequest = value;
   }
}
