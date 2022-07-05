package be.fgov.ehealth.certra.core.v2;

import javax.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {
   public ObjectFactory() {
   }

   public TextType createTextType() {
      return new TextType();
   }

   public LocalizedString createLocalizedString() {
      return new LocalizedString();
   }

   public ContactDataType createContactDataType() {
      return new ContactDataType();
   }

   public CertificateIdentifierType createCertificateIdentifierType() {
      return new CertificateIdentifierType();
   }

   public OrganizationType createOrganizationType() {
      return new OrganizationType();
   }

   public OrganizationIdentifierType createOrganizationIdentifierType() {
      return new OrganizationIdentifierType();
   }

   public ContractType createContractType() {
      return new ContractType();
   }

   public RevocationContractType createRevocationContractType() {
      return new RevocationContractType();
   }

   public EHealthCertificateSigningRequestType createEHealthCertificateSigningRequestType() {
      return new EHealthCertificateSigningRequestType();
   }

   public CertificateEntityType createCertificateEntityType() {
      return new CertificateEntityType();
   }

   public CertificateInfoType createCertificateInfoType() {
      return new CertificateInfoType();
   }

   public CertificateDetailsType createCertificateDetailsType() {
      return new CertificateDetailsType();
   }
}
