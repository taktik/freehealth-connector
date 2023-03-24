package org.taktik.connector.technical.handler.wss4j;

import org.taktik.connector.technical.config.ConfigFactory;
import org.taktik.connector.technical.config.ConfigValidator;
import org.taktik.connector.technical.config.domain.Duration;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.exception.TechnicalConnectorExceptionValues;
import org.taktik.connector.technical.handler.AbstractWsSecurityHandler;
import org.taktik.connector.technical.handler.utils.WSSecurityCrypto;
import org.taktik.connector.technical.service.sts.security.Credential;
import org.taktik.connector.technical.service.sts.security.SAMLToken;
import org.taktik.connector.technical.service.sts.security.impl.SAMLHolderOfKeyToken;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.xml.crypto.dsig.Reference;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import javax.xml.ws.handler.soap.SOAPMessageContext;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;
import org.apache.wss4j.common.WSEncryptionPart;
import org.apache.wss4j.common.crypto.Crypto;
import org.apache.wss4j.common.ext.WSSecurityException;
import org.apache.wss4j.dom.SOAPConstants;
import org.apache.wss4j.dom.WSSConfig;
import org.apache.wss4j.dom.message.WSSecHeader;
import org.apache.wss4j.dom.message.WSSecSignature;
import org.apache.wss4j.dom.message.WSSecTimestamp;
import org.apache.wss4j.dom.util.WSSecurityUtil;
import org.w3c.dom.Element;

public class WSSecHeaderGeneratorWss4jImpl implements AbstractWsSecurityHandler.WSSecHeaderGeneratorStep0, AbstractWsSecurityHandler.WSSecHeaderGeneratorStep1, AbstractWsSecurityHandler.WSSecHeaderGeneratorStep2, AbstractWsSecurityHandler.WSSecHeaderGeneratorStep3, AbstractWsSecurityHandler.WSSecHeaderGeneratorStep4 {
   private SOAPPart soapPart;
   private WSSecHeader wsSecHeader;
   private WSSecSignature sign;
   private WSSecTimestamp wsSecTimeStamp;
   private String assertionId;
   private Credential cred;
   private SOAPMessageContext ctx;
   private ConfigValidator config = ConfigFactory.getConfigValidator();

