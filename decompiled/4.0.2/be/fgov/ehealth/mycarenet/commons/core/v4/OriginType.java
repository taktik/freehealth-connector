package be.fgov.ehealth.mycarenet.commons.core.v4;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "OriginType",
   propOrder = {"actors", "_package", "siteID", "careProvider", "sender"}
)
public class OriginType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Actor"
   )
   protected List<ActorType> actors;
   @XmlElement(
      name = "Package"
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
   @XmlElement(
      name = "Sender"
   )
   protected PartyType sender;

   public OriginType() {
   }

   public List<ActorType> getActors() {
      if (this.actors == null) {
         this.actors = new ArrayList();
      }

      return this.actors;
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

   public PartyType getSender() {
      return this.sender;
   }

   public void setSender(PartyType value) {
      this.sender = value;
   }
}
