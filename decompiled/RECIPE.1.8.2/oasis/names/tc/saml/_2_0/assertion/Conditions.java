package oasis.names.tc.saml._2_0.assertion;

import be.recipe.common.util.CalendarAdapter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ConditionsType",
   propOrder = {"conditionsAndAudienceRestrictionsAndOneTimeUses"}
)
@XmlRootElement(
   name = "Conditions"
)
public class Conditions {
   @XmlElements({@XmlElement(
   name = "Condition"
), @XmlElement(
   name = "AudienceRestriction",
   type = AudienceRestriction.class
), @XmlElement(
   name = "ProxyRestriction",
   type = ProxyRestriction.class
), @XmlElement(
   name = "OneTimeUse",
   type = OneTimeUse.class
)})
   protected List<ConditionAbstractType> conditionsAndAudienceRestrictionsAndOneTimeUses;
   @XmlAttribute(
      name = "NotBefore"
   )
   @XmlJavaTypeAdapter(CalendarAdapter.class)
   @XmlSchemaType(
      name = "dateTime"
   )
   protected Calendar notBefore;
   @XmlAttribute(
      name = "NotOnOrAfter"
   )
   @XmlJavaTypeAdapter(CalendarAdapter.class)
   @XmlSchemaType(
      name = "dateTime"
   )
   protected Calendar notOnOrAfter;

   public List<ConditionAbstractType> getConditionsAndAudienceRestrictionsAndOneTimeUses() {
      if (this.conditionsAndAudienceRestrictionsAndOneTimeUses == null) {
         this.conditionsAndAudienceRestrictionsAndOneTimeUses = new ArrayList();
      }

      return this.conditionsAndAudienceRestrictionsAndOneTimeUses;
   }

   public Calendar getNotBefore() {
      return this.notBefore;
   }

   public void setNotBefore(Calendar value) {
      this.notBefore = value;
   }

   public Calendar getNotOnOrAfter() {
      return this.notOnOrAfter;
   }

   public void setNotOnOrAfter(Calendar value) {
      this.notOnOrAfter = value;
   }
}
