package org.taktik.freehealth.middleware.drugs.dto;

import java.io.Serializable;

public class MpppropInfos implements Serializable {
	private static final long serialVersionUID = 1L;
     private MpppropId id;
     private String strprop;
     private Double nbprop;
     private String type;

   
    public MpppropId getId() {
        return this.id;
    }
    
    public void setId(MpppropId id) {
        this.id = id;
    }

    public String getStrprop() {
        return this.strprop;
    }
    
    public void setStrprop(String strprop) {
        this.strprop = strprop;
    }
    public Double getNbprop() {
        return this.nbprop;
    }
    
    public void setNbprop(Double nbprop) {
        this.nbprop = nbprop;
    }
    public String getType() {
        return this.type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
}
