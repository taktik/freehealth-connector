package be.fgov.ehealth.standards.kmehr.schema.v1;

import java.math.BigDecimal;
import java.math.BigInteger;
import javax.xml.bind.JAXBElement;
import org.joda.time.DateTime;

public class RegimenFactory {
   private static ObjectFactory fact = new ObjectFactory();

   public RegimenFactory() {
   }

   public static JAXBElement<BigInteger> createDayNumber(BigInteger number) {
      return fact.createRegimenDaynumber(number);
   }

   public static JAXBElement<DateTime> createDate(DateTime date) {
      return fact.createRegimenDate(date);
   }

   public static JAXBElement<Daytime> createDaytime(Daytime date) {
      return fact.createRegimenDaytime(date);
   }

   public static JAXBElement<Weekday> createWeekday(Weekday date) {
      return fact.createRegimenWeekday(date);
   }

   public static JAXBElement<AdministrationquantityType> createQuantity(BigDecimal decimal) {
      return createQuantity(decimal, (AdministrationunitType)null);
   }

   public static JAXBElement<AdministrationquantityType> createQuantity(BigDecimal decimal, AdministrationunitType administrationunitType) {
      AdministrationquantityType administrationquantityType = new AdministrationquantityType();
      administrationquantityType.setDecimal(decimal);
      administrationquantityType.setUnit(administrationunitType);
      return fact.createRegimenQuantity(administrationquantityType);
   }
}
