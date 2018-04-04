package be.fgov.ehealth.technicalconnector.ra.builders;

import be.ehealth.technicalconnector.beid.BeIDInfo;
import be.ehealth.technicalconnector.beid.domain.Identity;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.fgov.ehealth.technicalconnector.ra.domain.Organization;
import be.fgov.ehealth.technicalconnector.ra.service.AuthenticationCertificateRegistrationService;
import be.fgov.ehealth.technicalconnector.ra.service.ServiceFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

public final class InformationBuilder {
   private InformationBuilder() {
      throw new UnsupportedOperationException();
   }

   // $FF: synthetic class
   static class SyntheticClass_1 {
   }

   private static class InnerChoiceIdentityRCStep extends InformationBuilder.AbstractInnerChoiceStep implements InformationBuilder.IdentityChoiceRCStep {
      private InnerChoiceIdentityRCStep() {
      }

      public List<X509Certificate> forCurrentIdentity() {
         try {
            Identity identity = BeIDInfo.getInstance().getIdentity();
            return (List)this.service.getRevokableCertificates(identity).getResult();
         } catch (TechnicalConnectorException var2) {
            return new ArrayList();
         }
      }

      // $FF: synthetic method
      InnerChoiceIdentityRCStep(InformationBuilder.SyntheticClass_1 x0) {
         this();
      }
   }

   private static class InnerChoiceIdentityAOStep extends InformationBuilder.AbstractInnerChoiceStep implements InformationBuilder.IdentityChoiceAOStep {
      private InnerChoiceIdentityAOStep() {
      }

      public List<Organization> forCurrentIdentity() {
         try {
            Identity identity = BeIDInfo.getInstance().getIdentity();
            return (List)this.service.getOrganizationList(identity).getResult();
         } catch (TechnicalConnectorException var2) {
            return new ArrayList();
         }
      }

      // $FF: synthetic method
      InnerChoiceIdentityAOStep(InformationBuilder.SyntheticClass_1 x0) {
         this();
      }
   }

   private static class InnerChoiceOrganizationStep extends InformationBuilder.AbstractInnerChoiceStep implements InformationBuilder.OrganizationChoiceStep {
      private InnerChoiceOrganizationStep() {
      }

      public List<String> forOrganization(Organization org) {
         try {
            return (List)this.service.getApplicationIdList(org).getResult();
         } catch (TechnicalConnectorException var3) {
            return new ArrayList();
         }
      }

      // $FF: synthetic method
      InnerChoiceOrganizationStep(InformationBuilder.SyntheticClass_1 x0) {
         this();
      }
   }

   private abstract static class AbstractInnerChoiceStep {
      protected AuthenticationCertificateRegistrationService service;

      public AbstractInnerChoiceStep() {
         try {
            this.service = ServiceFactory.getAuthenticationCertificateRegistrationService();
         } catch (TechnicalConnectorException var2) {
            throw new IllegalArgumentException(var2);
         }
      }
   }

   static class InformationBuilderSteps implements InformationBuilder.InformationBuilderStep {
      public InformationBuilder.OrganizationChoiceStep listActiveApplicationIds() {
         return new InformationBuilder.InnerChoiceOrganizationStep();
      }

      public InformationBuilder.IdentityChoiceAOStep listAssociatedOrganizations() {
         return new InformationBuilder.InnerChoiceIdentityAOStep();
      }

      public InformationBuilder.IdentityChoiceRCStep listRevocableCertificates() {
         return new InformationBuilder.InnerChoiceIdentityRCStep();
      }
   }

   public interface IdentityChoiceRCStep {
      List<X509Certificate> forCurrentIdentity();
   }

   public interface IdentityChoiceAOStep {
      List<Organization> forCurrentIdentity();
   }

   public interface OrganizationChoiceStep {
      List<String> forOrganization(Organization var1);
   }

   public interface InformationBuilderStep {
      InformationBuilder.OrganizationChoiceStep listActiveApplicationIds();

      InformationBuilder.IdentityChoiceAOStep listAssociatedOrganizations();

      InformationBuilder.IdentityChoiceRCStep listRevocableCertificates();
   }
}
