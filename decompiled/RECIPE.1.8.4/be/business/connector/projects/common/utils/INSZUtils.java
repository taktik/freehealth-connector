package be.business.connector.projects.common.utils;

import org.apache.commons.lang3.StringUtils;

public class INSZUtils {
   public static boolean isValidINSZ(String insz) {
      if (insz != null && (insz == null || !insz.equals(""))) {
         if (insz.length() == 11 && StringUtils.isNumeric(insz)) {
            Integer checkDigit = Integer.parseInt(insz.substring(9, 11));
            String r = insz.substring(0, 9);
            Integer rest = Integer.parseInt(r) % 97;
            if (!String.valueOf(checkDigit).equals(String.valueOf(97 - rest))) {
               r = "2" + r;
               long restL = Long.parseLong(r) % 97L;
               if (!String.valueOf(checkDigit).equals(String.valueOf(97L - restL))) {
                  return false;
               }
            }

            return true;
         } else {
            return false;
         }
      } else {
         return false;
      }
   }
}
