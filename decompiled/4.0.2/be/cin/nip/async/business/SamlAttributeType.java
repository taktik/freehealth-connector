package be.cin.nip.async.business;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import oasis.names.tc.saml._2_0.assertion.Attribute;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "SamlAttributeType",
   propOrder = {"attributes"}
)
public class SamlAttributeType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Attribute",
      namespace = "urn:oasis:names:tc:SAML:2.0:assertion"
   )
   protected List<Attribute> attributes;

   public SamlAttributeType() {
   }

   public List<Attribute> getAttributes() {
      if (this.attributes == null) {
         this.attributes = new ArrayList();
      }

      return this.attributes;
   }
}
