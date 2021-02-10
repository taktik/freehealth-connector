package be.cin.nippin.memberdata.saml.extension;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import oasis.names.tc.saml._2_0.protocol.AttributeQuery;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "",
   propOrder = {"attributeQueries"}
)
@XmlRootElement(
   name = "AttributeQueryList"
)
public class AttributeQueryList implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "AttributeQuery",
      namespace = "urn:oasis:names:tc:SAML:2.0:protocol",
      required = true
   )
   protected List<AttributeQuery> attributeQueries;

   public List<AttributeQuery> getAttributeQueries() {
      if (this.attributeQueries == null) {
         this.attributeQueries = new ArrayList();
      }

      return this.attributeQueries;
   }
}
