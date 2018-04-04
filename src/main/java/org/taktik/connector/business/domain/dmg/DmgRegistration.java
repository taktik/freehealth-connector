package org.taktik.connector.business.domain.dmg;

import java.util.Date;

public class DmgRegistration extends DmgMessage {
	private boolean success;
	private Date date;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
