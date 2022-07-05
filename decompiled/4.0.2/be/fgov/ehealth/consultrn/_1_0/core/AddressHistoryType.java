package be.fgov.ehealth.consultrn._1_0.core;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "AddressHistoryType",
   propOrder = {"address"}
)
public class AddressHistoryType extends HistoryType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Address"
   )
   protected AddressType address;

   public AddressHistoryType() {
   }

   public AddressType getAddress() {
      return this.address;
   }

   public void setAddress(AddressType value) {
      this.address = value;
   }
}
