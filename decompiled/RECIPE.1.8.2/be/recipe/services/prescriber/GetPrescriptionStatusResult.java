package be.recipe.services.prescriber;

import be.recipe.services.core.PrescriptionStatus;
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
   name = "getPrescriptionStatusResult",
   propOrder = {"prescriptionStatus", "executorId"}
)
@XmlRootElement(
   name = "getPrescriptionStatusResult"
)
public class GetPrescriptionStatusResult extends ResponseType implements Equals, HashCode, ToString {
   protected PrescriptionStatus prescriptionStatus;
   protected String executorId;

   public PrescriptionStatus getPrescriptionStatus() {
      return this.prescriptionStatus;
   }

   public void setPrescriptionStatus(PrescriptionStatus value) {
      this.prescriptionStatus = value;
   }

   public String getExecutorId() {
      return this.executorId;
   }

   public void setExecutorId(String value) {
      this.executorId = value;
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
      PrescriptionStatus thePrescriptionStatus = this.getPrescriptionStatus();
      strategy.appendField(locator, this, "prescriptionStatus", buffer, thePrescriptionStatus);
      String theExecutorId = this.getExecutorId();
      strategy.appendField(locator, this, "executorId", buffer, theExecutorId);
      return buffer;
   }

   public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
      if (!(object instanceof GetPrescriptionStatusResult)) {
         return false;
      } else if (this == object) {
         return true;
      } else if (!super.equals(thisLocator, thatLocator, object, strategy)) {
         return false;
      } else {
         GetPrescriptionStatusResult that = (GetPrescriptionStatusResult)object;
         PrescriptionStatus lhsPrescriptionStatus = this.getPrescriptionStatus();
         PrescriptionStatus rhsPrescriptionStatus = that.getPrescriptionStatus();
         if (!strategy.equals(LocatorUtils.property(thisLocator, "prescriptionStatus", lhsPrescriptionStatus), LocatorUtils.property(thatLocator, "prescriptionStatus", rhsPrescriptionStatus), lhsPrescriptionStatus, rhsPrescriptionStatus)) {
            return false;
         } else {
            String lhsExecutorId = this.getExecutorId();
            String rhsExecutorId = that.getExecutorId();
            return strategy.equals(LocatorUtils.property(thisLocator, "executorId", lhsExecutorId), LocatorUtils.property(thatLocator, "executorId", rhsExecutorId), lhsExecutorId, rhsExecutorId);
         }
      }
   }

   public boolean equals(Object object) {
      EqualsStrategy strategy = JAXBEqualsStrategy.INSTANCE;
      return this.equals((ObjectLocator)null, (ObjectLocator)null, object, strategy);
   }

   public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
      int currentHashCode = super.hashCode(locator, strategy);
      PrescriptionStatus thePrescriptionStatus = this.getPrescriptionStatus();
      currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "prescriptionStatus", thePrescriptionStatus), currentHashCode, thePrescriptionStatus);
      String theExecutorId = this.getExecutorId();
      currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "executorId", theExecutorId), currentHashCode, theExecutorId);
      return currentHashCode;
   }

   public int hashCode() {
      HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
      return this.hashCode((ObjectLocator)null, strategy);
   }
}
