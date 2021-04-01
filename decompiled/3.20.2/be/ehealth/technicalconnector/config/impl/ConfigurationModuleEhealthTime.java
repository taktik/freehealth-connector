package be.ehealth.technicalconnector.config.impl;

import be.ehealth.technicalconnector.config.Configuration;
import be.ehealth.technicalconnector.config.ConfigurationModule;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.utils.ConnectorIOUtils;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConfigurationModuleEhealthTime implements ConfigurationModule {
   public static final String SYSPROP_EHEALTH_TIME_ACTIVE = "be.ehealth.technicalconnector.config.impl.configurationmodule.ehealth.time.active";
   private static final Logger LOG = LoggerFactory.getLogger(ConfigurationModuleEhealthTime.class);
   private SimpleDateFormat sdf;

   public ConfigurationModuleEhealthTime() {
      this.sdf = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz", Locale.US);
   }

   public void init(Configuration config) throws TechnicalConnectorException {
      if (!"false".equals(System.getProperty("be.ehealth.technicalconnector.config.impl.configurationmodule.ehealth.time.active", "true")) && LOG.isDebugEnabled()) {
         HttpURLConnection conn = null;

         try {
            URL url = new URL("https://services.ehealth.fgov.be");
            conn = (HttpURLConnection)url.openConnection();
            long requestTime = (new Date()).getTime();
            conn.setRequestMethod("OPTIONS");
            conn.connect();
            if (LOG.isDebugEnabled()) {
               Date eHealthTime = this.sdf.parse(conn.getHeaderField("Date"));
               long diffInMillies = eHealthTime.getTime() - requestTime;
               long diff = TimeUnit.SECONDS.convert(Math.abs(diffInMillies), TimeUnit.MILLISECONDS);
               LOG.debug("eHealth time           : {}", this.sdf.format(eHealthTime));
               LOG.debug("Local time             : {}", this.sdf.format(requestTime));
               LOG.debug("diff with ehealth time : {}{} seconds.", 0L > diff ? "-" : "", diff);
            }
         } catch (Exception var14) {
            LOG.error("Unable to calculate ehealth time", var14);
         } finally {
            ConnectorIOUtils.closeQuietly((Object)conn);
         }

      }
   }

   public void unload() throws TechnicalConnectorException {
   }
}
