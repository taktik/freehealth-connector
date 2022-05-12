package be.fgov.ehealth.consultrn.commons.core.v3;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "PartnerType",
   propOrder = {"partnerReference", "partnerSsin", "partnerBirthDate", "partnerName"}
)
public class PartnerType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "PartnerReference"
   )
   protected String partnerReference;
   @XmlElement(
      name = "PartnerSsin"
   )
   protected String partnerSsin;
   @XmlElement(
      name = "PartnerBirthDate"
   )
   protected String partnerBirthDate;
   @XmlElement(
      name = "PartnerName"
   )
   protected PersonNameResponseType partnerName;

   public PartnerType() {
   }

   public String getPartnerReference() {
      return this.partnerReference;
   }

   public void setPartnerReference(String value) {
      this.partnerReference = value;
   }

   public String getPartnerSsin() {
      return this.partnerSsin;
   }

   public void setPartnerSsin(String value) {
      this.partnerSsin = value;
   }

   public String getPartnerBirthDate() {
      return this.partnerBirthDate;
   }

   public void setPartnerBirthDate(String value) {
      this.partnerBirthDate = value;
   }

   public PersonNameResponseType getPartnerName() {
      return this.partnerName;
   }

   public void setPartnerName(PersonNameResponseType value) {
      this.partnerName = value;
   }
}
