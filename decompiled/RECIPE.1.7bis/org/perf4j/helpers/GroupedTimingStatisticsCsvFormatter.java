package org.perf4j.helpers;

import java.util.Iterator;
import java.util.Map.Entry;
import org.perf4j.GroupedTimingStatistics;
import org.perf4j.TimingStatistics;

public class GroupedTimingStatisticsCsvFormatter implements GroupedTimingStatisticsFormatter {
   public static final String DEFAULT_FORMAT_STRING = "tag,start,stop,mean,min,max,stddev,count";
   private boolean pivot;
   private GroupedTimingStatisticsCsvFormatter.TimingStatsValueRetriever[] valueRetrievers;
   private GroupedTimingStatisticsCsvFormatter.GroupedTimingStatisticsValueRetriever[] pivotedValueRetrievers;

   public GroupedTimingStatisticsCsvFormatter() {
      this(false, "tag,start,stop,mean,min,max,stddev,count");
   }

   public GroupedTimingStatisticsCsvFormatter(boolean pivot, String configString) {
      this.pivot = pivot;
      String[] configElements = MiscUtils.splitAndTrim(configString, ",");
      int i;
      if (pivot) {
         this.pivotedValueRetrievers = new GroupedTimingStatisticsCsvFormatter.GroupedTimingStatisticsValueRetriever[configElements.length];

         for(i = 0; i < configElements.length; ++i) {
            this.pivotedValueRetrievers[i] = this.parsePivotedTimingStatsConfig(configElements[i]);
         }
      } else {
         this.valueRetrievers = new GroupedTimingStatisticsCsvFormatter.TimingStatsValueRetriever[configElements.length];

         for(i = 0; i < configElements.length; ++i) {
            this.valueRetrievers[i] = this.parseTimingStatsConfig(configElements[i]);
         }
      }

   }

   public String format(GroupedTimingStatistics stats) {
      String startTime = this.formatDate(stats.getStartTime());
      String stopTime = this.formatDate(stats.getStopTime());
      long windowLength = stats.getStopTime() - stats.getStartTime();
      StringBuilder retVal = new StringBuilder();
      if (this.pivot) {
         for(int i = 0; i < this.pivotedValueRetrievers.length; ++i) {
            if (i > 0) {
               retVal.append(',');
            }

            this.pivotedValueRetrievers[i].appendValue(startTime, stopTime, windowLength, stats, retVal);
         }

         retVal.append(MiscUtils.NEWLINE);
      } else {
         Iterator i$ = stats.getStatisticsByTag().entrySet().iterator();

         while(i$.hasNext()) {
            Entry<String, TimingStatistics> tagAndStats = (Entry)i$.next();
            String tag = (String)tagAndStats.getKey();
            TimingStatistics timingStats = (TimingStatistics)tagAndStats.getValue();

            for(int i = 0; i < this.valueRetrievers.length; ++i) {
               if (i > 0) {
                  retVal.append(',');
               }

               this.valueRetrievers[i].appendValue(tag, startTime, stopTime, windowLength, timingStats, retVal);
            }

            retVal.append(MiscUtils.NEWLINE);
         }
      }

      return retVal.toString();
   }

   protected String formatDate(long timeInMillis) {
      return MiscUtils.formatDateIso8601(timeInMillis);
   }

   protected GroupedTimingStatisticsCsvFormatter.GroupedTimingStatisticsValueRetriever parsePivotedTimingStatsConfig(String configName) {
      if ("start".equalsIgnoreCase(configName)) {
         return new GroupedTimingStatisticsCsvFormatter.GroupedTimingStatisticsValueRetriever() {
            public void appendValue(String start, String stop, long windowLength, GroupedTimingStatistics stats, StringBuilder toAppend) {
               toAppend.append(start);
            }
         };
      } else if ("stop".equalsIgnoreCase(configName)) {
         return new GroupedTimingStatisticsCsvFormatter.GroupedTimingStatisticsValueRetriever() {
            public void appendValue(String start, String stop, long windowLength, GroupedTimingStatistics stats, StringBuilder toAppend) {
               toAppend.append(stop);
            }
         };
      } else {
         final String tag;
         if (configName.toLowerCase().endsWith("mean")) {
            tag = configName.substring(0, configName.length() - "mean".length());
            return new GroupedTimingStatisticsCsvFormatter.GroupedTimingStatisticsValueRetriever() {
               public void appendValue(String start, String stop, long windowLength, GroupedTimingStatistics stats, StringBuilder toAppend) {
                  TimingStatistics timingStats = (TimingStatistics)stats.getStatisticsByTag().get(tag);
                  toAppend.append(timingStats == null ? "" : timingStats.getMean());
               }
            };
         } else if (configName.toLowerCase().endsWith("min")) {
            tag = configName.substring(0, configName.length() - "min".length());
            return new GroupedTimingStatisticsCsvFormatter.GroupedTimingStatisticsValueRetriever() {
               public void appendValue(String start, String stop, long windowLength, GroupedTimingStatistics stats, StringBuilder toAppend) {
                  TimingStatistics timingStats = (TimingStatistics)stats.getStatisticsByTag().get(tag);
                  toAppend.append(timingStats == null ? "" : timingStats.getMin());
               }
            };
         } else if (configName.toLowerCase().endsWith("max")) {
            tag = configName.substring(0, configName.length() - "max".length());
            return new GroupedTimingStatisticsCsvFormatter.GroupedTimingStatisticsValueRetriever() {
               public void appendValue(String start, String stop, long windowLength, GroupedTimingStatistics stats, StringBuilder toAppend) {
                  TimingStatistics timingStats = (TimingStatistics)stats.getStatisticsByTag().get(tag);
                  toAppend.append(timingStats == null ? "" : timingStats.getMax());
               }
            };
         } else if (configName.toLowerCase().endsWith("stddev")) {
            tag = configName.substring(0, configName.length() - "stddev".length());
            return new GroupedTimingStatisticsCsvFormatter.GroupedTimingStatisticsValueRetriever() {
               public void appendValue(String start, String stop, long windowLength, GroupedTimingStatistics stats, StringBuilder toAppend) {
                  TimingStatistics timingStats = (TimingStatistics)stats.getStatisticsByTag().get(tag);
                  toAppend.append(timingStats == null ? "" : timingStats.getStandardDeviation());
               }
            };
         } else if (configName.toLowerCase().endsWith("count")) {
            tag = configName.substring(0, configName.length() - "count".length());
            return new GroupedTimingStatisticsCsvFormatter.GroupedTimingStatisticsValueRetriever() {
               public void appendValue(String start, String stop, long windowLength, GroupedTimingStatistics stats, StringBuilder toAppend) {
                  TimingStatistics timingStats = (TimingStatistics)stats.getStatisticsByTag().get(tag);
                  toAppend.append(timingStats == null ? "" : timingStats.getCount());
               }
            };
         } else if (configName.toLowerCase().endsWith("tps")) {
            tag = configName.substring(0, configName.length() - "tps".length());
            return new GroupedTimingStatisticsCsvFormatter.GroupedTimingStatisticsValueRetriever() {
               public void appendValue(String start, String stop, long windowLength, GroupedTimingStatistics stats, StringBuilder toAppend) {
                  TimingStatistics timingStats = (TimingStatistics)stats.getStatisticsByTag().get(tag);
                  if (timingStats == null) {
                     toAppend.append("");
                  } else {
                     toAppend.append((double)timingStats.getCount() * 1000.0D / (double)windowLength);
                  }

               }
            };
         } else {
            throw new IllegalArgumentException("Unknown CSV format config string: " + configName);
         }
      }
   }

