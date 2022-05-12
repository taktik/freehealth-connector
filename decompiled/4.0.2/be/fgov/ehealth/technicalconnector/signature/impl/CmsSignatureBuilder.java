package be.fgov.ehealth.technicalconnector.signature.impl;

import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorExceptionValues;
import be.ehealth.technicalconnector.service.sts.security.Credential;
import be.fgov.ehealth.technicalconnector.signature.AdvancedElectronicSignatureEnumeration;
import be.fgov.ehealth.technicalconnector.signature.SignatureBuilder;
import be.fgov.ehealth.technicalconnector.signature.domain.SignatureVerificationError;
import be.fgov.ehealth.technicalconnector.signature.domain.SignatureVerificationResult;
import java.io.IOException;
import java.security.Security;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.apache.commons.lang3.ArrayUtils;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.jcajce.JcaCertStore;
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;
import org.bouncycastle.cms.CMSAttributeTableGenerator;
import org.bouncycastle.cms.CMSException;
import org.bouncycastle.cms.CMSProcessableByteArray;
import org.bouncycastle.cms.CMSSignedData;
import org.bouncycastle.cms.CMSSignedDataGenerator;
import org.bouncycastle.cms.CMSTypedData;
import org.bouncycastle.cms.DefaultSignedAttributeTableGenerator;
import org.bouncycastle.cms.SignerInformation;
import org.bouncycastle.cms.jcajce.JcaSignerInfoGeneratorBuilder;
import org.bouncycastle.cms.jcajce.JcaSimpleSignerInfoVerifierBuilder;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;
import org.bouncycastle.operator.jcajce.JcaDigestCalculatorProviderBuilder;
import org.bouncycastle.util.Selector;
import org.bouncycastle.util.Store;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class CmsSignatureBuilder extends AbstractSignatureBuilder implements SignatureBuilder {
   private static final String MSG_VERIFY_FAILED = "Unable to verify signature";
   private static final Logger LOG = LoggerFactory.getLogger(CmsSignatureBuilder.class);
   private static JcaX509CertificateConverter converter = new JcaX509CertificateConverter();
   private static JcaSimpleSignerInfoVerifierBuilder verifierBuilder = new JcaSimpleSignerInfoVerifierBuilder();
   private AdvancedElectronicSignatureEnumeration aes;

   public CmsSignatureBuilder(AdvancedElectronicSignatureEnumeration aes) {
      this.aes = aes;
   }

   public AdvancedElectronicSignatureEnumeration getSupportedAES() {
      return this.aes;
   }

   public SignatureVerificationResult verify(byte[] content, byte[] signature, Map<String, Object> options) throws TechnicalConnectorException {
      SignatureVerificationResult result = new SignatureVerificationResult();

      try {
         CMSSignedData signedContent = new CMSSignedData(signature);
         byte[] signedData;
         if (signedContent.getSignedContent() == null) {
            LOG.info("Signature has no ecapsulated signature. Adding content.");
            signedData = (new CMSSignedData(new CMSProcessableByteArray(content), signature)).getEncoded();
         } else {
            signedData = ArrayUtils.clone(signature);
         }

         return this.verify(signedData, options);
      } catch (CMSException var7) {
         LOG.error("Unable to verify signature", var7);
         result.getErrors().add(SignatureVerificationError.SIGNATURE_COULD_NOT_BE_VERIFIED);
      } catch (IOException var8) {
         LOG.error("Unable to verify signature", var8);
         result.getErrors().add(SignatureVerificationError.SIGNATURE_COULD_NOT_BE_VERIFIED);
      }

      return result;
   }

   public SignatureVerificationResult verify(Document signedContent, Element sigElement, Map<String, Object> options) throws TechnicalConnectorException {
      throw new UnsupportedOperationException();
   }

   public SignatureVerificationResult verify(byte[] signedByteArray, Map<String, Object> options) throws TechnicalConnectorException {
      SignatureVerificationResult result = new SignatureVerificationResult();

      try {
         CMSSignedData signedData = new CMSSignedData(signedByteArray);
         this.extractChain(result, signedData);
         this.validateChain(result, options);
         Iterator<SignerInformation> signerInfos = signedData.getSignerInfos().iterator();

         while(signerInfos.hasNext()) {
            SignerInformation signer = (SignerInformation)signerInfos.next();
            if (!signer.verify(verifierBuilder.build(result.getSigningCert().getPublicKey()))) {
               result.getErrors().add(SignatureVerificationError.SIGNATURE_COULD_NOT_BE_VERIFIED);
            }
         }
      } catch (Exception var7) {
         LOG.error("Unable to verify signature", var7);
         result.getErrors().add(SignatureVerificationError.SIGNATURE_COULD_NOT_BE_VERIFIED);
      }

      return result;
   }

   public byte[] sign(Credential signatureCredential, byte[] byteArrayToSign) throws TechnicalConnectorException {
      return this.sign(signatureCredential, byteArrayToSign, (Map)null);
   }

   public byte[] sign(Credential signatureCredential, byte[] byteToSign, Map<String, Object> options) throws TechnicalConnectorException {
      byte[] contentToSign = ArrayUtils.clone(byteToSign);
      Map<String, Object> optionMap = new HashMap();
      if (options != null) {
         optionMap.putAll(options);
      }

      this.validateInput(signatureCredential, contentToSign);

      try {
         CMSTypedData content = new CMSProcessableByteArray(contentToSign);
         CMSSignedDataGenerator generator = new CMSSignedDataGenerator();
         String signatureAlgorithm = (String)SignatureUtils.getOption("signatureAlgorithm", optionMap, "Sha1WithRSA");
         JcaSignerInfoGeneratorBuilder signerInfoGeneratorBuilder = new JcaSignerInfoGeneratorBuilder((new JcaDigestCalculatorProviderBuilder()).build());
         ContentSigner contentSigner = (new JcaContentSignerBuilder(signatureAlgorithm)).build(signatureCredential.getPrivateKey());
         CMSAttributeTableGenerator cmsAttributeTableGenerator = (CMSAttributeTableGenerator)SignatureUtils.getOption("signedAttributeGenerator", optionMap, new DefaultSignedAttributeTableGenerator());
         signerInfoGeneratorBuilder.setSignedAttributeGenerator(cmsAttributeTableGenerator);
         generator.addSignerInfoGenerator(signerInfoGeneratorBuilder.build(contentSigner, signatureCredential.getCertificate()));
         Certificate[] certificateChain = signatureCredential.getCertificateChain();
         if (certificateChain != null && certificateChain.length > 0) {
            generator.addCertificates(new JcaCertStore(Arrays.asList(certificateChain)));
         }

         boolean encapsulate = (Boolean)SignatureUtils.getOption("encapsulate", optionMap, Boolean.FALSE);
         return generator.generate(content, encapsulate).getEncoded();
      } catch (Exception var14) {
         LOG.error(var14.getMessage(), var14);
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_SIGNATURE, var14, new Object[]{var14.getClass().getSimpleName() + " : " + var14.getMessage()});
      }
   }

   private void extractChain(SignatureVerificationResult result, CMSSignedData signedData) throws CertificateException {
      Store<X509CertificateHolder> certs = signedData.getCertificates();
      Collection<X509CertificateHolder> certCollection = certs.getMatches(new X509CertifcateSelector());
      Iterator<X509CertificateHolder> iterator = certCollection.iterator();

      while(iterator.hasNext()) {
         result.getCertChain().add(converter.getCertificate((X509CertificateHolder)iterator.next()));
      }

   }

   static {
      Security.addProvider(new BouncyCastleProvider());
   }

   private static class X509CertifcateSelector implements Selector<X509CertificateHolder> {
      private X509CertifcateSelector() {
      }

      public boolean match(X509CertificateHolder cert) {
         return true;
      }

      public Object clone() {
         return new X509CertifcateSelector();
      }
   }
}
