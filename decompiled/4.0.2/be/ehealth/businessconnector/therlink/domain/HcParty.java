package be.ehealth.businessconnector.therlink.domain;

import be.ehealth.businessconnector.therlink.exception.TherLinkBusinessConnectorException;
import be.ehealth.businessconnector.therlink.exception.TherLinkBusinessConnectorExceptionValues;
import be.fgov.ehealth.standards.kmehr.cd.v1.CDHCPARTY;
import be.fgov.ehealth.standards.kmehr.id.v1.IDHCPARTY;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.builder.EqualsBuilder;

/** @deprecated */
@Deprecated
public class HcParty implements Serializable {
   private static final long serialVersionUID = 5656836929856945713L;
   private String type;
   private String applicationID;
   private String nihii;
   private String inss;
   private String hubId;
   private String cbe;
   private String name;
   private String firstName;
   private String familyName;
   private String eHP;
   private List<IDHCPARTY> ids = new ArrayList();
   private List<CDHCPARTY> cds = new ArrayList();

   public HcParty() {
   }

   public List<IDHCPARTY> getIds() {
      return this.ids;
   }

   public void setIds(List<IDHCPARTY> ids) {
      this.ids.clear();
      this.ids.addAll(ids);
   }

   public List<CDHCPARTY> getCds() {
      return this.cds;
   }

   public void setCds(List<CDHCPARTY> cds) {
      this.cds.clear();
      this.cds.addAll(cds);
   }

   public String getType() {
      return this.type;
   }

   public void setType(String type) {
      this.type = type;
   }

   /** @deprecated */
   @Deprecated
   public String getNihii() {
      return this.nihii;
   }

   /** @deprecated */
   @Deprecated
   public void setNihii(String nihii) {
      this.nihii = nihii;
   }

   /** @deprecated */
   @Deprecated
   public String getInss() {
      return this.inss;
   }

   /** @deprecated */
   @Deprecated
   public void setInss(String inss) {
      this.inss = inss;
   }

   /** @deprecated */
   @Deprecated
   public String getHubId() {
      return this.hubId;
   }

   /** @deprecated */
   @Deprecated
   public void setHubId(String hubId) {
      this.hubId = hubId;
   }

   /** @deprecated */
   @Deprecated
   public void setEHP(String ehp) {
      this.eHP = ehp;
   }

   /** @deprecated */
   @Deprecated
   public String getEHP() {
      return this.eHP;
   }

   /** @deprecated */
   @Deprecated
   public String getCbe() {
      return this.cbe;
   }

   /** @deprecated */
   @Deprecated
   public void setCbe(String cbe) {
      this.cbe = cbe;
   }

