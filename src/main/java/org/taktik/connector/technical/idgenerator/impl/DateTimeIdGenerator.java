package org.taktik.connector.technical.idgenerator.impl;

import org.taktik.connector.technical.idgenerator.IdGenerator;
import org.joda.time.DateTime;

public class DateTimeIdGenerator implements IdGenerator {
   public String generateId() {
      DateTime currentDateTime = new DateTime();
      return currentDateTime.toString("yyyyMMddHHmmss");
   }
}
