package be.recipe.services.executor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
   name = "getPrescriptionForExecutorUnsealedResultPart",
   propOrder = {"patientId", "prescriptionType"}
)
public class GetPrescriptionForExecutorUnsealedResultPart implements Equals, HashCode, ToString {
   protected String patientId;
   protected String prescriptionType;

   public String getPatientId() {
      return this.patientId;
   }

   public void setPatientId(String value) {
      this.patientId = value;
   }

   public String getPrescriptionType() {
      return this.prescriptionType;
   }

   public void setPrescriptionType(String value) {
      this.prescriptionType = value;
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
      String thePrescriptionType = this.getPatientId();
      strategy.appendField(locator, this, "patientId", buffer, thePrescriptionType);
      thePrescriptionType = this.getPrescriptionType();
      strategy.appendField(locator, this, "prescriptionType", buffer, thePrescriptionType);
      return buffer;
   }

   public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
      if (!(object instanceof GetPrescriptionForExecutorUnsealedResultPart)) {
         return false;
      } else if (this == object) {
         return true;
      } else {
         GetPrescriptionForExecutorUnsealedResultPart that = (GetPrescriptionForExecutorUnsealedResultPart)object;
         String lhsPrescriptionType = this.getPatientId();
         String rhsPrescriptionType = that.getPatientId();
         if (!strategy.equals(LocatorUtils.property(thisLocator, "patientId", lhsPrescriptionType), LocatorUtils.property(thatLocator, "patientId", rhsPrescriptionType), lhsPrescriptionType, rhsPrescriptionType)) {
            return false;
         } else {
            lhsPrescriptionType = this.getPrescriptionType();
            rhsPrescriptionType = that.getPrescriptionType();
            return strategy.equals(LocatorUtils.property(thisLocator, "prescriptionType", lhsPrescriptionType), LocatorUtils.property(thatLocator, "prescriptionType", rhsPrescriptionType), lhsPrescriptionType, rhsPrescriptionType);
         }
      }
   }

   public boolean equals(Object object) {
      EqualsStrategy strategy = JAXBEqualsStrategy.INSTANCE;
      return this.equals((ObjectLocator)null, (ObjectLocator)null, object, strategy);
   }

   public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
      int currentHashCode = 1;
      String thePrescriptionType = this.getPatientId();
      int currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "patientId", thePrescriptionType), currentHashCode, thePrescriptionType);
      thePrescriptionType = this.getPrescriptionType();
      currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "prescriptionType", thePrescriptionType), currentHashCode, thePrescriptionType);
      return currentHashCode;
   }

   public int hashCode() {
      HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
      return this.hashCode((ObjectLocator)null, strategy);
   }
}
