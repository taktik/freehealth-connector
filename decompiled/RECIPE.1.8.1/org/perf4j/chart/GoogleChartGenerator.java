package org.perf4j.chart;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.Map.Entry;
import org.perf4j.GroupedTimingStatistics;
import org.perf4j.TimingStatistics;
import org.perf4j.helpers.StatsValueRetriever;

public class GoogleChartGenerator implements StatisticsChartGenerator {
   public static final String DEFAULT_BASE_URL = "http://chart.apis.google.com/chart?";
   public static final int MAX_POSSIBLE_CHART_SIZE = 300000;
   public static final int DEFAULT_CHART_WIDTH = 750;
   public static final int DEFAULT_CHART_HEIGHT = 400;
   public static final String[] DEFAULT_SERIES_COLORS = new String[]{"ff0000", "00ff00", "0000ff", "00ffff", "ff00ff", "ffff00", "000000", "d2b48c", "ffa500", "a020f0"};
   private StatsValueRetriever valueRetriever;
   private String baseUrl;
   private LinkedList<GroupedTimingStatistics> data;
   private int width;
   private int height;
   private int maxDataPoints;
   private Set<String> enabledTags;

   public GoogleChartGenerator() {
      this(StatsValueRetriever.MEAN_VALUE_RETRIEVER, "http://chart.apis.google.com/chart?");
   }

   public GoogleChartGenerator(StatsValueRetriever statsValueRetriever) {
      this(statsValueRetriever, "http://chart.apis.google.com/chart?");
   }

   public GoogleChartGenerator(StatsValueRetriever valueRetriever, String baseUrl) {
      this.data = new LinkedList();
      this.width = 750;
      this.height = 400;
      this.maxDataPoints = 20;
      this.enabledTags = null;
      this.valueRetriever = valueRetriever;
      this.baseUrl = baseUrl;
   }

   public int getWidth() {
      return this.width;
   }

   public void setWidth(int width) {
      this.width = width;
   }

   public int getHeight() {
      return this.height;
   }

   public void setHeight(int height) {
      this.height = height;
   }

   public Set<String> getEnabledTags() {
      return this.enabledTags;
   }

   public void setEnabledTags(Set<String> enabledTags) {
      this.enabledTags = enabledTags;
   }

   public int getMaxDataPoints() {
      return this.maxDataPoints;
   }

   public void setMaxDataPoints(int maxDataPoints) {
      this.maxDataPoints = maxDataPoints;
   }

   public List<GroupedTimingStatistics> getData() {
      return Collections.unmodifiableList(this.data);
   }

   public synchronized void appendData(GroupedTimingStatistics statistics) {
      if (this.data.size() >= this.maxDataPoints) {
         this.data.removeFirst();
      }

      this.data.add(statistics);
   }

   public synchronized String getChartUrl() {
      if (this.width * this.height <= 300000 && this.width * this.height > 0) {
         StringBuilder retVal = new StringBuilder(this.baseUrl);
         retVal.append("cht=lxy");
         retVal.append("&chtt=").append(this.encodeUrl(this.valueRetriever.getValueName()));
         retVal.append("&chs=").append(this.width).append("x").append(this.height);
         retVal.append("&chxt=x,x,y");
         retVal.append(this.generateGoogleChartParams());
         return retVal.toString();
      } else {
         throw new IllegalArgumentException("The chart size must be between 0 and 300000 pixels. Current size is " + this.width + " x " + this.height);
      }
   }

