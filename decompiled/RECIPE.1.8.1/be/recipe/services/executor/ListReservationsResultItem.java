package be.recipe.services.executor;

import be.recipe.common.util.CalendarAdapter;
import be.recipe.services.core.PrescriptionStatus;
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
   name = "listReservationsResultItem",
   propOrder = {"rid", "executorId", "creationDateReservation", "lastUpdateReservation", "prescription", "creationDate", "expirationDate", "prescriptionStatus", "patientId", "prescriberId"}
)
public class ListReservationsResultItem implements Equals, HashCode, ToString {
   @XmlElement(
      required = true
   )
   protected String rid;
   protected String executorId;
   @XmlElement(
      type = String.class
   )
   @XmlJavaTypeAdapter(CalendarAdapter.class)
   @XmlSchemaType(
      name = "dateTime"
   )
   protected Calendar creationDateReservation;
   @XmlElement(
      type = String.class
   )
   @XmlJavaTypeAdapter(CalendarAdapter.class)
   @XmlSchemaType(
      name = "dateTime"
   )
   protected Calendar lastUpdateReservation;
   protected GetOpenPrescriptionForExecutor prescription;
   @XmlElement(
      type = String.class
   )
   @XmlJavaTypeAdapter(CalendarAdapter.class)
   @XmlSchemaType(
      name = "dateTime"
   )
   protected Calendar creationDate;
   @XmlElement(
      type = String.class
   )
   @XmlJavaTypeAdapter(CalendarAdapter.class)
   @XmlSchemaType(
      name = "dateTime"
   )
   protected Calendar expirationDate;
   @XmlElement(
      required = true
   )
   protected PrescriptionStatus prescriptionStatus;
   @XmlElement(
      required = true
   )
   protected String patientId;
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

   public String getExecutorId() {
      return this.executorId;
   }

   public void setExecutorId(String value) {
      this.executorId = value;
   }

   public Calendar getCreationDateReservation() {
      return this.creationDateReservation;
   }

   public void setCreationDateReservation(Calendar value) {
      this.creationDateReservation = value;
   }

   public Calendar getLastUpdateReservation() {
      return this.lastUpdateReservation;
   }

   public void setLastUpdateReservation(Calendar value) {
      this.lastUpdateReservation = value;
   }

   public GetOpenPrescriptionForExecutor getPrescription() {
      return this.prescription;
   }

   public void setPrescription(GetOpenPrescriptionForExecutor value) {
      this.prescription = value;
   }

   public Calendar getCreationDate() {
      return this.creationDate;
   }

   public void setCreationDate(Calendar value) {
      this.creationDate = value;
   }

   public Calendar getExpirationDate() {
      return this.expirationDate;
   }

   public void setExpirationDate(Calendar value) {
      this.expirationDate = value;
   }

   public PrescriptionStatus getPrescriptionStatus() {
      return this.prescriptionStatus;
   }

   public void setPrescriptionStatus(PrescriptionStatus value) {
      this.prescriptionStatus = value;
   }

   public String getPatientId() {
      return this.patientId;
   }

   public void setPatientId(String value) {
      this.patientId = value;
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
      thePrescriberId = this.getExecutorId();
      strategy.appendField(locator, this, "executorId", buffer, thePrescriberId);
      Calendar theExpirationDate = this.getCreationDateReservation();
      strategy.appendField(locator, this, "creationDateReservation", buffer, theExpirationDate);
      theExpirationDate = this.getLastUpdateReservation();
      strategy.appendField(locator, this, "lastUpdateReservation", buffer, theExpirationDate);
      GetOpenPrescriptionForExecutor thePrescription = this.getPrescription();
      strategy.appendField(locator, this, "prescription", buffer, thePrescription);
      theExpirationDate = this.getCreationDate();
      strategy.appendField(locator, this, "creationDate", buffer, theExpirationDate);
      theExpirationDate = this.getExpirationDate();
      strategy.appendField(locator, this, "expirationDate", buffer, theExpirationDate);
      PrescriptionStatus thePrescriptionStatus = this.getPrescriptionStatus();
      strategy.appendField(locator, this, "prescriptionStatus", buffer, thePrescriptionStatus);
      thePrescriberId = this.getPatientId();
      strategy.appendField(locator, this, "patientId", buffer, thePrescriberId);
      thePrescriberId = this.getPrescriberId();
      strategy.appendField(locator, this, "prescriberId", buffer, thePrescriberId);
      return buffer;
   }

