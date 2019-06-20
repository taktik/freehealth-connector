package org.taktik.connector.technical.service.etee.impl;

import org.taktik.connector.technical.cache.Cache;
import org.taktik.connector.technical.cache.CacheFactory;
import org.taktik.connector.technical.service.etee.RevocationStatusChecker;
import be.fgov.ehealth.technicalconnector.bootstrap.bcp.domain.CacheInformation;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractRevocationStatusChecker implements RevocationStatusChecker {
   private static final Logger LOG = LoggerFactory.getLogger(AbstractRevocationStatusChecker.class);
   private Cache<X509Certificate, Boolean> cache;

   public AbstractRevocationStatusChecker() {
      this.cache = CacheFactory.newInstance(CacheFactory.CacheType.MEMORY, "revocation-cache", CacheInformation.ExpiryType.NONE, null);
   }

   public boolean isRevoked(X509Certificate x509certificate) throws CertificateException {
      return this.isRevoked(x509certificate, new DateTime());
   }

   public boolean isRevoked(X509Certificate cert, DateTime validOn) throws CertificateException {
      if (cert == null) {
         throw new CertificateException("X509Certificate is empty.");
      } else {
         if (!this.cache.containsKey(cert)) {
            LOG.info("Checking revocation status for cert from subject : " + cert.getSubjectX500Principal().toString());
            boolean isRevoked = false;
            if (!this.isSelfSigned(cert)) {
               isRevoked = this.delegateRevoke(cert, validOn);
            } else {
               LOG.info("Selfsigned certificate detected, skipping delegateRevoke.");
            }

            this.cache.put(cert, isRevoked);
         }

         return this.cache.get(cert);
      }
   }

   abstract boolean delegateRevoke(X509Certificate var1, DateTime var2) throws CertificateException;

   private boolean isSelfSigned(X509Certificate cert) {
      try {
         cert.verify(cert.getPublicKey());
         return true;
      } catch (Exception var3) {
         return false;
      }
   }

   public void flushCache() {
      this.cache.clear();
   }
}
