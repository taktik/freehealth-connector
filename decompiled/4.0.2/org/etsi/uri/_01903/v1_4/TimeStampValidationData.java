package org.etsi.uri._01903.v1_4;

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
import org.etsi.uri._01903.v1_3.CertificateValuesType;
import org.etsi.uri._01903.v1_3.RevocationValuesType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ValidationDataType",
   propOrder = {"certificateValues", "revocationValues"}
)
@XmlRootElement(
   name = "TimeStampValidationData"
)
public class TimeStampValidationData implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "CertificateValues",
      namespace = "http://uri.etsi.org/01903/v1.3.2#"
   )
   protected CertificateValuesType certificateValues;
   @XmlElement(
      name = "RevocationValues",
      namespace = "http://uri.etsi.org/01903/v1.3.2#"
   )
   protected RevocationValuesType revocationValues;
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
      name = "UR"
   )
   @XmlSchemaType(
      name = "anyURI"
   )
   protected String ur;

   public TimeStampValidationData() {
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

   public String getId() {
      return this.id;
   }

   public void setId(String value) {
      this.id = value;
   }

   public String getUR() {
      return this.ur;
   }

   public void setUR(String value) {
      this.ur = value;
   }
}
