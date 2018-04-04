package org.taktik.connector.business.recipeprojects.common.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * @author Liesje Demuynck.
 */
public class INSZUtils {

    public static boolean isValidINSZ(final String insz){
        if (insz.length() == 11 && StringUtils.isNumeric(insz)) {
            Integer checkDigit = Integer.parseInt(insz.substring(9, 11));
            String r = insz.substring(0, 9);

            Integer rest = Integer.parseInt(r) % 97;

            if (!String.valueOf(checkDigit).equals(String.valueOf(97 - rest))) {
                r = "2" + r;
                long restL = Long.parseLong(r) % 97;
                if (!String.valueOf(checkDigit).equals(String.valueOf(97 - restL))) {
                    return false;
                }
            }
        } else {
            return false;
        }
    return true;
    }
}
