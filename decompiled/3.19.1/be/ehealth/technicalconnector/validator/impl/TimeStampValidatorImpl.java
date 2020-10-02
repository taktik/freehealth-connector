package be.ehealth.technicalconnector.validator.impl;

import be.ehealth.technicalconnector.exception.InvalidTimeStampException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.utils.ConfigurableImplementation;
import be.ehealth.technicalconnector.utils.ConnectorCryptoUtils;
import be.ehealth.technicalconnector.validator.TimeStampValidator;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.MessageDigest;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.Validate;
import org.bouncycastle.asn1.cms.Attribute;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cms.DefaultCMSSignatureAlgorithmNameGenerator;
import org.bouncycastle.cms.SignerInformationVerifier;
import org.bouncycastle.cms.bc.BcRSASignerInfoVerifierBuilder;
import org.bouncycastle.operator.DefaultDigestAlgorithmIdentifierFinder;
import org.bouncycastle.operator.DefaultSignatureAlgorithmIdentifierFinder;
import org.bouncycastle.operator.bc.BcDigestCalculatorProvider;
import org.bouncycastle.tsp.TimeStampToken;
import org.bouncycastle.tsp.TimeStampTokenInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TimeStampValidatorImpl implements TimeStampValidator, ConfigurableImplementation {
   private static final Logger LOG = LoggerFactory.getLogger(TimeStampValidatorImpl.class);
   private KeyStore keyStore;
   private List<String> aliases;

   public void validateTimeStampToken(byte[] bs, TimeStampToken tsToken) throws InvalidTimeStampException, TechnicalConnectorException {
      byte[] calculatedDigest = ConnectorCryptoUtils.calculateDigest(tsToken.getTimeStampInfo().getMessageImprintAlgOID().getId(), bs);
      byte[] tokenDigestValue = tsToken.getTimeStampInfo().getMessageImprintDigest();
      if (!MessageDigest.isEqual(calculatedDigest, tokenDigestValue)) {
         throw new InvalidTimeStampException("Response for different message imprint digest.");
      } else {
         Attribute scV1 = tsToken.getSignedAttributes().get(PKCSObjectIdentifiers.id_aa_signingCertificate);
         Attribute scV2 = tsToken.getSignedAttributes().get(PKCSObjectIdentifiers.id_aa_signingCertificateV2);
         if (scV1 == null && scV2 == null) {
            throw new InvalidTimeStampException("no signing certificate attribute present.", (Exception)null);
         } else if (scV1 != null && scV2 != null) {
            throw new InvalidTimeStampException("Conflicting signing certificate attributes present.");
         } else {
            this.validateTimeStampToken(tsToken);
         }
      }
   }

   public void validateTimeStampToken(TimeStampToken tsToken) throws InvalidTimeStampException, TechnicalConnectorException {
      Validate.notNull(this.keyStore, "keyStore is not correctly initialised.");
      Validate.notNull(this.aliases, "aliases is not correctly initialised.");
      Validate.notNull(tsToken, "Parameter tsToken value is not nullable.");
      TimeStampTokenInfo timeStampInfo = tsToken.getTimeStampInfo();
      if (timeStampInfo != null) {
         LOG.debug("Validating TimeStampToken with SerialNumber [" + timeStampInfo.getSerialNumber() + "]");
         if (timeStampInfo.getTsa() != null) {
            X500Name name = (X500Name)timeStampInfo.getTsa().getName();
            LOG.debug("Validating Timestamp against TrustStore Looking for [" + name + "].");
         }
      }

      boolean signatureValid = false;
      Exception lastException = null;
      Iterator i$ = this.aliases.iterator();

      while(i$.hasNext()) {
         String alias = (String)i$.next();

         try {
            X509Certificate ttsaCert = (X509Certificate)this.keyStore.getCertificate(alias);
            LOG.debug("Trying to validate timestamp against certificate with alias [" + alias + "] : [" + ttsaCert.getSubjectX500Principal().getName("RFC1779") + "]");
            X509CertificateHolder tokenSigner = new X509CertificateHolder(ttsaCert.getEncoded());
            SignerInformationVerifier verifier = (new BcRSASignerInfoVerifierBuilder(new DefaultCMSSignatureAlgorithmNameGenerator(), new DefaultSignatureAlgorithmIdentifierFinder(), new DefaultDigestAlgorithmIdentifierFinder(), new BcDigestCalculatorProvider())).build(tokenSigner);
            tsToken.validate(verifier);
            signatureValid = true;
            break;
         } catch (Exception var10) {
            lastException = var10;
            LOG.debug("TimeStampToken not valid with certificate-alias [" + alias + "]: " + var10.getMessage());
         }
      }

      if (!signatureValid) {
         throw new InvalidTimeStampException("timestamp is not valid ", lastException);
      } else {
         LOG.debug("timestampToken is valid");
      }
   }

   private List<String> getAliases() {
      try {
         ArrayList<String> aliasses = Collections.list(this.keyStore.aliases());
         Collections.reverse(aliasses);
         return aliasses;
      } catch (KeyStoreException var2) {
         return new ArrayList();
      }
   }

   public void initialize(Map<String, Object> parameterMap) throws TechnicalConnectorException {
      this.setKeyStore((KeyStore)parameterMap.get("timestampvalidatior.keystore"));
      this.aliases = new ArrayList();
      List<String> aliasList = this.getAliases();
      if (aliasList != null) {
         this.aliases.addAll(aliasList);
      }

   }

   public void setKeyStore(KeyStore keyStore) {
      this.keyStore = keyStore;
   }

   public void setAliases(List<String> aliases) {
      this.aliases = aliases;
   }
}
