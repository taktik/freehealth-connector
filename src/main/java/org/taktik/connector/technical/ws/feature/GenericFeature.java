package org.taktik.connector.technical.ws.feature;

import org.taktik.connector.technical.ws.domain.HandlerChain;
import java.util.HashMap;
import java.util.Map;

public abstract class GenericFeature {
   private boolean enabled;

   public abstract String getID();

   protected GenericFeature(boolean enabled) {
      this.enabled = enabled;
   }

   public boolean isEnabled() {
      return this.enabled;
   }

   public HandlerChain getHandlers() {
      return new HandlerChain();
   }

   public Map<String, Object> requestParamOptions() {
      return new HashMap();
   }
}
