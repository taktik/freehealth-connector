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
   name = "validationPropertiesParam",
   propOrder = {"symmKey", "clientPropertiesVersion"}
)
@XmlRootElement(
   name = "validationPropertiesParam"
)
public class ValidationPropertiesParam extends PartyIdentification implements Equals, HashCode, ToString {
   @XmlElement(
      required = true
   )
   protected byte[] symmKey;
   @XmlElement(
      required = true
   )
   protected String clientPropertiesVersion;

   public byte[] getSymmKey() {
      return this.symmKey;
   }

   public void setSymmKey(byte[] value) {
      this.symmKey = value;
   }

   public String getClientPropertiesVersion() {
      return this.clientPropertiesVersion;
   }

   public void setClientPropertiesVersion(String value) {
      this.clientPropertiesVersion = value;
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
      byte[] theSymmKey = this.getSymmKey();
      strategy.appendField(locator, this, "symmKey", buffer, theSymmKey);
      String theClientPropertiesVersion = this.getClientPropertiesVersion();
      strategy.appendField(locator, this, "clientPropertiesVersion", buffer, theClientPropertiesVersion);
      return buffer;
   }

   public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
      if (!(object instanceof ValidationPropertiesParam)) {
         return false;
      } else if (this == object) {
         return true;
      } else if (!super.equals(thisLocator, thatLocator, object, strategy)) {
         return false;
      } else {
         ValidationPropertiesParam that = (ValidationPropertiesParam)object;
         byte[] lhsSymmKey = this.getSymmKey();
         byte[] rhsSymmKey = that.getSymmKey();
         if (!strategy.equals(LocatorUtils.property(thisLocator, "symmKey", lhsSymmKey), LocatorUtils.property(thatLocator, "symmKey", rhsSymmKey), lhsSymmKey, rhsSymmKey)) {
            return false;
         } else {
            String lhsClientPropertiesVersion = this.getClientPropertiesVersion();
            String rhsClientPropertiesVersion = that.getClientPropertiesVersion();
            return strategy.equals(LocatorUtils.property(thisLocator, "clientPropertiesVersion", lhsClientPropertiesVersion), LocatorUtils.property(thatLocator, "clientPropertiesVersion", rhsClientPropertiesVersion), lhsClientPropertiesVersion, rhsClientPropertiesVersion);
         }
      }
   }

   public boolean equals(Object object) {
      EqualsStrategy strategy = JAXBEqualsStrategy.INSTANCE;
      return this.equals((ObjectLocator)null, (ObjectLocator)null, object, strategy);
   }

   public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
      int currentHashCode = super.hashCode(locator, strategy);
      byte[] theSymmKey = this.getSymmKey();
      currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "symmKey", theSymmKey), currentHashCode, theSymmKey);
      String theClientPropertiesVersion = this.getClientPropertiesVersion();
      currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "clientPropertiesVersion", theClientPropertiesVersion), currentHashCode, theClientPropertiesVersion);
      return currentHashCode;
   }

   public int hashCode() {
      HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
      return this.hashCode((ObjectLocator)null, strategy);
   }
}
