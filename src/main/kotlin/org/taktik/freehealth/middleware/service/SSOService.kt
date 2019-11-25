package org.taktik.freehealth.middleware.service

import org.taktik.freehealth.middleware.domain.sts.BearerToken
import java.util.UUID

interface SSOService {
    fun getBearerToken(tokenId: UUID, keystoreId: UUID, passPhrase: String, profile: String? = null): BearerToken?
}
