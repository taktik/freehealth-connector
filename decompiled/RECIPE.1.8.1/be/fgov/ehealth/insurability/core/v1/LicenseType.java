package be.fgov.ehealth.insurability.core.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "LicenseType",
   propOrder = {"username", "password"}
)
public class LicenseType {
   @XmlElement(
      name = "Username",
      required = true
   )
   protected String username;
   @XmlElement(
      name = "Password",
      required = true
   )
   protected String password;

   public String getUsername() {
      return this.username;
   }

   public void setUsername(String value) {
      this.username = value;
   }

   public String getPassword() {
      return this.password;
   }

   public void setPassword(String value) {
      this.password = value;
   }
}
