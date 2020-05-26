package be.apb.standards.smoa.schema.model.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "EncryptedDataType",
   propOrder = {"data", "encryptionKeyId"}
)
public class EncryptedDataType {
   @XmlElement(
      required = true
   )
   protected byte[] data;
   @XmlElement(
      required = true
   )
   protected String encryptionKeyId;

   public byte[] getData() {
      return this.data;
   }

   public void setData(byte[] value) {
      this.data = value;
   }

   public String getEncryptionKeyId() {
      return this.encryptionKeyId;
   }

   public void setEncryptionKeyId(String value) {
      this.encryptionKeyId = value;
   }
}
