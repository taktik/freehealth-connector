package be.recipe.services.executor;

import be.recipe.common.util.CalendarAdapter;
import be.recipe.services.core.PrescriptionStatus;
import be.recipe.services.core.ResponseType;
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
   name = "getOpenPrescriptionForExecutor",
   propOrder = {"creationDate", "encryptionKeyId", "feedbackAllowed", "patientId", "prescriberId", "prescription", "prescriptionType", "rid", "expirationDate", "prescriptionStatus"}
)
public class GetOpenPrescriptionForExecutor extends ResponseType implements Equals, HashCode, ToString {
   @XmlElement(
      type = String.class
   )
   @XmlJavaTypeAdapter(CalendarAdapter.class)
   @XmlSchemaType(
      name = "dateTime"
   )
   protected Calendar creationDate;
   protected String encryptionKeyId;
   protected boolean feedbackAllowed;
   protected String patientId;
   @XmlElement(
      required = true
   )
   protected String prescriberId;
   protected byte[] prescription;
   protected String prescriptionType;
   protected String rid;
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

   public Calendar getCreationDate() {
      return this.creationDate;
   }

   public void setCreationDate(Calendar value) {
      this.creationDate = value;
   }

   public String getEncryptionKeyId() {
      return this.encryptionKeyId;
   }

   public void setEncryptionKeyId(String value) {
      this.encryptionKeyId = value;
   }

   public boolean isFeedbackAllowed() {
      return this.feedbackAllowed;
   }

