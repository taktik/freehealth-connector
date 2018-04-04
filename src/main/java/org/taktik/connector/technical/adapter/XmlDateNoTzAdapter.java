package org.taktik.connector.technical.adapter;

import org.taktik.connector.technical.utils.DateUtils;
import org.joda.time.DateTime;

public class XmlDateNoTzAdapter extends XmlDateAdapter {
   public String marshal(DateTime value) throws Exception {
      return DateUtils.printDateWithoutTimeZone(value);
   }
}
