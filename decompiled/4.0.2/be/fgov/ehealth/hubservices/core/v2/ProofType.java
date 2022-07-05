package be.fgov.ehealth.hubservices.core.v2;

import be.fgov.ehealth.standards.kmehr.cd.v1.CDPROOF;
import be.fgov.ehealth.standards.kmehr.schema.v1.Base64EncryptedDataType;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ProofType",
   propOrder = {"cd", "binaryproof"}
)
public class ProofType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      required = true
   )
   protected CDPROOF cd;
   protected Base64EncryptedDataType binaryproof;

   public ProofType() {
   }

   public CDPROOF getCd() {
      return this.cd;
   }

   public void setCd(CDPROOF value) {
      this.cd = value;
   }

   public Base64EncryptedDataType getBinaryproof() {
      return this.binaryproof;
   }

   public void setBinaryproof(Base64EncryptedDataType value) {
      this.binaryproof = value;
   }
}
