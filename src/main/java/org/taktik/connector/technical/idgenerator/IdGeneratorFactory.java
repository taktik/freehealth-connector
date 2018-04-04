package org.taktik.connector.technical.idgenerator;

import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.idgenerator.impl.DateTimeIdGenerator;
import org.taktik.connector.technical.idgenerator.impl.NanoTimeGenerator;
import org.taktik.connector.technical.idgenerator.impl.TimeBasedUniqueKeyGenerator;
import org.taktik.connector.technical.idgenerator.impl.UUIDGenerator;
import org.taktik.connector.technical.idgenerator.impl.XSIDGenerator;
import org.taktik.connector.technical.utils.ConfigurableFactoryHelper;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class IdGeneratorFactory {
   private static final Logger LOG = LoggerFactory.getLogger(IdGeneratorFactory.class);
   public static final String PROPS_IDGENERATOR_PREFIX = "org.taktik.connector.technical.idgenerator.";
   public static final String PROPS_IDGENERATOR_SUFFIX = ".classname";
   public static final String DEFAULT = "default";
   public static final String UUID = "uuid";
   public static final String XSID = "xsid";
   public static final String TIMEBASED = "time";
   public static final String NANO = "nano";
   private static final String DEFAULT_INPUT_REF_GENERATOR_CHECKER_CLASS = DateTimeIdGenerator.class.getName();
   private static Map<String, IdGenerator> cachedInstance = new HashMap();
   private static Map<String, String> defaultGeneratorClasses = new HashMap();

   public static IdGenerator getIdGenerator() throws TechnicalConnectorException {
      return getIdGenerator("default");
   }

   public static IdGenerator getIdGenerator(String type) throws TechnicalConnectorException {
      if (!cachedInstance.containsKey(type)) {
         String defaultimpl = StringUtils.defaultString((String)defaultGeneratorClasses.get(type), DEFAULT_INPUT_REF_GENERATOR_CHECKER_CLASS);
         ConfigurableFactoryHelper<IdGenerator> helper = new ConfigurableFactoryHelper("org.taktik.connector.technical.idgenerator." + type + ".classname", defaultimpl);
         cachedInstance.put(type, helper.getImplementation());
      }

      return (IdGenerator)cachedInstance.get(type);
   }

   public static void invalidateCachedInstance() {
      cachedInstance.clear();
   }

   public static void registerDefaultImplementation(String type, Class<? extends IdGenerator> clazz) {
      if (defaultGeneratorClasses.containsKey(type)) {
         LOG.warn("Default implementation already exist for type [" + type + "] with value [" + (String)defaultGeneratorClasses.get(type) + "] replaced by" + clazz.getName());
      }

      defaultGeneratorClasses.put(type, clazz.getName());
   }

   static {
      defaultGeneratorClasses.put("uuid", UUIDGenerator.class.getName());
      defaultGeneratorClasses.put("xsid", XSIDGenerator.class.getName());
      defaultGeneratorClasses.put("default", DEFAULT_INPUT_REF_GENERATOR_CHECKER_CLASS);
      defaultGeneratorClasses.put("time", TimeBasedUniqueKeyGenerator.class.getName());
      defaultGeneratorClasses.put("nano", NanoTimeGenerator.class.getName());
   }
}
