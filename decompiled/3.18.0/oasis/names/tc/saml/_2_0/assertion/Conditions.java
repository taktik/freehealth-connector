package oasis.names.tc.saml._2_0.assertion;

import be.ehealth.technicalconnector.adapter.XmlDateTimeAdapter;
import java.io.Serializable;
import java.util.ArrayList;
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
import org.joda.time.DateTime;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ConditionsType",
   propOrder = {"conditionsAndAudienceRestrictionsAndOneTimeUses"}
)
@XmlRootElement(
   name = "Conditions"
)
public class Conditions implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElements({@XmlElement(
   name = "Condition"
), @XmlElement(
   name = "AudienceRestriction",
   type = AudienceRestriction.class
), @XmlElement(
   name = "OneTimeUse",
   type = OneTimeUse.class
), @XmlElement(
   name = "ProxyRestriction",
   type = ProxyRestriction.class
)})
   protected List<ConditionAbstractType> conditionsAndAudienceRestrictionsAndOneTimeUses;
   @XmlAttribute(
      name = "NotBefore"
   )
   @XmlJavaTypeAdapter(XmlDateTimeAdapter.class)
   @XmlSchemaType(
      name = "dateTime"
   )
   protected DateTime notBefore;
   @XmlAttribute(
      name = "NotOnOrAfter"
   )
   @XmlJavaTypeAdapter(XmlDateTimeAdapter.class)
   @XmlSchemaType(
      name = "dateTime"
   )
   protected DateTime notOnOrAfter;

   public List<ConditionAbstractType> getConditionsAndAudienceRestrictionsAndOneTimeUses() {
      if (this.conditionsAndAudienceRestrictionsAndOneTimeUses == null) {
         this.conditionsAndAudienceRestrictionsAndOneTimeUses = new ArrayList();
      }

      return this.conditionsAndAudienceRestrictionsAndOneTimeUses;
   }

   public DateTime getNotBefore() {
      return this.notBefore;
   }

   public void setNotBefore(DateTime value) {
      this.notBefore = value;
   }

   public DateTime getNotOnOrAfter() {
      return this.notOnOrAfter;
   }

   public void setNotOnOrAfter(DateTime value) {
      this.notOnOrAfter = value;
   }
}
