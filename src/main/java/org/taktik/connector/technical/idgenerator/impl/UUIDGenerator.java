package org.taktik.connector.technical.idgenerator.impl;

import org.taktik.connector.technical.idgenerator.IdGenerator;
import java.util.UUID;

public class UUIDGenerator implements IdGenerator {
   public String generateId() {
      return UUID.randomUUID().toString();
   }
}
