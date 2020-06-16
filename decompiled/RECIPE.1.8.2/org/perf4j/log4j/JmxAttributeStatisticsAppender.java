package org.perf4j.log4j;

import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.management.MBeanServer;
import javax.management.ObjectName;
import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.spi.LoggingEvent;
import org.perf4j.GroupedTimingStatistics;
import org.perf4j.helpers.AcceptableRangeConfiguration;
import org.perf4j.helpers.MiscUtils;
import org.perf4j.helpers.StatisticsExposingMBean;

public class JmxAttributeStatisticsAppender extends AppenderSkeleton {
   private String mBeanName = "org.perf4j:type=StatisticsExposingMBean,name=Perf4J";
   private String tagNamesToExpose;
   private String notificationThresholds;
   protected StatisticsExposingMBean mBean;

   public String getMBeanName() {
      return this.mBeanName;
   }

   public void setMBeanName(String mBeanName) {
      this.mBeanName = mBeanName;
   }

   public String getTagNamesToExpose() {
      return this.tagNamesToExpose;
   }

   public void setTagNamesToExpose(String tagNamesToExpose) {
      this.tagNamesToExpose = tagNamesToExpose;
   }

   public String getNotificationThresholds() {
      return this.notificationThresholds;
   }

   public void setNotificationThresholds(String notificationThresholds) {
      this.notificationThresholds = notificationThresholds;
   }

   public void activateOptions() {
      if (this.tagNamesToExpose == null) {
         throw new RuntimeException("You must set the TagNamesToExpose option before activating this appender");
      } else {
         String[] tagNames = MiscUtils.splitAndTrim(this.tagNamesToExpose, ",");
         List<AcceptableRangeConfiguration> rangeConfigs = new ArrayList();
         if (this.notificationThresholds != null) {
            String[] rangeConfigStrings = MiscUtils.splitAndTrim(this.notificationThresholds, ",");
            String[] arr$ = rangeConfigStrings;
            int len$ = rangeConfigStrings.length;

            for(int i$ = 0; i$ < len$; ++i$) {
               String rangeConfigString = arr$[i$];
               rangeConfigs.add(new AcceptableRangeConfiguration(rangeConfigString));
            }
         }

         this.mBean = new StatisticsExposingMBean(this.mBeanName, Arrays.asList(tagNames), rangeConfigs);

         try {
            MBeanServer mBeanServer = this.getMBeanServer();
            mBeanServer.registerMBean(this.mBean, new ObjectName(this.mBeanName));
         } catch (Exception var8) {
            throw new RuntimeException("Error registering statistics MBean: " + var8.getMessage(), var8);
         }
      }
   }

   protected void append(LoggingEvent event) {
      Object logMessage = event.getMessage();
      if (logMessage instanceof GroupedTimingStatistics && this.mBean != null) {
         this.mBean.updateCurrentTimingStatistics((GroupedTimingStatistics)logMessage);
      }

   }

   public boolean requiresLayout() {
      return false;
   }

   public void close() {
      try {
         MBeanServer mBeanServer = this.getMBeanServer();
         mBeanServer.unregisterMBean(new ObjectName(this.mBeanName));
      } catch (Exception var2) {
      }

   }

   protected MBeanServer getMBeanServer() {
      return ManagementFactory.getPlatformMBeanServer();
   }
}
