package be.fgov.ehealth.technicalconnector.ra.domain;

import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.fgov.ehealth.technicalconnector.ra.enumaration.UsageType;
import java.security.cert.X509Certificate;
import org.apache.commons.lang.Validate;

public final class RenewCertificateContract extends NewCertificateContract {
   private static final long serialVersionUID = 1L;

   public RenewCertificateContract(X509Certificate cert, ContactData contact, UsageType... types) throws TechnicalConnectorException {
      super(transform(cert), contact, types);
   }

   private static DistinguishedName transform(X509Certificate cert) throws TechnicalConnectorException {
      Validate.notNull(cert);
      return new DistinguishedName(cert.getSubjectX500Principal());
   }
}
