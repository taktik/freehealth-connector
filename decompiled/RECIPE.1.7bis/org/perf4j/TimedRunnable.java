package org.perf4j;

import java.io.Serializable;

public class TimedRunnable implements Runnable, Serializable {
   private Runnable wrappedTask;
   private LoggingStopWatch stopWatch;

   public TimedRunnable(Runnable task, LoggingStopWatch stopWatch) {
      this.wrappedTask = task;
      this.stopWatch = stopWatch;
   }

   public Runnable getWrappedTask() {
      return this.wrappedTask;
   }

   public LoggingStopWatch getStopWatch() {
      return this.stopWatch;
   }

   public void run() {
      try {
         this.stopWatch.start();
         this.wrappedTask.run();
      } finally {
         this.stopWatch.stop();
      }

   }
}
