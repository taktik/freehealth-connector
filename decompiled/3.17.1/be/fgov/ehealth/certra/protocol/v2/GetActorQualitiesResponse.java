package be.fgov.ehealth.certra.protocol.v2;

import be.fgov.ehealth.certra.core.v2.OrganizationType;
import be.fgov.ehealth.commons.protocol.v2.StatusResponseType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "GetActorQualitiesResponseType",
   propOrder = {"naturalPersonAuthorization", "organizationAuthorizations"}
)
@XmlRootElement(
   name = "GetActorQualitiesResponse"
)
public class GetActorQualitiesResponse extends StatusResponseType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "NaturalPersonAuthorization"
   )
   protected Boolean naturalPersonAuthorization;
   @XmlElement(
      name = "OrganizationAuthorization"
   )
   protected List<OrganizationType> organizationAuthorizations;

   public Boolean isNaturalPersonAuthorization() {
      return this.naturalPersonAuthorization;
   }

   public void setNaturalPersonAuthorization(Boolean value) {
      this.naturalPersonAuthorization = value;
   }

   public List<OrganizationType> getOrganizationAuthorizations() {
      if (this.organizationAuthorizations == null) {
         this.organizationAuthorizations = new ArrayList();
      }

      return this.organizationAuthorizations;
   }
}
