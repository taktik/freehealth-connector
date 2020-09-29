package org.perf4j.log4j;

import org.apache.log4j.Layout;
import org.apache.log4j.spi.LoggingEvent;
import org.apache.log4j.spi.OptionHandler;
import org.perf4j.GroupedTimingStatistics;
import org.perf4j.helpers.GroupedTimingStatisticsCsvFormatter;
import org.perf4j.helpers.MiscUtils;

public class StatisticsCsvLayout extends Layout implements OptionHandler {
   private boolean pivot = false;
   private String columns = "tag,start,stop,mean,min,max,stddev,count";
   private boolean printNonStatistics = false;
   protected GroupedTimingStatisticsCsvFormatter csvFormatter;

   public boolean isPivot() {
      return this.pivot;
   }

   public void setPivot(boolean pivot) {
      this.pivot = pivot;
   }

   public String getColumns() {
      return this.columns;
   }

   public void setColumns(String columns) {
      this.columns = columns;
   }

   public boolean isPrintNonStatistics() {
      return this.printNonStatistics;
   }

   public void setPrintNonStatistics(boolean printNonStatistics) {
      this.printNonStatistics = printNonStatistics;
   }

   public String format(LoggingEvent event) {
      try {
         return this.csvFormatter.format((GroupedTimingStatistics)event.getMessage());
      } catch (ClassCastException var3) {
         return this.isPrintNonStatistics() ? MiscUtils.escapeStringForCsv(event.getMessage().toString(), new StringBuilder()).append(MiscUtils.NEWLINE).toString() : "";
      }
   }

   public boolean ignoresThrowable() {
      return true;
   }

   public void activateOptions() {
      this.csvFormatter = new GroupedTimingStatisticsCsvFormatter(this.isPivot(), this.getColumns());
   }
}
