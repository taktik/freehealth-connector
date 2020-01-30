package be.recipe.services.executor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
   name = "markAsUnDeliveredResponse",
   propOrder = {"markAsUnDeliveredResultSealed"}
)
@XmlRootElement(
   name = "markAsUnDeliveredResponse"
)
public class MarkAsUnDeliveredResponse implements Equals, HashCode, ToString {
   protected byte[] markAsUnDeliveredResultSealed;

   public byte[] getMarkAsUnDeliveredResultSealed() {
      return this.markAsUnDeliveredResultSealed;
   }

   public void setMarkAsUnDeliveredResultSealed(byte[] value) {
      this.markAsUnDeliveredResultSealed = value;
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
      byte[] theMarkAsUnDeliveredResultSealed = this.getMarkAsUnDeliveredResultSealed();
      strategy.appendField(locator, this, "markAsUnDeliveredResultSealed", buffer, theMarkAsUnDeliveredResultSealed);
      return buffer;
   }

   public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
      if (!(object instanceof MarkAsUnDeliveredResponse)) {
         return false;
      } else if (this == object) {
         return true;
      } else {
         MarkAsUnDeliveredResponse that = (MarkAsUnDeliveredResponse)object;
         byte[] lhsMarkAsUnDeliveredResultSealed = this.getMarkAsUnDeliveredResultSealed();
         byte[] rhsMarkAsUnDeliveredResultSealed = that.getMarkAsUnDeliveredResultSealed();
         return strategy.equals(LocatorUtils.property(thisLocator, "markAsUnDeliveredResultSealed", lhsMarkAsUnDeliveredResultSealed), LocatorUtils.property(thatLocator, "markAsUnDeliveredResultSealed", rhsMarkAsUnDeliveredResultSealed), lhsMarkAsUnDeliveredResultSealed, rhsMarkAsUnDeliveredResultSealed);
      }
   }

   public boolean equals(Object object) {
      EqualsStrategy strategy = JAXBEqualsStrategy.INSTANCE;
      return this.equals((ObjectLocator)null, (ObjectLocator)null, object, strategy);
   }

   public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
      int currentHashCode = 1;
      byte[] theMarkAsUnDeliveredResultSealed = this.getMarkAsUnDeliveredResultSealed();
      int currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "markAsUnDeliveredResultSealed", theMarkAsUnDeliveredResultSealed), currentHashCode, theMarkAsUnDeliveredResultSealed);
      return currentHashCode;
   }

   public int hashCode() {
      HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
      return this.hashCode((ObjectLocator)null, strategy);
   }
}
