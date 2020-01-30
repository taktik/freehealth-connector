package be.recipe.services.core;

import be.recipe.services.executor.CreateFeedbackParam;
import be.recipe.services.executor.GetPrescriptionForExecutorParam;
import be.recipe.services.executor.ListNotificationsParam;
import be.recipe.services.executor.ListOpenPrescriptionsParam;
import be.recipe.services.executor.ListReservationsParam;
import be.recipe.services.executor.ListRidsInProcessParam;
import be.recipe.services.executor.MarkAsArchivedParam;
import be.recipe.services.executor.MarkAsDeliveredParam;
import be.recipe.services.executor.MarkAsUndeliveredParam;
import be.recipe.services.executor.PutRidsInProcessParam;
import be.recipe.services.patient.CreateRelationParam;
import be.recipe.services.patient.GetPrescriptionForPatientParam;
import be.recipe.services.patient.ListPatientPrescriptionsParam;
import be.recipe.services.patient.ListRelationsParam;
import be.recipe.services.patient.RevokeRelationParam;
import be.recipe.services.prescriber.CreatePrescriptionParam;
import be.recipe.services.prescriber.GetPrescriptionForPrescriberParam;
import be.recipe.services.prescriber.GetPrescriptionStatusParam;
import be.recipe.services.prescriber.ListFeedbacksParam;
import be.recipe.services.prescriber.ListOpenRidsParam;
import be.recipe.services.prescriber.ListRidsHistoryParam;
import be.recipe.services.prescriber.PutVisionParam;
import be.recipe.services.prescriber.RevokePrescriptionParam;
import be.recipe.services.prescriber.SendNotificationParam;
import be.recipe.services.prescriber.UpdateFeedbackFlagParam;
import be.recipe.services.prescriber.ValidationPropertiesParam;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "partyIdentification",
   propOrder = {"prescriberId", "executorId", "patientId", "type", "quality"}
)
@XmlSeeAlso({ListOpenRidsParam.class, UpdateFeedbackFlagParam.class, PutVisionParam.class, ListRidsHistoryParam.class, CreatePrescriptionParam.class, GetPrescriptionStatusParam.class, RevokePrescriptionParam.class, GetPrescriptionForPrescriberParam.class, ListFeedbacksParam.class, ValidationPropertiesParam.class, SendNotificationParam.class, be.recipe.services.patient.ListOpenRidsParam.class, ListPatientPrescriptionsParam.class, GetPrescriptionForPatientParam.class, CreateRelationParam.class, ListRelationsParam.class, be.recipe.services.patient.UpdateFeedbackFlagParam.class, be.recipe.services.patient.RevokePrescriptionParam.class, RevokeRelationParam.class, MarkAsArchivedParam.class, CreateFeedbackParam.class, PutRidsInProcessParam.class, GetPrescriptionForExecutorParam.class, be.recipe.services.executor.ListRidsHistoryParam.class, ListRidsInProcessParam.class, MarkAsUndeliveredParam.class, MarkAsDeliveredParam.class, ListNotificationsParam.class, be.recipe.services.executor.ListRelationsParam.class, ListOpenPrescriptionsParam.class, ListReservationsParam.class, be.recipe.services.executor.GetPrescriptionStatusParam.class, be.recipe.services.executor.RevokePrescriptionParam.class})
public class PartyIdentification {
   protected String prescriberId;
   protected String executorId;
   protected String patientId;
   protected String type;
   protected String quality;

   public String getPrescriberId() {
      return this.prescriberId;
   }

   public void setPrescriberId(String value) {
      this.prescriberId = value;
   }

   public String getExecutorId() {
      return this.executorId;
   }

   public void setExecutorId(String value) {
      this.executorId = value;
   }

   public String getPatientId() {
      return this.patientId;
   }

   public void setPatientId(String value) {
      this.patientId = value;
   }

   public String getType() {
      return this.type;
   }

   public void setType(String value) {
      this.type = value;
   }

   public String getQuality() {
      return this.quality;
   }

   public void setQuality(String value) {
      this.quality = value;
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
      String theQuality = this.getPrescriberId();
      strategy.appendField(locator, this, "prescriberId", buffer, theQuality);
      theQuality = this.getExecutorId();
      strategy.appendField(locator, this, "executorId", buffer, theQuality);
      theQuality = this.getPatientId();
      strategy.appendField(locator, this, "patientId", buffer, theQuality);
      theQuality = this.getType();
      strategy.appendField(locator, this, "type", buffer, theQuality);
      theQuality = this.getQuality();
      strategy.appendField(locator, this, "quality", buffer, theQuality);
      return buffer;
   }

   public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
      if (!(object instanceof PartyIdentification)) {
         return false;
      } else if (this == object) {
         return true;
      } else {
         PartyIdentification that = (PartyIdentification)object;
         String lhsQuality = this.getPrescriberId();
         String rhsQuality = that.getPrescriberId();
         if (!strategy.equals(LocatorUtils.property(thisLocator, "prescriberId", lhsQuality), LocatorUtils.property(thatLocator, "prescriberId", rhsQuality), lhsQuality, rhsQuality)) {
            return false;
         } else {
            lhsQuality = this.getExecutorId();
            rhsQuality = that.getExecutorId();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "executorId", lhsQuality), LocatorUtils.property(thatLocator, "executorId", rhsQuality), lhsQuality, rhsQuality)) {
               return false;
            } else {
               lhsQuality = this.getPatientId();
               rhsQuality = that.getPatientId();
               if (!strategy.equals(LocatorUtils.property(thisLocator, "patientId", lhsQuality), LocatorUtils.property(thatLocator, "patientId", rhsQuality), lhsQuality, rhsQuality)) {
                  return false;
               } else {
                  lhsQuality = this.getType();
                  rhsQuality = that.getType();
                  if (!strategy.equals(LocatorUtils.property(thisLocator, "type", lhsQuality), LocatorUtils.property(thatLocator, "type", rhsQuality), lhsQuality, rhsQuality)) {
                     return false;
                  } else {
                     lhsQuality = this.getQuality();
                     rhsQuality = that.getQuality();
                     return strategy.equals(LocatorUtils.property(thisLocator, "quality", lhsQuality), LocatorUtils.property(thatLocator, "quality", rhsQuality), lhsQuality, rhsQuality);
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
      String theQuality = this.getPrescriberId();
      int currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "prescriberId", theQuality), currentHashCode, theQuality);
      theQuality = this.getExecutorId();
      currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "executorId", theQuality), currentHashCode, theQuality);
      theQuality = this.getPatientId();
      currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "patientId", theQuality), currentHashCode, theQuality);
      theQuality = this.getType();
      currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "type", theQuality), currentHashCode, theQuality);
      theQuality = this.getQuality();
      currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "quality", theQuality), currentHashCode, theQuality);
      return currentHashCode;
   }

   public int hashCode() {
      HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
      return this.hashCode((ObjectLocator)null, strategy);
   }
}
