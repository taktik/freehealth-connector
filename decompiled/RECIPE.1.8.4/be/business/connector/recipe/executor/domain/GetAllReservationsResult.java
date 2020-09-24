package be.business.connector.recipe.executor.domain;

import be.recipe.services.core.ResponseType;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "GetAllReservationsResult"
)
@XmlRootElement(
   namespace = "http://services.recipe.be"
)
public class GetAllReservationsResult extends ResponseType {
   private List<Reservation> reservations;

   public GetAllReservationsResult() {
   }

   public GetAllReservationsResult(List<Reservation> reservations) {
      this.reservations = reservations;
   }

   public void add(Reservation rid) {
      if (this.reservations == null) {
         this.reservations = new ArrayList();
      }

      this.reservations.add(rid);
   }

   public List<Reservation> getReservations() {
      return this.reservations;
   }

   public void setReservations(List<Reservation> reservations) {
      this.reservations = reservations;
   }
}
