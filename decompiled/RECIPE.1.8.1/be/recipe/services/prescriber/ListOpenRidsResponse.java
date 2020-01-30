package be.recipe.services.prescriber;

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

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "listOpenRidsResponse",
   propOrder = {"listOpenRidsResultSealed"}
)
@XmlRootElement(
   name = "listOpenRidsResponse"
)
public class ListOpenRidsResponse implements Equals, HashCode, ToString {
   @XmlElement(
      name = "ListOpenRidsResultSealed"
   )
   protected byte[] listOpenRidsResultSealed;

   public byte[] getListOpenRidsResultSealed() {
      return this.listOpenRidsResultSealed;
   }

   public void setListOpenRidsResultSealed(byte[] value) {
      this.listOpenRidsResultSealed = value;
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
      byte[] theListOpenRidsResultSealed = this.getListOpenRidsResultSealed();
      strategy.appendField(locator, this, "listOpenRidsResultSealed", buffer, theListOpenRidsResultSealed);
      return buffer;
   }

   public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
      if (!(object instanceof ListOpenRidsResponse)) {
         return false;
      } else if (this == object) {
         return true;
      } else {
         ListOpenRidsResponse that = (ListOpenRidsResponse)object;
         byte[] lhsListOpenRidsResultSealed = this.getListOpenRidsResultSealed();
         byte[] rhsListOpenRidsResultSealed = that.getListOpenRidsResultSealed();
         return strategy.equals(LocatorUtils.property(thisLocator, "listOpenRidsResultSealed", lhsListOpenRidsResultSealed), LocatorUtils.property(thatLocator, "listOpenRidsResultSealed", rhsListOpenRidsResultSealed), lhsListOpenRidsResultSealed, rhsListOpenRidsResultSealed);
      }
   }

   public boolean equals(Object object) {
      EqualsStrategy strategy = JAXBEqualsStrategy.INSTANCE;
      return this.equals((ObjectLocator)null, (ObjectLocator)null, object, strategy);
   }

   public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
      int currentHashCode = 1;
      byte[] theListOpenRidsResultSealed = this.getListOpenRidsResultSealed();
      int currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "listOpenRidsResultSealed", theListOpenRidsResultSealed), currentHashCode, theListOpenRidsResultSealed);
      return currentHashCode;
   }

   public int hashCode() {
      HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
      return this.hashCode((ObjectLocator)null, strategy);
   }
}
