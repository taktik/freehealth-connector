package be.ehealth.businessconnector.ehbox.api.domain;

import java.util.ArrayList;
import java.util.List;

public class ErrorMessage<T> extends Message<T> {
   private static final long serialVersionUID = 7966804248741017234L;
   private String title;
   private String errorPublicationId;
   private String errorCode;
   private List<String> errorMsg = new ArrayList();

   public void setTitle(String title) {
      this.title = title;
   }

   public String getTitle() {
      return this.title;
   }

   public List<String> getErrorMsg() {
      return this.errorMsg;
   }

   public void setErrorCode(String errorCode) {
      this.errorCode = errorCode;
   }

   public String getErrorCode() {
      return this.errorCode;
   }

   public void setErrorPublicationId(String errorPublicationId) {
      this.errorPublicationId = errorPublicationId;
   }

   public String getErrorPublicationId() {
      return this.errorPublicationId;
   }

   public String toString() {
      return "ErrorMessage [getErrorTitle()=" + this.getTitle() + "]" + "Message [id=" + this.getId() + ", publicationId=" + this.getPublicationId() + ", sender=" + this.getSender() + ", destinations=" + this.getDestinations() + ", important=" + this.isImportant() + ", encrypted=" + this.isEncrypted() + "]";
   }
}
