package be.business.connector.recipe.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import be.business.connector.core.exceptions.IntegrationModuleException;
import be.business.connector.core.utils.I18nHelper;

/**
 * The Class {@link RidValidator}.
 */
public class RidValidator {

	/** The Constant RID_PATTERN. */
	private static final String RID_PATTERN = "BE(P|K|N)(P|0|1|2|3|4|5|6|7|8|9)[(0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 | A | B | C | D | E | F | G | H | J | K | L | M | N | P | Q | R | S | T | V | W | X | Y | Z)]{8}";

	/** The Constant RID_LENGTH. */
	private static final int RID_LENGTH = 12;

	/** The Constant ridPattern. */
	private static final Pattern ridPattern = Pattern.compile(RID_PATTERN);

	/** The Constant LOG. */
	private static final Logger LOG = Logger.getLogger(RidValidator.class);

	/**
	 * Instantiates a new rid validator.
	 */
	private RidValidator() {
	}

	/**
	 * Validates a rid.
	 *
	 * @param rid
	 *            the rid
	 */
	public static void validateRid(final String rid) {
		final Matcher ridMatcher = ridPattern.matcher(rid);
		if (!ridMatcher.find() || rid.length() != RID_LENGTH) {
			LOG.error("Invalid RID was provided.");
			throw new IntegrationModuleException(I18nHelper.getLabel("error.rid.validation", new Object[] { rid }));
		}
	}

	/**
	 * Validates a rid.
	 *
	 * @param rid
	 *            the rid
	 */
	public static boolean isValidRid(final String rid) {
		try {
			validateRid(rid);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}