package be.recipe.services.prescriber;

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
   name = "validationPropertiesResponse",
   propOrder = {"validationPropertiesResultSealed"}
)
@XmlRootElement(
   name = "validationPropertiesResponse"
)
public class ValidationPropertiesResponse implements Equals, HashCode, ToString {
   @XmlElement(
      name = "ValidationPropertiesResultSealed"
   )
   protected byte[] validationPropertiesResultSealed;

   public byte[] getValidationPropertiesResultSealed() {
      return this.validationPropertiesResultSealed;
   }

   public void setValidationPropertiesResultSealed(byte[] value) {
      this.validationPropertiesResultSealed = value;
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
      byte[] theValidationPropertiesResultSealed = this.getValidationPropertiesResultSealed();
      strategy.appendField(locator, this, "validationPropertiesResultSealed", buffer, theValidationPropertiesResultSealed);
      return buffer;
   }

   public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
      if (!(object instanceof ValidationPropertiesResponse)) {
         return false;
      } else if (this == object) {
         return true;
      } else {
         ValidationPropertiesResponse that = (ValidationPropertiesResponse)object;
         byte[] lhsValidationPropertiesResultSealed = this.getValidationPropertiesResultSealed();
         byte[] rhsValidationPropertiesResultSealed = that.getValidationPropertiesResultSealed();
         return strategy.equals(LocatorUtils.property(thisLocator, "validationPropertiesResultSealed", lhsValidationPropertiesResultSealed), LocatorUtils.property(thatLocator, "validationPropertiesResultSealed", rhsValidationPropertiesResultSealed), lhsValidationPropertiesResultSealed, rhsValidationPropertiesResultSealed);
      }
   }

   public boolean equals(Object object) {
      EqualsStrategy strategy = JAXBEqualsStrategy.INSTANCE;
      return this.equals((ObjectLocator)null, (ObjectLocator)null, object, strategy);
   }

   public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
      int currentHashCode = 1;
      byte[] theValidationPropertiesResultSealed = this.getValidationPropertiesResultSealed();
      int currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "validationPropertiesResultSealed", theValidationPropertiesResultSealed), currentHashCode, theValidationPropertiesResultSealed);
      return currentHashCode;
   }

   public int hashCode() {
      HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
      return this.hashCode((ObjectLocator)null, strategy);
   }
}
