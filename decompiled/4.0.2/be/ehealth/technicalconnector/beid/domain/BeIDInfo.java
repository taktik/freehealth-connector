package be.ehealth.technicalconnector.beid.domain;

import org.apache.commons.lang3.ArrayUtils;

public class BeIDInfo {
   private Identity identity;
   private Address address;
   private byte[] photo;

   public BeIDInfo() {
   }

   public Identity getIdentity() {
      return this.identity;
   }

   public void setIdentity(Identity identity) {
      this.identity = identity;
   }

   public Address getAddress() {
      return this.address;
   }

   public void setAddress(Address address) {
      this.address = address;
   }

   public byte[] getPhoto() {
      return ArrayUtils.clone(this.photo);
   }

   public void setPhoto(byte[] photo) {
      this.photo = photo;
   }
}
