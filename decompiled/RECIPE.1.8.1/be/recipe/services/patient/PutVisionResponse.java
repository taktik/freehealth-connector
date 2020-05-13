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
   name = "putVisionResponse",
   propOrder = {"putVisionResultSealed"}
)
@XmlRootElement(
   name = "putVisionResponse"
)
public class PutVisionResponse implements Equals, HashCode, ToString {
   @XmlElement(
      name = "PutVisionResultSealed"
   )
   protected byte[] putVisionResultSealed;

   public byte[] getPutVisionResultSealed() {
      return this.putVisionResultSealed;
   }

   public void setPutVisionResultSealed(byte[] value) {
      this.putVisionResultSealed = value;
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
      byte[] thePutVisionResultSealed = this.getPutVisionResultSealed();
      strategy.appendField(locator, this, "putVisionResultSealed", buffer, thePutVisionResultSealed);
      return buffer;
   }

   public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
      if (!(object instanceof PutVisionResponse)) {
         return false;
      } else if (this == object) {
         return true;
      } else {
         PutVisionResponse that = (PutVisionResponse)object;
         byte[] lhsPutVisionResultSealed = this.getPutVisionResultSealed();
         byte[] rhsPutVisionResultSealed = that.getPutVisionResultSealed();
         return strategy.equals(LocatorUtils.property(thisLocator, "putVisionResultSealed", lhsPutVisionResultSealed), LocatorUtils.property(thatLocator, "putVisionResultSealed", rhsPutVisionResultSealed), lhsPutVisionResultSealed, rhsPutVisionResultSealed);
      }
   }

   public boolean equals(Object object) {
      EqualsStrategy strategy = JAXBEqualsStrategy.INSTANCE;
      return this.equals((ObjectLocator)null, (ObjectLocator)null, object, strategy);
   }

   public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
      int currentHashCode = 1;
      byte[] thePutVisionResultSealed = this.getPutVisionResultSealed();
      int currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "putVisionResultSealed", thePutVisionResultSealed), currentHashCode, thePutVisionResultSealed);
      return currentHashCode;
   }

   public int hashCode() {
      HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
      return this.hashCode((ObjectLocator)null, strategy);
   }
}
