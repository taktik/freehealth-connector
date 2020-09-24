package be.apb.gfddpp.assuralia.bvac;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "parties",
   propOrder = {"party"}
)
public class Parties {
   protected List<Parties.Party> party;

   public List<Parties.Party> getParty() {
      if (this.party == null) {
         this.party = new ArrayList();
      }

      return this.party;
   }

   @XmlAccessorType(XmlAccessType.FIELD)
   @XmlType(
      name = "",
      propOrder = {"name", "requestorId"}
   )
   public static class Party {
      protected String name;
      @XmlElement(
         name = "requestor-id"
      )
      protected String requestorId;

      public String getName() {
         return this.name;
      }

      public void setName(String value) {
         this.name = value;
      }

      public String getRequestorId() {
         return this.requestorId;
      }

      public void setRequestorId(String value) {
         this.requestorId = value;
      }
   }
}
