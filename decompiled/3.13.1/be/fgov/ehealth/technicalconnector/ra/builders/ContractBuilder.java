package be.fgov.ehealth.technicalconnector.ra.builders;

import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.utils.IdentifierType;
import be.fgov.ehealth.technicalconnector.ra.domain.ContactData;
import be.fgov.ehealth.technicalconnector.ra.domain.Contract;
import be.fgov.ehealth.technicalconnector.ra.domain.DistinguishedName;
import be.fgov.ehealth.technicalconnector.ra.domain.NewCertificateContract;
import be.fgov.ehealth.technicalconnector.ra.domain.Organization;
import be.fgov.ehealth.technicalconnector.ra.domain.RenewCertificateContract;
import be.fgov.ehealth.technicalconnector.ra.domain.RevokeCertificateContract;
import be.fgov.ehealth.technicalconnector.ra.enumaration.Language;
import be.fgov.ehealth.technicalconnector.ra.enumaration.RevocationReason;
import be.fgov.ehealth.technicalconnector.ra.enumaration.UsageType;
import be.fgov.ehealth.technicalconnector.ra.utils.CertificateUtils;
import java.security.KeyPair;
import java.security.cert.X509Certificate;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;

public final class ContractBuilder {
   private ContractBuilder() {
      throw new UnsupportedOperationException();
   }

   private abstract static class BasicSteps<T extends Contract> implements ContractBuilder.RevokeReasonStep<T>, ContractBuilder.RevokeReasonDetailStep<T>, ContractBuilder.OrganizationChoiceStep1<T>, ContractBuilder.OrganizationChoiceStep2<T>, ContractBuilder.OrganizationChoiceStep3<T>, ContractBuilder.ContactDataStep1<T>, ContractBuilder.ContactDataStep2<T>, ContractBuilder.ContactDataStep3<T>, ContractBuilder.BuildStep<T>, ContractBuilder.LanguageChoiceStep<T>, ContractBuilder.Pkcs10GenerationStep<T> {
      private Language lang;
      private int type;
      protected X509Certificate subject;
      private String personalEmail;
      private String personalPhone;
      private String generalEmail;
      private String generalPhone;
      private UsageType[] usageTypes;
      private RevocationReason revokeReason;
      private String revokeDetail;
      private String applicationId;
      private String organizationName;
      private IdentifierType organizationIdentifier;
      private String organizationId;
      private KeyPair keypair;

      public BasicSteps(int type) {
         this.type = type;
      }

      protected void setSubject(X509Certificate cert) {
         Validate.notNull(cert);
         this.subject = cert;
      }

      public ContractBuilder.ContactDataStep<T> withApplicationId(String applicationId) {
         Validate.notEmpty(applicationId);
         this.applicationId = applicationId;
         return this;
      }

      public ContractBuilder.OrganizationChoiceStep3<T> withName(String name) {
         Validate.notEmpty(name);
         this.organizationName = name;
         return this;
      }

      public ContractBuilder.OrganizationChoiceStep2<T> withId(String id, IdentifierType type) {
         Validate.notEmpty(id);
         Validate.notNull(type);
         this.organizationId = id;
         this.organizationIdentifier = type;
         return this;
      }

      public ContractBuilder.ContactDataStep1<T> withPrivatePhone(String phone) {
         this.personalPhone = phone;
         return this;
      }

      public ContractBuilder.Pkcs10GenerationStep<T> withUsageType(UsageType... types) {
         this.usageTypes = types;
         return this;
      }

      public ContractBuilder.LanguageChoiceStep<T> generatePKCS10(KeyPair keypair) {
         Validate.notNull(keypair);
         this.keypair = keypair;
         return this;
      }

      public ContractBuilder.UsageTypeStep<T> withGeneralEmail(String mail) {
         this.generalEmail = mail;
         return this;
      }

      public ContractBuilder.ContactDataStep3<T> withGeneralPhone(String phone) {
         this.generalPhone = phone;
         return this;
      }

      public ContractBuilder.ContactDataStep2<T> withPrivateEmail(String mail) {
         this.personalEmail = mail;
         return this;
      }

      public ContractBuilder.RevokeReasonDetailStep<T> withReason(RevocationReason reason) {
         Validate.notNull(reason);
         this.revokeReason = reason;
         return this;
      }

      public ContractBuilder.LanguageChoiceStep<T> withExplanation(String msg) {
         Validate.notEmpty(msg);
         this.revokeDetail = msg;
         return this;
      }

      public ContractBuilder.BuildStep<T> useLanguage(Language lang) {
         Validate.notNull(lang);
         this.lang = lang;
         return this;
      }

      public T build() throws TechnicalConnectorException {
         switch(this.type) {
         case 0:
            DistinguishedName name = null;
            if (StringUtils.isNotEmpty(this.organizationId)) {
               Organization organization = new Organization(this.organizationId, this.organizationIdentifier, this.organizationName);
               name = new DistinguishedName(organization, this.applicationId);
            } else {
               name = new DistinguishedName();
            }

            NewCertificateContract newContract = new NewCertificateContract(name, this.generateContactData(), this.usageTypes);
            if (this.keypair != null) {
               newContract.setPkcs10DerEncoded(CertificateUtils.createCSR(name, this.keypair));
            }

            return newContract;
         case 1:
            RenewCertificateContract renewContract = new RenewCertificateContract(this.subject, this.generateContactData(), this.usageTypes);
            if (this.keypair != null) {
               renewContract.setPkcs10DerEncoded(CertificateUtils.createCSR(renewContract.getDistinguishedName(), this.keypair));
            }

            return renewContract;
         case 2:
            if (StringUtils.isNotEmpty(this.revokeDetail)) {
               return new RevokeCertificateContract(this.subject, this.revokeReason, this.revokeDetail, this.lang);
            }

            return new RevokeCertificateContract(this.subject, this.revokeReason, this.lang);
         default:
            throw new IllegalArgumentException("Unsupported type");
         }
      }

