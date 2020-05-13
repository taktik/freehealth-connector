package be.ehealth.technicalconnector.service.etee.impl;

import be.ehealth.technicalconnector.config.ConfigFactory;
import be.ehealth.technicalconnector.config.Configuration;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorExceptionValues;
import be.ehealth.technicalconnector.exception.UnsealConnectorException;
import be.ehealth.technicalconnector.exception.UnsealConnectorExceptionValues;
import be.ehealth.technicalconnector.service.etee.Crypto;
import be.ehealth.technicalconnector.service.etee.domain.EncryptionToken;
import be.ehealth.technicalconnector.service.etee.domain.UnsealedData;
import be.ehealth.technicalconnector.service.kgss.domain.KeyResult;
import be.ehealth.technicalconnector.service.sts.security.Credential;
import be.ehealth.technicalconnector.service.sts.security.impl.BeIDCredential;
import be.fgov.ehealth.etee.crypto.decrypt.DataUnsealer;
import be.fgov.ehealth.etee.crypto.decrypt.DataUnsealerBuilder;
import be.fgov.ehealth.etee.crypto.encrypt.DataSealer;
import be.fgov.ehealth.etee.crypto.encrypt.DataSealerBuilder;
import be.fgov.ehealth.etee.crypto.policies.EncryptionCredentials;
import be.fgov.ehealth.etee.crypto.policies.EncryptionPolicy;
import be.fgov.ehealth.etee.crypto.policies.OCSPOption;
import be.fgov.ehealth.etee.crypto.policies.OCSPPolicy;
import be.fgov.ehealth.etee.crypto.policies.SigningCredential;
import be.fgov.ehealth.etee.crypto.policies.SigningOption;
import be.fgov.ehealth.etee.crypto.policies.SigningPolicy;
import be.fgov.ehealth.etee.crypto.status.CryptoResult;
import be.fgov.ehealth.etee.crypto.status.NotificationWarning;
import be.fgov.ehealth.etee.crypto.utils.Iterables;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.crypto.SecretKey;
import org.apache.commons.lang.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CryptoImpl extends AbstractEndToEndCrypto {
   private static final String PROP_CAKEYSTORE_PATH = "CAKEYSTORE_LOCATION";
   private static final String PROP_CAKEYSTORE_PASSWORD = "CAKEYSTORE_PASSWORD";
   public static final String PROP_LIST_IGNORED_NOTIFICATION_ERRORS_ROOTKEY = "be.ehealth.technicalconnector.service.etee.cryptoimpl.ignored_notification_errors";
   private static final String PROP_KEYSTORE_DIR = "KEYSTORE_DIR";
   private static final Logger LOG = LoggerFactory.getLogger(CryptoImpl.class);
   private Map<Crypto.SigningPolicySelector, DataSealer> dataSealer = new EnumMap(Crypto.SigningPolicySelector.class);
   private Map<Crypto.SigningPolicySelector, DataUnsealer> dataUnsealer = new EnumMap(Crypto.SigningPolicySelector.class);
   private static Configuration config = ConfigFactory.getConfigValidatorFor("CAKEYSTORE_PASSWORD", "KEYSTORE_DIR", "CAKEYSTORE_LOCATION");

   public CryptoImpl() {
   }

   /** @deprecated */
   @Deprecated
   public CryptoImpl(Credential encryption, Map<String, PrivateKey> decryptionKeys) throws TechnicalConnectorException {
      this.initSealing(Crypto.SigningPolicySelector.WITH_NON_REPUDIATION, encryption, OCSPPolicy.NONE, new EnumMap(OCSPOption.class));
      this.initSealing(Crypto.SigningPolicySelector.WITHOUT_NON_REPUDIATION, encryption, OCSPPolicy.NONE, new EnumMap(OCSPOption.class));
      this.initUnsealing(Crypto.SigningPolicySelector.WITH_NON_REPUDIATION, decryptionKeys, OCSPPolicy.NONE, new EnumMap(OCSPOption.class), new EnumMap(SigningOption.class), SigningPolicy.EHEALTH_CERT, SigningPolicy.EID);
      this.initUnsealing(Crypto.SigningPolicySelector.WITHOUT_NON_REPUDIATION, decryptionKeys, OCSPPolicy.NONE, new EnumMap(OCSPOption.class), new EnumMap(SigningOption.class), SigningPolicy.EHEALTH_CERT, SigningPolicy.EID);
   }

   public byte[] seal(Crypto.SigningPolicySelector type, Set<EncryptionToken> paramEncryptionTokenSet, KeyResult symmKey, byte[] content) throws TechnicalConnectorException {
      try {
         this.dumpMessage(content, "Message to seal:");
         SecretKey key = null;
         String kekId = null;
         if (symmKey != null) {
            key = symmKey.getSecretKey();
            kekId = symmKey.getKeyId();
         }

         return this.getDataSealer(type).seal(convertoToEncryptionToken(paramEncryptionTokenSet), content, key, kekId);
      } catch (Exception var7) {
         LOG.error("Error while sealing message : {}", var7.getMessage());
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_CRYPTO, var7, new Object[]{"Data can't be sealed."});
      }
   }

   private static Set<be.fgov.ehealth.etee.crypto.encrypt.EncryptionToken> convertoToEncryptionToken(Set<EncryptionToken> paramEncryptionTokenSet) {
      Set<be.fgov.ehealth.etee.crypto.encrypt.EncryptionToken> sealedSet = null;
      if (paramEncryptionTokenSet != null) {
         sealedSet = new HashSet();
         Iterator i$ = paramEncryptionTokenSet.iterator();

         while(i$.hasNext()) {
            EncryptionToken etk = (EncryptionToken)i$.next();
            sealedSet.add(etk.getEtk());
         }
      }

      return sealedSet;
   }

   public UnsealedData unseal(Crypto.SigningPolicySelector type, byte[] protectedMessage) throws TechnicalConnectorException {
      CryptoResult<be.fgov.ehealth.etee.crypto.decrypt.UnsealedData> result = this.getDataUnsealer(type).unseal(protectedMessage);
      return processUnsealResult(result);
   }

   public UnsealedData unseal(Crypto.SigningPolicySelector type, KeyResult symmKey, byte[] protectedMessage) throws TechnicalConnectorException {
      CryptoResult<be.fgov.ehealth.etee.crypto.decrypt.UnsealedData> result = this.getDataUnsealer(type).unseal(protectedMessage, symmKey.getSecretKey());
      return processUnsealResult(result);
   }

   private static UnsealedData processUnsealResult(CryptoResult<be.fgov.ehealth.etee.crypto.decrypt.UnsealedData> result) throws TechnicalConnectorException {
      UnsealedData unsealedData = null;
      if (result.hasErrors()) {
         LOG.error("Unsealed message is invalid.");
         throw new UnsealConnectorException(UnsealConnectorExceptionValues.ERROR_CRYPTO, result, new Object[]{"Data can't be unsealed."});
      } else if (ignoreWarnings(result)) {
         return map((be.fgov.ehealth.etee.crypto.decrypt.UnsealedData)result.getData());
      } else {
         throw new UnsealConnectorException(UnsealConnectorExceptionValues.ERROR_CRYPTO, result, new Object[]{"Data can't be unsealed."});
      }
   }

   private static boolean ignoreWarnings(CryptoResult<be.fgov.ehealth.etee.crypto.decrypt.UnsealedData> result) {
      Set<NotificationWarning> warnings = new HashSet();
      warnings.addAll(result.getWarnings());
      List<String> ignoredWarnings = config.getMatchingProperties("be.ehealth.technicalconnector.service.etee.cryptoimpl.ignored_notification_errors");
      if (ignoredWarnings == null) {
         ignoredWarnings = new ArrayList();
      }

      Iterator i$ = ((List)ignoredWarnings).iterator();

      while(i$.hasNext()) {
         String ignoredWarning = (String)i$.next();
         warnings.remove(NotificationWarning.valueOf(ignoredWarning.toUpperCase()));
      }

      boolean returnContent = false;
      if (warnings != null && warnings.size() != 0) {
         if ((ignoredWarnings == null || ((List)ignoredWarnings).size() == 0) && warnings.size() > 0) {
            Iterator i$ = warnings.iterator();

            while(i$.hasNext()) {
               NotificationWarning warning = (NotificationWarning)i$.next();
               LOG.info("Ignored warnings: {}", warning);
            }

            returnContent = true;
         }
      } else {
         returnContent = true;
      }

      return returnContent;
   }

   public static UnsealedData map(be.fgov.ehealth.etee.crypto.decrypt.UnsealedData data) {
      UnsealedData result = new UnsealedData();
      result.setContent(data.getContent());
      result.setAuthenticationCert(data.getAuthenticationCert());
      result.setSignature(data.getSignature());
      result.setSigningTime(data.getSigningTime());
      result.setSignatureCert(data.getSignatureCert());
      return result;
   }

   private DataSealer getDataSealer(Crypto.SigningPolicySelector selector) throws TechnicalConnectorException {
      if (this.dataSealer == null) {
         TechnicalConnectorExceptionValues errorValue = TechnicalConnectorExceptionValues.ERROR_CRYPTO;
         String param = "Data Sealer not loaded";
         if (LOG.isDebugEnabled()) {
            LOG.debug(MessageFormat.format(errorValue.getMessage(), param));
         }

         throw new TechnicalConnectorException(errorValue, new Object[]{param});
      } else {
         return (DataSealer)this.dataSealer.get(selector);
      }
   }

   private DataUnsealer getDataUnsealer(Crypto.SigningPolicySelector selector) throws TechnicalConnectorException {
      if (this.dataUnsealer == null) {
         TechnicalConnectorExceptionValues errorValue = TechnicalConnectorExceptionValues.ERROR_CRYPTO;
         String param = "Data Sealer not loaded";
         if (LOG.isDebugEnabled()) {
            LOG.debug(MessageFormat.format(errorValue.getMessage(), param));
         }

         throw new TechnicalConnectorException(errorValue, new Object[]{param});
      } else {
         return (DataUnsealer)this.dataUnsealer.get(selector);
      }
   }

   public void initialize(Map<String, Object> parameterMap) throws TechnicalConnectorException {
      Credential encryption = (Credential)extract("datasealer.credential", parameterMap, (Object)null, Credential.class);
      Map<String, PrivateKey> decryptionKeys = (Map)extract("dataunsealer.pkmap", parameterMap, (Object)null, Map.class);
      Map<OCSPOption, Object> ocspOptionMap = (Map)extract("cryptolib.ocsp.optionmap", parameterMap, new HashMap(), Map.class);
      Map<SigningOption, Object> signingOptionMap = (Map)extract("cryptolib.signing.optionmap", parameterMap, new HashMap(), Map.class);
      OCSPPolicy oscpPolicy = (OCSPPolicy)extract("cryptolib.ocsp.policy", parameterMap, OCSPPolicy.RECEIVER_MANDATORY, OCSPPolicy.class);
      this.initSealing(Crypto.SigningPolicySelector.WITH_NON_REPUDIATION, encryption, oscpPolicy, ocspOptionMap);
      this.initSealing(Crypto.SigningPolicySelector.WITHOUT_NON_REPUDIATION, encryption, oscpPolicy, ocspOptionMap);
      this.initUnsealing(Crypto.SigningPolicySelector.WITH_NON_REPUDIATION, decryptionKeys, oscpPolicy, ocspOptionMap, signingOptionMap, SigningPolicy.EHEALTH_CERT, SigningPolicy.EID);
      this.initUnsealing(Crypto.SigningPolicySelector.WITHOUT_NON_REPUDIATION, decryptionKeys, oscpPolicy, ocspOptionMap, signingOptionMap, SigningPolicy.EHEALTH_CERT, SigningPolicy.EID);
   }

   private static <T> T extract(String key, Map<String, Object> parameters, T defaultValue, Class<T> clazz) throws TechnicalConnectorException {
      Object value = parameters.get(key);
      if (value == null) {
         if (defaultValue == null) {
            throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.CORE_TECHNICAL, new Object[]{"could not build initialize " + clazz + " with initialize, wrong input parameters : expected property " + key + " but got null."});
         } else {
            return defaultValue;
         }
      } else {
         return value;
      }
   }

   private void initSealing(Crypto.SigningPolicySelector type, Credential encryption, OCSPPolicy ocspPolicy, Map<OCSPOption, Object> ocspOptionMap) throws TechnicalConnectorException {
      SigningPolicy policy;
      SigningCredential outerSigningCred;
      SigningCredential innerSigningcredential;
      if (encryption instanceof BeIDCredential) {
         policy = SigningPolicy.EID;
         KeyStore eid = encryption.getKeyStore();
         outerSigningCred = this.retrieveSigningCredential("Authentication", eid);
         switch(type) {
         case WITH_NON_REPUDIATION:
            innerSigningcredential = this.retrieveSigningCredential("Signature", eid);
            break;
         case WITHOUT_NON_REPUDIATION:
            innerSigningcredential = outerSigningCred;
            break;
         default:
            throw new IllegalArgumentException("Unsupported SigningPolicyType [ " + type + "]");
         }
      } else {
         policy = SigningPolicy.EHEALTH_CERT;
         outerSigningCred = SigningCredential.create(encryption.getPrivateKey(), (X509Certificate[])Arrays.copyOf(encryption.getCertificateChain(), encryption.getCertificateChain().length, X509Certificate[].class));
         innerSigningcredential = outerSigningCred;
      }

      this.dataSealer.put(type, DataSealerBuilder.newBuilder().addOCSPPolicy(ocspPolicy, ocspOptionMap).addSigningPolicy(policy, outerSigningCred, innerSigningcredential).addPublicKeyPolicy(EncryptionPolicy.KNOWN_RECIPIENT).addSecretKeyPolicy(EncryptionPolicy.UNKNOWN_RECIPIENT).build());
   }

   private SigningCredential retrieveSigningCredential(String alias, KeyStore keyStore) {
      try {
         Certificate[] c = keyStore.getCertificateChain(alias);
         if (ArrayUtils.isEmpty(c)) {
            throw new IllegalArgumentException("The KeyStore doesn't contain the required key with alias [" + alias + "]");
         } else {
            X509Certificate[] certificateChain = (X509Certificate[])Arrays.copyOf(c, c.length, X509Certificate[].class);
            PrivateKey privateKey = (PrivateKey)keyStore.getKey(alias, (char[])null);
            return SigningCredential.create(privateKey, Iterables.newList(certificateChain));
         }
      } catch (KeyStoreException var6) {
         throw new IllegalArgumentException("Given keystore hasn't been initialized", var6);
      } catch (NoSuchAlgorithmException var7) {
         throw new IllegalStateException("There is a problem with the Security configuration... Check if all the required security providers are correctly registered", var7);
      } catch (UnrecoverableKeyException var8) {
         throw new IllegalStateException("The private key with alias [" + alias + "] could not be recovered from the given keystore", var8);
      }
   }

   private void initUnsealing(Crypto.SigningPolicySelector type, Map<String, PrivateKey> decryptionKeys, OCSPPolicy ocspPolicy, Map<OCSPOption, Object> ocspOptionMap, Map<SigningOption, Object> signingOptionMap, SigningPolicy... policies) throws TechnicalConnectorException {
      if (LOG.isDebugEnabled()) {
         Iterator i$ = decryptionKeys.keySet().iterator();

         while(i$.hasNext()) {
            String key = (String)i$.next();
            LOG.debug("Key Available for decryption : {}", key);
         }
      }

      Map<SigningOption, Object> clonedSigningOptionMap = new EnumMap(SigningOption.class);
      clonedSigningOptionMap.putAll(signingOptionMap);
      switch(type) {
      case WITH_NON_REPUDIATION:
         clonedSigningOptionMap.put(SigningOption.NON_REPUDIATION, Boolean.TRUE);
         break;
      case WITHOUT_NON_REPUDIATION:
         clonedSigningOptionMap.put(SigningOption.NON_REPUDIATION, Boolean.FALSE);
         break;
      default:
         throw new IllegalArgumentException("Unsupported SigningPolicyType [ " + type + "]");
      }

      this.dataUnsealer.put(type, DataUnsealerBuilder.newBuilder().addOCSPPolicy(ocspPolicy, ocspOptionMap).addSigningPolicy((KeyStore)ocspOptionMap.get(OCSPOption.TRUST_STORE), clonedSigningOptionMap, policies).addPublicKeyPolicy(EncryptionPolicy.KNOWN_RECIPIENT, EncryptionCredentials.from(decryptionKeys)).addSecretKeyPolicy(EncryptionPolicy.UNKNOWN_RECIPIENT).build());
   }
}
