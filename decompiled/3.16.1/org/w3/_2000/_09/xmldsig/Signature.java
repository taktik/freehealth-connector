package org.w3._2000._09.xmldsig;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
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
   name = "SignatureType",
   propOrder = {"signedInfo", "signatureValue", "keyInfo", "objects"}
)
@XmlRootElement(
   name = "Signature"
)
public class Signature implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "SignedInfo",
      required = true
   )
   protected SignedInfo signedInfo;
   @XmlElement(
      name = "SignatureValue",
      required = true
   )
   protected SignatureValue signatureValue;
   @XmlElement(
      name = "KeyInfo"
   )
   protected KeyInfo keyInfo;
   @XmlElement(
      name = "Object"
   )
   protected List<Object> objects;
   @XmlAttribute(
      name = "Id"
   )
   @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
   @XmlID
   @XmlSchemaType(
      name = "ID"
   )
   protected String id;

   public SignedInfo getSignedInfo() {
      return this.signedInfo;
   }

   public void setSignedInfo(SignedInfo value) {
      this.signedInfo = value;
   }

   public SignatureValue getSignatureValue() {
      return this.signatureValue;
   }

   public void setSignatureValue(SignatureValue value) {
      this.signatureValue = value;
   }

   public KeyInfo getKeyInfo() {
      return this.keyInfo;
   }

   public void setKeyInfo(KeyInfo value) {
      this.keyInfo = value;
   }

   public List<Object> getObjects() {
      if (this.objects == null) {
         this.objects = new ArrayList();
      }

      return this.objects;
   }

   public String getId() {
      return this.id;
   }

   public void setId(String value) {
      this.id = value;
   }
}
