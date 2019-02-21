package org.taktik.freehealth.middleware.dto.efact

import java.util.ArrayList
import java.util.HashMap

class FlatFileWithMetadata {
    var metadata : FlatFileMetadata? = null
    var flatFile : String? = null

    class FlatFileMetadata {
        var amount = 0L
        var recordsCount = 0L

        val codes = ArrayList<Long>()
        val codesPerOAMap = HashMap<String, MutableList<Long>>()
        val amountPerOAMap = HashMap<String, Array<Long>>()
        val recordsCountPerOAMap = HashMap<String, Array<Long>>()
    }
}
