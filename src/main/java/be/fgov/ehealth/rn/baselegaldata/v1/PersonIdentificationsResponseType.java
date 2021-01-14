package be.fgov.ehealth.rn.baselegaldata.v1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "PersonIdentificationsResponseType",
   propOrder = {"personIdentifications"}
)
public class PersonIdentificationsResponseType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "PersonIdentification",
      required = true
   )
   protected List personIdentifications;

   public List getPersonIdentifications() {
      if (this.personIdentifications == null) {
         this.personIdentifications = new ArrayList();
      }

      return this.personIdentifications;
   }
}
