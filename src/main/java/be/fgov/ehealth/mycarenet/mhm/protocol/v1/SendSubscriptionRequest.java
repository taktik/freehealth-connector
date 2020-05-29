package be.fgov.ehealth.mycarenet.mhm.protocol.v1;

import be.fgov.ehealth.mycarenet.commons.protocol.v3.SendRequestType;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(
		name = "SendSubscriptionRequest",
		namespace = "urn:be:fgov:ehealth:mycarenet:medicalhousemembership:protocol:v1"
)
public class SendSubscriptionRequest extends SendRequestType {
	private static final long serialVersionUID = 1L;
}
