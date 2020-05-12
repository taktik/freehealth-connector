package be.recipe.services.executor;

import be.recipe.services.core.ResponseType;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
   name = "listReservationsResult",
   propOrder = {"items", "hasMoreResults", "session"}
)
@XmlRootElement(
   name = "listReservationsResult"
)
public class ListReservationsResult extends ResponseType implements Equals, HashCode, ToString {
   protected List<ListReservationsResultItem> items;
   protected Boolean hasMoreResults;
   protected byte[] session;

   public List<ListReservationsResultItem> getItems() {
      if (this.items == null) {
         this.items = new ArrayList();
      }

      return this.items;
   }

   public Boolean isHasMoreResults() {
      return this.hasMoreResults;
   }

   public void setHasMoreResults(Boolean value) {
      this.hasMoreResults = value;
   }

   public byte[] getSession() {
      return this.session;
   }

   public void setSession(byte[] value) {
      this.session = value;
   }

   public Boolean getHasMoreResults() {
      return this.hasMoreResults;
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
      List<ListReservationsResultItem> theItems = this.items != null && !this.items.isEmpty() ? this.getItems() : null;
      strategy.appendField(locator, this, "items", buffer, theItems);
      Boolean theHasMoreResults = this.getHasMoreResults();
      strategy.appendField(locator, this, "hasMoreResults", buffer, theHasMoreResults);
      byte[] theSession = this.getSession();
      strategy.appendField(locator, this, "session", buffer, theSession);
      return buffer;
   }

   public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
      if (!(object instanceof ListReservationsResult)) {
         return false;
      } else if (this == object) {
         return true;
      } else if (!super.equals(thisLocator, thatLocator, object, strategy)) {
         return false;
      } else {
         ListReservationsResult that = (ListReservationsResult)object;
         List<ListReservationsResultItem> lhsItems = this.items != null && !this.items.isEmpty() ? this.getItems() : null;
         List<ListReservationsResultItem> rhsItems = that.items != null && !that.items.isEmpty() ? that.getItems() : null;
         if (!strategy.equals(LocatorUtils.property(thisLocator, "items", lhsItems), LocatorUtils.property(thatLocator, "items", rhsItems), lhsItems, rhsItems)) {
            return false;
         } else {
            Boolean lhsHasMoreResults = this.getHasMoreResults();
            Boolean rhsHasMoreResults = that.getHasMoreResults();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "hasMoreResults", lhsHasMoreResults), LocatorUtils.property(thatLocator, "hasMoreResults", rhsHasMoreResults), lhsHasMoreResults, rhsHasMoreResults)) {
               return false;
            } else {
               byte[] lhsSession = this.getSession();
               byte[] rhsSession = that.getSession();
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
      List<ListReservationsResultItem> theItems = this.items != null && !this.items.isEmpty() ? this.getItems() : null;
      currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "items", theItems), currentHashCode, theItems);
      Boolean theHasMoreResults = this.getHasMoreResults();
      currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "hasMoreResults", theHasMoreResults), currentHashCode, theHasMoreResults);
      byte[] theSession = this.getSession();
      currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "session", theSession), currentHashCode, theSession);
      return currentHashCode;
   }

   public int hashCode() {
      HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
      return this.hashCode((ObjectLocator)null, strategy);
   }
}
