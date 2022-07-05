package be.cin.encrypted;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "",
   propOrder = {"replyToEtk", "businessContent", "xades"}
)
@XmlRootElement(
   name = "EncryptedKnownContent"
)
public class EncryptedKnownContent implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Reply-to-Etk"
   )
   protected byte[] replyToEtk;
   @XmlElement(
      name = "BusinessContent",
      required = true
   )
   protected BusinessContent businessContent;
   @XmlElement(
      name = "Xades"
   )
   protected byte[] xades;

   public EncryptedKnownContent() {
   }

   public byte[] getReplyToEtk() {
      return this.replyToEtk;
   }

   public void setReplyToEtk(byte[] value) {
      this.replyToEtk = value;
   }

   public BusinessContent getBusinessContent() {
      return this.businessContent;
   }

   public void setBusinessContent(BusinessContent value) {
      this.businessContent = value;
   }

   public byte[] getXades() {
      return this.xades;
   }

   public void setXades(byte[] value) {
      this.xades = value;
   }
}
