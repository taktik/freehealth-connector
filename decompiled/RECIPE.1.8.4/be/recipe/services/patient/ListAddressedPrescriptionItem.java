package be.recipe.services.patient;

import be.recipe.common.util.CalendarAdapter;
import java.util.Calendar;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
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
   name = "ListAddressedPrescriptionItem",
   propOrder = {"rid", "prescriberLabel", "creationDate", "prescriberId"}
)
public class ListAddressedPrescriptionItem implements Equals, HashCode, ToString {
   @XmlElement(
      required = true
   )
   protected String rid;
   @XmlElement(
      required = true
   )
   protected String prescriberLabel;
   @XmlElement(
      required = true,
      type = String.class
   )
   @XmlJavaTypeAdapter(CalendarAdapter.class)
   @XmlSchemaType(
      name = "dateTime"
   )
   protected Calendar creationDate;
   @XmlElement(
      required = true
   )
   protected String prescriberId;

   public String getRid() {
      return this.rid;
   }

   public void setRid(String value) {
      this.rid = value;
   }

   public String getPrescriberLabel() {
      return this.prescriberLabel;
   }

   public void setPrescriberLabel(String value) {
      this.prescriberLabel = value;
   }

   public Calendar getCreationDate() {
      return this.creationDate;
   }

   public void setCreationDate(Calendar value) {
      this.creationDate = value;
   }

   public String getPrescriberId() {
      return this.prescriberId;
   }

   public void setPrescriberId(String value) {
      this.prescriberId = value;
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
      String thePrescriberId = this.getRid();
      strategy.appendField(locator, this, "rid", buffer, thePrescriberId);
      thePrescriberId = this.getPrescriberLabel();
      strategy.appendField(locator, this, "prescriberLabel", buffer, thePrescriberId);
      Calendar theCreationDate = this.getCreationDate();
      strategy.appendField(locator, this, "creationDate", buffer, theCreationDate);
      thePrescriberId = this.getPrescriberId();
      strategy.appendField(locator, this, "prescriberId", buffer, thePrescriberId);
      return buffer;
   }

   public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
      if (!(object instanceof ListAddressedPrescriptionItem)) {
         return false;
      } else if (this == object) {
         return true;
      } else {
         ListAddressedPrescriptionItem that = (ListAddressedPrescriptionItem)object;
         String lhsPrescriberId = this.getRid();
         String rhsPrescriberId = that.getRid();
         if (!strategy.equals(LocatorUtils.property(thisLocator, "rid", lhsPrescriberId), LocatorUtils.property(thatLocator, "rid", rhsPrescriberId), lhsPrescriberId, rhsPrescriberId)) {
            return false;
         } else {
            lhsPrescriberId = this.getPrescriberLabel();
            rhsPrescriberId = that.getPrescriberLabel();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "prescriberLabel", lhsPrescriberId), LocatorUtils.property(thatLocator, "prescriberLabel", rhsPrescriberId), lhsPrescriberId, rhsPrescriberId)) {
               return false;
            } else {
               Calendar lhsCreationDate = this.getCreationDate();
               Calendar rhsCreationDate = that.getCreationDate();
               if (!strategy.equals(LocatorUtils.property(thisLocator, "creationDate", lhsCreationDate), LocatorUtils.property(thatLocator, "creationDate", rhsCreationDate), lhsCreationDate, rhsCreationDate)) {
                  return false;
               } else {
                  lhsPrescriberId = this.getPrescriberId();
                  rhsPrescriberId = that.getPrescriberId();
                  return strategy.equals(LocatorUtils.property(thisLocator, "prescriberId", lhsPrescriberId), LocatorUtils.property(thatLocator, "prescriberId", rhsPrescriberId), lhsPrescriberId, rhsPrescriberId);
               }
            }
         }
      }
   }

   public boolean equals(Object object) {
      EqualsStrategy strategy = JAXBEqualsStrategy.INSTANCE;
      return this.equals((ObjectLocator)null, (ObjectLocator)null, object, strategy);
   }

   public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
      int currentHashCode = 1;
      String thePrescriberId = this.getRid();
      int currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "rid", thePrescriberId), currentHashCode, thePrescriberId);
      thePrescriberId = this.getPrescriberLabel();
      currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "prescriberLabel", thePrescriberId), currentHashCode, thePrescriberId);
      Calendar theCreationDate = this.getCreationDate();
      currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "creationDate", theCreationDate), currentHashCode, theCreationDate);
      thePrescriberId = this.getPrescriberId();
      currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "prescriberId", thePrescriberId), currentHashCode, thePrescriberId);
      return currentHashCode;
   }

   public int hashCode() {
      HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
      return this.hashCode((ObjectLocator)null, strategy);
   }
}
