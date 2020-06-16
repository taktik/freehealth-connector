package be.fgov.ehealth.insurability.core.v2;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "PackageType",
   propOrder = {"license"}
)
public class PackageType extends AbstractIdType {
   @XmlElement(
      name = "License",
      required = true
   )
   protected LicenseType license;

   public LicenseType getLicense() {
      return this.license;
   }

   public void setLicense(LicenseType value) {
      this.license = value;
   }
}
