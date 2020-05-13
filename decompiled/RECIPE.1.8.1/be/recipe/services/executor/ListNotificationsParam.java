package be.recipe.services.executor;

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
   name = "listNotificationsParam",
   propOrder = {"readFlag", "symmKey"}
)
@XmlRootElement(
   name = "listNotificationsParam"
)
public class ListNotificationsParam extends PartyIdentification implements Equals, HashCode, ToString {
   protected boolean readFlag;
   @XmlElement(
      required = true
   )
   protected byte[] symmKey;

   public boolean isReadFlag() {
      return this.readFlag;
   }

   public void setReadFlag(boolean value) {
      this.readFlag = value;
   }

   public byte[] getSymmKey() {
      return this.symmKey;
   }

   public void setSymmKey(byte[] value) {
      this.symmKey = value;
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
      boolean theReadFlag = this.isReadFlag();
      strategy.appendField(locator, this, "readFlag", buffer, theReadFlag);
      byte[] theSymmKey = this.getSymmKey();
      strategy.appendField(locator, this, "symmKey", buffer, theSymmKey);
      return buffer;
   }

   public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
      if (!(object instanceof ListNotificationsParam)) {
         return false;
      } else if (this == object) {
         return true;
      } else if (!super.equals(thisLocator, thatLocator, object, strategy)) {
         return false;
      } else {
         ListNotificationsParam that = (ListNotificationsParam)object;
         boolean lhsReadFlag = this.isReadFlag();
         boolean rhsReadFlag = that.isReadFlag();
         if (!strategy.equals(LocatorUtils.property(thisLocator, "readFlag", lhsReadFlag), LocatorUtils.property(thatLocator, "readFlag", rhsReadFlag), lhsReadFlag, rhsReadFlag)) {
            return false;
         } else {
            byte[] lhsSymmKey = this.getSymmKey();
            byte[] rhsSymmKey = that.getSymmKey();
            return strategy.equals(LocatorUtils.property(thisLocator, "symmKey", lhsSymmKey), LocatorUtils.property(thatLocator, "symmKey", rhsSymmKey), lhsSymmKey, rhsSymmKey);
         }
      }
   }

   public boolean equals(Object object) {
      EqualsStrategy strategy = JAXBEqualsStrategy.INSTANCE;
      return this.equals((ObjectLocator)null, (ObjectLocator)null, object, strategy);
   }

   public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
      int currentHashCode = super.hashCode(locator, strategy);
      boolean theReadFlag = this.isReadFlag();
      currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "readFlag", theReadFlag), currentHashCode, theReadFlag);
      byte[] theSymmKey = this.getSymmKey();
      currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "symmKey", theSymmKey), currentHashCode, theSymmKey);
      return currentHashCode;
   }

   public int hashCode() {
      HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
      return this.hashCode((ObjectLocator)null, strategy);
   }
}
