package be.recipe.services.executor;

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
   name = "listNotificationsResponse",
   propOrder = {"listNotificationsResultSealed"}
)
@XmlRootElement(
   name = "listNotificationsResponse"
)
public class ListNotificationsResponse implements Equals, HashCode, ToString {
   @XmlElement(
      name = "ListNotificationsResultSealed"
   )
   protected byte[] listNotificationsResultSealed;

   public byte[] getListNotificationsResultSealed() {
      return this.listNotificationsResultSealed;
   }

   public void setListNotificationsResultSealed(byte[] value) {
      this.listNotificationsResultSealed = value;
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
      byte[] theListNotificationsResultSealed = this.getListNotificationsResultSealed();
      strategy.appendField(locator, this, "listNotificationsResultSealed", buffer, theListNotificationsResultSealed);
      return buffer;
   }

   public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
      if (!(object instanceof ListNotificationsResponse)) {
         return false;
      } else if (this == object) {
         return true;
      } else {
         ListNotificationsResponse that = (ListNotificationsResponse)object;
         byte[] lhsListNotificationsResultSealed = this.getListNotificationsResultSealed();
         byte[] rhsListNotificationsResultSealed = that.getListNotificationsResultSealed();
         return strategy.equals(LocatorUtils.property(thisLocator, "listNotificationsResultSealed", lhsListNotificationsResultSealed), LocatorUtils.property(thatLocator, "listNotificationsResultSealed", rhsListNotificationsResultSealed), lhsListNotificationsResultSealed, rhsListNotificationsResultSealed);
      }
   }

   public boolean equals(Object object) {
      EqualsStrategy strategy = JAXBEqualsStrategy.INSTANCE;
      return this.equals((ObjectLocator)null, (ObjectLocator)null, object, strategy);
   }

   public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
      int currentHashCode = 1;
      byte[] theListNotificationsResultSealed = this.getListNotificationsResultSealed();
      int currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "listNotificationsResultSealed", theListNotificationsResultSealed), currentHashCode, theListNotificationsResultSealed);
      return currentHashCode;
   }

   public int hashCode() {
      HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
      return this.hashCode((ObjectLocator)null, strategy);
   }
}
