package be.fgov.ehealth.monitoring.protocol.v2;

import be.fgov.ehealth.commons.protocol.v2.RequestType;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "AliveCheckRequestType"
)
@XmlRootElement(
   name = "AliveCheckRequest"
)
public class AliveCheckRequest extends RequestType implements Serializable {
   private static final long serialVersionUID = 1L;
}
