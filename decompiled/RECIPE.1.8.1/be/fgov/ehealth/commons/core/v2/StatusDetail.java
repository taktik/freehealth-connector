package be.fgov.ehealth.commons.core.v2;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
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

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "StatusDetailType",
   propOrder = {"anies"}
)
@XmlRootElement(
   name = "StatusDetail"
)
public class StatusDetail implements Equals, HashCode, ToString {
   @XmlAnyElement(
      lax = true
   )
   protected List<Object> anies;

   public List<Object> getAnies() {
      if (this.anies == null) {
         this.anies = new ArrayList();
      }

      return this.anies;
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
      List<Object> theAnies = this.anies != null && !this.anies.isEmpty() ? this.getAnies() : null;
      strategy.appendField(locator, this, "anies", buffer, theAnies);
      return buffer;
   }

   public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
      if (!(object instanceof StatusDetail)) {
         return false;
      } else if (this == object) {
         return true;
      } else {
         StatusDetail that = (StatusDetail)object;
         List<Object> lhsAnies = this.anies != null && !this.anies.isEmpty() ? this.getAnies() : null;
         List<Object> rhsAnies = that.anies != null && !that.anies.isEmpty() ? that.getAnies() : null;
         return strategy.equals(LocatorUtils.property(thisLocator, "anies", lhsAnies), LocatorUtils.property(thatLocator, "anies", rhsAnies), lhsAnies, rhsAnies);
      }
   }

   public boolean equals(Object object) {
      EqualsStrategy strategy = JAXBEqualsStrategy.INSTANCE;
      return this.equals((ObjectLocator)null, (ObjectLocator)null, object, strategy);
   }

   public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
      int currentHashCode = 1;
      List<Object> theAnies = this.anies != null && !this.anies.isEmpty() ? this.getAnies() : null;
      int currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "anies", theAnies), currentHashCode, theAnies);
      return currentHashCode;
   }

   public int hashCode() {
      HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
      return this.hashCode((ObjectLocator)null, strategy);
   }
}
