package be.recipe.services.executor;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ListAddressedPrescriptionsResult"
)
@XmlRootElement(
   namespace = "http:/services.recipe.be/executor"
)
public class ListNotificationsResult {
   @XmlElement(
      name = "notifications"
   )
   private List<ListNotificationsItem> notifications = new ArrayList();

   public ListNotificationsResult() {
   }

   public ListNotificationsResult(List<ListNotificationsItem> addressedNotifications) {
      this.notifications = addressedNotifications;
   }

   public List<ListNotificationsItem> getAddressedNotifications() {
      return this.notifications;
   }

   public void setAddressedNotifications(List<ListNotificationsItem> addressedNotifications) {
      this.notifications = addressedNotifications;
   }

   public boolean add(ListNotificationsItem e) {
      return this.notifications.add(e);
   }
}
