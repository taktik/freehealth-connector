package org.perf4j.log4j;

import java.io.Flushable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;
import org.apache.log4j.Appender;
import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.helpers.AppenderAttachableImpl;
import org.apache.log4j.spi.AppenderAttachable;
import org.apache.log4j.spi.LoggingEvent;
import org.perf4j.GroupedTimingStatistics;
import org.perf4j.chart.GoogleChartGenerator;
import org.perf4j.chart.StatisticsChartGenerator;
import org.perf4j.helpers.MiscUtils;
import org.perf4j.helpers.StatsValueRetriever;

public class GraphingStatisticsAppender extends AppenderSkeleton implements AppenderAttachable, Flushable {
   protected static final Map<String, GraphingStatisticsAppender> APPENDERS_BY_NAME = Collections.synchronizedMap(new LinkedHashMap());
   private String graphType;
   private String tagNamesToGraph;
   private int dataPointsPerGraph;
   private StatisticsChartGenerator chartGenerator;
   private AtomicLong numLoggedStatistics;
   private volatile boolean hasUnflushedData;
   private Level lastAppendedEventLevel;
   private final AppenderAttachableImpl downstreamAppenders;

   public GraphingStatisticsAppender() {
      this.graphType = StatsValueRetriever.MEAN_VALUE_RETRIEVER.getValueName();
      this.tagNamesToGraph = null;
      this.dataPointsPerGraph = 20;
      this.numLoggedStatistics = new AtomicLong();
      this.hasUnflushedData = false;
      this.lastAppendedEventLevel = Level.INFO;
      this.downstreamAppenders = new AppenderAttachableImpl();
   }

   public String getGraphType() {
      return this.graphType;
   }

   public void setGraphType(String graphType) {
      this.graphType = graphType;
   }

   public String getTagNamesToGraph() {
      return this.tagNamesToGraph;
   }

   public void setTagNamesToGraph(String tagNamesToGraph) {
      this.tagNamesToGraph = tagNamesToGraph;
   }

   public int getDataPointsPerGraph() {
      return this.dataPointsPerGraph;
   }

   public void setDataPointsPerGraph(int dataPointsPerGraph) {
      if (dataPointsPerGraph <= 0) {
         throw new IllegalArgumentException("The DataPointsPerGraph option must be positive");
      } else {
         this.dataPointsPerGraph = dataPointsPerGraph;
      }
   }

   public void activateOptions() {
      this.chartGenerator = this.createChartGenerator();
      if (this.getName() != null) {
         APPENDERS_BY_NAME.put(this.getName(), this);
      }

   }

   protected StatisticsChartGenerator createChartGenerator() {
      StatsValueRetriever statsValueRetriever = (StatsValueRetriever)StatsValueRetriever.DEFAULT_RETRIEVERS.get(this.getGraphType());
      if (statsValueRetriever == null) {
         throw new RuntimeException("Unknown GraphType: " + this.getGraphType() + ". See the StatsValueRetriever class for the list of acceptable types.");
      } else {
         GoogleChartGenerator retVal = new GoogleChartGenerator(statsValueRetriever);
         if (this.getTagNamesToGraph() != null) {
            Set<String> enabledTags = new HashSet(Arrays.asList(MiscUtils.splitAndTrim(this.getTagNamesToGraph(), ",")));
            retVal.setEnabledTags(enabledTags);
         }

         return retVal;
      }
   }

   public StatisticsChartGenerator getChartGenerator() {
      return this.chartGenerator;
   }

   public static GraphingStatisticsAppender getAppenderByName(String appenderName) {
      return (GraphingStatisticsAppender)APPENDERS_BY_NAME.get(appenderName);
   }

   public static Collection<GraphingStatisticsAppender> getAllGraphingStatisticsAppenders() {
      return Collections.unmodifiableCollection(APPENDERS_BY_NAME.values());
   }

   public void addAppender(Appender appender) {
      AppenderAttachableImpl var2 = this.downstreamAppenders;
      synchronized(this.downstreamAppenders) {
         this.downstreamAppenders.addAppender(appender);
      }
   }

   public Enumeration getAllAppenders() {
      AppenderAttachableImpl var1 = this.downstreamAppenders;
      synchronized(this.downstreamAppenders) {
         return this.downstreamAppenders.getAllAppenders();
      }
   }

   public Appender getAppender(String name) {
      AppenderAttachableImpl var2 = this.downstreamAppenders;
      synchronized(this.downstreamAppenders) {
         return this.downstreamAppenders.getAppender(name);
      }
   }

   public boolean isAttached(Appender appender) {
      AppenderAttachableImpl var2 = this.downstreamAppenders;
      synchronized(this.downstreamAppenders) {
         return this.downstreamAppenders.isAttached(appender);
      }
   }

   public void removeAllAppenders() {
      AppenderAttachableImpl var1 = this.downstreamAppenders;
      synchronized(this.downstreamAppenders) {
         this.downstreamAppenders.removeAllAppenders();
      }
   }

   public void removeAppender(Appender appender) {
      AppenderAttachableImpl var2 = this.downstreamAppenders;
      synchronized(this.downstreamAppenders) {
         this.downstreamAppenders.removeAppender(appender);
      }
   }

   public void removeAppender(String name) {
      AppenderAttachableImpl var2 = this.downstreamAppenders;
      synchronized(this.downstreamAppenders) {
         this.downstreamAppenders.removeAppender(name);
      }
   }

   protected void append(LoggingEvent event) {
      Object logMessage = event.getMessage();
      if (logMessage instanceof GroupedTimingStatistics && this.chartGenerator != null) {
         this.chartGenerator.appendData((GroupedTimingStatistics)logMessage);
         this.hasUnflushedData = true;
         this.lastAppendedEventLevel = event.getLevel();
         if (this.numLoggedStatistics.incrementAndGet() % (long)this.getDataPointsPerGraph() == 0L) {
            this.flush();
         }
      }

   }

   public boolean requiresLayout() {
      return false;
   }

   public void close() {
      AppenderAttachableImpl var1 = this.downstreamAppenders;
      synchronized(this.downstreamAppenders) {
         this.flush();
         Enumeration enumer = this.downstreamAppenders.getAllAppenders();

         while(enumer != null && enumer.hasMoreElements()) {
            Appender appender = (Appender)enumer.nextElement();
            appender.close();
         }

      }
   }

   public void flush() {
      AppenderAttachableImpl var1 = this.downstreamAppenders;
      synchronized(this.downstreamAppenders) {
         if (this.hasUnflushedData && this.downstreamAppenders.getAllAppenders() != null) {
            this.downstreamAppenders.appendLoopOnAppenders(new LoggingEvent(Logger.class.getName(), Logger.getLogger("org.perf4j.TimingLogger"), System.currentTimeMillis(), this.lastAppendedEventLevel, this.chartGenerator.getChartUrl(), (Throwable)null));
            this.hasUnflushedData = false;
         }

      }
   }
}
