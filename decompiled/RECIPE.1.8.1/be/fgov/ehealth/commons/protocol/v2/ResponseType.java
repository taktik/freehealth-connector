package be.fgov.ehealth.commons.protocol.v2;

import be.recipe.common.util.JodaDateTimeAdapter;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.joda.time.DateTime;
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
   name = "ResponseType"
)
@XmlSeeAlso({PaginationResponseType.class, StatusResponseType.class})
public class ResponseType implements Equals, HashCode, ToString {
   @XmlAttribute(
      name = "Id",
      required = true
   )
   @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
   @XmlID
   @XmlSchemaType(
      name = "ID"
   )
   protected String id;
   @XmlAttribute(
      name = "InResponseTo"
   )
   protected String inResponseTo;
   @XmlAttribute(
      name = "IssueInstant",
      required = true
   )
   @XmlJavaTypeAdapter(JodaDateTimeAdapter.class)
   @XmlSchemaType(
      name = "dateTime"
   )
   protected DateTime issueInstant;

   public String getId() {
      return this.id;
   }

   public void setId(String value) {
      this.id = value;
   }

   public String getInResponseTo() {
      return this.inResponseTo;
   }

   public void setInResponseTo(String value) {
      this.inResponseTo = value;
   }

   public DateTime getIssueInstant() {
      return this.issueInstant;
   }

   public void setIssueInstant(DateTime value) {
      this.issueInstant = value;
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
      String theInResponseTo = this.getId();
      strategy.appendField(locator, this, "id", buffer, theInResponseTo);
      theInResponseTo = this.getInResponseTo();
      strategy.appendField(locator, this, "inResponseTo", buffer, theInResponseTo);
      DateTime theIssueInstant = this.getIssueInstant();
      strategy.appendField(locator, this, "issueInstant", buffer, theIssueInstant);
      return buffer;
   }

   public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
      if (!(object instanceof ResponseType)) {
         return false;
      } else if (this == object) {
         return true;
      } else {
         ResponseType that = (ResponseType)object;
         String lhsInResponseTo = this.getId();
         String rhsInResponseTo = that.getId();
         if (!strategy.equals(LocatorUtils.property(thisLocator, "id", lhsInResponseTo), LocatorUtils.property(thatLocator, "id", rhsInResponseTo), lhsInResponseTo, rhsInResponseTo)) {
            return false;
         } else {
            lhsInResponseTo = this.getInResponseTo();
            rhsInResponseTo = that.getInResponseTo();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "inResponseTo", lhsInResponseTo), LocatorUtils.property(thatLocator, "inResponseTo", rhsInResponseTo), lhsInResponseTo, rhsInResponseTo)) {
               return false;
            } else {
               DateTime lhsIssueInstant = this.getIssueInstant();
               DateTime rhsIssueInstant = that.getIssueInstant();
               return strategy.equals(LocatorUtils.property(thisLocator, "issueInstant", lhsIssueInstant), LocatorUtils.property(thatLocator, "issueInstant", rhsIssueInstant), lhsIssueInstant, rhsIssueInstant);
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
      String theInResponseTo = this.getId();
      int currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "id", theInResponseTo), currentHashCode, theInResponseTo);
      theInResponseTo = this.getInResponseTo();
      currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "inResponseTo", theInResponseTo), currentHashCode, theInResponseTo);
      DateTime theIssueInstant = this.getIssueInstant();
      currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "issueInstant", theIssueInstant), currentHashCode, theIssueInstant);
      return currentHashCode;
   }

   public int hashCode() {
      HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
      return this.hashCode((ObjectLocator)null, strategy);
   }
}
