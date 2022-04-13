package be.recipe.common.wss4j.handler;

import java.util.Date;

public interface WSTimeSource {

	/**
	 * Get the current date time
	 * 
	 * @return the current date/time as a date object
	 */
	Date now();
}
