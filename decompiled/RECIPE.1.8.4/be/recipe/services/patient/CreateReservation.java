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
   name = "createReservation",
   propOrder = {"createReservationParamSealed", "programIdentification", "mguid"}
)
@XmlRootElement(
   name = "createReservation"
)
public class CreateReservation implements Equals, HashCode, ToString {
   @XmlElement(
      name = "CreateReservationParamSealed",
      required = true
   )
   protected byte[] createReservationParamSealed;
   @XmlElement(
      name = "ProgramIdentification",
      required = true
   )
   protected String programIdentification;
   @XmlElement(
      name = "Mguid",
      required = true
   )
   protected String mguid;

   public byte[] getCreateReservationParamSealed() {
      return this.createReservationParamSealed;
   }

   public void setCreateReservationParamSealed(byte[] value) {
      this.createReservationParamSealed = value;
   }

   public String getProgramIdentification() {
      return this.programIdentification;
   }

   public void setProgramIdentification(String value) {
      this.programIdentification = value;
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
      byte[] theCreateReservationParamSealed = this.getCreateReservationParamSealed();
      strategy.appendField(locator, this, "createReservationParamSealed", buffer, theCreateReservationParamSealed);
      String theMguid = this.getProgramIdentification();
      strategy.appendField(locator, this, "programIdentification", buffer, theMguid);
      theMguid = this.getMguid();
      strategy.appendField(locator, this, "mguid", buffer, theMguid);
      return buffer;
   }

   public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
      if (!(object instanceof CreateReservation)) {
         return false;
      } else if (this == object) {
         return true;
      } else {
         CreateReservation that = (CreateReservation)object;
         byte[] lhsCreateReservationParamSealed = this.getCreateReservationParamSealed();
         byte[] rhsCreateReservationParamSealed = that.getCreateReservationParamSealed();
         if (!strategy.equals(LocatorUtils.property(thisLocator, "createReservationParamSealed", lhsCreateReservationParamSealed), LocatorUtils.property(thatLocator, "createReservationParamSealed", rhsCreateReservationParamSealed), lhsCreateReservationParamSealed, rhsCreateReservationParamSealed)) {
            return false;
         } else {
            String lhsMguid = this.getProgramIdentification();
            String rhsMguid = that.getProgramIdentification();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "programIdentification", lhsMguid), LocatorUtils.property(thatLocator, "programIdentification", rhsMguid), lhsMguid, rhsMguid)) {
               return false;
            } else {
               lhsMguid = this.getMguid();
               rhsMguid = that.getMguid();
               return strategy.equals(LocatorUtils.property(thisLocator, "mguid", lhsMguid), LocatorUtils.property(thatLocator, "mguid", rhsMguid), lhsMguid, rhsMguid);
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
      byte[] theCreateReservationParamSealed = this.getCreateReservationParamSealed();
      int currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "createReservationParamSealed", theCreateReservationParamSealed), currentHashCode, theCreateReservationParamSealed);
      String theMguid = this.getProgramIdentification();
      currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "programIdentification", theMguid), currentHashCode, theMguid);
      theMguid = this.getMguid();
      currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "mguid", theMguid), currentHashCode, theMguid);
      return currentHashCode;
   }

   public int hashCode() {
      HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
      return this.hashCode((ObjectLocator)null, strategy);
   }
}
