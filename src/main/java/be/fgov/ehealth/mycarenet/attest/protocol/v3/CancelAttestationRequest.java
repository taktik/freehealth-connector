package be.fgov.ehealth.mycarenet.attest.protocol.v3;

import be.fgov.ehealth.mycarenet.commons.protocol.v4.SendRequestType;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(
		name = "CancelAttestationRequest",
		namespace = "urn:be:fgov:ehealth:mycarenet:attest:protocol:v3"
)
public class CancelAttestationRequest extends SendRequestType {
	private static final long serialVersionUID = 1L;
}
