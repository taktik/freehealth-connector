package be.business.connector.recipe.utils;

import java.util.Date;

public class MedFile {
   MedFileTypeEnum medFileType;
   Date dateSuplement;
   String CNK;
   CommercialStatusEnum commercialStatus;
   CategorieEnum categorie;
   String frBenaming;
   String nlBenaming;
   float publiekprijsIncBTW;
   String atcCode;
   CategorieTerugbetalingEnum terugbetaling;
   float remGeldActief;
   float remGeldWIGW;
   String omschrijvingFR;
   String omschrijvingNL;

   public MedFileTypeEnum getMedFileType() {
      return this.medFileType;
   }

   public void setMedFileType(MedFileTypeEnum medFileType) {
      this.medFileType = medFileType;
   }

   public Date getDateSuplement() {
      return this.dateSuplement;
   }

   public void setDateSuplement(Date dateSuplement) {
      this.dateSuplement = dateSuplement;
   }

   public String getCNK() {
      return this.CNK;
   }

   public void setCNK(String CNK) {
      this.CNK = CNK;
   }

   public CommercialStatusEnum getCommercialStatus() {
      return this.commercialStatus;
   }

   public void setCommercialStatus(CommercialStatusEnum commercialStatus) {
      this.commercialStatus = commercialStatus;
   }

   public CategorieEnum getCategorie() {
      return this.categorie;
   }

   public void setCategorie(CategorieEnum categorie) {
      this.categorie = categorie;
   }

   public String getFrBenaming() {
      return this.frBenaming;
   }

   public void setFrBenaming(String frBenaming) {
      this.frBenaming = frBenaming;
   }

   public String getNlBenaming() {
      return this.nlBenaming;
   }

   public void setNlBenaming(String nlBenaming) {
      this.nlBenaming = nlBenaming;
   }

   public float getPubliekprijsIncBTW() {
      return this.publiekprijsIncBTW;
   }

   public void setPubliekprijsIncBTW(float publiekprijsIncBTW) {
      this.publiekprijsIncBTW = publiekprijsIncBTW;
   }

   public String getAtcCode() {
      return this.atcCode;
   }

   public void setAtcCode(String atcCode) {
      this.atcCode = atcCode;
   }

   public CategorieTerugbetalingEnum getTerugbetaling() {
      return this.terugbetaling;
   }

   public void setTerugbetaling(CategorieTerugbetalingEnum terugbetaling) {
      this.terugbetaling = terugbetaling;
   }

   public float getRemGeldActief() {
      return this.remGeldActief;
   }

   public void setRemGeldActief(float remGeldActief) {
      this.remGeldActief = remGeldActief;
   }

   public float getRemGeldWIGW() {
      return this.remGeldWIGW;
   }

   public void setRemGeldWIGW(float remGeldWIGW) {
      this.remGeldWIGW = remGeldWIGW;
   }

   public String getOmschrijvingFR() {
      return this.omschrijvingFR;
   }

   public void setOmschrijvingFR(String omschrijvingFR) {
      this.omschrijvingFR = omschrijvingFR;
   }

   public String getOmschrijvingNL() {
      return this.omschrijvingNL;
   }

   public void setOmschrijvingNL(String omschrijvingNL) {
      this.omschrijvingNL = omschrijvingNL;
   }
}
