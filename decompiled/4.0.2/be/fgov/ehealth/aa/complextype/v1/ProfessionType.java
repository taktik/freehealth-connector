package be.fgov.ehealth.aa.complextype.v1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import oasis.names.tc.saml._2_0.assertion.Attribute;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ProfessionType",
   propOrder = {"professionCode", "professionFriendlyNames", "attributes"}
)
@XmlSeeAlso({ProfessionalType.class})
public class ProfessionType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "ProfessionCode",
      required = true
   )
   protected String professionCode;
   @XmlElement(
      name = "ProfessionFriendlyName",
      required = true
   )
   protected List<NameType> professionFriendlyNames;
   @XmlElement(
      name = "Attribute",
      namespace = "urn:oasis:names:tc:SAML:2.0:assertion"
   )
   protected List<Attribute> attributes;

   public ProfessionType() {
   }

   public String getProfessionCode() {
      return this.professionCode;
   }

   public void setProfessionCode(String value) {
      this.professionCode = value;
   }

   public List<NameType> getProfessionFriendlyNames() {
      if (this.professionFriendlyNames == null) {
         this.professionFriendlyNames = new ArrayList();
      }

      return this.professionFriendlyNames;
   }

   public List<Attribute> getAttributes() {
      if (this.attributes == null) {
         this.attributes = new ArrayList();
      }

      return this.attributes;
   }
}
