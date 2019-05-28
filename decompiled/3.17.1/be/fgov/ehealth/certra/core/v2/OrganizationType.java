package be.fgov.ehealth.certra.core.v2;

import be.fgov.ehealth.commons.core.v2.Id;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "OrganizationType",
   propOrder = {"name", "identifier"}
)
public class OrganizationType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Name",
      required = true
   )
   protected TextType name;
   @XmlElement(
      name = "Identifier",
      required = true
   )
   protected Id identifier;

   public TextType getName() {
      return this.name;
   }

   public void setName(TextType value) {
      this.name = value;
   }

   public Id getIdentifier() {
      return this.identifier;
   }

   public void setIdentifier(Id value) {
      this.identifier = value;
   }
}
