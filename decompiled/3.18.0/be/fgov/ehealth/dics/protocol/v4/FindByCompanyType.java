package be.fgov.ehealth.dics.protocol.v4;

import be.fgov.ehealth.dics.core.v4.company.submit.VatNrPerCountryType;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "FindByCompanyType",
   propOrder = {"vatNr", "anyNamePart", "companyActorNr"}
)
public class FindByCompanyType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "VatNr"
   )
   protected VatNrPerCountryType vatNr;
   @XmlElement(
      name = "AnyNamePart"
   )
   protected String anyNamePart;
   @XmlElement(
      name = "CompanyActorNr"
   )
   protected String companyActorNr;

   public VatNrPerCountryType getVatNr() {
      return this.vatNr;
   }

   public void setVatNr(VatNrPerCountryType value) {
      this.vatNr = value;
   }

   public String getAnyNamePart() {
      return this.anyNamePart;
   }

   public void setAnyNamePart(String value) {
      this.anyNamePart = value;
   }

   public String getCompanyActorNr() {
      return this.companyActorNr;
   }

   public void setCompanyActorNr(String value) {
      this.companyActorNr = value;
   }
}
