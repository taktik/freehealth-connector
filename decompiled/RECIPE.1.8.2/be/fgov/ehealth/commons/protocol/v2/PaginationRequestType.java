package be.fgov.ehealth.commons.protocol.v2;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
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
   name = "PaginationRequestType"
)
public class PaginationRequestType extends RequestType implements Equals, HashCode, ToString {
   @XmlAttribute(
      name = "Offset",
      required = true
   )
   protected int offset;
   @XmlAttribute(
      name = "MaxElements",
      required = true
   )
   protected int maxElements;

   public int getOffset() {
      return this.offset;
   }

   public void setOffset(int value) {
      this.offset = value;
   }

   public int getMaxElements() {
      return this.maxElements;
   }

   public void setMaxElements(int value) {
      this.maxElements = value;
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
      super.appendFields(locator, buffer, strategy);
      int theMaxElements = this.getOffset();
      strategy.appendField(locator, this, "offset", buffer, theMaxElements);
      theMaxElements = this.getMaxElements();
      strategy.appendField(locator, this, "maxElements", buffer, theMaxElements);
      return buffer;
   }

   public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
      if (!(object instanceof PaginationRequestType)) {
         return false;
      } else if (this == object) {
         return true;
      } else if (!super.equals(thisLocator, thatLocator, object, strategy)) {
         return false;
      } else {
         PaginationRequestType that = (PaginationRequestType)object;
         int lhsMaxElements = this.getOffset();
         int rhsMaxElements = that.getOffset();
         if (!strategy.equals(LocatorUtils.property(thisLocator, "offset", lhsMaxElements), LocatorUtils.property(thatLocator, "offset", rhsMaxElements), lhsMaxElements, rhsMaxElements)) {
            return false;
         } else {
            lhsMaxElements = this.getMaxElements();
            rhsMaxElements = that.getMaxElements();
            return strategy.equals(LocatorUtils.property(thisLocator, "maxElements", lhsMaxElements), LocatorUtils.property(thatLocator, "maxElements", rhsMaxElements), lhsMaxElements, rhsMaxElements);
         }
      }
   }

   public boolean equals(Object object) {
      EqualsStrategy strategy = JAXBEqualsStrategy.INSTANCE;
      return this.equals((ObjectLocator)null, (ObjectLocator)null, object, strategy);
   }

   public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
      int currentHashCode = super.hashCode(locator, strategy);
      int theMaxElements = this.getOffset();
      currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "offset", theMaxElements), currentHashCode, theMaxElements);
      theMaxElements = this.getMaxElements();
      currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "maxElements", theMaxElements), currentHashCode, theMaxElements);
      return currentHashCode;
   }

   public int hashCode() {
      HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
      return this.hashCode((ObjectLocator)null, strategy);
   }
}
