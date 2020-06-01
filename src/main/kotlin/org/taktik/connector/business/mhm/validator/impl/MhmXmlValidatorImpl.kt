package org.taktik.connector.business.mhm.validator.impl

import be.fgov.ehealth.mycarenet.commons.core.v3.BlobType
import be.fgov.ehealth.mycarenet.commons.core.v3.CommonInputType
import be.fgov.ehealth.mycarenet.commons.core.v3.OriginType
import be.fgov.ehealth.mycarenet.commons.core.v3.RequestType
import be.fgov.ehealth.mycarenet.commons.core.v3.RoutingType
import be.fgov.ehealth.mycarenet.commons.protocol.v3.SendRequestType
import be.fgov.ehealth.mycarenet.commons.protocol.v3.SendResponseType
import be.fgov.ehealth.mycarenet.mhm.protocol.v1.CancelSubscriptionRequest
import be.fgov.ehealth.mycarenet.mhm.protocol.v1.CancelSubscriptionResponse
import be.fgov.ehealth.mycarenet.mhm.protocol.v1.NotifySubscriptionClosureRequest
import be.fgov.ehealth.mycarenet.mhm.protocol.v1.NotifySubscriptionClosureResponse
import be.fgov.ehealth.mycarenet.mhm.protocol.v1.SendSubscriptionRequest
import be.fgov.ehealth.mycarenet.mhm.protocol.v1.SendSubscriptionResponse
import org.taktik.connector.technical.validator.impl.XMLValidatorImpl

class MhmXmlValidatorImpl : XMLValidatorImpl(){
    companion object {
        private val COMMONS_CORE_XSD = "/ehealth-mycarenetcommons/XSD/mycarenet-commons-core-3_0.xsd"
        private val COMMONS_PROTOCOL_XSD = "/ehealth-mycarenetcommons/XSD/mycarenet-commons-protocol-3_0.xsd"
        private val MEMBERDATASYNC_PROTOCOL_XSD = "/ehealth-mycarenet-medicalhousemembership/XSD/mycarenet-medical-house-membership-protocol-1_0.xsd"

        init {
            XMLValidatorImpl.XSD_FILE_LOCATION_FOR_CLASS_MAP[SendRequestType::class.java] = "/ehealth-mycarenetcommons/XSD/mycarenet-commons-protocol-3_0.xsd"
            XMLValidatorImpl.XSD_FILE_LOCATION_FOR_CLASS_MAP[SendSubscriptionRequest::class.java] = "/ehealth-mycarenet-medicalhousemembership/XSD/mycarenet-medical-house-membership-protocol-1_0.xsd"
            XMLValidatorImpl.XSD_FILE_LOCATION_FOR_CLASS_MAP[SendSubscriptionResponse::class.java] = "/ehealth-mycarenet-medicalhousemembership/XSD/mycarenet-medical-house-membership-protocol-1_0.xsd"
            XMLValidatorImpl.XSD_FILE_LOCATION_FOR_CLASS_MAP[CancelSubscriptionRequest::class.java] = "/ehealth-mycarenet-medicalhousemembership/XSD/mycarenet-medical-house-membership-protocol-1_0.xsd"
            XMLValidatorImpl.XSD_FILE_LOCATION_FOR_CLASS_MAP[CancelSubscriptionResponse::class.java] = "/ehealth-mycarenet-medicalhousemembership/XSD/mycarenet-medical-house-membership-protocol-1_0.xsd"
            XMLValidatorImpl.XSD_FILE_LOCATION_FOR_CLASS_MAP[NotifySubscriptionClosureRequest::class.java] = "/ehealth-mycarenet-medicalhousemembership/XSD/mycarenet-medical-house-membership-protocol-1_0.xsd"
            XMLValidatorImpl.XSD_FILE_LOCATION_FOR_CLASS_MAP[NotifySubscriptionClosureResponse::class.java] = "/ehealth-mycarenet-medicalhousemembership/XSD/mycarenet-medical-house-membership-protocol-1_0.xsd"
            XMLValidatorImpl.XSD_FILE_LOCATION_FOR_CLASS_MAP[SendResponseType::class.java] = "/ehealth-mycarenetcommons/XSD/mycarenet-commons-protocol-3_0.xsd"
            XMLValidatorImpl.XSD_FILE_LOCATION_FOR_CLASS_MAP[BlobType::class.java] = "/ehealth-mycarenetcommons/XSD/mycarenet-commons-core-3_0.xsd"
            XMLValidatorImpl.XSD_FILE_LOCATION_FOR_CLASS_MAP[CommonInputType::class.java] = "/ehealth-mycarenetcommons/XSD/mycarenet-commons-core-3_0.xsd"
            XMLValidatorImpl.XSD_FILE_LOCATION_FOR_CLASS_MAP[RequestType::class.java] = "/ehealth-mycarenetcommons/XSD/mycarenet-commons-core-3_0.xsd"
            XMLValidatorImpl.XSD_FILE_LOCATION_FOR_CLASS_MAP[RoutingType::class.java] = "/ehealth-mycarenetcommons/XSD/mycarenet-commons-core-3_0.xsd"
            XMLValidatorImpl.XSD_FILE_LOCATION_FOR_CLASS_MAP[OriginType::class.java] = "/ehealth-mycarenetcommons/XSD/mycarenet-commons-core-3_0.xsd"
        }
    }
}
