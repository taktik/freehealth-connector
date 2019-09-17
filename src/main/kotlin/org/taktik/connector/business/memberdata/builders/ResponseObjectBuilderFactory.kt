package org.taktik.connector.business.memberdata.builders

import org.taktik.connector.technical.exception.TechnicalConnectorException
import org.taktik.connector.technical.utils.ConfigurableFactoryHelper

object ResponseObjectBuilderFactory {
    private val PROP_RESPONSEBUILDER_CLASS = "memberdata.responseobjectbuilder.class"
    private val DEFAULT_RESPONSEBUILDER_CLASS = "be.ehealth.businessconnector.mycarenet.memberdata.builders.impl.ResponseObjectBuilderImpl"
    private val helperFactoryresponseBuilder = ConfigurableFactoryHelper("memberdata.responseobjectbuilder.class", "be.ehealth.businessconnector.mycarenet.memberdata.builders.impl.ResponseObjectBuilderImpl")

    val responseObjectBuilder: ResponseObjectBuilder
        @Throws(TechnicalConnectorException::class)
        get() = helperFactoryresponseBuilder.getImplementation()
}
