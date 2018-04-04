/*
 *
 * Copyright (C) 2018 Taktik SA
 *
 * This file is part of FreeHealthConnector.
 *
 * FreeHealthConnector is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation.
 *
 * FreeHealthConnector is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with FreeHealthConnector.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

package org.taktik.connector.business.ehbox.api.domain

import org.taktik.connector.business.ehbox.api.domain.exception.EhboxBusinessConnectorException
import org.taktik.connector.business.ehbox.api.domain.exception.EhboxBusinessConnectorExceptionValues
import org.taktik.connector.business.ehbox.api.utils.SigningValue
import org.taktik.connector.technical.exception.TechnicalConnectorException
import org.taktik.connector.technical.exception.UnsealConnectorException
import org.taktik.connector.technical.utils.ConnectorIOUtils
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import java.io.Serializable
import java.text.MessageFormat
import org.apache.commons.lang.ArrayUtils
import org.bouncycastle.util.Arrays
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class Document : Serializable {
    var title: String? = null
    private var content: ByteArray? = null
    var filename: String? = null
    var mimeType: String? = null
    @Transient
    var signing: SigningValue? = null
    private var expection: UnsealConnectorException? = null

    @Throws(EhboxBusinessConnectorException::class)
    fun getDocument(fullpath: String) {
        var file = File(fullpath)
        if (file.isDirectory) {
            file = File(fullpath + System.getProperty("file.separator") + this.filename)
        }

        var fos: FileOutputStream? = null

        try {
            fos = FileOutputStream(file)
            fos.write(this.content!!)
            fos.flush()
        } catch (var8: IOException) {
            LOG.debug("\t## " + MessageFormat.format(EhboxBusinessConnectorExceptionValues.ERROR_EHBOX_DOCUMENT_OUTPUTSTREAM.message, file.toURI()))
            throw EhboxBusinessConnectorException(EhboxBusinessConnectorExceptionValues.ERROR_EHBOX_DOCUMENT_OUTPUTSTREAM, var8, *arrayOf<Any>(file.toURI()))
        } finally {
            ConnectorIOUtils.closeQuietly(fos as Any?)
        }

    }

    @Throws(UnsealConnectorException::class)
    fun getContent(): ByteArray {
        return if (this.content == null && this.expection != null) {
            throw this.expection!!
        } else {
            Arrays.clone(this.content)
        }
    }

    fun setContent(content: ByteArray?) {
        this.content = content?.let { Arrays.clone(it) }
    }


    @Throws(TechnicalConnectorException::class)
    fun setContent(inputStream: InputStream) {
        this.content = ConnectorIOUtils.getBytes(inputStream)
    }

    fun setException(expection: UnsealConnectorException) {
        this.expection = expection
    }

    companion object {
        private val LOG = LoggerFactory.getLogger(Document::class.java)
    }
}
