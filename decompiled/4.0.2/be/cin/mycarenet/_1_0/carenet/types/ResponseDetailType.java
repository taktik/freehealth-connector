package be.cin.mycarenet._1_0.carenet.types;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ResponseDetailType",
   propOrder = {"consultantDoctor", "decision"}
)
public class ResponseDetailType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "ConsultantDoctor",
      required = true
   )
   protected String consultantDoctor;
   @XmlElement(
      name = "Decision",
      required = true
   )
   protected DecisionType decision;

   public ResponseDetailType() {
   }

   public String getConsultantDoctor() {
      return this.consultantDoctor;
   }

   public void setConsultantDoctor(String value) {
      this.consultantDoctor = value;
   }

   public DecisionType getDecision() {
      return this.decision;
   }

   public void setDecision(DecisionType value) {
      this.decision = value;
   }
}
