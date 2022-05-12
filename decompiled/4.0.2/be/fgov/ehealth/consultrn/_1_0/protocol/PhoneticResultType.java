package be.fgov.ehealth.consultrn._1_0.protocol;

import be.fgov.ehealth.consultrn._1_0.core.PersonType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "PhoneticResultType",
   propOrder = {"persons"}
)
public class PhoneticResultType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Person",
      required = true
   )
   protected List<PersonType> persons;

   public PhoneticResultType() {
   }

   public List<PersonType> getPersons() {
      if (this.persons == null) {
         this.persons = new ArrayList();
      }

      return this.persons;
   }
}
