package org.taktik.connector.technical.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import javax.xml.bind.DatatypeConverter;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.ISODateTimeFormat;

public final class DateUtils {
   private DateUtils() {
      throw new UnsupportedOperationException();
   }

   public static DateTime parseDateTime(String lexicalXSDDate) {
      return lexicalXSDDate != null && !lexicalXSDDate.isEmpty() ? convert(DatatypeConverter.parseDateTime(lexicalXSDDate)) : null;
   }

   public static String printDateTime(DateTime dateTime) {
      return dateTime == null ? null : DatatypeConverter.printDateTime(convert(dateTime));
   }

   public static DateTime parseTime(String lexicalXSDDate) {
      return lexicalXSDDate != null && !lexicalXSDDate.isEmpty() ? convert(DatatypeConverter.parseTime(lexicalXSDDate)) : null;
   }

   public static String printTime(DateTime dateTime) {
      return dateTime == null ? null : DatatypeConverter.printTime(convert(dateTime));
   }

   public static String printTimeWithoutTimezone(DateTime dateTime) {
      return dateTime == null ? null : DateTimeFormat.forPattern("HH:mm:ss").print(dateTime);
   }

   public static DateTime parseDate(String lexicalXSDDate) {
      return lexicalXSDDate != null && !lexicalXSDDate.isEmpty() ? convert(DatatypeConverter.parseDate(lexicalXSDDate)) : null;
   }

   public static String printDate(DateTime dateTime) {
      return dateTime == null ? null : DatatypeConverter.printDate(convert(dateTime));
   }

   public static String printDateWithoutTimeZone(DateTime dateTime) {
      return dateTime == null ? null : ISODateTimeFormat.date().print(dateTime);
   }

   public static DateTime convert(Calendar cal) {
      return cal == null ? null : new DateTime(cal);
   }

   public static DateTime convert(Date date) {
      return date == null ? null : new DateTime(date);
   }

   private static Calendar convert(DateTime dateTime) {
      return dateTime == null ? null : dateTime.toCalendar(Locale.getDefault());
   }
}
