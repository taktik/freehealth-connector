package be.fgov.ehealth.mycarenet.mhm.protocol.v1;

import be.fgov.ehealth.mycarenet.commons.protocol.v3.SendRequestType;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(
        name = "CancelSubscriptionRequest",
        namespace = "urn:be:fgov:ehealth:mycarenet:medicalhousemembership:protocol:v1"
)
public class CancelSubscriptionRequest extends SendRequestType {
    private static final long serialVersionUID = 1L;
}
