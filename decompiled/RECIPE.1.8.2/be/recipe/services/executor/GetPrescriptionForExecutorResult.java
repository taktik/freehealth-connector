package be.recipe.services.executor;

import be.recipe.services.core.ResponseType;
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
   name = "getPrescriptionForExecutorResult",
   propOrder = {"getPrescriptionForExecutorResultSealed", "getPrescriptionForExecutorUnsealedResultPart"}
)
@XmlRootElement(
   name = "getPrescriptionForExecutorResult"
)
public class GetPrescriptionForExecutorResult extends ResponseType implements Equals, HashCode, ToString {
   protected byte[] getPrescriptionForExecutorResultSealed;
   protected GetPrescriptionForExecutorUnsealedResultPart getPrescriptionForExecutorUnsealedResultPart;

   public byte[] getGetPrescriptionForExecutorResultSealed() {
      return this.getPrescriptionForExecutorResultSealed;
   }

   public void setGetPrescriptionForExecutorResultSealed(byte[] value) {
      this.getPrescriptionForExecutorResultSealed = value;
   }

   public GetPrescriptionForExecutorUnsealedResultPart getGetPrescriptionForExecutorUnsealedResultPart() {
      return this.getPrescriptionForExecutorUnsealedResultPart;
   }

   public void setGetPrescriptionForExecutorUnsealedResultPart(GetPrescriptionForExecutorUnsealedResultPart value) {
      this.getPrescriptionForExecutorUnsealedResultPart = value;
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
      byte[] theGetPrescriptionForExecutorResultSealed = this.getGetPrescriptionForExecutorResultSealed();
      strategy.appendField(locator, this, "getPrescriptionForExecutorResultSealed", buffer, theGetPrescriptionForExecutorResultSealed);
      GetPrescriptionForExecutorUnsealedResultPart theGetPrescriptionForExecutorUnsealedResultPart = this.getGetPrescriptionForExecutorUnsealedResultPart();
      strategy.appendField(locator, this, "getPrescriptionForExecutorUnsealedResultPart", buffer, theGetPrescriptionForExecutorUnsealedResultPart);
      return buffer;
   }

   public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
      if (!(object instanceof GetPrescriptionForExecutorResult)) {
         return false;
      } else if (this == object) {
         return true;
      } else if (!super.equals(thisLocator, thatLocator, object, strategy)) {
         return false;
      } else {
         GetPrescriptionForExecutorResult that = (GetPrescriptionForExecutorResult)object;
         byte[] lhsGetPrescriptionForExecutorResultSealed = this.getGetPrescriptionForExecutorResultSealed();
         byte[] rhsGetPrescriptionForExecutorResultSealed = that.getGetPrescriptionForExecutorResultSealed();
         if (!strategy.equals(LocatorUtils.property(thisLocator, "getPrescriptionForExecutorResultSealed", lhsGetPrescriptionForExecutorResultSealed), LocatorUtils.property(thatLocator, "getPrescriptionForExecutorResultSealed", rhsGetPrescriptionForExecutorResultSealed), lhsGetPrescriptionForExecutorResultSealed, rhsGetPrescriptionForExecutorResultSealed)) {
            return false;
         } else {
            GetPrescriptionForExecutorUnsealedResultPart lhsGetPrescriptionForExecutorUnsealedResultPart = this.getGetPrescriptionForExecutorUnsealedResultPart();
            GetPrescriptionForExecutorUnsealedResultPart rhsGetPrescriptionForExecutorUnsealedResultPart = that.getGetPrescriptionForExecutorUnsealedResultPart();
            return strategy.equals(LocatorUtils.property(thisLocator, "getPrescriptionForExecutorUnsealedResultPart", lhsGetPrescriptionForExecutorUnsealedResultPart), LocatorUtils.property(thatLocator, "getPrescriptionForExecutorUnsealedResultPart", rhsGetPrescriptionForExecutorUnsealedResultPart), lhsGetPrescriptionForExecutorUnsealedResultPart, rhsGetPrescriptionForExecutorUnsealedResultPart);
         }
      }
   }

   public boolean equals(Object object) {
      EqualsStrategy strategy = JAXBEqualsStrategy.INSTANCE;
      return this.equals((ObjectLocator)null, (ObjectLocator)null, object, strategy);
   }

   public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
      int currentHashCode = super.hashCode(locator, strategy);
      byte[] theGetPrescriptionForExecutorResultSealed = this.getGetPrescriptionForExecutorResultSealed();
      currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "getPrescriptionForExecutorResultSealed", theGetPrescriptionForExecutorResultSealed), currentHashCode, theGetPrescriptionForExecutorResultSealed);
      GetPrescriptionForExecutorUnsealedResultPart theGetPrescriptionForExecutorUnsealedResultPart = this.getGetPrescriptionForExecutorUnsealedResultPart();
      currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "getPrescriptionForExecutorUnsealedResultPart", theGetPrescriptionForExecutorUnsealedResultPart), currentHashCode, theGetPrescriptionForExecutorUnsealedResultPart);
      return currentHashCode;
   }

   public int hashCode() {
      HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
      return this.hashCode((ObjectLocator)null, strategy);
   }
}
