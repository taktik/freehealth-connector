package be.apb.gfddpp.common.web;

import be.apb.gfddpp.common.log.Logger;
import be.apb.gfddpp.common.persistance.criteria.Criteria;
import be.apb.gfddpp.common.persistance.criteria.DateCriteria;
import be.apb.gfddpp.common.persistance.criteria.DateRange;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

public class JQGridParams {
   public static final String PAGE = "page";
   public static final String ROWS = "rows";
   public static final String SIDX = "sidx";
   public static final String SORD = "sord";
   public static final String MASK = "mask_";
   public static DateFormat DF_DATETIME = new SimpleDateFormat("dd-MM-yyyy HH:mm");
   public static DateFormat DF_DATE = new SimpleDateFormat("dd-MM-yyyy");
   private static Logger LOG = Logger.getLogger(JQGridParams.class);
   private QueryString qs;
   private int page;
   private int rows;
   private String sidx;
   private String sord;
   private int count;
   private int totalPages;
   private int start;

   public JQGridParams(QueryString query, int count) {
      this.qs = query;
      this.count = count;
      this.page = this.getNumericValue(this.qs, "page", 1);
      this.rows = this.getNumericValue(this.qs, "rows", 50);
      this.sidx = this.getValue(this.qs, "sidx", "");
      this.sord = this.getValue(this.qs, "sord", "asc");
      if (count > 0 && this.rows > 0) {
         this.totalPages = (int)Math.ceil(new Double((double)count) / new Double((double)this.rows));
      }

      if (this.page > this.totalPages) {
         this.page = this.totalPages;
      }

      this.start = this.rows * this.page - this.rows;
      if (this.start < 0) {
         this.start = 0;
      }

   }

   public static List<Criteria> createMasks(QueryString qs, Class clazz) {
      List<Criteria> masks = new ArrayList();
      Iterator var3 = qs.getParameterMap().entrySet().iterator();

      while(var3.hasNext()) {
         Entry<String, String[]> entry = (Entry)var3.next();
         if (((String)entry.getKey()).toLowerCase().startsWith("mask_")) {
            Field field = getField(clazz, ((String)entry.getKey()).replaceFirst("mask_", ""));
            if (field != null && !"".equals(entry.getValue())) {
               Criteria crit = null;
               if (field.getType().isAssignableFrom(Date.class)) {
                  crit = createDateMask(field, (String[])entry.getValue());
               } else if (field.getType().isEnum()) {
                  crit = createEnumMask(field, (String[])entry.getValue());
               } else {
                  String value = ((String[])entry.getValue())[0];
                  if (((String)entry.getKey()).equalsIgnoreCase("mask_tag")) {
                     value = value.replace("@", "#");
                  }

                  crit = new Criteria(field, value);
               }

               if (crit != null) {
                  masks.add(crit);
               }
            }
         }
      }

      return masks;
   }

   public static Criteria createMask(String key, String value, Class clazz) {
      QueryString qs = new QueryString("mask_" + key, value);
      List<Criteria> masks = createMasks(qs, clazz);
      return !masks.isEmpty() ? (Criteria)masks.get(0) : null;
   }

   private static Criteria<DateRange> createDateMask(Field field, String[] values) {
      if (!field.getType().isAssignableFrom(Date.class)) {
         return null;
      } else {
         Date d1 = null;
         Date d2 = null;
         if (values != null && values.length > 0) {
            d1 = parseDate(values[0]);
            if (values.length > 1 && !values[1].equals(values[0])) {
               d2 = parseDate(values[1]);
            } else {
               Calendar c1 = Calendar.getInstance();
               c1.setTime(d1);
               c1.set(11, 0);
               c1.set(12, 0);
               c1.set(13, 0);
               d1 = c1.getTime();
               Calendar c2 = Calendar.getInstance();
               c2.setTime(d1);
               c2.set(11, 23);
               c2.set(12, 59);
               c2.set(13, 59);
               d2 = c2.getTime();
            }
         }

         return new DateCriteria(field, new DateRange(d1, d2));
      }
   }

   private static Criteria createEnumMask(Field field, String[] values) {
      if (field.getType().isEnum() && values != null && values.length > 0) {
         Field[] enumFlds = field.getType().getDeclaredFields();
         Field[] var3 = enumFlds;
         int var4 = enumFlds.length;

         for(int var5 = 0; var5 < var4; ++var5) {
            Field enumField = var3[var5];
            if (enumField.isEnumConstant() && enumField.getName().equalsIgnoreCase(values[0])) {
               Class clazz = field.getType();
               return new Criteria(field, Enum.valueOf(clazz, enumField.getName()));
            }
         }
      }

      return null;
   }

   private static Date parseDate(String date) {
      try {
         return DF_DATETIME.parse(date);
      } catch (ParseException var5) {
         try {
            Date d = DF_DATE.parse(date);
            Calendar cal = Calendar.getInstance();
            cal.setTime(d);
            cal.set(10, 0);
            cal.set(12, 0);
            cal.set(13, 0);
            return cal.getTime();
         } catch (ParseException var4) {
            LOG.debug("Could not parse JQGrid date: [" + date + "]", var4);
            return null;
         }
      }
   }

   private static Field getField(Class clazz, String fieldName) {
      if (fieldName != null && !"".equals(fieldName)) {
         Field[] var2 = clazz.getDeclaredFields();
         int var3 = var2.length;

         for(int var4 = 0; var4 < var3; ++var4) {
            Field field = var2[var4];
            if (fieldName.equalsIgnoreCase(field.getName())) {
               return field;
            }
         }
      }

      return null;
   }

   private int getNumericValue(QueryString qs, String param, int defaultValue) {
      int value = defaultValue;
      if (qs.contains(param)) {
         try {
            String queryValue = qs.getParameter(param);
            if (queryValue != null && !"".equals(queryValue)) {
               value = Integer.valueOf(queryValue);
            }
         } catch (NumberFormatException var6) {
            return defaultValue;
         }
      }

      return value;
   }

   private String getValue(QueryString qs, String param, String defaultValue) {
      return qs.contains(param) ? qs.getParameter(param) : defaultValue;
   }

   public QueryString getQs() {
      return this.qs;
   }

   public int getPage() {
      return this.page;
   }

   public int getRows() {
      return this.rows;
   }

   public String getSidx() {
      return this.sidx;
   }

   public String getSord() {
      return this.sord;
   }

   public int getCount() {
      return this.count;
   }

   public int getTotalPages() {
      return this.totalPages;
   }

   public int getStart() {
      return this.start;
   }
}
