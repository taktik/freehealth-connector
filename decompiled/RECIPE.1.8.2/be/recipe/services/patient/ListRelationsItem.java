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
   name = "listRelationsItem",
   propOrder = {"patientId", "mandateHolderId"}
)
@XmlRootElement(
   name = "listRelationsItem"
)
public class ListRelationsItem implements Equals, HashCode, ToString {
   @XmlElement(
      required = true
   )
   protected String patientId;
   @XmlElement(
      required = true
   )
   protected String mandateHolderId;

   public String getPatientId() {
      return this.patientId;
   }

   public void setPatientId(String value) {
      this.patientId = value;
   }

   public String getMandateHolderId() {
      return this.mandateHolderId;
   }

   public void setMandateHolderId(String value) {
      this.mandateHolderId = value;
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
      String theMandateHolderId = this.getPatientId();
      strategy.appendField(locator, this, "patientId", buffer, theMandateHolderId);
      theMandateHolderId = this.getMandateHolderId();
      strategy.appendField(locator, this, "mandateHolderId", buffer, theMandateHolderId);
      return buffer;
   }

   public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
      if (!(object instanceof ListRelationsItem)) {
         return false;
      } else if (this == object) {
         return true;
      } else {
         ListRelationsItem that = (ListRelationsItem)object;
         String lhsMandateHolderId = this.getPatientId();
         String rhsMandateHolderId = that.getPatientId();
         if (!strategy.equals(LocatorUtils.property(thisLocator, "patientId", lhsMandateHolderId), LocatorUtils.property(thatLocator, "patientId", rhsMandateHolderId), lhsMandateHolderId, rhsMandateHolderId)) {
            return false;
         } else {
            lhsMandateHolderId = this.getMandateHolderId();
            rhsMandateHolderId = that.getMandateHolderId();
            return strategy.equals(LocatorUtils.property(thisLocator, "mandateHolderId", lhsMandateHolderId), LocatorUtils.property(thatLocator, "mandateHolderId", rhsMandateHolderId), lhsMandateHolderId, rhsMandateHolderId);
         }
      }
   }

   public boolean equals(Object object) {
      EqualsStrategy strategy = JAXBEqualsStrategy.INSTANCE;
      return this.equals((ObjectLocator)null, (ObjectLocator)null, object, strategy);
   }

   public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
      int currentHashCode = 1;
      String theMandateHolderId = this.getPatientId();
      int currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "patientId", theMandateHolderId), currentHashCode, theMandateHolderId);
      theMandateHolderId = this.getMandateHolderId();
      currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "mandateHolderId", theMandateHolderId), currentHashCode, theMandateHolderId);
      return currentHashCode;
   }

   public int hashCode() {
      HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
      return this.hashCode((ObjectLocator)null, strategy);
   }
}
