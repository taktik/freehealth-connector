package be.cin.mycarenet._1_0.carenet.types;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "GeneralSituationType",
   propOrder = {"transfers"}
)
@XmlRootElement(
   name = "GeneralSituation"
)
public class GeneralSituation implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Transfer"
   )
   protected List<Transfer> transfers;
   @XmlAttribute(
      name = "Event",
      required = true
   )
   protected EventType event;

   public GeneralSituation() {
   }

   public List<Transfer> getTransfers() {
      if (this.transfers == null) {
         this.transfers = new ArrayList();
      }

      return this.transfers;
   }

   public EventType getEvent() {
      return this.event;
   }

   public void setEvent(EventType value) {
      this.event = value;
   }
}
