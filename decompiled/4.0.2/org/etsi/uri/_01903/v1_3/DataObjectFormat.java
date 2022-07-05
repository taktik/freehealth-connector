package org.etsi.uri._01903.v1_3;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "DataObjectFormatType",
   propOrder = {"description", "objectIdentifier", "mimeType", "encoding"}
)
@XmlRootElement(
   name = "DataObjectFormat"
)
public class DataObjectFormat implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Description"
   )
   protected String description;
   @XmlElement(
      name = "ObjectIdentifier"
   )
   protected ObjectIdentifier objectIdentifier;
   @XmlElement(
      name = "MimeType"
   )
   protected String mimeType;
   @XmlElement(
      name = "Encoding"
   )
   @XmlSchemaType(
      name = "anyURI"
   )
   protected String encoding;
   @XmlAttribute(
      name = "ObjectReference",
      required = true
   )
   @XmlSchemaType(
      name = "anyURI"
   )
   protected String objectReference;

   public DataObjectFormat() {
   }

   public String getDescription() {
      return this.description;
   }

   public void setDescription(String value) {
      this.description = value;
   }

   public ObjectIdentifier getObjectIdentifier() {
      return this.objectIdentifier;
   }

   public void setObjectIdentifier(ObjectIdentifier value) {
      this.objectIdentifier = value;
   }

   public String getMimeType() {
      return this.mimeType;
   }

   public void setMimeType(String value) {
      this.mimeType = value;
   }

   public String getEncoding() {
      return this.encoding;
   }

   public void setEncoding(String value) {
      this.encoding = value;
   }

   public String getObjectReference() {
      return this.objectReference;
   }

   public void setObjectReference(String value) {
      this.objectReference = value;
   }
}
