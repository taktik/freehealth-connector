package be.fgov.ehealth.consultrn._1_0.protocol;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "SearchPhoneticRequestType",
   propOrder = {"phoneticCriteria"}
)
@XmlRootElement(
   name = "SearchPhoneticRequest"
)
public class SearchPhoneticRequest extends ConsultRnRequestType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "PhoneticCriteria",
      required = true
   )
   protected PhoneticCriteriaType phoneticCriteria;

   public PhoneticCriteriaType getPhoneticCriteria() {
      return this.phoneticCriteria;
   }

   public void setPhoneticCriteria(PhoneticCriteriaType value) {
      this.phoneticCriteria = value;
   }
}
