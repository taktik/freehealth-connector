package be.recipe.common.util;

import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

@XmlTransient
public class JodaDateTimeAdapter extends XmlAdapter<String, DateTime> {
   private static final DateTimeFormatter DATE_PATTERN = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");

   public DateTime unmarshal(String dateStr) {
      DateTime result = DATE_PATTERN.parseDateTime(dateStr);
      return result;
   }

   public String marshal(DateTime dateTime) {
      String result = DATE_PATTERN.print(dateTime);
      return result;
   }
}
