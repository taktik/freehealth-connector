package org.taktik.freehealth.middleware.drugs.dto;

import java.io.Serializable;

public class IngredientInfos  implements Serializable{

	private static final long serialVersionUID = 1L;
     private IngredientId id;
     private String name;
     private String note;
     private Boolean deleted;
     private String type;

    public IngredientInfos() {
    }
   
    public IngredientId getId() {
        return this.id;
    }
    
    public void setId(IngredientId id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public String getNote() {
        return this.note;
    }
    
    public void setNote(String note) {
        this.note = note;
    }
    public Boolean getDeleted() {
        return this.deleted;
    }
    
    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
    public String getType() {
        return this.type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
}
