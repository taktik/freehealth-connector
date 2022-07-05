package be.fgov.ehealth.mediprima.core.v1;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "RefundPodmiSppisType",
   propOrder = {"refundCode", "affiliedMutualityInd", "beneficiaryStatus", "justifications", "podmiSppisPart", "medicalUrgencyInd"}
)
public class RefundPodmiSppisType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "RefundCode",
      required = true
   )
   protected BigInteger refundCode;
   @XmlElement(
      name = "AffiliedMutualityInd"
   )
   protected boolean affiliedMutualityInd;
   @XmlElement(
      name = "BeneficiaryStatus",
      required = true
   )
   protected String beneficiaryStatus;
   @XmlElement(
      name = "Justification",
      required = true
   )
   protected List<NameType> justifications;
   @XmlElement(
      name = "PodmiSppisPart",
      required = true
   )
   protected PodmiSppisPartType podmiSppisPart;
   @XmlElement(
      name = "MedicalUrgencyInd"
   )
   protected boolean medicalUrgencyInd;

   public RefundPodmiSppisType() {
   }

   public BigInteger getRefundCode() {
      return this.refundCode;
   }

   public void setRefundCode(BigInteger value) {
      this.refundCode = value;
   }

   public boolean isAffiliedMutualityInd() {
      return this.affiliedMutualityInd;
   }

   public void setAffiliedMutualityInd(boolean value) {
      this.affiliedMutualityInd = value;
   }

   public String getBeneficiaryStatus() {
      return this.beneficiaryStatus;
   }

   public void setBeneficiaryStatus(String value) {
      this.beneficiaryStatus = value;
   }

   public List<NameType> getJustifications() {
      if (this.justifications == null) {
         this.justifications = new ArrayList();
      }

      return this.justifications;
   }

   public PodmiSppisPartType getPodmiSppisPart() {
      return this.podmiSppisPart;
   }

   public void setPodmiSppisPart(PodmiSppisPartType value) {
      this.podmiSppisPart = value;
   }

   public boolean isMedicalUrgencyInd() {
      return this.medicalUrgencyInd;
   }

   public void setMedicalUrgencyInd(boolean value) {
      this.medicalUrgencyInd = value;
   }
}
