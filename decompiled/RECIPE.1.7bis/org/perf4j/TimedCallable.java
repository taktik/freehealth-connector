package org.perf4j;

import java.io.Serializable;
import java.util.concurrent.Callable;

public class TimedCallable<V> implements Callable<V>, Serializable {
   private Callable<V> wrappedTask;
   private LoggingStopWatch stopWatch;

   public TimedCallable(Callable<V> task, LoggingStopWatch stopWatch) {
      this.wrappedTask = task;
      this.stopWatch = stopWatch;
   }

   public Callable<V> getWrappedTask() {
      return this.wrappedTask;
   }

   public LoggingStopWatch getStopWatch() {
      return this.stopWatch;
   }

   public V call() throws Exception {
      Object var1;
      try {
         this.stopWatch.start();
         var1 = this.wrappedTask.call();
      } finally {
         this.stopWatch.stop();
      }

      return var1;
   }
}
