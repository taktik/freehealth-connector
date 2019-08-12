package be.ehealth.technicalconnector.utils.impl;

import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.Validate;
import org.bouncycastle.util.encoders.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class JaxbContextFactory {
   /** @deprecated */
   @Deprecated
   public static final String PROP_CACHE_TYPE = "be.ehealth.technicalconnector.utils.impl.JaxbContextFactory.cache_type";
   private static final Logger LOG = LoggerFactory.getLogger(JaxbContextFactory.class);
   private static final Map<String, JAXBContext> CACHE = new HashMap();

   private JaxbContextFactory() {
      throw new UnsupportedOperationException();
   }

   public static void initJaxbContext(Package packageInstance) {
      try {
         getJaxbContextForPackage(packageInstance);
      } catch (Exception var2) {
         LOG.warn("Unable to load JaxbContext for " + packageInstance, var2);
      }

   }

   public static void initJaxbContext(Class... classesToBeBound) {
      try {
         getJaxbContextForClass(classesToBeBound);
      } catch (JAXBException var2) {
         LOG.warn("Unable to load JaxbContext for " + ArrayUtils.toString(classesToBeBound), var2);
      }

   }

   public static JAXBContext getJaxbContextForPackage(Package pack) {
      Validate.notNull(pack);

      try {
         String key = pack.getName();
         JAXBContext context = (JAXBContext)CACHE.get(key);
         if (context == null) {
            context = JAXBContext.newInstance(key);
            CACHE.put(key, context);
         }

         return context;
      } catch (JAXBException var3) {
         throw new IllegalArgumentException("Unable to create jaxbContext for package" + pack.getName());
      }
   }

   public static JAXBContext getJaxbContextForClass(Class... classesToBeBound) throws JAXBException {
      String key = calculateKey(classesToBeBound);
      JAXBContext context = (JAXBContext)CACHE.get(key);
      if (context == null) {
         context = JAXBContext.newInstance(classesToBeBound);
         CACHE.put(key, context);
      }

      return context;
   }

   private static String calculateKey(Class... classesToBeBound) {
      Set<String> keyList = new TreeSet();
      Class[] arr$ = classesToBeBound;
      int len$ = classesToBeBound.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         Class<?> classToBeBound = arr$[i$];
         keyList.add(classToBeBound.getName());
      }

      if (keyList.size() == 1) {
         return (String)keyList.iterator().next();
      } else {
         MessageDigest complete = DigestUtils.getMd5Digest();
         Iterator i$ = keyList.iterator();

         while(i$.hasNext()) {
            String clazz = (String)i$.next();
            complete.update(clazz.getBytes());
         }

         return new String(Base64.encode(complete.digest()));
      }
   }

   public static void reset() {
      CACHE.clear();
   }
}
