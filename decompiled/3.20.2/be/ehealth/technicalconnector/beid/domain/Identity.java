package be.ehealth.technicalconnector.beid.domain;

import java.io.Serializable;
import java.util.GregorianCalendar;
import org.apache.commons.lang.ArrayUtils;
import org.joda.time.DateTime;

public class Identity implements Serializable {
   private static final long serialVersionUID = 1L;
   private String cardNumber;
   private String chipNumber;
   private DateTime cardValidityDateBegin;
   private DateTime cardValidityDateEnd;
   private String cardDeliveryMunicipality;
   private String nationalNumber;
   private String name;
   private String firstName;
   private String middleName;
   private String nationality;
   private String placeOfBirth;
   private DateTime dateOfBirth;
   private Gender gender;
   private String nobleCondition;
   private DocumentType documentType;
   private SpecialStatus specialStatus;
   private byte[] photoDigest;
   private String duplicate;
   private SpecialOrganisation specialOrganisation;
   private boolean memberOfFamily;
   private byte[] data;

   public String getCardNumber() {
      return this.cardNumber;
   }

   public void setCardNumber(String cardNumber) {
      this.cardNumber = cardNumber;
   }

   public String getChipNumber() {
      return this.chipNumber;
   }

   public void setChipNumber(String chipNumber) {
      this.chipNumber = chipNumber;
   }

   public DateTime getCardValidityDateBegin() {
      return this.cardValidityDateBegin;
   }

   /** @deprecated */
   @Deprecated
   public void setCardValidityDateBegin(GregorianCalendar gregorianCalendar) {
      this.cardValidityDateBegin = new DateTime(gregorianCalendar);
   }

   public void setCardValidityDateBegin(DateTime cardValidityDateBegin) {
      this.cardValidityDateBegin = cardValidityDateBegin;
   }

   public DateTime getCardValidityDateEnd() {
      return this.cardValidityDateEnd;
   }

   /** @deprecated */
   @Deprecated
   public void setCardValidityDateEnd(GregorianCalendar cardValidityDateEnd) {
      this.cardValidityDateEnd = new DateTime(cardValidityDateEnd);
   }

   public void setCardValidityDateEnd(DateTime cardValidityDateEnd) {
      this.cardValidityDateEnd = cardValidityDateEnd;
   }

   public String getCardDeliveryMunicipality() {
      return this.cardDeliveryMunicipality;
   }

   public void setCardDeliveryMunicipality(String cardDeliveryMunicipality) {
      this.cardDeliveryMunicipality = cardDeliveryMunicipality;
   }

   public String getNationalNumber() {
      return this.nationalNumber;
   }

   public void setNationalNumber(String nationalNumber) {
      this.nationalNumber = nationalNumber;
   }

   public String getName() {
      return this.name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getFirstName() {
      return this.firstName;
   }

   public void setFirstName(String firstName) {
      this.firstName = firstName;
   }

   public String getMiddleName() {
      return this.middleName;
   }

   public void setMiddleName(String middleName) {
      this.middleName = middleName;
   }

   public String getNationality() {
      return this.nationality;
   }

   public void setNationality(String nationality) {
      this.nationality = nationality;
   }

   public String getPlaceOfBirth() {
      return this.placeOfBirth;
   }

   public void setPlaceOfBirth(String placeOfBirth) {
      this.placeOfBirth = placeOfBirth;
   }

   public DateTime getDateOfBirth() {
      return this.dateOfBirth;
   }

   /** @deprecated */
   @Deprecated
   public void setDateOfBirth(GregorianCalendar gregorianCalendar) {
      this.dateOfBirth = new DateTime(gregorianCalendar);
   }

   public void setDateOfBirth(DateTime dateOfBirth) {
      this.dateOfBirth = dateOfBirth;
   }

   public Gender getGender() {
      return this.gender;
   }

   public void setGender(Gender gender) {
      this.gender = gender;
   }

   public String getNobleCondition() {
      return this.nobleCondition;
   }

   public void setNobleCondition(String nobleCondition) {
      this.nobleCondition = nobleCondition;
   }

   public DocumentType getDocumentType() {
      return this.documentType;
   }

   public void setDocumentType(DocumentType documentType) {
      this.documentType = documentType;
   }

   public byte[] getPhotoDigest() {
      return ArrayUtils.clone(this.photoDigest);
   }

   public void setPhotoDigest(byte[] photoDigest) {
      this.photoDigest = ArrayUtils.clone(photoDigest);
   }

   public SpecialStatus getSpecialStatus() {
      return this.specialStatus;
   }

   public void setSpecialStatus(SpecialStatus specialStatus) {
      this.specialStatus = specialStatus;
   }

   public String getDuplicate() {
      return this.duplicate;
   }

   public void setDuplicate(String duplicate) {
      this.duplicate = duplicate;
   }

   public boolean isMemberOfFamily() {
      return this.memberOfFamily;
   }

   public void setIsMemberOfFamily(boolean isMemberOfFamily) {
      this.memberOfFamily = isMemberOfFamily;
   }

   public SpecialOrganisation getSpecialOrganisation() {
      return this.specialOrganisation;
   }

   public void setSpecialOrganisation(SpecialOrganisation specialOrganisation) {
      this.specialOrganisation = specialOrganisation;
   }

   public byte[] getData() {
      return ArrayUtils.clone(this.data);
   }

   public void setData(byte[] data) {
      this.data = ArrayUtils.clone((byte[])data.clone());
   }

   public String toString() {
      return "[" + this.name + " " + this.firstName + "]";
   }
}
