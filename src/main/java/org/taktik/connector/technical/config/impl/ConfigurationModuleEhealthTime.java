package org.taktik.connector.technical.config.impl;

import org.taktik.connector.technical.config.Configuration;
import org.taktik.connector.technical.config.ConfigurationModule;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.utils.ConnectorIOUtils;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConfigurationModuleEhealthTime implements ConfigurationModule {
   private static final Logger LOG = LoggerFactory.getLogger(ConfigurationModuleEhealthTime.class);
   private static final SimpleDateFormat FORMAT;

   public void init(Configuration config) throws TechnicalConnectorException {
      if (LOG.isDebugEnabled()) {
         HttpURLConnection conn = null;

         try {
            URL url = new URL("http://services.ehealth.fgov.be");
            conn = (HttpURLConnection)url.openConnection();
            long requestTime = (new Date()).getTime();
            conn.setRequestMethod("GET");
            Date eHealthTime = FORMAT.parse(conn.getHeaderField("Date"));
            long diffInMillies = eHealthTime.getTime() - requestTime;
            long diff = TimeUnit.SECONDS.convert(Math.abs(diffInMillies), TimeUnit.MILLISECONDS);
            LOG.debug("eHealth time           : " + FORMAT.format(eHealthTime));
            LOG.debug("Local time             : " + FORMAT.format(requestTime));
            LOG.debug("diff with ehealth time : " + (0L > diff ? "-" : "") + diff + " seconds.");
         } catch (Exception var14) {
            LOG.error("Unable to calculate ehealth time", var14);
         } finally {
            ConnectorIOUtils.closeQuietly((Object)conn);
         }

      }
   }

   public void unload() throws TechnicalConnectorException {
   }

   static {
      FORMAT = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz", Locale.US);
   }
}
