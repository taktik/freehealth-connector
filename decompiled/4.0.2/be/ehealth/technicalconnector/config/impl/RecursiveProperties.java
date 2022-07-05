package be.ehealth.technicalconnector.config.impl;

import be.ehealth.technicalconnector.exception.ConfigurationException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.utils.ConnectorIOUtils;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.Vector;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RecursiveProperties extends Properties {
   private static final Logger LOG = LoggerFactory.getLogger(RecursiveProperties.class);
   private Properties uddi;
   private Map<String, Lookup> lookups;

   private static Map<String, Lookup> create(Lookup... lookups) {
      Map<String, Lookup> result = new RegexHashMap();
      Lookup[] var2 = lookups;
      int var3 = lookups.length;

      for(int var4 = 0; var4 < var3; ++var4) {
         Lookup lookup = var2[var4];
         result.put(lookup.getRegex(), lookup);
      }

      return result;
   }

   RecursiveProperties(InputStream... uddiStreams) {
      this.uddi = new Properties();
      this.lookups = create(new RecursiveLookup(this), new UddiLookup(this.uddi, this), new SystemLookup());
      InputStream[] var2 = uddiStreams;
      int var3 = uddiStreams.length;

      for(int var4 = 0; var4 < var3; ++var4) {
         InputStream uddiStream = var2[var4];
         load(uddiStream, this.uddi);
      }

   }

   RecursiveProperties(boolean load) throws TechnicalConnectorException {
      this.uddi = new Properties();
      this.lookups = create(new RecursiveLookup(this), new UddiLookup(this.uddi, this), new SystemLookup());
      if (load) {
         this.defaultLoad();
      }

   }

   RecursiveProperties() throws TechnicalConnectorException {
      this(true);
   }

   public synchronized Enumeration<Object> keys() {
      Enumeration<Object> keysEnum = super.keys();
      Vector<Object> keyList = new Vector();

      while(keysEnum.hasMoreElements()) {
         keyList.add(keysEnum.nextElement());
      }

      Collections.sort(keyList, new Comparator<Object>() {
         public int compare(Object o1, Object o2) {
            return o1.toString().compareTo(o2.toString());
         }
      });
      return keyList.elements();
   }

   private void defaultLoad() throws TechnicalConnectorException {
      load("/uddi/uddi-default.properties", this.uddi);
      load(this.getProperty("uddi.local.cache.dir", ConnectorIOUtils.getTempFileLocation("uddi-local.properties")), this.uddi);
   }

   private static void load(String location, Properties properties) {
      try {
         load(ConnectorIOUtils.getResourceAsStream(location, true), properties);
      } catch (Exception var3) {
         LOG.warn("Unable to add properties from location [{}]", location);
      }

   }

   private static void load(InputStream is, Properties properties) {
      try {
         Properties props = new Properties();
         props.load(is);
         properties.putAll(props);
      } catch (Exception var6) {
         LOG.warn("Unable to add properties.", var6);
      } finally {
         ConnectorIOUtils.closeQuietly((Object)is);
      }

   }

   public String getProperty(String key, String defaultValue) {
      LOG.debug("Resolving property for key [{}] with default value[{}]", key, defaultValue);
      String val = this.getProperty(key);
      if (val == null && defaultValue != null) {
         LOG.debug("Resolving defaultValue [{}]", defaultValue);
         val = this.getProperty(defaultValue);
         if (val == null) {
            val = defaultValue;
         }
      }

      return val;
   }

   public String getProperty(String key) {
      return this.getProperty(key, (List)(new ArrayList()));
   }

   private String getProperty(String key, List<String> lookupKeys) {
      if (key == null) {
         return null;
      } else {
         String value = super.getProperty(key);
         if (value == null) {
            value = key;
         }

         while(value != null && this.lookups.containsKey(value)) {
            value = ((Lookup)this.lookups.get(value)).process(value, lookupKeys);
         }

         if (value != null && value.equals(key)) {
            value = null;
         }

         LOG.debug("Returning value [{}] for property with key [{}].", value, key);
         return value;
      }
   }

   private static class SystemLookup extends AbstractLookup {
      SystemLookup() {
         super("$system{", "}", null);
      }

      String getRefValue(String ref, List<String> lookupKeys) {
         RecursiveProperties.LOG.debug("Looking system-key [{}]", ref);
         return System.getProperty(ref);
      }
   }

   private static class UddiLookup extends AbstractLookup {
      private Properties uddi;
      private Properties config;

      public UddiLookup(Properties uddi, Properties config) {
         super("$uddi{", "}", null);
         this.uddi = uddi;
         this.config = config;
      }

      String getRefValue(String ref, List<String> lookupKeys) {
         String env = this.config.getProperty("environment", "prd");
         String uddiKey = env + "-" + ref;
         RecursiveProperties.LOG.debug("Looking uddi-key [{}]", uddiKey);
         return this.uddi.getProperty(uddiKey);
      }
   }

   private static class RecursiveLookup extends AbstractLookup {
      private RecursiveProperties props;

      public RecursiveLookup(RecursiveProperties props) {
         super("${", "}", null);
         this.props = props;
      }

      String getRefValue(String ref, List<String> lookupKeys) {
         return this.props.getProperty(ref, lookupKeys);
      }
   }

   private abstract static class AbstractLookup implements Lookup {
      private final String regex;
      private Pattern pattern;
      private String startTag;
      private String endTag;

      private AbstractLookup(String startTag, String endTag) {
         this.regex = ".*" + Pattern.quote(startTag) + ".*" + Pattern.quote(endTag) + ".*";
         this.pattern = Pattern.compile(this.regex);
         this.startTag = startTag;
         this.endTag = endTag;
      }

      public String getRegex() {
         return this.regex;
      }

      public String process(String value, List<String> lookupKeys) {
         RecursiveProperties.LOG.debug("Lookup value [{}]", value);
         if (value == null) {
            return null;
         } else {
            if (this.pattern.matcher(value).matches()) {
               Set<String> refs = new HashSet(Arrays.asList(StringUtils.substringsBetween(value, this.startTag, this.endTag)));
               Iterator var4 = refs.iterator();

               while(var4.hasNext()) {
                  String ref = (String)var4.next();
                  if (lookupKeys.contains(ref)) {
                     throw new ConfigurationException("A circular reference detected.");
                  }

                  lookupKeys.add(ref);
                  String refValue = this.getRefValue(ref, lookupKeys);
                  String refTag = this.startTag + ref + this.endTag;
                  if (refValue != null) {
                     value = value.replace(refTag, refValue);
                  } else if (!value.equals(refTag)) {
                     value = value.replace(refTag, "");
                  } else {
                     value = null;
                  }
               }
            }

            RecursiveProperties.LOG.debug("Returning value [{}]", value);
            return value;
         }
      }

      abstract String getRefValue(String var1, List<String> var2);
   }

   private interface Lookup {
      String getRegex();

      String process(String var1, List<String> var2);
   }

   private static class RegexHashMap<K, V> extends HashMap<K, V> {
      private Map<Pattern, V> matcherMap;

      private RegexHashMap() {
         this.matcherMap = new HashMap();
      }

      public V get(Object key) {
         Iterator var2 = this.matcherMap.entrySet().iterator();

         Map.Entry matcher;
         do {
            if (!var2.hasNext()) {
               return super.get(key);
            }

            matcher = (Map.Entry)var2.next();
         } while(!((Pattern)matcher.getKey()).matcher(key.toString()).matches());

         return matcher.getValue();
      }

      public boolean containsKey(Object key) {
         Iterator var2 = this.matcherMap.entrySet().iterator();

         Map.Entry matcher;
         do {
            if (!var2.hasNext()) {
               return super.containsKey(key);
            }

            matcher = (Map.Entry)var2.next();
         } while(!((Pattern)matcher.getKey()).matcher(key.toString()).matches());

         return true;
      }

      public V put(K key, V value) {
         this.matcherMap.put(Pattern.compile(key.toString()), value);
         return super.put(key, value);
      }
   }
}
