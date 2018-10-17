package org.taktik.connector.business.domain.chapter4

import java.io.Serializable
import java.util.ArrayList

/**
 * Created with IntelliJ IDEA.
 * User: aduchate
 * Date: 11/06/13
 * Time: 15:07
 * To change this template use File | Settings | File Templates.
 */
class AgreementResponse : Serializable {

    var isAcknowledged: Boolean = false
    var warnings: Collection<Problem>? = null
    var errors: Collection<Problem>? = null
    var content: ByteArray? = null

    var requestXML: String? = null
    var responseXML: String? = null

    internal var transactions: MutableList<AgreementTransaction> = ArrayList()

    fun addTransaction(t: AgreementTransaction): AgreementTransaction {
        transactions.add(t)
        return t
    }

    fun getTransactions(): List<AgreementTransaction> {
        return transactions
    }

}
