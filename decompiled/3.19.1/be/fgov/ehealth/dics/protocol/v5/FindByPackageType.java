package be.fgov.ehealth.dics.protocol.v5;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "FindByPackageType",
   propOrder = {"anyNamePart", "atcCode", "ctiExtendedCode", "authorisationNr", "commercialised", "inSupplyProblem", "componentEquivalents"}
)
public class FindByPackageType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "AnyNamePart"
   )
   protected String anyNamePart;
   @XmlElement(
      name = "AtcCode"
   )
   protected String atcCode;
   @XmlElement(
      name = "CtiExtendedCode"
   )
   protected String ctiExtendedCode;
   @XmlElement(
      name = "AuthorisationNr"
   )
   protected String authorisationNr;
   @XmlElement(
      name = "Commercialised"
   )
   protected Boolean commercialised;
   @XmlElement(
      name = "InSupplyProblem"
   )
   protected Boolean inSupplyProblem;
   @XmlElement(
      name = "ComponentEquivalent"
   )
   protected List<ComponentEquivalentType> componentEquivalents;

   public String getAnyNamePart() {
      return this.anyNamePart;
   }

   public void setAnyNamePart(String value) {
      this.anyNamePart = value;
   }

   public String getAtcCode() {
      return this.atcCode;
   }

   public void setAtcCode(String value) {
      this.atcCode = value;
   }

   public String getCtiExtendedCode() {
      return this.ctiExtendedCode;
   }

   public void setCtiExtendedCode(String value) {
      this.ctiExtendedCode = value;
   }

   public String getAuthorisationNr() {
      return this.authorisationNr;
   }

   public void setAuthorisationNr(String value) {
      this.authorisationNr = value;
   }

   public Boolean isCommercialised() {
      return this.commercialised;
   }

   public void setCommercialised(Boolean value) {
      this.commercialised = value;
   }

   public Boolean isInSupplyProblem() {
      return this.inSupplyProblem;
   }

   public void setInSupplyProblem(Boolean value) {
      this.inSupplyProblem = value;
   }

   public List<ComponentEquivalentType> getComponentEquivalents() {
      if (this.componentEquivalents == null) {
         this.componentEquivalents = new ArrayList();
      }

      return this.componentEquivalents;
   }
}
