package be.ehealth.businessconnector.ehbox.api.domain;

import be.ehealth.businessconnector.ehbox.api.domain.exception.EhboxBusinessConnectorException;
import be.ehealth.businessconnector.ehbox.api.domain.exception.EhboxBusinessConnectorExceptionValues;
import be.ehealth.businessconnector.ehbox.api.utils.QualityType;
import be.ehealth.technicalconnector.utils.IdentifierType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Addressee {
   private static final Logger LOG = LoggerFactory.getLogger(Addressee.class);
   private String id;
   private IdentifierType identifier;
   private String quality;
   private String applicationId;
   private String lastName;
   private String firstName;
   private String organizationName;
   private String personInOrganisation;
   private boolean oOoProcessed;

   public Addressee(String id, QualityType qualityType) {
      if (qualityType == null) {
         throw new IllegalArgumentException("when calling the constructor with a  QualityType argument , it cannot be empty!");
      } else {
         this.id = id;
         this.quality = qualityType.getQuality();
         this.identifier = qualityType.getIdentifierType();
      }
   }

   public Addressee(String id, IdentifierType helperType) {
      this.id = id;
      this.identifier = helperType;
   }

   public Addressee(IdentifierType type) {
      if (type == null) {
         throw new IllegalArgumentException("this constructor cannot be called with a null value");
      } else {
         this.identifier = type;
      }
   }

   public boolean isOoOProcessed() {
      return this.oOoProcessed;
   }

   public void setOoOProcessed(boolean ooOProcessed) {
      this.oOoProcessed = ooOProcessed;
   }

   public final String getId() {
      return this.id;
   }

   public final long getIdAsLong() {
      return Long.parseLong(this.id);
   }

   public final void setId(String id) {
      this.id = id;
   }

   public final String getType() {
      return this.identifier == null ? null : this.identifier.getType(49);
   }

   public final String getSubType() {
      return this.identifier == null ? null : this.identifier.getSubType(49);
   }

   public final String getQuality() {
      return this.quality;
   }

   public final void setQuality(String quality) {
      this.quality = quality;
   }

   public void setQuality(QualityType quality) {
      if (quality != null) {
         this.quality = quality.getQuality();
         this.identifier = quality.getIdentifierType();
      }

   }

   public final String getApplicationId() {
      return this.applicationId;
   }

   public final void setApplicationId(String applicationId) {
      this.applicationId = applicationId;
   }

   public final String getLastName() {
      return this.lastName;
   }

   public final void setLastName(String lastName) {
      this.lastName = lastName;
   }

   public final String getFirstName() {
      return this.firstName;
   }

   public final void setFirstName(String firstName) {
      this.firstName = firstName;
   }

   public final String getOrganizationName() {
      return this.organizationName;
   }

   public final void setOrganizationName(String organizationName) {
      this.organizationName = organizationName;
   }

   public void setIdenfitierTypeHelper(IdentifierType helper) {
      this.identifier = helper;
   }

   public IdentifierType getIdentifierTypeHelper() throws EhboxBusinessConnectorException {
      if (this.identifier == null) {
         LOG.debug("\t## Identifier is empty : throwing Ehbox business connector exception");
         throw new EhboxBusinessConnectorException(EhboxBusinessConnectorExceptionValues.NO_QUALITY_SET, (Throwable)null, new Object[0]);
      } else {
         return this.identifier;
      }
   }

   public void setPersonInOrganisation(String personInOrganisation) {
      this.personInOrganisation = personInOrganisation;
   }

   public String getPersonInOrganisation() {
      return this.personInOrganisation;
   }

   public String toString() {
      StringBuffer sb = new StringBuffer();
      if (this.getPersonInOrganisation() == null) {
         sb.append(this.getFirstName());
         sb.append(" ");
         sb.append(this.getLastName());
      } else {
         sb.append(this.getOrganizationName());
      }

      return "Addressee [id=" + this.id + ", identifier=" + this.identifier + ", quality=" + this.quality + ", name=" + sb.toString() + "]";
   }
}
