package be.fgov.ehealth.commons.core.v2;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "AuthorType",
   propOrder = {"hcParties"}
)
@XmlRootElement(
   name = "Author"
)
public class Author implements Serializable {
   private static final long serialVersionUID = 1L;
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
}
