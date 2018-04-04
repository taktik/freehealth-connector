package org.taktik.connector.technical.handler;

import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.service.sts.security.SAMLToken;
import org.taktik.connector.technical.session.Session;
import java.util.concurrent.TimeUnit;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SAMLHolderOfKeyHandler extends AbstractWsSecurityHandler {
   private static final Logger LOG = LoggerFactory.getLogger(SAMLHolderOfKeyHandler.class);
   private SAMLToken token;

   public SAMLHolderOfKeyHandler() {
   }

   public SAMLHolderOfKeyHandler(SAMLToken token) {
      this.token = token;
   }

   protected void addWSSecurity(SOAPMessageContext context) throws TechnicalConnectorException {
      SAMLToken lazyToken = this.token;
      if (lazyToken == null) {
         LOG.debug("[Lazy Loading] Trying to load SAMLToken from session.");
         lazyToken = Session.getInstance().getSession().getSAMLToken();
      }

      this.buildSignature().on(context.getMessage()).withTimeStamp(60L, TimeUnit.SECONDS).withSAMLToken(lazyToken).sign(new AbstractWsSecurityHandler.SignedParts[]{AbstractWsSecurityHandler.SignedParts.BODY, AbstractWsSecurityHandler.SignedParts.TIMESTAMP});
   }

   protected Logger getLogger() {
      return LOG;
   }
}
