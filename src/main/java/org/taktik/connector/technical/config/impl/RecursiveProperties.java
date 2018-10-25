package org.taktik.connector.technical.config.impl;

import com.sun.javafx.runtime.SystemProperties;
import org.taktik.connector.technical.exception.ConfigurationException;
import org.taktik.connector.technical.utils.ConnectorIOUtils;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.Map.Entry;
import java.util.regex.Pattern;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RecursiveProperties extends SystemOverridenProperties {
   private static final Logger LOG = LoggerFactory.getLogger(RecursiveProperties.class);
   private Properties uddi = new Properties();
   private Map<String, RecursiveProperties.Lookup> lookups;

   private static Map<String, RecursiveProperties.Lookup> create(RecursiveProperties.Lookup... lookups) {
      Map<String, RecursiveProperties.Lookup> result = new RecursiveProperties.RegexHashMap();
      RecursiveProperties.Lookup[] arr$ = lookups;
      int len$ = lookups.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         RecursiveProperties.Lookup lookup = arr$[i$];
         result.put(lookup.getRegex(), lookup);
      }

      return result;
   }

   RecursiveProperties(InputStream... uddiStreams) {
      this.lookups = create(new RecursiveProperties.RecursiveLookup(this), new RecursiveProperties.UddiLookup(this.uddi, this), new RecursiveProperties.SystemLookup());
      InputStream[] arr$ = uddiStreams;
      int len$ = uddiStreams.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         InputStream uddiStream = arr$[i$];
         load(uddiStream, this.uddi);
      }

   }

   RecursiveProperties() {
      this.lookups = create(new RecursiveProperties.RecursiveLookup(this), new RecursiveProperties.UddiLookup(this.uddi, this), new RecursiveProperties.SystemLookup());
      load("/uddi/uddi-default.properties", this.uddi);
      load(this.getProperty("uddi.local.cache.dir", System.getProperty("java.io.tmpdir")) + File.separator + "uddi-local.properties", this.uddi);
   }

   private static void load(String location, Properties properties) {
      try {
         load(ConnectorIOUtils.getResourceAsStream(location, true), properties);
      } catch (Exception var3) {
         LOG.warn("Unable to add properties from location [" + location + "]");
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
      return this.getProperty(key, new ArrayList());
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
            value = ((RecursiveProperties.Lookup)this.lookups.get(value)).process(value, lookupKeys);
         }

         value = this.trimEndpoints(key, value);
         if (value != null && value.equals(key)) {
            value = null;
         }

         if (value == null) {
            //Desperate situations call for desperate measures...
            value = System.getProperty(key);
         }

         LOG.debug("Returning value [" + value + "] for property with key [" + key + "].");
         return value;
      }
   }

   private String trimEndpoints(String key, String value) {
      if (key.toLowerCase().startsWith("endpoint") && "true".equals(this.getProperty("remove.trail.withspaces", "true"))) {
         LOG.debug("Removing whitespace.");
         value = StringUtils.trim(value);
      }

      return value;
   }

   // $FF: synthetic class
   static class SyntheticClass_1 {
   }

   private static class SystemLookup extends RecursiveProperties.AbstractLookup {
      SystemLookup() {
         super("$system{", "}", (RecursiveProperties.SyntheticClass_1)null);
      }

      String getRefValue(String ref, List<String> lookupKeys) {
         RecursiveProperties.LOG.debug("Looking system-key [" + ref + "]");
         return System.getProperty(ref);
      }
   }

   private static class UddiLookup extends RecursiveProperties.AbstractLookup {
      private Properties uddi;
      private Properties config;

      public UddiLookup(Properties uddi, Properties config) {
         super("$uddi{", "}", (RecursiveProperties.SyntheticClass_1)null);
         this.uddi = uddi;
         this.config = config;
      }

      String getRefValue(String ref, List<String> lookupKeys) {
         String env = this.config.getProperty("environment", "prd");
         String uddiKey = env + "-" + ref;
         RecursiveProperties.LOG.debug("Looking uddi-key [" + uddiKey + "]");
         return this.uddi.getProperty(uddiKey);
      }
   }

   private static class RecursiveLookup extends RecursiveProperties.AbstractLookup {
      private RecursiveProperties props;

      public RecursiveLookup(RecursiveProperties props) {
         super("${", "}", (RecursiveProperties.SyntheticClass_1)null);
         this.props = props;
      }

      String getRefValue(String ref, List<String> lookupKeys) {
         return this.props.getProperty(ref, lookupKeys);
      }
   }

   private abstract static class AbstractLookup implements RecursiveProperties.Lookup {
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
               Iterator i$ = refs.iterator();

               while(i$.hasNext()) {
                  String ref = (String)i$.next();
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

      // $FF: synthetic method
      AbstractLookup(String x0, String x1, RecursiveProperties.SyntheticClass_1 x2) {
         this(x0, x1);
      }
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
         Iterator i$ = this.matcherMap.entrySet().iterator();

         Entry matcher;
         do {
            if (!i$.hasNext()) {
               return super.get(key);
            }

            matcher = (Entry)i$.next();
         } while(!((Pattern)matcher.getKey()).matcher(key.toString()).matches());

         return (V) matcher.getValue();
      }

      public boolean containsKey(Object key) {
         Iterator i$ = this.matcherMap.entrySet().iterator();

         Entry matcher;
         do {
            if (!i$.hasNext()) {
               return super.containsKey(key);
            }

            matcher = (Entry)i$.next();
         } while(!((Pattern)matcher.getKey()).matcher(key.toString()).matches());

         return true;
      }

      public V put(K key, V value) {
         this.matcherMap.put(Pattern.compile(key.toString()), value);
         return super.put(key, value);
      }

      // $FF: synthetic method
      RegexHashMap(RecursiveProperties.SyntheticClass_1 x0) {
         this();
      }
   }
}
