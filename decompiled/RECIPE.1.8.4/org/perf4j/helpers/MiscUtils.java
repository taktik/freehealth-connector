package org.perf4j.helpers;

import java.util.Calendar;
import org.perf4j.GroupedTimingStatistics;

public class MiscUtils {
   public static final String NEWLINE = System.getProperty("line.separator");

   public static StringBuilder escapeStringForCsv(String string, StringBuilder toAppend) {
      toAppend.append('"');
      int lastQuoteIndex = 0;

      for(int i = 0; i < string.length(); ++i) {
         char charAtIndex = string.charAt(i);
         if ('"' == charAtIndex) {
            toAppend.append(string.substring(lastQuoteIndex, i)).append("\"\"");
            lastQuoteIndex = i + 1;
         }
      }

      toAppend.append(string.substring(lastQuoteIndex));
      return toAppend.append('"');
   }

   public static StringBuilder padIntToTwoDigits(int i, StringBuilder toAppend) {
      if (i < 10) {
         toAppend.append("0");
      }

      return toAppend.append(i);
   }

   public static String formatDateIso8601(long timeInMillis) {
      StringBuilder retVal = new StringBuilder(19);
      Calendar cal = Calendar.getInstance(GroupedTimingStatistics.getTimeZone());
      cal.setTimeInMillis(timeInMillis);
      int year = cal.get(1);
      int month = cal.get(2);
      int day = cal.get(5);
      int hour = cal.get(11);
      int minute = cal.get(12);
      int second = cal.get(13);
      retVal.append(year).append('-');
      padIntToTwoDigits(month + 1, retVal).append('-');
      padIntToTwoDigits(day, retVal).append(' ');
      padIntToTwoDigits(hour, retVal).append(':');
      padIntToTwoDigits(minute, retVal).append(':');
      return padIntToTwoDigits(second, retVal).toString();
   }

   public static String[] splitAndTrim(String stringToSplit, String delimiter) {
      String[] retVal = stringToSplit.split(delimiter);

      for(int i = 0; i < retVal.length; ++i) {
         retVal[i] = retVal[i].trim();
      }

      return retVal;
   }
}
