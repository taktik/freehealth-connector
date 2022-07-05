package be.fgov.ehealth.mediprima.core.v1;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "CarmedContentCareType",
   propOrder = {"medicalCover", "refundPodmiSppis"}
)
public class CarmedContentCareType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "MedicalCover"
   )
   protected MedicalCoverType medicalCover;
   @XmlElement(
      name = "RefundPodmiSppis"
   )
   protected RefundPodmiSppisType refundPodmiSppis;

   public CarmedContentCareType() {
   }

   public MedicalCoverType getMedicalCover() {
      return this.medicalCover;
   }

   public void setMedicalCover(MedicalCoverType value) {
      this.medicalCover = value;
   }

   public RefundPodmiSppisType getRefundPodmiSppis() {
      return this.refundPodmiSppis;
   }

   public void setRefundPodmiSppis(RefundPodmiSppisType value) {
      this.refundPodmiSppis = value;
   }
}
