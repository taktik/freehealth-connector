package be.fgov.ehealth.mediprima.core.v1;

import java.io.Serializable;
import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "CarmedIdentifierType",
   propOrder = {"carmedNumber", "versionNumber", "beneficiary", "validityPeriod", "pswc"}
)
public class CarmedIdentifierType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "CarmedNumber",
      required = true
   )
   protected String carmedNumber;
   @XmlElement(
      name = "VersionNumber",
      required = true
   )
   protected BigInteger versionNumber;
   @XmlElement(
      name = "Beneficiary",
      required = true
   )
   protected BeneficiaryType beneficiary;
   @XmlElement(
      name = "ValidityPeriod",
      required = true
   )
   protected PeriodType validityPeriod;
   @XmlElement(
      name = "Pswc",
      required = true
   )
   protected OcmwCpasType pswc;

   public CarmedIdentifierType() {
   }

   public String getCarmedNumber() {
      return this.carmedNumber;
   }

   public void setCarmedNumber(String value) {
      this.carmedNumber = value;
   }

   public BigInteger getVersionNumber() {
      return this.versionNumber;
   }

   public void setVersionNumber(BigInteger value) {
      this.versionNumber = value;
   }

   public BeneficiaryType getBeneficiary() {
      return this.beneficiary;
   }

   public void setBeneficiary(BeneficiaryType value) {
      this.beneficiary = value;
   }

   public PeriodType getValidityPeriod() {
      return this.validityPeriod;
   }

   public void setValidityPeriod(PeriodType value) {
      this.validityPeriod = value;
   }

   public OcmwCpasType getPswc() {
      return this.pswc;
   }

   public void setPswc(OcmwCpasType value) {
      this.pswc = value;
   }
}
