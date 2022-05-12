package be.ehealth.business.mycarenetcommons.domain;

import be.cin.encrypted.BusinessContent;
import be.fgov.ehealth.mycarenet.commons.protocol.v4.SendRequestType;

public class EncryptedRequestHolder<T extends SendRequestType> {
   private T sendRequest;
   private BusinessContent businessContent;

   public EncryptedRequestHolder(T sendRequest, BusinessContent businessContent) {
      this.sendRequest = sendRequest;
      this.businessContent = businessContent;
   }

   public T getSendAttestationRequest() {
      return this.sendRequest;
   }

   public BusinessContent getBusinessContent() {
      return this.businessContent;
   }
}
