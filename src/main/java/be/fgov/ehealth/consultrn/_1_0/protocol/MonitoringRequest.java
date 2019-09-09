package be.fgov.ehealth.consultrn._1_0.protocol;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "MonitoringRequestType"
)
@XmlRootElement(
   name = "MonitoringRequest"
)
public class MonitoringRequest extends ConsultRnRequestType implements Serializable {
   private static final long serialVersionUID = 1L;
}
