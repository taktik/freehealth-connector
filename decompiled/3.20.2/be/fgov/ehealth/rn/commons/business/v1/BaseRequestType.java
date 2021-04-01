package be.fgov.ehealth.rn.commons.business.v1;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "BaseRequestType",
   propOrder = {"informationCustomer", "informationCBSS", "legalContext"}
)
public abstract class BaseRequestType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "InformationCustomer",
      required = true
   )
   protected InformationCustomerType informationCustomer;
   @XmlElement(
      name = "InformationCBSS"
   )
   protected InformationCBSSType informationCBSS;
   @XmlElement(
      name = "LegalContext",
      required = true
   )
   protected String legalContext;

   public InformationCustomerType getInformationCustomer() {
      return this.informationCustomer;
   }

   public void setInformationCustomer(InformationCustomerType value) {
      this.informationCustomer = value;
   }

   public InformationCBSSType getInformationCBSS() {
      return this.informationCBSS;
   }

   public void setInformationCBSS(InformationCBSSType value) {
      this.informationCBSS = value;
   }

   public String getLegalContext() {
      return this.legalContext;
   }

   public void setLegalContext(String value) {
      this.legalContext = value;
   }
}
