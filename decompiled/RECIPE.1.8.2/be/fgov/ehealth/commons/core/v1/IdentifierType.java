package be.fgov.ehealth.commons.core.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "IdentifierType",
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

   public void setId(String value) {
      this.id = value;
   }

   public String getType() {
      return this.type;
   }

   public void setType(String value) {
      this.type = value;
   }

   public String getSubType() {
      return this.subType;
   }

   public void setSubType(String value) {
      this.subType = value;
   }
}
