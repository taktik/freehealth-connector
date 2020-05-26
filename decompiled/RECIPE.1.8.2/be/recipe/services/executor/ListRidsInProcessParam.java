package be.recipe.services.executor;

import be.recipe.services.core.Page;
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
   name = "listRidsInProcessParam",
   propOrder = {"symmKey", "page", "session"}
)
@XmlRootElement(
   name = "listRidsInProcessParam"
)
public class ListRidsInProcessParam extends PartyIdentification implements Equals, HashCode, ToString {
   @XmlElement(
      required = true
   )
   protected byte[] symmKey;
   protected Page page;
   protected byte[] session;

   public byte[] getSymmKey() {
      return this.symmKey;
   }

   public void setSymmKey(byte[] value) {
      this.symmKey = value;
   }

   public Page getPage() {
      return this.page;
   }

   public void setPage(Page value) {
      this.page = value;
   }

   public byte[] getSession() {
      return this.session;
   }

   public void setSession(byte[] value) {
      this.session = value;
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
      byte[] theSession = this.getSymmKey();
      strategy.appendField(locator, this, "symmKey", buffer, theSession);
      Page thePage = this.getPage();
      strategy.appendField(locator, this, "page", buffer, thePage);
      theSession = this.getSession();
      strategy.appendField(locator, this, "session", buffer, theSession);
      return buffer;
   }

   public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
      if (!(object instanceof ListRidsInProcessParam)) {
         return false;
      } else if (this == object) {
         return true;
      } else if (!super.equals(thisLocator, thatLocator, object, strategy)) {
         return false;
      } else {
         ListRidsInProcessParam that = (ListRidsInProcessParam)object;
         byte[] lhsSession = this.getSymmKey();
         byte[] rhsSession = that.getSymmKey();
         if (!strategy.equals(LocatorUtils.property(thisLocator, "symmKey", lhsSession), LocatorUtils.property(thatLocator, "symmKey", rhsSession), lhsSession, rhsSession)) {
            return false;
         } else {
            Page lhsPage = this.getPage();
            Page rhsPage = that.getPage();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "page", lhsPage), LocatorUtils.property(thatLocator, "page", rhsPage), lhsPage, rhsPage)) {
               return false;
            } else {
               lhsSession = this.getSession();
               rhsSession = that.getSession();
               return strategy.equals(LocatorUtils.property(thisLocator, "session", lhsSession), LocatorUtils.property(thatLocator, "session", rhsSession), lhsSession, rhsSession);
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
      byte[] theSession = this.getSymmKey();
      currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "symmKey", theSession), currentHashCode, theSession);
      Page thePage = this.getPage();
      currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "page", thePage), currentHashCode, thePage);
      theSession = this.getSession();
      currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "session", theSession), currentHashCode, theSession);
      return currentHashCode;
   }

   public int hashCode() {
      HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
      return this.hashCode((ObjectLocator)null, strategy);
   }
}
