package be.fgov.ehealth.monitoring.core.v2;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "MonitoringResponseType",
   propOrder = {"monitoring"}
)
@XmlRootElement(
   name = "MonitoringXML",
   namespace = ""
)
public class MonitoringXML implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Monitoring",
      required = true
   )
   protected MonitoringType monitoring;

   public MonitoringXML() {
   }

   public MonitoringType getMonitoring() {
      return this.monitoring;
   }

   public void setMonitoring(MonitoringType value) {
      this.monitoring = value;
   }
}
