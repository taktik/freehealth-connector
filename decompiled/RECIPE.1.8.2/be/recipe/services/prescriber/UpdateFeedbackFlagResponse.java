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
   name = "updateFeedbackFlagResponse",
   propOrder = {"updateFeedbackFlagResultSealed"}
)
@XmlRootElement(
   name = "updateFeedbackFlagResponse"
)
public class UpdateFeedbackFlagResponse implements Equals, HashCode, ToString {
   @XmlElement(
      name = "UpdateFeedbackFlagResultSealed"
   )
   protected byte[] updateFeedbackFlagResultSealed;

   public byte[] getUpdateFeedbackFlagResultSealed() {
      return this.updateFeedbackFlagResultSealed;
   }

   public void setUpdateFeedbackFlagResultSealed(byte[] value) {
      this.updateFeedbackFlagResultSealed = value;
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
      byte[] theUpdateFeedbackFlagResultSealed = this.getUpdateFeedbackFlagResultSealed();
      strategy.appendField(locator, this, "updateFeedbackFlagResultSealed", buffer, theUpdateFeedbackFlagResultSealed);
      return buffer;
   }

   public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
      if (!(object instanceof UpdateFeedbackFlagResponse)) {
         return false;
      } else if (this == object) {
         return true;
      } else {
         UpdateFeedbackFlagResponse that = (UpdateFeedbackFlagResponse)object;
         byte[] lhsUpdateFeedbackFlagResultSealed = this.getUpdateFeedbackFlagResultSealed();
         byte[] rhsUpdateFeedbackFlagResultSealed = that.getUpdateFeedbackFlagResultSealed();
         return strategy.equals(LocatorUtils.property(thisLocator, "updateFeedbackFlagResultSealed", lhsUpdateFeedbackFlagResultSealed), LocatorUtils.property(thatLocator, "updateFeedbackFlagResultSealed", rhsUpdateFeedbackFlagResultSealed), lhsUpdateFeedbackFlagResultSealed, rhsUpdateFeedbackFlagResultSealed);
      }
   }

   public boolean equals(Object object) {
      EqualsStrategy strategy = JAXBEqualsStrategy.INSTANCE;
      return this.equals((ObjectLocator)null, (ObjectLocator)null, object, strategy);
   }

   public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
      int currentHashCode = 1;
      byte[] theUpdateFeedbackFlagResultSealed = this.getUpdateFeedbackFlagResultSealed();
      int currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "updateFeedbackFlagResultSealed", theUpdateFeedbackFlagResultSealed), currentHashCode, theUpdateFeedbackFlagResultSealed);
      return currentHashCode;
   }

   public int hashCode() {
      HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
      return this.hashCode((ObjectLocator)null, strategy);
   }
}