   protected GroupedTimingStatisticsCsvFormatter.TimingStatsValueRetriever parseTimingStatsConfig(String configName) {
      if ("tag".equals(configName)) {
         return new GroupedTimingStatisticsCsvFormatter.TimingStatsValueRetriever() {
            public void appendValue(String tag, String start, String stop, long windowLength, TimingStatistics timingStats, StringBuilder toAppend) {
               MiscUtils.escapeStringForCsv(tag, toAppend);
            }
         };
      } else if ("start".equals(configName)) {
         return new GroupedTimingStatisticsCsvFormatter.TimingStatsValueRetriever() {
            public void appendValue(String tag, String start, String stop, long windowLength, TimingStatistics timingStats, StringBuilder toAppend) {
               toAppend.append(start);
            }
         };
      } else if ("stop".equals(configName)) {
         return new GroupedTimingStatisticsCsvFormatter.TimingStatsValueRetriever() {
            public void appendValue(String tag, String start, String stop, long windowLength, TimingStatistics timingStats, StringBuilder toAppend) {
               toAppend.append(stop);
            }
         };
      } else if ("mean".equals(configName)) {
         return new GroupedTimingStatisticsCsvFormatter.TimingStatsValueRetriever() {
            public void appendValue(String tag, String start, String stop, long windowLength, TimingStatistics timingStats, StringBuilder toAppend) {
               toAppend.append(timingStats.getMean());
            }
         };
      } else if ("min".equals(configName)) {
         return new GroupedTimingStatisticsCsvFormatter.TimingStatsValueRetriever() {
            public void appendValue(String tag, String start, String stop, long windowLength, TimingStatistics timingStats, StringBuilder toAppend) {
               toAppend.append(timingStats.getMin());
            }
         };
      } else if ("max".equals(configName)) {
         return new GroupedTimingStatisticsCsvFormatter.TimingStatsValueRetriever() {
            public void appendValue(String tag, String start, String stop, long windowLength, TimingStatistics timingStats, StringBuilder toAppend) {
               toAppend.append(timingStats.getMax());
            }
         };
      } else if ("stddev".equals(configName)) {
         return new GroupedTimingStatisticsCsvFormatter.TimingStatsValueRetriever() {
            public void appendValue(String tag, String start, String stop, long windowLength, TimingStatistics timingStats, StringBuilder toAppend) {
               toAppend.append(timingStats.getStandardDeviation());
            }
         };
      } else if ("count".equals(configName)) {
         return new GroupedTimingStatisticsCsvFormatter.TimingStatsValueRetriever() {
            public void appendValue(String tag, String start, String stop, long windowLength, TimingStatistics timingStats, StringBuilder toAppend) {
               toAppend.append(timingStats.getCount());
            }
         };
      } else if ("tps".equals(configName)) {
         return new GroupedTimingStatisticsCsvFormatter.TimingStatsValueRetriever() {
            public void appendValue(String tag, String start, String stop, long windowLength, TimingStatistics timingStats, StringBuilder toAppend) {
               toAppend.append((double)timingStats.getCount() * 1000.0D / (double)windowLength);
            }
         };
      } else {
         throw new IllegalArgumentException("Unknown CSV format config string: " + configName);
      }
   }

   protected interface GroupedTimingStatisticsValueRetriever {
      void appendValue(String var1, String var2, long var3, GroupedTimingStatistics var5, StringBuilder var6);
   }

   protected interface TimingStatsValueRetriever {
      void appendValue(String var1, String var2, String var3, long var4, TimingStatistics var6, StringBuilder var7);
   }
}
