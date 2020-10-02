package oasis.names.tc.saml._1_0.assertion;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.namespace.QName;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "AuthorityBindingType"
)
@XmlRootElement(
   name = "AuthorityBinding"
)
public class AuthorityBinding implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlAttribute(
      name = "AuthorityKind",
      required = true
   )
   protected QName authorityKind;
   @XmlAttribute(
      name = "Location",
      required = true
   )
   @XmlSchemaType(
      name = "anyURI"
   )
   protected String location;
   @XmlAttribute(
      name = "Binding",
      required = true
   )
   @XmlSchemaType(
      name = "anyURI"
   )
   protected String binding;

   public QName getAuthorityKind() {
      return this.authorityKind;
   }

   public void setAuthorityKind(QName value) {
      this.authorityKind = value;
   }

   public String getLocation() {
      return this.location;
   }

   public void setLocation(String value) {
      this.location = value;
   }

   public String getBinding() {
      return this.binding;
   }

   public void setBinding(String value) {
      this.binding = value;
   }
}
