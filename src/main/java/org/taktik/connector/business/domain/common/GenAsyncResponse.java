package org.taktik.connector.business.domain.common;

import be.cin.nip.async.generic.TAck;
import org.jetbrains.annotations.Nullable;
import org.taktik.freehealth.middleware.dto.mycarenet.CommonOutput;
import org.taktik.freehealth.middleware.dto.mycarenet.MycarenetConversation;

public class GenAsyncResponse {
	Boolean result;

	private CommonOutput commonOutput;
	private MycarenetConversation mycarenetConversation;
	private TAck tack;

	public Boolean getResult() {
		return result;
	}

	public void setResult(Boolean result) {
		this.result = result;
	}

	public CommonOutput getCommonOutput() {
		return commonOutput;
	}

	public void setCommonOutput(CommonOutput commonOutput) {
		this.commonOutput = commonOutput;
	}

	public MycarenetConversation getMycarenetConversation() {
		return mycarenetConversation;
	}

	public void setMycarenetConversation(MycarenetConversation mycarenetConversation) {
		this.mycarenetConversation = mycarenetConversation;
	}

	public TAck getTack() {
		return tack;
	}

	public void setTack(TAck tack) {
		this.tack = tack;
	}
}
