package be.fgov.ehealth.genericinsurability.core.v1;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "OriginType",
   propOrder = {"_package", "siteID", "careProvider"}
)
public class OriginType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Package",
      required = true
   )
   protected PackageType _package;
   @XmlElement(
      name = "SiteID"
   )
   protected ValueRefString siteID;
   @XmlElement(
      name = "CareProvider"
   )
   protected CareProviderType careProvider;

   public OriginType() {
   }

   public PackageType getPackage() {
      return this._package;
   }

   public void setPackage(PackageType value) {
      this._package = value;
   }

   public ValueRefString getSiteID() {
      return this.siteID;
   }

   public void setSiteID(ValueRefString value) {
      this.siteID = value;
   }

   public CareProviderType getCareProvider() {
      return this.careProvider;
   }

   public void setCareProvider(CareProviderType value) {
      this.careProvider = value;
   }
}
