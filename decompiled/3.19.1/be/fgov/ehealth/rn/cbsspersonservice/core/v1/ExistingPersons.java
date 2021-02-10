package be.fgov.ehealth.rn.cbsspersonservice.core.v1;

import be.fgov.ehealth.rn.baselegaldata.v1.PersonIdentificationType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ExistingPersons",
   propOrder = {"existingPersons"}
)
public class ExistingPersons implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "ExistingPerson",
      required = true
   )
   protected List<PersonIdentificationType> existingPersons;

   public List<PersonIdentificationType> getExistingPersons() {
      if (this.existingPersons == null) {
         this.existingPersons = new ArrayList();
      }

      return this.existingPersons;
   }
}
