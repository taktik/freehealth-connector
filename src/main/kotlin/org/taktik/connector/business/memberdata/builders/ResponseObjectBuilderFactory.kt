package org.taktik.connector.business.memberdata.builders

import org.taktik.connector.technical.exception.TechnicalConnectorException
import org.taktik.connector.technical.utils.ConfigurableFactoryHelper

object ResponseObjectBuilderFactory {
    private val helperFactoryresponseBuilder = ConfigurableFactoryHelper<ResponseObjectBuilder>("memberdata.responseobjectbuilder.class", "org.taktik.connector.business.memberdata.builders.impl.ResponseObjectBuilderImpl")

    val responseObjectBuilder: ResponseObjectBuilder
        @Throws(TechnicalConnectorException::class)
        get() = helperFactoryresponseBuilder.getImplementation()
}
