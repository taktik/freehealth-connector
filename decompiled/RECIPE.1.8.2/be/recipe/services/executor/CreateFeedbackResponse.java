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
   name = "createFeedbackResponse",
   propOrder = {"createFeedbackResultSealed"}
)
@XmlRootElement(
   name = "createFeedbackResponse"
)
public class CreateFeedbackResponse implements Equals, HashCode, ToString {
   @XmlElement(
      name = "CreateFeedbackResultSealed"
   )
   protected byte[] createFeedbackResultSealed;

   public byte[] getCreateFeedbackResultSealed() {
      return this.createFeedbackResultSealed;
   }

   public void setCreateFeedbackResultSealed(byte[] value) {
      this.createFeedbackResultSealed = value;
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
      byte[] theCreateFeedbackResultSealed = this.getCreateFeedbackResultSealed();
      strategy.appendField(locator, this, "createFeedbackResultSealed", buffer, theCreateFeedbackResultSealed);
      return buffer;
   }

   public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
      if (!(object instanceof CreateFeedbackResponse)) {
         return false;
      } else if (this == object) {
         return true;
      } else {
         CreateFeedbackResponse that = (CreateFeedbackResponse)object;
         byte[] lhsCreateFeedbackResultSealed = this.getCreateFeedbackResultSealed();
         byte[] rhsCreateFeedbackResultSealed = that.getCreateFeedbackResultSealed();
         return strategy.equals(LocatorUtils.property(thisLocator, "createFeedbackResultSealed", lhsCreateFeedbackResultSealed), LocatorUtils.property(thatLocator, "createFeedbackResultSealed", rhsCreateFeedbackResultSealed), lhsCreateFeedbackResultSealed, rhsCreateFeedbackResultSealed);
      }
   }

   public boolean equals(Object object) {
      EqualsStrategy strategy = JAXBEqualsStrategy.INSTANCE;
      return this.equals((ObjectLocator)null, (ObjectLocator)null, object, strategy);
   }

   public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
      int currentHashCode = 1;
      byte[] theCreateFeedbackResultSealed = this.getCreateFeedbackResultSealed();
      int currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "createFeedbackResultSealed", theCreateFeedbackResultSealed), currentHashCode, theCreateFeedbackResultSealed);
      return currentHashCode;
   }

   public int hashCode() {
      HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
      return this.hashCode((ObjectLocator)null, strategy);
   }
}
