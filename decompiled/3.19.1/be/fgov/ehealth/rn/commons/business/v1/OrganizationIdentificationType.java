package be.fgov.ehealth.rn.commons.business.v1;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "OrganizationIdentificationType",
   propOrder = {"sector", "institution", "cbeNumber"}
)
public class OrganizationIdentificationType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Sector"
   )
   protected Integer sector;
   @XmlElement(
      name = "Institution"
   )
   protected Integer institution;
   @XmlElement(
      name = "CbeNumber"
   )
   protected String cbeNumber;

   public Integer getSector() {
      return this.sector;
   }

   public void setSector(Integer value) {
      this.sector = value;
   }

   public Integer getInstitution() {
      return this.institution;
   }

   public void setInstitution(Integer value) {
      this.institution = value;
   }

   public String getCbeNumber() {
      return this.cbeNumber;
   }

   public void setCbeNumber(String value) {
      this.cbeNumber = value;
   }
}
