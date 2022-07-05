package be.ehealth.technicalconnector.adapter;

import be.ehealth.technicalconnector.utils.DateUtils;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import org.joda.time.DateTime;

public class XmlDateTimeAdapter extends XmlAdapter<String, DateTime> {
   public XmlDateTimeAdapter() {
   }

   public DateTime unmarshal(String value) throws Exception {
      return DateUtils.parseDateTime(value);
   }

   public String marshal(DateTime value) throws Exception {
      return DateUtils.printDateTime(value);
   }
}
