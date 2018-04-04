package be.cin.nip.sync.reg.v1;

import org.taktik.connector.technical.adapter.XmlDateAdapter;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.joda.time.DateTime;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "registrationsType",
   propOrder = {"registrant", "registration"}
)
@XmlRootElement(
   name = "registrations"
)
public class Registrations implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      required = true
   )
   protected RegistrantType registrant;
   @XmlElement(
      required = true
   )
   protected RegistrationType registration;
   @XmlAttribute(
      name = "StartDateTime",
      required = true
   )
   @XmlJavaTypeAdapter(XmlDateAdapter.class)
   @XmlSchemaType(
      name = "date"
   )
   protected DateTime startDateTime;

   public RegistrantType getRegistrant() {
      return this.registrant;
   }

   public void setRegistrant(RegistrantType value) {
      this.registrant = value;
   }

   public RegistrationType getRegistration() {
      return this.registration;
   }

   public void setRegistration(RegistrationType value) {
      this.registration = value;
   }

   public DateTime getStartDateTime() {
      return this.startDateTime;
   }

   public void setStartDateTime(DateTime value) {
      this.startDateTime = value;
   }
}
