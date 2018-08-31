package org.taktik.connector.business.chapterIV.validators

import org.taktik.connector.business.chapterIV.exception.ChapterIVBusinessConnectorException
import org.taktik.connector.technical.exception.TechnicalConnectorException
import java.io.Serializable

interface Chapter4XmlValidator : Serializable {
    @Throws(ChapterIVBusinessConnectorException::class, TechnicalConnectorException::class)
    fun validate(xmlObject: Any?)
}
