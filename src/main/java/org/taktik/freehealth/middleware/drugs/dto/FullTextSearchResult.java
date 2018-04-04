package org.taktik.freehealth.middleware.drugs.dto;

import java.io.Serializable;

/**
 * Encapsulate a result in a full-text search
 * @author abaudoux
 */

public class FullTextSearchResult implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected String resultClass;
	protected String id;
	protected String lang;
	protected String title;
	protected Number score;
	
	public String getResultClass() {
		return resultClass;
	}
	public void setResultClass(String resultClass) {
		this.resultClass = resultClass;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLang() {
		return lang;
	}
	public void setLang(String lang) {
		this.lang = lang;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Number getScore() {
		return score;
	}
	public void setScore(Number score) {
		this.score = score;
	}
}
