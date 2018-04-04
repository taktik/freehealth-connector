package org.perf4j.helpers;

import org.perf4j.GroupedTimingStatistics;

public class GroupedTimingStatisticsTextFormatter implements GroupedTimingStatisticsFormatter {
   public String format(GroupedTimingStatistics stats) {
      return stats.toString() + MiscUtils.NEWLINE;
   }
}
