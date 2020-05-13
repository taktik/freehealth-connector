package be.fgov.ehealth.technicalconnector.ra.builders;

import be.ehealth.technicalconnector.beid.BeIDInfo;
import be.ehealth.technicalconnector.beid.domain.Identity;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.utils.IdentifierType;
import be.fgov.ehealth.certra.core.v2.RevocationReasonType;
import be.fgov.ehealth.technicalconnector.ra.domain.Actor;
import be.fgov.ehealth.technicalconnector.ra.domain.ActorId;
import be.fgov.ehealth.technicalconnector.ra.domain.RevocationContractRequest;
import java.util.Arrays;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.Validate;

public final class RevocationRequestBuilder {
   private RevocationRequestBuilder() {
      throw new UnsupportedOperationException();
   }

   private static class BasicSteps implements RevocationRequestBuilder.PublicKeyStep, RevocationRequestBuilder.TypeStep, RevocationRequestBuilder.BuildStep {
      private byte[] publicKeyIdentifier;
      private Actor signer;
      private RevocationReasonType revocationReasonType;

      public BasicSteps() throws TechnicalConnectorException {
         Identity requestor = BeIDInfo.getInstance().getIdentity();
         this.signer = Actor.newBuilder().firstNames(Arrays.asList(requestor.getFirstName())).name(requestor.getName()).ids(Arrays.asList(ActorId.newBuilder().type(IdentifierType.SSIN.getType(48)).value(requestor.getNationalNumber()).build())).build();
      }

      public RevocationRequestBuilder.TypeStep withPublicKeyIdentifier(byte[] publicKeyIdentifier) {
         Validate.notNull(publicKeyIdentifier);
         this.publicKeyIdentifier = ArrayUtils.clone(publicKeyIdentifier);
         return this;
      }

      public RevocationRequestBuilder.BuildStep withRevocationReasonType(RevocationReasonType revocationReasonType) {
         this.revocationReasonType = revocationReasonType;
         return this;
      }

      public RevocationContractRequest build() throws TechnicalConnectorException {
         Validate.notNull(this.publicKeyIdentifier);
         Validate.notNull(this.revocationReasonType);
         Validate.notNull(this.signer);
         return RevocationContractRequest.newBuilder().publicKeyIdentifier(this.publicKeyIdentifier).revocationReason(this.revocationReasonType).signer(this.signer).build();
      }
   }

   public interface BuildStep {
      RevocationContractRequest build() throws TechnicalConnectorException;
   }

   public interface TypeStep {
      RevocationRequestBuilder.BuildStep withRevocationReasonType(RevocationReasonType var1);
   }

   public interface PublicKeyStep {
      RevocationRequestBuilder.TypeStep withPublicKeyIdentifier(byte[] var1);
   }

   static class RevokeRequestSteps implements RevocationRequestBuilder.RevokeRequestBuilderStep {
      public RevocationRequestBuilder.PublicKeyStep create() throws TechnicalConnectorException {
         return new RevocationRequestBuilder.BasicSteps();
      }
   }

   public interface RevokeRequestBuilderStep {
      RevocationRequestBuilder.PublicKeyStep create() throws TechnicalConnectorException;
   }
}
