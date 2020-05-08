package be.fgov.ehealth.aa.complextype.v1;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import oasis.names.tc.saml._2_0.assertion.AttributeStatement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "GIAMIEntityType",
   propOrder = {"attributeStatement", "giamiAdministrator"}
)
@XmlSeeAlso({GIAMIOrganization.class})
public class GIAMIEntityType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "AttributeStatement",
      namespace = "urn:oasis:names:tc:SAML:2.0:assertion",
      required = true
   )
   protected AttributeStatement attributeStatement;
   @XmlElement(
      name = "GIAMIAdministrator"
   )
   protected GIAMIAdministrator giamiAdministrator;

   public AttributeStatement getAttributeStatement() {
      return this.attributeStatement;
   }

   public void setAttributeStatement(AttributeStatement value) {
      this.attributeStatement = value;
   }

   public GIAMIAdministrator getGIAMIAdministrator() {
      return this.giamiAdministrator;
   }

   public void setGIAMIAdministrator(GIAMIAdministrator value) {
      this.giamiAdministrator = value;
   }
}
