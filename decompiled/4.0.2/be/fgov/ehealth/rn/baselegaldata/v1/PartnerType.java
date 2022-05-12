package be.fgov.ehealth.rn.baselegaldata.v1;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "PartnerType",
   propOrder = {"partnerFictionalIdentificationNumber", "partnerSsin", "partnerName"}
)
public class PartnerType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "PartnerFictionalIdentificationNumber"
   )
   protected String partnerFictionalIdentificationNumber;
   @XmlElement(
      name = "PartnerSsin"
   )
   protected String partnerSsin;
   @XmlElement(
      name = "PartnerName"
   )
   protected MinimalNameInfoType partnerName;

   public PartnerType() {
   }

   public String getPartnerFictionalIdentificationNumber() {
      return this.partnerFictionalIdentificationNumber;
   }

   public void setPartnerFictionalIdentificationNumber(String value) {
      this.partnerFictionalIdentificationNumber = value;
   }

   public String getPartnerSsin() {
      return this.partnerSsin;
   }

   public void setPartnerSsin(String value) {
      this.partnerSsin = value;
   }

   public MinimalNameInfoType getPartnerName() {
      return this.partnerName;
   }

   public void setPartnerName(MinimalNameInfoType value) {
      this.partnerName = value;
   }
}
