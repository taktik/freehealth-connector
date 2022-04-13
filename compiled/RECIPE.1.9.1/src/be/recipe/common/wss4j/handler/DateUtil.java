package be.recipe.common.wss4j.handler;


import java.time.Instant;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class DateUtil {
	private static final Logger LOG = LoggerFactory.getLogger(DateUtil.class);
	private static final DateTimeFormatter MILLISECOND_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
	private static final DateTimeFormatter SECOND_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");

	public static boolean verifyCreated(Instant created, int timeToLive, int futureTimeToLive) {
		if (created == null) {
			return true;
		}
		Instant validCreation = Instant.now();
		if (futureTimeToLive > 0) {
			validCreation = validCreation.plusSeconds(futureTimeToLive);
		}
		if (created.isAfter(validCreation)) {
			LOG.debug("Validation of Created: The message was created in the future!");
			return false;
		}
		validCreation = Instant.now().minusSeconds(timeToLive);
		if (created.isBefore(validCreation)) {
			LOG.debug("Validation of Created: The message was created too long ago");
			return false;
		}
		LOG.debug("Validation of Created: Everything is ok");
		return true;
	}

	public static DateTimeFormatter getDateTimeFormatter(boolean milliseconds) {
		if (milliseconds) {
			return MILLISECOND_FORMATTER;
		}
		return SECOND_FORMATTER;
	}
}
