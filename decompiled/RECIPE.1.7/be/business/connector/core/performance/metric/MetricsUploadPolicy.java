package org.taktik.connector.business.recipeprojects.core.performance.metric;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import org.apache.log4j.Appender;
import org.apache.log4j.Logger;
import org.apache.log4j.rolling.RollingPolicy;
import org.apache.log4j.rolling.RolloverDescription;
import org.apache.log4j.rolling.TimeBasedRollingPolicy;
import org.apache.log4j.rolling.TriggeringPolicy;
import org.apache.log4j.rolling.helper.FileRenameAction;
import org.apache.log4j.spi.LoggingEvent;
import org.apache.log4j.spi.OptionHandler;

@Deprecated
public class MetricsUploadPolicy implements RollingPolicy, OptionHandler, TriggeringPolicy {
   private static final Logger LOG = Logger.getLogger(MetricsUploadPolicy.class);
   private TimeBasedRollingPolicy tbrp = new TimeBasedRollingPolicy();
   private File destination;
   private Field destinationField;

   public RolloverDescription rollover(String currentActiveFile) {
      RolloverDescription desc = this.tbrp.rollover(currentActiveFile);
      this.destination = this.getDestination(desc);
      return desc;
   }

   private File getDestination(RolloverDescription desc) {
      if (desc != null) {
         try {
            FileRenameAction fra = (FileRenameAction)desc.getSynchronous();
            if (this.destinationField == null) {
               Field[] var6;
               int var5 = (var6 = fra.getClass().getDeclaredFields()).length;

               for(int var4 = 0; var4 < var5; ++var4) {
                  Field f = var6[var4];
                  if (f.getName().equalsIgnoreCase("source") && Modifier.isPrivate(f.getModifiers())) {
                     f.setAccessible(true);
                     this.destinationField = f;
                     break;
                  }
               }
            }

            return (File)this.destinationField.get(fra);
         } catch (IllegalArgumentException var7) {
            LOG.error("Could not get metrics upload file.", var7);
         } catch (IllegalAccessException var8) {
            LOG.error("Could not get metrics upload file.", var8);
         }
      }

      return null;
   }

   public void activateOptions() {
      this.tbrp.activateOptions();
   }

   public boolean equals(Object obj) {
      return this.tbrp.equals(obj);
   }

   public String getActiveFileName() {
      return this.tbrp.getActiveFileName();
   }

   public String getFileNamePattern() {
      return this.tbrp.getFileNamePattern();
   }

   public int hashCode() {
      return this.tbrp.hashCode();
   }

   public RolloverDescription initialize(String currentActiveFile, boolean append) {
      return this.tbrp.initialize(currentActiveFile, append);
   }

   public boolean isTriggeringEvent(Appender appender, LoggingEvent event, String filename, long fileLength) {
      return this.tbrp.isTriggeringEvent(appender, event, filename, fileLength);
   }

   public void setActiveFileName(String afn) {
      this.tbrp.setActiveFileName(afn);
   }

   public void setFileNamePattern(String fnp) {
      this.tbrp.setFileNamePattern(fnp);
   }

   public String toString() {
      return this.tbrp.toString();
   }

   public File getDestination() {
      return this.destination;
   }
}
