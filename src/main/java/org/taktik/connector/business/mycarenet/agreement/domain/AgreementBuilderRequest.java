package org.taktik.connector.business.mycarenet.agreement.domain;

import be.cin.encrypted.BusinessContent;
import be.fgov.ehealth.mycarenet.commons.protocol.v3.SendRequestType;

public abstract class AgreementBuilderRequest<T extends SendRequestType> {
   private T request;
   private BusinessContent businessContent;

   public AgreementBuilderRequest(T request) {
      this.request = request;
   }

   public void setBusinessContent(BusinessContent businessContent) {
      this.businessContent = businessContent;
   }

   public BusinessContent getBusinessContent() {
      return this.businessContent;
   }

   public T getRequest() {
      return this.request;
   }
}
