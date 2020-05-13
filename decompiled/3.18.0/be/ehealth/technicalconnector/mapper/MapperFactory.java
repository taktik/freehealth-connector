package be.ehealth.technicalconnector.mapper;

import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.mapper.impl.MapperDozerImpl;
import be.ehealth.technicalconnector.utils.ConfigurableFactoryHelper;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import org.apache.commons.codec.digest.DigestUtils;
import org.bouncycastle.util.encoders.Base64;

public final class MapperFactory {
   private static ConfigurableFactoryHelper<Mapper> helper = new ConfigurableFactoryHelper(MapperDozerImpl.class.getName(), MapperDozerImpl.class.getName());
   private static Map<String, Mapper> cache = new HashMap();

   private MapperFactory() {
   }

   public static Mapper getMapper(String... mappingFiles) {
      Set<String> mappingSet = new TreeSet();
      mappingSet.addAll(Arrays.asList(mappingFiles));
      MessageDigest complete = DigestUtils.getMd5Digest();
      Iterator i$ = mappingSet.iterator();

      while(i$.hasNext()) {
         String mapping = (String)i$.next();
         complete.update(mapping.getBytes());
      }

      String key = new String(Base64.encode(complete.digest()));
      if (!cache.containsKey(key)) {
         Map<String, Object> options = new HashMap();
         options.put("be.ehealth.technicalconnector.mapper.configfiles", mappingFiles);

         try {
            cache.put(key, helper.getImplementation(options));
         } catch (TechnicalConnectorException var6) {
            throw new IllegalArgumentException(var6);
         }
      }

      return (Mapper)cache.get(key);
   }
}
