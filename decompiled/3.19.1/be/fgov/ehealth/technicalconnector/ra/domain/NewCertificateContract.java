package be.fgov.ehealth.technicalconnector.ra.domain;

import be.ehealth.technicalconnector.beid.BeIDInfo;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.fgov.ehealth.technicalconnector.ra.enumaration.UsageType;
import be.fgov.ehealth.technicalconnector.ra.utils.CertificateUtils;
import be.fgov.ehealth.technicalconnector.ra.utils.RaPropertiesLoader;
import java.io.IOException;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.PublicKey;
import java.security.spec.RSAPublicKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.Validate;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.bouncycastle.asn1.DERBitString;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.pkcs.PKCS10CertificationRequest;

public class NewCertificateContract extends Request {
   private static final long serialVersionUID = 1L;
   private String dn;
   private ContactData contact;
   private byte[] pkcs10;
   private LocalizedText text;
   private List<UsageType> usageTypes = new ArrayList();
   private Actor signer;

   public NewCertificateContract(GeneratedContract generatedContract, KeyPair keyPair, List<UsageType> usageTypes) throws TechnicalConnectorException {
      super(BeIDInfo.getInstance().getIdentity());
      Validate.notNull(generatedContract);
      Validate.isTrue(generatedContract.isContractViewed());
      Validate.notEmpty(generatedContract.getDN());
      Validate.notNull(generatedContract.getSigner());
      Validate.notNull(generatedContract.getContactData());
      Validate.notNull(generatedContract.getIdentifierType());
      Validate.notNull(keyPair);
      this.contact = generatedContract.getContactData();
      this.dn = generatedContract.getDN().replace(generatedContract.getIdentifierType().getType(48) + "=", generatedContract.getIdentifierType().getType(48) + "\\=");
      this.text = generatedContract.getText();
      this.usageTypes = usageTypes;
      this.signer = generatedContract.getSigner();
      this.pkcs10 = CertificateUtils.createCSR(generatedContract.getDN(), keyPair);
      this.verifyPKCS10(this.pkcs10, this.dn);
   }

   public ContactData getContact() {
      return this.contact;
   }

   public byte[] getPkcs10DerEncoded() {
      return ArrayUtils.clone(this.pkcs10);
   }

   public String getDn() {
      return this.dn;
   }

   public List<UsageType> getUsageTypes() {
      return this.usageTypes;
   }

   public Actor getSigner() {
      return this.signer;
   }

   public LocalizedText getText() {
      return this.text;
   }

   private void verifyPKCS10(byte[] pkcs10, String dn) {
      Validate.notNull(pkcs10);

      PKCS10CertificationRequest csr;
      try {
         csr = new PKCS10CertificationRequest(pkcs10);
      } catch (IOException var7) {
         throw new IllegalArgumentException(var7);
      }

      SubjectPublicKeyInfo pkInfo = csr.getSubjectPublicKeyInfo();
      Validate.isTrue(RaPropertiesLoader.getProperty("authentication.key.algorithm.oid").equals(pkInfo.getAlgorithm().getAlgorithm().getId()), "Public key was not RSA.");
      Validate.isTrue(this.getKeySize(pkInfo) >= Integer.parseInt(RaPropertiesLoader.getProperty("authentication.key.size")));
      X500Name subject = csr.getSubject();
      X500Name calculatedSubject = new X500Name(dn);
      Validate.isTrue(subject.equals(calculatedSubject));
   }

   private int getKeySize(SubjectPublicKeyInfo subjectPKInfo) {
      try {
         X509EncodedKeySpec xspec = new X509EncodedKeySpec((new DERBitString(subjectPKInfo.getEncoded())).getBytes());
         AlgorithmIdentifier keyAlg = subjectPKInfo.getAlgorithm();
         PublicKey publicKey = KeyFactory.getInstance(keyAlg.getAlgorithm().getId()).generatePublic(xspec);
         String algorithm = publicKey.getAlgorithm();
         KeyFactory keyFact = KeyFactory.getInstance(algorithm);
         RSAPublicKeySpec keySpec = (RSAPublicKeySpec)keyFact.getKeySpec(publicKey, RSAPublicKeySpec.class);
         BigInteger modulus = keySpec.getModulus();
         return modulus.toString(2).length();
      } catch (Exception var9) {
         throw new IllegalArgumentException(var9);
      }
   }

   public boolean equals(Object o) {
      if (this == o) {
         return true;
      } else if (o != null && this.getClass() == o.getClass()) {
         NewCertificateContract that = (NewCertificateContract)o;
         return (new EqualsBuilder()).appendSuper(super.equals(o)).append(this.getDn(), that.getDn()).append(this.getContact(), that.getContact()).append(this.pkcs10, that.pkcs10).append(this.getText(), that.getText()).append(this.getUsageTypes(), that.getUsageTypes()).append(this.getSigner(), that.getSigner()).isEquals();
      } else {
         return false;
      }
   }

   public int hashCode() {
      return (new HashCodeBuilder(17, 37)).appendSuper(super.hashCode()).append(this.getDn()).append(this.getContact()).append(this.pkcs10).append(this.getText()).append(this.getUsageTypes()).append(this.getSigner()).toHashCode();
   }
}
