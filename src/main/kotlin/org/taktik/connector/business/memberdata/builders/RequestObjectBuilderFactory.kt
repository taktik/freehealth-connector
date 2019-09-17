package org.taktik.connector.business.memberdata.builders

import org.taktik.connector.technical.exception.TechnicalConnectorException
import org.taktik.connector.technical.utils.ConfigurableFactoryHelper

object RequestObjectBuilderFactory {
    private val PROP_ENCRYPTEDREQUESTBUILDER_CLASS = "memberdata.encryptedrequestobjectbuilder.class"
    private val PROP_NOTENCRYPTEDREQUESTBUILDER_CLASS = "memberdata.notencryptedrequestobjectbuilder.class"
    private val DEFAULT_ENCRYPTEDREQUESTBUILDER_CLASS = "be.ehealth.businessconnector.mycarenet.memberdata.builders.impl.EncryptedRequestObjectBuilderImpl"
    private val DEFAULT_NOTENCRYPTEDREQUESTBUILDER_CLASS = "be.ehealth.businessconnector.mycarenet.memberdata.builders.impl.NotEncryptedRequestObjectBuilderImpl"
    private val helperFactoryERequestBuilder = ConfigurableFactoryHelper("memberdata.encryptedrequestobjectbuilder.class", "be.ehealth.businessconnector.mycarenet.memberdata.builders.impl.EncryptedRequestObjectBuilderImpl")
    private val helperFactoryNERequestBuilder = ConfigurableFactoryHelper("memberdata.notencryptedrequestobjectbuilder.class", "be.ehealth.businessconnector.mycarenet.memberdata.builders.impl.NotEncryptedRequestObjectBuilderImpl")

    val encryptedRequestObjectBuilder: RequestObjectBuilder
        @Throws(TechnicalConnectorException::class)
        get() = helperFactoryERequestBuilder.getImplementation()

    val notEncryptedRequestObjectBuilder: RequestObjectBuilder
        @Throws(TechnicalConnectorException::class)
        get() = helperFactoryNERequestBuilder.getImplementation()
}
