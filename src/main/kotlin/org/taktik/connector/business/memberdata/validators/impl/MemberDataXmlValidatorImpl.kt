package org.taktik.connector.business.memberdata.validators.impl

import org.taktik.connector.technical.validator.impl.XMLValidatorImpl
import be.fgov.ehealth.mycarenet.commons.core.v3.BlobType
import be.fgov.ehealth.mycarenet.commons.core.v3.CommonInputType
import be.fgov.ehealth.mycarenet.commons.core.v3.OriginType
import be.fgov.ehealth.mycarenet.commons.core.v3.RequestType
import be.fgov.ehealth.mycarenet.commons.core.v3.RoutingType
import be.fgov.ehealth.mycarenet.commons.protocol.v3.SendRequestType
import be.fgov.ehealth.mycarenet.commons.protocol.v3.SendResponseType
import be.fgov.ehealth.mycarenet.memberdata.protocol.v1.MemberDataConsultationRequest
import be.fgov.ehealth.mycarenet.memberdata.protocol.v1.MemberDataConsultationResponse

class MemberDataXmlValidatorImpl : XMLValidatorImpl() {
    companion object {
        private val COMMONS_CORE_XSD = "/ehealth-mycarenetcommons/XSD/mycarenet-commons-core-3_0.xsd"
        private val COMMONS_PROTOCOL_XSD = "/ehealth-mycarenetcommons/XSD/mycarenet-commons-protocol-3_0.xsd"
        private val MEMBERDATASYNC_PROTOCOL_XSD = "/ehealth-mycarenet-memberdata/XSD/mycarenet-memberdata-protocol-1_0.xsd"

        init {
            XSD_FILE_LOCATION_FOR_CLASS_MAP[SendRequestType::class.java] = "/ehealth-mycarenetcommons/XSD/mycarenet-commons-protocol-3_0.xsd"
            XSD_FILE_LOCATION_FOR_CLASS_MAP[MemberDataConsultationRequest::class.java] = "/ehealth-mycarenet-memberdata/XSD/mycarenet-memberdata-protocol-1_0.xsd"
            XSD_FILE_LOCATION_FOR_CLASS_MAP[MemberDataConsultationResponse::class.java] = "/ehealth-mycarenet-memberdata/XSD/mycarenet-memberdata-protocol-1_0.xsd"
            XSD_FILE_LOCATION_FOR_CLASS_MAP[SendResponseType::class.java] = "/ehealth-mycarenetcommons/XSD/mycarenet-commons-protocol-3_0.xsd"
            XSD_FILE_LOCATION_FOR_CLASS_MAP[BlobType::class.java] = "/ehealth-mycarenetcommons/XSD/mycarenet-commons-core-3_0.xsd"
            XSD_FILE_LOCATION_FOR_CLASS_MAP[CommonInputType::class.java] = "/ehealth-mycarenetcommons/XSD/mycarenet-commons-core-3_0.xsd"
            XSD_FILE_LOCATION_FOR_CLASS_MAP[RequestType::class.java] = "/ehealth-mycarenetcommons/XSD/mycarenet-commons-core-3_0.xsd"
            XSD_FILE_LOCATION_FOR_CLASS_MAP[RoutingType::class.java] = "/ehealth-mycarenetcommons/XSD/mycarenet-commons-core-3_0.xsd"
            XSD_FILE_LOCATION_FOR_CLASS_MAP[OriginType::class.java] = "/ehealth-mycarenetcommons/XSD/mycarenet-commons-core-3_0.xsd"
        }
    }
}
