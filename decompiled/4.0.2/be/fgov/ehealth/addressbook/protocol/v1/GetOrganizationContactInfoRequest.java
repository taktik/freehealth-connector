package be.fgov.ehealth.addressbook.protocol.v1;

import be.fgov.ehealth.commons.protocol.v2.RequestType;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "GetOrganizationContactInfoRequestType",
   propOrder = {"ehp", "cbe", "nihii", "institutionType"}
)
@XmlRootElement(
   name = "GetOrganizationContactInfoRequest"
)
public class GetOrganizationContactInfoRequest extends RequestType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "EHP"
   )
   protected String ehp;
   @XmlElement(
      name = "CBE"
   )
   protected String cbe;
   @XmlElement(
      name = "NIHII"
   )
   protected String nihii;
   @XmlElement(
      name = "InstitutionType"
   )
   protected String institutionType;

   public GetOrganizationContactInfoRequest() {
   }

   public String getEHP() {
      return this.ehp;
   }

   public void setEHP(String value) {
      this.ehp = value;
   }

   public String getCBE() {
      return this.cbe;
   }

   public void setCBE(String value) {
      this.cbe = value;
   }

   public String getNIHII() {
      return this.nihii;
   }

   public void setNIHII(String value) {
      this.nihii = value;
   }

   public String getInstitutionType() {
      return this.institutionType;
   }

   public void setInstitutionType(String value) {
      this.institutionType = value;
   }
}
