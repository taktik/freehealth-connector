package be.fgov.ehealth.recipe.protocol.v3;

import be.fgov.ehealth.commons.protocol.v1.ResponseType;
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
public class UpdatePatientFeedbackFlagResponse extends ResponseType {
}
