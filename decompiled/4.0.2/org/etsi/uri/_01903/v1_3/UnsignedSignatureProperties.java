package org.etsi.uri._01903.v1_3;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
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
   name = "UnsignedSignaturePropertiesType",
   propOrder = {"counterSignatures", "signatureTimeStamps", "completeCertificateRefs", "completeRevocationRefs", "attributeCertificateRefs", "attributeRevocationRefs", "sigAndRefsTimeStamps", "refsOnlyTimeStamps", "certificateValues", "revocationValues", "attrAuthoritiesCertValues", "attributeRevocationValues", "archiveTimeStamps", "anies"}
)
@XmlRootElement(
   name = "UnsignedSignatureProperties"
)
public class UnsignedSignatureProperties implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "CounterSignature"
   )
   protected List<CounterSignature> counterSignatures;
   @XmlElement(
      name = "SignatureTimeStamp"
   )
   protected List<XAdESTimeStampType> signatureTimeStamps;
   @XmlElement(
      name = "CompleteCertificateRefs"
   )
   protected CompleteCertificateRefsType completeCertificateRefs;
   @XmlElement(
      name = "CompleteRevocationRefs"
   )
   protected CompleteRevocationRefsType completeRevocationRefs;
   @XmlElement(
      name = "AttributeCertificateRefs"
   )
   protected CompleteCertificateRefsType attributeCertificateRefs;
   @XmlElement(
      name = "AttributeRevocationRefs"
   )
   protected CompleteRevocationRefsType attributeRevocationRefs;
   @XmlElement(
      name = "SigAndRefsTimeStamp"
   )
   protected List<XAdESTimeStampType> sigAndRefsTimeStamps;
   @XmlElement(
      name = "RefsOnlyTimeStamp"
   )
   protected List<XAdESTimeStampType> refsOnlyTimeStamps;
   @XmlElement(
      name = "CertificateValues"
   )
   protected CertificateValuesType certificateValues;
   @XmlElement(
      name = "RevocationValues"
   )
   protected RevocationValuesType revocationValues;
   @XmlElement(
      name = "AttrAuthoritiesCertValues"
   )
   protected CertificateValuesType attrAuthoritiesCertValues;
   @XmlElement(
      name = "AttributeRevocationValues"
   )
   protected RevocationValuesType attributeRevocationValues;
   @XmlElement(
      name = "ArchiveTimeStamp"
   )
   protected List<XAdESTimeStampType> archiveTimeStamps;
   @XmlAnyElement(
      lax = true
   )
   protected List<Object> anies;
   @XmlAttribute(
      name = "Id"
   )
   @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
   @XmlID
   @XmlSchemaType(
      name = "ID"
   )
   protected String id;

   public UnsignedSignatureProperties() {
   }

   public List<CounterSignature> getCounterSignatures() {
      if (this.counterSignatures == null) {
         this.counterSignatures = new ArrayList();
      }

      return this.counterSignatures;
   }

   public List<XAdESTimeStampType> getSignatureTimeStamps() {
      if (this.signatureTimeStamps == null) {
         this.signatureTimeStamps = new ArrayList();
      }

      return this.signatureTimeStamps;
   }

   public CompleteCertificateRefsType getCompleteCertificateRefs() {
      return this.completeCertificateRefs;
   }

   public void setCompleteCertificateRefs(CompleteCertificateRefsType value) {
      this.completeCertificateRefs = value;
   }

   public CompleteRevocationRefsType getCompleteRevocationRefs() {
      return this.completeRevocationRefs;
   }

   public void setCompleteRevocationRefs(CompleteRevocationRefsType value) {
      this.completeRevocationRefs = value;
   }

   public CompleteCertificateRefsType getAttributeCertificateRefs() {
      return this.attributeCertificateRefs;
   }

   public void setAttributeCertificateRefs(CompleteCertificateRefsType value) {
      this.attributeCertificateRefs = value;
   }

   public CompleteRevocationRefsType getAttributeRevocationRefs() {
      return this.attributeRevocationRefs;
   }

   public void setAttributeRevocationRefs(CompleteRevocationRefsType value) {
      this.attributeRevocationRefs = value;
   }

   public List<XAdESTimeStampType> getSigAndRefsTimeStamps() {
      if (this.sigAndRefsTimeStamps == null) {
         this.sigAndRefsTimeStamps = new ArrayList();
      }

      return this.sigAndRefsTimeStamps;
   }

   public List<XAdESTimeStampType> getRefsOnlyTimeStamps() {
      if (this.refsOnlyTimeStamps == null) {
         this.refsOnlyTimeStamps = new ArrayList();
      }

      return this.refsOnlyTimeStamps;
   }

   public CertificateValuesType getCertificateValues() {
      return this.certificateValues;
   }

   public void setCertificateValues(CertificateValuesType value) {
      this.certificateValues = value;
   }

   public RevocationValuesType getRevocationValues() {
      return this.revocationValues;
   }

   public void setRevocationValues(RevocationValuesType value) {
      this.revocationValues = value;
   }

   public CertificateValuesType getAttrAuthoritiesCertValues() {
      return this.attrAuthoritiesCertValues;
   }

   public void setAttrAuthoritiesCertValues(CertificateValuesType value) {
      this.attrAuthoritiesCertValues = value;
   }

   public RevocationValuesType getAttributeRevocationValues() {
      return this.attributeRevocationValues;
   }

   public void setAttributeRevocationValues(RevocationValuesType value) {
      this.attributeRevocationValues = value;
   }

   public List<XAdESTimeStampType> getArchiveTimeStamps() {
      if (this.archiveTimeStamps == null) {
         this.archiveTimeStamps = new ArrayList();
      }

      return this.archiveTimeStamps;
   }

   public List<Object> getAnies() {
      if (this.anies == null) {
         this.anies = new ArrayList();
      }

      return this.anies;
   }

   public String getId() {
      return this.id;
   }

   public void setId(String value) {
      this.id = value;
   }
}
