package be.recipe.services.prescriber;

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
   name = "createPrescription",
   propOrder = {"createPrescriptionParamSealed", "securityToken", "prescriptionType", "documentId", "keyId", "programIdentification", "prescriptionVersion", "referenceSourceVersion", "mguid"}
)
@XmlRootElement(
   name = "createPrescription"
)
public class CreatePrescription implements Equals, HashCode, ToString {
   @XmlElement(
      name = "CreatePrescriptionParamSealed"
   )
   protected byte[] createPrescriptionParamSealed;
   @XmlElement(
      name = "SecurityToken"
   )
   protected Object securityToken;
   @XmlElement(
      name = "PrescriptionType"
   )
   protected String prescriptionType;
   @XmlElement(
      name = "DocumentId"
   )
   protected String documentId;
   @XmlElement(
      name = "KeyId"
   )
   protected String keyId;
   @XmlElement(
      name = "ProgramIdentification",
      required = true
   )
   protected String programIdentification;
   @XmlElement(
      name = "PrescriptionVersion",
      required = true
   )
   protected String prescriptionVersion;
   @XmlElement(
      name = "ReferenceSourceVersion",
      required = true
   )
   protected String referenceSourceVersion;
   @XmlElement(
      name = "Mguid",
      required = true
   )
   protected String mguid;

   public byte[] getCreatePrescriptionParamSealed() {
      return this.createPrescriptionParamSealed;
   }

   public void setCreatePrescriptionParamSealed(byte[] value) {
      this.createPrescriptionParamSealed = value;
   }

   public Object getSecurityToken() {
      return this.securityToken;
   }

   public void setSecurityToken(Object value) {
      this.securityToken = value;
   }

   public String getPrescriptionType() {
      return this.prescriptionType;
   }

   public void setPrescriptionType(String value) {
      this.prescriptionType = value;
   }

   public String getDocumentId() {
      return this.documentId;
   }

   public void setDocumentId(String value) {
      this.documentId = value;
   }

   public String getKeyId() {
      return this.keyId;
   }

   public void setKeyId(String value) {
      this.keyId = value;
   }

   public String getProgramIdentification() {
      return this.programIdentification;
   }

   public void setProgramIdentification(String value) {
      this.programIdentification = value;
   }

   public String getPrescriptionVersion() {
      return this.prescriptionVersion;
   }

   public void setPrescriptionVersion(String value) {
      this.prescriptionVersion = value;
   }

   public String getReferenceSourceVersion() {
      return this.referenceSourceVersion;
   }

   public void setReferenceSourceVersion(String value) {
      this.referenceSourceVersion = value;
   }

   public String getMguid() {
      return this.mguid;
   }

   public void setMguid(String value) {
      this.mguid = value;
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
      byte[] theCreatePrescriptionParamSealed = this.getCreatePrescriptionParamSealed();
      strategy.appendField(locator, this, "createPrescriptionParamSealed", buffer, theCreatePrescriptionParamSealed);
      Object theSecurityToken = this.getSecurityToken();
      strategy.appendField(locator, this, "securityToken", buffer, theSecurityToken);
      String theMguid = this.getPrescriptionType();
      strategy.appendField(locator, this, "prescriptionType", buffer, theMguid);
      theMguid = this.getDocumentId();
      strategy.appendField(locator, this, "documentId", buffer, theMguid);
      theMguid = this.getKeyId();
      strategy.appendField(locator, this, "keyId", buffer, theMguid);
      theMguid = this.getProgramIdentification();
      strategy.appendField(locator, this, "programIdentification", buffer, theMguid);
      theMguid = this.getPrescriptionVersion();
      strategy.appendField(locator, this, "prescriptionVersion", buffer, theMguid);
      theMguid = this.getReferenceSourceVersion();
      strategy.appendField(locator, this, "referenceSourceVersion", buffer, theMguid);
      theMguid = this.getMguid();
      strategy.appendField(locator, this, "mguid", buffer, theMguid);
      return buffer;
   }

