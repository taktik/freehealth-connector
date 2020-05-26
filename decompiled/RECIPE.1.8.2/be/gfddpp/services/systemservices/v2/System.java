package be.gfddpp.services.systemservices.v2;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "system",
   propOrder = {"systemType", "systemId"}
)
public class System {
   @XmlElement(
      required = true
   )
   protected String systemType;
   @XmlElement(
      required = true
   )
   protected String systemId;

   public String getSystemType() {
      return this.systemType;
   }

   public void setSystemType(String value) {
      this.systemType = value;
   }

   public String getSystemId() {
      return this.systemId;
   }

   public void setSystemId(String value) {
      this.systemId = value;
   }
}
