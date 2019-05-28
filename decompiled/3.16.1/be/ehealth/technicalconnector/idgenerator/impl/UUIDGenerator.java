package be.ehealth.technicalconnector.idgenerator.impl;

import be.ehealth.technicalconnector.idgenerator.IdGenerator;
import java.util.UUID;

public class UUIDGenerator implements IdGenerator {
   public String generateId() {
      return UUID.randomUUID().toString();
   }
}
