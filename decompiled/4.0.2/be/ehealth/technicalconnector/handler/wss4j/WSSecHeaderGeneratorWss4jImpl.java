package be.ehealth.technicalconnector.handler.wss4j;

import be.ehealth.technicalconnector.config.ConfigFactory;
import be.ehealth.technicalconnector.config.ConfigValidator;
import be.ehealth.technicalconnector.config.domain.Duration;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorExceptionValues;
import be.ehealth.technicalconnector.handler.AbstractWsSecurityHandler;
import be.ehealth.technicalconnector.handler.utils.WSSecurityCrypto;
import be.ehealth.technicalconnector.service.sts.security.Credential;
import be.ehealth.technicalconnector.service.sts.security.SAMLToken;
import be.ehealth.technicalconnector.service.sts.security.impl.SAMLHolderOfKeyToken;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.xml.crypto.dsig.Reference;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import org.apache.commons.lang.Validate;
import org.apache.commons.lang3.StringUtils;
import org.apache.wss4j.common.WSEncryptionPart;
import org.apache.wss4j.common.crypto.Crypto;
import org.apache.wss4j.common.ext.WSSecurityException;
import org.apache.wss4j.dom.SOAPConstants;
import org.apache.wss4j.dom.message.WSSecHeader;
import org.apache.wss4j.dom.message.WSSecSignature;
import org.apache.wss4j.dom.message.WSSecTimestamp;
import org.apache.wss4j.dom.util.WSSecurityUtil;
import org.w3c.dom.Element;

public class WSSecHeaderGeneratorWss4jImpl implements AbstractWsSecurityHandler.WSSecHeaderGeneratorStep0, AbstractWsSecurityHandler.WSSecHeaderGeneratorStep1, AbstractWsSecurityHandler.WSSecHeaderGeneratorStep2, AbstractWsSecurityHandler.WSSecHeaderGeneratorStep3, AbstractWsSecurityHandler.WSSecHeaderGeneratorStep4 {
   public static final String DEFAULT_DIGEST_METHOD_ALGORITHM = "default.digest.method.algorithm";
   public static final String DEFAULT_SIGNATURE_METHOD_ALGORITHM = "default.signature.method.algorithm";
   public static final String WSSEC_MUSTUNDERSTAND = "be.ehealth.technicalconnector.handler.wss4j.WSSecHeaderGeneratorWss4jImpl.mustUnderstand";
   private SOAPPart soapPart;
   private WSSecHeader wsSecHeader;
   private WSSecSignature sign;
   private WSSecTimestamp wsSecTimeStamp;
   private String assertionId;
   private Credential cred;
   private SOAPMessageContext ctx;
   private ConfigValidator config = ConfigFactory.getConfigValidator();

   public WSSecHeaderGeneratorWss4jImpl() {
   }

