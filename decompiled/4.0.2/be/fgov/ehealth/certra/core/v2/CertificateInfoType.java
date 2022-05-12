package be.fgov.ehealth.certra.core.v2;

import be.ehealth.technicalconnector.adapter.XmlDateTimeAdapter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.joda.time.DateTime;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "CertificateInfoType",
   propOrder = {"requestedDate", "publicKeyIdentifier", "certificateEntity", "authenticationCertificateStatus", "etkStatus", "automaticallyValidated", "authenticationCertificateDetails", "revocable", "replaceable", "replacementPeriodStartDate", "contactData", "baseServiceUsages"}
)
public class CertificateInfoType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "RequestedDate",
      required = true,
      type = String.class
   )
   @XmlJavaTypeAdapter(XmlDateTimeAdapter.class)
   @XmlSchemaType(
      name = "dateTime"
   )
   protected DateTime requestedDate;
   @XmlElement(
      name = "PublicKeyIdentifier",
      required = true,
      type = String.class
   )
   @XmlJavaTypeAdapter(HexBinaryAdapter.class)
   @XmlSchemaType(
      name = "hexBinary"
   )
   protected byte[] publicKeyIdentifier;
   @XmlElement(
      name = "CertificateEntity",
      required = true
   )
   protected CertificateEntityType certificateEntity;
   @XmlElement(
      name = "AuthenticationCertificateStatus",
      required = true
   )
   @XmlSchemaType(
      name = "string"
   )
   protected CertificateStatusType authenticationCertificateStatus;
   @XmlElement(
      name = "ETKStatus",
      required = true
   )
   @XmlSchemaType(
      name = "string"
   )
   protected ETKStatusType etkStatus;
   @XmlElement(
      name = "AutomaticallyValidated"
   )
   protected boolean automaticallyValidated;
   @XmlElement(
      name = "AuthenticationCertificateDetails"
   )
   protected CertificateDetailsType authenticationCertificateDetails;
   @XmlElement(
      name = "Revocable"
   )
   protected boolean revocable;
   @XmlElement(
      name = "Replaceable"
   )
   protected boolean replaceable;
   @XmlElement(
      name = "ReplacementPeriodStartDate",
      type = String.class
   )
   @XmlJavaTypeAdapter(XmlDateTimeAdapter.class)
   @XmlSchemaType(
      name = "dateTime"
   )
   protected DateTime replacementPeriodStartDate;
   @XmlElement(
      name = "ContactData",
      required = true
   )
   protected ContactDataType contactData;
   @XmlElement(
      name = "BaseServiceUsage"
   )
   protected List<String> baseServiceUsages;

   public CertificateInfoType() {
   }

   public DateTime getRequestedDate() {
      return this.requestedDate;
   }

   public void setRequestedDate(DateTime value) {
      this.requestedDate = value;
   }

   public byte[] getPublicKeyIdentifier() {
      return this.publicKeyIdentifier;
   }

   public void setPublicKeyIdentifier(byte[] value) {
      this.publicKeyIdentifier = value;
   }

   public CertificateEntityType getCertificateEntity() {
      return this.certificateEntity;
   }

   public void setCertificateEntity(CertificateEntityType value) {
      this.certificateEntity = value;
   }

   public CertificateStatusType getAuthenticationCertificateStatus() {
      return this.authenticationCertificateStatus;
   }

   public void setAuthenticationCertificateStatus(CertificateStatusType value) {
      this.authenticationCertificateStatus = value;
   }

   public ETKStatusType getETKStatus() {
      return this.etkStatus;
   }

   public void setETKStatus(ETKStatusType value) {
      this.etkStatus = value;
   }

   public boolean isAutomaticallyValidated() {
      return this.automaticallyValidated;
   }

   public void setAutomaticallyValidated(boolean value) {
      this.automaticallyValidated = value;
   }

   public CertificateDetailsType getAuthenticationCertificateDetails() {
      return this.authenticationCertificateDetails;
   }

   public void setAuthenticationCertificateDetails(CertificateDetailsType value) {
      this.authenticationCertificateDetails = value;
   }

   public boolean isRevocable() {
      return this.revocable;
   }

   public void setRevocable(boolean value) {
      this.revocable = value;
   }

   public boolean isReplaceable() {
      return this.replaceable;
   }

   public void setReplaceable(boolean value) {
      this.replaceable = value;
   }

   public DateTime getReplacementPeriodStartDate() {
      return this.replacementPeriodStartDate;
   }

   public void setReplacementPeriodStartDate(DateTime value) {
      this.replacementPeriodStartDate = value;
   }

   public ContactDataType getContactData() {
      return this.contactData;
   }

   public void setContactData(ContactDataType value) {
      this.contactData = value;
   }

   public List<String> getBaseServiceUsages() {
      if (this.baseServiceUsages == null) {
         this.baseServiceUsages = new ArrayList();
      }

      return this.baseServiceUsages;
   }
}
