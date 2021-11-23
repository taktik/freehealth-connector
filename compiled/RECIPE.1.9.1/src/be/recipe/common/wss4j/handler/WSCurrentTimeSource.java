package be.recipe.common.wss4j.handler;

import java.util.Date;

public class WSCurrentTimeSource implements WSTimeSource {

	/**
	 * Get the current date time
	 * 
	 * @return the current date/time as a date object
	 */
	public Date now() {
		return new Date();
	}

}
