package org.taktik.connector.technical.handler;

import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.service.sts.SAMLTokenFactory;
import org.taktik.connector.technical.service.sts.security.SAMLToken;
import org.taktik.connector.technical.service.sts.security.impl.KeyPairCredential;
import org.taktik.connector.technical.service.sts.security.impl.SAMLSenderVouchesCredential;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Element;

public class SAMLSenderVouchesHandler extends AbstractWsSecurityHandler {
   private static final Logger LOG = LoggerFactory.getLogger(SAMLSenderVouchesHandler.class);
   private SAMLToken token;

   /** @deprecated */
   @Deprecated
   public SAMLSenderVouchesHandler(Element assertion, X509Certificate certificate, PrivateKey privateKey) throws TechnicalConnectorException {
      this.token = SAMLTokenFactory.getInstance().createSamlToken(assertion, new KeyPairCredential(privateKey, certificate));
   }

   public SAMLSenderVouchesHandler(SAMLSenderVouchesCredential token) {
      this.token = token;
   }

   protected void addWSSecurity(SOAPMessageContext context) throws TechnicalConnectorException {
      this.buildSignature().on(context.getMessage()).withTimeStamp(60L, TimeUnit.SECONDS).withBinarySecurityToken(this.token).withSAMLToken(this.token).sign(new AbstractWsSecurityHandler.SignedParts[]{AbstractWsSecurityHandler.SignedParts.BODY, AbstractWsSecurityHandler.SignedParts.TIMESTAMP});
   }

   protected Logger getLogger() {
      return LOG;
   }
}
