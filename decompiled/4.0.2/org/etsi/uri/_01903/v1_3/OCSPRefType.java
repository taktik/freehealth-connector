package org.etsi.uri._01903.v1_3;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "OCSPRefType",
   propOrder = {"ocspIdentifier", "digestAlgAndValue"}
)
public class OCSPRefType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "OCSPIdentifier",
      required = true
   )
   protected OCSPIdentifierType ocspIdentifier;
   @XmlElement(
      name = "DigestAlgAndValue"
   )
   protected DigestAlgAndValueType digestAlgAndValue;

   public OCSPRefType() {
   }

   public OCSPIdentifierType getOCSPIdentifier() {
      return this.ocspIdentifier;
   }

   public void setOCSPIdentifier(OCSPIdentifierType value) {
      this.ocspIdentifier = value;
   }

   public DigestAlgAndValueType getDigestAlgAndValue() {
      return this.digestAlgAndValue;
   }

   public void setDigestAlgAndValue(DigestAlgAndValueType value) {
      this.digestAlgAndValue = value;
   }
}
