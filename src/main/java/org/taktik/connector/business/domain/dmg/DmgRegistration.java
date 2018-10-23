package org.taktik.connector.business.domain.dmg;

import org.taktik.freehealth.middleware.dto.mycarenet.CommonOutput;
import org.taktik.freehealth.middleware.dto.mycarenet.MycarenetConversation;

import java.util.Date;

public class DmgRegistration extends DmgMessage {
	private CommonOutput commonOutput;
	private MycarenetConversation mycarenetConversation;
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

	public void setCommonOutput(CommonOutput commonOutput){
		this.commonOutput = commonOutput;
	}

	public CommonOutput getCommonOutput(){
		return this.commonOutput;
	}

	public void setMycarenetConversation(MycarenetConversation mycarenetConversation){
		this.mycarenetConversation = mycarenetConversation;
	}

	public MycarenetConversation getMycarenetConversation(){
		return this.mycarenetConversation;
	}
}
