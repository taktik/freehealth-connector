package be.fgov.ehealth.rn.baselegaldata.v1;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "MinimalAddressType",
   propOrder = {"diplomaticPost", "diplomaticAddress", "postAddress", "referenceAddress", "residentialAddress", "temporaryAddress"}
)
public class MinimalAddressType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "DiplomaticPost"
   )
   protected DiplomaticPostType diplomaticPost;
   @XmlElement(
      name = "DiplomaticAddress"
   )
   protected PlainAddressOptionalInceptionDateType diplomaticAddress;
   @XmlElement(
      name = "PostAddress"
   )
   protected PlainAddressOptionalInceptionDateType postAddress;
   @XmlElement(
      name = "ReferenceAddress"
   )
   protected ResidentialAddressOptionalInceptionDateType referenceAddress;
   @XmlElement(
      name = "ResidentialAddress"
   )
   protected ResidentialAddressOptionalInceptionDateType residentialAddress;
   @XmlElement(
      name = "TemporaryAddress"
   )
   protected PlainAddressOptionalInceptionDateType temporaryAddress;

   public MinimalAddressType() {
   }

   public DiplomaticPostType getDiplomaticPost() {
      return this.diplomaticPost;
   }

   public void setDiplomaticPost(DiplomaticPostType value) {
      this.diplomaticPost = value;
   }

   public PlainAddressOptionalInceptionDateType getDiplomaticAddress() {
      return this.diplomaticAddress;
   }

   public void setDiplomaticAddress(PlainAddressOptionalInceptionDateType value) {
      this.diplomaticAddress = value;
   }

   public PlainAddressOptionalInceptionDateType getPostAddress() {
      return this.postAddress;
   }

   public void setPostAddress(PlainAddressOptionalInceptionDateType value) {
      this.postAddress = value;
   }

   public ResidentialAddressOptionalInceptionDateType getReferenceAddress() {
      return this.referenceAddress;
   }

   public void setReferenceAddress(ResidentialAddressOptionalInceptionDateType value) {
      this.referenceAddress = value;
   }

   public ResidentialAddressOptionalInceptionDateType getResidentialAddress() {
      return this.residentialAddress;
   }

   public void setResidentialAddress(ResidentialAddressOptionalInceptionDateType value) {
      this.residentialAddress = value;
   }

   public PlainAddressOptionalInceptionDateType getTemporaryAddress() {
      return this.temporaryAddress;
   }

   public void setTemporaryAddress(PlainAddressOptionalInceptionDateType value) {
      this.temporaryAddress = value;
   }
}
