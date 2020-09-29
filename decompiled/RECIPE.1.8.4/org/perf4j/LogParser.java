package org.perf4j;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.perf4j.chart.GoogleChartGenerator;
import org.perf4j.chart.StatisticsChartGenerator;
import org.perf4j.helpers.GroupedTimingStatisticsCsvFormatter;
import org.perf4j.helpers.GroupedTimingStatisticsFormatter;
import org.perf4j.helpers.GroupedTimingStatisticsTextFormatter;
import org.perf4j.helpers.GroupingStatisticsIterator;
import org.perf4j.helpers.StatsValueRetriever;
import org.perf4j.helpers.StopWatchLogIterator;

public class LogParser {
   private Reader inputLog;
   private PrintStream statisticsOutput;
   private PrintStream graphingOutput;
   private StatisticsChartGenerator meanTimeChartGenerator;
   private StatisticsChartGenerator tpsChartGenerator;
   private long timeSlice;
   private boolean createRollupStatistics;
   private GroupedTimingStatisticsFormatter statisticsFormatter;

   public LogParser() {
      this(new InputStreamReader(System.in), System.out, (PrintStream)null, 30000L, false, new GroupedTimingStatisticsTextFormatter());
   }

   public LogParser(Reader inputLog, PrintStream statisticsOutput, PrintStream graphingOutput, long timeSlice, boolean createRollupStatistics, GroupedTimingStatisticsFormatter statisticsFormatter) {
      this.inputLog = inputLog;
      this.statisticsOutput = statisticsOutput;
      this.graphingOutput = graphingOutput;
      this.timeSlice = timeSlice;
      this.createRollupStatistics = createRollupStatistics;
      if (graphingOutput != null) {
         this.meanTimeChartGenerator = this.newMeanTimeChartGenerator();
         this.tpsChartGenerator = this.newTpsChartGenerator();
      }

      this.statisticsFormatter = statisticsFormatter;
   }

   public void parseLog() {
      Iterator<StopWatch> stopWatchIter = new StopWatchLogIterator(this.inputLog);
      int i = 0;
      GroupingStatisticsIterator statsIter = new GroupingStatisticsIterator(stopWatchIter, this.timeSlice, this.createRollupStatistics);

      while(true) {
         do {
            GroupedTimingStatistics statistics;
            do {
               if (!statsIter.hasNext()) {
                  return;
               }

               statistics = statsIter.next();
               if (this.statisticsOutput != null) {
                  this.statisticsOutput.print(this.statisticsFormatter.format(statistics));
               }
            } while(this.graphingOutput == null);

            this.meanTimeChartGenerator.appendData(statistics);
            this.tpsChartGenerator.appendData(statistics);
            ++i;
         } while(i % 20 != 0 && statsIter.hasNext());

         this.printGraphOutput();
      }
   }

   protected StatisticsChartGenerator newMeanTimeChartGenerator() {
      return new GoogleChartGenerator();
   }

   protected StatisticsChartGenerator newTpsChartGenerator() {
      return new GoogleChartGenerator(StatsValueRetriever.TPS_VALUE_RETRIEVER);
   }

   protected void printGraphOutput() {
      this.graphingOutput.println("<br/><br/><img src=\"" + this.meanTimeChartGenerator.getChartUrl() + "\"/>");
      this.graphingOutput.println("<br/><br/><img src=\"" + this.tpsChartGenerator.getChartUrl() + "\"/>");
   }

   public static void main(String[] args) {
      System.exit(runMain(args));
   }

   public static int runMain(String[] args) {
      try {
         List<String> argsList = new ArrayList(Arrays.asList(args));
         if (printUsage(argsList)) {
            return 0;
         } else {
            PrintStream statisticsOutput = openStatisticsOutput(argsList);
            PrintStream graphingOutput = openGraphingOutput(argsList);
            long timeSlice = getTimeSlice(argsList);
            boolean rollupStatistics = getRollupStatistics(argsList);
            GroupedTimingStatisticsFormatter formatter = getStatisticsFormatter(argsList);
            Reader input = openInput(argsList);
            if (!argsList.isEmpty()) {
               printUnknownArgs(argsList);
               return 1;
            } else {
               (new LogParser(input, statisticsOutput, graphingOutput, timeSlice, rollupStatistics, formatter)).parseLog();
               closeGraphingOutput(graphingOutput);
               return 0;
            }
         }
      } catch (Exception var9) {
         var9.printStackTrace();
         return 1;
      }
   }

