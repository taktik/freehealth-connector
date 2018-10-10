package org.taktik.freehealth.middleware.dto.ehbox

import java.util.UUID

class AltKeystoresList {
    var keystores : List<AltKeystore> = listOf()
}

class AltKeystore {
    var uuid : UUID? = null
    var passPhrase: String? = null
}