   public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
      if (!(object instanceof CreatePrescription)) {
         return false;
      } else if (this == object) {
         return true;
      } else {
         CreatePrescription that = (CreatePrescription)object;
         byte[] lhsCreatePrescriptionParamSealed = this.getCreatePrescriptionParamSealed();
         byte[] rhsCreatePrescriptionParamSealed = that.getCreatePrescriptionParamSealed();
         if (!strategy.equals(LocatorUtils.property(thisLocator, "createPrescriptionParamSealed", lhsCreatePrescriptionParamSealed), LocatorUtils.property(thatLocator, "createPrescriptionParamSealed", rhsCreatePrescriptionParamSealed), lhsCreatePrescriptionParamSealed, rhsCreatePrescriptionParamSealed)) {
            return false;
         } else {
            Object lhsSecurityToken = this.getSecurityToken();
            Object rhsSecurityToken = that.getSecurityToken();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "securityToken", lhsSecurityToken), LocatorUtils.property(thatLocator, "securityToken", rhsSecurityToken), lhsSecurityToken, rhsSecurityToken)) {
               return false;
            } else {
               String lhsMguid = this.getPrescriptionType();
               String rhsMguid = that.getPrescriptionType();
               if (!strategy.equals(LocatorUtils.property(thisLocator, "prescriptionType", lhsMguid), LocatorUtils.property(thatLocator, "prescriptionType", rhsMguid), lhsMguid, rhsMguid)) {
                  return false;
               } else {
                  lhsMguid = this.getDocumentId();
                  rhsMguid = that.getDocumentId();
                  if (!strategy.equals(LocatorUtils.property(thisLocator, "documentId", lhsMguid), LocatorUtils.property(thatLocator, "documentId", rhsMguid), lhsMguid, rhsMguid)) {
                     return false;
                  } else {
                     lhsMguid = this.getKeyId();
                     rhsMguid = that.getKeyId();
                     if (!strategy.equals(LocatorUtils.property(thisLocator, "keyId", lhsMguid), LocatorUtils.property(thatLocator, "keyId", rhsMguid), lhsMguid, rhsMguid)) {
                        return false;
                     } else {
                        lhsMguid = this.getProgramIdentification();
                        rhsMguid = that.getProgramIdentification();
                        if (!strategy.equals(LocatorUtils.property(thisLocator, "programIdentification", lhsMguid), LocatorUtils.property(thatLocator, "programIdentification", rhsMguid), lhsMguid, rhsMguid)) {
                           return false;
                        } else {
                           lhsMguid = this.getPrescriptionVersion();
                           rhsMguid = that.getPrescriptionVersion();
                           if (!strategy.equals(LocatorUtils.property(thisLocator, "prescriptionVersion", lhsMguid), LocatorUtils.property(thatLocator, "prescriptionVersion", rhsMguid), lhsMguid, rhsMguid)) {
                              return false;
                           } else {
                              lhsMguid = this.getReferenceSourceVersion();
                              rhsMguid = that.getReferenceSourceVersion();
                              if (!strategy.equals(LocatorUtils.property(thisLocator, "referenceSourceVersion", lhsMguid), LocatorUtils.property(thatLocator, "referenceSourceVersion", rhsMguid), lhsMguid, rhsMguid)) {
                                 return false;
                              } else {
                                 lhsMguid = this.getMguid();
                                 rhsMguid = that.getMguid();
                                 return strategy.equals(LocatorUtils.property(thisLocator, "mguid", lhsMguid), LocatorUtils.property(thatLocator, "mguid", rhsMguid), lhsMguid, rhsMguid);
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
      byte[] theCreatePrescriptionParamSealed = this.getCreatePrescriptionParamSealed();
      int currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "createPrescriptionParamSealed", theCreatePrescriptionParamSealed), currentHashCode, theCreatePrescriptionParamSealed);
      Object theSecurityToken = this.getSecurityToken();
      currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "securityToken", theSecurityToken), currentHashCode, theSecurityToken);
      String theMguid = this.getPrescriptionType();
      currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "prescriptionType", theMguid), currentHashCode, theMguid);
      theMguid = this.getDocumentId();
      currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "documentId", theMguid), currentHashCode, theMguid);
      theMguid = this.getKeyId();
      currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "keyId", theMguid), currentHashCode, theMguid);
      theMguid = this.getProgramIdentification();
      currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "programIdentification", theMguid), currentHashCode, theMguid);
      theMguid = this.getPrescriptionVersion();
      currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "prescriptionVersion", theMguid), currentHashCode, theMguid);
      theMguid = this.getReferenceSourceVersion();
      currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "referenceSourceVersion", theMguid), currentHashCode, theMguid);
      theMguid = this.getMguid();
      currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "mguid", theMguid), currentHashCode, theMguid);
      return currentHashCode;
   }

   public int hashCode() {
      HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
      return this.hashCode((ObjectLocator)null, strategy);
   }
}
