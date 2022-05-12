package be.ehealth.technicalconnector.idgenerator.impl;

import be.ehealth.technicalconnector.idgenerator.IdGenerator;

public class NanoTimeGenerator implements IdGenerator {
   public NanoTimeGenerator() {
   }

   public String generateId() {
      long time = System.nanoTime();
      return Long.toString(time, 36).toUpperCase();
   }
}
