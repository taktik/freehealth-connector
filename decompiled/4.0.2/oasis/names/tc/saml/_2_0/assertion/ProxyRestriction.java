package oasis.names.tc.saml._2_0.assertion;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ProxyRestrictionType",
   propOrder = {"audiences"}
)
@XmlRootElement(
   name = "ProxyRestriction"
)
public class ProxyRestriction extends ConditionAbstractType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Audience"
   )
   @XmlSchemaType(
      name = "anyURI"
   )
   protected List<String> audiences;
   @XmlAttribute(
      name = "Count"
   )
   @XmlSchemaType(
      name = "nonNegativeInteger"
   )
   protected BigInteger count;

   public ProxyRestriction() {
   }

   public List<String> getAudiences() {
      if (this.audiences == null) {
         this.audiences = new ArrayList();
      }

      return this.audiences;
   }

   public BigInteger getCount() {
      return this.count;
   }

   public void setCount(BigInteger value) {
      this.count = value;
   }
}
