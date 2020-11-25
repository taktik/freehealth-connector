package be.fgov.ehealth.technicalconnector.distributedkeys.jca;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.Key;
import java.security.KeyStoreException;
import java.security.KeyStoreSpi;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.KeyStore.LoadStoreParameter;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DistributedKeyStore extends KeyStoreSpi {
   private static final Logger LOG = LoggerFactory.getLogger(DistributedKeyStore.class);
   private DistributedKeyLoadStoreParam keyStoreParameter;
   private Map<String, Certificate[]> cache = new HashMap();

   public void engineLoad(LoadStoreParameter param) throws IOException, NoSuchAlgorithmException, CertificateException {
      if (param instanceof DistributedKeyLoadStoreParam) {
         this.keyStoreParameter = (DistributedKeyLoadStoreParam)param;
      }

   }

   public Key engineGetKey(String alias, char[] password) throws NoSuchAlgorithmException, UnrecoverableKeyException {
      return new DistributedPrivateKey(this.keyStoreParameter.getProxy(), alias);
   }

   public Certificate[] engineGetCertificateChain(String alias) {
      if (!this.cache.containsKey(alias)) {
         LOG.debug("Adding [" + alias + "] to cache");
         this.cache.put(alias, this.keyStoreParameter.getProxy().getCertificateChain(alias).toArray(new Certificate[0]));
      }

      return (Certificate[])this.cache.get(alias);
   }

   public Certificate engineGetCertificate(String alias) {
      try {
         LOG.debug("Trying to obtain certificate with Alias [" + alias + "]");
         return this.engineGetCertificateChain(alias)[0];
      } catch (Exception var3) {
         LOG.error("Unable to obtain certificate with alias [" + alias + "]", var3);
         return null;
      }
   }

   public Date engineGetCreationDate(String alias) {
      return null;
   }

   public void engineSetKeyEntry(String alias, Key key, char[] password, Certificate[] chain) throws KeyStoreException {
      LOG.debug("Unsupported operation enginSetKeyEntry");
   }

   public void engineSetKeyEntry(String alias, byte[] key, Certificate[] chain) throws KeyStoreException {
      LOG.debug("Unsupported operation engineSetKeyEntry");
   }

   public void engineSetCertificateEntry(String alias, Certificate cert) throws KeyStoreException {
      LOG.debug("Unsupported operation engineSetCertificateEntry");
   }

   public void engineDeleteEntry(String alias) throws KeyStoreException {
      LOG.debug("Unsupported operation engineDeleteEntry");
   }

   public Enumeration<String> engineAliases() {
      Set<String> aliases = this.keyStoreParameter.getProxy().getAliases();
      return Collections.enumeration(aliases);
   }

   public boolean engineContainsAlias(String alias) {
      Set<String> aliases = this.keyStoreParameter.getProxy().getAliases();
      return aliases.contains(alias);
   }

   public int engineSize() {
      Set<String> aliases = this.keyStoreParameter.getProxy().getAliases();
      return aliases.size();
   }

   public boolean engineIsKeyEntry(String alias) {
      Set<String> aliases = this.keyStoreParameter.getProxy().getAliases();
      return aliases.contains(alias);
   }

   public boolean engineIsCertificateEntry(String alias) {
      return false;
   }

   public String engineGetCertificateAlias(Certificate cert) {
      throw new UnsupportedOperationException();
   }

   public void engineStore(OutputStream stream, char[] password) throws IOException, NoSuchAlgorithmException, CertificateException {
      LOG.debug("Unsupported operation engineStore");
   }

   public void engineLoad(InputStream stream, char[] password) throws IOException, NoSuchAlgorithmException, CertificateException {
      LOG.debug("Unsupported operation engineLoad");
   }
}
