package be.cin.mycarenet.esb.medicaladvisoragreement.chap4.consult.v1;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "RequestType",
   propOrder = {"encryptedContent"}
)
public class RequestType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "EncryptedContent",
      required = true
   )
   protected byte[] encryptedContent;

   public byte[] getEncryptedContent() {
      return this.encryptedContent;
   }

   public void setEncryptedContent(byte[] value) {
      this.encryptedContent = value;
   }
}
