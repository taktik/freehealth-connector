package be.fgov.ehealth.etee.ra.csr._1_0.protocol;

import javax.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {
   public ObjectFactory() {
   }

   public EHealthCertificateRequest createEHealthCertificateRequest() {
      return new EHealthCertificateRequest();
   }

   public ContactDataType createContactDataType() {
      return new ContactDataType();
   }

   public SearchCriteriumType createSearchCriteriumType() {
      return new SearchCriteriumType();
   }

   public UsagesType createUsagesType() {
      return new UsagesType();
   }
}
