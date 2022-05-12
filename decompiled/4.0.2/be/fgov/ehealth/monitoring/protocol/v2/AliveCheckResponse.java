package be.fgov.ehealth.monitoring.protocol.v2;

import be.fgov.ehealth.commons.protocol.v2.StatusResponseType;
import be.fgov.ehealth.monitoring.core.v2.MonitoringXML;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "AliveCheckResponseType",
   propOrder = {"monitoringXML"}
)
@XmlRootElement(
   name = "AliveCheckResponse"
)
public class AliveCheckResponse extends StatusResponseType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "MonitoringXML",
      required = true
   )
   protected MonitoringXML monitoringXML;

   public AliveCheckResponse() {
   }

   public MonitoringXML getMonitoringXML() {
      return this.monitoringXML;
   }

   public void setMonitoringXML(MonitoringXML value) {
      this.monitoringXML = value;
   }
}
