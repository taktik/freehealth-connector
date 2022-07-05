package be.fgov.ehealth.rn.baselegaldata.v1;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "AddressDeclarationType",
   propOrder = {"residentialAddress"}
)
public class AddressDeclarationType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "ResidentialAddress"
   )
   protected ForeignAddressDeclarationType residentialAddress;

   public AddressDeclarationType() {
   }

   public ForeignAddressDeclarationType getResidentialAddress() {
      return this.residentialAddress;
   }

   public void setResidentialAddress(ForeignAddressDeclarationType value) {
      this.residentialAddress = value;
   }
}
