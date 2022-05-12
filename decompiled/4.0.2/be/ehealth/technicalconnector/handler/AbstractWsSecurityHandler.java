package be.ehealth.technicalconnector.handler;

import be.ehealth.technicalconnector.config.ConfigFactory;
import be.ehealth.technicalconnector.config.Configuration;
import be.ehealth.technicalconnector.config.domain.Duration;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.handler.wss4j.WSSecHeaderGeneratorWss4jImpl;
import be.ehealth.technicalconnector.service.sts.security.Credential;
import be.ehealth.technicalconnector.service.sts.security.SAMLToken;
import be.ehealth.technicalconnector.utils.ConfigurableFactoryHelper;
import java.util.concurrent.TimeUnit;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.ProtocolException;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import org.slf4j.Logger;

public abstract class AbstractWsSecurityHandler extends AbstractSOAPHandler {
   public static final String PROP_WSSECHEADER_GENERATOR = "be.ehealth.technicalconnector.handler.wssecurity";
   private static final String PROP_SIGNATURE_TIMESTAMP_EXPIRES_TTL = "security.outgoing.message.timestamp.expires.ttl";
   private static final Configuration config = ConfigFactory.getConfigValidator();

   public AbstractWsSecurityHandler() {
   }

   public WSSecHeaderGeneratorStep0 buildSignature() throws TechnicalConnectorException {
      ConfigurableFactoryHelper<WSSecHeaderGeneratorStep0> helper = new ConfigurableFactoryHelper("be.ehealth.technicalconnector.handler.wssecurity", WSSecHeaderGeneratorWss4jImpl.class.getName());
      return (WSSecHeaderGeneratorStep0)helper.getImplementation();
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

   protected abstract Logger getLogger();

   protected long getTimeStampTTL() {
      return config.getDurationProperty("security.outgoing.message.timestamp.expires.ttl", 60L, TimeUnit.SECONDS).convert(TimeUnit.SECONDS);
   }

   public interface WSSecHeaderGeneratorStep4 {
      void sign(SignedParts... var1) throws TechnicalConnectorException;
   }

   public interface WSSecHeaderGeneratorStep3 extends WSSecHeaderGeneratorStep4 {
      WSSecHeaderGeneratorStep3 withSAMLToken(SAMLToken var1) throws TechnicalConnectorException;
   }

   public interface WSSecHeaderGeneratorStep2 extends WSSecHeaderGeneratorStep3 {
      WSSecHeaderGeneratorStep3 withBinarySecurityToken(Credential var1) throws TechnicalConnectorException;
   }

   public interface WSSecHeaderGeneratorStep1 extends WSSecHeaderGeneratorStep2 {
      WSSecHeaderGeneratorStep2 withTimeStamp(long var1, TimeUnit var3);

      WSSecHeaderGeneratorStep2 withTimeStamp(Duration var1);
   }

   public interface WSSecHeaderGeneratorStep0 extends WSSecHeaderGeneratorStep2 {
      /** @deprecated */
      @Deprecated
      WSSecHeaderGeneratorStep1 on(SOAPMessage var1) throws TechnicalConnectorException;

      WSSecHeaderGeneratorStep1 on(SOAPMessageContext var1) throws TechnicalConnectorException;
   }

   public static enum SignedParts {
      BODY,
      TIMESTAMP,
      BST,
      SAML_ASSERTION;

      private SignedParts() {
      }
   }
}
