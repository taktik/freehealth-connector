package be.fgov.ehealth.dics.protocol.v3;

import be.ehealth.technicalconnector.adapter.XmlDateAdapter;
import be.fgov.ehealth.commons.protocol.v2.RequestType;
import be.fgov.ehealth.dics.core.v3.company.submit.VatNrPerCountryType;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.joda.time.DateTime;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "FindCompanyRequestType",
   propOrder = {"vatNr", "anyNamePart", "companyActorNr"}
)
@XmlRootElement(
   name = "FindCompanyRequest"
)
public class FindCompanyRequest extends RequestType implements Serializable {
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
   @XmlAttribute(
      name = "lang",
      namespace = "http://www.w3.org/XML/1998/namespace",
      required = true
   )
   protected String lang;
   @XmlAttribute(
      name = "SearchDate"
   )
   @XmlJavaTypeAdapter(XmlDateAdapter.class)
   @XmlSchemaType(
      name = "date"
   )
   protected DateTime searchDate;

   public FindCompanyRequest() {
   }

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

   public String getLang() {
      return this.lang;
   }

   public void setLang(String value) {
      this.lang = value;
   }

   public DateTime getSearchDate() {
      return this.searchDate;
   }

   public void setSearchDate(DateTime value) {
      this.searchDate = value;
   }
}
