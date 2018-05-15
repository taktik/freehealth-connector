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

package org.taktik.freehealth.middleware.service

import org.taktik.freehealth.middleware.drugs.dto.DocId
import org.taktik.freehealth.middleware.drugs.dto.DocPreview
import org.taktik.freehealth.middleware.drugs.dto.FullTextSearchResult
import org.taktik.freehealth.middleware.drugs.dto.MpExtendedInfos
import org.taktik.freehealth.middleware.drugs.dto.MpFullInfos
import org.taktik.freehealth.middleware.drugs.dto.MpId
import org.taktik.freehealth.middleware.drugs.dto.MpPreview
import org.taktik.freehealth.middleware.drugs.dto.MppId
import org.taktik.freehealth.middleware.drugs.dto.MppInfos
import org.taktik.freehealth.middleware.drugs.dto.MppPreview

import java.io.IOException

/**
 * Published WebServices for the drugs system.
 * @author abaudoux
 */

interface DrugsService {
    /**
     * Retrieve a list of MPP's by name.
     *
     *
     * @param searchString the first letters of the mpp
     * @param first the first result to return
     * @param count the number of results to return
     * @return The list of found Mpp in preview type.
     */
    fun getMedecinePackages(
        searchString: String,
        lang: String,
        types: List<String>,
        first: Int,
        count: Int
    ): List<MppPreview>

    /**
     * Retrieve a list of MPP's by name or ingredients'.
     *
     *
     * @param searchString the first letters of the mpp or its ingredients
     * @param first the first result to return
     * @param count the number of results to return
     * @return The list of found Mpp in preview type.
     */
    fun getMedecinePackagesFromIngredients(
        searchString: String,
        lang: String,
        types: List<String>,
        first: Int,
        count: Int
    ): List<MppPreview>

    /**
     * Retrieve detailed infos about a Mpp
     * @param medecinePackageID the id of the Mpp
     * @return The detailed infos
     */
    fun getMppInfos(medecinePackageID: MppId): MppInfos

    /**
     * Retrieve extended infos about a Mp
     * @param medecinePackageID the id of the Mpp
     * @return The detailed infos
     */
    fun getExtentedMpInfosWithPackage(medecinePackageID: MppId): MpExtendedInfos

    /**
     * Perform a full-text search through the Drugs database.
     * @return
     */
    @Throws(IOException::class)
    fun fullTextSearch(
        search: String,
        lang: String,
        classes: List<String>,
        types: List<String>,
        first: Int,
        count: Int
    ): List<FullTextSearchResult>

    /**
     * Retrieve a preview of a doc
     */
    fun getDocPreview(id: String, lang: String): DocPreview

    /**
     * Retrieve combined children Docs and mps of a doc.
     *
     * The first item in the list is children docs
     * The second item in the list is children mps
     */
    fun getChildrenDocsAndMps(docID: DocId): List<Any>

    /**
     * Retrieve the root documentation (main chapters)
     * @param lang
     * @return
     */
    fun getRootDocs(lang: String): List<DocPreview>

    /**
     * Get a Mp from a mpp
     * @param mppId
     * @param lang
     * @return
     */
    fun getMpFromMpp(mppId: String, lang: String): MpPreview

    /**
     * Retrieve the parent documentation of a given doc.
     */
    fun getParentDoc(docId: DocId): DocPreview

    /**
     * Retrieve the related documentation of a given mp.
     */
    fun getDocOfMp(mpId: MpId): DocPreview

    fun getFullMpInfosWithPackage(medecinePackageID: MppId): MpFullInfos

    fun getCheapAlternativesBasedOnAtc(medecinePackageID: MppId): List<*>

    fun getInteractions(medecinePackageID: MppId, otherCnks: List<String>): List<*>

    fun getCheapAlternativesBasedOnInn(innClusterId: String, lang: String): List<*>

    fun getInnClusters(
        searchString: String,
        lang: String,
        types: List<String>,
        first: Int,
        count: Int
    ): List<MppPreview>
}
