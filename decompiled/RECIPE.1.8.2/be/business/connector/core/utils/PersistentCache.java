package be.business.connector.core.utils;

import be.business.connector.core.exceptions.IntegrationModuleException;
import be.recipe.services.executor.GetPrescriptionForExecutorResult;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.xml.bind.JAXBException;
import org.apache.commons.lang3.Validate;
import org.apache.log4j.Logger;

public class PersistentCache<K, V> {
   private static final Logger LOG = Logger.getLogger(PersistentCache.class);
   private Map<K, V> cache = new LinkedHashMap();
   private final int maxSize;
   private final String archivingPath;
   private final PropertyHandler propertyHandler;

   public PersistentCache(PropertyHandler propertyHandler, PersistentCacheType cacheType) throws IntegrationModuleException {
      Validate.notNull(propertyHandler, "propertyHandler can't be Null", new Object[0]);
      Validate.notNull(cacheType, "cacheType can't be Null", new Object[0]);
      this.propertyHandler = propertyHandler;
      this.maxSize = this.getPropertyHandler().getIntegerProperty(cacheType + ".cache.size");
      this.archivingPath = this.getPropertyHandler().getProperty(cacheType + ".cache.directory");
      this.loadEntries();
   }

   private void loadEntries() throws IntegrationModuleException {
      try {
         LOG.info("archivingPath: " + this.archivingPath);
         File rootFolder = new File(this.archivingPath);
         if (!rootFolder.exists() || !rootFolder.isDirectory()) {
            rootFolder.mkdir();
         }

         File[] files = rootFolder.listFiles();
         Arrays.sort(files, new Comparator<File>() {
            public int compare(File f1, File f2) {
               return Long.valueOf(f2.lastModified()).compareTo(f1.lastModified());
            }
         });
         File[] var6 = files;
         int var5 = files.length;

         for(int var4 = 0; var4 < var5; ++var4) {
            File file = var6[var4];
            byte[] content = Files.readAllBytes(file.toPath());
            this.put(file.getName(), content);
         }

      } catch (Exception var8) {
         throw new IntegrationModuleException(var8);
      }
   }

   public Map<K, V> getInstance() {
      return this.cache;
   }

   public V get(K keyId) {
      Validate.notNull(keyId, "keyId can't be Null", new Object[0]);
      return this.cache.containsKey(keyId) ? this.cache.get(keyId) : null;
   }

   public boolean containsKey(String keyId) {
      return this.cache.containsKey(keyId);
   }

   public void put(K key, V value) {
      Validate.notNull(key, "key can't be Null", new Object[0]);
      Validate.notNull(value, "value can't be Null", new Object[0]);
      if (!this.cache.containsKey(key) && this.cache.keySet().size() == this.maxSize) {
         K lastKey = this.cache.keySet().toArray()[0];
         this.cache.remove(lastKey);
         this.remove(lastKey);
      }

      if (!this.cache.containsKey(key)) {
         this.writeKey(key, value);
         this.cache.put(key, value);
      }

   }

   public static String rewriteKey(String key) {
      Validate.notNull(key, "key can't be Null", new Object[0]);
      return key.replaceAll("\\/", "_");
   }

   private void remove(K keyId) {
      Validate.notNull(keyId, "keyId can't be Null", new Object[0]);
      File file = new File(this.cache + File.separator + keyId);
      file.delete();
   }

   private void writeKey(K keyId, V value) {
      Validate.notNull(keyId, "keyId can't be Null", new Object[0]);
      Validate.notNull(value, "value can't be Null", new Object[0]);

      try {
         File file = new File(this.archivingPath + File.separator + keyId);
         if (!file.exists()) {
            byte[] bytes = null;
            if (value instanceof byte[]) {
               bytes = (byte[])value;
            }

            if (value instanceof GetPrescriptionForExecutorResult) {
               MarshallerHelper<GetPrescriptionForExecutorResult, GetPrescriptionForExecutorResult> marshallerHelpe = new MarshallerHelper(GetPrescriptionForExecutorResult.class, GetPrescriptionForExecutorResult.class);
               bytes = marshallerHelpe.marsh((GetPrescriptionForExecutorResult)value).getBytes();
            }

            Files.write(file.toPath(), bytes, new OpenOption[]{StandardOpenOption.CREATE_NEW});
         }

      } catch (IOException | JAXBException var6) {
         throw new RuntimeException(var6);
      }
   }

   public void print() {
      Iterator iterator = this.cache.keySet().iterator();

      while(iterator.hasNext()) {
         K next = iterator.next();
         LOG.info(next + " " + this.cache.get(next));
      }

   }

   public PropertyHandler getPropertyHandler() {
      return this.propertyHandler;
   }

   public void clear() {
      this.cache.clear();
   }
}
