package be.fgov.ehealth.rn.registries.commons.v1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ValidationErrorsType",
   propOrder = {"validationErrors"}
)
@XmlRootElement(
   name = "ValidationErrors"
)
public class ValidationErrors implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "ValidationError",
      required = true
   )
   protected List<DetailedStatusType> validationErrors;

   public ValidationErrors() {
   }

   public List<DetailedStatusType> getValidationErrors() {
      if (this.validationErrors == null) {
         this.validationErrors = new ArrayList();
      }

      return this.validationErrors;
   }
}
