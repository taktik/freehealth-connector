package be.apb.standards.smoa.schema.model.v1;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "DrugRelatedProblemType",
   propOrder = {"cause", "intervention", "result"}
)
public class DrugRelatedProblemType {
   protected List<String> cause;
   protected List<String> intervention;
   protected List<String> result;

   public List<String> getCause() {
      if (this.cause == null) {
         this.cause = new ArrayList();
      }

      return this.cause;
   }

   public List<String> getIntervention() {
      if (this.intervention == null) {
         this.intervention = new ArrayList();
      }

      return this.intervention;
   }

   public List<String> getResult() {
      if (this.result == null) {
         this.result = new ArrayList();
      }

      return this.result;
   }
}
