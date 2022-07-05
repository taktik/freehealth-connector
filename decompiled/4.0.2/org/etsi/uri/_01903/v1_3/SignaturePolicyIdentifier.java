package org.etsi.uri._01903.v1_3;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "SignaturePolicyIdentifierType",
   propOrder = {"signaturePolicyImplied", "signaturePolicyId"}
)
@XmlRootElement(
   name = "SignaturePolicyIdentifier"
)
public class SignaturePolicyIdentifier implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "SignaturePolicyImplied"
   )
   protected Object signaturePolicyImplied;
   @XmlElement(
      name = "SignaturePolicyId"
   )
   protected SignaturePolicyIdType signaturePolicyId;

   public SignaturePolicyIdentifier() {
   }

   public Object getSignaturePolicyImplied() {
      return this.signaturePolicyImplied;
   }

   public void setSignaturePolicyImplied(Object value) {
      this.signaturePolicyImplied = value;
   }

   public SignaturePolicyIdType getSignaturePolicyId() {
      return this.signaturePolicyId;
   }

   public void setSignaturePolicyId(SignaturePolicyIdType value) {
      this.signaturePolicyId = value;
   }
}
