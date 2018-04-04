package org.perf4j.helpers;

import java.util.Iterator;
import java.util.NoSuchElementException;
import org.perf4j.GroupedTimingStatistics;
import org.perf4j.StopWatch;

public class GroupingStatisticsIterator implements Iterator<GroupedTimingStatistics> {
   private Iterator<StopWatch> stopWatchIterator;
   private long timeSlice;
   private boolean createRollupStatistics;
   private Boolean hasNext;
   private GroupedTimingStatistics nextGroupedTimingStatistics;
   private GroupedTimingStatistics currentGroupedTimingStatistics;
   private long nextTimeSliceEndTime;

   public GroupingStatisticsIterator(Iterator<StopWatch> stopWatchIterator) {
      this(stopWatchIterator, 30000L, false);
   }

   public GroupingStatisticsIterator(Iterator<StopWatch> stopWatchIterator, long timeSlice, boolean createRollupStatistics) {
      this.hasNext = null;
      this.nextGroupedTimingStatistics = null;
      this.currentGroupedTimingStatistics = new GroupedTimingStatistics();
      this.nextTimeSliceEndTime = 0L;
      this.stopWatchIterator = stopWatchIterator;
      this.timeSlice = timeSlice;
      this.createRollupStatistics = createRollupStatistics;
      this.currentGroupedTimingStatistics.setCreateRollupStatistics(createRollupStatistics);
   }

   public boolean hasNext() {
      if (this.hasNext == null) {
         this.nextGroupedTimingStatistics = this.getNext();
         this.hasNext = this.nextGroupedTimingStatistics != null;
      }

      return this.hasNext.booleanValue();
   }

   public GroupedTimingStatistics next() {
      if (Boolean.FALSE.equals(this.hasNext)) {
         throw new NoSuchElementException();
      } else {
         if (this.nextGroupedTimingStatistics == null) {
            this.nextGroupedTimingStatistics = this.getNext();
            if (this.nextGroupedTimingStatistics == null) {
               this.hasNext = false;
               throw new NoSuchElementException();
            }
         }

         GroupedTimingStatistics retVal = this.nextGroupedTimingStatistics;
         this.hasNext = null;
         this.nextGroupedTimingStatistics = null;
         return retVal;
      }
   }

   public void remove() {
      throw new UnsupportedOperationException();
   }

   private GroupedTimingStatistics getNext() {
      while(this.stopWatchIterator.hasNext()) {
         StopWatch stopWatch = (StopWatch)this.stopWatchIterator.next();
         long startTime = stopWatch == null ? System.currentTimeMillis() : stopWatch.getStartTime();
         if (this.nextTimeSliceEndTime == 0L) {
            this.nextTimeSliceEndTime = startTime / this.timeSlice * this.timeSlice + this.timeSlice;
         }

         if (startTime >= this.nextTimeSliceEndTime) {
            this.currentGroupedTimingStatistics.setStartTime(this.nextTimeSliceEndTime - this.timeSlice);
            this.currentGroupedTimingStatistics.setStopTime(this.nextTimeSliceEndTime);
            GroupedTimingStatistics retVal = this.currentGroupedTimingStatistics;
            this.currentGroupedTimingStatistics = new GroupedTimingStatistics();
            this.currentGroupedTimingStatistics.setCreateRollupStatistics(this.createRollupStatistics);
            if (stopWatch != null) {
               this.currentGroupedTimingStatistics.addStopWatch(stopWatch);
            }

            this.nextTimeSliceEndTime = startTime / this.timeSlice * this.timeSlice + this.timeSlice;
            return retVal;
         }

         if (stopWatch != null) {
            this.currentGroupedTimingStatistics.addStopWatch(stopWatch);
         }
      }

      if (!this.currentGroupedTimingStatistics.getStatisticsByTag().isEmpty()) {
         this.currentGroupedTimingStatistics.setStartTime(this.nextTimeSliceEndTime - this.timeSlice);
         this.currentGroupedTimingStatistics.setStopTime(this.nextTimeSliceEndTime);
         GroupedTimingStatistics retVal = this.currentGroupedTimingStatistics;
         this.currentGroupedTimingStatistics = new GroupedTimingStatistics();
         this.currentGroupedTimingStatistics.setCreateRollupStatistics(this.createRollupStatistics);
         return retVal;
      } else {
         return null;
      }
   }
}
