package be.recipe.services.patient;

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
   name = "createReservationParam",
   propOrder = {"symmKey", "rid", "executorId"}
)
@XmlRootElement(
   name = "createReservationParam"
)
public class CreateReservationParam implements Equals, HashCode, ToString {
   @XmlElement(
      required = true
   )
   protected byte[] symmKey;
   @XmlElement(
      required = true
   )
   protected String rid;
   @XmlElement(
      required = true
   )
   protected String executorId;

   public byte[] getSymmKey() {
      return this.symmKey;
   }

   public void setSymmKey(byte[] value) {
      this.symmKey = value;
   }

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
      byte[] theSymmKey = this.getSymmKey();
      strategy.appendField(locator, this, "symmKey", buffer, theSymmKey);
      String theExecutorId = this.getRid();
      strategy.appendField(locator, this, "rid", buffer, theExecutorId);
      theExecutorId = this.getExecutorId();
      strategy.appendField(locator, this, "executorId", buffer, theExecutorId);
      return buffer;
   }

   public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
      if (!(object instanceof CreateReservationParam)) {
         return false;
      } else if (this == object) {
         return true;
      } else {
         CreateReservationParam that = (CreateReservationParam)object;
         byte[] lhsSymmKey = this.getSymmKey();
         byte[] rhsSymmKey = that.getSymmKey();
         if (!strategy.equals(LocatorUtils.property(thisLocator, "symmKey", lhsSymmKey), LocatorUtils.property(thatLocator, "symmKey", rhsSymmKey), lhsSymmKey, rhsSymmKey)) {
            return false;
         } else {
            String lhsExecutorId = this.getRid();
            String rhsExecutorId = that.getRid();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "rid", lhsExecutorId), LocatorUtils.property(thatLocator, "rid", rhsExecutorId), lhsExecutorId, rhsExecutorId)) {
               return false;
            } else {
               lhsExecutorId = this.getExecutorId();
               rhsExecutorId = that.getExecutorId();
               return strategy.equals(LocatorUtils.property(thisLocator, "executorId", lhsExecutorId), LocatorUtils.property(thatLocator, "executorId", rhsExecutorId), lhsExecutorId, rhsExecutorId);
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
      byte[] theSymmKey = this.getSymmKey();
      int currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "symmKey", theSymmKey), currentHashCode, theSymmKey);
      String theExecutorId = this.getRid();
      currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "rid", theExecutorId), currentHashCode, theExecutorId);
      theExecutorId = this.getExecutorId();
      currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "executorId", theExecutorId), currentHashCode, theExecutorId);
      return currentHashCode;
   }

   public int hashCode() {
      HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
      return this.hashCode((ObjectLocator)null, strategy);
   }
}
