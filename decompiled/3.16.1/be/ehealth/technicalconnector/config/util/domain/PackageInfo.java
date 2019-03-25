package be.ehealth.technicalconnector.config.util.domain;

/** @deprecated */
@Deprecated
public class PackageInfo {
   private String userName;
   private String password;
   private String packageName;

   public PackageInfo() {
   }

   public PackageInfo(String userName, String password, String name) {
      this.userName = userName;
      this.password = password;
      this.packageName = name;
   }

   public void setUserName(String userName) {
      this.userName = userName;
   }

   public String getUserName() {
      return this.userName;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   public String getPassword() {
      return this.password;
   }

   public void setPackageName(String name) {
      this.packageName = name;
   }

   public String getPackageName() {
      return this.packageName;
   }
}
