package be.fgov.ehealth.mediprima.uma.core.v1;

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
   name = "RegistryStatusType",
   propOrder = {"errorDetails"}
)
@XmlRootElement(
   name = "RegistryStatus"
)
public class RegistryStatus implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "ErrorDetail",
      required = true
   )
   protected List<ErrorType> errorDetails;

   public List<ErrorType> getErrorDetails() {
      if (this.errorDetails == null) {
         this.errorDetails = new ArrayList();
      }

      return this.errorDetails;
   }
}
