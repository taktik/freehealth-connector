package be.fgov.ehealth.mediprima.protocol.v1;

import be.fgov.ehealth.commons.protocol.v2.StatusResponseType;
import be.fgov.ehealth.mediprima.core.v1.CarmedAttributedListType;
import be.fgov.ehealth.mediprima.core.v1.ConsultCarmedDataType;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ConsultCarmedInterventionResponseType",
   propOrder = {"selectionCriteria", "result"}
)
@XmlRootElement(
   name = "ConsultCarmedInterventionResponse"
)
public class ConsultCarmedInterventionResponse extends StatusResponseType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "SelectionCriteria",
      required = true
   )
   protected ConsultCarmedDataType selectionCriteria;
   @XmlElement(
      name = "Result"
   )
   protected CarmedAttributedListType result;

   public ConsultCarmedInterventionResponse() {
   }

   public ConsultCarmedDataType getSelectionCriteria() {
      return this.selectionCriteria;
   }

   public void setSelectionCriteria(ConsultCarmedDataType value) {
      this.selectionCriteria = value;
   }

   public CarmedAttributedListType getResult() {
      return this.result;
   }

   public void setResult(CarmedAttributedListType value) {
      this.result = value;
   }
}
