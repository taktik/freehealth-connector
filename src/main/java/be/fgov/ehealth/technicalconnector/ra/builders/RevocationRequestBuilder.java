package be.fgov.ehealth.technicalconnector.ra.builders;

import org.taktik.connector.technical.beid.BeIDInfo;
import org.taktik.connector.technical.beid.domain.Identity;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.utils.IdentifierType;
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

   private static class BasicSteps implements PublicKeyStep, TypeStep, BuildStep {
      private byte[] publicKeyIdentifier;
      private Actor signer;
      private RevocationReasonType revocationReasonType;

      public BasicSteps() throws TechnicalConnectorException {
         Identity requestor = BeIDInfo.getInstance().getIdentity();
         this.signer = Actor.newBuilder().firstNames(Arrays.asList(requestor.getFirstName())).name(requestor.getName()).ids(Arrays.asList(ActorId.newBuilder().type(IdentifierType.SSIN.getType(48)).value(requestor.getNationalNumber()).build())).build();
      }

      public TypeStep withPublicKeyIdentifier(byte[] publicKeyIdentifier) {
         Validate.notNull(publicKeyIdentifier);
         this.publicKeyIdentifier = ArrayUtils.clone(publicKeyIdentifier);
         return this;
      }

      public BuildStep withRevocationReasonType(RevocationReasonType revocationReasonType) {
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
      BuildStep withRevocationReasonType(RevocationReasonType var1);
   }

   public interface PublicKeyStep {
      TypeStep withPublicKeyIdentifier(byte[] var1);
   }

   static class RevokeRequestSteps implements RevokeRequestBuilderStep {
      public PublicKeyStep create() throws TechnicalConnectorException {
         return new BasicSteps();
      }
   }

   public interface RevokeRequestBuilderStep {
      PublicKeyStep create() throws TechnicalConnectorException;
   }
}
