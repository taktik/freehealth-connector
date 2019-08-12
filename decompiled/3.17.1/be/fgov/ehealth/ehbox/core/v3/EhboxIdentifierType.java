package be.fgov.ehealth.ehbox.core.v3;

import be.fgov.ehealth.commons.core.v1.IdentifierType;
import be.fgov.ehealth.ehbox.consultation.protocol.v3.DestinationContextType;
import be.fgov.ehealth.ehbox.publication.protocol.v3.Recipient;
import be.fgov.ehealth.ehbox.publication.protocol.v3.Substitute;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "EhboxIdentifierType",
   propOrder = {"quality", "user"}
)
@XmlSeeAlso({DestinationContextType.class, be.fgov.ehealth.ehbox.publication.protocol.v3.DestinationContextType.class, Substitute.class, Recipient.class})
public class EhboxIdentifierType extends IdentifierType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Quality",
      required = true
   )
   protected String quality;
   @XmlElement(
      name = "User"
   )
   protected User user;

   public String getQuality() {
      return this.quality;
   }

   public void setQuality(String value) {
      this.quality = value;
   }

   public User getUser() {
      return this.user;
   }

   public void setUser(User value) {
      this.user = value;
   }
}
