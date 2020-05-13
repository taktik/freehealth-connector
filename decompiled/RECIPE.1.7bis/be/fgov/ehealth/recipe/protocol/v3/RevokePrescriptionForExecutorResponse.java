package be.fgov.ehealth.recipe.protocol.v3;

import be.fgov.ehealth.commons.protocol.v1.ResponseType;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "RevokePrescriptionForExecutorResponseType"
)
@XmlRootElement(
   name = "RevokePrescriptionForExecutorResponse"
)
public class RevokePrescriptionForExecutorResponse extends ResponseType implements Serializable {
   private static final long serialVersionUID = 1L;
}
