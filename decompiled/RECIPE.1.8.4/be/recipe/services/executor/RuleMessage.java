package be.recipe.services.executor;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ruleMessage",
   propOrder = {"messageText"}
)
public class RuleMessage {
   @XmlElement(
      required = true
   )
   protected List<MessageText> messageText;

   public List<MessageText> getMessageText() {
      if (this.messageText == null) {
         this.messageText = new ArrayList();
      }

      return this.messageText;
   }
}
