package be.fgov.ehealth.rn.baselegaldata.v1;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "AbstractOptionalAddressType",
   propOrder = {"diplomaticPost", "diplomaticAddress", "postAddress", "referenceAddress", "residentialAddress", "temporaryAddress"}
)
@XmlSeeAlso({AddressBaseType.class, AddressWithStatusAndSourceType.class})
public abstract class AbstractOptionalAddressType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "DiplomaticPost"
   )
   protected DiplomaticPostType diplomaticPost;
   @XmlElement(
      name = "DiplomaticAddress"
   )
   protected PlainAddressType diplomaticAddress;
   @XmlElement(
      name = "PostAddress"
   )
   protected PlainAddressType postAddress;
   @XmlElement(
      name = "ReferenceAddress"
   )
   protected ResidentialAddressType referenceAddress;
   @XmlElement(
      name = "ResidentialAddress"
   )
   protected ResidentialAddressType residentialAddress;
   @XmlElement(
      name = "TemporaryAddress"
   )
   protected PlainAddressType temporaryAddress;

   public AbstractOptionalAddressType() {
   }

   public DiplomaticPostType getDiplomaticPost() {
      return this.diplomaticPost;
   }

   public void setDiplomaticPost(DiplomaticPostType value) {
      this.diplomaticPost = value;
   }

   public PlainAddressType getDiplomaticAddress() {
      return this.diplomaticAddress;
   }

   public void setDiplomaticAddress(PlainAddressType value) {
      this.diplomaticAddress = value;
   }

   public PlainAddressType getPostAddress() {
      return this.postAddress;
   }

   public void setPostAddress(PlainAddressType value) {
      this.postAddress = value;
   }

   public ResidentialAddressType getReferenceAddress() {
      return this.referenceAddress;
   }

   public void setReferenceAddress(ResidentialAddressType value) {
      this.referenceAddress = value;
   }

   public ResidentialAddressType getResidentialAddress() {
      return this.residentialAddress;
   }

   public void setResidentialAddress(ResidentialAddressType value) {
      this.residentialAddress = value;
   }

   public PlainAddressType getTemporaryAddress() {
      return this.temporaryAddress;
   }

   public void setTemporaryAddress(PlainAddressType value) {
      this.temporaryAddress = value;
   }
}
