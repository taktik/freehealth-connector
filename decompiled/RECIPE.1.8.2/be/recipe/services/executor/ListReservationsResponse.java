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
   name = "listReservationsResponse",
   propOrder = {"listReservationsResultSealed"}
)
@XmlRootElement(
   name = "listReservationsResponse"
)
public class ListReservationsResponse implements Equals, HashCode, ToString {
   @XmlElement(
      name = "ListReservationsResultSealed"
   )
   protected byte[] listReservationsResultSealed;

   public byte[] getListReservationsResultSealed() {
      return this.listReservationsResultSealed;
   }

   public void setListReservationsResultSealed(byte[] value) {
      this.listReservationsResultSealed = value;
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
      byte[] theListReservationsResultSealed = this.getListReservationsResultSealed();
      strategy.appendField(locator, this, "listReservationsResultSealed", buffer, theListReservationsResultSealed);
      return buffer;
   }

   public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
      if (!(object instanceof ListReservationsResponse)) {
         return false;
      } else if (this == object) {
         return true;
      } else {
         ListReservationsResponse that = (ListReservationsResponse)object;
         byte[] lhsListReservationsResultSealed = this.getListReservationsResultSealed();
         byte[] rhsListReservationsResultSealed = that.getListReservationsResultSealed();
         return strategy.equals(LocatorUtils.property(thisLocator, "listReservationsResultSealed", lhsListReservationsResultSealed), LocatorUtils.property(thatLocator, "listReservationsResultSealed", rhsListReservationsResultSealed), lhsListReservationsResultSealed, rhsListReservationsResultSealed);
      }
   }

   public boolean equals(Object object) {
      EqualsStrategy strategy = JAXBEqualsStrategy.INSTANCE;
      return this.equals((ObjectLocator)null, (ObjectLocator)null, object, strategy);
   }

   public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
      int currentHashCode = 1;
      byte[] theListReservationsResultSealed = this.getListReservationsResultSealed();
      int currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "listReservationsResultSealed", theListReservationsResultSealed), currentHashCode, theListReservationsResultSealed);
      return currentHashCode;
   }

   public int hashCode() {
      HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
      return this.hashCode((ObjectLocator)null, strategy);
   }
}
