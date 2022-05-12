package be.ehealth.technicalconnector.adapter;

import be.ehealth.technicalconnector.utils.DateUtils;
import org.joda.time.DateTime;

public class XmlDateNoTzAdapter extends XmlDateAdapter {
   public XmlDateNoTzAdapter() {
   }

   public String marshal(DateTime value) throws Exception {
      return DateUtils.printDateWithoutTimeZone(value);
   }
}
