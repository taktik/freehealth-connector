package be.recipe.common.exceptions;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.jvnet.jaxb2_commons.lang.Equals;
import org.jvnet.jaxb2_commons.lang.EqualsStrategy;
import org.jvnet.jaxb2_commons.lang.HashCode;
import org.jvnet.jaxb2_commons.lang.HashCodeStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBEqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBHashCodeStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;
import org.jvnet.jaxb2_commons.locator.util.LocatorUtils;

@XmlRootElement
@XmlType(
   name = "RecipeExceptionDetails"
)
public class RecipeExceptionDetails implements Serializable {
   private static final long serialVersionUID = 1L;
   protected String code;
   @XmlElement(
      required = true
   )
   protected RecipeExceptionDetails.ErrorMap errorMap;

   public String getCode() {
      return this.code;
   }

   public void setCode(String value) {
      this.code = value;
   }

   public RecipeExceptionDetails.ErrorMap getErrorMap() {
      return this.errorMap;
   }

   public void setErrorMap(RecipeExceptionDetails.ErrorMap value) {
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
      RecipeExceptionDetails.ErrorMap theErrorMap = this.getErrorMap();
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
            RecipeExceptionDetails.ErrorMap lhsErrorMap = this.getErrorMap();
            RecipeExceptionDetails.ErrorMap rhsErrorMap = that.getErrorMap();
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
      RecipeExceptionDetails.ErrorMap theErrorMap = this.getErrorMap();
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
   public static class ErrorMap implements Serializable, Equals, HashCode, ToString {
      private static final long serialVersionUID = 1L;
      @XmlElement(
         name = "entry"
      )
      protected List<RecipeExceptionDetails.ErrorMap.Entry> entries;

      public List<RecipeExceptionDetails.ErrorMap.Entry> getEntries() {
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
         List<RecipeExceptionDetails.ErrorMap.Entry> theEntries = this.entries != null && !this.entries.isEmpty() ? this.getEntries() : null;
         strategy.appendField(locator, this, "entries", buffer, theEntries);
         return buffer;
      }

      public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
         if (!(object instanceof RecipeExceptionDetails.ErrorMap)) {
            return false;
         } else if (this == object) {
            return true;
         } else {
            RecipeExceptionDetails.ErrorMap that = (RecipeExceptionDetails.ErrorMap)object;
            List<RecipeExceptionDetails.ErrorMap.Entry> lhsEntries = this.entries != null && !this.entries.isEmpty() ? this.getEntries() : null;
            List<RecipeExceptionDetails.ErrorMap.Entry> rhsEntries = that.entries != null && !that.entries.isEmpty() ? that.getEntries() : null;
            return strategy.equals(LocatorUtils.property(thisLocator, "entries", lhsEntries), LocatorUtils.property(thatLocator, "entries", rhsEntries), lhsEntries, rhsEntries);
         }
      }

      public boolean equals(Object object) {
         EqualsStrategy strategy = JAXBEqualsStrategy.INSTANCE;
         return this.equals((ObjectLocator)null, (ObjectLocator)null, object, strategy);
      }

      public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
         int currentHashCode = 1;
         List<RecipeExceptionDetails.ErrorMap.Entry> theEntries = this.entries != null && !this.entries.isEmpty() ? this.getEntries() : null;
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
      public static class Entry implements Serializable, Equals, HashCode, ToString {
         private static final long serialVersionUID = 1L;
         protected String key;
         protected be.recipe.services.core.RecipeError value;

         public String getKey() {
            return this.key;
         }

         public void setKey(String value) {
            this.key = value;
         }

         public be.recipe.services.core.RecipeError getValue() {
            return this.value;
         }

         public void setValue(be.recipe.services.core.RecipeError value) {
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
            be.recipe.services.core.RecipeError theValue = this.getValue();
            strategy.appendField(locator, this, "value", buffer, theValue);
            return buffer;
         }

         public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
            if (!(object instanceof RecipeExceptionDetails.ErrorMap.Entry)) {
               return false;
            } else if (this == object) {
               return true;
            } else {
               RecipeExceptionDetails.ErrorMap.Entry that = (RecipeExceptionDetails.ErrorMap.Entry)object;
               String lhsKey = this.getKey();
               String rhsKey = that.getKey();
               if (!strategy.equals(LocatorUtils.property(thisLocator, "key", lhsKey), LocatorUtils.property(thatLocator, "key", rhsKey), lhsKey, rhsKey)) {
                  return false;
               } else {
                  be.recipe.services.core.RecipeError lhsValue = this.getValue();
                  be.recipe.services.core.RecipeError rhsValue = that.getValue();
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
            be.recipe.services.core.RecipeError theValue = this.getValue();
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
