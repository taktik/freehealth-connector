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
   name = "SignedInfoType",
   propOrder = {"canonicalizationMethod", "signatureMethod", "references"}
)
@XmlRootElement(
   name = "SignedInfo"
)
public class SignedInfo implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "CanonicalizationMethod",
      required = true
   )
   protected CanonicalizationMethod canonicalizationMethod;
   @XmlElement(
      name = "SignatureMethod",
      required = true
   )
   protected SignatureMethod signatureMethod;
   @XmlElement(
      name = "Reference",
      required = true
   )
   protected List<Reference> references;
   @XmlAttribute(
      name = "Id"
   )
   @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
   @XmlID
   @XmlSchemaType(
      name = "ID"
   )
   protected String id;

   public SignedInfo() {
   }

   public CanonicalizationMethod getCanonicalizationMethod() {
      return this.canonicalizationMethod;
   }

   public void setCanonicalizationMethod(CanonicalizationMethod value) {
      this.canonicalizationMethod = value;
   }

   public SignatureMethod getSignatureMethod() {
      return this.signatureMethod;
   }

   public void setSignatureMethod(SignatureMethod value) {
      this.signatureMethod = value;
   }

   public List<Reference> getReferences() {
      if (this.references == null) {
         this.references = new ArrayList();
      }

      return this.references;
   }

   public String getId() {
      return this.id;
   }

   public void setId(String value) {
      this.id = value;
   }
}
