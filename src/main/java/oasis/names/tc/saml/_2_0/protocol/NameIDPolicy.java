package oasis.names.tc.saml._2_0.protocol;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "NameIDPolicyType"
)
@XmlRootElement(
   name = "NameIDPolicy"
)
public class NameIDPolicy implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlAttribute(
      name = "Format"
   )
   @XmlSchemaType(
      name = "anyURI"
   )
   protected String format;
   @XmlAttribute(
      name = "SPNameQualifier"
   )
   protected String spNameQualifier;
   @XmlAttribute(
      name = "AllowCreate"
   )
   protected Boolean allowCreate;

   public String getFormat() {
      return this.format;
   }

   public void setFormat(String value) {
      this.format = value;
   }

   public String getSPNameQualifier() {
      return this.spNameQualifier;
   }

   public void setSPNameQualifier(String value) {
      this.spNameQualifier = value;
   }

   public Boolean isAllowCreate() {
      return this.allowCreate;
   }

   public void setAllowCreate(Boolean value) {
      this.allowCreate = value;
   }
}