   public AbstractWsSecurityHandler.WSSecHeaderGeneratorStep1 on(SOAPMessage message) throws TechnicalConnectorException {
      try {
         Validate.notNull(message);
         this.soapPart = message.getSOAPPart();
         this.wsSecHeader = new WSSecHeader();
         this.wsSecHeader.insertSecurityHeader(this.soapPart);
         WSSConfig config = WSSConfig.getNewInstance();
         config.setAddInclusivePrefixes(false);
         this.sign = new WSSecSignature(config);
         return this;
      } catch (WSSecurityException var3) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.HANDLER_ERROR, new Object[]{"unable to insert security header.", var3});
      }
   }

   public AbstractWsSecurityHandler.WSSecHeaderGeneratorStep1 on(SOAPMessageContext ctx) throws TechnicalConnectorException {
      Validate.notNull(ctx);
      this.ctx = ctx;
      return this.on(ctx.getMessage());
   }

   public AbstractWsSecurityHandler.WSSecHeaderGeneratorStep2 withTimeStamp(long ttl, TimeUnit unit) {
      this.withTimeStamp(new Duration(ttl, unit));
      return this;
   }

   public AbstractWsSecurityHandler.WSSecHeaderGeneratorStep2 withTimeStamp(Duration duration) {
      this.wsSecTimeStamp = new WSSecTimestamp();
      this.wsSecTimeStamp.setTimeToLive((int)duration.convert(TimeUnit.SECONDS));
      this.wsSecTimeStamp.build(this.soapPart, this.wsSecHeader);
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
      Element securityHeaderElement = this.wsSecHeader.getSecurityHeader();
      securityHeaderElement.appendChild(importedAssertionElement);
      this.assertionId = assertionElement.getAttribute("AssertionID");
      return this;
   }

   public void sign(AbstractWsSecurityHandler.SignedParts... parts) throws TechnicalConnectorException {
      try {
         if (this.cred instanceof SAMLHolderOfKeyToken && StringUtils.isNotEmpty(this.assertionId)) {
            this.sign.setSignatureAlgorithm("http://www.w3.org/2001/04/xmldsig-more#rsa-sha256");
            this.sign.setKeyIdentifierType(12);
            this.sign.setCustomTokenValueType("http://docs.oasis-open.org/wss/oasis-wss-saml-token-profile-1.0#SAMLAssertionID");
            this.sign.setCustomTokenId(this.assertionId);
         } else {
            this.sign.setKeyIdentifierType(1);
         }

         this.determineSignatureAlgorithm();
         this.determineDigestAlgo();
         Crypto crypto = new WSSecurityCrypto(this.cred.getPrivateKey(), this.cred.getCertificate());
         this.sign.prepare(this.soapPart, crypto, this.wsSecHeader);
         if (!(this.cred instanceof SAMLHolderOfKeyToken) || !StringUtils.isNotEmpty(this.assertionId)) {
            this.sign.appendBSTElementToHeader(this.wsSecHeader);
         }

         List<Reference> referenceList = this.sign.addReferencesToSign(this.generateReferencesToSign(parts), this.wsSecHeader);
         if (!referenceList.isEmpty()) {
            this.sign.computeSignature(referenceList, false, (Element)null);
         }

      } catch (WSSecurityException var4) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.HANDLER_ERROR, "unable to insert security header.", var4);
      }
   }

   private void determineDigestAlgo() {
      if (this.ctx != null && StringUtils.isNotBlank((String)this.ctx.get("digest.method.algorithm"))) {
         this.sign.setDigestAlgo((String)this.ctx.get("digest.method.algorithm"));
      } else if (this.ctx != null && this.ctx.get("javax.xml.ws.soap.http.soapaction.uri") != null && ((String) this.ctx.get("javax.xml.ws.soap.http.soapaction.uri")).contains(":recipe:")) {
         this.sign.setDigestAlgo(this.config.getProperty("default.digest.method.algorithm", "http://www.w3.org/2000/09/xmldsig#sha1"));
      } else {
         this.sign.setDigestAlgo(this.config.getProperty("default.digest.method.algorithm", "http://www.w3.org/2001/04/xmlenc#sha256"));
      }
   }

   private void determineSignatureAlgorithm() {
      if (this.ctx != null && StringUtils.isNotBlank((String)this.ctx.get("signature.method.algorithm"))) {
         this.sign.setSignatureAlgorithm((String)this.ctx.get("signature.method.algorithm"));
      } else if (this.ctx != null && this.ctx.get("javax.xml.ws.soap.http.soapaction.uri") != null && ((String) this.ctx.get("javax.xml.ws.soap.http.soapaction.uri")).contains(":recipe:")) {
         this.sign.setSignatureAlgorithm(this.config.getProperty("default.signature.method.algorithm", "http://www.w3.org/2000/09/xmldsig#rsa-sha1"));
      } else {
         this.sign.setSignatureAlgorithm(this.config.getProperty("default.signature.method.algorithm", "http://www.w3.org/2001/04/xmldsig-more#rsa-sha256"));
      }
   }

   protected List<WSEncryptionPart> generateReferencesToSign(AbstractWsSecurityHandler.SignedParts[] parts) {
      List<WSEncryptionPart> signParts = new ArrayList();
      AbstractWsSecurityHandler.SignedParts[] arr$ = parts;
      int len$ = parts.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         AbstractWsSecurityHandler.SignedParts part = arr$[i$];
         switch(part) {
         case TIMESTAMP:
            Validate.notNull(this.wsSecTimeStamp);
            signParts.add(new WSEncryptionPart(this.wsSecTimeStamp.getId()));
            break;
         case BODY:
            SOAPConstants soapConstants = WSSecurityUtil.getSOAPConstants(this.soapPart.getDocumentElement());
            signParts.add(new WSEncryptionPart(soapConstants.getBodyQName().getLocalPart(), soapConstants.getEnvelopeURI(), "Content"));
            break;
         case SAML_ASSERTION:
            Validate.notNull(this.assertionId);
            signParts.add(new WSEncryptionPart(this.assertionId));
            break;
         case BST:
            signParts.add(new WSEncryptionPart(this.sign.getBSTTokenId()));
         }
      }

      return signParts;
   }
}
