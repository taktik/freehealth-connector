package be.fgov.ehealth.chap4.core.v1;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "OriginType",
   propOrder = {"_package", "careProvider"}
)
public class OriginType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Package",
      required = true
   )
   protected PackageType _package;
   @XmlElement(
      name = "CareProvider"
   )
   protected CareProviderType careProvider;

   public PackageType getPackage() {
      return this._package;
   }

   public void setPackage(PackageType value) {
      this._package = value;
   }

   public CareProviderType getCareProvider() {
      return this.careProvider;
   }

   public void setCareProvider(CareProviderType value) {
      this.careProvider = value;
   }
}
