package org.perf4j;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.SortedMap;
import java.util.TimeZone;
import java.util.TreeMap;
import java.util.Map.Entry;
import org.perf4j.helpers.MiscUtils;

public class GroupedTimingStatistics implements Serializable, Cloneable {
   private SortedMap<String, TimingStatistics> statisticsByTag = new TreeMap();
   private long startTime;
   private long stopTime;
   private boolean createRollupStatistics;
   private static TimeZone timeZone = TimeZone.getDefault();

   public GroupedTimingStatistics() {
   }

   public GroupedTimingStatistics(SortedMap<String, TimingStatistics> statisticsByTag, long startTime, long stopTime, boolean createRollupStatistics) {
      this.statisticsByTag = statisticsByTag;
      this.startTime = startTime;
      this.stopTime = stopTime;
      this.createRollupStatistics = createRollupStatistics;
   }

   public GroupedTimingStatistics addStopWatch(StopWatch stopWatch) {
      String tag = stopWatch.getTag();
      this.addStopWatchToStatsByTag(tag, stopWatch);
      if (this.createRollupStatistics) {
         int indexOfDot = -1;

         while((indexOfDot = tag.indexOf(46, indexOfDot + 1)) >= 0) {
            this.addStopWatchToStatsByTag(tag.substring(0, indexOfDot), stopWatch);
         }
      }

      return this;
   }

   public GroupedTimingStatistics addStopWatches(Collection<StopWatch> stopWatches) {
      Iterator i$ = stopWatches.iterator();

      while(i$.hasNext()) {
         StopWatch stopWatch = (StopWatch)i$.next();
         this.addStopWatch(stopWatch);
      }

      return this;
   }

   public static TimeZone getTimeZone() {
      return timeZone;
   }

   public static void setTimeZone(TimeZone tz) {
      timeZone = tz;
   }

   public SortedMap<String, TimingStatistics> getStatisticsByTag() {
      return this.statisticsByTag;
   }

   public void setStatisticsByTag(SortedMap<String, TimingStatistics> statisticsByTag) {
      this.statisticsByTag = statisticsByTag;
   }

   public long getStartTime() {
      return this.startTime;
   }

   public void setStartTime(long startTime) {
      this.startTime = startTime;
   }

   public long getStopTime() {
      return this.stopTime;
   }

   public void setStopTime(long stopTime) {
      this.stopTime = stopTime;
   }

   public boolean isCreateRollupStatistics() {
      return this.createRollupStatistics;
   }

   public void setCreateRollupStatistics(boolean createRollupStatistics) {
      this.createRollupStatistics = createRollupStatistics;
   }

   private void addStopWatchToStatsByTag(String tag, StopWatch stopWatch) {
      TimingStatistics stats = (TimingStatistics)this.statisticsByTag.get(tag);
      if (stats == null) {
         this.statisticsByTag.put(tag, stats = new TimingStatistics());
      }

      stats.addSampleTime(stopWatch.getElapsedTime());
   }

   public String toString() {
      StringBuilder retVal = new StringBuilder();
      retVal.append("Performance Statistics   ").append(MiscUtils.formatDateIso8601(this.startTime)).append(" - ").append(MiscUtils.formatDateIso8601(this.stopTime)).append(MiscUtils.NEWLINE);
      retVal.append(String.format("%-48s%12s%12s%12s%12s%12s%n", "Tag", "Avg(ms)", "Min", "Max", "Std Dev", "Count"));
      Iterator i$ = this.statisticsByTag.entrySet().iterator();

      while(i$.hasNext()) {
         Entry<String, TimingStatistics> tagWithTimingStatistics = (Entry)i$.next();
         String tag = (String)tagWithTimingStatistics.getKey();
         TimingStatistics timingStatistics = (TimingStatistics)tagWithTimingStatistics.getValue();
         retVal.append(String.format("%-48s%12.1f%12d%12d%12.1f%12d%n", tag, timingStatistics.getMean(), timingStatistics.getMin(), timingStatistics.getMax(), timingStatistics.getStandardDeviation(), timingStatistics.getCount()));
      }

      return retVal.toString();
   }

   public GroupedTimingStatistics clone() {
      try {
         GroupedTimingStatistics retVal = (GroupedTimingStatistics)super.clone();
         retVal.statisticsByTag = new TreeMap(retVal.statisticsByTag);
         Iterator i$ = retVal.statisticsByTag.entrySet().iterator();

         while(i$.hasNext()) {
            Entry<String, TimingStatistics> tagAndStats = (Entry)i$.next();
            tagAndStats.setValue(((TimingStatistics)tagAndStats.getValue()).clone());
         }

         return retVal;
      } catch (CloneNotSupportedException var4) {
         throw new Error("Unexpected CloneNotSupportedException");
      }
   }

   public boolean equals(Object o) {
      if (this == o) {
         return true;
      } else if (!(o instanceof GroupedTimingStatistics)) {
         return false;
      } else {
         GroupedTimingStatistics that = (GroupedTimingStatistics)o;
         return this.startTime == that.startTime && this.stopTime == that.stopTime && this.statisticsByTag.equals(that.statisticsByTag);
      }
   }

   public int hashCode() {
      int result = this.statisticsByTag.hashCode();
      result = 31 * result + (int)(this.startTime ^ this.startTime >>> 32);
      result = 31 * result + (int)(this.stopTime ^ this.stopTime >>> 32);
      return result;
   }
}
