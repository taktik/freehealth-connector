package be.ehealth.businessconnector.ehbox.api.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DocumentMessage<T> extends Message<T> {
   private static final long serialVersionUID = 7523369900577837011L;
   private Document document;
   private String freeText;
   private String freeInformationTableTitle;
   private Map<String, String> freeInformationTableRows = new HashMap();
   private String patientInss;
   private List<Document> annex = new ArrayList();
   private List<String> copyMailTo = new ArrayList();

   public DocumentMessage() {
   }

   public final String getDocumentTitle() {
      return this.document == null ? null : this.document.getTitle();
   }

   public final Document getDocument() {
      return this.document;
   }

   public final void setDocument(Document document) {
      this.document = document;
   }

   public final String getFreeText() {
      return this.freeText;
   }

   public final void setFreeText(String freeText) {
      if (freeText != null) {
         this.setHasFreeInformations(true);
      }

      this.freeText = freeText;
   }

   public final String getPatientInss() {
      return this.patientInss;
   }

   public final void setPatientInss(String patientInss) {
      if (patientInss != null) {
         this.setEncrypted(true);
      }

      this.patientInss = patientInss;
   }

   public final List<Document> getAnnexList() {
      if (this.annex == null) {
         this.annex = new ArrayList();
      }

      return this.annex;
   }

   public List<String> getCopyMailTo() {
      return this.copyMailTo;
   }

   public String getFreeInformationTableTitle() {
      return this.freeInformationTableTitle;
   }

   public void setFreeInformationTableTitle(String freeInformationTableTitle) {
      this.freeInformationTableTitle = freeInformationTableTitle;
   }

   public Map<String, String> getFreeInformationTableRows() {
      return this.freeInformationTableRows;
   }

   public void setFreeInformationTableRows(Map<String, String> freeInformationTableRows) {
      this.freeInformationTableRows = freeInformationTableRows;
   }
}
