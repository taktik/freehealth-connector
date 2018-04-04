package be.ehealth.apb.gfddpp.services.pcdh;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "TherapeuticalRelationShipType",
   namespace = "urn:be:fgov:ehealth:gfddpp:core:v1",
   propOrder = {"therapeuticalRelationShipFlag"}
)
public class TherapeuticalRelationShipType {
   @XmlElement(
      name = "TherapeuticalRelationShipFlag"
   )
   protected boolean therapeuticalRelationShipFlag;

   public boolean isTherapeuticalRelationShipFlag() {
      return this.therapeuticalRelationShipFlag;
   }

   public void setTherapeuticalRelationShipFlag(boolean var1) {
      this.therapeuticalRelationShipFlag = var1;
   }
}
