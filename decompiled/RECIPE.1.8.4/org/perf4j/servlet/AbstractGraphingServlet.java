package org.perf4j.servlet;

import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.perf4j.chart.StatisticsChartGenerator;
import org.perf4j.helpers.MiscUtils;

public abstract class AbstractGraphingServlet extends HttpServlet {
   protected List<String> graphNames;

   public void init() throws ServletException {
      String graphNamesString = this.getInitParameter("graphNames");
      if (graphNamesString != null) {
         this.graphNames = Arrays.asList(MiscUtils.splitAndTrim(graphNamesString, ","));
      }

   }

   public void destroy() {
      this.graphNames = null;
   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      Map<String, StatisticsChartGenerator> chartsByName = this.getChartGeneratorsToDisplay(request);
      response.setContentType("text/html;charset=utf-8");
      this.writeHeader(request, response);
      Iterator i$ = chartsByName.entrySet().iterator();

      while(i$.hasNext()) {
         Entry<String, StatisticsChartGenerator> nameAndChart = (Entry)i$.next();
         this.writeChart((String)nameAndChart.getKey(), (StatisticsChartGenerator)nameAndChart.getValue(), request, response);
      }

      this.writeFooter(request, response);
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      this.doGet(request, response);
   }

   protected void writeHeader(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      response.getWriter().println("<html>");
      response.getWriter().println("<head>");
      response.getWriter().println("<title>Perf4J Performance Graphs</title>");
      if (request.getParameter("refreshRate") != null) {
         int refreshRate = Integer.parseInt(request.getParameter("refreshRate"));
         response.getWriter().println("<meta http-equiv=\"refresh\" content=\"" + refreshRate + "\">");
      }

      response.getWriter().println("<head>");
      response.getWriter().println("<body>");
   }

   protected void writeChart(String name, StatisticsChartGenerator chartGenerator, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      response.getWriter().println("<br><br>");
      String chartUrl = chartGenerator == null ? null : chartGenerator.getChartUrl();
      if (chartUrl != null) {
         response.getWriter().println("<b>" + name + "</b><br>");
         response.getWriter().println("<img src=\"" + chartUrl + "\">");
      } else {
         response.getWriter().println("<b>Unknown graph name: " + name + "</b><br>");
      }

   }

   protected void writeFooter(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      response.getWriter().println("</body>");
      response.getWriter().println("</html>");
      response.getWriter().flush();
   }

   protected Map<String, StatisticsChartGenerator> getChartGeneratorsToDisplay(HttpServletRequest request) {
      List graphsToDisplay;
      if (request.getParameter("graphName") != null) {
         graphsToDisplay = Arrays.asList(request.getParameterValues("graphName"));
      } else if (this.graphNames != null) {
         graphsToDisplay = this.graphNames;
      } else {
         graphsToDisplay = this.getAllKnownGraphNames();
      }

      Map<String, StatisticsChartGenerator> retVal = new LinkedHashMap();
      Iterator i$ = graphsToDisplay.iterator();

      while(i$.hasNext()) {
         String graphName = (String)i$.next();
         retVal.put(graphName, this.getGraphByName(graphName));
      }

      return retVal;
   }

   protected abstract StatisticsChartGenerator getGraphByName(String var1);

   protected abstract List<String> getAllKnownGraphNames();
}
