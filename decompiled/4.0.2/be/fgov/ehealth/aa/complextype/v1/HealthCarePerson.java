package be.fgov.ehealth.aa.complextype.v1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import oasis.names.tc.saml._2_0.assertion.Attribute;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "HealthCarePersonType",
   propOrder = {"lastName", "firstName", "middleNames", "attributes", "professionals"}
)
@XmlRootElement(
   name = "HealthCarePerson"
)
public class HealthCarePerson implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "LastName",
      required = true
   )
   protected String lastName;
   @XmlElement(
      name = "FirstName"
   )
   protected String firstName;
   @XmlElement(
      name = "MiddleNames"
   )
   protected String middleNames;
   @XmlElement(
      name = "Attribute",
      namespace = "urn:oasis:names:tc:SAML:2.0:assertion",
      required = true
   )
   protected List<Attribute> attributes;
   @XmlElement(
      name = "Professional",
      required = true
   )
   protected List<ProfessionalType> professionals;
   @XmlAttribute(
      name = "searchResult"
   )
   @XmlSchemaType(
      name = "anySimpleType"
   )
   protected String searchResult;

   public HealthCarePerson() {
   }

   public String getLastName() {
      return this.lastName;
   }

   public void setLastName(String value) {
      this.lastName = value;
   }

   public String getFirstName() {
      return this.firstName;
   }

   public void setFirstName(String value) {
      this.firstName = value;
   }

   public String getMiddleNames() {
      return this.middleNames;
   }

   public void setMiddleNames(String value) {
      this.middleNames = value;
   }

   public List<Attribute> getAttributes() {
      if (this.attributes == null) {
         this.attributes = new ArrayList();
      }

      return this.attributes;
   }

   public List<ProfessionalType> getProfessionals() {
      if (this.professionals == null) {
         this.professionals = new ArrayList();
      }

      return this.professionals;
   }

   public String getSearchResult() {
      return this.searchResult;
   }

   public void setSearchResult(String value) {
      this.searchResult = value;
   }
}
