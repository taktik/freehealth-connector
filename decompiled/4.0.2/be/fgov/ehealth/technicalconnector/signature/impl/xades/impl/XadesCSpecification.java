package be.fgov.ehealth.technicalconnector.signature.impl.xades.impl;

import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorExceptionValues;
import be.ehealth.technicalconnector.service.sts.security.Credential;
import be.fgov.ehealth.etee.crypto.ocsp.OCSPCheckerBuilder;
import be.fgov.ehealth.etee.crypto.ocsp.OCSPData;
import be.fgov.ehealth.etee.crypto.policies.OCSPPolicy;
import be.fgov.ehealth.technicalconnector.signature.domain.SignatureVerificationResult;
import be.fgov.ehealth.technicalconnector.signature.impl.xades.domain.SignedPropertiesBuilder;
import be.fgov.ehealth.technicalconnector.signature.impl.xades.domain.UnsignedPropertiesBuilder;
import java.io.IOException;
import java.security.cert.X509CRL;
import java.security.cert.X509Certificate;
import java.util.Iterator;
import java.util.Map;
import org.apache.xml.security.signature.XMLSignature;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ocsp.BasicOCSPResponse;
import org.bouncycastle.cert.ocsp.BasicOCSPResp;
import org.bouncycastle.cert.ocsp.OCSPException;
import org.bouncycastle.cert.ocsp.OCSPRespBuilder;
import org.w3c.dom.Element;

public class XadesCSpecification implements be.fgov.ehealth.technicalconnector.signature.impl.xades.XadesSpecification {
   public XadesCSpecification() {
   }

   public void addOptionalBeforeSignatureParts(SignedPropertiesBuilder signedProps, XMLSignature sig, Credential signing, String uuid, Map<String, Object> options) throws TechnicalConnectorException {
   }

   public void addOptionalAfterSignatureParts(UnsignedPropertiesBuilder unsignedProps, XMLSignature sig, String uuid, Map<String, Object> options) throws TechnicalConnectorException {
      try {
         X509Certificate signing = sig.getKeyInfo().getX509Certificate();
         OCSPData ocsp = (OCSPData)OCSPCheckerBuilder.newBuilder().addOCSPPolicy(OCSPPolicy.RECEIVER_MANDATORY).build().validate(signing).getData();
         unsignedProps.addCertificate(signing);
         Iterator var7 = ocsp.getCrls().iterator();

         while(var7.hasNext()) {
            X509CRL crl = (X509CRL)var7.next();
            unsignedProps.addCrlRef(crl);
         }

         unsignedProps.addOCSPRef(this.convertToOCSPResp(ocsp));
      } catch (Exception var9) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_GENERAL, var9, new Object[]{"Unable to add optional Signature parts"});
      }
   }

   private byte[] convertToOCSPResp(OCSPData data) throws IOException, OCSPException {
      BasicOCSPResp basicResp = new BasicOCSPResp(BasicOCSPResponse.getInstance(ASN1Primitive.fromByteArray(data.getOcspResponse())));
      return (new OCSPRespBuilder()).build(0, basicResp).getEncoded();
   }

   public void verify(SignatureVerificationResult result, Element sigElement) {
   }
}