   public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
      if (!(object instanceof ListReservationsResultItem)) {
         return false;
      } else if (this == object) {
         return true;
      } else {
         ListReservationsResultItem that = (ListReservationsResultItem)object;
         String lhsPrescriberId = this.getRid();
         String rhsPrescriberId = that.getRid();
         if (!strategy.equals(LocatorUtils.property(thisLocator, "rid", lhsPrescriberId), LocatorUtils.property(thatLocator, "rid", rhsPrescriberId), lhsPrescriberId, rhsPrescriberId)) {
            return false;
         } else {
            lhsPrescriberId = this.getExecutorId();
            rhsPrescriberId = that.getExecutorId();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "executorId", lhsPrescriberId), LocatorUtils.property(thatLocator, "executorId", rhsPrescriberId), lhsPrescriberId, rhsPrescriberId)) {
               return false;
            } else {
               Calendar lhsExpirationDate = this.getCreationDateReservation();
               Calendar rhsExpirationDate = that.getCreationDateReservation();
               if (!strategy.equals(LocatorUtils.property(thisLocator, "creationDateReservation", lhsExpirationDate), LocatorUtils.property(thatLocator, "creationDateReservation", rhsExpirationDate), lhsExpirationDate, rhsExpirationDate)) {
                  return false;
               } else {
                  lhsExpirationDate = this.getLastUpdateReservation();
                  rhsExpirationDate = that.getLastUpdateReservation();
                  if (!strategy.equals(LocatorUtils.property(thisLocator, "lastUpdateReservation", lhsExpirationDate), LocatorUtils.property(thatLocator, "lastUpdateReservation", rhsExpirationDate), lhsExpirationDate, rhsExpirationDate)) {
                     return false;
                  } else {
                     GetOpenPrescriptionForExecutor lhsPrescription = this.getPrescription();
                     GetOpenPrescriptionForExecutor rhsPrescription = that.getPrescription();
                     if (!strategy.equals(LocatorUtils.property(thisLocator, "prescription", lhsPrescription), LocatorUtils.property(thatLocator, "prescription", rhsPrescription), lhsPrescription, rhsPrescription)) {
                        return false;
                     } else {
                        lhsExpirationDate = this.getCreationDate();
                        rhsExpirationDate = that.getCreationDate();
                        if (!strategy.equals(LocatorUtils.property(thisLocator, "creationDate", lhsExpirationDate), LocatorUtils.property(thatLocator, "creationDate", rhsExpirationDate), lhsExpirationDate, rhsExpirationDate)) {
                           return false;
                        } else {
                           lhsExpirationDate = this.getExpirationDate();
                           rhsExpirationDate = that.getExpirationDate();
                           if (!strategy.equals(LocatorUtils.property(thisLocator, "expirationDate", lhsExpirationDate), LocatorUtils.property(thatLocator, "expirationDate", rhsExpirationDate), lhsExpirationDate, rhsExpirationDate)) {
                              return false;
                           } else {
                              PrescriptionStatus lhsPrescriptionStatus = this.getPrescriptionStatus();
                              PrescriptionStatus rhsPrescriptionStatus = that.getPrescriptionStatus();
                              if (!strategy.equals(LocatorUtils.property(thisLocator, "prescriptionStatus", lhsPrescriptionStatus), LocatorUtils.property(thatLocator, "prescriptionStatus", rhsPrescriptionStatus), lhsPrescriptionStatus, rhsPrescriptionStatus)) {
                                 return false;
                              } else {
                                 lhsPrescriberId = this.getPatientId();
                                 rhsPrescriberId = that.getPatientId();
                                 if (!strategy.equals(LocatorUtils.property(thisLocator, "patientId", lhsPrescriberId), LocatorUtils.property(thatLocator, "patientId", rhsPrescriberId), lhsPrescriberId, rhsPrescriberId)) {
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
      String thePrescriberId = this.getRid();
      int currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "rid", thePrescriberId), currentHashCode, thePrescriberId);
      thePrescriberId = this.getExecutorId();
      currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "executorId", thePrescriberId), currentHashCode, thePrescriberId);
      Calendar theExpirationDate = this.getCreationDateReservation();
      currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "creationDateReservation", theExpirationDate), currentHashCode, theExpirationDate);
      theExpirationDate = this.getLastUpdateReservation();
      currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "lastUpdateReservation", theExpirationDate), currentHashCode, theExpirationDate);
      GetOpenPrescriptionForExecutor thePrescription = this.getPrescription();
      currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "prescription", thePrescription), currentHashCode, thePrescription);
      theExpirationDate = this.getCreationDate();
      currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "creationDate", theExpirationDate), currentHashCode, theExpirationDate);
      theExpirationDate = this.getExpirationDate();
      currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "expirationDate", theExpirationDate), currentHashCode, theExpirationDate);
      PrescriptionStatus thePrescriptionStatus = this.getPrescriptionStatus();
      currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "prescriptionStatus", thePrescriptionStatus), currentHashCode, thePrescriptionStatus);
      thePrescriberId = this.getPatientId();
      currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "patientId", thePrescriberId), currentHashCode, thePrescriberId);
      thePrescriberId = this.getPrescriberId();
      currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "prescriberId", thePrescriberId), currentHashCode, thePrescriberId);
      return currentHashCode;
   }

   public int hashCode() {
      HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
      return this.hashCode((ObjectLocator)null, strategy);
   }
}
