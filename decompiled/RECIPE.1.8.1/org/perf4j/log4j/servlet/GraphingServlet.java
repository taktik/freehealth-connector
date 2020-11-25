package org.perf4j.log4j.servlet;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.perf4j.chart.StatisticsChartGenerator;
import org.perf4j.log4j.GraphingStatisticsAppender;
import org.perf4j.servlet.AbstractGraphingServlet;

public class GraphingServlet extends AbstractGraphingServlet {
   protected StatisticsChartGenerator getGraphByName(String name) {
      GraphingStatisticsAppender appender = GraphingStatisticsAppender.getAppenderByName(name);
      return appender == null ? null : appender.getChartGenerator();
   }

   protected List<String> getAllKnownGraphNames() {
      List<String> retVal = new ArrayList();
      Iterator i$ = GraphingStatisticsAppender.getAllGraphingStatisticsAppenders().iterator();

      while(i$.hasNext()) {
         GraphingStatisticsAppender appender = (GraphingStatisticsAppender)i$.next();
         retVal.add(appender.getName());
      }

      return retVal;
   }
}
