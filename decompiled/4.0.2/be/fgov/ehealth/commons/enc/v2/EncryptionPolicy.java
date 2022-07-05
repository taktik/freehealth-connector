package be.fgov.ehealth.commons.enc.v2;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "",
   propOrder = {"keyInfo"}
)
@XmlRootElement(
   name = "EncryptionPolicy"
)
public class EncryptionPolicy implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "KeyInfo",
      required = true
   )
   protected KeyInfo keyInfo;
   @XmlAttribute(
      name = "MimeType",
      required = true
   )
   protected String mimeType;

   public EncryptionPolicy() {
   }

   public KeyInfo getKeyInfo() {
      return this.keyInfo;
   }

   public void setKeyInfo(KeyInfo value) {
      this.keyInfo = value;
   }

   public String getMimeType() {
      return this.mimeType;
   }

   public void setMimeType(String value) {
      this.mimeType = value;
   }
}
