package be.ehealth.technicalconnector.ws.feature;

import java.util.List;
import javax.xml.ws.handler.Handler;

public abstract class GenericFeature {
   protected boolean enabled;

   public abstract String getID();

   public boolean isEnabled() {
      return this.enabled;
   }

   public abstract List<Handler<?>> getHandlers();
}
