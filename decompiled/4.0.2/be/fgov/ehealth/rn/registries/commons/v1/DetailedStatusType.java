package be.fgov.ehealth.rn.registries.commons.v1;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "DetailedStatusType",
   propOrder = {"code", "description"}
)
public class DetailedStatusType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Code",
      required = true
   )
   protected String code;
   @XmlElement(
      name = "Description",
      required = true
   )
   protected String description;

   public DetailedStatusType() {
   }

   public String getCode() {
      return this.code;
   }

   public void setCode(String value) {
      this.code = value;
   }

   public String getDescription() {
      return this.description;
   }

   public void setDescription(String value) {
      this.description = value;
   }
}
