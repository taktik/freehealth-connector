package org.taktik.connector.technical.config.impl;

import org.taktik.connector.technical.config.Configuration;
import org.taktik.connector.technical.config.ConfigurationModule;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Enumeration;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConfigurationModuleVersion implements ConfigurationModule {
   private static final Logger LOG = LoggerFactory.getLogger(ConfigurationModuleVersion.class);

   public void init(Configuration config) throws TechnicalConnectorException {
      LOG.debug("Initializing ConfigurationModule " + this.getClass().getName());
      if (LOG.isDebugEnabled()) {
         this.getJarFromCP();
      }

   }

   public void getJarFromCP() {
      try {
         Enumeration resEnum = Thread.currentThread().getContextClassLoader().getResources("META-INF/MANIFEST.MF");
         String[] cpElements = ArrayUtils.EMPTY_STRING_ARRAY;

         while(resEnum.hasMoreElements()) {
            URL url = (URL)resEnum.nextElement();
            StringBuilder sb = new StringBuilder("[CP Content] ");
            String substringAfterLast = StringUtils.substringAfterLast(StringUtils.substringBefore(url.getPath(), "!"), "/");
            if (!"MANIFEST.MF".equals(substringAfterLast)) {
               sb.append(substringAfterLast);
               cpElements = (String[])((String[])ArrayUtils.add(cpElements, sb.toString()));
            }
         }

         Arrays.sort(cpElements);
         String[] arr$ = cpElements;
         int len$ = cpElements.length;

         for(int i$ = 0; i$ < len$; ++i$) {
            String cpElement = arr$[i$];
            LOG.debug(cpElement);
         }
      } catch (IOException var7) {
         LOG.error(var7.getMessage(), var7);
      }

   }

   public void unload() throws TechnicalConnectorException {
   }
}
