package org.perf4j.log4j;

import java.io.Flushable;
import java.util.Enumeration;
import org.apache.log4j.Appender;
import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.helpers.AppenderAttachableImpl;
import org.apache.log4j.spi.AppenderAttachable;
import org.apache.log4j.spi.LoggingEvent;
import org.perf4j.GroupedTimingStatistics;
import org.perf4j.helpers.GenericAsyncCoalescingStatisticsAppender;

public class AsyncCoalescingStatisticsAppender extends AppenderSkeleton implements AppenderAttachable {
   private Level downstreamLogLevel;
   private final GenericAsyncCoalescingStatisticsAppender baseImplementation;
   private final AppenderAttachableImpl downstreamAppenders;
   private Thread shutdownHook;

   public AsyncCoalescingStatisticsAppender() {
      this.downstreamLogLevel = Level.INFO;
      this.baseImplementation = this.newGenericAsyncCoalescingStatisticsAppender();
      this.downstreamAppenders = new AppenderAttachableImpl();
      this.shutdownHook = null;
   }

   public long getTimeSlice() {
      return this.baseImplementation.getTimeSlice();
   }

   public void setTimeSlice(long timeSlice) {
      this.baseImplementation.setTimeSlice(timeSlice);
   }

   public String getDownstreamLogLevel() {
      return this.downstreamLogLevel.toString();
   }

   public void setDownstreamLogLevel(String downstreamLogLevel) {
      this.downstreamLogLevel = Level.toLevel(downstreamLogLevel);
   }

   public boolean isCreateRollupStatistics() {
      return this.baseImplementation.isCreateRollupStatistics();
   }

   public void setCreateRollupStatistics(boolean createRollupStatistics) {
      this.baseImplementation.setCreateRollupStatistics(createRollupStatistics);
   }

   public int getQueueSize() {
      return this.baseImplementation.getQueueSize();
   }

   public void setQueueSize(int queueSize) {
      this.baseImplementation.setQueueSize(queueSize);
   }

   public String getStopWatchParserClassName() {
      return this.baseImplementation.getStopWatchParserClassName();
   }

   public void setStopWatchParserClassName(String stopWatchParserClassName) {
      this.baseImplementation.setStopWatchParserClassName(stopWatchParserClassName);
   }

   public void setName(String name) {
      super.setName(name);
      this.baseImplementation.setName(name);
   }

   public synchronized void activateOptions() {
      this.baseImplementation.start(new GenericAsyncCoalescingStatisticsAppender.GroupedTimingStatisticsHandler() {
         public void handle(GroupedTimingStatistics statistics) {
            LoggingEvent coalescedLoggingEvent = new LoggingEvent(Logger.class.getName(), Logger.getLogger("org.perf4j.TimingLogger"), System.currentTimeMillis(), AsyncCoalescingStatisticsAppender.this.downstreamLogLevel, statistics, (Throwable)null);

            try {
               synchronized(AsyncCoalescingStatisticsAppender.this.downstreamAppenders) {
                  AsyncCoalescingStatisticsAppender.this.downstreamAppenders.appendLoopOnAppenders(coalescedLoggingEvent);
               }
            } catch (Exception var6) {
               AsyncCoalescingStatisticsAppender.this.getErrorHandler().error("Exception calling append with GroupedTimingStatistics on downstream appender", var6, -1, coalescedLoggingEvent);
            }

         }

         public void error(String errorMessage) {
            AsyncCoalescingStatisticsAppender.this.getErrorHandler().error(errorMessage);
         }
      });
      if (this.shutdownHook == null) {
         try {
            Runtime.getRuntime().addShutdownHook(this.shutdownHook = new Thread("perf4j-async-stats-appender-shutdown") {
               public void run() {
                  if (!AsyncCoalescingStatisticsAppender.this.closed) {
                     AsyncCoalescingStatisticsAppender.this.close();
                  }

               }
            });
         } catch (Exception var2) {
         }
      }

   }

   public int getNumDiscardedMessages() {
      return this.baseImplementation.getNumDiscardedMessages();
   }

   public void addAppender(Appender appender) {
      synchronized(this.downstreamAppenders) {
         this.downstreamAppenders.addAppender(appender);
      }
   }

   public Enumeration getAllAppenders() {
      synchronized(this.downstreamAppenders) {
         return this.downstreamAppenders.getAllAppenders();
      }
   }

   public Appender getAppender(String name) {
      synchronized(this.downstreamAppenders) {
         return this.downstreamAppenders.getAppender(name);
      }
   }

   public boolean isAttached(Appender appender) {
      synchronized(this.downstreamAppenders) {
         return this.downstreamAppenders.isAttached(appender);
      }
   }

   public void removeAllAppenders() {
      synchronized(this.downstreamAppenders) {
         this.downstreamAppenders.removeAllAppenders();
      }
   }

   public void removeAppender(Appender appender) {
      synchronized(this.downstreamAppenders) {
         this.downstreamAppenders.removeAppender(appender);
      }
   }

   public void removeAppender(String name) {
      synchronized(this.downstreamAppenders) {
         this.downstreamAppenders.removeAppender(name);
      }
   }

   protected void append(LoggingEvent event) {
      this.baseImplementation.append(String.valueOf(event.getMessage()));
   }

   public boolean requiresLayout() {
      return false;
   }

   public void close() {
      this.baseImplementation.stop();
      synchronized(this.downstreamAppenders) {
         Enumeration enumer = this.downstreamAppenders.getAllAppenders();

         while(enumer != null && enumer.hasMoreElements()) {
            Appender appender = (Appender)enumer.nextElement();
            if (appender instanceof Flushable) {
               try {
                  ((Flushable)appender).flush();
               } catch (Exception var6) {
               }
            }
         }

         enumer = this.downstreamAppenders.getAllAppenders();

         while(true) {
            if (enumer == null || !enumer.hasMoreElements()) {
               break;
            }

            ((Appender)enumer.nextElement()).close();
         }
      }

      this.closed = true;
   }

   protected GenericAsyncCoalescingStatisticsAppender newGenericAsyncCoalescingStatisticsAppender() {
      return new GenericAsyncCoalescingStatisticsAppender();
   }
}
