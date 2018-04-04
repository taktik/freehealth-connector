package org.taktik.connector.business.mycarenetdomaincommons.exception;

public class ValidationError {
   private String path;
   private String description;

   public ValidationError(String path, String description) {
      this.path = path;
      this.description = description;
   }

   public String getPath() {
      return this.path;
   }

   public String getDescription() {
      return this.description;
   }

   public String toString() {
      return "ValidationError [path=" + this.path + ", description=" + this.description + "]";
   }
}
