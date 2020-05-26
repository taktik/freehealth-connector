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
   name = "revokeRelationResponse",
   propOrder = {"revokeRelationResultSealed"}
)
@XmlRootElement(
   name = "revokeRelationResponse"
)
public class RevokeRelationResponse implements Equals, HashCode, ToString {
   @XmlElement(
      name = "RevokeRelationResultSealed"
   )
   protected byte[] revokeRelationResultSealed;

   public byte[] getRevokeRelationResultSealed() {
      return this.revokeRelationResultSealed;
   }

   public void setRevokeRelationResultSealed(byte[] value) {
      this.revokeRelationResultSealed = value;
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
      byte[] theRevokeRelationResultSealed = this.getRevokeRelationResultSealed();
      strategy.appendField(locator, this, "revokeRelationResultSealed", buffer, theRevokeRelationResultSealed);
      return buffer;
   }

   public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
      if (!(object instanceof RevokeRelationResponse)) {
         return false;
      } else if (this == object) {
         return true;
      } else {
         RevokeRelationResponse that = (RevokeRelationResponse)object;
         byte[] lhsRevokeRelationResultSealed = this.getRevokeRelationResultSealed();
         byte[] rhsRevokeRelationResultSealed = that.getRevokeRelationResultSealed();
         return strategy.equals(LocatorUtils.property(thisLocator, "revokeRelationResultSealed", lhsRevokeRelationResultSealed), LocatorUtils.property(thatLocator, "revokeRelationResultSealed", rhsRevokeRelationResultSealed), lhsRevokeRelationResultSealed, rhsRevokeRelationResultSealed);
      }
   }

   public boolean equals(Object object) {
      EqualsStrategy strategy = JAXBEqualsStrategy.INSTANCE;
      return this.equals((ObjectLocator)null, (ObjectLocator)null, object, strategy);
   }

   public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
      int currentHashCode = 1;
      byte[] theRevokeRelationResultSealed = this.getRevokeRelationResultSealed();
      int currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "revokeRelationResultSealed", theRevokeRelationResultSealed), currentHashCode, theRevokeRelationResultSealed);
      return currentHashCode;
   }

   public int hashCode() {
      HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
      return this.hashCode((ObjectLocator)null, strategy);
   }
}
