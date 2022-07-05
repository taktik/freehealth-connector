package be.ehealth.technicalconnector.ws.domain;

import be.ehealth.technicalconnector.ws.feature.GenericFeature;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class FeatureLoader {
   private Map<Class, GenericFeature> activeFeatures = new HashMap();

   public FeatureLoader() {
   }

   public <T extends GenericFeature> T getFeature(Class<T> clazz) {
      Iterator var2 = this.activeFeatures.entrySet().iterator();

      Map.Entry entry;
      do {
         if (!var2.hasNext()) {
            return (GenericFeature)this.activeFeatures.get(clazz);
         }

         entry = (Map.Entry)var2.next();
      } while(!clazz.isAssignableFrom((Class)entry.getKey()));

      return (GenericFeature)entry.getValue();
   }

   public boolean hasFeature(Class<?> clazz) {
      Iterator var2 = this.activeFeatures.keySet().iterator();

      Class key;
      do {
         if (!var2.hasNext()) {
            return this.activeFeatures.containsKey(clazz);
         }

         key = (Class)var2.next();
      } while(!clazz.isAssignableFrom(key));

      return true;
   }

   public void register(GenericFeature... features) {
      GenericFeature[] var2 = features;
      int var3 = features.length;

      for(int var4 = 0; var4 < var3; ++var4) {
         GenericFeature feature = var2[var4];
         if (feature != null) {
            this.activeFeatures.put(feature.getClass(), feature);
         }
      }

   }

   public HandlerChain getHandlerChain() {
      HandlerChain handlerChain = new HandlerChain();
      Iterator var2 = this.activeFeatures.values().iterator();

      while(var2.hasNext()) {
         GenericFeature feature = (GenericFeature)var2.next();
         handlerChain.add(feature.getHandlers());
      }

      return handlerChain;
   }

   public Map<String, Object> getRequestMap() {
      Map<String, Object> requestMap = new HashMap();
      Iterator var2 = this.activeFeatures.values().iterator();

      while(var2.hasNext()) {
         GenericFeature feature = (GenericFeature)var2.next();
         if (feature != null) {
            requestMap.put(feature.getID(), feature.isEnabled());
            requestMap.putAll(feature.requestParamOptions());
         }
      }

      return requestMap;
   }
}
