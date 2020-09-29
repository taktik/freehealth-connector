package be.recipe.services.prescriber;

import be.recipe.common.util.CalendarAdapter;
import java.util.Calendar;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
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
   name = "listFeedbackItem",
   propOrder = {"rid", "sentBy", "sentDate", "content"}
)
@XmlRootElement(
   name = "listFeedbackItem"
)
public class ListFeedbackItem implements Equals, HashCode, ToString {
   @XmlElement(
      required = true
   )
   protected String rid;
   @XmlElement(
      required = true
   )
   protected String sentBy;
   @XmlElement(
      required = true,
      type = String.class
   )
   @XmlJavaTypeAdapter(CalendarAdapter.class)
   @XmlSchemaType(
      name = "dateTime"
   )
   protected Calendar sentDate;
   @XmlElement(
      required = true
   )
   protected byte[] content;

   public String getRid() {
      return this.rid;
   }

   public void setRid(String value) {
      this.rid = value;
   }

   public String getSentBy() {
      return this.sentBy;
   }

   public void setSentBy(String value) {
      this.sentBy = value;
   }

   public Calendar getSentDate() {
      return this.sentDate;
   }

   public void setSentDate(Calendar value) {
      this.sentDate = value;
   }

   public byte[] getContent() {
      return this.content;
   }

   public void setContent(byte[] value) {
      this.content = value;
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
      String theSentBy = this.getRid();
      strategy.appendField(locator, this, "rid", buffer, theSentBy);
      theSentBy = this.getSentBy();
      strategy.appendField(locator, this, "sentBy", buffer, theSentBy);
      Calendar theSentDate = this.getSentDate();
      strategy.appendField(locator, this, "sentDate", buffer, theSentDate);
      byte[] theContent = this.getContent();
      strategy.appendField(locator, this, "content", buffer, theContent);
      return buffer;
   }

   public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
      if (!(object instanceof ListFeedbackItem)) {
         return false;
      } else if (this == object) {
         return true;
      } else {
         ListFeedbackItem that = (ListFeedbackItem)object;
         String lhsSentBy = this.getRid();
         String rhsSentBy = that.getRid();
         if (!strategy.equals(LocatorUtils.property(thisLocator, "rid", lhsSentBy), LocatorUtils.property(thatLocator, "rid", rhsSentBy), lhsSentBy, rhsSentBy)) {
            return false;
         } else {
            lhsSentBy = this.getSentBy();
            rhsSentBy = that.getSentBy();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "sentBy", lhsSentBy), LocatorUtils.property(thatLocator, "sentBy", rhsSentBy), lhsSentBy, rhsSentBy)) {
               return false;
            } else {
               Calendar lhsSentDate = this.getSentDate();
               Calendar rhsSentDate = that.getSentDate();
               if (!strategy.equals(LocatorUtils.property(thisLocator, "sentDate", lhsSentDate), LocatorUtils.property(thatLocator, "sentDate", rhsSentDate), lhsSentDate, rhsSentDate)) {
                  return false;
               } else {
                  byte[] lhsContent = this.getContent();
                  byte[] rhsContent = that.getContent();
                  return strategy.equals(LocatorUtils.property(thisLocator, "content", lhsContent), LocatorUtils.property(thatLocator, "content", rhsContent), lhsContent, rhsContent);
               }
            }
         }
      }
   }

   public boolean equals(Object object) {
      EqualsStrategy strategy = JAXBEqualsStrategy.INSTANCE;
      return this.equals((ObjectLocator)null, (ObjectLocator)null, object, strategy);
   }

   public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
      int currentHashCode = 1;
      String theSentBy = this.getRid();
      int currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "rid", theSentBy), currentHashCode, theSentBy);
      theSentBy = this.getSentBy();
      currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "sentBy", theSentBy), currentHashCode, theSentBy);
      Calendar theSentDate = this.getSentDate();
      currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "sentDate", theSentDate), currentHashCode, theSentDate);
      byte[] theContent = this.getContent();
      currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "content", theContent), currentHashCode, theContent);
      return currentHashCode;
   }

   public int hashCode() {
      HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
      return this.hashCode((ObjectLocator)null, strategy);
   }
}
