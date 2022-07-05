package be.fgov.ehealth.consultrn.commons.core.v3;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "AddressType",
   propOrder = {"diplomaticInformation", "residentialAddress", "temporaryAddress"}
)
public class AddressType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "DiplomaticInformation"
   )
   protected DiplomaticInformationType diplomaticInformation;
   @XmlElement(
      name = "ResidentialAddress"
   )
   protected ResidentialAddressResponseType residentialAddress;
   @XmlElement(
      name = "TemporaryAddress"
   )
   protected TemporaryAddressType temporaryAddress;

   public AddressType() {
   }

   public DiplomaticInformationType getDiplomaticInformation() {
      return this.diplomaticInformation;
   }

   public void setDiplomaticInformation(DiplomaticInformationType value) {
      this.diplomaticInformation = value;
   }

   public ResidentialAddressResponseType getResidentialAddress() {
      return this.residentialAddress;
   }

   public void setResidentialAddress(ResidentialAddressResponseType value) {
      this.residentialAddress = value;
   }

   public TemporaryAddressType getTemporaryAddress() {
      return this.temporaryAddress;
   }

   public void setTemporaryAddress(TemporaryAddressType value) {
      this.temporaryAddress = value;
   }
}
