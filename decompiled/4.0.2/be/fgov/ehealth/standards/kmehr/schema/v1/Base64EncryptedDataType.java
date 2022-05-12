package be.fgov.ehealth.standards.kmehr.schema.v1;

import be.fgov.ehealth.standards.kmehr.cd.v1.CDENCRYPTIONMETHOD;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "Base64EncryptedDataType",
   propOrder = {"cd", "base64EncryptedValue"}
)
public class Base64EncryptedDataType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      required = true
   )
   protected CDENCRYPTIONMETHOD cd;
   @XmlElement(
      name = "Base64EncryptedValue",
      required = true
   )
   protected Base64EncryptedValueType base64EncryptedValue;

   public Base64EncryptedDataType() {
   }

   public CDENCRYPTIONMETHOD getCd() {
      return this.cd;
   }

   public void setCd(CDENCRYPTIONMETHOD value) {
      this.cd = value;
   }

   public Base64EncryptedValueType getBase64EncryptedValue() {
      return this.base64EncryptedValue;
   }

   public void setBase64EncryptedValue(Base64EncryptedValueType value) {
      this.base64EncryptedValue = value;
   }
}
