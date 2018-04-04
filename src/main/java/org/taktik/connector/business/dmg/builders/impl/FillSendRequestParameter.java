package org.taktik.connector.business.dmg.builders.impl;

import org.taktik.connector.business.common.domain.Patient;
import org.taktik.connector.business.mycarenetdomaincommons.domain.Blob;
import org.joda.time.DateTime;

public class FillSendRequestParameter {
   private boolean isTest;
   private String referenceId;
   private Patient patientInfo;
   private DateTime referenceDate;
   private Blob blob;

   public FillSendRequestParameter(boolean isTest, String referenceId, Patient patientInfo, DateTime referenceDate, Blob blob) {
      this.isTest = isTest;
      this.referenceId = referenceId;
      this.patientInfo = patientInfo;
      this.referenceDate = referenceDate;
      this.blob = blob;
   }

   public boolean isTest() {
      return this.isTest;
   }

   public void setTest(boolean isTest) {
      this.isTest = isTest;
   }

   public String getReferenceId() {
      return this.referenceId;
   }

   public void setReferenceId(String referenceId) {
      this.referenceId = referenceId;
   }

   public Patient getPatientInfo() {
      return this.patientInfo;
   }

   public void setPatientInfo(Patient patientInfo) {
      this.patientInfo = patientInfo;
   }

   public DateTime getReferenceDate() {
      return this.referenceDate;
   }

   public void setReferenceDate(DateTime referenceDate) {
      this.referenceDate = referenceDate;
   }

   public Blob getBlob() {
      return this.blob;
   }

   public void setBlob(Blob blob) {
      this.blob = blob;
   }
}
