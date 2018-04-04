package org.taktik.connector.technical.mapper;

import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.mapper.impl.MapperDozerImpl;
import org.taktik.connector.technical.utils.ConfigurableFactoryHelper;
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
         options.put("org.taktik.connector.technical.mapper.configfiles", mappingFiles);

         try {
            cache.put(key, helper.getImplementation(options));
         } catch (TechnicalConnectorException var6) {
            throw new IllegalArgumentException(var6);
         }
      }

      return (Mapper)cache.get(key);
   }
}
