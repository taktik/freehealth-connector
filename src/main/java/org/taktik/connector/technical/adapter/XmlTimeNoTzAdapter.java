package org.taktik.connector.technical.adapter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDateTime;
import org.joda.time.format.ISODateTimeFormat;
import org.taktik.connector.technical.utils.DateUtils;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.text.MessageFormat;

public class XmlTimeNoTzAdapter extends XmlTimeAdapter {
   public static DateTimeZone noTimeZone = DateTimeZone.forOffsetMillis(0);
   private final static String regex = "(\\d{2}):(\\d{2}):(\\d{2})\\..+";
   private static final Log log = LogFactory.getLog(XmlTimeNoTzAdapter.class);

   public DateTime unmarshal(String value) throws Exception {
      if (value == null) return null;
      try {
         DateTime dateTime = LocalDateTime.parse(value, ISODateTimeFormat.localTimeParser()).toDateTime(noTimeZone);
         log.debug(MessageFormat.format("Unmarshal {0} to {1}", value, dateTime));
         return dateTime;
      } catch (Exception var3) {
         log.warn(MessageFormat.format("Unable to parse time {0}", value));

         if (value.matches(regex)) {
            return this.unmarshal(value.replaceAll(regex, "$1:$2:$3"));
         } else {
            return DateUtils.parseTime(value);
         }
      }
   }

   public String marshal(DateTime value) throws Exception {
      if (value == null) return null;
      String s = value.getZone() == noTimeZone ? value.toLocalDateTime().toString(ISODateTimeFormat.time()) : super.marshal(value);
      log.debug(MessageFormat.format("Marshal {0} to {1}", value, s));
      return s;
   }
}
