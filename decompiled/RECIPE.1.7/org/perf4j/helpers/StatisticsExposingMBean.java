package org.perf4j.helpers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.management.Attribute;
import javax.management.AttributeList;
import javax.management.AttributeNotFoundException;
import javax.management.DynamicMBean;
import javax.management.InvalidAttributeValueException;
import javax.management.MBeanAttributeInfo;
import javax.management.MBeanConstructorInfo;
import javax.management.MBeanException;
import javax.management.MBeanInfo;
import javax.management.MBeanNotificationInfo;
import javax.management.MBeanOperationInfo;
import javax.management.MBeanParameterInfo;
import javax.management.MalformedObjectNameException;
import javax.management.Notification;
import javax.management.NotificationBroadcasterSupport;
import javax.management.ObjectName;
import javax.management.ReflectionException;
import org.perf4j.GroupedTimingStatistics;
import org.perf4j.TimingStatistics;

public class StatisticsExposingMBean extends NotificationBroadcasterSupport implements DynamicMBean {
   public static final String DEFAULT_MBEAN_NAME = "org.perf4j:type=StatisticsExposingMBean,name=Perf4J";
   public static final String OUT_OF_RANGE_NOTIFICATION_TYPE = "org.perf4j.threshold.exceeded";
   protected ObjectName mBeanName;
   protected MBeanInfo managementInterface;
   protected Collection<String> tagsToExpose;
   protected Map<AcceptableRangeConfiguration, Boolean> acceptableRanges;
   protected ExecutorService outOfRangeNotifierThread;
   protected long outOfRangeNotificationSeqNo;
   protected GroupedTimingStatistics currentTimingStatistics;
   protected Pattern attributeNamePattern = Pattern.compile("(.*)(Mean|StdDev|Min|Max|Count|TPS)");

   public StatisticsExposingMBean(String mBeanName, Collection<String> tagsToExpose, Collection<AcceptableRangeConfiguration> acceptableRanges) {
      if (mBeanName == null) {
         mBeanName = "org.perf4j:type=StatisticsExposingMBean,name=Perf4J";
      }

      try {
         this.mBeanName = new ObjectName(mBeanName);
      } catch (MalformedObjectNameException var6) {
         throw new IllegalArgumentException(var6);
      }

      if (acceptableRanges != null && !acceptableRanges.isEmpty()) {
         this.acceptableRanges = new LinkedHashMap();
         Iterator i$ = acceptableRanges.iterator();

         while(i$.hasNext()) {
            AcceptableRangeConfiguration acceptableRange = (AcceptableRangeConfiguration)i$.next();
            this.acceptableRanges.put(acceptableRange, Boolean.TRUE);
            if (!this.attributeNamePattern.matcher(acceptableRange.getAttributeName()).matches()) {
               throw new IllegalArgumentException("Acceptable range attribute name " + acceptableRange.getAttributeName() + " invalid - must match pattern " + this.attributeNamePattern.pattern());
            }
         }

         this.outOfRangeNotifierThread = Executors.newSingleThreadExecutor();
      } else {
         this.acceptableRanges = Collections.emptyMap();
      }

      this.tagsToExpose = new ArrayList(tagsToExpose);
      this.managementInterface = this.createMBeanInfoFromTagNames(tagsToExpose);
      this.currentTimingStatistics = new GroupedTimingStatistics();
   }

   public synchronized void updateCurrentTimingStatistics(GroupedTimingStatistics currentTimingStatistics) {
      if (currentTimingStatistics == null) {
         throw new IllegalArgumentException("timing statistics may not be null");
      } else {
         this.currentTimingStatistics = currentTimingStatistics;
         this.sendNotificationsIfValuesNotAcceptable();
      }
   }

   public void exposeTag(String tagName) {
      this.tagsToExpose.add(tagName);
      this.managementInterface = this.createMBeanInfoFromTagNames(this.tagsToExpose);
   }

   public boolean removeTag(String tagName) {
      boolean retVal = this.tagsToExpose.remove(tagName);
      this.managementInterface = this.createMBeanInfoFromTagNames(this.tagsToExpose);
      return retVal;
   }

   public synchronized Object getAttribute(String attribute) throws AttributeNotFoundException, MBeanException, ReflectionException {
      Matcher matcher = this.attributeNamePattern.matcher(attribute);
      if (matcher.matches()) {
         String tagName = matcher.group(1);
         String statisticName = matcher.group(2);
         TimingStatistics timingStats = (TimingStatistics)this.currentTimingStatistics.getStatisticsByTag().get(tagName);
         long windowLength = this.currentTimingStatistics.getStopTime() - this.currentTimingStatistics.getStartTime();
         return ((StatsValueRetriever)this.getStatsValueRetrievers().get(statisticName)).getStatsValue(timingStats, windowLength);
      } else {
         throw new AttributeNotFoundException("No attribute named " + attribute);
      }
   }

   public void setAttribute(Attribute attribute) throws AttributeNotFoundException, InvalidAttributeValueException, MBeanException, ReflectionException {
      throw new AttributeNotFoundException("Statistics attributes are not writable");
   }

