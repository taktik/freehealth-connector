package be.ehealth.technicalconnector.service.sts.security.impl;

import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorExceptionValues;
import be.ehealth.technicalconnector.service.sts.security.ExtendedCredential;
import be.fgov.ehealth.etee.crypto.utils.SecurityConfiguration;
import java.security.NoSuchProviderException;
import java.security.cert.CertPath;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.util.Arrays;
import org.joda.time.DateTime;

public abstract class AbstractExtendedCredential implements ExtendedCredential {
   private static final CertificateFactory CF;

   public AbstractExtendedCredential() {
   }

   public CertPath getCertPath() throws TechnicalConnectorException {
      try {
         return CF.generateCertPath(Arrays.asList(this.getCertificateChain()));
      } catch (CertificateException var2) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_IOEXCEPTION, var2, new Object[0]);
      }
   }

   public DateTime getExpirationDateTime() throws TechnicalConnectorException {
      return new DateTime(this.getCertificate().getNotAfter());
   }

   static {
      try {
         SecurityConfiguration.configure();
         CF = CertificateFactory.getInstance("X.509", "BC");
      } catch (NoSuchProviderException var1) {
         throw new IllegalArgumentException(var1);
      } catch (CertificateException var2) {
         throw new IllegalArgumentException(var2);
      }
   }
}
