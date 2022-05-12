package be.fgov.ehealth.certra.core.v2;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "OrganizationIdentifierType",
   propOrder = {"name", "identifierType", "validationRegEx", "checkDigitAlgorithms", "availableBaseServices"}
)
public class OrganizationIdentifierType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Name",
      required = true
   )
   protected TextType name;
   @XmlElement(
      name = "IdentifierType",
      required = true
   )
   @XmlSchemaType(
      name = "anyURI"
   )
   protected String identifierType;
   @XmlElement(
      name = "ValidationRegEx"
   )
   protected String validationRegEx;
   @XmlElement(
      name = "CheckDigitAlgorithm"
   )
   protected List<String> checkDigitAlgorithms;
   @XmlElement(
      name = "AvailableBaseService"
   )
   protected List<String> availableBaseServices;

   public OrganizationIdentifierType() {
   }

   public TextType getName() {
      return this.name;
   }

   public void setName(TextType value) {
      this.name = value;
   }

   public String getIdentifierType() {
      return this.identifierType;
   }

   public void setIdentifierType(String value) {
      this.identifierType = value;
   }

   public String getValidationRegEx() {
      return this.validationRegEx;
   }

   public void setValidationRegEx(String value) {
      this.validationRegEx = value;
   }

   public List<String> getCheckDigitAlgorithms() {
      if (this.checkDigitAlgorithms == null) {
         this.checkDigitAlgorithms = new ArrayList();
      }

      return this.checkDigitAlgorithms;
   }

   public List<String> getAvailableBaseServices() {
      if (this.availableBaseServices == null) {
         this.availableBaseServices = new ArrayList();
      }

      return this.availableBaseServices;
   }
}
