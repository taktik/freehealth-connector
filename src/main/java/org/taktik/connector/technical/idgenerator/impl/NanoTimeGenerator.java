package org.taktik.connector.technical.idgenerator.impl;

import org.taktik.connector.technical.idgenerator.IdGenerator;

public class NanoTimeGenerator implements IdGenerator {
   public String generateId() {
      long time = System.nanoTime();
      return Long.toString(time, 36).toUpperCase();
   }
}
