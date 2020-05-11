package be.recipe.services.core;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "recipeError",
   propOrder = {"message", "resolution", "severity"}
)
public class RecipeError {
   protected String message;
   protected String resolution;
   protected String severity;

   public String getMessage() {
      return this.message;
   }

   public void setMessage(String value) {
      this.message = value;
   }

   public String getResolution() {
      return this.resolution;
   }

   public void setResolution(String value) {
      this.resolution = value;
   }

   public String getSeverity() {
      return this.severity;
   }

   public void setSeverity(String value) {
      this.severity = value;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (!(o instanceof RecipeError)) return false;
      RecipeError that = (RecipeError) o;
      return Objects.equals(message, that.message) &&
              Objects.equals(resolution, that.resolution) &&
              Objects.equals(severity, that.severity);
   }

   @Override
   public int hashCode() {
      return Objects.hash(message, resolution, severity);
   }
}