   public AbstractWsSecurityHandler.WSSecHeaderGeneratorStep1 on(SOAPMessage message) throws TechnicalConnectorException {
      try {
         Validate.notNull(message);
         this.soapPart = message.getSOAPPart();
         this.wsSecHeader = new WSSecHeader(this.soapPart);
         this.wsSecHeader.insertSecurityHeader();
         this.wsSecHeader.setMustUnderstand(Boolean.valueOf(this.config.getProperty("be.ehealth.technicalconnector.handler.wss4j.WSSecHeaderGeneratorWss4jImpl.mustUnderstand", "true")));
         this.sign = new WSSecSignature(this.wsSecHeader);
         this.sign.setAddInclusivePrefixes(false);
         return this;
      } catch (WSSecurityException var3) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.HANDLER_ERROR, new Object[]{"unable to insert security header.", var3});
      }
   }

   public AbstractWsSecurityHandler.WSSecHeaderGeneratorStep1 on(SOAPMessageContext ctx) throws TechnicalConnectorException {
      org.apache.commons.lang3.Validate.notNull(ctx);
      this.ctx = ctx;
      return this.on(ctx.getMessage());
   }

   public AbstractWsSecurityHandler.WSSecHeaderGeneratorStep2 withTimeStamp(long ttl, TimeUnit unit) {
      this.withTimeStamp(new Duration(ttl, unit));
      return this;
   }

   public AbstractWsSecurityHandler.WSSecHeaderGeneratorStep2 withTimeStamp(Duration duration) {
      this.wsSecTimeStamp = new WSSecTimestamp(this.wsSecHeader);
      this.wsSecTimeStamp.setTimeToLive((int)duration.convert(TimeUnit.SECONDS));
      this.wsSecTimeStamp.build();
      return this;
   }

   public AbstractWsSecurityHandler.WSSecHeaderGeneratorStep3 withBinarySecurityToken(Credential cred) throws TechnicalConnectorException {
      this.cred = cred;
      return this;
   }

   public AbstractWsSecurityHandler.WSSecHeaderGeneratorStep3 withSAMLToken(SAMLToken token) throws TechnicalConnectorException {
      this.cred = token;
      Element assertionElement = token.getAssertion();
      Element importedAssertionElement = (Element)this.soapPart.importNode(assertionElement, true);
      Element securityHeaderElement = this.wsSecHeader.getSecurityHeaderElement();
      securityHeaderElement.appendChild(importedAssertionElement);
      this.assertionId = assertionElement.getAttribute("AssertionID");
      return this;
   }

   public void sign(AbstractWsSecurityHandler.SignedParts... parts) throws TechnicalConnectorException {
      try {
         if (this.cred instanceof SAMLHolderOfKeyToken && StringUtils.isNotEmpty(this.assertionId)) {
            this.sign.setKeyIdentifierType(12);
            this.sign.setCustomTokenValueType("http://docs.oasis-open.org/wss/oasis-wss-saml-token-profile-1.0#SAMLAssertionID");
            this.sign.setCustomTokenId(this.assertionId);
         } else {
            this.sign.setKeyIdentifierType(1);
         }

         AlgorithmHelper algorithmHelper = AlgorithmHelperFactory.getAlgorithmHelper(this.cred);
         this.determineSignatureAlgorithm(algorithmHelper, this.cred);
         this.determineDigestAlgo(algorithmHelper, this.cred);
         Crypto crypto = new WSSecurityCrypto(this.cred.getPrivateKey(), this.cred.getCertificate());
         this.sign.prepare(crypto);
         if (!(this.cred instanceof SAMLHolderOfKeyToken) || !StringUtils.isNotEmpty(this.assertionId)) {
            this.sign.appendBSTElementToHeader();
         }

         List<Reference> referenceList = this.sign.addReferencesToSign(this.generateReferencesToSign(parts));
         if (!referenceList.isEmpty()) {
            this.sign.computeSignature(referenceList, false, (Element)null);
         }

      } catch (WSSecurityException var5) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.HANDLER_ERROR, new Object[]{"unable to insert security header.", var5});
      }
   }

   private void determineDigestAlgo(AlgorithmHelper helper, Credential cred) {
      if (this.ctx != null && StringUtils.isNotBlank((String)this.ctx.get("digest.method.algorithm"))) {
         this.sign.setDigestAlgo((String)this.ctx.get("digest.method.algorithm"));
      } else {
         this.sign.setDigestAlgo(helper.determineDigestAlgo(cred));
      }

   }

   private void determineSignatureAlgorithm(AlgorithmHelper helper, Credential cred) throws TechnicalConnectorException {
      if (this.ctx != null && StringUtils.isNotBlank((String)this.ctx.get("signature.method.algorithm"))) {
         this.sign.setSignatureAlgorithm((String)this.ctx.get("signature.method.algorithm"));
      } else {
         this.sign.setSignatureAlgorithm(helper.determineSignatureAlgorithm(cred));
      }

   }

   protected List<WSEncryptionPart> generateReferencesToSign(AbstractWsSecurityHandler.SignedParts[] parts) {
      List<WSEncryptionPart> signParts = new ArrayList();
      AbstractWsSecurityHandler.SignedParts[] var3 = parts;
      int var4 = parts.length;

      for(int var5 = 0; var5 < var4; ++var5) {
         AbstractWsSecurityHandler.SignedParts part = var3[var5];
         switch (part) {
            case TIMESTAMP:
               org.apache.commons.lang3.Validate.notNull(this.wsSecTimeStamp);
               signParts.add(new WSEncryptionPart(this.wsSecTimeStamp.getId()));
               break;
            case BODY:
               SOAPConstants soapConstants = WSSecurityUtil.getSOAPConstants(this.soapPart.getDocumentElement());
               signParts.add(new WSEncryptionPart(soapConstants.getBodyQName().getLocalPart(), soapConstants.getEnvelopeURI(), "Content"));
               break;
            case SAML_ASSERTION:
               org.apache.commons.lang3.Validate.notNull(this.assertionId);
               signParts.add(new WSEncryptionPart(this.assertionId));
               break;
            case BST:
               signParts.add(new WSEncryptionPart(this.sign.getBSTTokenId()));
         }
      }

      return signParts;
   }
}
