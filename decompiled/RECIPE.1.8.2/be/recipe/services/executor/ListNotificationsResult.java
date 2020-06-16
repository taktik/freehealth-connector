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
   name = "ListNotificationsResult",
   propOrder = {"notifications"}
)
@XmlRootElement(
   name = "listNotificationsResult"
)
public class ListNotificationsResult extends ResponseType implements Equals, HashCode, ToString {
   protected List<ListNotificationsItem> notifications;

   public List<ListNotificationsItem> getNotifications() {
      if (this.notifications == null) {
         this.notifications = new ArrayList();
      }

      return this.notifications;
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
      List<ListNotificationsItem> theNotifications = this.notifications != null && !this.notifications.isEmpty() ? this.getNotifications() : null;
      strategy.appendField(locator, this, "notifications", buffer, theNotifications);
      return buffer;
   }

   public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
      if (!(object instanceof ListNotificationsResult)) {
         return false;
      } else if (this == object) {
         return true;
      } else if (!super.equals(thisLocator, thatLocator, object, strategy)) {
         return false;
      } else {
         ListNotificationsResult that = (ListNotificationsResult)object;
         List<ListNotificationsItem> lhsNotifications = this.notifications != null && !this.notifications.isEmpty() ? this.getNotifications() : null;
         List<ListNotificationsItem> rhsNotifications = that.notifications != null && !that.notifications.isEmpty() ? that.getNotifications() : null;
         return strategy.equals(LocatorUtils.property(thisLocator, "notifications", lhsNotifications), LocatorUtils.property(thatLocator, "notifications", rhsNotifications), lhsNotifications, rhsNotifications);
      }
   }

   public boolean equals(Object object) {
      EqualsStrategy strategy = JAXBEqualsStrategy.INSTANCE;
      return this.equals((ObjectLocator)null, (ObjectLocator)null, object, strategy);
   }

   public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
      int currentHashCode = super.hashCode(locator, strategy);
      List<ListNotificationsItem> theNotifications = this.notifications != null && !this.notifications.isEmpty() ? this.getNotifications() : null;
      currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "notifications", theNotifications), currentHashCode, theNotifications);
      return currentHashCode;
   }

   public int hashCode() {
      HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
      return this.hashCode((ObjectLocator)null, strategy);
   }
}
