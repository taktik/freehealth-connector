package be.apb.gfddpp.common.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class QueryString {
   private Map<String, List<String>> parameters;
   private static final Logger LOG = LoggerFactory.getLogger(QueryString.class);

   public QueryString(String qs) {
      LOG.debug("QueryString: [" + qs + "]");
      this.parameters = new TreeMap();
      if (qs != null) {
         String[] pairs = qs.split("&");
         String[] arr$ = pairs;
         int len$ = pairs.length;

         for(int i$ = 0; i$ < len$; ++i$) {
            String pair = arr$[i$];
            int pos = pair.indexOf(61);
            String name;
            String value;
            if (pos == -1) {
               name = pair;
               value = null;
            } else {
               try {
                  name = URLDecoder.decode(pair.substring(0, pos), "UTF-8");
                  value = URLDecoder.decode(pair.substring(pos + 1, pair.length()), "UTF-8");
               } catch (UnsupportedEncodingException var11) {
                  throw new IllegalStateException("No UTF-8");
               }
            }

            if (name != null && !"".equals(name) && value != null && !"".equals(value)) {
               List<String> list = this.parameters.get(name.toLowerCase());
               if (list == null) {
                  list = new ArrayList();
                  this.parameters.put(name.toLowerCase(), list);
               }

               list.add(value);
               LOG.debug("QueryString parsed:  name=[" + name + "] - value=[" + value + "]");
            }
         }
      }

   }

   public QueryString(String key, String value) {
      this("&" + key + "=" + value);
   }

   public String getParameter(String name) {
      if (name != null) {
         if (!this.parameters.containsKey(name)) {
            return null;
         } else {
            List<String> values = this.parameters.get(name.toLowerCase());
            if (values == null) {
               return null;
            } else {
               return values.size() == 0 ? "" : values.get(0);
            }
         }
      } else {
         return null;
      }
   }

   public String[] getParameterValues(String name) {
      if (name != null) {
         List<String> values = this.parameters.get(name.toLowerCase());
         return values == null ? null : values.toArray(new String[values.size()]);
      } else {
         return null;
      }
   }

   public Enumeration<String> getParameterNames() {
      return Collections.enumeration(this.parameters.keySet());
   }

   public Map<String, String[]> getParameterMap() {
      Map<String, String[]> map = new TreeMap<>();

      Entry<String,List<String>> entry;
      String[] values;
      for (Entry<String, List<String>> stringListEntry : this.parameters.entrySet()) {
         entry = stringListEntry;
         List<String> list = entry.getValue();
         if (list == null) {
            values = null;
         } else {
            values = list.toArray(new String[list.size()]);
         }
         map.put(entry.getKey(), values);
      }

      return map;
   }

   public boolean contains(String parameter) {
      return parameter != null && this.parameters.containsKey(parameter.toLowerCase());
   }
}
