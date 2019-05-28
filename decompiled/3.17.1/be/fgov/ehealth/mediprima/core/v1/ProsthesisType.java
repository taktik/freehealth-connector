package be.fgov.ehealth.mediprima.core.v1;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ProsthesisType",
   propOrder = {"companyList"}
)
public class ProsthesisType extends MedicalCoverCommonInformationType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "CompanyList"
   )
   protected CompanyListType companyList;

   public CompanyListType getCompanyList() {
      return this.companyList;
   }

   public void setCompanyList(CompanyListType value) {
      this.companyList = value;
   }
}
