package be.recipe.services.prescriber;

import be.recipe.services.core.ResponseType;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "listFeedbacksResult",
   propOrder = {"feedbacks"}
)
@XmlRootElement(
   name = "listFeedbacksResult"
)
public class ListFeedbacksResult extends ResponseType {
   protected List<ListFeedbackItem> feedbacks;

   public List<ListFeedbackItem> getFeedbacks() {
      if (this.feedbacks == null) {
         this.feedbacks = new ArrayList();
      }

      return this.feedbacks;
   }
}
