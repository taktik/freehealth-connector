package org.taktik.connector.business.mediprima.validator

import org.taktik.connector.technical.exception.TechnicalConnectorException
import org.taktik.connector.technical.validator.ValidatorHelper.Companion.validate

object XmlValidator {
    private const val MEDIPRIMA_XSD = "/ehealth-mediprima/XSD/ehealth-mediprima-protocol-1_0.xsd"
    @Throws(TechnicalConnectorException::class)
    fun validateXml(xmlObject: Any) = validate(xmlObject, "/ehealth-mediprima/XSD/ehealth-mediprima-protocol-1_0.xsd")
}
