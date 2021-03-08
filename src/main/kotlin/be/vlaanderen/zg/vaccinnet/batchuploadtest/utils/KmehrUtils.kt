package be.vlaanderen.zg.vaccinnet.batchuploadtest.utils

import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.cd.v1.CDHCPARTY
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.cd.v1.CDHCPARTYschemes
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.id.v1.IDHCPARTY
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.id.v1.IDHCPARTYschemes
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.schema.v1.ItemType
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.schema.v1.TransactionType


object KmehrUtils {
    fun getIdHcParty(
        idHcParties: List<IDHCPARTY>,
        scheme: IDHCPARTYschemes
    ) = idHcParties.first { idhcparty: IDHCPARTY -> scheme == idhcparty.s }

    fun getCdHcParty(cdHcParties: List<CDHCPARTY>, scheme: CDHCPARTYschemes): CDHCPARTY =
        cdHcParties.first { cdhcparty: CDHCPARTY -> scheme == cdhcparty.s }

    fun getItem(transactionType: TransactionType): ItemType =
        transactionType.headingsAndItemsAndTexts.filterIsInstance(ItemType::class.java).first()
}
