package be.ehealth.technicalconnector.idgenerator.impl;

import be.ehealth.technicalconnector.idgenerator.IdGenerator;
import java.util.Random;

public class XSIDGenerator implements IdGenerator {
   private Random random = new Random();

   public String generateId() {
      long now = System.currentTimeMillis();
      long randomLong = this.random.nextLong();
      return "ID_" + now + "-" + randomLong;
   }
}
