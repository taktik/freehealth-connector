package be.fgov.ehealth.addressbook.core.v1;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "EHealthBoxType",
   propOrder = {"id", "type", "subType", "quality"}
)
public class EHealthBoxType implements Serializable {
   private static final long serialVersionUID = 1L;
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
   @XmlElement(
      name = "Quality",
      required = true
   )
   protected String quality;

   public EHealthBoxType() {
   }

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

   public String getQuality() {
      return this.quality;
   }

   public void setQuality(String value) {
      this.quality = value;
   }
}
