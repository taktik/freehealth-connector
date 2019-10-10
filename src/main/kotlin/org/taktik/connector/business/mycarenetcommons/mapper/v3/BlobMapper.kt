package org.taktik.connector.business.mycarenetcommons.mapper.v3

import org.taktik.connector.business.mycarenetdomaincommons.domain.Blob
import be.fgov.ehealth.mycarenet.commons.core.v3.BlobType

object BlobMapper {
    fun mapBlobTypefromBlob(blob: Blob): BlobType {
       return BlobType().apply {
            value = blob.content
            id = blob.id
            contentEncoding = blob.contentEncoding
            hashValue = blob.hashValue
            contentType = blob.contentType
            contentEncryption = blob.contentEncryption
        }
    }

    fun mapBlobfromBlobType(blob: BlobType): Blob {
        return Blob().apply {
            content = blob.value
            id = blob.id
            contentEncoding = blob.contentEncoding
            contentEncryption = blob.contentEncryption
            hashValue = blob.hashValue
            contentType = blob.contentType
        }
    }
}
