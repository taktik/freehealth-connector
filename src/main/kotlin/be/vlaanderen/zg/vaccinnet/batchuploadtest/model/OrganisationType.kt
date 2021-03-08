package be.vlaanderen.zg.vaccinnet.batchuploadtest.model

import be.fgov.ehealth.standards.kmehr.cd.v1.CDHCPARTYvalues
import java.util.*

enum class OrganisationType(val certificateType: String, val cdHcParty: CDHCPARTYvalues) {
    HOSPITAL("HOSPITAL", CDHCPARTYvalues.ORGHOSPITAL), GUARD_POST(
        "GUARD_POST",
        CDHCPARTYvalues.ORGPRACTICE
    ),
    SORTING_CENTER("SORTING_CENTER", CDHCPARTYvalues.ORGPRACTICE), OTHER("OTHER", CDHCPARTYvalues.ORGPRACTICE);

    companion object {
        operator fun get(certificateType: String?): OrganisationType {
            return Arrays.stream(values())
                .filter { organisationType: OrganisationType ->
                    organisationType.certificateType == certificateType
                }
                .findAny()
                .orElse(OTHER)
        }
    }
}
