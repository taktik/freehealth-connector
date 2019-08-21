package org.taktik.connector.technical.handler;

import org.taktik.connector.technical.config.ConfigFactory;
import org.taktik.connector.technical.config.Configuration;
import org.taktik.connector.technical.config.domain.Duration;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.handler.wss4j.WSSecHeaderGeneratorWss4jImpl;
import org.taktik.connector.technical.service.sts.security.Credential;
import org.taktik.connector.technical.service.sts.security.SAMLToken;
import org.taktik.connector.technical.utils.ConfigurableFactoryHelper;
import java.util.concurrent.TimeUnit;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.ProtocolException;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import org.apache.commons.logging.Log;

public abstract class AbstractWsSecurityHandler extends AbstractSOAPHandler {
   public static final String PROP_WSSECHEADER_GENERATOR = "org.taktik.connector.technical.handler.wssecurity";
   private static final String PROP_SIGNATURE_TIMESTAMP_EXPIRES_TTL = "security.outgoing.message.timestamp.expires.ttl";
   private static final Configuration config = ConfigFactory.getConfigValidator();

   public AbstractWsSecurityHandler.WSSecHeaderGeneratorStep0 buildSignature() throws TechnicalConnectorException {
      ConfigurableFactoryHelper<AbstractWsSecurityHandler.WSSecHeaderGeneratorStep0> helper = new ConfigurableFactoryHelper("org.taktik.connector.technical.handler.wssecurity", WSSecHeaderGeneratorWss4jImpl.class.getName());
      return (AbstractWsSecurityHandler.WSSecHeaderGeneratorStep0)helper.getImplementation();
   }

   public boolean handleOutbound(SOAPMessageContext context) {
      try {
         this.getLogger().debug("adding WS-Security header");
         this.addWSSecurity(context);
         context.getMessage().saveChanges();
         return true;
      } catch (Exception var3) {
         throw new ProtocolException(var3);
      }
   }

   protected abstract void addWSSecurity(SOAPMessageContext var1) throws TechnicalConnectorException;

   protected abstract Log getLogger();

   protected long getTimeStampTTL() {
      return config.getDurationProperty("security.outgoing.message.timestamp.expires.ttl", 60L, TimeUnit.SECONDS).convert(TimeUnit.SECONDS);
   }

   public interface WSSecHeaderGeneratorStep4 {
      void sign(AbstractWsSecurityHandler.SignedParts... var1) throws TechnicalConnectorException;
   }

   public interface WSSecHeaderGeneratorStep3 extends AbstractWsSecurityHandler.WSSecHeaderGeneratorStep4 {
      AbstractWsSecurityHandler.WSSecHeaderGeneratorStep3 withSAMLToken(SAMLToken var1) throws TechnicalConnectorException;
   }

   public interface WSSecHeaderGeneratorStep2 extends AbstractWsSecurityHandler.WSSecHeaderGeneratorStep3 {
      AbstractWsSecurityHandler.WSSecHeaderGeneratorStep3 withBinarySecurityToken(Credential var1) throws TechnicalConnectorException;
   }

   public interface WSSecHeaderGeneratorStep1 extends AbstractWsSecurityHandler.WSSecHeaderGeneratorStep2 {
      AbstractWsSecurityHandler.WSSecHeaderGeneratorStep2 withTimeStamp(long var1, TimeUnit var3);

      AbstractWsSecurityHandler.WSSecHeaderGeneratorStep2 withTimeStamp(Duration var1);
   }

   public interface WSSecHeaderGeneratorStep0 extends AbstractWsSecurityHandler.WSSecHeaderGeneratorStep2 {
      AbstractWsSecurityHandler.WSSecHeaderGeneratorStep1 on(SOAPMessage var1) throws TechnicalConnectorException;
   }

   public static enum SignedParts {
      BODY,
      TIMESTAMP,
      BST,
      SAML_ASSERTION;
   }
}
