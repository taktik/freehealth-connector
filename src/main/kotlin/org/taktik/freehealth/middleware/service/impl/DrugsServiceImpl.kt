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

package org.taktik.freehealth.middleware.service.impl

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.taktik.freehealth.middleware.drugs.dto.DocId
import org.taktik.freehealth.middleware.drugs.dto.DocPreview
import org.taktik.freehealth.middleware.drugs.dto.FullTextSearchResult
import org.taktik.freehealth.middleware.drugs.dto.IamFullInfos
import org.taktik.freehealth.middleware.drugs.dto.MpExtendedInfos
import org.taktik.freehealth.middleware.drugs.dto.MpFullInfos
import org.taktik.freehealth.middleware.drugs.dto.MpId
import org.taktik.freehealth.middleware.drugs.dto.MpPreview
import org.taktik.freehealth.middleware.drugs.dto.MppId
import org.taktik.freehealth.middleware.drugs.dto.MppInfos
import org.taktik.freehealth.middleware.drugs.dto.MppPreview
import org.taktik.freehealth.middleware.drugs.logic.DrugsLogic
import org.taktik.freehealth.middleware.service.DrugsService

import java.io.IOException
import java.util.ArrayList

@Service
class DrugsServiceImpl : DrugsService {
    private var drugsLogic: DrugsLogic? = null

    override fun getMedecinePackages(searchString: String, lang: String, types: List<String>, first: Int, count: Int): List<MppPreview> {
        return drugsLogic!!.getMedecinePackages(searchString, lang, types, first, count)
    }

    override fun getInnClusters(searchString: String, lang: String, types: List<String>, first: Int, count: Int): List<MppPreview> {
        return drugsLogic!!.getInnClusters("be", searchString, lang, types, first, count)
    }

    override fun getMedecinePackagesFromIngredients(searchString: String, lang: String, types: List<String>, first: Int, count: Int): List<MppPreview> {
        return drugsLogic!!.getMedecinePackagesFromIngredients(searchString, lang, types, first, count)
    }

    override fun getMppInfos(medecinePackageID: MppId): MppInfos {
        return drugsLogic!!.getInfos(medecinePackageID)
    }

    override fun getExtentedMpInfosWithPackage(medecinePackageID: MppId): MpExtendedInfos {
        return drugsLogic!!.getExtendedMpInfos(medecinePackageID)
    }

    override fun getCheapAlternativesBasedOnAtc(medecinePackageID: MppId): List<MpPreview> {
        return drugsLogic!!.getCheapAlternativesBasedOnAtc(medecinePackageID)
    }

    override fun getInteractions(medecinePackageID: MppId, otherCnks: List<String>): List<IamFullInfos> {
        return drugsLogic!!.getInteractions(medecinePackageID, otherCnks)
    }

    override fun getCheapAlternativesBasedOnInn(innCluster: String, lang: String): List<MppPreview> {
        return drugsLogic!!.getCheapAlternativesBasedOnInn(innCluster, lang)
    }

    override fun getFullMpInfosWithPackage(medecinePackageID: MppId): MpFullInfos {
        return drugsLogic!!.getFullMpInfos(medecinePackageID)
    }

    @Throws(IOException::class)
    override fun fullTextSearch(search: String, lang: String, classes: List<String>, types: List<String>, from: Int, count: Int): List<FullTextSearchResult> {
        return drugsLogic!!.fullTextSearch(search, lang, classes, types, from, count)
    }

    override fun getChildrenDocsAndMps(docID: DocId): List<Any> {
        val result = ArrayList<Any>()
        result.add(drugsLogic!!.getChildrenDocs(docID))
        result.add(drugsLogic!!.getChildrenMps(docID))
        return result
    }

    override fun getDocPreview(id: String, lang: String): DocPreview {
        return drugsLogic!!.getDocPreview(id, lang)
    }

    override fun getMpFromMpp(mppId: String, lang: String): MpPreview {
        return drugsLogic!!.getMpFromMpp(mppId, lang)
    }

    override fun getRootDocs(lang: String): List<DocPreview> {
        return drugsLogic!!.getRootDocs(lang)
    }

    override fun getDocOfMp(mpId: MpId): DocPreview {
        return drugsLogic!!.getDocOfMp(mpId)
    }

    override fun getParentDoc(docID: DocId): DocPreview {
        return drugsLogic!!.getParentDoc(docID)
    }

    @Autowired
    fun setDrugsLogic(drugsLogic: DrugsLogic) {
        this.drugsLogic = drugsLogic
    }
}
