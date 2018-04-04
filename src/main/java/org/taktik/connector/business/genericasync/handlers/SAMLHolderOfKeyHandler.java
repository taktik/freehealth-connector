package org.taktik.connector.business.genericasync.handlers;

import org.taktik.connector.technical.config.domain.Duration;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.handler.AbstractWsSecurityHandler;
import org.taktik.connector.technical.service.sts.security.SAMLToken;
import org.taktik.connector.technical.session.Session;
import java.util.concurrent.TimeUnit;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SAMLHolderOfKeyHandler extends AbstractWsSecurityHandler {
   private static final Logger LOG = LoggerFactory.getLogger(SAMLHolderOfKeyHandler.class);
   private SAMLToken token;
   private Duration duration;

   public SAMLHolderOfKeyHandler() {
      this((SAMLToken)null);
   }

   public SAMLHolderOfKeyHandler(SAMLToken token) {
      this(token, new Duration(60L, TimeUnit.SECONDS));
   }

   public SAMLHolderOfKeyHandler(SAMLToken token, Duration duration) {
      this.token = token;
      this.duration = duration;
   }

   protected void addWSSecurity(SOAPMessageContext context) throws TechnicalConnectorException {
      SAMLToken lazyToken = this.token;
      if (lazyToken == null) {
         LOG.debug("[Lazy Loading] Trying to load SAMLToken from session.");
         lazyToken = Session.getInstance().getSession().getSAMLToken();
      }

      this.buildSignature().on(context.getMessage()).withTimeStamp(this.duration).withSAMLToken(lazyToken).sign(new AbstractWsSecurityHandler.SignedParts[]{AbstractWsSecurityHandler.SignedParts.TIMESTAMP});
   }

   protected Logger getLogger() {
      return LOG;
   }
}
