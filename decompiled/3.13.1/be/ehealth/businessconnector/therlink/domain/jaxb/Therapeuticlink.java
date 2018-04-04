package be.ehealth.businessconnector.therlink.domain.jaxb;

import be.fgov.ehealth.hubservices.core.v2.TherapeuticLinkType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(
   name = "therapeuticlink",
   namespace = "http://www.ehealth.fgov.be/hubservices/core/v2"
)
public class Therapeuticlink extends TherapeuticLinkType {
   private static final long serialVersionUID = 1L;
}
