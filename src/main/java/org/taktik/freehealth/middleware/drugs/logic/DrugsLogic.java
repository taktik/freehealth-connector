package org.taktik.freehealth.middleware.drugs.logic;

import org.taktik.freehealth.middleware.drugs.civics.AddedDocumentPreview;
import org.taktik.freehealth.middleware.drugs.civics.ParagraphInfos;
import org.taktik.freehealth.middleware.drugs.civics.ParagraphPreview;
import org.taktik.freehealth.middleware.drugs.dto.DocExtendedInfos;
import org.taktik.freehealth.middleware.drugs.dto.DocId;
import org.taktik.freehealth.middleware.drugs.dto.DocPreview;
import org.taktik.freehealth.middleware.drugs.dto.FullTextSearchResult;
import org.taktik.freehealth.middleware.drugs.dto.IamFullInfos;
import org.taktik.freehealth.middleware.drugs.dto.MpExtendedInfos;
import org.taktik.freehealth.middleware.drugs.dto.MpFullInfos;
import org.taktik.freehealth.middleware.drugs.dto.MpId;
import org.taktik.freehealth.middleware.drugs.dto.MpPreview;
import org.taktik.freehealth.middleware.drugs.dto.MppId;
import org.taktik.freehealth.middleware.drugs.dto.MppInfos;
import org.taktik.freehealth.middleware.drugs.dto.MppPreview;

import java.io.IOException;
import java.util.List;

/**
 * The main interface to the drugs database system.
 *
 * MP 	= Medial product
 * MPP 	= Medical product package
 *
 *
 * @author abaudoux
 *
 */

public interface DrugsLogic {

	/**
	 * Initialize the drugs database :
	 * open a connection, and copy the default
	 * database if needed.
	 *
	 */
	public void initDrugsDatabase();

	/**
	 * Is there any valid drug database?
	 * @return
	 */
	public boolean isDataBasePresent();

	/**
	 * Installs a new Drugs database from the file at Path
	 * @param path
	 */
	public void installNewDrugsDatabase(String path);

	/**
	 * Return the most appropriate available language for
	 * the given proposed language
	 */

	public String getAvailableLanguage(String proposedLanguage);

	/**
	 * Retrieve a list of MPP's by name.
	 *
	 *
	 * @param searchString the first letters of the mpp
	 * @param lang The language of the Search
	 * @param types The types of the dbs to search in. Specify null or empty list
	 * To search in all dbs.
	 * @param first the first result to return
	 * @param count the number of results to return
	 * @return The list of found Mpp in preview type.
	 */
	public List<MppPreview> getMedecinePackages(String searchString, String lang, List<String> types, int first, int count);

	/**
	 * Retrieve a list of MPP's by name or ingredients'.
	 *
	 *
	 * @param searchString the first letters of the mpp or its ingredients
	 * @param lang The language of the Search
	 * @param types The types of the dbs to search in. Specify null or empty list
	 * To search in all dbs.
	 * @param first the first result to return
	 * @param count the number of results to return
	 * @return The list of found Mpp in preview type.
	 */
	public List<MppPreview> getMedecinePackagesFromIngredients(String searchString, String lang, List<String> types, int first, int count);


    public List<MppPreview> getMedecinePackagesFromInn(String inn, String lang);

    /**
	 * Retrieve detailed infos about a Mpp
	 * @param medecinePackageID the id of the Mpp
	 * @return The detailed infos
	 */
	public MppInfos getInfos(MppId medecinePackageID);

	/**
	 * Retrieve extended infos about a medecine
	 */
	public MpExtendedInfos getExtendedMpInfos(MppId medecinePackageID);

	/**
	 * Retrieve extended infos about a medecine
	 */
	public MpExtendedInfos getExtendedMpInfos(MpId medecineID);


	/**
	 * Retrieve extended infos about a documentationItem
	 */
	public DocPreview getDocPreview(String id, String lang);

	/**
	 * Retrive children Docs of a doc
	 */
	public List<DocPreview> getChildrenDocs(DocId docID);


	/**
	 * Retrive children Mps of a doc
	 */
	public List<MpPreview> getChildrenMps(DocId docID);


	/**
	 * Retrieve extended infos about a documentationItem
	 */
	public DocExtendedInfos getExtendedDocInfos(DocId docID);

	/**
	 * Get the next documentation.
	 * @param docID
	 * @return
	 */
	public DocPreview getNextDoc(DocId docID);

	/**
	 * Get the previous documentation.
	 * @param docID
	 * @return
	 */
	public DocPreview getPreviousDoc(DocId docID);

	/**
	 * Retrieve the root documentation (main chapters)
	 * @param lang
	 * @return
	 */
	public List<DocPreview> getRootDocs(String lang);


	/**
	 * Retrieve full infos about a Mp
	 */
	public MpFullInfos getFullMpInfos(MpId mpId);


    /**
     * Looks for Interactions between newMppId and otherMpps in specified language.
     * @param newMppId
     * @param otherMpps
     * @return
     */
    public List<IamFullInfos> getInteractions(MppId newMppId, List<String> otherMpps);

	/**
	 * Perform a full-text search through the Drugs database.
	 * @return
	 * @throws IOException
	 */
	public List<FullTextSearchResult> fullTextSearch(String search, String lang, List<String> classes, List<String> types, int from, int count) throws IOException;


	/**
	 * Get a Mp from a mpp id
	 * @param mpId
	 * @param lang
	 * @return
	 */
	public MpPreview getMpFromMpp(String mpId, String lang);

	/**
	 * Returns the documentation relative to the given Mp.
	 * @param mpId
	 * @return
	 */
	DocPreview getDocOfMp(MpId mpId);

	DocPreview getParentDoc(DocId docID);

    MpFullInfos getFullMpInfos(MppId medecinePackageID);

    List<MpPreview> getCheapAlternativesBasedOnAtc(MppId medecinePackageID);

    List<MppPreview> getCheapAlternativesBasedOnInn(String innCluster, String lang);

    List<ParagraphPreview> findParagraphs(String searchString, String language);

    ParagraphInfos getParagraphInfos(String chapterName, String paragraphName);

	List<MppPreview> getMppsForParagraph(String chapterName, String paragraphName);

	List<String> getVtmNamesForParagraph(String chapterName, String paragraphName, String language);

	List<AddedDocumentPreview> getAddedDocuments(String chapterName, String paragraphName);

    List<ParagraphPreview> findParagraphsWithCnk(Long cnk, String language);
}