   protected String generateGoogleChartParams() {
      long minTimeValue = Long.MAX_VALUE;
      long maxTimeValue = Long.MIN_VALUE;
      double maxDataValue = Double.MIN_VALUE;
      Map<String, List<Number>[]> tagsToXDataAndYData = new TreeMap();
      Iterator i$ = this.data.iterator();

      label69:
      while(i$.hasNext()) {
         GroupedTimingStatistics groupedTimingStatistics = (GroupedTimingStatistics)i$.next();
         Map<String, TimingStatistics> statsByTag = groupedTimingStatistics.getStatisticsByTag();
         long windowStartTime = groupedTimingStatistics.getStartTime();
         long windowLength = groupedTimingStatistics.getStopTime() - windowStartTime;
         minTimeValue = Math.min(minTimeValue, windowStartTime);
         maxTimeValue = Math.max(maxTimeValue, windowStartTime);
         Iterator i$ = statsByTag.entrySet().iterator();

         while(true) {
            Entry tagWithData;
            String tag;
            do {
               if (!i$.hasNext()) {
                  continue label69;
               }

               tagWithData = (Entry)i$.next();
               tag = (String)tagWithData.getKey();
            } while(this.enabledTags != null && !this.enabledTags.contains(tag));

            List<Number>[] xAndYData = (List[])tagsToXDataAndYData.get(tagWithData.getKey());
            if (xAndYData == null) {
               tagsToXDataAndYData.put(tag, xAndYData = new List[]{new ArrayList(), new ArrayList()});
            }

            Number yValue = this.valueRetriever.getStatsValue((TimingStatistics)tagWithData.getValue(), windowLength);
            xAndYData[0].add(windowStartTime);
            xAndYData[1].add(yValue);
            maxDataValue = Math.max(maxDataValue, yValue.doubleValue());
         }
      }

      if (tagsToXDataAndYData.isEmpty()) {
         return "";
      } else {
         DecimalFormat decimalFormat = new DecimalFormat("##0.0", new DecimalFormatSymbols(Locale.US));
         SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
         dateFormat.setTimeZone(GroupedTimingStatistics.getTimeZone());
         String axisRangeParam = "&chxr=2,0," + decimalFormat.format(maxDataValue);
         int stepSize = this.data.size() / 10 + 1;
         StringBuilder timeAxisLabels = new StringBuilder("&chxl=0:");
         StringBuilder timeAxisLabelPositions = new StringBuilder("&chxp=0");
         Iterator iter = this.data.iterator();

         int i;
         while(iter.hasNext()) {
            GroupedTimingStatistics groupedTimingStatistics = (GroupedTimingStatistics)iter.next();
            long windowStartTime = groupedTimingStatistics.getStartTime();
            String label = dateFormat.format(new Date(windowStartTime));
            double position = 100.0D * (double)(windowStartTime - minTimeValue) / (double)(maxTimeValue - minTimeValue);
            timeAxisLabels.append("|").append(label);
            timeAxisLabelPositions.append(",").append(decimalFormat.format(position));

            for(i = 1; i < stepSize && iter.hasNext(); ++i) {
               iter.next();
            }
         }

         timeAxisLabels.append("|1:|Time");
         timeAxisLabelPositions.append("|1,50");
         double xAxisGridlineStepSize = this.data.size() > 2 ? 100.0D / (double)(this.data.size() - 1) : 50.0D;
         String gridlinesParam = "&chg=" + decimalFormat.format(xAxisGridlineStepSize) + ",10";
         StringBuilder chartDataParam = new StringBuilder("&chd=t:");
         StringBuilder chartColorsParam = new StringBuilder("&chco=");
         StringBuilder chartShapeMarkerParam = new StringBuilder("&chm=");
         StringBuilder chartLegendParam = new StringBuilder("&chdl=");
         i = 0;

         for(Iterator iter = tagsToXDataAndYData.entrySet().iterator(); iter.hasNext(); ++i) {
            Entry<String, List<Number>[]> tagWithXAndYData = (Entry)iter.next();
            List<Number> xValues = ((List[])tagWithXAndYData.getValue())[0];
            chartDataParam.append(this.numberValuesToGoogleDataSeriesParam(xValues, (double)minTimeValue, (double)maxTimeValue));
            chartDataParam.append("|");
            List<Number> yValues = ((List[])tagWithXAndYData.getValue())[1];
            chartDataParam.append(this.numberValuesToGoogleDataSeriesParam(yValues, 0.0D, maxDataValue));
            String color = DEFAULT_SERIES_COLORS[i % DEFAULT_SERIES_COLORS.length];
            chartColorsParam.append(color);
            chartShapeMarkerParam.append("d,").append(color).append(",").append(i).append(",-1,5.0");
            chartLegendParam.append((String)tagWithXAndYData.getKey());
            if (iter.hasNext()) {
               chartDataParam.append("|");
               chartColorsParam.append(",");
               chartShapeMarkerParam.append("|");
               chartLegendParam.append("|");
            }
         }

         return chartDataParam.toString() + chartColorsParam + chartShapeMarkerParam + chartLegendParam + axisRangeParam + timeAxisLabels + timeAxisLabelPositions + gridlinesParam;
      }
   }

   protected String numberValuesToGoogleDataSeriesParam(List<Number> values, double minPossibleValue, double maxPossibleValue) {
      StringBuilder retVal = new StringBuilder();
      double valueRange = maxPossibleValue - minPossibleValue;
      DecimalFormat formatter = new DecimalFormat("##0.0", new DecimalFormatSymbols(Locale.US));
      Iterator iter = values.iterator();

      while(iter.hasNext()) {
         Number value = (Number)iter.next();
         double normalizedNumber = 100.0D * (value.doubleValue() - minPossibleValue) / valueRange;
         retVal.append(formatter.format(normalizedNumber));
         if (iter.hasNext()) {
            retVal.append(",");
         }
      }

      return retVal.toString();
   }

   protected String encodeUrl(String string) {
      try {
         return URLEncoder.encode(string, "UTF-8");
      } catch (UnsupportedEncodingException var3) {
         return string;
      }
   }
}
