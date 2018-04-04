package org.taktik.freehealth.middleware.drugs.dto;

import java.io.Serializable;

public class MpPreview implements Serializable {

	private static final long serialVersionUID = 1L;
     private MpId id;
     private String name;
     private String inforespId;
     private String pos;
     private String note;
     private String equiv;
     private String dopingcode;
     private String vaccinecode;

    public MpPreview() {
    }

    public MpId getId() {
        return this.id;
    }
    
    public void setId(MpId id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public String getInforespId() {
        return this.inforespId;
    }
    
    public void setInforespId(String inforespId) {
        this.inforespId = inforespId;
    }
    public String getPos() {
        return this.pos;
    }
    
    public void setPos(String pos) {
        this.pos = pos;
    }
    public String getNote() {
        return this.note;
    }
    
    public void setNote(String note) {
        this.note = note;
    }
    public String getEquiv() {
        return this.equiv;
    }
    
    public void setEquiv(String equiv) {
        this.equiv = equiv;
    }
    public String getDopingcode() {
        return this.dopingcode;
    }
    
    public void setDopingcode(String dopingcode) {
        this.dopingcode = dopingcode;
    }

	public String getVaccinecode() {
		return vaccinecode;
	}


	public void setVaccinecode(String vaccinecode) {
		this.vaccinecode = vaccinecode;
	}

}
