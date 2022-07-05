package be.fgov.ehealth.rn.personservice.protocol.v1;

import be.fgov.ehealth.commons.protocol.v2.StatusResponseType;
import be.fgov.ehealth.rn.personservice.core.v1.PersonResponseResultsType;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "SearchPersonPhoneticallyResponseType",
   propOrder = {"result"}
)
@XmlRootElement(
   name = "SearchPersonPhoneticallyResponse"
)
public class SearchPersonPhoneticallyResponse extends StatusResponseType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Result"
   )
   protected PersonResponseResultsType result;

   public SearchPersonPhoneticallyResponse() {
   }

   public PersonResponseResultsType getResult() {
      return this.result;
   }

   public void setResult(PersonResponseResultsType value) {
      this.result = value;
   }
}
