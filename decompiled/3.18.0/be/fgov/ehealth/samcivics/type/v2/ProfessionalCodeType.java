package be.fgov.ehealth.samcivics.type.v2;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ProfessionalCodeType",
   propOrder = {"name", "professionalName"}
)
public class ProfessionalCodeType extends BaseType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Name",
      required = true
   )
   protected String name;
   @XmlElement(
      name = "ProfessionalName"
   )
   protected String professionalName;

   public String getName() {
      return this.name;
   }

   public void setName(String value) {
      this.name = value;
   }

   public String getProfessionalName() {
      return this.professionalName;
   }

   public void setProfessionalName(String value) {
      this.professionalName = value;
   }
}
