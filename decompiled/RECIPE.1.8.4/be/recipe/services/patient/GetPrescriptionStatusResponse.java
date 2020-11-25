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
   name = "getPrescriptionStatusResponse",
   propOrder = {"getPrescriptionStatusResultSealed"}
)
@XmlRootElement(
   name = "getPrescriptionStatusResponse"
)
public class GetPrescriptionStatusResponse implements Equals, HashCode, ToString {
   @XmlElement(
      name = "GetPrescriptionStatusResultSealed"
   )
   protected byte[] getPrescriptionStatusResultSealed;

   public byte[] getGetPrescriptionStatusResultSealed() {
      return this.getPrescriptionStatusResultSealed;
   }

   public void setGetPrescriptionStatusResultSealed(byte[] value) {
      this.getPrescriptionStatusResultSealed = value;
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
      byte[] theGetPrescriptionStatusResultSealed = this.getGetPrescriptionStatusResultSealed();
      strategy.appendField(locator, this, "getPrescriptionStatusResultSealed", buffer, theGetPrescriptionStatusResultSealed);
      return buffer;
   }

   public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
      if (!(object instanceof GetPrescriptionStatusResponse)) {
         return false;
      } else if (this == object) {
         return true;
      } else {
         GetPrescriptionStatusResponse that = (GetPrescriptionStatusResponse)object;
         byte[] lhsGetPrescriptionStatusResultSealed = this.getGetPrescriptionStatusResultSealed();
         byte[] rhsGetPrescriptionStatusResultSealed = that.getGetPrescriptionStatusResultSealed();
         return strategy.equals(LocatorUtils.property(thisLocator, "getPrescriptionStatusResultSealed", lhsGetPrescriptionStatusResultSealed), LocatorUtils.property(thatLocator, "getPrescriptionStatusResultSealed", rhsGetPrescriptionStatusResultSealed), lhsGetPrescriptionStatusResultSealed, rhsGetPrescriptionStatusResultSealed);
      }
   }

   public boolean equals(Object object) {
      EqualsStrategy strategy = JAXBEqualsStrategy.INSTANCE;
      return this.equals((ObjectLocator)null, (ObjectLocator)null, object, strategy);
   }

   public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
      int currentHashCode = 1;
      byte[] theGetPrescriptionStatusResultSealed = this.getGetPrescriptionStatusResultSealed();
      int currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "getPrescriptionStatusResultSealed", theGetPrescriptionStatusResultSealed), currentHashCode, theGetPrescriptionStatusResultSealed);
      return currentHashCode;
   }

   public int hashCode() {
      HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
      return this.hashCode((ObjectLocator)null, strategy);
   }
}
