package org.taktik.connector.technical.adapter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joda.time.format.ISODateTimeFormat;
import org.taktik.connector.technical.utils.DateUtils;
import org.joda.time.DateTime;

import java.text.MessageFormat;

public class XmlDateNoTzAdapter extends XmlDateAdapter {
   Log log = LogFactory.getLog(XmlDateNoTzAdapter.class);

   public String marshal(DateTime value) throws Exception {
      String s = DateUtils.printDateWithoutTimeZone(value);
      log.debug(MessageFormat.format("Marshal {0} to {1}", value, s));
      return s;
   }

   public DateTime unmarshal(String value) throws Exception {
      DateTime dateTime = DateTime.parse(value.replaceAll("\\+[0-9][0-9]:[0-9][0-9]$", ""), ISODateTimeFormat.localDateParser());
      log.debug(MessageFormat.format("Unmarshal {0} to {1}", value, dateTime));
      return dateTime;
   }

}
