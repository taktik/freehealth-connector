package be.ehealth.technicalconnector.beid.impl;

import be.ehealth.technicalconnector.beid.domain.Address;
import be.ehealth.technicalconnector.beid.domain.DocumentType;
import be.ehealth.technicalconnector.beid.domain.Gender;
import be.ehealth.technicalconnector.beid.domain.Identity;
import be.ehealth.technicalconnector.beid.domain.SpecialOrganisation;
import be.ehealth.technicalconnector.beid.domain.SpecialStatus;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

public class CommonsEidInstantiator$EidMapperImpl implements CommonsEidInstantiator.EidMapper {
   private final DatatypeFactory datatypeFactory;

   public CommonsEidInstantiator$EidMapperImpl() {
      try {
         this.datatypeFactory = DatatypeFactory.newInstance();
      } catch (DatatypeConfigurationException var2) {
         throw new RuntimeException(var2);
      }
   }

   public Identity map(be.fedict.commons.eid.consumer.Identity identity) {
      if (identity == null) {
         return null;
      } else {
         Identity identity1 = new Identity();
         identity1.setCardNumber(identity.getCardNumber());
         identity1.setChipNumber(identity.getChipNumber());
         identity1.setCardValidityDateBegin(xmlGregorianCalendarToJodaDateTime(this.calendarToXmlGregorianCalendar(identity.getCardValidityDateBegin())));
         identity1.setCardValidityDateEnd(xmlGregorianCalendarToJodaDateTime(this.calendarToXmlGregorianCalendar(identity.getCardValidityDateEnd())));
         identity1.setCardDeliveryMunicipality(identity.getCardDeliveryMunicipality());
         identity1.setNationalNumber(identity.getNationalNumber());
         identity1.setName(identity.getName());
         identity1.setFirstName(identity.getFirstName());
         identity1.setMiddleName(identity.getMiddleName());
         identity1.setNationality(identity.getNationality());
         identity1.setPlaceOfBirth(identity.getPlaceOfBirth());
         identity1.setDateOfBirth(xmlGregorianCalendarToJodaDateTime(this.calendarToXmlGregorianCalendar(identity.getDateOfBirth())));
         identity1.setGender(this.genderToGender(identity.getGender()));
         identity1.setNobleCondition(identity.getNobleCondition());
         identity1.setDocumentType(this.documentTypeToDocumentType(identity.getDocumentType()));
         byte[] photoDigest = identity.getPhotoDigest();
         if (photoDigest != null) {
            identity1.setPhotoDigest(Arrays.copyOf(photoDigest, photoDigest.length));
         }

         identity1.setSpecialStatus(this.specialStatusToSpecialStatus(identity.getSpecialStatus()));
         identity1.setDuplicate(identity.getDuplicate());
         identity1.setSpecialOrganisation(this.specialOrganisationToSpecialOrganisation(identity.getSpecialOrganisation()));
         byte[] data = identity.getData();
         if (data != null) {
            identity1.setData(Arrays.copyOf(data, data.length));
         }

         return identity1;
      }
   }

   public Address map(be.fedict.commons.eid.consumer.Address address) {
      if (address == null) {
         return null;
      } else {
         Address address1 = new Address();
         address1.setZip(address.getZip());
         address1.setMunicipality(address.getMunicipality());
         byte[] data = address.getData();
         if (data != null) {
            address1.setData(Arrays.copyOf(data, data.length));
         }

         address1.setStreetAndNumber(address.getStreetAndNumber());
         return address1;
      }
   }

   private XMLGregorianCalendar calendarToXmlGregorianCalendar(Calendar cal) {
      if (cal == null) {
         return null;
      } else {
         GregorianCalendar gcal = new GregorianCalendar(cal.getTimeZone());
         gcal.setTimeInMillis(cal.getTimeInMillis());
         return this.datatypeFactory.newXMLGregorianCalendar(gcal);
      }
   }

   private static DateTime xmlGregorianCalendarToJodaDateTime(XMLGregorianCalendar xcal) {
      if (xcal == null) {
         return null;
      } else if (xcal.getYear() != -2147483648 && xcal.getMonth() != -2147483648 && xcal.getDay() != -2147483648 && xcal.getHour() != -2147483648 && xcal.getMinute() != -2147483648) {
         if (xcal.getSecond() != -2147483648 && xcal.getMillisecond() != -2147483648 && xcal.getTimezone() != -2147483648) {
            return new DateTime(xcal.getYear(), xcal.getMonth(), xcal.getDay(), xcal.getHour(), xcal.getMinute(), xcal.getSecond(), xcal.getMillisecond(), DateTimeZone.forOffsetMillis(xcal.getTimezone() * '\uea60'));
         } else if (xcal.getSecond() != -2147483648 && xcal.getMillisecond() != -2147483648) {
            return new DateTime(xcal.getYear(), xcal.getMonth(), xcal.getDay(), xcal.getHour(), xcal.getMinute(), xcal.getSecond(), xcal.getMillisecond());
         } else if (xcal.getSecond() != -2147483648 && xcal.getTimezone() != -2147483648) {
            return new DateTime(xcal.getYear(), xcal.getMonth(), xcal.getDay(), xcal.getHour(), xcal.getMinute(), xcal.getSecond(), DateTimeZone.forOffsetMillis(xcal.getTimezone() * '\uea60'));
         } else if (xcal.getSecond() != -2147483648) {
            return new DateTime(xcal.getYear(), xcal.getMonth(), xcal.getDay(), xcal.getHour(), xcal.getMinute(), xcal.getSecond());
         } else {
            return xcal.getTimezone() != -2147483648 ? new DateTime(xcal.getYear(), xcal.getMonth(), xcal.getDay(), xcal.getHour(), xcal.getMinute(), DateTimeZone.forOffsetMillis(xcal.getTimezone() * '\uea60')) : new DateTime(xcal.getYear(), xcal.getMonth(), xcal.getDay(), xcal.getHour(), xcal.getMinute());
         }
      } else {
         return null;
      }
   }

