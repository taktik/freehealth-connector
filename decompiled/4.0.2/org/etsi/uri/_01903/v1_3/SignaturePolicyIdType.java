package org.etsi.uri._01903.v1_3;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.w3._2000._09.xmldsig.Transforms;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "SignaturePolicyIdType",
   propOrder = {"sigPolicyId", "transforms", "sigPolicyHash", "sigPolicyQualifiers"}
)
public class SignaturePolicyIdType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "SigPolicyId",
      required = true
   )
   protected ObjectIdentifier sigPolicyId;
   @XmlElement(
      name = "Transforms",
      namespace = "http://www.w3.org/2000/09/xmldsig#"
   )
   protected Transforms transforms;
   @XmlElement(
      name = "SigPolicyHash",
      required = true
   )
   protected DigestAlgAndValueType sigPolicyHash;
   @XmlElement(
      name = "SigPolicyQualifiers"
   )
   protected SigPolicyQualifiersListType sigPolicyQualifiers;

   public SignaturePolicyIdType() {
   }

   public ObjectIdentifier getSigPolicyId() {
      return this.sigPolicyId;
   }

   public void setSigPolicyId(ObjectIdentifier value) {
      this.sigPolicyId = value;
   }

   public Transforms getTransforms() {
      return this.transforms;
   }

   public void setTransforms(Transforms value) {
      this.transforms = value;
   }

   public DigestAlgAndValueType getSigPolicyHash() {
      return this.sigPolicyHash;
   }

   public void setSigPolicyHash(DigestAlgAndValueType value) {
      this.sigPolicyHash = value;
   }

   public SigPolicyQualifiersListType getSigPolicyQualifiers() {
      return this.sigPolicyQualifiers;
   }

   public void setSigPolicyQualifiers(SigPolicyQualifiersListType value) {
      this.sigPolicyQualifiers = value;
   }
}
