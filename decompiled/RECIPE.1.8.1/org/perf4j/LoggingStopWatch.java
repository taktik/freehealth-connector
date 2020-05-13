package org.perf4j;

public class LoggingStopWatch extends StopWatch {
   private long timeThreshold = 0L;

   public LoggingStopWatch() {
   }

   public LoggingStopWatch(String tag) {
      super(tag);
   }

   public LoggingStopWatch(String tag, String message) {
      super(tag, message);
   }

   public LoggingStopWatch(long startTime, long elapsedTime, String tag, String message) {
      super(startTime, elapsedTime, tag, message);
   }

   public long getTimeThreshold() {
      return this.timeThreshold;
   }

   public LoggingStopWatch setTimeThreshold(long timeThreshold) {
      this.timeThreshold = timeThreshold;
      return this;
   }

   public LoggingStopWatch setTag(String tag) {
      super.setTag(tag);
      return this;
   }

   public LoggingStopWatch setMessage(String message) {
      super.setMessage(message);
      return this;
   }

   public String stop() {
      String retVal = super.stop();
      this.doLogInternal(retVal, (Throwable)null);
      return retVal;
   }

   public String stop(Throwable exception) {
      String retVal = super.stop();
      this.doLogInternal(retVal, exception);
      return retVal;
   }

   public String stop(String tag, Throwable exception) {
      this.setTag(tag);
      return this.stop(exception);
   }

   public String stop(String tag, String message, Throwable exception) {
      this.setTag(tag);
      this.setMessage(message);
      return this.stop(exception);
   }

   public String lap(String tag, Throwable exception) {
      String retVal = this.stop(tag, exception);
      this.start();
      return retVal;
   }

   public String lap(String tag, String message, Throwable exception) {
      String retVal = this.stop(tag, message, exception);
      this.start();
      return retVal;
   }

   public boolean isLogging() {
      return true;
   }

   protected void log(String stopWatchAsString, Throwable exception) {
      System.err.println(stopWatchAsString);
      if (exception != null) {
         exception.printStackTrace();
      }

   }

   public LoggingStopWatch clone() {
      return (LoggingStopWatch)super.clone();
   }

   private void doLogInternal(String stopWatchAsString, Throwable exception) {
      if (this.timeThreshold == 0L || this.getElapsedTime() >= this.timeThreshold) {
         this.log(stopWatchAsString, exception);
      }

   }
}
