package org.taktik.connector.business.mycarenetdomaincommons.domain;

import java.io.Serializable;

public class CareReceiverId implements Serializable {
   private static final long serialVersionUID = 2489515568374148313L;
   private String ssinNumber;
   private String registrationNumberWithMutuality;
   private String mutuality;

   public CareReceiverId(String ssinNumber) {
      this.ssinNumber = ssinNumber;
   }

   public CareReceiverId(String registrationNumberWithMutuality, String mutuality) {
      this.registrationNumberWithMutuality = registrationNumberWithMutuality;
      this.mutuality = mutuality;
   }

   public CareReceiverId(String ssinNumber, String registrationNumberWithMutuality, String mutuality) {
      this.ssinNumber = ssinNumber;
      this.registrationNumberWithMutuality = registrationNumberWithMutuality;
      this.mutuality = mutuality;
   }

   public String getSsinNumber() {
      return this.ssinNumber;
   }

   public void setSsinNumber(String ssinNumber) {
      this.ssinNumber = ssinNumber;
   }

   public String getRegistrationNumberWithMutuality() {
      return this.registrationNumberWithMutuality;
   }

   public void setRegistrationNumberWithMutuality(String registrationNumberWithMutuality) {
      this.registrationNumberWithMutuality = registrationNumberWithMutuality;
   }

   public String getMutuality() {
      return this.mutuality;
   }

   public void setMutuality(String mutuality) {
      this.mutuality = mutuality;
   }

   public String toString() {
      return "CareReceiverId [ssinNumber=" + this.ssinNumber + ", registrationNumberWithMutuality=" + this.registrationNumberWithMutuality + ", mutuality=" + this.mutuality + "]";
   }
}
