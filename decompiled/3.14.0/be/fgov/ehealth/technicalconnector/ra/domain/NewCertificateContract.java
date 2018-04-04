package be.fgov.ehealth.technicalconnector.ra.domain;

import be.ehealth.technicalconnector.beid.BeIDInfo;
import be.ehealth.technicalconnector.beid.domain.Identity;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.fgov.ehealth.technicalconnector.ra.enumaration.UsageType;
import be.fgov.ehealth.technicalconnector.ra.utils.RaPropertiesLoader;
import java.io.IOException;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.RSAPublicKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.Validate;
import org.bouncycastle.asn1.DERBitString;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.pkcs.PKCS10CertificationRequest;

public class NewCertificateContract extends Contract {
   private static final long serialVersionUID = 1L;
   private DistinguishedName name;
   private ContactData contact;
   private byte[] pkcs10;
   private Set<UsageType> usageTypes = new HashSet();
   private String contract;

   public NewCertificateContract(DistinguishedName name, ContactData contact, UsageType... types) throws TechnicalConnectorException {
      super(BeIDInfo.getInstance().getIdentity());
      Validate.notNull(name);
      Validate.notNull(contact);
      this.name = name;
      this.contact = contact;
      if (ArrayUtils.isNotEmpty(types)) {
         this.usageTypes.addAll(Arrays.asList(types));
      }

      this.contract = generatedContract(name, contact, this.getRequestor());
   }

   public ContactData getContact() {
      return this.contact;
   }

   public Set<UsageType> getUsageTypes() {
      return this.usageTypes;
   }

   public DistinguishedName getDistinguishedName() {
      return this.name;
   }

   public void setPkcs10DerEncoded(byte[] pkcs10) {
      verifyPKCS10(pkcs10, this.name);
      this.pkcs10 = ArrayUtils.clone(pkcs10);
   }

   public byte[] getPkcs10DerEncoded() {
      return ArrayUtils.clone(this.pkcs10);
   }

   protected String getContent() {
      return this.contract;
   }

   private static String generatedContract(DistinguishedName name, ContactData contact, Identity requestor) throws TechnicalConnectorException {
      Map<String, Object> context = new HashMap();
      context.put("oids", name.toOIDMap());
      context.put("identity", requestor);
      context.put("contact", contact);
      context.put("name", name);
      String template = "/templates/contract.create." + contact.getLanguage().getLanguageAbbreviation() + ".html";
      return generatedContract(context, template);
   }

   private static void verifyPKCS10(byte[] pkcs10, DistinguishedName name) {
      Validate.notNull(pkcs10);

      PKCS10CertificationRequest csr;
      try {
         csr = new PKCS10CertificationRequest(pkcs10);
      } catch (IOException var6) {
         throw new IllegalArgumentException(var6);
      }

      SubjectPublicKeyInfo pkInfo = csr.getSubjectPublicKeyInfo();
      Validate.isTrue(RaPropertiesLoader.getProperty("authentication.key.algorithm.oid").equals(pkInfo.getAlgorithm().getAlgorithm().getId()), "Public key was not RSA.");
      Validate.isTrue(getKeySize(pkInfo) >= Integer.parseInt(RaPropertiesLoader.getProperty("authentication.key.size")));
      X500Name subject = csr.getSubject();
      X500Name calculatedSubject = new X500Name(name.asNormalizedEhealthDN());
      Validate.isTrue(subject.equals(calculatedSubject));
   }

   private static int getKeySize(SubjectPublicKeyInfo subjectPKInfo) {
      try {
         X509EncodedKeySpec xspec = new X509EncodedKeySpec((new DERBitString(subjectPKInfo.getEncoded())).getBytes());
         AlgorithmIdentifier keyAlg = subjectPKInfo.getAlgorithm();
         PublicKey publicKey = KeyFactory.getInstance(keyAlg.getAlgorithm().getId()).generatePublic(xspec);
         String algorithm = publicKey.getAlgorithm();
         KeyFactory keyFact = KeyFactory.getInstance(algorithm);
         RSAPublicKeySpec keySpec = (RSAPublicKeySpec)keyFact.getKeySpec(publicKey, RSAPublicKeySpec.class);
         BigInteger modulus = keySpec.getModulus();
         return modulus.toString(2).length();
      } catch (Exception var8) {
         throw new IllegalArgumentException(var8);
      }
   }
}
