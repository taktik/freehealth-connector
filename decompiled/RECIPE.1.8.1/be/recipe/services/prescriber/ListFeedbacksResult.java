package be.recipe.services.prescriber;

import be.recipe.services.core.ResponseType;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.jvnet.jaxb2_commons.lang.Equals;
import org.jvnet.jaxb2_commons.lang.EqualsStrategy;
import org.jvnet.jaxb2_commons.lang.HashCode;
import org.jvnet.jaxb2_commons.lang.HashCodeStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBEqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBHashCodeStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;
import org.jvnet.jaxb2_commons.locator.util.LocatorUtils;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "listFeedbacksResult",
   propOrder = {"feedbacks"}
)
@XmlRootElement(
   name = "listFeedbacksResult"
)
public class ListFeedbacksResult extends ResponseType implements Equals, HashCode, ToString {
   protected List<ListFeedbackItem> feedbacks;

   public List<ListFeedbackItem> getFeedbacks() {
      if (this.feedbacks == null) {
         this.feedbacks = new ArrayList();
      }

      return this.feedbacks;
   }

   public String toString() {
      ToStringStrategy strategy = JAXBToStringStrategy.INSTANCE;
      StringBuilder buffer = new StringBuilder();
      this.append((ObjectLocator)null, buffer, strategy);
      return buffer.toString();
   }

   public StringBuilder append(ObjectLocator locator, StringBuilder buffer, ToStringStrategy strategy) {
      strategy.appendStart(locator, this, buffer);
      this.appendFields(locator, buffer, strategy);
      strategy.appendEnd(locator, this, buffer);
      return buffer;
   }

   public StringBuilder appendFields(ObjectLocator locator, StringBuilder buffer, ToStringStrategy strategy) {
      super.appendFields(locator, buffer, strategy);
      List<ListFeedbackItem> theFeedbacks = this.feedbacks != null && !this.feedbacks.isEmpty() ? this.getFeedbacks() : null;
      strategy.appendField(locator, this, "feedbacks", buffer, theFeedbacks);
      return buffer;
   }

   public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
      if (!(object instanceof ListFeedbacksResult)) {
         return false;
      } else if (this == object) {
         return true;
      } else if (!super.equals(thisLocator, thatLocator, object, strategy)) {
         return false;
      } else {
         ListFeedbacksResult that = (ListFeedbacksResult)object;
         List<ListFeedbackItem> lhsFeedbacks = this.feedbacks != null && !this.feedbacks.isEmpty() ? this.getFeedbacks() : null;
         List<ListFeedbackItem> rhsFeedbacks = that.feedbacks != null && !that.feedbacks.isEmpty() ? that.getFeedbacks() : null;
         return strategy.equals(LocatorUtils.property(thisLocator, "feedbacks", lhsFeedbacks), LocatorUtils.property(thatLocator, "feedbacks", rhsFeedbacks), lhsFeedbacks, rhsFeedbacks);
      }
   }

   public boolean equals(Object object) {
      EqualsStrategy strategy = JAXBEqualsStrategy.INSTANCE;
      return this.equals((ObjectLocator)null, (ObjectLocator)null, object, strategy);
   }

   public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
      int currentHashCode = super.hashCode(locator, strategy);
      List<ListFeedbackItem> theFeedbacks = this.feedbacks != null && !this.feedbacks.isEmpty() ? this.getFeedbacks() : null;
      currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "feedbacks", theFeedbacks), currentHashCode, theFeedbacks);
      return currentHashCode;
   }

   public int hashCode() {
      HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
      return this.hashCode((ObjectLocator)null, strategy);
   }
}
