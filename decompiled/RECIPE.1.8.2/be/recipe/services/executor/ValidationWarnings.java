package be.recipe.services.executor;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "validationWarnings",
   propOrder = {"validationWarning"}
)
public class ValidationWarnings {
   @XmlElement(
      required = true
   )
   protected List<ValidationWarning> validationWarning;

   public List<ValidationWarning> getValidationWarning() {
      if (this.validationWarning == null) {
         this.validationWarning = new ArrayList();
      }

      return this.validationWarning;
   }
}
