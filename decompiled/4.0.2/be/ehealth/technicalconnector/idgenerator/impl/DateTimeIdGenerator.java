package be.ehealth.technicalconnector.idgenerator.impl;

import be.ehealth.technicalconnector.idgenerator.IdGenerator;
import org.joda.time.DateTime;

public class DateTimeIdGenerator implements IdGenerator {
   public DateTimeIdGenerator() {
   }

   public String generateId() {
      DateTime currentDateTime = new DateTime();
      return currentDateTime.toString("yyyyMMddHHmmss");
   }
}
