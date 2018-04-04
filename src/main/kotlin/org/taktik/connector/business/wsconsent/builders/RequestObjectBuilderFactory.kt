package org.taktik.connector.business.wsconsent.builders

import org.taktik.connector.business.wsconsent.exception.WsConsentBusinessConnectorException
import org.taktik.connector.technical.exception.TechnicalConnectorException
import org.taktik.connector.technical.utils.ConfigurableFactoryHelper

object RequestObjectBuilderFactory {
	private val helperFactoryRequestObjectBuilder = ConfigurableFactoryHelper<RequestObjectBuilder>("org.taktik.connector.business.wsconsent.builders.RequestObjectBuilder.class", "org.taktik.connector.business.wsconsent.builders.impl.RequestObjectBuilderImpl")
	private val helperFactoryConsentBuilder = ConfigurableFactoryHelper<ConsentBuilder>("wsconsent.ConsentBuilder.class", "org.taktik.connector.business.wsconsent.builders.impl.ConsentBuilderImpl")

	val requestObjectBuilder: RequestObjectBuilder
		@Throws(TechnicalConnectorException::class, WsConsentBusinessConnectorException::class, InstantiationException::class)
		get() = helperFactoryRequestObjectBuilder.getImplementation()

	val consentBuilder: ConsentBuilder
		@Throws(TechnicalConnectorException::class, WsConsentBusinessConnectorException::class)
		get() = helperFactoryConsentBuilder.getImplementation()

}
