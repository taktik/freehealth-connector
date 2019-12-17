package org.taktik.connector.technical.adapter;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.taktik.connector.technical.utils.DateUtils;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class XmlTimeNoTzAdapter extends XmlTimeAdapter {
   public static DateTimeZone noTimeZone = DateTimeZone.forOffsetMillis(3600001);

   public String marshal(DateTime value) throws Exception {
      return value.getZone() == noTimeZone ? DateUtils.printTimeWithoutTimeZone(value) : super.marshal(value);
   }
}
