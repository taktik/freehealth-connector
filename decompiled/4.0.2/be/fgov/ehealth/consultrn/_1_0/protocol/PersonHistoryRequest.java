package be.fgov.ehealth.consultrn._1_0.protocol;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "PersonHistoryRequest",
   propOrder = {"ssin"}
)
@XmlSeeAlso({PersonHistoryFamilyCompositionRequest.class, PersonHistoryNationalityRequest.class, PersonHistoryAddressRequest.class, PersonHistoryDeceaseRequest.class, PersonHistoryCivilStateRequest.class, PersonHistoryBirthRequest.class, PersonHistoryGenderRequest.class, PersonHistoryNameRequest.class})
public class PersonHistoryRequest extends ConsultRnRequestType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "SSIN",
      required = true
   )
   protected String ssin;

   public PersonHistoryRequest() {
   }

   public String getSSIN() {
      return this.ssin;
   }

   public void setSSIN(String value) {
      this.ssin = value;
   }
}
