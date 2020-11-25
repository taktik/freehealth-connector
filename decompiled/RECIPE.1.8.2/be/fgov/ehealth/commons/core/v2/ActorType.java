package be.fgov.ehealth.commons.core.v2;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
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

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ActorType",
   propOrder = {"ids", "firstNames", "name"}
)
public class ActorType implements Equals, HashCode, ToString {
   @XmlElement(
      name = "Id",
      required = true
   )
   protected List<Id> ids;
   @XmlElement(
      name = "FirstName"
   )
   protected List<String> firstNames;
   @XmlElement(
      name = "Name",
      required = true
   )
   protected String name;

   public List<Id> getIds() {
      if (this.ids == null) {
         this.ids = new ArrayList();
      }

      return this.ids;
   }

   public List<String> getFirstNames() {
      if (this.firstNames == null) {
         this.firstNames = new ArrayList();
      }

      return this.firstNames;
   }

   public String getName() {
      return this.name;
   }

   public void setName(String value) {
      this.name = value;
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
      List<Id> theFirstNames = this.ids != null && !this.ids.isEmpty() ? this.getIds() : null;
      strategy.appendField(locator, this, "ids", buffer, theFirstNames);
      theFirstNames = this.firstNames != null && !this.firstNames.isEmpty() ? this.getFirstNames() : null;
      strategy.appendField(locator, this, "firstNames", buffer, theFirstNames);
      String theName = this.getName();
      strategy.appendField(locator, this, "name", buffer, theName);
      return buffer;
   }

   public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
      if (!(object instanceof ActorType)) {
         return false;
      } else if (this == object) {
         return true;
      } else {
         ActorType that = (ActorType)object;
         List<Id> lhsFirstNames = this.ids != null && !this.ids.isEmpty() ? this.getIds() : null;
         List<Id> rhsFirstNames = that.ids != null && !that.ids.isEmpty() ? that.getIds() : null;
         if (!strategy.equals(LocatorUtils.property(thisLocator, "ids", lhsFirstNames), LocatorUtils.property(thatLocator, "ids", rhsFirstNames), lhsFirstNames, rhsFirstNames)) {
            return false;
         } else {
            lhsFirstNames = this.firstNames != null && !this.firstNames.isEmpty() ? this.getFirstNames() : null;
            rhsFirstNames = that.firstNames != null && !that.firstNames.isEmpty() ? that.getFirstNames() : null;
            if (!strategy.equals(LocatorUtils.property(thisLocator, "firstNames", lhsFirstNames), LocatorUtils.property(thatLocator, "firstNames", rhsFirstNames), lhsFirstNames, rhsFirstNames)) {
               return false;
            } else {
               String lhsName = this.getName();
               String rhsName = that.getName();
               return strategy.equals(LocatorUtils.property(thisLocator, "name", lhsName), LocatorUtils.property(thatLocator, "name", rhsName), lhsName, rhsName);
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
      List<Id> theFirstNames = this.ids != null && !this.ids.isEmpty() ? this.getIds() : null;
      int currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "ids", theFirstNames), currentHashCode, theFirstNames);
      theFirstNames = this.firstNames != null && !this.firstNames.isEmpty() ? this.getFirstNames() : null;
      currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "firstNames", theFirstNames), currentHashCode, theFirstNames);
      String theName = this.getName();
      currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "name", theName), currentHashCode, theName);
      return currentHashCode;
   }

   public int hashCode() {
      HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
      return this.hashCode((ObjectLocator)null, strategy);
   }
}
