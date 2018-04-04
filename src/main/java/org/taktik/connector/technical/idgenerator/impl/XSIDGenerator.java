package org.taktik.connector.technical.idgenerator.impl;

import org.taktik.connector.technical.idgenerator.IdGenerator;
import java.util.Random;

public class XSIDGenerator implements IdGenerator {
   private Random random = new Random();

   public String generateId() {
      long now = System.currentTimeMillis();
      long randomLong = this.random.nextLong();
      return "ID_" + now + "-" + randomLong;
   }
}
