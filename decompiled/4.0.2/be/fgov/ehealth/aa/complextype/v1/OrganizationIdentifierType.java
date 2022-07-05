package be.fgov.ehealth.aa.complextype.v1;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "OrganizationIdentifierType",
   propOrder = {"type", "subType"}
)
public class OrganizationIdentifierType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Type",
      required = true
   )
   protected Object type;
   @XmlElement(
      name = "SubType"
   )
   protected String subType;

   public OrganizationIdentifierType() {
   }

   public Object getType() {
      return this.type;
   }

   public void setType(Object value) {
      this.type = value;
   }

   public String getSubType() {
      return this.subType;
   }

   public void setSubType(String value) {
      this.subType = value;
   }
}
