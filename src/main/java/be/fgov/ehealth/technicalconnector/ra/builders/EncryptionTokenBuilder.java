package be.fgov.ehealth.technicalconnector.ra.builders;

import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.service.sts.security.Credential;
import be.fgov.ehealth.technicalconnector.ra.utils.CertificateUtils;
import java.security.KeyPair;
import java.security.cert.X509Certificate;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.Validate;

public final class EncryptionTokenBuilder {
   private EncryptionTokenBuilder() {
      throw new UnsupportedOperationException();
   }

   static class Steps implements KeyPairStep, ChallengeStep, BuildStep {
      private KeyPair pair;
      private byte[] challenge;
      private Credential cred;

      public Steps(Credential cred) {
         this.cred = cred;
      }

      public ChallengeStep withKeyPair(KeyPair pair) {
         Validate.notNull(pair);
         this.pair = pair;
         return this;
      }

      public BuildStep withChallenge(byte[] challenge) {
         Validate.isTrue(ArrayUtils.isNotEmpty(challenge));
         this.challenge = ArrayUtils.clone(challenge);
         return this;
      }

      public X509Certificate build() throws TechnicalConnectorException {
         return CertificateUtils.generateCert(this.pair.getPublic(), CertificateUtils.obtainSerialNumber(this.pair.getPrivate(), this.challenge), this.cred);
      }
   }

   public interface BuildStep {
      X509Certificate build() throws TechnicalConnectorException;
   }

   public interface ChallengeStep {
      BuildStep withChallenge(byte[] var1);
   }

   public interface KeyPairStep {
      ChallengeStep withKeyPair(KeyPair var1);
   }

   static class EncryptionTokenBuilderSteps implements EncryptionTokenBuilderStep {
      private Credential cred;

      public EncryptionTokenBuilderSteps(Credential cred) {
         this.cred = cred;
      }

      public KeyPairStep create() {
         return new Steps(this.cred);
      }
   }

   public interface EncryptionTokenBuilderStep {
      KeyPairStep create();
   }
}
