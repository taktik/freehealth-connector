package org.taktik.connector.technical.adapter;

import org.taktik.connector.technical.utils.DateUtils;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import org.joda.time.DateTime;

public class XmlDateTimeAdapter extends XmlAdapter<String, DateTime> {
   public DateTime unmarshal(String value) throws Exception {
      return DateUtils.parseDateTime(value);
   }

   public String marshal(DateTime value) throws Exception {
      return DateUtils.printDateTime(value);
   }
}
