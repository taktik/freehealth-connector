package be.fgov.ehealth.recipe.protocol.v1;

import be.fgov.ehealth.commons.protocol.v1.ResponseType;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "UpdatePatientFeedbackFlagResponseType"
)
@XmlRootElement(
   name = "UpdatePatientFeedbackFlagResponse"
)
public class UpdatePatientFeedbackFlagResponse extends ResponseType implements Serializable {
   private static final long serialVersionUID = 1L;
}
