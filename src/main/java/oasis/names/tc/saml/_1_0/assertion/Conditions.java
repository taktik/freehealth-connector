package oasis.names.tc.saml._1_0.assertion;

import org.taktik.connector.technical.adapter.XmlDateTimeAdapter;
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
   propOrder = {"audienceRestrictionConditionsAndDoNotCacheConditionsAndConditions"}
)
@XmlRootElement(
   name = "Conditions"
)
public class Conditions implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElements({@XmlElement(
   name = "AudienceRestrictionCondition",
   type = AudienceRestrictionCondition.class
), @XmlElement(
   name = "DoNotCacheCondition",
   type = DoNotCacheCondition.class
), @XmlElement(
   name = "Condition"
)})
   protected List<ConditionAbstractType> audienceRestrictionConditionsAndDoNotCacheConditionsAndConditions;
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

   public List<ConditionAbstractType> getAudienceRestrictionConditionsAndDoNotCacheConditionsAndConditions() {
      if (this.audienceRestrictionConditionsAndDoNotCacheConditionsAndConditions == null) {
         this.audienceRestrictionConditionsAndDoNotCacheConditionsAndConditions = new ArrayList();
      }

      return this.audienceRestrictionConditionsAndDoNotCacheConditionsAndConditions;
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
