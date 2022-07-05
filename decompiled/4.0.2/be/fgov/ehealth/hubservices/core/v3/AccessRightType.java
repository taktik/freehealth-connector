package be.fgov.ehealth.hubservices.core.v3;

import be.fgov.ehealth.standards.kmehr.cd.v1.CDACCESSRIGHT;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "AccessRightType",
   propOrder = {"transaction", "hcparty", "cd"}
)
@XmlRootElement(
   name = "AccessRightType"
)
public class AccessRightType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      required = true
   )
   protected TransactionIdType transaction;
   @XmlElement(
      required = true
   )
   protected HcpartyType hcparty;
   @XmlElement(
      required = true
   )
   protected CDACCESSRIGHT cd;

   public AccessRightType() {
   }

   public TransactionIdType getTransaction() {
      return this.transaction;
   }

   public void setTransaction(TransactionIdType value) {
      this.transaction = value;
   }

   public HcpartyType getHcparty() {
      return this.hcparty;
   }

   public void setHcparty(HcpartyType value) {
      this.hcparty = value;
   }

   public CDACCESSRIGHT getCd() {
      return this.cd;
   }

   public void setCd(CDACCESSRIGHT value) {
      this.cd = value;
   }
}
