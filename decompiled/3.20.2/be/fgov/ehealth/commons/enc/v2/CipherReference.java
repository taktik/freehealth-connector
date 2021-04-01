package be.fgov.ehealth.commons.enc.v2;

import java.io.Serializable;
import javax.activation.DataHandler;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttachmentRef;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "CipherReferenceType",
   propOrder = {"uri", "digest"}
)
@XmlRootElement(
   name = "CipherReference"
)
public class CipherReference implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "URI",
      required = true,
      type = String.class
   )
   @XmlAttachmentRef
   @XmlSchemaType(
      name = "anyURI"
   )
   protected DataHandler uri;
   @XmlElement(
      name = "Digest",
      required = true
   )
   protected Digest digest;

   public DataHandler getURI() {
      return this.uri;
   }

   public void setURI(DataHandler value) {
      this.uri = value;
   }

   public Digest getDigest() {
      return this.digest;
   }

   public void setDigest(Digest value) {
      this.digest = value;
   }
}
