package be.fgov.ehealth.etkra.protocol.v2;

import be.fgov.ehealth.commons.protocol.v2.StatusResponseType;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ActivateETKResponseType"
)
@XmlRootElement(
   name = "ActivateETKResponse"
)
public class ActivateETKResponse extends StatusResponseType implements Serializable {
   private static final long serialVersionUID = 1L;

   public ActivateETKResponse() {
   }
}
