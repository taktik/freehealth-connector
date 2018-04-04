package org.apache.log4j;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import org.apache.log4j.helpers.LogLog;
import org.apache.log4j.spi.LoggingEvent;

public class DailyMaxRollingFileAppender extends FileAppender {
   static final int TOP_OF_TROUBLE = -1;
   static final int TOP_OF_MINUTE = 0;
   static final int TOP_OF_HOUR = 1;
   static final int HALF_DAY = 2;
   static final int TOP_OF_DAY = 3;
   static final int TOP_OF_WEEK = 4;
   static final int TOP_OF_MONTH = 5;
   private String datePattern = "'.'yyyy-MM-dd";
   private int maxBackupIndex = 1;
   private String scheduledFilename;
   private long nextCheck = System.currentTimeMillis() - 1L;
   Date now = new Date();
   SimpleDateFormat sdf;
   RollingPastCalendar rpc = new RollingPastCalendar();
   int checkPeriod = -1;
   static final TimeZone gmtTimeZone = TimeZone.getTimeZone("GMT");

   public DailyMaxRollingFileAppender() {
   }

   public DailyMaxRollingFileAppender(Layout layout, String filename, String datePattern) throws IOException {
      super(layout, filename, true);
      this.datePattern = datePattern;
      this.activateOptions();
   }

   public void setDatePattern(String pattern) {
      this.datePattern = pattern;
   }

   public String getDatePattern() {
      return this.datePattern;
   }

   public void setMaxBackupIndex(int maxBackups) {
      this.maxBackupIndex = maxBackups;
   }

   public int getMaxBackupIndex() {
      return this.maxBackupIndex;
   }

   public void activateOptions() {
      super.activateOptions();
      LogLog.debug("Max backup file kept: " + this.maxBackupIndex + ".");
      if (this.datePattern != null && this.fileName != null) {
         this.now.setTime(System.currentTimeMillis());
         this.sdf = new SimpleDateFormat(this.datePattern);
         int type = this.computeCheckPeriod();
         this.printPeriodicity(type);
         this.rpc.setType(type);
         File file = new File(this.fileName);
         this.scheduledFilename = this.fileName + this.sdf.format(new Date(file.lastModified()));
      } else {
         LogLog.error("Either File or DatePattern options are not set for appender [" + this.name + "].");
      }

   }

   void printPeriodicity(int type) {
      switch(type) {
      case 0:
         LogLog.debug("Appender [[+name+]] to be rolled every minute.");
         break;
      case 1:
         LogLog.debug("Appender [" + this.name + "] to be rolled on top of every hour.");
         break;
      case 2:
         LogLog.debug("Appender [" + this.name + "] to be rolled at midday and midnight.");
         break;
      case 3:
         LogLog.debug("Appender [" + this.name + "] to be rolled at midnight.");
         break;
      case 4:
         LogLog.debug("Appender [" + this.name + "] to be rolled at start of week.");
         break;
      case 5:
         LogLog.debug("Appender [" + this.name + "] to be rolled at start of every month.");
         break;
      default:
         LogLog.warn("Unknown periodicity for appender [[+name+]].");
      }

   }

   int computeCheckPeriod() {
      RollingPastCalendar rollingPastCalendar = new RollingPastCalendar(gmtTimeZone, Locale.ENGLISH);
      Date epoch = new Date(0L);
      if (this.datePattern != null) {
         for(int i = 0; i <= 5; ++i) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(this.datePattern);
            simpleDateFormat.setTimeZone(gmtTimeZone);
            String r0 = simpleDateFormat.format(epoch);
            rollingPastCalendar.setType(i);
            Date next = new Date(rollingPastCalendar.getNextCheckMillis(epoch));
            String r1 = simpleDateFormat.format(next);
            if (r0 != null && r1 != null && !r0.equals(r1)) {
               return i;
            }
         }
      }

