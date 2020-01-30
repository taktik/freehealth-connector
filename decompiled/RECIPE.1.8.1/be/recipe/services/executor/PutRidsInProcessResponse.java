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
   name = "putRidsInProcessResponse",
   propOrder = {"putRidsInProcessResultSealed"}
)
@XmlRootElement(
   name = "putRidsInProcessResponse"
)
public class PutRidsInProcessResponse implements Equals, HashCode, ToString {
   @XmlElement(
      name = "PutRidsInProcessResultSealed"
   )
   protected byte[] putRidsInProcessResultSealed;

   public byte[] getPutRidsInProcessResultSealed() {
      return this.putRidsInProcessResultSealed;
   }

   public void setPutRidsInProcessResultSealed(byte[] value) {
      this.putRidsInProcessResultSealed = value;
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
      byte[] thePutRidsInProcessResultSealed = this.getPutRidsInProcessResultSealed();
      strategy.appendField(locator, this, "putRidsInProcessResultSealed", buffer, thePutRidsInProcessResultSealed);
      return buffer;
   }

   public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
      if (!(object instanceof PutRidsInProcessResponse)) {
         return false;
      } else if (this == object) {
         return true;
      } else {
         PutRidsInProcessResponse that = (PutRidsInProcessResponse)object;
         byte[] lhsPutRidsInProcessResultSealed = this.getPutRidsInProcessResultSealed();
         byte[] rhsPutRidsInProcessResultSealed = that.getPutRidsInProcessResultSealed();
         return strategy.equals(LocatorUtils.property(thisLocator, "putRidsInProcessResultSealed", lhsPutRidsInProcessResultSealed), LocatorUtils.property(thatLocator, "putRidsInProcessResultSealed", rhsPutRidsInProcessResultSealed), lhsPutRidsInProcessResultSealed, rhsPutRidsInProcessResultSealed);
      }
   }

   public boolean equals(Object object) {
      EqualsStrategy strategy = JAXBEqualsStrategy.INSTANCE;
      return this.equals((ObjectLocator)null, (ObjectLocator)null, object, strategy);
   }

   public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
      int currentHashCode = 1;
      byte[] thePutRidsInProcessResultSealed = this.getPutRidsInProcessResultSealed();
      int currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "putRidsInProcessResultSealed", thePutRidsInProcessResultSealed), currentHashCode, thePutRidsInProcessResultSealed);
      return currentHashCode;
   }

   public int hashCode() {
      HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
      return this.hashCode((ObjectLocator)null, strategy);
   }
}
