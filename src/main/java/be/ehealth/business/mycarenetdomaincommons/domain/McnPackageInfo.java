package be.ehealth.business.mycarenetdomaincommons.domain;

import org.taktik.connector.technical.config.util.domain.PackageInfo;

public class McnPackageInfo extends PackageInfo {
   private String packageName;
   private String userName;
   private String password;

   public McnPackageInfo(String userName, String password, String packageName) {
      this.packageName = packageName;
      this.userName = userName;
      this.password = password;
   }

   public String getPackageName() {
      return this.packageName;
   }

   public void setPackageName(String packageName) {
      this.packageName = packageName;
   }

   public String getUserName() {
      return this.userName;
   }

   public void setUserName(String userName) {
      this.userName = userName;
   }

   public String getPassword() {
      return this.password;
   }

   public void setPassword(String password) {
      this.password = password;
   }
}
