package be.recipe.services.prescriber;

import be.recipe.services.core.ResponseType;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "sendNotificationResult"
)
@XmlRootElement(
   name = "sendNotificationResult"
)
public class SendNotificationResult extends ResponseType {

}
