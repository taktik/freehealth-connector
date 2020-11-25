package be.recipe.services.executor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "validationWarning",
   propOrder = {"ruleId", "ruleMessage", "severity"}
)
public class ValidationWarning {
   @XmlElement(
      required = true
   )
   protected RuleId ruleId;
   @XmlElement(
      required = true
   )
   protected RuleMessage ruleMessage;
   @XmlElement(
      required = true
   )
   protected String severity;

   public RuleId getRuleId() {
      return this.ruleId;
   }

   public void setRuleId(RuleId value) {
      this.ruleId = value;
   }

   public RuleMessage getRuleMessage() {
      return this.ruleMessage;
   }

   public void setRuleMessage(RuleMessage value) {
      this.ruleMessage = value;
   }

   public String getSeverity() {
      return this.severity;
   }

   public void setSeverity(String value) {
      this.severity = value;
   }
}
