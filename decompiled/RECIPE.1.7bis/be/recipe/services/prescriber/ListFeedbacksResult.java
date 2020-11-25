package be.recipe.services.prescriber;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(
   namespace = "http:/services.recipe.be/prescriber"
)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ListFeedbacksResult"
)
public class ListFeedbacksResult {
   @XmlElement(
      name = "feedbacks"
   )
   private List<ListFeedbackItem> listOfFeedbacks = new ArrayList();

   public List<ListFeedbackItem> getFeedbacks() {
      return this.listOfFeedbacks;
   }

   public void setFeedbacks(List<ListFeedbackItem> listOfFeedbacks) {
      this.listOfFeedbacks = listOfFeedbacks;
   }

   public boolean add(ListFeedbackItem e) {
      return this.listOfFeedbacks.add(e);
   }
}
