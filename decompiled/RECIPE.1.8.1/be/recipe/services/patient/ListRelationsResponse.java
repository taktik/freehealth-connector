package be.recipe.services.patient;

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
   name = "listRelationsResponse",
   propOrder = {"listRelationsResultSealed"}
)
@XmlRootElement(
   name = "listRelationsResponse"
)
public class ListRelationsResponse implements Equals, HashCode, ToString {
   @XmlElement(
      name = "ListRelationsResultSealed"
   )
   protected byte[] listRelationsResultSealed;

   public byte[] getListRelationsResultSealed() {
      return this.listRelationsResultSealed;
   }

   public void setListRelationsResultSealed(byte[] value) {
      this.listRelationsResultSealed = value;
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
      byte[] theListRelationsResultSealed = this.getListRelationsResultSealed();
      strategy.appendField(locator, this, "listRelationsResultSealed", buffer, theListRelationsResultSealed);
      return buffer;
   }

   public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
      if (!(object instanceof ListRelationsResponse)) {
         return false;
      } else if (this == object) {
         return true;
      } else {
         ListRelationsResponse that = (ListRelationsResponse)object;
         byte[] lhsListRelationsResultSealed = this.getListRelationsResultSealed();
         byte[] rhsListRelationsResultSealed = that.getListRelationsResultSealed();
         return strategy.equals(LocatorUtils.property(thisLocator, "listRelationsResultSealed", lhsListRelationsResultSealed), LocatorUtils.property(thatLocator, "listRelationsResultSealed", rhsListRelationsResultSealed), lhsListRelationsResultSealed, rhsListRelationsResultSealed);
      }
   }

   public boolean equals(Object object) {
      EqualsStrategy strategy = JAXBEqualsStrategy.INSTANCE;
      return this.equals((ObjectLocator)null, (ObjectLocator)null, object, strategy);
   }

   public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
      int currentHashCode = 1;
      byte[] theListRelationsResultSealed = this.getListRelationsResultSealed();
      int currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "listRelationsResultSealed", theListRelationsResultSealed), currentHashCode, theListRelationsResultSealed);
      return currentHashCode;
   }

   public int hashCode() {
      HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
      return this.hashCode((ObjectLocator)null, strategy);
   }
}
