package org.perf4j.helpers;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import org.perf4j.GroupedTimingStatistics;
import org.perf4j.StopWatch;

public class GenericAsyncCoalescingStatisticsAppender {
   private String name = "";
   private long timeSlice = 30000L;
   private boolean createRollupStatistics = false;
   private int queueSize = 1024;
   private String stopWatchParserClassName = StopWatchParser.class.getName();
   private GenericAsyncCoalescingStatisticsAppender.GroupedTimingStatisticsHandler handler = null;
   private BlockingQueue<String> loggedMessages = null;
   private StopWatchParser stopWatchParser;
   private Thread drainingThread = null;
   private volatile int numDiscardedMessages = 0;

   public String getName() {
      return this.name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public long getTimeSlice() {
      return this.timeSlice;
   }

   public void setTimeSlice(long timeSlice) {
      this.timeSlice = timeSlice;
   }

   public boolean isCreateRollupStatistics() {
      return this.createRollupStatistics;
   }

   public void setCreateRollupStatistics(boolean createRollupStatistics) {
      this.createRollupStatistics = createRollupStatistics;
   }

   public int getQueueSize() {
      return this.queueSize;
   }

   public void setQueueSize(int queueSize) {
      this.queueSize = queueSize;
   }

   public String getStopWatchParserClassName() {
      return this.stopWatchParserClassName;
   }

   public void setStopWatchParserClassName(String stopWatchParserClassName) {
      this.stopWatchParserClassName = stopWatchParserClassName;
   }

   public int getNumDiscardedMessages() {
      return this.numDiscardedMessages;
   }

   public void start(GenericAsyncCoalescingStatisticsAppender.GroupedTimingStatisticsHandler handler) {
      if (this.drainingThread != null) {
         this.stopDrainingThread();
      }

      this.handler = handler;
      this.stopWatchParser = this.newStopWatchParser();
      this.numDiscardedMessages = 0;
      this.loggedMessages = new ArrayBlockingQueue(this.getQueueSize());
      this.drainingThread = new Thread(new GenericAsyncCoalescingStatisticsAppender.Dispatcher(), "perf4j-async-stats-appender-sink-" + this.getName());
      this.drainingThread.setDaemon(true);
      this.drainingThread.start();
   }

   public void append(String message) {
      if (this.stopWatchParser.isPotentiallyValid(message) && !this.loggedMessages.offer(message)) {
         ++this.numDiscardedMessages;
         this.handler.error(message);
      }

   }

   public void stop() {
      this.stopDrainingThread();
   }

   private void stopDrainingThread() {
      try {
         this.loggedMessages.put("");
         this.drainingThread.join(10000L);
      } catch (Exception var2) {
         this.handler.error("Unexpected error stopping AsyncCoalescingStatisticsAppender draining thread: " + var2.getMessage());
      }

   }

   private StopWatchParser newStopWatchParser() {
      try {
         return (StopWatchParser)Class.forName(this.stopWatchParserClassName).newInstance();
      } catch (Exception var2) {
         throw new RuntimeException("Could not create StopWatchParser: " + var2.getMessage(), var2);
      }
   }

   private class StopWatchesFromQueueIterator implements Iterator<StopWatch> {
      private LinkedList<String> drainedMessages;
      private StopWatch nextStopWatch;
      private boolean done;
      private boolean timeSliceOver;

      private StopWatchesFromQueueIterator() {
         this.drainedMessages = new LinkedList();
      }

      public boolean hasNext() {
         if (this.nextStopWatch == null) {
            this.nextStopWatch = this.getNext();
         }

         return this.timeSliceOver || this.nextStopWatch != null;
      }

      public StopWatch next() {
         if (this.timeSliceOver) {
            this.timeSliceOver = false;
            return null;
         } else {
            if (this.nextStopWatch == null) {
               this.nextStopWatch = this.getNext();
               if (this.nextStopWatch == null) {
                  throw new NoSuchElementException();
               }
            }

            StopWatch retVal = this.nextStopWatch;
            this.nextStopWatch = null;
            return retVal;
         }
      }

      public void remove() {
         throw new UnsupportedOperationException();
      }

      private StopWatch getNext() {
         if (this.done) {
            return null;
         } else {
            while(true) {
               String message;
               if (this.drainedMessages.isEmpty()) {
                  GenericAsyncCoalescingStatisticsAppender.this.loggedMessages.drainTo(this.drainedMessages, 64);
                  if (this.drainedMessages.isEmpty()) {
                     try {
                        message = (String)GenericAsyncCoalescingStatisticsAppender.this.loggedMessages.poll(GenericAsyncCoalescingStatisticsAppender.this.timeSlice, TimeUnit.MILLISECONDS);
                        if (message == null) {
                           this.timeSliceOver = true;
                           return null;
                        }

                        this.drainedMessages.add(message);
                     } catch (InterruptedException var3) {
                        this.done = true;
                        return null;
                     }
                  }
               }

               while(!this.drainedMessages.isEmpty()) {
                  message = (String)this.drainedMessages.removeFirst();
                  if (message.length() == 0) {
                     this.done = true;
                     return null;
                  }

                  StopWatch parsedStopWatch = GenericAsyncCoalescingStatisticsAppender.this.stopWatchParser.parseStopWatch(message);
                  if (parsedStopWatch != null) {
                     return parsedStopWatch;
                  }
               }
            }
         }
      }

      // $FF: synthetic method
      StopWatchesFromQueueIterator(Object x1) {
         this();
      }
   }

   private class Dispatcher implements Runnable {
      private Dispatcher() {
      }

      public void run() {
         GroupingStatisticsIterator statsIterator = new GroupingStatisticsIterator(GenericAsyncCoalescingStatisticsAppender.this.new StopWatchesFromQueueIterator(), GenericAsyncCoalescingStatisticsAppender.this.timeSlice, GenericAsyncCoalescingStatisticsAppender.this.createRollupStatistics);

         while(statsIterator.hasNext()) {
            try {
               GenericAsyncCoalescingStatisticsAppender.this.handler.handle(statsIterator.next());
            } catch (Exception var3) {
               GenericAsyncCoalescingStatisticsAppender.this.handler.error("Error calling the GroupedTimingStatisticsHandler: " + var3.getMessage());
            }
         }

      }

      // $FF: synthetic method
      Dispatcher(Object x1) {
         this();
      }
   }

   public interface GroupedTimingStatisticsHandler {
      void handle(GroupedTimingStatistics var1);

      void error(String var1);
   }
}
