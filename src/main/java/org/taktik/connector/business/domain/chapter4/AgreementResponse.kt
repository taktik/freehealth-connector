package org.taktik.connector.business.domain.chapter4

import org.taktik.freehealth.middleware.dto.mycarenet.MycarenetError
import org.taktik.freehealth.middleware.dto.mycarenet.CommonOutput
import org.taktik.freehealth.middleware.dto.mycarenet.MycarenetConversation
import java.io.Serializable
import java.util.ArrayList

/**
 * Created with IntelliJ IDEA.
 * User: aduchate
 * Date: 11/06/13
 * Time: 15:07
 * To change this template use File | Settings | File Templates.
 */
class AgreementResponse(
    var commonOutput: CommonOutput? = null,
    var mycarenetConversation: MycarenetConversation? = null,
    var isAcknowledged: Boolean = false,
    var warnings: List<MycarenetError>? = null,
    var errors: List<MycarenetError>? = null,
    var content: ByteArray? = null,
    var transactions: MutableList<AgreementTransaction> = ArrayList()
                       ) : Serializable {

    var requestXML: String? = null
    var responseXML: String? = null

    internal var transactions: MutableList<AgreementTransaction> = ArrayList()

    fun addTransaction(t: AgreementTransaction): AgreementTransaction {
        transactions.add(t)
        return t
    }

}
