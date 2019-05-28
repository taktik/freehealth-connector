package be.fgov.ehealth.technicalconnector.ra.builders;

import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.utils.IdentifierType;
import be.fgov.ehealth.technicalconnector.ra.domain.Actor;
import be.fgov.ehealth.technicalconnector.ra.domain.ActorId;
import be.fgov.ehealth.technicalconnector.ra.domain.ContactData;
import be.fgov.ehealth.technicalconnector.ra.domain.DistinguishedName;
import be.fgov.ehealth.technicalconnector.ra.domain.ForeignerRequest;
import be.fgov.ehealth.technicalconnector.ra.utils.CertificateUtils;
import java.security.KeyPair;
import java.util.Arrays;
import org.apache.commons.lang.Validate;

public final class ForeignRequestBuilder {
   private String name;
   private String firstName;
   private String ssinBis;
   private String personalEmail;
   private String personalPhone;
   private String generalEmail;
   private String generalPhone;
   private KeyPair keyPair;

   public ForeignRequestBuilder withName(String name) {
      this.name = name;
      return this;
   }

   public ForeignRequestBuilder withFirstName(String firstName) {
      this.firstName = firstName;
      return this;
   }

   public ForeignRequestBuilder withSsinBis(String ssinBis) {
      this.ssinBis = ssinBis;
      return this;
   }

   public ForeignRequestBuilder withPersonalEmail(String personalEmail) {
      this.personalEmail = personalEmail;
      return this;
   }

   public ForeignRequestBuilder withPersonalPhone(String personalPhone) {
      this.personalPhone = personalPhone;
      return this;
   }

   public ForeignRequestBuilder withGeneralEmail(String generalEmail) {
      this.generalEmail = generalEmail;
      return this;
   }

   public ForeignRequestBuilder withGeneralPhone(String generalPhone) {
      this.generalPhone = generalPhone;
      return this;
   }

   public ForeignRequestBuilder withKeyPair(KeyPair keyPair) {
      this.keyPair = keyPair;
      return this;
   }

   public ForeignerRequest build() throws TechnicalConnectorException {
      Validate.notNull(this.ssinBis);
      Validate.notNull(this.name);
      Validate.notNull(this.personalPhone);
      Validate.notNull(this.personalEmail);
      DistinguishedName distinguishedName = new DistinguishedName(this.ssinBis, this.name, this.firstName, IdentifierType.SSIN);
      Actor foreignPerson = Actor.newBuilder().firstNames(Arrays.asList(this.firstName)).name(this.name).ids(Arrays.asList(ActorId.newBuilder().type(IdentifierType.SSIN.getType(48)).value(this.ssinBis).build())).build();
      ContactData contactData = new ContactData(this.generalPhone, this.personalPhone, this.generalEmail, this.personalEmail);
      return new ForeignerRequest(foreignPerson, contactData, CertificateUtils.createCSR(distinguishedName.asNormalizedEhealthDN(), this.keyPair));
   }
}
