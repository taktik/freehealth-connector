package be.recipe.services.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "recipeExceptionDetails",
   propOrder = {"code", "errorMap"}
)
public class RecipeExceptionDetails {
   protected String code;
   @XmlElement(
      required = true
   )
   protected ErrorMap errorMap;

   public String getCode() {
      return this.code;
   }

   public void setCode(String value) {
      this.code = value;
   }

   public ErrorMap getErrorMap() {
      return this.errorMap;
   }

   public void setErrorMap(ErrorMap value) {
      this.errorMap = value;
   }

   @XmlAccessorType(XmlAccessType.FIELD)
   @XmlType(
      name = "",
      propOrder = {"entries"}
   )
   public static class ErrorMap {
      @XmlElement(
         name = "entry"
      )
      protected List<Entry> entries;

      public List<Entry> getEntries() {
         if (this.entries == null) {
            this.entries = new ArrayList();
         }

         return this.entries;
      }

      @XmlAccessorType(XmlAccessType.FIELD)
      @XmlType(
         name = "",
         propOrder = {"key", "value"}
      )
      public static class Entry {
         protected String key;
         protected RecipeError value;

         public String getKey() {
            return this.key;
         }

         public void setKey(String value) {
            this.key = value;
         }

         public RecipeError getValue() {
            return this.value;
         }

         public void setValue(RecipeError value) {
            this.value = value;
         }

         @Override
         public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Entry)) return false;
            Entry entry = (Entry) o;
            return Objects.equals(key, entry.key) &&
                    Objects.equals(value, entry.value);
         }

         @Override
         public int hashCode() {
            return Objects.hash(key, value);
         }
      }

      @Override
      public boolean equals(Object o) {
         if (this == o) return true;
         if (!(o instanceof ErrorMap)) return false;
         ErrorMap errorMap = (ErrorMap) o;
         return Objects.equals(entries, errorMap.entries);
      }

      @Override
      public int hashCode() {
         return Objects.hash(entries);
      }
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (!(o instanceof RecipeExceptionDetails)) return false;
      RecipeExceptionDetails that = (RecipeExceptionDetails) o;
      return Objects.equals(code, that.code) &&
              Objects.equals(errorMap, that.errorMap);
   }

   @Override
   public int hashCode() {
      return Objects.hash(code, errorMap);
   }
}
