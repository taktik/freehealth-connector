package be.fgov.ehealth.commons.core.v2;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ActorType",
   propOrder = {"ids", "firstNames", "name"}
)
public class ActorType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Id",
      required = true
   )
   protected List<Id> ids;
   @XmlElement(
      name = "FirstName"
   )
   protected List<String> firstNames;
   @XmlElement(
      name = "Name",
      required = true
   )
   protected String name;

   public ActorType() {
   }

   public List<Id> getIds() {
      if (this.ids == null) {
         this.ids = new ArrayList();
      }

      return this.ids;
   }

   public List<String> getFirstNames() {
      if (this.firstNames == null) {
         this.firstNames = new ArrayList();
      }

      return this.firstNames;
   }

   public String getName() {
      return this.name;
   }

   public void setName(String value) {
      this.name = value;
   }
}
