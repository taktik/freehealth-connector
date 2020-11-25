package oasis.names.tc.saml._2_0.assertion;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "BaseIDAbstractType"
)
@XmlRootElement(
   name = "BaseID"
)
public abstract class BaseID {
   @XmlAttribute(
      name = "NameQualifier"
   )
   protected String nameQualifier;
   @XmlAttribute(
      name = "SPNameQualifier"
   )
   protected String spNameQualifier;

   public String getNameQualifier() {
      return this.nameQualifier;
   }

   public void setNameQualifier(String value) {
      this.nameQualifier = value;
   }

   public String getSPNameQualifier() {
      return this.spNameQualifier;
   }

   public void setSPNameQualifier(String value) {
      this.spNameQualifier = value;
   }
}
