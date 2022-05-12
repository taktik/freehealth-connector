package be.fgov.ehealth.ehbox.consultation.protocol.v3;

import be.fgov.ehealth.ehbox.core.v3.EhboxIdentifierType;
import be.fgov.ehealth.ehbox.core.v3.MandateType;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "DestinationContextType",
   propOrder = {"mandate"}
)
public class DestinationContextType extends EhboxIdentifierType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Mandate"
   )
   protected MandateType mandate;

   public DestinationContextType() {
   }

   public MandateType getMandate() {
      return this.mandate;
   }

   public void setMandate(MandateType value) {
      this.mandate = value;
   }
}
