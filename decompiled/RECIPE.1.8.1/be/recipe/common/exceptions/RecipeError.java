package be.recipe.common.exceptions;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class RecipeError implements Serializable {
   private String message = "";
   private String severity = "";
   private String resolution = "";

   public RecipeError() {
   }

   public RecipeError(String i18nMsg) {
      String[] splitted = i18nMsg.split(";");
      if (splitted[0] != null) {
         this.message = splitted[0].trim();
      }

      if (splitted.length > 1 && splitted[1] != null) {
         this.severity = splitted[1].trim();
      }

      if (splitted.length > 2 && splitted[2] != null) {
         this.resolution = splitted[2].trim();
      }

   }

   public String getMessage() {
      return this.message;
   }

   public String getSeverity() {
      return this.severity;
   }

   public String getResolution() {
      return this.resolution;
   }

   public void setMessage(String message) {
      this.message = message;
   }

   public void setSeverity(String severity) {
      this.severity = severity;
   }

   public void setResolution(String resolution) {
      this.resolution = resolution;
   }
}
