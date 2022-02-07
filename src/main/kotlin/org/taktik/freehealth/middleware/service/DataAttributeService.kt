package org.taktik.freehealth.middleware.service

import oasis.names.tc.saml._2_0.protocol.Response
import java.util.*

interface DataAttributeService {
 fun getDinRoutingInfo(keystoreId: UUID, tokenId: UUID, passPhrase: String, nihii: String, patientSsin: String, patientDateOfBirth: Int, cause: String, from: Long, to: Long, total: Boolean, prolongation: Boolean) : Response
}
