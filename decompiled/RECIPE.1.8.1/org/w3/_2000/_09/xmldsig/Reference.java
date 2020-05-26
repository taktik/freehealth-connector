package org.w3._2000._09.xmldsig;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ReferenceType",
   propOrder = {"transforms", "digestMethod", "digestValue"}
)
@XmlRootElement(
   name = "Reference"
)
public class Reference {
   @XmlElement(
      name = "Transforms"
   )
   protected Transforms transforms;
   @XmlElement(
      name = "DigestMethod",
      required = true
   )
   protected DigestMethod digestMethod;
   @XmlElement(
      name = "DigestValue",
      required = true
   )
   protected byte[] digestValue;
   @XmlAttribute(
      name = "Id"
   )
   @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
   @XmlID
   @XmlSchemaType(
      name = "ID"
   )
   protected String id;
   @XmlAttribute(
      name = "URI"
   )
   @XmlSchemaType(
      name = "anyURI"
   )
   protected String uri;
   @XmlAttribute(
      name = "Type"
   )
   @XmlSchemaType(
      name = "anyURI"
   )
   protected String type;

   public Transforms getTransforms() {
      return this.transforms;
   }

   public void setTransforms(Transforms value) {
      this.transforms = value;
   }

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
      this.digestValue = (byte[])value;
   }

   public String getId() {
      return this.id;
   }

   public void setId(String value) {
      this.id = value;
   }

   public String getURI() {
      return this.uri;
   }

   public void setURI(String value) {
      this.uri = value;
   }

   public String getType() {
      return this.type;
   }

   public void setType(String value) {
      this.type = value;
   }
}
