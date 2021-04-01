package be.fgov.ehealth.technicalconnector.ra.builders;

import be.ehealth.technicalconnector.beid.BeIDInfo;
import be.ehealth.technicalconnector.beid.domain.Identity;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.utils.IdentifierType;
import be.fgov.ehealth.technicalconnector.ra.domain.Actor;
import be.fgov.ehealth.technicalconnector.ra.domain.ActorId;
import be.fgov.ehealth.technicalconnector.ra.domain.CertificateIdentifier;
import be.fgov.ehealth.technicalconnector.ra.domain.ContactData;
import be.fgov.ehealth.technicalconnector.ra.domain.ContractRequest;
import be.fgov.ehealth.technicalconnector.ra.domain.Organization;
import java.util.Arrays;
import org.apache.commons.lang.Validate;

public final class ContractRequestBuilder {
   private ContractRequestBuilder() {
      throw new UnsupportedOperationException();
   }

   private static class BasicSteps implements ContractRequestBuilder.InitStep, ContractRequestBuilder.OrganizationChoiceStep1, ContractRequestBuilder.OrganizationChoiceStep2, ContractRequestBuilder.OrganizationChoiceStep3, ContractRequestBuilder.ContactDataStep, ContractRequestBuilder.ContactDataStep0, ContractRequestBuilder.ContactDataStep1, ContractRequestBuilder.ContactDataStep2, ContractRequestBuilder.ContactDataStep3, ContractRequestBuilder.BuildStep {
      private String personalEmail;
      private String personalPhone;
      private String generalEmail;
      private String generalPhone;
      private String organizationName;
      private IdentifierType organizationIdentifier;
      private String organizationId;
      private Identity requestor = BeIDInfo.getInstance().getIdentity();
      private CertificateIdentifier certificateIdentifier;
      Actor signer;

      public BasicSteps() throws TechnicalConnectorException {
         this.signer = Actor.newBuilder().firstNames(Arrays.asList(this.requestor.getFirstName())).name(this.requestor.getName()).ids(Arrays.asList(ActorId.newBuilder().type(IdentifierType.SSIN.getType(48)).value(this.requestor.getNationalNumber()).build())).build();
      }

      public ContractRequestBuilder.ContactDataStep withEid() {
         this.certificateIdentifier = CertificateIdentifier.newBuilder().actor(this.signer).build();
         return this;
      }

      public ContractRequestBuilder.OrganizationChoiceStep1 forOrganization() {
         return this;
      }

      public ContractRequestBuilder.OrganizationChoiceStep3 forOrganization(Organization organization) {
         this.withId(organization.getId(), organization.getType());
         this.withName(organization.getName());
         return this;
      }

      public ContractRequestBuilder.ContactDataStep withApplicationId(String applicationId) {
         Validate.notEmpty(applicationId);
         this.buildCertificateIdentifier(applicationId);
         return this;
      }

      public ContractRequestBuilder.ContactDataStep withoutApplicationId() {
         this.buildCertificateIdentifier((String)null);
         return this;
      }

      public void buildCertificateIdentifier(String applicationId) {
         this.certificateIdentifier = CertificateIdentifier.newBuilder().actor(Actor.newBuilder().firstNames(Arrays.asList(this.requestor.getFirstName())).name(this.organizationName).ids(Arrays.asList(ActorId.newBuilder().type(this.organizationIdentifier.getType(48)).value(this.organizationId).build())).build()).applicationId(applicationId).build();
      }

      public ContractRequestBuilder.OrganizationChoiceStep3 withName(String name) {
         Validate.notEmpty(name);
         this.organizationName = name;
         return this;
      }

      public ContractRequestBuilder.OrganizationChoiceStep2 withId(String id, IdentifierType type) {
         Validate.notEmpty(id);
         Validate.notNull(type);
         this.organizationId = id;
         this.organizationIdentifier = type;
         return this;
      }

      public ContractRequestBuilder.BuildStep withContact(ContactData contactData) {
         this.withPrivatePhone(contactData.getPhonePrivate());
         this.withPrivateEmail(contactData.getEmailPrivate());
         this.withGeneralPhone(contactData.getPhoneGeneral());
         this.withGeneralEmail(contactData.getEmailGeneral());
         return this;
      }

      public ContractRequestBuilder.ContactDataStep0 forContact() {
         return this;
      }

      public ContractRequestBuilder.ContactDataStep1 withPrivatePhone(String phone) {
         Validate.notEmpty(phone);
         this.personalPhone = phone;
         return this;
      }

      public ContractRequestBuilder.ContactDataStep2 withPrivateEmail(String mail) {
         Validate.notEmpty(mail);
         this.personalEmail = mail;
         return this;
      }

      public ContractRequestBuilder.ContactDataStep3 withGeneralPhone(String phone) {
         this.generalPhone = phone;
         return this;
      }

      public ContractRequestBuilder.BuildStep withGeneralEmail(String mail) {
         this.generalEmail = mail;
         return this;
      }

      public ContractRequest build() throws TechnicalConnectorException {
         return ContractRequest.newBuilder().signer(this.signer).contactData(new ContactData(this.generalPhone, this.personalPhone, this.generalEmail, this.personalEmail)).certificateIdentifier(this.certificateIdentifier).build();
      }
   }

   public interface BuildStep {
      ContractRequest build() throws TechnicalConnectorException;
   }

   public interface ContactDataStep3 {
      ContractRequestBuilder.BuildStep withGeneralEmail(String var1);
   }

   public interface ContactDataStep2 {
      ContractRequestBuilder.ContactDataStep3 withGeneralPhone(String var1);
   }

   public interface ContactDataStep1 {
      ContractRequestBuilder.ContactDataStep2 withPrivateEmail(String var1);
   }

   public interface ContactDataStep0 {
      ContractRequestBuilder.ContactDataStep1 withPrivatePhone(String var1);
   }

   public interface ContactDataStep {
      ContractRequestBuilder.BuildStep withContact(ContactData var1);

      ContractRequestBuilder.ContactDataStep0 forContact();
   }

   public interface OrganizationChoiceStep3 {
      ContractRequestBuilder.ContactDataStep withoutApplicationId();

      ContractRequestBuilder.ContactDataStep withApplicationId(String var1);
   }

   public interface OrganizationChoiceStep2 {
      ContractRequestBuilder.OrganizationChoiceStep3 withName(String var1);
   }

   public interface OrganizationChoiceStep1 {
      ContractRequestBuilder.OrganizationChoiceStep2 withId(String var1, IdentifierType var2);
   }

   public interface InitStep {
      ContractRequestBuilder.ContactDataStep withEid();

      ContractRequestBuilder.OrganizationChoiceStep1 forOrganization();

      ContractRequestBuilder.OrganizationChoiceStep3 forOrganization(Organization var1);
   }

   static class ContractRequestSteps implements ContractRequestBuilder.ContractRequestBuilderStep {
      public ContractRequestBuilder.InitStep create() throws TechnicalConnectorException {
         return new ContractRequestBuilder.BasicSteps();
      }
   }

   public interface ContractRequestBuilderStep {
      ContractRequestBuilder.InitStep create() throws TechnicalConnectorException;
   }
}
