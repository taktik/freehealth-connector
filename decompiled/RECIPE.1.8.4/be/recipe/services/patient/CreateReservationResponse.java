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
   name = "createReservationResponse",
   propOrder = {"createReservationResultSealed"}
)
@XmlRootElement(
   name = "createReservationResponse"
)
public class CreateReservationResponse implements Equals, HashCode, ToString {
   @XmlElement(
      name = "CreateReservationResultSealed"
   )
   protected byte[] createReservationResultSealed;

   public byte[] getCreateReservationResultSealed() {
      return this.createReservationResultSealed;
   }

   public void setCreateReservationResultSealed(byte[] value) {
      this.createReservationResultSealed = value;
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
      byte[] theCreateReservationResultSealed = this.getCreateReservationResultSealed();
      strategy.appendField(locator, this, "createReservationResultSealed", buffer, theCreateReservationResultSealed);
      return buffer;
   }

   public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
      if (!(object instanceof CreateReservationResponse)) {
         return false;
      } else if (this == object) {
         return true;
      } else {
         CreateReservationResponse that = (CreateReservationResponse)object;
         byte[] lhsCreateReservationResultSealed = this.getCreateReservationResultSealed();
         byte[] rhsCreateReservationResultSealed = that.getCreateReservationResultSealed();
         return strategy.equals(LocatorUtils.property(thisLocator, "createReservationResultSealed", lhsCreateReservationResultSealed), LocatorUtils.property(thatLocator, "createReservationResultSealed", rhsCreateReservationResultSealed), lhsCreateReservationResultSealed, rhsCreateReservationResultSealed);
      }
   }

   public boolean equals(Object object) {
      EqualsStrategy strategy = JAXBEqualsStrategy.INSTANCE;
      return this.equals((ObjectLocator)null, (ObjectLocator)null, object, strategy);
   }

   public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
      int currentHashCode = 1;
      byte[] theCreateReservationResultSealed = this.getCreateReservationResultSealed();
      int currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "createReservationResultSealed", theCreateReservationResultSealed), currentHashCode, theCreateReservationResultSealed);
      return currentHashCode;
   }

   public int hashCode() {
      HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
      return this.hashCode((ObjectLocator)null, strategy);
   }
}