   protected static boolean printUsage(List<String> argsList) {
      if (getIndexOfArg(argsList, false, "-h", "--help", "-?", "--usage") >= 0) {
         System.out.println("Usage: LogParser [-o|--out|--output outputFile] [-g|--graph graphingOutputFile] [-t|--timeslice timeslice] [-r] [-f|--format text|csv] [logInputFile]");
         System.out.println("Arguments:");
         System.out.println("  logInputFile - The log file to be parsed. If not specified, log data is read from stdin.");
         System.out.println("  -o|--out|--output outputFile - The file where generated statistics should be written. If not specified, statistics are written to stdout.");
         System.out.println("  -g|--graph graphingOutputFile - The file where generated perf graphs should be written. If not specified, no graphs are written.");
         System.out.println("  -t|--timeslice timeslice - The length of time (in ms) of each timeslice for which statistics should be generated. Defaults to 30000 ms.");
         System.out.println("  -r - Whether or not statistics rollups should be generated. If not specified, rollups are not generated.");
         System.out.println("  -f|--format text|csv - The format for the statistics output, either plain text or CSV. Defaults to text.");
         System.out.println("                         If format is csv, then the columns output are tag, start, stop, mean, min, max, stddev, and count.");
         System.out.println();
         System.out.println("Note that out, stdout, err and stderr can be used as aliases to the standard output streams when specifying output files.");
         return true;
      } else {
         return false;
      }
   }

   protected static PrintStream openStatisticsOutput(List<String> argsList) throws IOException {
      int indexOfOut = getIndexOfArg(argsList, true, "-o", "--output", "--out");
      if (indexOfOut >= 0) {
         String fileName = (String)argsList.remove(indexOfOut + 1);
         argsList.remove(indexOfOut);
         return openStream(fileName);
      } else {
         return System.out;
      }
   }

   protected static PrintStream openGraphingOutput(List<String> argsList) throws IOException {
      int indexOfOut = getIndexOfArg(argsList, true, "-g", "--graph");
      if (indexOfOut >= 0) {
         String fileName = (String)argsList.remove(indexOfOut + 1);
         argsList.remove(indexOfOut);
         PrintStream retVal = openStream(fileName);
         retVal.println("<html>");
         retVal.println("<head><title>Perf4J Performance Graphs</title></head>");
         retVal.println("<body>");
         return retVal;
      } else {
         return null;
      }
   }

   protected static void closeGraphingOutput(PrintStream graphingOutput) throws IOException {
      if (graphingOutput != null) {
         graphingOutput.println("</body></html>");
         if (graphingOutput != System.out && graphingOutput != System.err) {
            graphingOutput.close();
         }
      }

   }

   protected static long getTimeSlice(List<String> argsList) {
      int indexOfOut = getIndexOfArg(argsList, true, "-t", "--timeslice");
      if (indexOfOut >= 0) {
         String timeslice = (String)argsList.remove(indexOfOut + 1);
         argsList.remove(indexOfOut);
         return Long.parseLong(timeslice);
      } else {
         return 30000L;
      }
   }

   protected static boolean getRollupStatistics(List<String> argsList) {
      int indexOfOut = getIndexOfArg(argsList, false, "-r", "--rollup");
      if (indexOfOut >= 0) {
         argsList.remove(indexOfOut);
         return true;
      } else {
         return false;
      }
   }

   protected static GroupedTimingStatisticsFormatter getStatisticsFormatter(List<String> argsList) {
      int indexOfFormat = getIndexOfArg(argsList, true, "-f", "--format");
      if (indexOfFormat >= 0) {
         String formatString = (String)argsList.remove(indexOfFormat + 1);
         argsList.remove(indexOfFormat);
         if ("text".equalsIgnoreCase(formatString)) {
            return new GroupedTimingStatisticsTextFormatter();
         } else if ("csv".equalsIgnoreCase(formatString)) {
            return new GroupedTimingStatisticsCsvFormatter();
         } else {
            throw new IllegalArgumentException("Unknown format type: " + formatString);
         }
      } else {
         return new GroupedTimingStatisticsTextFormatter();
      }
   }

   protected static Reader openInput(List<String> argsList) throws IOException {
      if (argsList.isEmpty()) {
         return new InputStreamReader(System.in);
      } else {
         String fileName = (String)argsList.remove(0);
         return new BufferedReader(new FileReader(fileName));
      }
   }

   protected static void printUnknownArgs(List<String> argsList) {
      System.out.println("Unknown arguments: ");
      Iterator i$ = argsList.iterator();

      while(i$.hasNext()) {
         String arg = (String)i$.next();
         System.out.print(arg + " ");
      }

      System.out.println();
   }

   protected static int getIndexOfArg(List<String> args, boolean needsParam, String... argNames) {
      int retVal = -1;
      boolean foundArg = false;
      String[] arr$ = argNames;
      int len$ = argNames.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         String argName = arr$[i$];
         int argIndex = args.indexOf(argName);
         if (argIndex >= 0) {
            if (foundArg) {
               throw new IllegalArgumentException("You must specify only one of " + Arrays.toString(argNames));
            }

            retVal = argIndex;
            foundArg = true;
         }
      }

      if (retVal >= 0 && needsParam && retVal == args.size() - 1) {
         throw new IllegalArgumentException("You must specify a parameter for the " + (String)args.get(retVal) + " arg");
      } else {
         return retVal;
      }
   }

   protected static PrintStream openStream(String fileName) throws IOException {
      if (!"stdout".equals(fileName) && !"out".equals(fileName)) {
         return !"stderr".equals(fileName) && !"err".equals(fileName) ? new PrintStream(new FileOutputStream(fileName), true) : System.err;
      } else {
         return System.out;
      }
   }
}
