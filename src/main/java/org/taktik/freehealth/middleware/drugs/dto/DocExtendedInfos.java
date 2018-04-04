package org.taktik.freehealth.middleware.drugs.dto;

import java.io.Serializable;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

public class DocExtendedInfos implements Serializable {

	private static final long serialVersionUID = 1L;
     private DocId id;
     private String hierarchy;
     private Integer docindex;
     private Boolean mpgrp;
     private String title;
     private String content;
     private String pos;
     private String type;
     private DocPreview parent;
     private DocPreview next;
     private DocPreview previous;
     private SortedSet<MpExtendedInfos> mps = new TreeSet<MpExtendedInfos>();
     
     private List<DocPreview> children;
     
     private List<DocExtendedInfos> mpGroups;

    public List<DocExtendedInfos> getMpGroups() {
		return mpGroups;
	}


	public void setMpGroups(List<DocExtendedInfos> mpGroups) {
		this.mpGroups = mpGroups;
	}


	public DocExtendedInfos() {
    }
    

    public DocId getId() {
        return this.id;
    }
    
    public void setId(DocId id) {
        this.id = id;
    }

    public String getHierarchy() {
        return this.hierarchy;
    }
    
    public void setHierarchy(String hierarchy) {
        this.hierarchy = hierarchy;
    }
    public Integer getDocindex() {
        return this.docindex;
    }
    
    public void setDocindex(Integer docindex) {
        this.docindex = docindex;
    }
    public Boolean getMpgrp() {
        return this.mpgrp;
    }
    
    public void setMpgrp(Boolean mpgrp) {
        this.mpgrp = mpgrp;
    }
    public String getTitle() {
        return this.title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    public String getContent() {
        return this.content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }
    public String getPos() {
        return this.pos;
    }
    
    public void setPos(String pos) {
        this.pos = pos;
    }
    public String getType() {
        return this.type;
    }
    
    public void setType(String type) {
        this.type = type;
    }


	public DocPreview getParent() {
		return parent;
	}

	public void setParent(DocPreview parent) {
		this.parent = parent;
	}

	public DocPreview getNext() {
		return next;
	}

	public void setNext(DocPreview next) {
		this.next = next;
	}

	public DocPreview getPrevious() {
		return previous;
	}

	public void setPrevious(DocPreview previous) {
		this.previous = previous;
	}

	public SortedSet<MpExtendedInfos> getMps() {
		return mps;
	}

	public void setMps(SortedSet<MpExtendedInfos> mps) {
		this.mps = mps;
	}

	public List<DocPreview> getChildren() {
		return children;
	}

	public void setChildren(List<DocPreview> children) {
		this.children = children;
	}

}
