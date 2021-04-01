package be.ehealth.technicalconnector.ws.domain;

import be.ehealth.technicalconnector.ws.feature.GenericFeature;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class FeatureLoader {
   private Map<Class, GenericFeature> activeFeatures = new HashMap();

   public <T extends GenericFeature> T getFeature(Class<T> clazz) {
      Iterator i$ = this.activeFeatures.entrySet().iterator();

      Entry entry;
      do {
         if (!i$.hasNext()) {
            return (GenericFeature)this.activeFeatures.get(clazz);
         }

         entry = (Entry)i$.next();
      } while(!clazz.isAssignableFrom((Class)entry.getKey()));

      return (GenericFeature)entry.getValue();
   }

   public boolean hasFeature(Class<?> clazz) {
      Iterator i$ = this.activeFeatures.keySet().iterator();

      Class key;
      do {
         if (!i$.hasNext()) {
            return this.activeFeatures.containsKey(clazz);
         }

         key = (Class)i$.next();
      } while(!clazz.isAssignableFrom(key));

      return true;
   }

   public void register(GenericFeature... features) {
      GenericFeature[] arr$ = features;
      int len$ = features.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         GenericFeature feature = arr$[i$];
         if (feature != null) {
            this.activeFeatures.put(feature.getClass(), feature);
         }
      }

   }

   public HandlerChain getHandlerChain() {
      HandlerChain handlerChain = new HandlerChain();
      Iterator i$ = this.activeFeatures.values().iterator();

      while(i$.hasNext()) {
         GenericFeature feature = (GenericFeature)i$.next();
         handlerChain.add(feature.getHandlers());
      }

      return handlerChain;
   }

   public Map<String, Object> getRequestMap() {
      Map<String, Object> requestMap = new HashMap();
      Iterator i$ = this.activeFeatures.values().iterator();

      while(i$.hasNext()) {
         GenericFeature feature = (GenericFeature)i$.next();
         if (feature != null) {
            requestMap.put(feature.getID(), feature.isEnabled());
            requestMap.putAll(feature.requestParamOptions());
         }
      }

      return requestMap;
   }
}
