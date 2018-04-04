package org.taktik.freehealth.middleware.drugs.dto;

import java.io.Serializable;

/**
 * Infos about a Galenic form
 * @author abaudoux
 *
 */
public class GalInfos implements Serializable {
	private static final long serialVersionUID = 1L;
    private GalId id;
    private String name;
    private Boolean deleted;

   public GalInfos() {
   }

   public GalId getId() {
       return this.id;
   }

   public void setId(GalId id) {
       this.id = id;
   }
   public String getName() {
       return this.name;
   }

   public void setName(String name) {
       this.name = name;
   }
   public Boolean getDeleted() {
       return this.deleted;
   }

   public void setDeleted(Boolean deleted) {
       this.deleted = deleted;
   }

}
