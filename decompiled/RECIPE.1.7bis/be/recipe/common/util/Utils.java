package be.recipe.common.util;

import org.apache.commons.lang3.StringUtils;

public class Utils {
   public static String formatId(String id, int nDigits) {
      return StringUtils.isBlank(id) ? null : (new String(new char[nDigits - id.length()])).replace('\u0000', '0') + id;
   }
}
