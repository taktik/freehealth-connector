package be.recipe.services.executor;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "properties",
   propOrder = {"property"}
)
public class Properties {
   @XmlElement(
      required = true
   )
   protected List<Property> property;

   public List<Property> getProperty() {
      if (this.property == null) {
         this.property = new ArrayList();
      }

      return this.property;
   }
}
