package be.recipe.services.core;

import be.recipe.services.executor.CreateFeedbackResult;
import be.recipe.services.executor.GetOpenPrescriptionForExecutor;
import be.recipe.services.executor.GetPrescriptionForExecutorResult;
import be.recipe.services.executor.GetPrescriptionForExecutorResultUnsealed;
import be.recipe.services.executor.ListNotificationsResult;
import be.recipe.services.executor.ListOpenPrescriptionsResult;
import be.recipe.services.executor.ListReservationsResult;
import be.recipe.services.executor.ListRidsInProcessResult;
import be.recipe.services.executor.MarkAsArchivedResult;
import be.recipe.services.executor.MarkAsDeliveredResult;
import be.recipe.services.executor.MarkAsUndeliveredResult;
import be.recipe.services.executor.PutRidsInProcessResult;
import be.recipe.services.patient.CreateRelationResult;
import be.recipe.services.patient.CreateReservationResult;
import be.recipe.services.patient.GetPrescriptionForPatientResult;
import be.recipe.services.patient.GetVisionResult;
import be.recipe.services.patient.ListPatientPrescriptionsResult;
import be.recipe.services.patient.ListRelationsResult;
import be.recipe.services.patient.RevokeRelationResult;
import be.recipe.services.prescriber.CreatePrescriptionResult;
import be.recipe.services.prescriber.GetPrescriptionForPrescriberResult;
import be.recipe.services.prescriber.GetPrescriptionStatusResult;
import be.recipe.services.prescriber.ListFeedbacksResult;
import be.recipe.services.prescriber.ListOpenRidsResult;
import be.recipe.services.prescriber.ListRidsHistoryResult;
import be.recipe.services.prescriber.PutVisionResult;
import be.recipe.services.prescriber.RevokePrescriptionResult;
import be.recipe.services.prescriber.SendNotificationResult;
import be.recipe.services.prescriber.UpdateFeedbackFlagResult;
import be.recipe.services.prescriber.ValidationPropertiesResult;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
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
   name = "ResponseType",
   propOrder = {"status"}
)
@XmlSeeAlso({GetOpenPrescriptionForExecutor.class, GetPrescriptionForExecutorResultUnsealed.class, ListRidsHistoryResult.class, GetPrescriptionForPrescriberResult.class, UpdateFeedbackFlagResult.class, RevokePrescriptionResult.class, ValidationPropertiesResult.class, ListOpenRidsResult.class, SendNotificationResult.class, PutVisionResult.class, GetPrescriptionStatusResult.class, ListFeedbacksResult.class, CreatePrescriptionResult.class, ListPatientPrescriptionsResult.class, RevokeRelationResult.class, be.recipe.services.patient.UpdateFeedbackFlagResult.class, GetVisionResult.class, be.recipe.services.patient.RevokePrescriptionResult.class, be.recipe.services.patient.PutVisionResult.class, be.recipe.services.patient.GetPrescriptionStatusResult.class, CreateReservationResult.class, be.recipe.services.patient.ListRidsHistoryResult.class, ListRelationsResult.class, GetPrescriptionForPatientResult.class, CreateRelationResult.class, be.recipe.services.patient.ListOpenRidsResult.class, ListOpenPrescriptionsResult.class, MarkAsUndeliveredResult.class, be.recipe.services.executor.RevokePrescriptionResult.class, GetPrescriptionForExecutorResult.class, MarkAsDeliveredResult.class, be.recipe.services.executor.GetPrescriptionStatusResult.class, ListNotificationsResult.class, ListReservationsResult.class, CreateFeedbackResult.class, be.recipe.services.executor.ListRidsHistoryResult.class, ListRidsInProcessResult.class, be.recipe.services.executor.ListRelationsResult.class, MarkAsArchivedResult.class, PutRidsInProcessResult.class})
public class ResponseType implements Equals, HashCode, ToString {
   @XmlElement(
      required = true
   )
   protected StatusType status;
   @XmlAttribute(
      name = "id"
   )
   protected String id;

   public StatusType getStatus() {
      return this.status;
   }

   public void setStatus(StatusType value) {
      this.status = value;
   }

   public String getId() {
      return this.id;
   }

   public void setId(String value) {
      this.id = value;
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
      StatusType theStatus = this.getStatus();
      strategy.appendField(locator, this, "status", buffer, theStatus);
      String theId = this.getId();
      strategy.appendField(locator, this, "id", buffer, theId);
      return buffer;
   }

   public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
      if (!(object instanceof ResponseType)) {
         return false;
      } else if (this == object) {
         return true;
      } else {
         ResponseType that = (ResponseType)object;
         StatusType lhsStatus = this.getStatus();
         StatusType rhsStatus = that.getStatus();
         if (!strategy.equals(LocatorUtils.property(thisLocator, "status", lhsStatus), LocatorUtils.property(thatLocator, "status", rhsStatus), lhsStatus, rhsStatus)) {
            return false;
         } else {
            String lhsId = this.getId();
            String rhsId = that.getId();
            return strategy.equals(LocatorUtils.property(thisLocator, "id", lhsId), LocatorUtils.property(thatLocator, "id", rhsId), lhsId, rhsId);
         }
      }
   }

   public boolean equals(Object object) {
      EqualsStrategy strategy = JAXBEqualsStrategy.INSTANCE;
      return this.equals((ObjectLocator)null, (ObjectLocator)null, object, strategy);
   }

   public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
      int currentHashCode = 1;
      StatusType theStatus = this.getStatus();
      int currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "status", theStatus), currentHashCode, theStatus);
      String theId = this.getId();
      currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "id", theId), currentHashCode, theId);
      return currentHashCode;
   }

   public int hashCode() {
      HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
      return this.hashCode((ObjectLocator)null, strategy);
   }
}
