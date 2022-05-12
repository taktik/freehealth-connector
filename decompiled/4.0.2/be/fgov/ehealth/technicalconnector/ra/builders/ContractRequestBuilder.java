package be.fgov.ehealth.technicalconnector.ra.builders;

import be.ehealth.technicalconnector.beid.BeIDFactory;
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

   private static class BasicSteps implements InitStep, OrganizationChoiceStep1, OrganizationChoiceStep2, OrganizationChoiceStep3, ContactDataStep, ContactDataStep0, ContactDataStep1, ContactDataStep2, ContactDataStep3, BuildStep {
      private String personalEmail;
      private String personalPhone;
      private String generalEmail;
      private String generalPhone;
      private String organizationName;
      private IdentifierType organizationIdentifier;
      private String organizationId;
      private Identity requestor = BeIDFactory.getBeIDInfo("requestor", false).getIdentity();
      private CertificateIdentifier certificateIdentifier;
      Actor signer;

      public BasicSteps() throws TechnicalConnectorException {
         this.signer = Actor.newBuilder().firstNames(Arrays.asList(this.requestor.getFirstName())).name(this.requestor.getName()).ids(Arrays.asList(ActorId.newBuilder().type(IdentifierType.SSIN.getType(48)).value(this.requestor.getNationalNumber()).build())).build();
      }

      public ContactDataStep withEid() {
         this.certificateIdentifier = CertificateIdentifier.newBuilder().actor(this.signer).build();
         return this;
      }

      public OrganizationChoiceStep1 forOrganization() {
         return this;
      }

      public OrganizationChoiceStep3 forOrganization(Organization organization) {
         this.withId(organization.getId(), organization.getType());
         this.withName(organization.getName());
         return this;
      }

      public ContactDataStep withApplicationId(String applicationId) {
         Validate.notEmpty(applicationId);
         this.buildCertificateIdentifier(applicationId);
         return this;
      }

      public ContactDataStep withoutApplicationId() {
         this.buildCertificateIdentifier((String)null);
         return this;
      }

      public void buildCertificateIdentifier(String applicationId) {
         this.certificateIdentifier = CertificateIdentifier.newBuilder().actor(Actor.newBuilder().firstNames(Arrays.asList(this.requestor.getFirstName())).name(this.organizationName).ids(Arrays.asList(ActorId.newBuilder().type(this.organizationIdentifier.getType(48)).value(this.organizationId).build())).build()).applicationId(applicationId).build();
      }

      public OrganizationChoiceStep3 withName(String name) {
         Validate.notEmpty(name);
         this.organizationName = name;
         return this;
      }

      public OrganizationChoiceStep2 withId(String id, IdentifierType type) {
         Validate.notEmpty(id);
         Validate.notNull(type);
         this.organizationId = id;
         this.organizationIdentifier = type;
         return this;
      }

      public BuildStep withContact(ContactData contactData) {
         this.withPrivatePhone(contactData.getPhonePrivate());
         this.withPrivateEmail(contactData.getEmailPrivate());
         this.withGeneralPhone(contactData.getPhoneGeneral());
         this.withGeneralEmail(contactData.getEmailGeneral());
         return this;
      }

      public ContactDataStep0 forContact() {
         return this;
      }

      public ContactDataStep1 withPrivatePhone(String phone) {
         Validate.notEmpty(phone);
         this.personalPhone = phone;
         return this;
      }

      public ContactDataStep2 withPrivateEmail(String mail) {
         Validate.notEmpty(mail);
         this.personalEmail = mail;
         return this;
      }

      public ContactDataStep3 withGeneralPhone(String phone) {
         this.generalPhone = phone;
         return this;
      }

      public BuildStep withGeneralEmail(String mail) {
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
      BuildStep withGeneralEmail(String var1);
   }

   public interface ContactDataStep2 {
      ContactDataStep3 withGeneralPhone(String var1);
   }

   public interface ContactDataStep1 {
      ContactDataStep2 withPrivateEmail(String var1);
   }

   public interface ContactDataStep0 {
      ContactDataStep1 withPrivatePhone(String var1);
   }

   public interface ContactDataStep {
      BuildStep withContact(ContactData var1);

      ContactDataStep0 forContact();
   }

   public interface OrganizationChoiceStep3 {
      ContactDataStep withoutApplicationId();

      ContactDataStep withApplicationId(String var1);
   }

   public interface OrganizationChoiceStep2 {
      OrganizationChoiceStep3 withName(String var1);
   }

   public interface OrganizationChoiceStep1 {
      OrganizationChoiceStep2 withId(String var1, IdentifierType var2);
   }

   public interface InitStep {
      ContactDataStep withEid();

      OrganizationChoiceStep1 forOrganization();

      OrganizationChoiceStep3 forOrganization(Organization var1);
   }

   static class ContractRequestSteps implements ContractRequestBuilderStep {
      ContractRequestSteps() {
      }

      public InitStep create() throws TechnicalConnectorException {
         return new BasicSteps();
      }
   }

   public interface ContractRequestBuilderStep {
      InitStep create() throws TechnicalConnectorException;
   }
}