   public String getName() {
      return this.name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getFirstName() {
      return this.firstName;
   }

   public void setFirstName(String firstName) {
      this.firstName = firstName;
   }

   public String getFamilyName() {
      return this.familyName;
   }

   public void setFamilyName(String familyName) {
      this.familyName = familyName;
   }

   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      } else if (obj == null) {
         return false;
      } else if (this.getClass() != obj.getClass()) {
         return false;
      } else {
         HcParty other = (HcParty)obj;
         return (new EqualsBuilder()).append(this.cds, other.getCds()).append(this.ids, other.getIds()).append(this.familyName, other.getFamilyName()).append(this.firstName, other.getFirstName()).append(this.name, other.getName()).append(this.type, other.getType()).isEquals();
      }
   }

   public int hashCode() {
      int prime = true;
      int result = 1;
      result = 31 * result + (this.applicationID == null ? 0 : this.applicationID.hashCode());
      result = 31 * result + (this.cbe == null ? 0 : this.cbe.hashCode());
      result = 31 * result + (this.cds == null ? 0 : this.cds.hashCode());
      result = 31 * result + (this.eHP == null ? 0 : this.eHP.hashCode());
      result = 31 * result + (this.familyName == null ? 0 : this.familyName.hashCode());
      result = 31 * result + (this.firstName == null ? 0 : this.firstName.hashCode());
      result = 31 * result + (this.hubId == null ? 0 : this.hubId.hashCode());
      result = 31 * result + (this.ids == null ? 0 : this.ids.hashCode());
      result = 31 * result + (this.inss == null ? 0 : this.inss.hashCode());
      result = 31 * result + (this.name == null ? 0 : this.name.hashCode());
      result = 31 * result + (this.nihii == null ? 0 : this.nihii.hashCode());
      result = 31 * result + (this.type == null ? 0 : this.type.hashCode());
      return result;
   }

   public String toString() {
      StringBuilder builder = new StringBuilder();
      builder.append("HcParty ").append("[");
      builder.append("hubId=").append(this.hubId).append(", ");
      builder.append("type=").append(this.type).append(", ");
      builder.append("nihii=").append(this.nihii).append(", ");
      builder.append("inss=").append(this.inss).append("]");
      return builder.toString();
   }

   /** @deprecated */
   @Deprecated
   public String getApplicationID() {
      return this.applicationID;
   }

   /** @deprecated */
   @Deprecated
   public void setApplicationID(String value) {
      this.applicationID = value;
   }

   public static class Builder {
      private HcParty hcp = new HcParty();

      public Builder() {
      }

      public Builder withFirstName(String value) {
         this.hcp.setFirstName(value);
         return this;
      }

      public Builder withFamilyName(String value) {
         this.hcp.setFamilyName(value);
         return this;
      }

      public Builder withName(String value) {
         this.hcp.setName(value);
         return this;
      }

      public Builder withInss(String value) {
         this.hcp.setInss(value);
         return this;
      }

      public Builder withCbe(String value) {
         this.hcp.setCbe(value);
         return this;
      }

      public Builder withNihii(String value) {
         this.hcp.setNihii(value);
         return this;
      }

      public Builder withType(String value) {
         this.hcp.setType(value);
         return this;
      }

      public Builder withApplicationID(String string) {
         this.hcp.setApplicationID(string);
         return this;
      }

      public HcParty build() throws TherLinkBusinessConnectorException {
         this.validateNameConstraint();
         this.validateAtLeastOneNihiiOrInss();
         this.validateForApplicationRule();
         return this.hcp;
      }

      private void validateForApplicationRule() throws TherLinkBusinessConnectorException {
         if (this.hcp.getApplicationID() != null && (!this.hcp.getType().equalsIgnoreCase("application") || this.hcp.getNihii() != null || this.hcp.getInss() != null)) {
            throw new TherLinkBusinessConnectorException(TherLinkBusinessConnectorExceptionValues.HCP_NOT_VALID, new Object[]{"If HcParty has an applicationID, then type should be 'application' and niss neither nihii should be filled in"});
         }
      }

      private void validateAtLeastOneNihiiOrInss() throws TherLinkBusinessConnectorException {
         if (this.hcp.getNihii() == null && this.hcp.getInss() == null && this.hcp.getApplicationID() == null && this.hcp.getEHP() == null) {
            String msg = "At least nihii or inss should be defined";
            throw new TherLinkBusinessConnectorException(TherLinkBusinessConnectorExceptionValues.HCP_NOT_VALID, new Object[]{msg});
         }
      }

      private void validateNameConstraint() throws TherLinkBusinessConnectorException {
         if (!this.hasName() && !this.hasCompleteName() && this.hasAtLeastOneName()) {
            throw new TherLinkBusinessConnectorException(TherLinkBusinessConnectorExceptionValues.VALIDATION_ERROR, new Object[]{"Hcp should have a firstName and a FamilyName, (X)OR a name and nothing else"});
         }
      }

      private boolean hasAtLeastOneName() {
         return this.hcp.getFirstName() != null || this.hcp.getFamilyName() != null && this.hcp.getName() != null;
      }

      private boolean hasCompleteName() {
         return this.hcp.getFirstName() != null && this.hcp.getFamilyName() != null && this.hcp.getName() == null;
      }

      private boolean hasName() {
         return this.hcp.getFirstName() == null && this.hcp.getFamilyName() == null && this.hcp.getName() != null;
      }

      public Builder withEhp(String string) {
         this.hcp.setEHP(string);
         return this;
      }
   }
}
