package be.fgov.ehealth.mycarenet.commons.core.v2;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "PackageType",
   propOrder = {"license"}
)
public class PackageType extends AbstractIdType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "License",
      required = true
   )
   protected LicenseType license;

   public PackageType() {
   }

   public LicenseType getLicense() {
      return this.license;
   }

   public void setLicense(LicenseType value) {
      this.license = value;
   }
}
