package be.fgov.ehealth.consultrn._1_0.core;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "AddressType",
   propOrder = {"standardAddress", "plainAddress"}
)
public class AddressType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "StandardAddress"
   )
   protected StandardAddressType standardAddress;
   @XmlElement(
      name = "PlainAddress"
   )
   protected PlainAddressType plainAddress;
   @XmlAttribute(
      name = "ModificationDate"
   )
   protected String modificationDate;
   @XmlAttribute(
      name = "Origin"
   )
   protected OriginType origin;

   public StandardAddressType getStandardAddress() {
      return this.standardAddress;
   }

   public void setStandardAddress(StandardAddressType value) {
      this.standardAddress = value;
   }

   public PlainAddressType getPlainAddress() {
      return this.plainAddress;
   }

   public void setPlainAddress(PlainAddressType value) {
      this.plainAddress = value;
   }

   public String getModificationDate() {
      return this.modificationDate;
   }

   public void setModificationDate(String value) {
      this.modificationDate = value;
   }

   public OriginType getOrigin() {
      return this.origin;
   }

   public void setOrigin(OriginType value) {
      this.origin = value;
   }
}
