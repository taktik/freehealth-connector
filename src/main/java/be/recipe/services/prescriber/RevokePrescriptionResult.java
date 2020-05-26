package be.recipe.services.prescriber;

import be.recipe.services.core.ResponseType;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "revokePrescriptionResult"
)
@XmlRootElement(
   name = "revokePrescriptionResult"
)
public class RevokePrescriptionResult extends ResponseType {

}
