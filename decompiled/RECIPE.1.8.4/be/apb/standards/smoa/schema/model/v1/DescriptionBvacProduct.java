package be.apb.standards.smoa.schema.model.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "DescriptionBvacProduct",
   propOrder = {"fullDescription"}
)
public class DescriptionBvacProduct {
   @XmlElement(
      required = true
   )
   protected String fullDescription;

   public String getFullDescription() {
      return this.fullDescription;
   }

   public void setFullDescription(String value) {
      this.fullDescription = value;
   }
}
