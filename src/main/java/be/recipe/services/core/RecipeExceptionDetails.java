package be.recipe.services.core;

import java.util.ArrayList;
import java.util.List;
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

   public String toString() {
      ToStringStrategy strategy = JAXBToStringStrategy.INSTANCE;
      StringBuilder buffer = new StringBuilder();
      this.append((ObjectLocator)null, buffer, strategy);
      return buffer.toString();
   }

   public StringBuilder append(ObjectLocator locator, StringBuilder buffer, ToStringStrategy strategy) {
      strategy.appendStart(locator, this, buffer);
      this.appendFields(locator, buffer, strategy);
      strategy.appendEnd(locator, this, buffer);
      return buffer;
   }

   public StringBuilder appendFields(ObjectLocator locator, StringBuilder buffer, ToStringStrategy strategy) {
      String theCode = this.getCode();
      strategy.appendField(locator, this, "code", buffer, theCode);
      ErrorMap theErrorMap = this.getErrorMap();
      strategy.appendField(locator, this, "errorMap", buffer, theErrorMap);
      return buffer;
   }

   public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
      if (!(object instanceof RecipeExceptionDetails)) {
         return false;
      } else if (this == object) {
         return true;
      } else {
         RecipeExceptionDetails that = (RecipeExceptionDetails)object;
         String lhsCode = this.getCode();
         String rhsCode = that.getCode();
         if (!strategy.equals(LocatorUtils.property(thisLocator, "code", lhsCode), LocatorUtils.property(thatLocator, "code", rhsCode), lhsCode, rhsCode)) {
            return false;
         } else {
            ErrorMap lhsErrorMap = this.getErrorMap();
            ErrorMap rhsErrorMap = that.getErrorMap();
            return strategy.equals(LocatorUtils.property(thisLocator, "errorMap", lhsErrorMap), LocatorUtils.property(thatLocator, "errorMap", rhsErrorMap), lhsErrorMap, rhsErrorMap);
         }
      }
   }

   public boolean equals(Object object) {
      EqualsStrategy strategy = JAXBEqualsStrategy.INSTANCE;
      return this.equals((ObjectLocator)null, (ObjectLocator)null, object, strategy);
   }

   public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
      int currentHashCode = 1;
      String theCode = this.getCode();
      int currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "code", theCode), currentHashCode, theCode);
      ErrorMap theErrorMap = this.getErrorMap();
      currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "errorMap", theErrorMap), currentHashCode, theErrorMap);
      return currentHashCode;
   }

   public int hashCode() {
      HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
      return this.hashCode((ObjectLocator)null, strategy);
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

      public String toString() {
         ToStringStrategy strategy = JAXBToStringStrategy.INSTANCE;
         StringBuilder buffer = new StringBuilder();
         this.append((ObjectLocator)null, buffer, strategy);
         return buffer.toString();
      }

      public StringBuilder append(ObjectLocator locator, StringBuilder buffer, ToStringStrategy strategy) {
         strategy.appendStart(locator, this, buffer);
         this.appendFields(locator, buffer, strategy);
         strategy.appendEnd(locator, this, buffer);
         return buffer;
      }

      public StringBuilder appendFields(ObjectLocator locator, StringBuilder buffer, ToStringStrategy strategy) {
         List<Entry> theEntries = this.entries != null && !this.entries.isEmpty() ? this.getEntries() : null;
         strategy.appendField(locator, this, "entries", buffer, theEntries);
         return buffer;
      }

      public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
         if (!(object instanceof ErrorMap)) {
            return false;
         } else if (this == object) {
            return true;
         } else {
            ErrorMap that = (ErrorMap)object;
            List<Entry> lhsEntries = this.entries != null && !this.entries.isEmpty() ? this.getEntries() : null;
            List<Entry> rhsEntries = that.entries != null && !that.entries.isEmpty() ? that.getEntries() : null;
            return strategy.equals(LocatorUtils.property(thisLocator, "entries", lhsEntries), LocatorUtils.property(thatLocator, "entries", rhsEntries), lhsEntries, rhsEntries);
         }
      }

      public boolean equals(Object object) {
         EqualsStrategy strategy = JAXBEqualsStrategy.INSTANCE;
         return this.equals((ObjectLocator)null, (ObjectLocator)null, object, strategy);
      }

      public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
         int currentHashCode = 1;
         List<Entry> theEntries = this.entries != null && !this.entries.isEmpty() ? this.getEntries() : null;
         int currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "entries", theEntries), currentHashCode, theEntries);
         return currentHashCode;
      }

      public int hashCode() {
         HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
         return this.hashCode((ObjectLocator)null, strategy);
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

         public String toString() {
            ToStringStrategy strategy = JAXBToStringStrategy.INSTANCE;
            StringBuilder buffer = new StringBuilder();
            this.append((ObjectLocator)null, buffer, strategy);
            return buffer.toString();
         }

         public StringBuilder append(ObjectLocator locator, StringBuilder buffer, ToStringStrategy strategy) {
            strategy.appendStart(locator, this, buffer);
            this.appendFields(locator, buffer, strategy);
            strategy.appendEnd(locator, this, buffer);
            return buffer;
         }

         public StringBuilder appendFields(ObjectLocator locator, StringBuilder buffer, ToStringStrategy strategy) {
            String theKey = this.getKey();
            strategy.appendField(locator, this, "key", buffer, theKey);
            RecipeError theValue = this.getValue();
            strategy.appendField(locator, this, "value", buffer, theValue);
            return buffer;
         }

         public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
            if (!(object instanceof Entry)) {
               return false;
            } else if (this == object) {
               return true;
            } else {
               Entry that = (Entry)object;
               String lhsKey = this.getKey();
               String rhsKey = that.getKey();
               if (!strategy.equals(LocatorUtils.property(thisLocator, "key", lhsKey), LocatorUtils.property(thatLocator, "key", rhsKey), lhsKey, rhsKey)) {
                  return false;
               } else {
                  RecipeError lhsValue = this.getValue();
                  RecipeError rhsValue = that.getValue();
                  return strategy.equals(LocatorUtils.property(thisLocator, "value", lhsValue), LocatorUtils.property(thatLocator, "value", rhsValue), lhsValue, rhsValue);
               }
            }
         }

         public boolean equals(Object object) {
            EqualsStrategy strategy = JAXBEqualsStrategy.INSTANCE;
            return this.equals((ObjectLocator)null, (ObjectLocator)null, object, strategy);
         }

         public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
            int currentHashCode = 1;
            String theKey = this.getKey();
            int currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "key", theKey), currentHashCode, theKey);
            RecipeError theValue = this.getValue();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "value", theValue), currentHashCode, theValue);
            return currentHashCode;
         }

         public int hashCode() {
            HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
            return this.hashCode((ObjectLocator)null, strategy);
         }
      }
   }
}
