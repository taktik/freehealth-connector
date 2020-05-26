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
   name = "listPatientPrescriptionResponse",
   propOrder = {"listPatientPrescriptionsResultSealed"}
)
@XmlRootElement(
   name = "listPatientPrescriptionResponse"
)
public class ListPatientPrescriptionResponse implements Equals, HashCode, ToString {
   @XmlElement(
      name = "ListPatientPrescriptionsResultSealed"
   )
   protected byte[] listPatientPrescriptionsResultSealed;

   public byte[] getListPatientPrescriptionsResultSealed() {
      return this.listPatientPrescriptionsResultSealed;
   }

   public void setListPatientPrescriptionsResultSealed(byte[] value) {
      this.listPatientPrescriptionsResultSealed = value;
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
      byte[] theListPatientPrescriptionsResultSealed = this.getListPatientPrescriptionsResultSealed();
      strategy.appendField(locator, this, "listPatientPrescriptionsResultSealed", buffer, theListPatientPrescriptionsResultSealed);
      return buffer;
   }

   public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
      if (!(object instanceof ListPatientPrescriptionResponse)) {
         return false;
      } else if (this == object) {
         return true;
      } else {
         ListPatientPrescriptionResponse that = (ListPatientPrescriptionResponse)object;
         byte[] lhsListPatientPrescriptionsResultSealed = this.getListPatientPrescriptionsResultSealed();
         byte[] rhsListPatientPrescriptionsResultSealed = that.getListPatientPrescriptionsResultSealed();
         return strategy.equals(LocatorUtils.property(thisLocator, "listPatientPrescriptionsResultSealed", lhsListPatientPrescriptionsResultSealed), LocatorUtils.property(thatLocator, "listPatientPrescriptionsResultSealed", rhsListPatientPrescriptionsResultSealed), lhsListPatientPrescriptionsResultSealed, rhsListPatientPrescriptionsResultSealed);
      }
   }

   public boolean equals(Object object) {
      EqualsStrategy strategy = JAXBEqualsStrategy.INSTANCE;
      return this.equals((ObjectLocator)null, (ObjectLocator)null, object, strategy);
   }

   public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
      int currentHashCode = 1;
      byte[] theListPatientPrescriptionsResultSealed = this.getListPatientPrescriptionsResultSealed();
      int currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "listPatientPrescriptionsResultSealed", theListPatientPrescriptionsResultSealed), currentHashCode, theListPatientPrescriptionsResultSealed);
      return currentHashCode;
   }

   public int hashCode() {
      HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
      return this.hashCode((ObjectLocator)null, strategy);
   }
}
