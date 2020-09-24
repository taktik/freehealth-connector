package org.perf4j.chart;

import java.util.List;
import org.perf4j.GroupedTimingStatistics;

public interface StatisticsChartGenerator {
   int DEFAULT_MAX_DATA_POINTS = 20;

   String getChartUrl();

   void appendData(GroupedTimingStatistics var1);

   List<GroupedTimingStatistics> getData();
}
