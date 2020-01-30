package be.apb.standards.smoa.schema.model.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "Identity",
   propOrder = {"name"}
)
public class Identity {
   @XmlElement(
      required = true
   )
   protected String name;

   public String getName() {
      return this.name;
   }

   public void setName(String value) {
      this.name = value;
   }
}
