package org.perf4j.helpers;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import org.perf4j.TimingStatistics;

public abstract class StatsValueRetriever {
   public static final StatsValueRetriever MEAN_VALUE_RETRIEVER = new StatsValueRetriever() {
      public Number getStatsValue(TimingStatistics timingStats, long windowLength) {
         return timingStats == null ? 0.0D : timingStats.getMean();
      }

      public Class getValueClass() {
         return Double.class;
      }

      public String getValueName() {
         return "Mean";
      }
   };
   public static final StatsValueRetriever STD_DEV_VALUE_RETRIEVER = new StatsValueRetriever() {
      public Number getStatsValue(TimingStatistics timingStats, long windowLength) {
         return timingStats == null ? 0.0D : timingStats.getStandardDeviation();
      }

      public Class getValueClass() {
         return Double.class;
      }

      public String getValueName() {
         return "StdDev";
      }
   };
   public static final StatsValueRetriever MIN_VALUE_RETRIEVER = new StatsValueRetriever() {
      public Number getStatsValue(TimingStatistics timingStats, long windowLength) {
         return timingStats == null ? 0L : timingStats.getMin();
      }

      public Class getValueClass() {
         return Long.class;
      }

      public String getValueName() {
         return "Min";
      }
   };
   public static final StatsValueRetriever MAX_VALUE_RETRIEVER = new StatsValueRetriever() {
      public Number getStatsValue(TimingStatistics timingStats, long windowLength) {
         return timingStats == null ? 0L : timingStats.getMax();
      }

      public Class getValueClass() {
         return Long.class;
      }

      public String getValueName() {
         return "Max";
      }
   };
   public static final StatsValueRetriever COUNT_VALUE_RETRIEVER = new StatsValueRetriever() {
      public Number getStatsValue(TimingStatistics timingStats, long windowLength) {
         return timingStats == null ? 0 : timingStats.getCount();
      }

      public Class getValueClass() {
         return Integer.class;
      }

      public String getValueName() {
         return "Count";
      }
   };
   public static final StatsValueRetriever TPS_VALUE_RETRIEVER = new StatsValueRetriever() {
      public Number getStatsValue(TimingStatistics timingStats, long windowLength) {
         return timingStats != null && windowLength != 0L ? (double)timingStats.getCount() / ((double)windowLength / 1000.0D) : 0.0D;
      }

      public Class getValueClass() {
         return Double.class;
      }

      public String getValueName() {
         return "TPS";
      }
   };
   public static final Map<String, StatsValueRetriever> DEFAULT_RETRIEVERS;

   public abstract Number getStatsValue(TimingStatistics var1, long var2);

   public abstract Class getValueClass();

   public abstract String getValueName();

   static {
      Map<String, StatsValueRetriever> defaultRetrievers = new LinkedHashMap();
      defaultRetrievers.put(MEAN_VALUE_RETRIEVER.getValueName(), MEAN_VALUE_RETRIEVER);
      defaultRetrievers.put(STD_DEV_VALUE_RETRIEVER.getValueName(), STD_DEV_VALUE_RETRIEVER);
      defaultRetrievers.put(MIN_VALUE_RETRIEVER.getValueName(), MIN_VALUE_RETRIEVER);
      defaultRetrievers.put(MAX_VALUE_RETRIEVER.getValueName(), MAX_VALUE_RETRIEVER);
      defaultRetrievers.put(COUNT_VALUE_RETRIEVER.getValueName(), COUNT_VALUE_RETRIEVER);
      defaultRetrievers.put(TPS_VALUE_RETRIEVER.getValueName(), TPS_VALUE_RETRIEVER);
      DEFAULT_RETRIEVERS = Collections.unmodifiableMap(defaultRetrievers);
   }
}
