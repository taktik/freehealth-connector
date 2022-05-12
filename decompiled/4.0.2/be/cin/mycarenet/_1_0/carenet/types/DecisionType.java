package be.cin.mycarenet._1_0.carenet.types;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "DecisionType",
   propOrder = {"reference", "reason", "justification"}
)
public class DecisionType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Reference",
      required = true
   )
   protected String reference;
   @XmlElement(
      name = "Reason"
   )
   protected String reason;
   @XmlElement(
      name = "Justification"
   )
   protected String justification;
   @XmlAttribute(
      name = "result",
      required = true
   )
   protected DecisionResultType result;

   public DecisionType() {
   }

   public String getReference() {
      return this.reference;
   }

   public void setReference(String value) {
      this.reference = value;
   }

   public String getReason() {
      return this.reason;
   }

   public void setReason(String value) {
      this.reason = value;
   }

   public String getJustification() {
      return this.justification;
   }

   public void setJustification(String value) {
      this.justification = value;
   }

   public DecisionResultType getResult() {
      return this.result;
   }

   public void setResult(DecisionResultType value) {
      this.result = value;
   }
}
