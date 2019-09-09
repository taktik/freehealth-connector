package be.fgov.ehealth.consultrn.commons.core.v3;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "DiplomaticInformationType",
   propOrder = {"diplomaticPost", "diplomaticAddress", "postAddress"}
)
public class DiplomaticInformationType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "DiplomaticPost"
   )
   protected DiplomaticPostType diplomaticPost;
   @XmlElement(
      name = "DiplomaticAddress"
   )
   protected TemporaryAddressType diplomaticAddress;
   @XmlElement(
      name = "PostAddress"
   )
   protected TemporaryAddressType postAddress;

   public DiplomaticPostType getDiplomaticPost() {
      return this.diplomaticPost;
   }

   public void setDiplomaticPost(DiplomaticPostType value) {
      this.diplomaticPost = value;
   }

   public TemporaryAddressType getDiplomaticAddress() {
      return this.diplomaticAddress;
   }

   public void setDiplomaticAddress(TemporaryAddressType value) {
      this.diplomaticAddress = value;
   }

   public TemporaryAddressType getPostAddress() {
      return this.postAddress;
   }

   public void setPostAddress(TemporaryAddressType value) {
      this.postAddress = value;
   }
}
