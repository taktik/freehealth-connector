package be.fgov.ehealth.etee.commons.core.v2;

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
   name = "EteeStatusDetailType",
   propOrder = {"errors"}
)
@XmlRootElement(
   name = "EteeStatusDetail"
)
public class EteeStatusDetail implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Errors"
   )
   protected List<EteeErrorType> errors;

   public List<EteeErrorType> getErrors() {
      if (this.errors == null) {
         this.errors = new ArrayList();
      }

      return this.errors;
   }
}
