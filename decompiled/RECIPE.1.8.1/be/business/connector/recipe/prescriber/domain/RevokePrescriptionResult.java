package be.business.connector.recipe.prescriber.domain;

import be.recipe.services.core.ResponseType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "RevokePrescriptionResult"
)
@XmlRootElement(
   namespace = "http:/services.recipe.be/prescriber"
)
public class RevokePrescriptionResult extends ResponseType {
}
