package be.cin.mycarenet.esb.medicaladvisoragreement.chap4.consult.v1;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ResponseType",
   propOrder = {"sealedContent"}
)
public class ResponseType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "SealedContent",
      required = true
   )
   protected byte[] sealedContent;

   public byte[] getSealedContent() {
      return this.sealedContent;
   }

   public void setSealedContent(byte[] value) {
      this.sealedContent = value;
   }
}
