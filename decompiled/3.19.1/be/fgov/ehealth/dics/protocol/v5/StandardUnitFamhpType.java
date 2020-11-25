package be.fgov.ehealth.dics.protocol.v5;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "StandardUnitFamhpType",
   propOrder = {"description"}
)
public class StandardUnitFamhpType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Description"
   )
   protected ConsultTextType description;
   @XmlAttribute(
      name = "Name",
      required = true
   )
   protected String name;

   public ConsultTextType getDescription() {
      return this.description;
   }

   public void setDescription(ConsultTextType value) {
      this.description = value;
   }

   public String getName() {
      return this.name;
   }

   public void setName(String value) {
      this.name = value;
   }
}
