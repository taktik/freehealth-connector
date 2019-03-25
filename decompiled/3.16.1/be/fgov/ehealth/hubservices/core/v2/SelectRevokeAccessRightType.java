package be.fgov.ehealth.hubservices.core.v2;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "SelectRevokeAccessRightType",
   propOrder = {"transaction", "hcparty"}
)
public class SelectRevokeAccessRightType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      required = true
   )
   protected TransactionIdType transaction;
   protected BasicHcPartyType hcparty;

   public TransactionIdType getTransaction() {
      return this.transaction;
   }

   public void setTransaction(TransactionIdType value) {
      this.transaction = value;
   }

   public BasicHcPartyType getHcparty() {
      return this.hcparty;
   }

   public void setHcparty(BasicHcPartyType value) {
      this.hcparty = value;
   }
}
