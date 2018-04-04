package be.ehealth.technicalconnector.handler;

import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorExceptionValues;
import be.ehealth.technicalconnector.service.sts.security.Credential;
import be.ehealth.technicalconnector.service.sts.security.impl.KeyPairCredential;
import be.ehealth.technicalconnector.session.Session;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CertificateCallback extends AbstractWsSecurityHandler {
   private static final Logger LOG = LoggerFactory.getLogger(CertificateCallback.class);
   private Credential cred;

   public CertificateCallback() throws TechnicalConnectorException {
   }

   public CertificateCallback(X509Certificate certificate, PrivateKey privateKey) throws TechnicalConnectorException {
      this.cred = new KeyPairCredential(privateKey, certificate);
   }

   public CertificateCallback(Credential cred) throws TechnicalConnectorException {
      this.cred = cred;
   }

   protected void addWSSecurity(SOAPMessageContext context) throws TechnicalConnectorException {
      Credential lazyCred = this.cred;
      if (lazyCred == null) {
         LOG.debug("[Lazy Loading] Trying to load SAMLToken from session.");
         lazyCred = Session.getInstance().getSession().getHolderOfKeyCredential();
         if (lazyCred == null) {
            throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_TECHNICAL, new Object[]{"Unable to lazy load credential."});
         }
      }

      this.buildSignature().on(context.getMessage()).withTimeStamp(60L, TimeUnit.SECONDS).withBinarySecurityToken(lazyCred).sign(new AbstractWsSecurityHandler.SignedParts[]{AbstractWsSecurityHandler.SignedParts.BODY, AbstractWsSecurityHandler.SignedParts.TIMESTAMP, AbstractWsSecurityHandler.SignedParts.BST});
   }

   protected Logger getLogger() {
      return LOG;
   }
}
