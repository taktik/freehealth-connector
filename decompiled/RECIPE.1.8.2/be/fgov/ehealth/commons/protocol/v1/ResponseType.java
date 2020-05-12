package be.fgov.ehealth.commons.protocol.v1;

import be.fgov.ehealth.commons.core.v1.StatusType;
import be.fgov.ehealth.insurability.protocol.v2.GetInsurabilityForPharmacistResponse;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ResponseType",
   propOrder = {"status"}
)
@XmlSeeAlso({GetInsurabilityForPharmacistResponse.class})
public class ResponseType {
   @XmlElement(
      name = "Status",
      required = true
   )
   protected StatusType status;
   @XmlAttribute(
      name = "Id"
   )
   protected String id;

   public StatusType getStatus() {
      return this.status;
   }

   public void setStatus(StatusType value) {
      this.status = value;
   }

   public String getId() {
      return this.id;
   }

   public void setId(String value) {
      this.id = value;
   }
}
