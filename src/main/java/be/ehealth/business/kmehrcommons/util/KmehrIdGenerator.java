package be.ehealth.business.kmehrcommons.util;

import org.taktik.connector.technical.idgenerator.IdGenerator;
import org.joda.time.DateTime;

public class KmehrIdGenerator implements IdGenerator {
   public static final String KMEHR_ID_GENERATOR_TAG = "kmehr";

   public String generateId() {
      DateTime currentDateTime = new DateTime();
      return currentDateTime.toString("yyyyMMddHHmmss");
   }
}
