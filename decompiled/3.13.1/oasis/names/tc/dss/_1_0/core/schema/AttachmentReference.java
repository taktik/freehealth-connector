package oasis.names.tc.dss._1_0.core.schema;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import org.w3._2000._09.xmldsig.DigestMethod;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "AttachmentReferenceType",
   propOrder = {"digestMethod", "digestValue"}
)
@XmlRootElement(
   name = "AttachmentReference"
)
public class AttachmentReference implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "DigestMethod",
      namespace = "http://www.w3.org/2000/09/xmldsig#"
   )
   protected DigestMethod digestMethod;
   @XmlElement(
      name = "DigestValue",
      namespace = "http://www.w3.org/2000/09/xmldsig#"
   )
   protected byte[] digestValue;
   @XmlAttribute(
      name = "AttRefURI"
   )
   @XmlSchemaType(
      name = "anyURI"
   )
   protected String attRefURI;
   @XmlAttribute(
      name = "MimeType"
   )
   protected String mimeType;

   public DigestMethod getDigestMethod() {
      return this.digestMethod;
   }

   public void setDigestMethod(DigestMethod value) {
      this.digestMethod = value;
   }

   public byte[] getDigestValue() {
      return this.digestValue;
   }

   public void setDigestValue(byte[] value) {
      this.digestValue = value;
   }

   public String getAttRefURI() {
      return this.attRefURI;
   }

   public void setAttRefURI(String value) {
      this.attRefURI = value;
   }

   public String getMimeType() {
      return this.mimeType;
   }

   public void setMimeType(String value) {
      this.mimeType = value;
   }
}
