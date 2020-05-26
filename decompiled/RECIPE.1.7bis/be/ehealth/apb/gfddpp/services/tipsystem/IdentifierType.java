package be.ehealth.apb.gfddpp.services.tipsystem;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "IdentifierType",
   namespace = "urn:be:fgov:ehealth:commons:core:v1",
   propOrder = {"id", "type", "subType"}
)
public class IdentifierType {
   @XmlElement(
      name = "Id",
      required = true
   )
   protected String id;
   @XmlElement(
      name = "Type",
      required = true
   )
   protected String type;
   @XmlElement(
      name = "SubType"
   )
   protected String subType;

   public String getId() {
      return this.id;
   }

   public void setId(String var1) {
      this.id = var1;
   }

   public String getType() {
      return this.type;
   }

   public void setType(String var1) {
      this.type = var1;
   }

   public String getSubType() {
      return this.subType;
   }

   public void setSubType(String var1) {
      this.subType = var1;
   }
}
