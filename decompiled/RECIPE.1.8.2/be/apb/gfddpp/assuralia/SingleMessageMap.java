package be.apb.gfddpp.assuralia;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "SingleMessageMap",
   propOrder = {"key", "encryptedSingleMessage"}
)
public class SingleMessageMap {
   @XmlElement(
      name = "Key",
      required = true
   )
   protected byte[] key;
   @XmlElement(
      name = "EncryptedSingleMessage",
      required = true
   )
   protected byte[] encryptedSingleMessage;

   public byte[] getKey() {
      return this.key;
   }

   public void setKey(byte[] value) {
      this.key = value;
   }

   public byte[] getEncryptedSingleMessage() {
      return this.encryptedSingleMessage;
   }

   public void setEncryptedSingleMessage(byte[] value) {
      this.encryptedSingleMessage = value;
   }
}
