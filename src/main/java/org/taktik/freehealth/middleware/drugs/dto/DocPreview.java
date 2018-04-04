package org.taktik.freehealth.middleware.drugs.dto;

import java.io.Serializable;

public class DocPreview implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected DocId id;
	protected String title;
	protected String hierarchy;
    private Boolean mpgrp;
	
	public DocPreview() {
	}
	
	public DocId getId() {
		return id;
	}
	public void setId(DocId id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	public String getHierarchy() {
		return hierarchy;
	}

	public void setHierarchy(String hierarchy) {
		this.hierarchy = hierarchy;
	}

	public Boolean getMpgrp() {
		return mpgrp;
	}

	public void setMpgrp(Boolean mpgrp) {
		this.mpgrp = mpgrp;
	}
	
}
