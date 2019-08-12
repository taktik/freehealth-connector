package be.fgov.ehealth.consultrn.core.v2;

import be.fgov.ehealth.consultrn.commons.core.v3.PersonResponseType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ExistingPersonsType",
   propOrder = {"existingPersons"}
)
public class ExistingPersonsType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "ExistingPerson",
      required = true
   )
   protected List<PersonResponseType> existingPersons;

   public List<PersonResponseType> getExistingPersons() {
      if (this.existingPersons == null) {
         this.existingPersons = new ArrayList();
      }

      return this.existingPersons;
   }
}
