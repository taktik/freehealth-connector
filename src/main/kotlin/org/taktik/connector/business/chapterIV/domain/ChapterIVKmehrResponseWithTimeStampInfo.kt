package org.taktik.connector.business.chapterIV.domain

import be.fgov.ehealth.medicalagreement.core.v1.Kmehrresponse
import org.apache.commons.lang.ArrayUtils
import org.bouncycastle.util.Arrays
import org.taktik.connector.technical.utils.MarshallerHelper
import java.io.Serializable

class ChapterIVKmehrResponseWithTimeStampInfo(kmehrResponseBytes: ByteArray) : Serializable {
    private val kmehrResponseBytes: ByteArray = ArrayUtils.clone(kmehrResponseBytes)

    fun getKmehrResponseBytes(): ByteArray {
        return Arrays.clone(this.kmehrResponseBytes)
    }

    val kmehrresponse: Kmehrresponse
        get() {
            val helper = MarshallerHelper(Kmehrresponse::class.java, Kmehrresponse::class.java)
            return helper.toObject(this.kmehrResponseBytes)
        }
}
