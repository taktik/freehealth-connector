package be.fgov.ehealth.rn.personservice.core.v1;

import javax.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {
   public ObjectFactory() {
   }

   public SearchPersonBySsinCriteriaType createSearchPersonBySsinCriteriaType() {
      return new SearchPersonBySsinCriteriaType();
   }

   public SearchPersonPhoneticallyCriteriaType createSearchPersonPhoneticallyCriteriaType() {
      return new SearchPersonPhoneticallyCriteriaType();
   }

   public PersonResponseResultType createPersonResponseResultType() {
      return new PersonResponseResultType();
   }

   public PhoneticName createPhoneticName() {
      return new PhoneticName();
   }

   public PhoneticBirth createPhoneticBirth() {
      return new PhoneticBirth();
   }

   public PhoneticGender createPhoneticGender() {
      return new PhoneticGender();
   }

   public PhoneticAddress createPhoneticAddress() {
      return new PhoneticAddress();
   }

   public PersonResponseResultsType createPersonResponseResultsType() {
      return new PersonResponseResultsType();
   }
}
