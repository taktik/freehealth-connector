package be.fgov.ehealth.etee.commons.core.v1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "",
   propOrder = {"nameFr", "nameNl", "identifierType", "validationRegEx", "checkDigitAlgorithm", "etkUsageTypeAuthorizeds"}
)
@XmlRootElement(
   name = "OrganizationTypes"
)
public class OrganizationTypes implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "NameFr",
      required = true
   )
   protected String nameFr;
   @XmlElement(
      name = "NameNl",
      required = true
   )
   protected String nameNl;
   @XmlElement(
      name = "IdentifierType",
      required = true
   )
   protected String identifierType;
   @XmlElement(
      name = "ValidationRegEx",
      required = true
   )
   protected String validationRegEx;
   @XmlElement(
      name = "CheckDigitAlgorithm"
   )
   @XmlSchemaType(
      name = "string"
   )
   protected CheckDigitType checkDigitAlgorithm;
   @XmlElement(
      name = "ETKUsageTypeAuthorized"
   )
   @XmlSchemaType(
      name = "string"
   )
   protected List<ETKUsageType> etkUsageTypeAuthorizeds;

   public String getNameFr() {
      return this.nameFr;
   }

   public void setNameFr(String value) {
      this.nameFr = value;
   }

   public String getNameNl() {
      return this.nameNl;
   }

   public void setNameNl(String value) {
      this.nameNl = value;
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

   public CheckDigitType getCheckDigitAlgorithm() {
      return this.checkDigitAlgorithm;
   }

   public void setCheckDigitAlgorithm(CheckDigitType value) {
      this.checkDigitAlgorithm = value;
   }

   public List<ETKUsageType> getETKUsageTypeAuthorizeds() {
      if (this.etkUsageTypeAuthorizeds == null) {
         this.etkUsageTypeAuthorizeds = new ArrayList();
      }

      return this.etkUsageTypeAuthorizeds;
   }
}
