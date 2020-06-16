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
   name = "markAsDeliveredResponse",
   propOrder = {"markAsDeliveredResultSealed"}
)
@XmlRootElement(
   name = "markAsDeliveredResponse"
)
public class MarkAsDeliveredResponse implements Equals, HashCode, ToString {
   @XmlElement(
      name = "MarkAsDeliveredResultSealed"
   )
   protected byte[] markAsDeliveredResultSealed;

   public byte[] getMarkAsDeliveredResultSealed() {
      return this.markAsDeliveredResultSealed;
   }

   public void setMarkAsDeliveredResultSealed(byte[] value) {
      this.markAsDeliveredResultSealed = value;
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
      byte[] theMarkAsDeliveredResultSealed = this.getMarkAsDeliveredResultSealed();
      strategy.appendField(locator, this, "markAsDeliveredResultSealed", buffer, theMarkAsDeliveredResultSealed);
      return buffer;
   }

   public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
      if (!(object instanceof MarkAsDeliveredResponse)) {
         return false;
      } else if (this == object) {
         return true;
      } else {
         MarkAsDeliveredResponse that = (MarkAsDeliveredResponse)object;
         byte[] lhsMarkAsDeliveredResultSealed = this.getMarkAsDeliveredResultSealed();
         byte[] rhsMarkAsDeliveredResultSealed = that.getMarkAsDeliveredResultSealed();
         return strategy.equals(LocatorUtils.property(thisLocator, "markAsDeliveredResultSealed", lhsMarkAsDeliveredResultSealed), LocatorUtils.property(thatLocator, "markAsDeliveredResultSealed", rhsMarkAsDeliveredResultSealed), lhsMarkAsDeliveredResultSealed, rhsMarkAsDeliveredResultSealed);
      }
   }

   public boolean equals(Object object) {
      EqualsStrategy strategy = JAXBEqualsStrategy.INSTANCE;
      return this.equals((ObjectLocator)null, (ObjectLocator)null, object, strategy);
   }

   public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
      int currentHashCode = 1;
      byte[] theMarkAsDeliveredResultSealed = this.getMarkAsDeliveredResultSealed();
      int currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "markAsDeliveredResultSealed", theMarkAsDeliveredResultSealed), currentHashCode, theMarkAsDeliveredResultSealed);
      return currentHashCode;
   }

   public int hashCode() {
      HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
      return this.hashCode((ObjectLocator)null, strategy);
   }
}
