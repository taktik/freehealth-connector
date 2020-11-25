package be.fgov.ehealth.etee.ra.revoke._1_0.protocol;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "",
   propOrder = {"status", "ssin", "revocablePersonalCertificates", "revocableOrganizationCertificates", "aaOrganizationAuth"}
)
@XmlRootElement(
   name = "RevocableCertificatesDataResponse"
)
public class RevocableCertificatesDataResponse implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Status",
      required = true
   )
   @XmlSchemaType(
      name = "string"
   )
   protected StatusType status;
   @XmlElement(
      name = "SSIN",
      required = true
   )
   protected String ssin;
   @XmlElement(
      name = "RevocablePersonalCertificate"
   )
   protected List<RevocableCertificateType> revocablePersonalCertificates;
   @XmlElement(
      name = "RevocableOrganizationCertificate"
   )
   protected List<RevocableCertificateType> revocableOrganizationCertificates;
   @XmlElement(
      name = "AAOrganizationAuth"
   )
   protected byte[] aaOrganizationAuth;

   public StatusType getStatus() {
      return this.status;
   }

   public void setStatus(StatusType value) {
      this.status = value;
   }

   public String getSSIN() {
      return this.ssin;
   }

   public void setSSIN(String value) {
      this.ssin = value;
   }

   public List<RevocableCertificateType> getRevocablePersonalCertificates() {
      if (this.revocablePersonalCertificates == null) {
         this.revocablePersonalCertificates = new ArrayList();
      }

      return this.revocablePersonalCertificates;
   }

   public List<RevocableCertificateType> getRevocableOrganizationCertificates() {
      if (this.revocableOrganizationCertificates == null) {
         this.revocableOrganizationCertificates = new ArrayList();
      }

      return this.revocableOrganizationCertificates;
   }

   public byte[] getAAOrganizationAuth() {
      return this.aaOrganizationAuth;
   }

   public void setAAOrganizationAuth(byte[] value) {
      this.aaOrganizationAuth = value;
   }
}
