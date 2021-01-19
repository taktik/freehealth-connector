package oasis.names.tc.saml._2_0.protocol;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "AssertionIDRequestType",
   propOrder = {"assertionIDReves"}
)
@XmlRootElement(
   name = "AssertionIDRequest"
)
public class AssertionIDRequest extends RequestAbstractType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "AssertionIDRef",
      namespace = "urn:oasis:names:tc:SAML:2.0:assertion",
      required = true
   )
   @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
   @XmlSchemaType(
      name = "NCName"
   )
   protected List<String> assertionIDReves;

   public List<String> getAssertionIDReves() {
      if (this.assertionIDReves == null) {
         this.assertionIDReves = new ArrayList();
      }

      return this.assertionIDReves;
   }
}
