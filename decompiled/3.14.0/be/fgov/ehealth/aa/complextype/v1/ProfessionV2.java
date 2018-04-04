package be.fgov.ehealth.aa.complextype.v1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import oasis.names.tc.saml._2_0.assertion.Attribute;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ProfessionTypeV2",
   propOrder = {"professionCodes", "professionFriendlyNames", "attributes", "eMailAddress"}
)
@XmlRootElement(
   name = "ProfessionV2"
)
public class ProfessionV2 implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "ProfessionCode",
      required = true
   )
   protected List<ProfessionCode> professionCodes;
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
   protected String eMailAddress;

   public List<ProfessionCode> getProfessionCodes() {
      if (this.professionCodes == null) {
         this.professionCodes = new ArrayList();
      }

      return this.professionCodes;
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

   public String getEMailAddress() {
      return this.eMailAddress;
   }

   public void setEMailAddress(String value) {
      this.eMailAddress = value;
   }
}
