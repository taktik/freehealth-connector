package be.fgov.ehealth.addressbook.protocol.v1;

import be.fgov.ehealth.addressbook.core.v1.IndividualContactInformationType;
import be.fgov.ehealth.commons.protocol.v2.StatusResponseType;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "GetProfessionalContactInfoResponseType",
   propOrder = {"individualContactInformation"}
)
@XmlRootElement(
   name = "GetProfessionalContactInfoResponse"
)
public class GetProfessionalContactInfoResponse extends StatusResponseType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "IndividualContactInformation"
   )
   protected IndividualContactInformationType individualContactInformation;

   public GetProfessionalContactInfoResponse() {
   }

   public IndividualContactInformationType getIndividualContactInformation() {
      return this.individualContactInformation;
   }

   public void setIndividualContactInformation(IndividualContactInformationType value) {
      this.individualContactInformation = value;
   }
}
