package org.taktik.freehealth.middleware.drugs.dao;

import org.taktik.freehealth.middleware.drugs.*;
import org.taktik.freehealth.middleware.drugs.Atc;
import org.taktik.freehealth.middleware.drugs.civics.*;
import org.taktik.freehealth.middleware.drugs.dto.DocId;
import org.taktik.freehealth.middleware.drugs.dto.FullTextSearchResult;
import org.taktik.freehealth.middleware.drugs.dto.MpId;
import org.taktik.freehealth.middleware.drugs.dto.MppId;

import java.io.IOException;
import java.util.List;

/**
 * DAO interface of the Drugs database system
 * @author abaudoux
 *
 */
public interface DrugsDAO {
	/**
	 * Opens a session to the datastore
	 */
	void openDataStoreSession();

	/**
	 * Closes a session to the datastore
	 */
	void closeDataStoreSession();

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
	List<Mpp> getMedecinePackages(String searchString, String lang, List<String> types, int first, int count);

	/**
	 * Retrieve a list of MPP's by name or ingredients' name.
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
	List<Mpp> getMedecinePackagesFromIngredients(String searchString,
	                                             String lang, List<String> types, int first, int count);

	/**
	 * Load a Mpp and pre-fetch some of its relations : Gal and MP
	 * @param medecinePackageID The id of the Mp
	 * @return The loaded Mpp
	 */
	Mpp getInfos(MppId medecinePackageID);

	/**
	 * Load a Mp and pre-fetch some of its relations
	 * @param medecineID The id of the Medecine
	 * @return The loaded Mp
	 */
	Mp getExtendedInfos(MpId medecineID);

	/**
	 * Load a documentation
	 * @param docID
	 * @return
	 */
	Doc getExtendedInfos(DocId docID);

	/**
	 * Load a documentation without prefetching anything
	 * @param docID
	 * @return
	 */
	Doc getDoc(DocId docID);

	/**
	 * Load the root documentation nodes.
	 */
	List<Doc> getRootDocs(String lang);

	/**
	 * Retrieve full infos about a Mp
	 */
	Mp getFullMpInfos(MpId mpId);

	/**
	 * Perform a full-text search through the Drugs database.
	 * @return
	 * @throws IOException
	 */
	List<FullTextSearchResult> fullTextSearch(String search, String lang, List<String> classes, List<String> types, int from, int count) throws IOException;

	/**
	 * Retrieve a mpp without prefetching anything
	 */
	Mpp getMpp(MppId mppId);

	/**
	 * Installs a new Drugs database from path
	 * @param path
	 */
	void installNewDrugsDatabase(String path);

	/**
	 * Is there any drug database available?
	 * @return
	 */
	boolean isDatabasePresent();

	/**
	 * Initializes the drugs database systems
	 */
	void initDrugsDatabase();

	/**
	 * Release any connections to the drugs database.
	 */
	void stopDrugsDatabase();

	/**
	 * Retrieve a mp without prefetching anything
	 */
	Mp getMp(MpId mpId);


    Atc getAtc(MppId medecinePackageID);

    List<Mp> getMpsWithAtc(Atc atc);

    List<Mpp> getCheapMppsWithInn(String inn, String lang);

    List<Mpp> getMppsWithInn(String inn, String lang);

    List<Iam> getIams(String id, String lang);

    Ampp getAmpp(MppId mppId);

    Therapy getTherapy(Long therapyId);

    Paragraph getParagraph(Therapy therapy);

    Verse getHeaderVerse(Paragraph paragraph);

    List<Verse> getChildrenVerses(Verse verse);

    List<Paragraph> findParagraphs(String searchString, String language);

    Paragraph getParagraph(String chapterName, String paragraphName);

	List<Mpp> getMppsForParagraph(String chapterName, String paragraphName);

	List<AddedDocument> getAddedDocuments(String chapterName, String paragraphName);

    String getShortText(Long nameId, String fr);

    List<Paragraph> findParagraphsWithCnk(Long cnk, String language);
}
