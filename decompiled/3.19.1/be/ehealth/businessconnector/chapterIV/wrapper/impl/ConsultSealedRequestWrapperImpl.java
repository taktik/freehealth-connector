package be.ehealth.businessconnector.chapterIV.wrapper.impl;

import be.cin.io.sealed.medicaladvisoragreement.consult.v1.Request;
import be.cin.types.v1.CareReceiverIdType;
import be.ehealth.businessconnector.chapterIV.wrapper.SealedRequestWrapper;
import org.joda.time.DateTime;

public class ConsultSealedRequestWrapperImpl extends AbstractWrapper<Request> implements SealedRequestWrapper<Request> {
   private static final long serialVersionUID = -8305462296795745790L;

   public ConsultSealedRequestWrapperImpl(Request request) {
      super(request);
   }

   public ConsultSealedRequestWrapperImpl() {
      super(new Request());
   }

   public CareReceiverIdType getCareReceiver() {
      return ((Request)this.getXmlObject()).getCareReceiver();
   }

   public void setCareReceiver(CareReceiverIdType value) {
      ((Request)this.getXmlObject()).setCareReceiver(value);
   }

   public DateTime getAgreementStartDate() {
      return ((Request)this.getXmlObject()).getAgreementStartDate();
   }

   public void setAgreementStartDate(DateTime value) {
      ((Request)this.getXmlObject()).setAgreementStartDate(value);
   }

   public String getUnsealKeyId() {
      return ((Request)this.getXmlObject()).getUnsealKeyId();
   }

   public void setUnsealKeyId(String value) {
      ((Request)this.getXmlObject()).setUnsealKeyId(value);
   }

   public byte[] getSealedContent() {
      return ((Request)this.getXmlObject()).getSealedContent();
   }

   public void setSealedContent(byte[] value) {
      ((Request)this.getXmlObject()).setSealedContent(value);
   }
}