      return -1;
   }

   void rollOver() throws IOException {
      SimpleDateFormat simpleDateFormat = new SimpleDateFormat(this.datePattern);
      if (this.datePattern == null) {
         this.errorHandler.error("Missing DatePattern option in rollOver().");
      } else {
         String datedFilename = this.fileName + this.sdf.format(this.now);
         if (!this.scheduledFilename.equals(datedFilename)) {
            this.closeFile();
            File target = new File(this.scheduledFilename);
            if (target.exists()) {
               target.delete();
            }

            File file = new File(this.fileName);
            boolean result = file.renameTo(target);
            if (result) {
               LogLog.debug(this.fileName + " -> " + this.scheduledFilename);
               if (this.maxBackupIndex > 0) {
                  String dateBeforeStr = this.dateBefore();
                  Date tmpDeleteOlder = null;

                  try {
                     tmpDeleteOlder = simpleDateFormat.parse(dateBeforeStr);
                  } catch (ParseException var20) {
                     LogLog.error("parsing DatePattern failed " + dateBeforeStr);
                  }

                  file = new File(this.fileName + dateBeforeStr);
                  File logDir = file.getParentFile();
                  File[] logDirAll = logDir.listFiles();
                  String fileDateSubstring = null;
                  String tmpFileName = null;
                  Date tmpFileDate = null;
                  File[] var16 = logDirAll;
                  int var15 = logDirAll.length;

                  for(int var14 = 0; var14 < var15; ++var14) {
                     File logFile = var16[var14];
                     tmpFileName = logFile.getName();
                     fileDateSubstring = tmpFileName.substring(tmpFileName.lastIndexOf(46));
                     if (fileDateSubstring.contains(".20")) {
                        try {
                           tmpFileDate = simpleDateFormat.parse(fileDateSubstring);
                           if (tmpFileDate.before(tmpDeleteOlder)) {
                              logFile.delete();
                           }
                        } catch (ParseException var19) {
                           var19.printStackTrace();
                        }
                     }
                  }

                  if (file.exists()) {
                     file.delete();
                  }
               }
            } else {
               LogLog.error("Failed to rename [[+fileName+]] to [[+scheduledFilename+]].");
            }

            try {
               this.setFile(this.fileName, false, this.bufferedIO, this.bufferSize);
            } catch (IOException var18) {
               this.errorHandler.error("setFile(" + this.fileName + ", false) call failed.");
            }

            this.scheduledFilename = datedFilename;
         }
      }
   }

   private String dateBefore() {
      String dataAnte = "";
      if (this.datePattern != null) {
         SimpleDateFormat simpleDateFormat = new SimpleDateFormat(this.datePattern);
         dataAnte = simpleDateFormat.format(new Date(this.rpc.getPastCheckMillis(new Date(), this.maxBackupIndex)));
      }

      return dataAnte;
   }

   protected void subAppend(LoggingEvent event) {
      long n = System.currentTimeMillis();
      if (n >= this.nextCheck) {
         this.now.setTime(n);
         this.nextCheck = this.rpc.getNextCheckMillis(this.now);

         try {
            this.rollOver();
         } catch (IOException var5) {
            LogLog.error("rollOver() failed.", var5);
         }
      }

      super.subAppend(event);
   }

   public static void main(String[] args) {
      DailyMaxRollingFileAppender dmrfa = new DailyMaxRollingFileAppender();
      dmrfa.setDatePattern("'.'yyyy-MM-dd-HH-mm");
      dmrfa.setFile("prova");
      System.out.println("dmrfa.getMaxBackupIndex():" + dmrfa.getMaxBackupIndex());
      dmrfa.activateOptions();

      for(int i = 0; i < 5; ++i) {
         dmrfa.subAppend((LoggingEvent)null);

         try {
            Thread.sleep(60000L);
         } catch (InterruptedException var3) {
            ;
         }

         System.out.println("Fine attesa");
      }

   }
}
