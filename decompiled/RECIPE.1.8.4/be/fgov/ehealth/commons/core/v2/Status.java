package be.fgov.ehealth.commons.core.v2;

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
   name = "StatusType",
   propOrder = {"statusCode", "statusMessage", "statusDetail"}
)
@XmlRootElement(
   name = "Status"
)
public class Status implements Equals, HashCode, ToString {
   @XmlElement(
      name = "StatusCode",
      required = true
   )
   protected StatusCode statusCode;
   @XmlElement(
      name = "StatusMessage"
   )
   protected String statusMessage;
   @XmlElement(
      name = "StatusDetail"
   )
   protected StatusDetail statusDetail;

   public StatusCode getStatusCode() {
      return this.statusCode;
   }

   public void setStatusCode(StatusCode value) {
      this.statusCode = value;
   }

   public String getStatusMessage() {
      return this.statusMessage;
   }

   public void setStatusMessage(String value) {
      this.statusMessage = value;
   }

   public StatusDetail getStatusDetail() {
      return this.statusDetail;
   }

   public void setStatusDetail(StatusDetail value) {
      this.statusDetail = value;
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
      StatusCode theStatusCode = this.getStatusCode();
      strategy.appendField(locator, this, "statusCode", buffer, theStatusCode);
      String theStatusMessage = this.getStatusMessage();
      strategy.appendField(locator, this, "statusMessage", buffer, theStatusMessage);
      StatusDetail theStatusDetail = this.getStatusDetail();
      strategy.appendField(locator, this, "statusDetail", buffer, theStatusDetail);
      return buffer;
   }

   public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
      if (!(object instanceof Status)) {
         return false;
      } else if (this == object) {
         return true;
      } else {
         Status that = (Status)object;
         StatusCode lhsStatusCode = this.getStatusCode();
         StatusCode rhsStatusCode = that.getStatusCode();
         if (!strategy.equals(LocatorUtils.property(thisLocator, "statusCode", lhsStatusCode), LocatorUtils.property(thatLocator, "statusCode", rhsStatusCode), lhsStatusCode, rhsStatusCode)) {
            return false;
         } else {
            String lhsStatusMessage = this.getStatusMessage();
            String rhsStatusMessage = that.getStatusMessage();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "statusMessage", lhsStatusMessage), LocatorUtils.property(thatLocator, "statusMessage", rhsStatusMessage), lhsStatusMessage, rhsStatusMessage)) {
               return false;
            } else {
               StatusDetail lhsStatusDetail = this.getStatusDetail();
               StatusDetail rhsStatusDetail = that.getStatusDetail();
               return strategy.equals(LocatorUtils.property(thisLocator, "statusDetail", lhsStatusDetail), LocatorUtils.property(thatLocator, "statusDetail", rhsStatusDetail), lhsStatusDetail, rhsStatusDetail);
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
      StatusCode theStatusCode = this.getStatusCode();
      int currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "statusCode", theStatusCode), currentHashCode, theStatusCode);
      String theStatusMessage = this.getStatusMessage();
      currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "statusMessage", theStatusMessage), currentHashCode, theStatusMessage);
      StatusDetail theStatusDetail = this.getStatusDetail();
      currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "statusDetail", theStatusDetail), currentHashCode, theStatusDetail);
      return currentHashCode;
   }

   public int hashCode() {
      HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
      return this.hashCode((ObjectLocator)null, strategy);
   }
}
