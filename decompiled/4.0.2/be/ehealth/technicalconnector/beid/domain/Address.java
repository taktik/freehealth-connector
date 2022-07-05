package be.ehealth.technicalconnector.beid.domain;

import java.io.Serializable;
import org.apache.commons.lang3.ArrayUtils;

public class Address implements Serializable {
   private static final long serialVersionUID = 1L;
   private String streetAndNumber;
   private String zip;
   private String municipality;
   private byte[] data;

   public Address() {
   }

   public String getStreetAndNumber() {
      return this.streetAndNumber;
   }

   public String getZip() {
      return this.zip;
   }

   public String getMunicipality() {
      return this.municipality;
   }

   public byte[] getData() {
      return ArrayUtils.clone(this.data);
   }

   public void setZip(String zip) {
      this.zip = zip;
   }

   public void setMunicipality(String municipality) {
      this.municipality = municipality;
   }

   public void setData(byte[] data) {
      this.data = ArrayUtils.clone(data);
   }

   public void setStreetAndNumber(String streetAndNumber) {
      this.streetAndNumber = streetAndNumber;
   }
}
