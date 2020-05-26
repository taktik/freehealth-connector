package org.perf4j.log4j;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.perf4j.LoggingStopWatch;

public class Log4JStopWatch extends LoggingStopWatch {
   private transient Logger logger;
   private Level normalPriority;
   private Level exceptionPriority;

   public Log4JStopWatch() {
      this("", (String)null, Logger.getLogger("org.perf4j.TimingLogger"), Level.INFO, Level.WARN);
   }

   public Log4JStopWatch(Logger logger) {
      this("", (String)null, logger, Level.INFO, Level.WARN);
   }

   public Log4JStopWatch(Logger logger, Level normalPriority) {
      this("", (String)null, logger, normalPriority, Level.WARN);
   }

   public Log4JStopWatch(Logger logger, Level normalPriority, Level exceptionPriority) {
      this("", (String)null, logger, normalPriority, exceptionPriority);
   }

   public Log4JStopWatch(String tag) {
      this(tag, (String)null, Logger.getLogger("org.perf4j.TimingLogger"), Level.INFO, Level.WARN);
   }

   public Log4JStopWatch(String tag, Logger logger) {
      this(tag, (String)null, logger, Level.INFO, Level.WARN);
   }

   public Log4JStopWatch(String tag, Logger logger, Level normalPriority) {
      this(tag, (String)null, logger, normalPriority, Level.WARN);
   }

   public Log4JStopWatch(String tag, Logger logger, Level normalPriority, Level exceptionPriority) {
      this(tag, (String)null, logger, normalPriority, exceptionPriority);
   }

   public Log4JStopWatch(String tag, String message) {
      this(tag, message, Logger.getLogger("org.perf4j.TimingLogger"), Level.INFO, Level.WARN);
   }

   public Log4JStopWatch(String tag, String message, Logger logger) {
      this(tag, message, logger, Level.INFO, Level.WARN);
   }

   public Log4JStopWatch(String tag, String message, Logger logger, Level normalPriority) {
      this(tag, message, logger, normalPriority, Level.WARN);
   }

   public Log4JStopWatch(String tag, String message, Logger logger, Level normalPriority, Level exceptionPriority) {
      this(System.currentTimeMillis(), -1L, tag, message, logger, normalPriority, exceptionPriority);
   }

   public Log4JStopWatch(long startTime, long elapsedTime, String tag, String message, Logger logger, Level normalPriority, Level exceptionPriority) {
      super(startTime, elapsedTime, tag, message);
      this.logger = logger;
      this.normalPriority = normalPriority;
      this.exceptionPriority = exceptionPriority;
   }

   public Logger getLogger() {
      return this.logger;
   }

   public Log4JStopWatch setLogger(Logger logger) {
      this.logger = logger;
      return this;
   }

   public Level getNormalPriority() {
      return this.normalPriority;
   }

   public Log4JStopWatch setNormalPriority(Level normalPriority) {
      this.normalPriority = normalPriority;
      return this;
   }

   public Level getExceptionPriority() {
      return this.exceptionPriority;
   }

   public Log4JStopWatch setExceptionPriority(Level exceptionPriority) {
      this.exceptionPriority = exceptionPriority;
      return this;
   }

   public Log4JStopWatch setTimeThreshold(long timeThreshold) {
      super.setTimeThreshold(timeThreshold);
      return this;
   }

   public Log4JStopWatch setTag(String tag) {
      super.setTag(tag);
      return this;
   }

   public Log4JStopWatch setMessage(String message) {
      super.setMessage(message);
      return this;
   }

   public boolean isLogging() {
      return this.logger.isEnabledFor(this.normalPriority);
   }

   protected void log(String stopWatchAsString, Throwable exception) {
      this.logger.log(exception == null ? this.normalPriority : this.exceptionPriority, stopWatchAsString, exception);
   }

   public Log4JStopWatch clone() {
      return (Log4JStopWatch)super.clone();
   }

   private void writeObject(ObjectOutputStream stream) throws IOException {
      stream.defaultWriteObject();
      stream.writeUTF(this.logger.getName());
   }

   private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
      stream.defaultReadObject();
      this.logger = Logger.getLogger(stream.readUTF());
   }
}
