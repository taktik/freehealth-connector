package be.recipe.services.core;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
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
      String theSeverity = this.getMessage();
      strategy.appendField(locator, this, "message", buffer, theSeverity);
      theSeverity = this.getResolution();
      strategy.appendField(locator, this, "resolution", buffer, theSeverity);
      theSeverity = this.getSeverity();
      strategy.appendField(locator, this, "severity", buffer, theSeverity);
      return buffer;
   }

   public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
      if (!(object instanceof RecipeError)) {
         return false;
      } else if (this == object) {
         return true;
      } else {
         RecipeError that = (RecipeError)object;
         String lhsSeverity = this.getMessage();
         String rhsSeverity = that.getMessage();
         if (!strategy.equals(LocatorUtils.property(thisLocator, "message", lhsSeverity), LocatorUtils.property(thatLocator, "message", rhsSeverity), lhsSeverity, rhsSeverity)) {
            return false;
         } else {
            lhsSeverity = this.getResolution();
            rhsSeverity = that.getResolution();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "resolution", lhsSeverity), LocatorUtils.property(thatLocator, "resolution", rhsSeverity), lhsSeverity, rhsSeverity)) {
               return false;
            } else {
               lhsSeverity = this.getSeverity();
               rhsSeverity = that.getSeverity();
               return strategy.equals(LocatorUtils.property(thisLocator, "severity", lhsSeverity), LocatorUtils.property(thatLocator, "severity", rhsSeverity), lhsSeverity, rhsSeverity);
            }
         }
      }
   }

   public boolean equals(Object object) {
      EqualsStrategy strategy = JAXBEqualsStrategy.INSTANCE;
      return this.equals((ObjectLocator)null, (ObjectLocator)null, object, strategy);
   }

   public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
      int currentHashCode = 1;
      String theSeverity = this.getMessage();
      int currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "message", theSeverity), currentHashCode, theSeverity);
      theSeverity = this.getResolution();
      currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "resolution", theSeverity), currentHashCode, theSeverity);
      theSeverity = this.getSeverity();
      currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "severity", theSeverity), currentHashCode, theSeverity);
      return currentHashCode;
   }

   public int hashCode() {
      HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
      return this.hashCode((ObjectLocator)null, strategy);
   }
}
