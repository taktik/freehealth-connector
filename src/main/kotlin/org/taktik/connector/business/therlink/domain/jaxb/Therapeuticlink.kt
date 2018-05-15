package org.taktik.connector.business.therlink.domain.jaxb

import be.fgov.ehealth.hubservices.core.v2.TherapeuticLinkType
import javax.xml.bind.annotation.XmlRootElement

@XmlRootElement(name = "therapeuticlink", namespace = "http://www.ehealth.fgov.be/hubservices/core/v2")
class Therapeuticlink : TherapeuticLinkType() {
    companion object {
        private val serialVersionUID = 1L
    }
}
