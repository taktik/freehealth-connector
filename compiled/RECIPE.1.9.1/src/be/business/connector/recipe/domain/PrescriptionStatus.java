package be.business.connector.recipe.domain;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum PrescriptionStatus {

	/** The Open. */
	NotDelivered,
	/** The In progress. */
	InProcess,
	/** The Delivered. */
	Delivered,
	/** The Revoked. */
	Revoked,
	/** The Expired. */
	Expired,
	/** The Archived. */
	Archived;

	private static final Map<Integer, PrescriptionStatus> lookup = new HashMap<>();

	static {
		for (PrescriptionStatus s : EnumSet.allOf(PrescriptionStatus.class)) {
			lookup.put(s.ordinal(), s);
		}
	}

	public static PrescriptionStatus get(int code) {
		return lookup.get(code);
	}

}
