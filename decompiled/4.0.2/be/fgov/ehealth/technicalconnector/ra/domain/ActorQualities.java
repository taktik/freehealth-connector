package be.fgov.ehealth.technicalconnector.ra.domain;

import java.io.Serializable;
import java.util.List;

public final class ActorQualities implements Serializable {
   private static final long serialVersionUID = 1L;
   private Boolean naturalPersonAuthorization;
   private List<Organization> organizationAuthorizations;

   public ActorQualities() {
   }

   public Boolean getNaturalPersonAuthorization() {
      return this.naturalPersonAuthorization;
   }

   public void setNaturalPersonAuthorization(Boolean naturalPersonAuthorization) {
      this.naturalPersonAuthorization = naturalPersonAuthorization;
   }

   public List<Organization> getOrganizationAuthorizations() {
      return this.organizationAuthorizations;
   }

   public void setOrganizationAuthorizations(List<Organization> organizationAuthorizations) {
      this.organizationAuthorizations = organizationAuthorizations;
   }
}
