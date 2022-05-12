package org.etsi.uri._01903.v1_3;

import java.io.Serializable;
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
   name = "QualifyingPropertiesType",
   propOrder = {"signedProperties", "unsignedProperties"}
)
@XmlRootElement(
   name = "QualifyingProperties"
)
public class QualifyingProperties implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "SignedProperties"
   )
   protected SignedProperties signedProperties;
   @XmlElement(
      name = "UnsignedProperties"
   )
   protected UnsignedProperties unsignedProperties;
   @XmlAttribute(
      name = "Target",
      required = true
   )
   @XmlSchemaType(
      name = "anyURI"
   )
   protected String target;
   @XmlAttribute(
      name = "Id"
   )
   @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
   @XmlID
   @XmlSchemaType(
      name = "ID"
   )
   protected String id;

   public QualifyingProperties() {
   }

   public SignedProperties getSignedProperties() {
      return this.signedProperties;
   }

   public void setSignedProperties(SignedProperties value) {
      this.signedProperties = value;
   }

   public UnsignedProperties getUnsignedProperties() {
      return this.unsignedProperties;
   }

   public void setUnsignedProperties(UnsignedProperties value) {
      this.unsignedProperties = value;
   }

   public String getTarget() {
      return this.target;
   }

   public void setTarget(String value) {
      this.target = value;
   }

   public String getId() {
      return this.id;
   }

   public void setId(String value) {
      this.id = value;
   }
}
