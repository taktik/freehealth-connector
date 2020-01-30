package be.ehealth.apb.gfddpp.services.pcdh;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "StatusTypeType",
   namespace = "urn:be:fgov:ehealth:gfddpp:core:v1",
   propOrder = {"type"}
)
public class StatusTypeType {
   @XmlElement(
      name = "Type",
      required = true
   )
   protected String type;

   public String getType() {
      return this.type;
   }

   public void setType(String var1) {
      this.type = var1;
   }
}
