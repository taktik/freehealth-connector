package be.recipe.services.core;

import java.util.ArrayList;
import java.util.List;
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
   name = "StatusType",
   propOrder = {"code", "messageCode", "messages", "statusUpdater", "prescriptionStatus"}
)
public class StatusType implements Equals, HashCode, ToString {
   @XmlElement(
      required = true
   )
   protected String code;
   protected String messageCode;
   protected List<LocalisedString> messages;
   protected String statusUpdater;
   protected String prescriptionStatus;

   public String getCode() {
      return this.code;
   }

   public void setCode(String value) {
      this.code = value;
   }

   public String getMessageCode() {
      return this.messageCode;
   }

   public void setMessageCode(String value) {
      this.messageCode = value;
   }

   public List<LocalisedString> getMessages() {
      if (this.messages == null) {
         this.messages = new ArrayList();
      }

      return this.messages;
   }

   public String getStatusUpdater() {
      return this.statusUpdater;
   }

   public void setStatusUpdater(String value) {
      this.statusUpdater = value;
   }

   public String getPrescriptionStatus() {
      return this.prescriptionStatus;
   }

   public void setPrescriptionStatus(String value) {
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
      String thePrescriptionStatus = this.getCode();
      strategy.appendField(locator, this, "code", buffer, thePrescriptionStatus);
      thePrescriptionStatus = this.getMessageCode();
      strategy.appendField(locator, this, "messageCode", buffer, thePrescriptionStatus);
      List<LocalisedString> theMessages = this.messages != null && !this.messages.isEmpty() ? this.getMessages() : null;
      strategy.appendField(locator, this, "messages", buffer, theMessages);
      thePrescriptionStatus = this.getStatusUpdater();
      strategy.appendField(locator, this, "statusUpdater", buffer, thePrescriptionStatus);
      thePrescriptionStatus = this.getPrescriptionStatus();
      strategy.appendField(locator, this, "prescriptionStatus", buffer, thePrescriptionStatus);
      return buffer;
   }

   public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
      if (!(object instanceof StatusType)) {
         return false;
      } else if (this == object) {
         return true;
      } else {
         StatusType that = (StatusType)object;
         String lhsPrescriptionStatus = this.getCode();
         String rhsPrescriptionStatus = that.getCode();
         if (!strategy.equals(LocatorUtils.property(thisLocator, "code", lhsPrescriptionStatus), LocatorUtils.property(thatLocator, "code", rhsPrescriptionStatus), lhsPrescriptionStatus, rhsPrescriptionStatus)) {
            return false;
         } else {
            lhsPrescriptionStatus = this.getMessageCode();
            rhsPrescriptionStatus = that.getMessageCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "messageCode", lhsPrescriptionStatus), LocatorUtils.property(thatLocator, "messageCode", rhsPrescriptionStatus), lhsPrescriptionStatus, rhsPrescriptionStatus)) {
               return false;
            } else {
               List<LocalisedString> lhsMessages = this.messages != null && !this.messages.isEmpty() ? this.getMessages() : null;
               List<LocalisedString> rhsMessages = that.messages != null && !that.messages.isEmpty() ? that.getMessages() : null;
               if (!strategy.equals(LocatorUtils.property(thisLocator, "messages", lhsMessages), LocatorUtils.property(thatLocator, "messages", rhsMessages), lhsMessages, rhsMessages)) {
                  return false;
               } else {
                  lhsPrescriptionStatus = this.getStatusUpdater();
                  rhsPrescriptionStatus = that.getStatusUpdater();
                  if (!strategy.equals(LocatorUtils.property(thisLocator, "statusUpdater", lhsPrescriptionStatus), LocatorUtils.property(thatLocator, "statusUpdater", rhsPrescriptionStatus), lhsPrescriptionStatus, rhsPrescriptionStatus)) {
                     return false;
                  } else {
                     lhsPrescriptionStatus = this.getPrescriptionStatus();
                     rhsPrescriptionStatus = that.getPrescriptionStatus();
                     return strategy.equals(LocatorUtils.property(thisLocator, "prescriptionStatus", lhsPrescriptionStatus), LocatorUtils.property(thatLocator, "prescriptionStatus", rhsPrescriptionStatus), lhsPrescriptionStatus, rhsPrescriptionStatus);
                  }
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
      String thePrescriptionStatus = this.getCode();
      int currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "code", thePrescriptionStatus), currentHashCode, thePrescriptionStatus);
      thePrescriptionStatus = this.getMessageCode();
      currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "messageCode", thePrescriptionStatus), currentHashCode, thePrescriptionStatus);
      List<LocalisedString> theMessages = this.messages != null && !this.messages.isEmpty() ? this.getMessages() : null;
      currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "messages", theMessages), currentHashCode, theMessages);
      thePrescriptionStatus = this.getStatusUpdater();
      currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "statusUpdater", thePrescriptionStatus), currentHashCode, thePrescriptionStatus);
      thePrescriptionStatus = this.getPrescriptionStatus();
      currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "prescriptionStatus", thePrescriptionStatus), currentHashCode, thePrescriptionStatus);
      return currentHashCode;
   }

   public int hashCode() {
      HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
      return this.hashCode((ObjectLocator)null, strategy);
   }
}