   public synchronized AttributeList getAttributes(String[] attributeNames) {
      AttributeList retVal = new AttributeList();
      String[] arr$ = attributeNames;
      int len$ = attributeNames.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         String attributeName = arr$[i$];

         try {
            retVal.add(new Attribute(attributeName, this.getAttribute(attributeName)));
         } catch (Exception var8) {
            ;
         }
      }

      return retVal;
   }

   public AttributeList setAttributes(AttributeList attributes) {
      return new AttributeList();
   }

   public Object invoke(String actionName, Object[] params, String[] signature) throws MBeanException, ReflectionException {
      if ("exposeTag".equals(actionName)) {
         this.exposeTag(params[0].toString());
         return null;
      } else if ("removeTag".equals(actionName)) {
         return this.removeTag(params[0].toString());
      } else {
         throw new UnsupportedOperationException("Unsupported operation: " + actionName);
      }
   }

   public MBeanInfo getMBeanInfo() {
      return this.managementInterface;
   }

   public MBeanNotificationInfo[] getNotificationInfo() {
      return this.managementInterface.getNotifications();
   }

   protected Map<String, StatsValueRetriever> getStatsValueRetrievers() {
      return StatsValueRetriever.DEFAULT_RETRIEVERS;
   }

   protected MBeanInfo createMBeanInfoFromTagNames(Collection<String> tagNames) {
      MBeanAttributeInfo[] attributes = new MBeanAttributeInfo[tagNames.size() * this.getStatsValueRetrievers().size()];
      int i = 0;
      Iterator i$ = tagNames.iterator();

      while(i$.hasNext()) {
         String tagName = (String)i$.next();

         String statName;
         StatsValueRetriever statsValueRetriever;
         for(Iterator i$ = this.getStatsValueRetrievers().entrySet().iterator(); i$.hasNext(); attributes[i++] = new MBeanAttributeInfo(tagName + statName, statsValueRetriever.getValueClass().getName(), "Returns " + statName + " for tag " + tagName, true, false, false)) {
            Entry<String, StatsValueRetriever> statNameAndValueRetriever = (Entry)i$.next();
            statName = (String)statNameAndValueRetriever.getKey();
            statsValueRetriever = (StatsValueRetriever)statNameAndValueRetriever.getValue();
         }
      }

      MBeanOperationInfo[] operations = new MBeanOperationInfo[]{new MBeanOperationInfo("exposeTag", "Allows the caller to add a monitored tag at runtime", new MBeanParameterInfo[]{new MBeanParameterInfo("tagName", String.class.getName(), "The name of the tag to expose")}, "void", 1), new MBeanOperationInfo("removeTag", "Allows the caller to remove a monitored tag at runtime", new MBeanParameterInfo[]{new MBeanParameterInfo("tagName", String.class.getName(), "The name of the tag to remove")}, "boolean", 1)};
      MBeanNotificationInfo[] notificationInfos;
      if (this.acceptableRanges.isEmpty()) {
         notificationInfos = new MBeanNotificationInfo[0];
      } else {
         notificationInfos = new MBeanNotificationInfo[]{new MBeanNotificationInfo(new String[]{"org.perf4j.threshold.exceeded"}, Notification.class.getName(), "Notification sent if any statistics move outside of the specified acceptable ranges")};
      }

      return new MBeanInfo(this.getClass().getName(), "Timing Statistics", attributes, (MBeanConstructorInfo[])null, operations, notificationInfos);
   }

   protected void sendNotificationsIfValuesNotAcceptable() {
      Iterator i$ = this.acceptableRanges.entrySet().iterator();

      while(i$.hasNext()) {
         Entry<AcceptableRangeConfiguration, Boolean> acceptableRangeAndWasGood = (Entry)i$.next();
         AcceptableRangeConfiguration acceptableRange = (AcceptableRangeConfiguration)acceptableRangeAndWasGood.getKey();
         boolean lastCheckWasGood = ((Boolean)acceptableRangeAndWasGood.getValue()).booleanValue();

         double attributeValue;
         try {
            attributeValue = ((Number)this.getAttribute(acceptableRange.getAttributeName())).doubleValue();
         } catch (Exception var8) {
            continue;
         }

         boolean isValueInRange = acceptableRange.isInRange(attributeValue);
         acceptableRangeAndWasGood.setValue(isValueInRange);
         if (lastCheckWasGood && !isValueInRange) {
            this.sendOutOfRangeNotification(attributeValue, acceptableRange);
         }
      }

   }

   protected void sendOutOfRangeNotification(final double attributeValue, final AcceptableRangeConfiguration acceptableRange) {
      this.outOfRangeNotifierThread.execute(new Runnable() {
         public void run() {
            String errorMessage = "Attribute value " + attributeValue + " not in range " + acceptableRange;
            StatisticsExposingMBean.this.sendNotification(new Notification("org.perf4j.threshold.exceeded", StatisticsExposingMBean.this.mBeanName, ++StatisticsExposingMBean.this.outOfRangeNotificationSeqNo, System.currentTimeMillis(), errorMessage));
         }
      });
   }
}