   protected Gender genderToGender(be.fedict.commons.eid.consumer.Gender gender) {
      if (gender == null) {
         return null;
      } else {
         Gender gender1;
         switch (gender) {
            case MALE:
               gender1 = Gender.MALE;
               break;
            case FEMALE:
               gender1 = Gender.FEMALE;
               break;
            default:
               throw new IllegalArgumentException("Unexpected enum constant: " + gender);
         }

         return gender1;
      }
   }

   protected DocumentType documentTypeToDocumentType(be.fedict.commons.eid.consumer.DocumentType documentType) {
      if (documentType == null) {
         return null;
      } else {
         DocumentType documentType1;
         switch (documentType) {
            case BELGIAN_CITIZEN:
               documentType1 = DocumentType.BELGIAN_CITIZEN;
               break;
            case KIDS_CARD:
               documentType1 = DocumentType.KIDS_CARD;
               break;
            case BOOTSTRAP_CARD:
               documentType1 = DocumentType.BOOTSTRAP_CARD;
               break;
            case HABILITATION_CARD:
               documentType1 = DocumentType.HABILITATION_CARD;
               break;
            case FOREIGNER_A:
               documentType1 = DocumentType.FOREIGNER_A;
               break;
            case FOREIGNER_B:
               documentType1 = DocumentType.FOREIGNER_B;
               break;
            case FOREIGNER_C:
               documentType1 = DocumentType.FOREIGNER_C;
               break;
            case FOREIGNER_D:
               documentType1 = DocumentType.FOREIGNER_D;
               break;
            case FOREIGNER_E:
               documentType1 = DocumentType.FOREIGNER_E;
               break;
            case FOREIGNER_E_PLUS:
               documentType1 = DocumentType.FOREIGNER_E_PLUS;
               break;
            case FOREIGNER_F:
               documentType1 = DocumentType.FOREIGNER_F;
               break;
            case FOREIGNER_F_PLUS:
               documentType1 = DocumentType.FOREIGNER_F_PLUS;
               break;
            case EUROPEAN_BLUE_CARD_H:
               documentType1 = DocumentType.EUROPEAN_BLUE_CARD_H;
               break;
            case FOREIGNER_I:
               documentType1 = DocumentType.FOREIGNER_I;
               break;
            case FOREIGNER_J:
               documentType1 = DocumentType.FOREIGNER_J;
               break;
            case FOREIGNER_M:
               documentType1 = DocumentType.FOREIGNER_M;
               break;
            case FOREIGNER_N:
               documentType1 = DocumentType.FOREIGNER_N;
               break;
            case FOREIGNER_EU:
               documentType1 = DocumentType.FOREIGNER_EU;
               break;
            case FOREIGNER_EU_PLUS:
               documentType1 = DocumentType.FOREIGNER_EU_PLUS;
               break;
            default:
               throw new IllegalArgumentException("Unexpected enum constant: " + documentType);
         }

         return documentType1;
      }
   }

   protected SpecialStatus specialStatusToSpecialStatus(be.fedict.commons.eid.consumer.SpecialStatus specialStatus) {
      if (specialStatus == null) {
         return null;
      } else {
         SpecialStatus specialStatus1;
         switch (specialStatus) {
            case NO_STATUS:
               specialStatus1 = SpecialStatus.NO_STATUS;
               break;
            case WHITE_CANE:
               specialStatus1 = SpecialStatus.WHITE_CANE;
               break;
            case EXTENDED_MINORITY:
               specialStatus1 = SpecialStatus.EXTENDED_MINORITY;
               break;
            case WHITE_CANE_EXTENDED_MINORITY:
               specialStatus1 = SpecialStatus.WHITE_CANE_EXTENDED_MINORITY;
               break;
            case YELLOW_CANE:
               specialStatus1 = SpecialStatus.YELLOW_CANE;
               break;
            case YELLOW_CANE_EXTENDED_MINORITY:
               specialStatus1 = SpecialStatus.YELLOW_CANE_EXTENDED_MINORITY;
               break;
            default:
               throw new IllegalArgumentException("Unexpected enum constant: " + specialStatus);
         }

         return specialStatus1;
      }
   }

   protected SpecialOrganisation specialOrganisationToSpecialOrganisation(be.fedict.commons.eid.consumer.SpecialOrganisation specialOrganisation) {
      if (specialOrganisation == null) {
         return null;
      } else {
         SpecialOrganisation specialOrganisation1;
         switch (specialOrganisation) {
            case UNSPECIFIED:
               specialOrganisation1 = SpecialOrganisation.UNSPECIFIED;
               break;
            case SHAPE:
               specialOrganisation1 = SpecialOrganisation.SHAPE;
               break;
            case NATO:
               specialOrganisation1 = SpecialOrganisation.NATO;
               break;
            case FORMER_BLUE_CARD_HOLDER:
               specialOrganisation1 = SpecialOrganisation.FORMER_BLUE_CARD_HOLDER;
               break;
            case RESEARCHER:
               specialOrganisation1 = SpecialOrganisation.RESEARCHER;
               break;
            case UNKNOWN:
               specialOrganisation1 = SpecialOrganisation.UNKNOWN;
               break;
            default:
               throw new IllegalArgumentException("Unexpected enum constant: " + specialOrganisation);
         }

         return specialOrganisation1;
      }
   }
}
