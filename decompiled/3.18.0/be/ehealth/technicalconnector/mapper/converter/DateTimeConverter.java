package be.ehealth.technicalconnector.mapper.converter;

import java.util.Calendar;
import java.util.Locale;
import org.dozer.CustomConverter;
import org.dozer.MappingException;
import org.joda.time.DateTime;

public class DateTimeConverter implements CustomConverter {
   public Object convert(Object destination, Object source, Class destClass, Class sourceClass) {
      if (source == null) {
         return null;
      } else if (source instanceof Calendar) {
         return new DateTime(source);
      } else if (source instanceof DateTime) {
         return ((DateTime)source).toCalendar(Locale.getDefault());
      } else {
         throw new MappingException("Converter DateTimeConverter used incorrectly. Arguments passed in were:" + destination + " and " + source);
      }
   }
}
