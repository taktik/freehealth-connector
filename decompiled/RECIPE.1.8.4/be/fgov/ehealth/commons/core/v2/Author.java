package be.fgov.ehealth.commons.core.v2;

import java.util.ArrayList;
import java.util.List;
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
   name = "AuthorType",
   propOrder = {"hcParties"}
)
@XmlRootElement(
   name = "Author"
)
public class Author implements Equals, HashCode, ToString {
   @XmlElement(
      name = "HcParty",
      required = true
   )
   protected List<ActorType> hcParties;

   public List<ActorType> getHcParties() {
      if (this.hcParties == null) {
         this.hcParties = new ArrayList();
      }

      return this.hcParties;
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
      List<ActorType> theHcParties = this.hcParties != null && !this.hcParties.isEmpty() ? this.getHcParties() : null;
      strategy.appendField(locator, this, "hcParties", buffer, theHcParties);
      return buffer;
   }

   public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
      if (!(object instanceof Author)) {
         return false;
      } else if (this == object) {
         return true;
      } else {
         Author that = (Author)object;
         List<ActorType> lhsHcParties = this.hcParties != null && !this.hcParties.isEmpty() ? this.getHcParties() : null;
         List<ActorType> rhsHcParties = that.hcParties != null && !that.hcParties.isEmpty() ? that.getHcParties() : null;
         return strategy.equals(LocatorUtils.property(thisLocator, "hcParties", lhsHcParties), LocatorUtils.property(thatLocator, "hcParties", rhsHcParties), lhsHcParties, rhsHcParties);
      }
   }

   public boolean equals(Object object) {
      EqualsStrategy strategy = JAXBEqualsStrategy.INSTANCE;
      return this.equals((ObjectLocator)null, (ObjectLocator)null, object, strategy);
   }

   public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
      int currentHashCode = 1;
      List<ActorType> theHcParties = this.hcParties != null && !this.hcParties.isEmpty() ? this.getHcParties() : null;
      int currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "hcParties", theHcParties), currentHashCode, theHcParties);
      return currentHashCode;
   }

   public int hashCode() {
      HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
      return this.hashCode((ObjectLocator)null, strategy);
   }
}
