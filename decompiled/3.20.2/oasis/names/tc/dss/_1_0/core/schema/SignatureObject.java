package oasis.names.tc.dss._1_0.core.schema;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import org.w3._2000._09.xmldsig.Signature;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "",
   propOrder = {"other", "signaturePtr", "base64Signature", "timestamp", "signature"}
)
@XmlRootElement(
   name = "SignatureObject"
)
public class SignatureObject implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Other"
   )
   protected AnyType other;
   @XmlElement(
      name = "SignaturePtr"
   )
   protected SignaturePtr signaturePtr;
   @XmlElement(
      name = "Base64Signature"
   )
   protected Base64Signature base64Signature;
   @XmlElement(
      name = "Timestamp"
   )
   protected Timestamp timestamp;
   @XmlElement(
      name = "Signature",
      namespace = "http://www.w3.org/2000/09/xmldsig#"
   )
   protected Signature signature;
   @XmlAttribute(
      name = "SchemaRefs"
   )
   @XmlIDREF
   @XmlSchemaType(
      name = "IDREFS"
   )
   protected List<Object> schemaRefs;

   public AnyType getOther() {
      return this.other;
   }

   public void setOther(AnyType value) {
      this.other = value;
   }

   public SignaturePtr getSignaturePtr() {
      return this.signaturePtr;
   }

   public void setSignaturePtr(SignaturePtr value) {
      this.signaturePtr = value;
   }

   public Base64Signature getBase64Signature() {
      return this.base64Signature;
   }

   public void setBase64Signature(Base64Signature value) {
      this.base64Signature = value;
   }

   public Timestamp getTimestamp() {
      return this.timestamp;
   }

   public void setTimestamp(Timestamp value) {
      this.timestamp = value;
   }

   public Signature getSignature() {
      return this.signature;
   }

   public void setSignature(Signature value) {
      this.signature = value;
   }

   public List<Object> getSchemaRefs() {
      if (this.schemaRefs == null) {
         this.schemaRefs = new ArrayList();
      }

      return this.schemaRefs;
   }
}
