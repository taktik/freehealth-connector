package be.fgov.ehealth.hubservices.core.v1;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "SelectGetAccessRightType",
   propOrder = {"transaction"}
)
public class SelectGetAccessRightType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      required = true
   )
   protected TransactionIdType transaction;

   public SelectGetAccessRightType() {
   }

   public TransactionIdType getTransaction() {
      return this.transaction;
   }

   public void setTransaction(TransactionIdType value) {
      this.transaction = value;
   }
}
