package be.business.connector.projects.common.utils;

import java.math.BigInteger;
import java.text.SimpleDateFormat;

import org.apache.commons.lang3.StringUtils;

/**
 * @author Liesje Demuynck.
 */
public class INSZUtils {

	public static final BigInteger NINETY_SEVEN = new BigInteger("97");

    public static boolean isValidINSZ(final String inss){
    	if (inss == null || StringUtils.isBlank(inss) || inss.length() != 11) {
			return false;
		}
		return isNumeric(inss) && check97MinusModulo97(inss) && validDate(inss);
	}

	/**
	 * Checks if the input String is numeric.
	 * 
	 * @param s
	 *            the input String
	 * @return {@code true} if {@code s} is numeric, {@code false} otherwise
	 */
	private static boolean isNumeric(final String s) {
		return s != null && s.replaceAll("\\D", "").equals(s);
	}

	/**
	 * Check97 minus modulo97.
	 * 
	 * @param fieldValue
	 *            the field value
	 * @return true, if successful
	 */
	private static boolean check97MinusModulo97(String fieldValue) {
		boolean res = false;
		if (StringUtils.isNotBlank(fieldValue) && fieldValue.length() > 1) {
			final Integer checkValue = new Integer(fieldValue.substring(fieldValue.length() - 2));
			BigInteger fullNumber = new BigInteger(fieldValue.substring(0, fieldValue.length() - 2));
			int modulo = fullNumber.mod(NINETY_SEVEN).intValue();
			res = checkValue.intValue() == (97 - modulo);
			if (checkValue.intValue() == 97) {
				res = checkModulo97(fieldValue);
			}
			// If invalid, try by adding 2 in front of the NRN
			if (!res) {
				fieldValue = "2" + fieldValue;

				fullNumber = new BigInteger(fieldValue.substring(0, fieldValue.length() - 2));
				modulo = fullNumber.mod(NINETY_SEVEN).intValue();
				res = checkValue.intValue() == (97 - modulo);

				if (checkValue.intValue() == 97) {
					res = checkModulo97(fieldValue);
				}
			}
		}
		return res;
	}

	/**
	 * Check modulo97.
	 * 
	 * @param fieldValue
	 *            the field value
	 * @return true, if successful
	 */
	private static boolean checkModulo97(final String fieldValue) {
		boolean res = false;
		final Integer checkValue = new Integer(fieldValue.substring(fieldValue.length() - 2));
		final Integer specialCheckValue = new Integer("97");
		int isTrue;
		if (checkValue.intValue() == specialCheckValue.intValue()) {
			final BigInteger fullNumber = new BigInteger(fieldValue.substring(0, fieldValue.length() - 2));
			final Integer modulo = new Integer(fullNumber.mod(new BigInteger("97")).intValue());
			final Integer checkNull = new Integer("0");
			isTrue = checkNull.compareTo(modulo);
			if (isTrue == 0) {
				res = true;
			}
		} else {
			final BigInteger fullNumber = new BigInteger(fieldValue.substring(0, fieldValue.length() - 2));
			final Integer modulo = new Integer(fullNumber.mod(new BigInteger("97")).intValue());
			res = checkValue.intValue() == modulo.intValue();
		}
		return (res);
	}

	/**
	 * Validate the date part of the SSIN.
	 * 
	 * @param fieldValue
	 * @return
	 */
	private static boolean validDate(final String fieldValue) {
		boolean res = false;
		if (StringUtils.isNotBlank(fieldValue) && fieldValue.length() > 1) {
			final String sYear = fieldValue.substring(0, 2);
			final String sMonth = fieldValue.substring(2, 4);
			int iMonth = Integer.parseInt(sMonth);
			if (iMonth > 40) {
				iMonth -= 40;
			} else if (iMonth > 20) {
				iMonth -= 20;
			}
			String realMonth = String.format("%02d", iMonth);
			if (realMonth.equals("00")) {
				realMonth = "01";
			}
			String sDay = fieldValue.substring(4, 6);
			if (sDay.equals("00")) {
				sDay = "01";
			}
			final String yearRealMonthDay = sYear + realMonth + sDay;
			final SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
			sdf.setLenient(false);
			try {
				sdf.parse(yearRealMonthDay);
				res = true;
			}catch(final Exception e) {
				res = false;
			}
			
		}
		return res;
	}
}