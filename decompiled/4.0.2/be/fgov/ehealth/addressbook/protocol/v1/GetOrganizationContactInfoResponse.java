package be.fgov.ehealth.addressbook.protocol.v1;

import be.fgov.ehealth.addressbook.core.v1.OrganizationContactInformationType;
import be.fgov.ehealth.commons.protocol.v2.StatusResponseType;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "GetOrganizationContactInfoResponseType",
   propOrder = {"organizationContactInformation"}
)
@XmlRootElement(
   name = "GetOrganizationContactInfoResponse"
)
public class GetOrganizationContactInfoResponse extends StatusResponseType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "OrganizationContactInformation"
   )
   protected OrganizationContactInformationType organizationContactInformation;

   public GetOrganizationContactInfoResponse() {
   }

   public OrganizationContactInformationType getOrganizationContactInformation() {
      return this.organizationContactInformation;
   }

   public void setOrganizationContactInformation(OrganizationContactInformationType value) {
      this.organizationContactInformation = value;
   }
}
