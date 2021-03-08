package be.vlaanderen.zg.vaccinnet.batchuploadtest.model

import javax.xml.bind.annotation.XmlRootElement

@XmlRootElement(name = "organisation")
class Organisation(val riziv: String? = null, val name: String? = null,
                   val userCode: String? = null, val certificateFileName: String? = null,
                   val certificatePassword: String? = null
) {
    val organisationType: OrganisationType
        get() {
            val nihii = "NIHII-"
            return OrganisationType.Companion.get(
                certificateFileName!!.substring(
                    if (certificateFileName.startsWith(
                            nihii
                        )
                    ) nihii.length else 0, certificateFileName.indexOf("=")
                )
            )
        }
}
