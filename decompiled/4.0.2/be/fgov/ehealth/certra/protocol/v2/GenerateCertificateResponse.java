package be.fgov.ehealth.certra.protocol.v2;

import be.fgov.ehealth.commons.protocol.v2.StatusResponseType;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "GenerateCertificateResponseType",
   propOrder = {"publicKeyIdentifier", "automaticallyValidated", "replacesCertificate"}
)
@XmlRootElement(
   name = "GenerateCertificateResponse"
)
public class GenerateCertificateResponse extends StatusResponseType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "PublicKeyIdentifier",
      type = String.class
   )
   @XmlJavaTypeAdapter(HexBinaryAdapter.class)
   @XmlSchemaType(
      name = "hexBinary"
   )
   protected byte[] publicKeyIdentifier;
   @XmlElement(
      name = "AutomaticallyValidated"
   )
   protected Boolean automaticallyValidated;
   @XmlElement(
      name = "ReplacesCertificate",
      type = String.class
   )
   @XmlJavaTypeAdapter(HexBinaryAdapter.class)
   @XmlSchemaType(
      name = "hexBinary"
   )
   protected byte[] replacesCertificate;

   public GenerateCertificateResponse() {
   }

   public byte[] getPublicKeyIdentifier() {
      return this.publicKeyIdentifier;
   }

   public void setPublicKeyIdentifier(byte[] value) {
      this.publicKeyIdentifier = value;
   }

   public Boolean isAutomaticallyValidated() {
      return this.automaticallyValidated;
   }

   public void setAutomaticallyValidated(Boolean value) {
      this.automaticallyValidated = value;
   }

   public byte[] getReplacesCertificate() {
      return this.replacesCertificate;
   }

   public void setReplacesCertificate(byte[] value) {
      this.replacesCertificate = value;
   }
}
