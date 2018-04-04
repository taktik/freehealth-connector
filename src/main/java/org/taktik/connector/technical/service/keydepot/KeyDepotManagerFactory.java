package org.taktik.connector.technical.service.keydepot;

import org.taktik.connector.technical.service.keydepot.impl.KeyDepotManagerImpl;

public final class KeyDepotManagerFactory {
   private KeyDepotManagerFactory() {
      throw new UnsupportedOperationException();
   }

   public static KeyDepotManager getKeyDepotManager() {
      return KeyDepotManagerImpl.getInstance();
   }
}
