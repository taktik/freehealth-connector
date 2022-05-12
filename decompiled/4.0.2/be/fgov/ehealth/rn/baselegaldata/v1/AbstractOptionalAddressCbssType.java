package be.fgov.ehealth.rn.baselegaldata.v1;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "AbstractOptionalAddressCbssType",
   propOrder = {"residentialAddress"}
)
@XmlSeeAlso({AddressCbssBaseType.class, AddressCbssWithUpdateStatusType.class, AddressCbssWithStatusAndSourceType.class})
public abstract class AbstractOptionalAddressCbssType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "ResidentialAddress"
   )
   protected ResidentialAddressType residentialAddress;

   public AbstractOptionalAddressCbssType() {
   }

   public ResidentialAddressType getResidentialAddress() {
      return this.residentialAddress;
   }

   public void setResidentialAddress(ResidentialAddressType value) {
      this.residentialAddress = value;
   }
}