      private ContactData generateContactData() {
         ContactData contact;
         if (StringUtils.isEmpty(this.generalEmail)) {
            contact = new ContactData(this.personalPhone, this.personalEmail, this.lang);
         } else {
            contact = new ContactData(this.generalPhone, this.personalPhone, this.generalEmail, this.personalEmail, this.lang);
         }

         return contact;
      }
   }

   private static class CreateSteps extends ContractBuilder.BasicSteps<NewCertificateContract> implements ContractBuilder.CreateStep<NewCertificateContract> {
      public CreateSteps() {
         super(0);
      }

      public ContractBuilder.ContactDataStep<NewCertificateContract> withEid() {
         return this;
      }

      public ContractBuilder.OrganizationChoiceStep1<NewCertificateContract> forOrganization() {
         return this;
      }

      public ContractBuilder.OrganizationChoiceStep3<NewCertificateContract> forOrganization(Organization organization) {
         this.withId(organization.getId(), organization.getType());
         this.withName(organization.getName());
         return this;
      }
   }

   private static class RevokeSteps extends ContractBuilder.BasicSteps<RevokeCertificateContract> implements ContractBuilder.RevokeStep<RevokeCertificateContract> {
      public RevokeSteps() {
         super(2);
      }

      public ContractBuilder.RevokeReasonStep<RevokeCertificateContract> withCert(X509Certificate cert) {
         this.setSubject(cert);
         return this;
      }
   }

   private static class RenewSteps extends ContractBuilder.BasicSteps<RenewCertificateContract> implements ContractBuilder.RenewStep<RenewCertificateContract> {
      public RenewSteps() {
         super(1);
      }

      public ContractBuilder.ContactDataStep<RenewCertificateContract> withCert(X509Certificate cert) {
         this.setSubject(cert);
         return this;
      }
   }

   public interface BuildStep<T extends Contract> {
      T build() throws TechnicalConnectorException;
   }

   public interface LanguageChoiceStep<T extends Contract> {
      ContractBuilder.BuildStep<T> useLanguage(Language var1);
   }

   public interface RevokeReasonDetailStep<T extends Contract> extends ContractBuilder.LanguageChoiceStep<T> {
      ContractBuilder.LanguageChoiceStep<T> withExplanation(String var1);
   }

   public interface RevokeReasonStep<T extends Contract> {
      ContractBuilder.RevokeReasonDetailStep<T> withReason(RevocationReason var1);
   }

   public interface Pkcs10GenerationStep<T extends Contract> extends ContractBuilder.LanguageChoiceStep<T> {
      ContractBuilder.LanguageChoiceStep<T> generatePKCS10(KeyPair var1);
   }

   public interface UsageTypeStep<T extends Contract> extends ContractBuilder.Pkcs10GenerationStep<T> {
      ContractBuilder.Pkcs10GenerationStep<T> withUsageType(UsageType... var1);
   }

   public interface ContactDataStep3<T extends Contract> {
      ContractBuilder.UsageTypeStep<T> withGeneralEmail(String var1);
   }

   public interface ContactDataStep2<T extends Contract> extends ContractBuilder.UsageTypeStep<T> {
      ContractBuilder.ContactDataStep3<T> withGeneralPhone(String var1);
   }

   public interface ContactDataStep1<T extends Contract> {
      ContractBuilder.ContactDataStep2<T> withPrivateEmail(String var1);
   }

   public interface ContactDataStep<T extends Contract> {
      ContractBuilder.ContactDataStep1<T> withPrivatePhone(String var1);
   }

   public interface OrganizationChoiceStep3<T extends Contract> extends ContractBuilder.ContactDataStep<T> {
      ContractBuilder.ContactDataStep<T> withApplicationId(String var1);
   }

   public interface OrganizationChoiceStep2<T extends Contract> {
      ContractBuilder.OrganizationChoiceStep3<T> withName(String var1);
   }

   public interface OrganizationChoiceStep1<T extends Contract> {
      ContractBuilder.OrganizationChoiceStep2<T> withId(String var1, IdentifierType var2);
   }

   public interface RevokeStep<T extends Contract> {
      ContractBuilder.RevokeReasonStep<T> withCert(X509Certificate var1);
   }

   public interface RenewStep<T extends Contract> {
      ContractBuilder.ContactDataStep<T> withCert(X509Certificate var1);
   }

   public interface CreateStep<T extends Contract> {
      ContractBuilder.ContactDataStep<T> withEid();

      ContractBuilder.OrganizationChoiceStep1<T> forOrganization();

      ContractBuilder.OrganizationChoiceStep3<T> forOrganization(Organization var1);
   }

   static class ContractSteps implements ContractBuilder.ContractBuilderStep {
      public ContractBuilder.CreateStep<NewCertificateContract> create() {
         return new ContractBuilder.CreateSteps();
      }

      public ContractBuilder.RenewStep<RenewCertificateContract> renew() {
         return new ContractBuilder.RenewSteps();
      }

      public ContractBuilder.RevokeStep<RevokeCertificateContract> revoke() {
         return new ContractBuilder.RevokeSteps();
      }
   }

   public interface ContractBuilderStep {
      ContractBuilder.CreateStep<NewCertificateContract> create();

      ContractBuilder.RenewStep<RenewCertificateContract> renew();

      ContractBuilder.RevokeStep<RevokeCertificateContract> revoke();
   }
}
