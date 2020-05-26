package be.fgov.ehealth.recipe.protocol.v2;

import be.fgov.ehealth.commons.protocol.v1.ResponseType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "MarkAsUnDeliveredResponseType"
)
@XmlRootElement(
   name = "MarkAsUnDeliveredResponse"
)
public class MarkAsUnDeliveredResponse extends ResponseType {
}
