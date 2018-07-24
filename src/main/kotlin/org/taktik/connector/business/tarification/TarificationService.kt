package org.taktik.connector.business.tarification

import be.fgov.ehealth.mycarenet.commons.protocol.v2.TarificationConsultationRequest
import be.fgov.ehealth.mycarenet.commons.protocol.v2.TarificationConsultationResponse
import org.taktik.connector.technical.service.sts.security.SAMLToken

interface TarificationService {
    fun consultTarification(token: SAMLToken, request: TarificationConsultationRequest): TarificationConsultationResponse
}
