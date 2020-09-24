package be.recipe.common.util;

import java.util.Calendar;
import javax.xml.bind.DatatypeConverter;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlAdapter;

@XmlTransient
public class CalendarAdapter extends XmlAdapter<String, Calendar> {
   public Calendar unmarshal(String dateStr) {
      return DatatypeConverter.parseDateTime(dateStr);
   }

   public String marshal(Calendar dateTime) {
      return DatatypeConverter.printDateTime(dateTime);
   }
}
