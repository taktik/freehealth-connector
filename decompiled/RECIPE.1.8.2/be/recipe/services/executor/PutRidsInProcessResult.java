package be.recipe.services.executor;

import be.recipe.services.core.ResponseType;
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
   name = "putRidsInProcessResult",
   propOrder = {"items"}
)
@XmlRootElement(
   name = "putRidsInProcessResult"
)
public class PutRidsInProcessResult extends ResponseType implements Equals, HashCode, ToString {
   @XmlElement(
      name = "item"
   )
   protected List<PutRidsInProcessResultItem> items;

   public List<PutRidsInProcessResultItem> getItems() {
      if (this.items == null) {
         this.items = new ArrayList();
      }

      return this.items;
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
      List<PutRidsInProcessResultItem> theItems = this.items != null && !this.items.isEmpty() ? this.getItems() : null;
      strategy.appendField(locator, this, "items", buffer, theItems);
      return buffer;
   }

   public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
      if (!(object instanceof PutRidsInProcessResult)) {
         return false;
      } else if (this == object) {
         return true;
      } else if (!super.equals(thisLocator, thatLocator, object, strategy)) {
         return false;
      } else {
         PutRidsInProcessResult that = (PutRidsInProcessResult)object;
         List<PutRidsInProcessResultItem> lhsItems = this.items != null && !this.items.isEmpty() ? this.getItems() : null;
         List<PutRidsInProcessResultItem> rhsItems = that.items != null && !that.items.isEmpty() ? that.getItems() : null;
         return strategy.equals(LocatorUtils.property(thisLocator, "items", lhsItems), LocatorUtils.property(thatLocator, "items", rhsItems), lhsItems, rhsItems);
      }
   }

   public boolean equals(Object object) {
      EqualsStrategy strategy = JAXBEqualsStrategy.INSTANCE;
      return this.equals((ObjectLocator)null, (ObjectLocator)null, object, strategy);
   }

   public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
      int currentHashCode = super.hashCode(locator, strategy);
      List<PutRidsInProcessResultItem> theItems = this.items != null && !this.items.isEmpty() ? this.getItems() : null;
      currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "items", theItems), currentHashCode, theItems);
      return currentHashCode;
   }

   public int hashCode() {
      HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
      return this.hashCode((ObjectLocator)null, strategy);
   }
}
