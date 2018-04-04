package be.fgov.ehealth.technicalconnector.signature.impl.xades.impl;

import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.service.sts.security.Credential;
import be.fgov.ehealth.technicalconnector.signature.domain.SignatureVerificationError;
import be.fgov.ehealth.technicalconnector.signature.domain.SignatureVerificationResult;
import be.fgov.ehealth.technicalconnector.signature.impl.DomUtils;
import be.fgov.ehealth.technicalconnector.signature.impl.SignatureUtils;
import be.fgov.ehealth.technicalconnector.signature.impl.xades.XadesSpecification;
import be.fgov.ehealth.technicalconnector.signature.impl.xades.domain.SignedPropertiesBuilder;
import be.fgov.ehealth.technicalconnector.signature.impl.xades.domain.UnsignedPropertiesBuilder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateExpiredException;
import java.security.cert.CertificateNotYetValidException;
import java.security.cert.X509Certificate;
import java.util.Map;
import javax.security.auth.x500.X500Principal;
import org.apache.xml.security.signature.XMLSignature;
import org.bouncycastle.util.encoders.Base64;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class XadesBesSpecification implements XadesSpecification {
   private static final Logger LOG = LoggerFactory.getLogger(XadesBesSpecification.class);

   public void addOptionalBeforeSignatureParts(SignedPropertiesBuilder signedProps, XMLSignature sig, Credential signing, String uuid, Map<String, Object> options) throws TechnicalConnectorException {
      signedProps.setId(uuid);
      signedProps.setSigningCert(signing.getCertificate());
      signedProps.setSigningTime(new DateTime());
   }

   public void addOptionalAfterSignatureParts(UnsignedPropertiesBuilder unsignedProps, XMLSignature sig, String uuid, Map<String, Object> options) throws TechnicalConnectorException {
   }

   public void verify(SignatureVerificationResult result, Element sigElement) {
      this.verifySigningTime(result, sigElement);
      this.verifySigningCertificate(result, sigElement);
   }

   private void verifySigningTime(SignatureVerificationResult result, Element sigElement) {
      NodeList signingTime = DomUtils.getMatchingChilds(sigElement, "http://uri.etsi.org/01903/v1.3.2#", "SigningTime");
      if (signingTime != null && signingTime.getLength() == 1) {
         try {
            result.setSigningTime(new DateTime(((Element)signingTime.item(0)).getTextContent()));
         } catch (IllegalArgumentException var5) {
            LOG.error("Invalid signing time", var5);
            result.getErrors().add(SignatureVerificationError.XADES_SIGNEDPROPS_INVALID_SIGNINGTIME);
         }
      } else {
         result.getErrors().add(SignatureVerificationError.XADES_SIGNEDPROPS_DONT_HAVE_SIGNINGTIME);
      }

   }

   private void verifySigningCertificate(SignatureVerificationResult result, Element sigElement) {
      if (result.getSigningCert() == null) {
         LOG.debug("Unable to obtain signing certificate.");
         result.getErrors().add(SignatureVerificationError.XADES_SIGNEDPROPS_COULD_NOT_BE_VERIFIED);
      } else {
         NodeList signingCertificateList = DomUtils.getMatchingChilds(sigElement, "http://uri.etsi.org/01903/v1.3.2#", "SigningCertificate");
         if (signingCertificateList != null && signingCertificateList.getLength() == 1) {
            Element certEl = (Element)signingCertificateList.item(0);
            this.verifyDigest(result, certEl);
            this.verifyIssuerName(result, certEl);
            this.verifySerialNumber(result, certEl);
            this.verifyValidity(result);
         } else {
            result.getErrors().add(SignatureVerificationError.XADES_SIGNEDPROPS_NOT_VALID);
         }

      }
   }

   private void verifyValidity(SignatureVerificationResult result) {
      try {
         result.getSigningCert().checkValidity();
      } catch (CertificateExpiredException var3) {
         LOG.error("Signing certificate expired.", var3);
         result.getErrors().add(SignatureVerificationError.CERTIFICATE_EXPIRED);
      } catch (CertificateNotYetValidException var4) {
         LOG.error("Signing certificate not yet valid.", var4);
         result.getErrors().add(SignatureVerificationError.CERTIFICATE_NOT_YET_VALID);
      }

   }

   private void verifyDigest(SignatureVerificationResult result, Element certEl) {
      X509Certificate signingCert = result.getSigningCert();
      String digestMethod = ((Element)certEl.getElementsByTagNameNS("http://www.w3.org/2000/09/xmldsig#", "DigestMethod").item(0)).getAttribute("Algorithm");
      String digestValue = certEl.getElementsByTagNameNS("http://www.w3.org/2000/09/xmldsig#", "DigestValue").item(0).getTextContent();

      try {
         MessageDigest messageDigest = SignatureUtils.getDigestInstance(digestMethod);
         messageDigest.reset();
         byte[] calculatedDigest = messageDigest.digest(signingCert.getEncoded());
         if (!MessageDigest.isEqual(calculatedDigest, Base64.decode(digestValue))) {
            result.getErrors().add(SignatureVerificationError.XADES_SIGNEDPROPS_NOT_VALID);
         }
      } catch (CertificateEncodingException var8) {
         LOG.warn("Unable to encode certificate with CN [{}] Reason: {}", new Object[]{signingCert.getSubjectX500Principal().getName("RFC1779"), var8.getMessage(), var8});
         result.getErrors().add(SignatureVerificationError.XADES_SIGNEDPROPS_COULD_NOT_BE_VERIFIED);
      } catch (NoSuchAlgorithmException var9) {
         LOG.error("Invalid digest method [{}]", digestMethod, var9);
         result.getErrors().add(SignatureVerificationError.XADES_SIGNEDPROPS_NOT_VALID);
      }

   }

   private void verifyIssuerName(SignatureVerificationResult result, Element certEl) {
      try {
         String x509IssuerName = ((Element)certEl.getElementsByTagNameNS("http://www.w3.org/2000/09/xmldsig#", "X509IssuerName").item(0)).getTextContent();
         X500Principal principal = new X500Principal(x509IssuerName);
         if (!principal.getName("RFC1779").equalsIgnoreCase(result.getSigningCert().getIssuerX500Principal().getName("RFC1779"))) {
            result.getErrors().add(SignatureVerificationError.XADES_SIGNEDPROPS_NOT_VALID);
         }
      } catch (Exception var5) {
         LOG.error("Unable to verify issuer name", var5);
         result.getErrors().add(SignatureVerificationError.XADES_SIGNEDPROPS_NOT_VALID);
      }

   }

   private void verifySerialNumber(SignatureVerificationResult result, Element certEl) {
      try {
         String x509SerialNumber = ((Element)certEl.getElementsByTagNameNS("http://www.w3.org/2000/09/xmldsig#", "X509SerialNumber").item(0)).getTextContent();
         if (!x509SerialNumber.equals(result.getSigningCert().getSerialNumber().toString())) {
            result.getErrors().add(SignatureVerificationError.XADES_SIGNEDPROPS_NOT_VALID);
         }
      } catch (Exception var4) {
         LOG.error("Unable to verify serial number", var4);
         result.getErrors().add(SignatureVerificationError.XADES_SIGNEDPROPS_NOT_VALID);
      }

   }
}
