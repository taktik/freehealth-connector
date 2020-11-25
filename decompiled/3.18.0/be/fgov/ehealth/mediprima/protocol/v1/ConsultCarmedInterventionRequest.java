package be.fgov.ehealth.mediprima.protocol.v1;

import be.fgov.ehealth.commons.protocol.v2.RequestType;
import be.fgov.ehealth.mediprima.core.v1.ConsultCarmedDataType;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ConsultCarmedInterventionRequestType",
   propOrder = {"selectionCriteria"}
)
@XmlRootElement(
   name = "ConsultCarmedInterventionRequest"
)
public class ConsultCarmedInterventionRequest extends RequestType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "SelectionCriteria",
      required = true
   )
   protected ConsultCarmedDataType selectionCriteria;

   public ConsultCarmedDataType getSelectionCriteria() {
      return this.selectionCriteria;
   }

   public void setSelectionCriteria(ConsultCarmedDataType value) {
      this.selectionCriteria = value;
   }
}
