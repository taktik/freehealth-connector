package org.etsi.uri._01903.v1_3;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "CRLRefType",
   propOrder = {"digestAlgAndValue", "crlIdentifier"}
)
public class CRLRefType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "DigestAlgAndValue",
      required = true
   )
   protected DigestAlgAndValueType digestAlgAndValue;
   @XmlElement(
      name = "CRLIdentifier"
   )
   protected CRLIdentifierType crlIdentifier;

   public CRLRefType() {
   }

   public DigestAlgAndValueType getDigestAlgAndValue() {
      return this.digestAlgAndValue;
   }

   public void setDigestAlgAndValue(DigestAlgAndValueType value) {
      this.digestAlgAndValue = value;
   }

   public CRLIdentifierType getCRLIdentifier() {
      return this.crlIdentifier;
   }

   public void setCRLIdentifier(CRLIdentifierType value) {
      this.crlIdentifier = value;
   }
}
