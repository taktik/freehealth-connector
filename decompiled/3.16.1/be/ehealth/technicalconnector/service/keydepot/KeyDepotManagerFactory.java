package be.ehealth.technicalconnector.service.keydepot;

import be.ehealth.technicalconnector.service.keydepot.impl.KeyDepotManagerImpl;

public final class KeyDepotManagerFactory {
   private KeyDepotManagerFactory() {
      throw new UnsupportedOperationException();
   }

   public static KeyDepotManager getKeyDepotManager() {
      return KeyDepotManagerImpl.getInstance();
   }
}
