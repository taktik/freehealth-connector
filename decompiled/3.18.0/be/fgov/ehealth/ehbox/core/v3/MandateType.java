package be.fgov.ehealth.ehbox.core.v3;

import be.fgov.ehealth.commons.core.v1.IdentifierType;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "MandateType",
   propOrder = {"name"}
)
public class MandateType extends IdentifierType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Name"
   )
   protected String name;

   public String getName() {
      return this.name;
   }

   public void setName(String value) {
      this.name = value;
   }
}
