package be.fgov.ehealth.hubservices.core.v2;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "TherapeuticExclusionWithOperationContext",
   propOrder = {"operationcontexts"}
)
public class TherapeuticExclusionWithOperationContext extends TherapeuticExclusionType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "operationcontext"
   )
   protected List<OperationContextType> operationcontexts;

   public TherapeuticExclusionWithOperationContext() {
   }

   public List<OperationContextType> getOperationcontexts() {
      if (this.operationcontexts == null) {
         this.operationcontexts = new ArrayList();
      }

      return this.operationcontexts;
   }
}
