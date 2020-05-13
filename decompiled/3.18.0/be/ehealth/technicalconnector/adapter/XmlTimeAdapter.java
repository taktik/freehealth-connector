package be.ehealth.technicalconnector.adapter;

import be.ehealth.technicalconnector.utils.DateUtils;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import org.joda.time.DateTime;

public class XmlTimeAdapter extends XmlAdapter<String, DateTime> {
   public DateTime unmarshal(String value) throws Exception {
      return DateUtils.parseTime(value);
   }

   public String marshal(DateTime value) throws Exception {
      return DateUtils.printTime(value);
   }
}
