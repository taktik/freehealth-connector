package be.recipe.services.patient;

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
   name = "getPrescriptionForPatientParam",
   propOrder = {"rid", "symmKey", "version"}
)
@XmlRootElement(
   name = "getPrescriptionForPatientParam"
)
public class GetPrescriptionForPatientParam extends PartyIdentification implements Equals, HashCode, ToString {
   @XmlElement(
      required = true
   )
   protected String rid;
   @XmlElement(
      required = true
   )
   protected byte[] symmKey;
   @XmlElement(
      required = true
   )
   protected String version;

   public String getRid() {
      return this.rid;
   }

   public void setRid(String value) {
      this.rid = value;
   }

   public byte[] getSymmKey() {
      return this.symmKey;
   }

   public void setSymmKey(byte[] value) {
      this.symmKey = value;
   }

   public String getVersion() {
      return this.version;
   }

   public void setVersion(String value) {
      this.version = value;
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
      String theVersion = this.getRid();
      strategy.appendField(locator, this, "rid", buffer, theVersion);
      byte[] theSymmKey = this.getSymmKey();
      strategy.appendField(locator, this, "symmKey", buffer, theSymmKey);
      theVersion = this.getVersion();
      strategy.appendField(locator, this, "version", buffer, theVersion);
      return buffer;
   }

   public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
      if (!(object instanceof GetPrescriptionForPatientParam)) {
         return false;
      } else if (this == object) {
         return true;
      } else if (!super.equals(thisLocator, thatLocator, object, strategy)) {
         return false;
      } else {
         GetPrescriptionForPatientParam that = (GetPrescriptionForPatientParam)object;
         String lhsVersion = this.getRid();
         String rhsVersion = that.getRid();
         if (!strategy.equals(LocatorUtils.property(thisLocator, "rid", lhsVersion), LocatorUtils.property(thatLocator, "rid", rhsVersion), lhsVersion, rhsVersion)) {
            return false;
         } else {
            byte[] lhsSymmKey = this.getSymmKey();
            byte[] rhsSymmKey = that.getSymmKey();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "symmKey", lhsSymmKey), LocatorUtils.property(thatLocator, "symmKey", rhsSymmKey), lhsSymmKey, rhsSymmKey)) {
               return false;
            } else {
               lhsVersion = this.getVersion();
               rhsVersion = that.getVersion();
               return strategy.equals(LocatorUtils.property(thisLocator, "version", lhsVersion), LocatorUtils.property(thatLocator, "version", rhsVersion), lhsVersion, rhsVersion);
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
      String theVersion = this.getRid();
      currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "rid", theVersion), currentHashCode, theVersion);
      byte[] theSymmKey = this.getSymmKey();
      currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "symmKey", theSymmKey), currentHashCode, theSymmKey);
      theVersion = this.getVersion();
      currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "version", theVersion), currentHashCode, theVersion);
      return currentHashCode;
   }

   public int hashCode() {
      HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
      return this.hashCode((ObjectLocator)null, strategy);
   }
}
