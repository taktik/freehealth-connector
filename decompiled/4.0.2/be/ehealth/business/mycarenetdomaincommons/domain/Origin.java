package be.ehealth.business.mycarenetdomaincommons.domain;

import be.ehealth.technicalconnector.config.util.domain.PackageInfo;
import java.util.List;

public class Origin {
   private McnPackageInfo packageInfo;
   private String siteId;
   private CareProvider careProvider;
   private Party sender;
   private List<Actor> actors;

   /** @deprecated */
   @Deprecated
   public Origin(PackageInfo packageInfo, CareProvider careProvider) {
      this.packageInfo = this.map(packageInfo);
      this.careProvider = careProvider;
   }

   /** @deprecated */
   @Deprecated
   public Origin(PackageInfo packageInfo, Party sender) {
      this.packageInfo = this.map(packageInfo);
      this.sender = sender;
   }

   /** @deprecated */
   @Deprecated
   public Origin(PackageInfo packageInfo, String siteId, Party sender) {
      this.packageInfo = this.map(packageInfo);
      this.siteId = siteId;
      this.sender = sender;
   }

   /** @deprecated */
   @Deprecated
   public Origin(PackageInfo packageInfo, String siteId, CareProvider careProvider, Party sender) {
      this.packageInfo = this.map(packageInfo);
      this.siteId = siteId;
      this.careProvider = careProvider;
      this.sender = sender;
   }

   /** @deprecated */
   @Deprecated
   public PackageInfo getPackageInfo() {
      return this.packageInfo;
   }

   /** @deprecated */
   @Deprecated
   public void setPackageInfo(PackageInfo packageInfo) {
      this.packageInfo = this.map(packageInfo);
   }

   private McnPackageInfo map(PackageInfo oldType) {
      return new McnPackageInfo(oldType.getUserName(), oldType.getPassword(), oldType.getPackageName());
   }

   public Origin(McnPackageInfo packageInfo, CareProvider careProvider) {
      this.packageInfo = packageInfo;
      this.careProvider = careProvider;
   }

   public Origin(McnPackageInfo packageInfo, Party sender) {
      this.packageInfo = packageInfo;
      this.sender = sender;
   }

   public Origin(McnPackageInfo packageInfo, String siteId, Party sender) {
      this.packageInfo = packageInfo;
      this.siteId = siteId;
      this.sender = sender;
   }

   public Origin(McnPackageInfo packageInfo, String siteId, CareProvider careProvider, Party sender, List<Actor> actors) {
      this.packageInfo = packageInfo;
      this.siteId = siteId;
      this.careProvider = careProvider;
      this.sender = sender;
      this.actors = actors;
   }

   public McnPackageInfo getMcnPackageInfo() {
      return this.packageInfo;
   }

   public void setMcnPackageInfo(McnPackageInfo packageInfo) {
      this.packageInfo = packageInfo;
   }

   public String getSiteId() {
      return this.siteId;
   }

   public void setSiteId(String siteId) {
      this.siteId = siteId;
   }

   public CareProvider getCareProvider() {
      return this.careProvider;
   }

   public void setCareProvider(CareProvider careProvider) {
      this.careProvider = careProvider;
   }

   public Party getSender() {
      return this.sender;
   }

   public void setSender(Party sender) {
      this.sender = sender;
   }

   public List<Actor> getActors() {
      return this.actors;
   }
}
