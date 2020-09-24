package be.recipe.services.prescriber;

import be.recipe.services.core.PartyIdentification;
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
   name = "createPrescriptionParam",
   propOrder = {"prescription", "prescriptionType", "feedbackRequested", "keyId", "symmKey", "prescriberLabel", "expirationDate", "vision"}
)
@XmlRootElement(
   name = "createPrescriptionParam"
)
public class CreatePrescriptionParam extends PartyIdentification implements Equals, HashCode, ToString {
   @XmlElement(
      required = true
   )
   protected byte[] prescription;
   @XmlElement(
      required = true
   )
   protected String prescriptionType;
   protected boolean feedbackRequested;
   @XmlElement(
      required = true
   )
   protected String keyId;
   @XmlElement(
      required = true
   )
   protected byte[] symmKey;
   @XmlElement(
      required = true
   )
   protected String prescriberLabel;
   @XmlElement(
      required = true
   )
   protected String expirationDate;
   @XmlElement(
      required = true
   )
   protected String vision;

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

   public boolean isFeedbackRequested() {
      return this.feedbackRequested;
   }

   public void setFeedbackRequested(boolean value) {
      this.feedbackRequested = value;
   }

   public String getKeyId() {
      return this.keyId;
   }

   public void setKeyId(String value) {
      this.keyId = value;
   }

   public byte[] getSymmKey() {
      return this.symmKey;
   }

   public void setSymmKey(byte[] value) {
      this.symmKey = value;
   }

   public String getPrescriberLabel() {
      return this.prescriberLabel;
   }

   public void setPrescriberLabel(String value) {
      this.prescriberLabel = value;
   }

   public String getExpirationDate() {
      return this.expirationDate;
   }

   public void setExpirationDate(String value) {
      this.expirationDate = value;
   }

   public String getVision() {
      return this.vision;
   }

   public void setVision(String value) {
      this.vision = value;
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
      byte[] theSymmKey = this.getPrescription();
      strategy.appendField(locator, this, "prescription", buffer, theSymmKey);
      String theVision = this.getPrescriptionType();
      strategy.appendField(locator, this, "prescriptionType", buffer, theVision);
      boolean theFeedbackRequested = this.isFeedbackRequested();
      strategy.appendField(locator, this, "feedbackRequested", buffer, theFeedbackRequested);
      theVision = this.getKeyId();
      strategy.appendField(locator, this, "keyId", buffer, theVision);
      theSymmKey = this.getSymmKey();
      strategy.appendField(locator, this, "symmKey", buffer, theSymmKey);
      theVision = this.getPrescriberLabel();
      strategy.appendField(locator, this, "prescriberLabel", buffer, theVision);
      theVision = this.getExpirationDate();
      strategy.appendField(locator, this, "expirationDate", buffer, theVision);
      theVision = this.getVision();
      strategy.appendField(locator, this, "vision", buffer, theVision);
      return buffer;
   }

   public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
      if (!(object instanceof CreatePrescriptionParam)) {
         return false;
      } else if (this == object) {
         return true;
      } else if (!super.equals(thisLocator, thatLocator, object, strategy)) {
         return false;
      } else {
         CreatePrescriptionParam that = (CreatePrescriptionParam)object;
         byte[] lhsSymmKey = this.getPrescription();
         byte[] rhsSymmKey = that.getPrescription();
         if (!strategy.equals(LocatorUtils.property(thisLocator, "prescription", lhsSymmKey), LocatorUtils.property(thatLocator, "prescription", rhsSymmKey), lhsSymmKey, rhsSymmKey)) {
            return false;
         } else {
            String lhsVision = this.getPrescriptionType();
            String rhsVision = that.getPrescriptionType();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "prescriptionType", lhsVision), LocatorUtils.property(thatLocator, "prescriptionType", rhsVision), lhsVision, rhsVision)) {
               return false;
            } else {
               boolean lhsFeedbackRequested = this.isFeedbackRequested();
               boolean rhsFeedbackRequested = that.isFeedbackRequested();
               if (!strategy.equals(LocatorUtils.property(thisLocator, "feedbackRequested", lhsFeedbackRequested), LocatorUtils.property(thatLocator, "feedbackRequested", rhsFeedbackRequested), lhsFeedbackRequested, rhsFeedbackRequested)) {
                  return false;
               } else {
                  lhsVision = this.getKeyId();
                  rhsVision = that.getKeyId();
                  if (!strategy.equals(LocatorUtils.property(thisLocator, "keyId", lhsVision), LocatorUtils.property(thatLocator, "keyId", rhsVision), lhsVision, rhsVision)) {
                     return false;
                  } else {
                     lhsSymmKey = this.getSymmKey();
                     rhsSymmKey = that.getSymmKey();
                     if (!strategy.equals(LocatorUtils.property(thisLocator, "symmKey", lhsSymmKey), LocatorUtils.property(thatLocator, "symmKey", rhsSymmKey), lhsSymmKey, rhsSymmKey)) {
                        return false;
                     } else {
                        lhsVision = this.getPrescriberLabel();
                        rhsVision = that.getPrescriberLabel();
                        if (!strategy.equals(LocatorUtils.property(thisLocator, "prescriberLabel", lhsVision), LocatorUtils.property(thatLocator, "prescriberLabel", rhsVision), lhsVision, rhsVision)) {
                           return false;
                        } else {
                           lhsVision = this.getExpirationDate();
                           rhsVision = that.getExpirationDate();
                           if (!strategy.equals(LocatorUtils.property(thisLocator, "expirationDate", lhsVision), LocatorUtils.property(thatLocator, "expirationDate", rhsVision), lhsVision, rhsVision)) {
                              return false;
                           } else {
                              lhsVision = this.getVision();
                              rhsVision = that.getVision();
                              return strategy.equals(LocatorUtils.property(thisLocator, "vision", lhsVision), LocatorUtils.property(thatLocator, "vision", rhsVision), lhsVision, rhsVision);
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
      byte[] theSymmKey = this.getPrescription();
      currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "prescription", theSymmKey), currentHashCode, theSymmKey);
      String theVision = this.getPrescriptionType();
      currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "prescriptionType", theVision), currentHashCode, theVision);
      boolean theFeedbackRequested = this.isFeedbackRequested();
      currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "feedbackRequested", theFeedbackRequested), currentHashCode, theFeedbackRequested);
      theVision = this.getKeyId();
      currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "keyId", theVision), currentHashCode, theVision);
      theSymmKey = this.getSymmKey();
      currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "symmKey", theSymmKey), currentHashCode, theSymmKey);
      theVision = this.getPrescriberLabel();
      currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "prescriberLabel", theVision), currentHashCode, theVision);
      theVision = this.getExpirationDate();
      currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "expirationDate", theVision), currentHashCode, theVision);
      theVision = this.getVision();
      currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "vision", theVision), currentHashCode, theVision);
      return currentHashCode;
   }

   public int hashCode() {
      HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
      return this.hashCode((ObjectLocator)null, strategy);
   }
}
