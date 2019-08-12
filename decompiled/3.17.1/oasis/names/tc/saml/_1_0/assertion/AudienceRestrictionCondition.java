package oasis.names.tc.saml._1_0.assertion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "AudienceRestrictionConditionType",
   propOrder = {"audiences"}
)
@XmlRootElement(
   name = "AudienceRestrictionCondition"
)
public class AudienceRestrictionCondition extends ConditionAbstractType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Audience",
      required = true
   )
   @XmlSchemaType(
      name = "anyURI"
   )
   protected List<String> audiences;

   public List<String> getAudiences() {
      if (this.audiences == null) {
         this.audiences = new ArrayList();
      }

      return this.audiences;
   }
}
