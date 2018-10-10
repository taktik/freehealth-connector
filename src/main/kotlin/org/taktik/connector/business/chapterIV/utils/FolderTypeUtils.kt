package org.taktik.connector.business.chapterIV.utils

import be.fgov.ehealth.standards.kmehr.cd.v1.CDITEM
import be.fgov.ehealth.standards.kmehr.cd.v1.CDITEMMAAvalues
import be.fgov.ehealth.standards.kmehr.cd.v1.CDITEMschemes
import be.fgov.ehealth.standards.kmehr.schema.v1.ContentType
import be.fgov.ehealth.standards.kmehr.schema.v1.FolderType
import be.fgov.ehealth.standards.kmehr.schema.v1.ItemType
import be.fgov.ehealth.standards.kmehr.schema.v1.TransactionType
import org.joda.time.DateTime

object FolderTypeUtils {
    fun retrieveConsultationStartDateOrAgreementStartDate(folder: FolderType?): DateTime? {
        if (folder != null) {
            (folder.transactions[0] as TransactionType).item.forEach {
                if (CDITEMMAAvalues.CONSULTATIONSTARTDATE.value() == (it.cds[0] as CDITEM).value && (it.cds[0] as CDITEM).s == CDITEMschemes.CD_ITEM_MAA) {
                    return (it.contents[0] as ContentType).date
                }
                if (CDITEMMAAvalues.AGREEMENTSTARTDATE.value() == (it.cds[0] as CDITEM).value && (it.cds[0] as CDITEM).s == CDITEMschemes.CD_ITEM_MAA) {
                    return (it.contents[0] as ContentType).date
                }
            }
        }
        return null
    }
}
