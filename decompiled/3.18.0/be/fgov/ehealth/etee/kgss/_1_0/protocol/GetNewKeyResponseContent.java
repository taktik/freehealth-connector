package be.fgov.ehealth.etee.kgss._1_0.protocol;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "",
   propOrder = {"newKeyIdentifier", "newKey"}
)
@XmlRootElement(
   name = "GetNewKeyResponseContent"
)
public class GetNewKeyResponseContent implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "NewKeyIdentifier"
   )
   protected byte[] newKeyIdentifier;
   @XmlElement(
      name = "NewKey"
   )
   protected byte[] newKey;

   public byte[] getNewKeyIdentifier() {
      return this.newKeyIdentifier;
   }

   public void setNewKeyIdentifier(byte[] value) {
      this.newKeyIdentifier = value;
   }

   public byte[] getNewKey() {
      return this.newKey;
   }

   public void setNewKey(byte[] value) {
      this.newKey = value;
   }
}
