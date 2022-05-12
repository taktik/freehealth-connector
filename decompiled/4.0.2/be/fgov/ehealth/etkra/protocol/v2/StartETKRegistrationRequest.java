package be.fgov.ehealth.etkra.protocol.v2;

import be.fgov.ehealth.commons.protocol.v2.RequestType;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.w3._2000._09.xmldsig.Signature;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "StartETKRegistrationRequestType",
   propOrder = {"publicEncryptionKey", "signature"}
)
@XmlRootElement(
   name = "StartETKRegistrationRequest"
)
public class StartETKRegistrationRequest extends RequestType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "PublicEncryptionKey",
      required = true
   )
   protected byte[] publicEncryptionKey;
   @XmlElement(
      name = "Signature",
      namespace = "http://www.w3.org/2000/09/xmldsig#",
      required = true
   )
   protected Signature signature;

   public StartETKRegistrationRequest() {
   }

   public byte[] getPublicEncryptionKey() {
      return this.publicEncryptionKey;
   }

   public void setPublicEncryptionKey(byte[] value) {
      this.publicEncryptionKey = value;
   }

   public Signature getSignature() {
      return this.signature;
   }

   public void setSignature(Signature value) {
      this.signature = value;
   }
}
