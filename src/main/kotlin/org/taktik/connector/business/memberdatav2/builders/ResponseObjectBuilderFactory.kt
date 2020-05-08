package org.taktik.connector.business.memberdatav2.builders

import org.taktik.connector.technical.exception.TechnicalConnectorException
import org.taktik.connector.technical.utils.ConfigurableFactoryHelper

object ResponseObjectBuilderFactory {
    private val helperFactoryresponseBuilder: ConfigurableFactoryHelper<ResponseObjectBuilder> = ConfigurableFactoryHelper("memberdata.responseobjectbuilder.class", "org.taktik.connector.business.memberdatav2.builders.impl.ResponseObjectBuilderImpl")

    val responseObjectBuilder: ResponseObjectBuilder?
        @Throws(TechnicalConnectorException::class)
        get() = helperFactoryresponseBuilder.implementation
}
