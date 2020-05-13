package be.apb.standards.smoa.schema.model.v1;

import be.apb.standards.smoa.schema.id.v1.AbstractSubstanceProductIdType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "SubstanceProductType",
   propOrder = {"id", "substance", "substancePackaging"}
)
public class SubstanceProductType extends AbstractSubstanceProductType {
   protected AbstractSubstanceProductIdType id;
   @XmlElement(
      required = true
   )
   protected String substance;
   @XmlElement(
      required = true
   )
   protected String substancePackaging;

   public AbstractSubstanceProductIdType getId() {
      return this.id;
   }

   public void setId(AbstractSubstanceProductIdType value) {
      this.id = value;
   }

   public String getSubstance() {
      return this.substance;
   }

   public void setSubstance(String value) {
      this.substance = value;
   }

   public String getSubstancePackaging() {
      return this.substancePackaging;
   }

   public void setSubstancePackaging(String value) {
      this.substancePackaging = value;
   }
}
