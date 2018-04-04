package be.ehealth.apb.gfddpp.services.tipsystem;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "RoutingParametersType",
   namespace = "urn:be:fgov:ehealth:gfddpp:core:v1",
   propOrder = {"tipId"}
)
public class RoutingParametersType {
   @XmlElement(
      name = "TIPId",
      required = true
   )
   protected String tipId;

   public String getTIPId() {
      return this.tipId;
   }

   public void setTIPId(String var1) {
      this.tipId = var1;
   }
}
