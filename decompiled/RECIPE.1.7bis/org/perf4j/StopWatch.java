package org.perf4j;

import java.io.Serializable;

public class StopWatch implements Serializable, Cloneable {
   public static final String DEFAULT_LOGGER_NAME = "org.perf4j.TimingLogger";
   private long startTime;
   private long elapsedTime;
   private String tag;
   private String message;

   public StopWatch() {
      this("", (String)null);
   }

   public StopWatch(String tag) {
      this(tag, (String)null);
   }

   public StopWatch(String tag, String message) {
      this(System.currentTimeMillis(), -1L, tag, message);
   }

   public StopWatch(long startTime, long elapsedTime, String tag, String message) {
      this.startTime = startTime;
      this.elapsedTime = elapsedTime;
      this.tag = tag;
      this.message = message;
   }

   public long getStartTime() {
      return this.startTime;
   }

   public long getElapsedTime() {
      return this.elapsedTime == -1L ? System.currentTimeMillis() - this.startTime : this.elapsedTime;
   }

   public String getTag() {
      return this.tag;
   }

   public StopWatch setTag(String tag) {
      this.tag = tag;
      return this;
   }

   public String getMessage() {
      return this.message;
   }

   public StopWatch setMessage(String message) {
      this.message = message;
      return this;
   }

   public void start() {
      this.startTime = System.currentTimeMillis();
      this.elapsedTime = -1L;
   }

   public void start(String tag) {
      this.start();
      this.tag = tag;
   }

   public void start(String tag, String message) {
      this.start();
      this.tag = tag;
      this.message = message;
   }

   public String stop() {
      this.elapsedTime = System.currentTimeMillis() - this.startTime;
      return this.toString();
   }

   public String stop(String tag) {
      this.tag = tag;
      return this.stop();
   }

   public String stop(String tag, String message) {
      this.tag = tag;
      this.message = message;
      return this.stop();
   }

   public String lap(String tag) {
      String retVal = this.stop(tag);
      this.start();
      return retVal;
   }

   public String lap(String tag, String message) {
      String retVal = this.stop(tag, message);
      this.start();
      return retVal;
   }

   public String toString() {
      return "start[" + this.startTime + "] time[" + this.getElapsedTime() + "] tag[" + this.tag + (this.message == null ? "]" : "] message[" + this.message + "]");
   }

   public StopWatch clone() {
      try {
         return (StopWatch)super.clone();
      } catch (CloneNotSupportedException var2) {
         throw new Error("Unexpected CloneNotSupportedException");
      }
   }

   public boolean equals(Object o) {
      if (this == o) {
         return true;
      } else if (!(o instanceof StopWatch)) {
         return false;
      } else {
         StopWatch stopWatch = (StopWatch)o;
         if (this.elapsedTime != stopWatch.elapsedTime) {
            return false;
         } else if (this.startTime != stopWatch.startTime) {
            return false;
         } else {
            if (this.message != null) {
               if (!this.message.equals(stopWatch.message)) {
                  return false;
               }
            } else if (stopWatch.message != null) {
               return false;
            }

            if (this.tag != null) {
               if (!this.tag.equals(stopWatch.tag)) {
                  return false;
               }
            } else if (stopWatch.tag != null) {
               return false;
            }

            return true;
         }
      }
   }

   public int hashCode() {
      int result = (int)(this.startTime ^ this.startTime >>> 32);
      result = 31 * result + (int)(this.elapsedTime ^ this.elapsedTime >>> 32);
      result = 31 * result + (this.tag != null ? this.tag.hashCode() : 0);
      result = 31 * result + (this.message != null ? this.message.hashCode() : 0);
      return result;
   }
}
