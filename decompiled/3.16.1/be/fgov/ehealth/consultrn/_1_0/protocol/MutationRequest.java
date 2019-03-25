package be.fgov.ehealth.consultrn._1_0.protocol;

import be.fgov.ehealth.commons._1_0.core.IdentifierType;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "MutationRequestType",
   propOrder = {"organisation"}
)
@XmlRootElement(
   name = "MutationRequest"
)
public class MutationRequest implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Organisation"
   )
   protected IdentifierType organisation;

   public IdentifierType getOrganisation() {
      return this.organisation;
   }

   public void setOrganisation(IdentifierType value) {
      this.organisation = value;
   }
}
