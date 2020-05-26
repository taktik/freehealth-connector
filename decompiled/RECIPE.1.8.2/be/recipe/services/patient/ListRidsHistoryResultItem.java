package be.recipe.services.patient;

import be.recipe.services.core.PrescriptionStatus;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
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
   name = "ListRidsHistoryResultItem",
   propOrder = {"rid", "prescriptionStatus"}
)
public class ListRidsHistoryResultItem implements Equals, HashCode, ToString {
   @XmlElement(
      required = true
   )
   protected String rid;
   protected PrescriptionStatus prescriptionStatus;

   public String getRid() {
      return this.rid;
   }

   public void setRid(String value) {
      this.rid = value;
   }

   public PrescriptionStatus getPrescriptionStatus() {
      return this.prescriptionStatus;
   }

   public void setPrescriptionStatus(PrescriptionStatus value) {
      this.prescriptionStatus = value;
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
      String theRid = this.getRid();
      strategy.appendField(locator, this, "rid", buffer, theRid);
      PrescriptionStatus thePrescriptionStatus = this.getPrescriptionStatus();
      strategy.appendField(locator, this, "prescriptionStatus", buffer, thePrescriptionStatus);
      return buffer;
   }

   public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
      if (!(object instanceof ListRidsHistoryResultItem)) {
         return false;
      } else if (this == object) {
         return true;
      } else {
         ListRidsHistoryResultItem that = (ListRidsHistoryResultItem)object;
         String lhsRid = this.getRid();
         String rhsRid = that.getRid();
         if (!strategy.equals(LocatorUtils.property(thisLocator, "rid", lhsRid), LocatorUtils.property(thatLocator, "rid", rhsRid), lhsRid, rhsRid)) {
            return false;
         } else {
            PrescriptionStatus lhsPrescriptionStatus = this.getPrescriptionStatus();
            PrescriptionStatus rhsPrescriptionStatus = that.getPrescriptionStatus();
            return strategy.equals(LocatorUtils.property(thisLocator, "prescriptionStatus", lhsPrescriptionStatus), LocatorUtils.property(thatLocator, "prescriptionStatus", rhsPrescriptionStatus), lhsPrescriptionStatus, rhsPrescriptionStatus);
         }
      }
   }

   public boolean equals(Object object) {
      EqualsStrategy strategy = JAXBEqualsStrategy.INSTANCE;
      return this.equals((ObjectLocator)null, (ObjectLocator)null, object, strategy);
   }

   public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
      int currentHashCode = 1;
      String theRid = this.getRid();
      int currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "rid", theRid), currentHashCode, theRid);
      PrescriptionStatus thePrescriptionStatus = this.getPrescriptionStatus();
      currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "prescriptionStatus", thePrescriptionStatus), currentHashCode, thePrescriptionStatus);
      return currentHashCode;
   }

   public int hashCode() {
      HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
      return this.hashCode((ObjectLocator)null, strategy);
   }
}
