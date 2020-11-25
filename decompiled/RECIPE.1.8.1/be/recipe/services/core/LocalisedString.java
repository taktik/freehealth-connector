package be.recipe.services.core;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;
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
   name = "LocalisedString",
   propOrder = {"value"}
)
public class LocalisedString implements Equals, HashCode, ToString {
   @XmlValue
   protected String value;
   @XmlAttribute(
      name = "lang"
   )
   protected LangageType lang;

   public String getValue() {
      return this.value;
   }

   public void setValue(String value) {
      this.value = value;
   }

   public LangageType getLang() {
      return this.lang;
   }

   public void setLang(LangageType value) {
      this.lang = value;
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
      String theValue = this.getValue();
      strategy.appendField(locator, this, "value", buffer, theValue);
      LangageType theLang = this.getLang();
      strategy.appendField(locator, this, "lang", buffer, theLang);
      return buffer;
   }

   public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
      if (!(object instanceof LocalisedString)) {
         return false;
      } else if (this == object) {
         return true;
      } else {
         LocalisedString that = (LocalisedString)object;
         String lhsValue = this.getValue();
         String rhsValue = that.getValue();
         if (!strategy.equals(LocatorUtils.property(thisLocator, "value", lhsValue), LocatorUtils.property(thatLocator, "value", rhsValue), lhsValue, rhsValue)) {
            return false;
         } else {
            LangageType lhsLang = this.getLang();
            LangageType rhsLang = that.getLang();
            return strategy.equals(LocatorUtils.property(thisLocator, "lang", lhsLang), LocatorUtils.property(thatLocator, "lang", rhsLang), lhsLang, rhsLang);
         }
      }
   }

   public boolean equals(Object object) {
      EqualsStrategy strategy = JAXBEqualsStrategy.INSTANCE;
      return this.equals((ObjectLocator)null, (ObjectLocator)null, object, strategy);
   }

   public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
      int currentHashCode = 1;
      String theValue = this.getValue();
      int currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "value", theValue), currentHashCode, theValue);
      LangageType theLang = this.getLang();
      currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "lang", theLang), currentHashCode, theLang);
      return currentHashCode;
   }

   public int hashCode() {
      HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
      return this.hashCode((ObjectLocator)null, strategy);
   }
}