   public void setFeedbackAllowed(boolean value) {
      this.feedbackAllowed = value;
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

   public byte[] getPrescription() {
      return this.prescription;
   }

   public void setPrescription(byte[] value) {
      this.prescription = value;
   }

   public String getPrescriptionType() {
      return this.prescriptionType;
   }

   public void setPrescriptionType(String value) {
      this.prescriptionType = value;
   }

   public String getRid() {
      return this.rid;
   }

   public void setRid(String value) {
      this.rid = value;
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
      Calendar theExpirationDate = this.getCreationDate();
      strategy.appendField(locator, this, "creationDate", buffer, theExpirationDate);
      String theRid = this.getEncryptionKeyId();
      strategy.appendField(locator, this, "encryptionKeyId", buffer, theRid);
      boolean theFeedbackAllowed = this.isFeedbackAllowed();
      strategy.appendField(locator, this, "feedbackAllowed", buffer, theFeedbackAllowed);
      theRid = this.getPatientId();
      strategy.appendField(locator, this, "patientId", buffer, theRid);
      theRid = this.getPrescriberId();
      strategy.appendField(locator, this, "prescriberId", buffer, theRid);
      byte[] thePrescription = this.getPrescription();
      strategy.appendField(locator, this, "prescription", buffer, thePrescription);
      theRid = this.getPrescriptionType();
      strategy.appendField(locator, this, "prescriptionType", buffer, theRid);
      theRid = this.getRid();
      strategy.appendField(locator, this, "rid", buffer, theRid);
      theExpirationDate = this.getExpirationDate();
      strategy.appendField(locator, this, "expirationDate", buffer, theExpirationDate);
      PrescriptionStatus thePrescriptionStatus = this.getPrescriptionStatus();
      strategy.appendField(locator, this, "prescriptionStatus", buffer, thePrescriptionStatus);
      return buffer;
   }

   public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
      if (!(object instanceof GetOpenPrescriptionForExecutor)) {
         return false;
      } else if (this == object) {
         return true;
      } else if (!super.equals(thisLocator, thatLocator, object, strategy)) {
         return false;
      } else {
         GetOpenPrescriptionForExecutor that = (GetOpenPrescriptionForExecutor)object;
         Calendar lhsExpirationDate = this.getCreationDate();
         Calendar rhsExpirationDate = that.getCreationDate();
         if (!strategy.equals(LocatorUtils.property(thisLocator, "creationDate", lhsExpirationDate), LocatorUtils.property(thatLocator, "creationDate", rhsExpirationDate), lhsExpirationDate, rhsExpirationDate)) {
            return false;
         } else {
            String lhsRid = this.getEncryptionKeyId();
            String rhsRid = that.getEncryptionKeyId();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "encryptionKeyId", lhsRid), LocatorUtils.property(thatLocator, "encryptionKeyId", rhsRid), lhsRid, rhsRid)) {
               return false;
            } else {
               boolean lhsFeedbackAllowed = this.isFeedbackAllowed();
               boolean rhsFeedbackAllowed = that.isFeedbackAllowed();
               if (!strategy.equals(LocatorUtils.property(thisLocator, "feedbackAllowed", lhsFeedbackAllowed), LocatorUtils.property(thatLocator, "feedbackAllowed", rhsFeedbackAllowed), lhsFeedbackAllowed, rhsFeedbackAllowed)) {
                  return false;
               } else {
                  lhsRid = this.getPatientId();
                  rhsRid = that.getPatientId();
                  if (!strategy.equals(LocatorUtils.property(thisLocator, "patientId", lhsRid), LocatorUtils.property(thatLocator, "patientId", rhsRid), lhsRid, rhsRid)) {
                     return false;
                  } else {
                     lhsRid = this.getPrescriberId();
                     rhsRid = that.getPrescriberId();
                     if (!strategy.equals(LocatorUtils.property(thisLocator, "prescriberId", lhsRid), LocatorUtils.property(thatLocator, "prescriberId", rhsRid), lhsRid, rhsRid)) {
                        return false;
                     } else {
                        byte[] lhsPrescription = this.getPrescription();
                        byte[] rhsPrescription = that.getPrescription();
                        if (!strategy.equals(LocatorUtils.property(thisLocator, "prescription", lhsPrescription), LocatorUtils.property(thatLocator, "prescription", rhsPrescription), lhsPrescription, rhsPrescription)) {
                           return false;
                        } else {
                           lhsRid = this.getPrescriptionType();
                           rhsRid = that.getPrescriptionType();
                           if (!strategy.equals(LocatorUtils.property(thisLocator, "prescriptionType", lhsRid), LocatorUtils.property(thatLocator, "prescriptionType", rhsRid), lhsRid, rhsRid)) {
                              return false;
                           } else {
                              lhsRid = this.getRid();
                              rhsRid = that.getRid();
                              if (!strategy.equals(LocatorUtils.property(thisLocator, "rid", lhsRid), LocatorUtils.property(thatLocator, "rid", rhsRid), lhsRid, rhsRid)) {
                                 return false;
                              } else {
                                 lhsExpirationDate = this.getExpirationDate();
                                 rhsExpirationDate = that.getExpirationDate();
                                 if (!strategy.equals(LocatorUtils.property(thisLocator, "expirationDate", lhsExpirationDate), LocatorUtils.property(thatLocator, "expirationDate", rhsExpirationDate), lhsExpirationDate, rhsExpirationDate)) {
                                    return false;
                                 } else {
                                    PrescriptionStatus lhsPrescriptionStatus = this.getPrescriptionStatus();
                                    PrescriptionStatus rhsPrescriptionStatus = that.getPrescriptionStatus();
                                    return strategy.equals(LocatorUtils.property(thisLocator, "prescriptionStatus", lhsPrescriptionStatus), LocatorUtils.property(thatLocator, "prescriptionStatus", rhsPrescriptionStatus), lhsPrescriptionStatus, rhsPrescriptionStatus);
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
      int currentHashCode = super.hashCode(locator, strategy);
      Calendar theExpirationDate = this.getCreationDate();
      currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "creationDate", theExpirationDate), currentHashCode, theExpirationDate);
      String theRid = this.getEncryptionKeyId();
      currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "encryptionKeyId", theRid), currentHashCode, theRid);
      boolean theFeedbackAllowed = this.isFeedbackAllowed();
      currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "feedbackAllowed", theFeedbackAllowed), currentHashCode, theFeedbackAllowed);
      theRid = this.getPatientId();
      currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "patientId", theRid), currentHashCode, theRid);
      theRid = this.getPrescriberId();
      currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "prescriberId", theRid), currentHashCode, theRid);
      byte[] thePrescription = this.getPrescription();
      currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "prescription", thePrescription), currentHashCode, thePrescription);
      theRid = this.getPrescriptionType();
      currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "prescriptionType", theRid), currentHashCode, theRid);
      theRid = this.getRid();
      currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "rid", theRid), currentHashCode, theRid);
      theExpirationDate = this.getExpirationDate();
      currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "expirationDate", theExpirationDate), currentHashCode, theExpirationDate);
      PrescriptionStatus thePrescriptionStatus = this.getPrescriptionStatus();
      currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "prescriptionStatus", thePrescriptionStatus), currentHashCode, thePrescriptionStatus);
      return currentHashCode;
   }

   public int hashCode() {
      HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
      return this.hashCode((ObjectLocator)null, strategy);
   }
}
