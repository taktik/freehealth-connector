package be.ehealth.businessconnector.chapterIV.wrapper.impl;

import be.cin.io.unsealed.medicaladvisoragreement.consult.v1.Response;
import be.ehealth.businessconnector.chapterIV.wrapper.UnsealedResponseWrapper;

public class ConsultUnsealedResponseWrapperImpl extends AbstractWrapper<Response> implements UnsealedResponseWrapper<Response> {
   private static final long serialVersionUID = 8977452551125377230L;

   public ConsultUnsealedResponseWrapperImpl(Response response) {
      super(response);
   }

   public byte[] getTimestampReplyBytes() {
      return ((Response)this.getXmlObject()).getTimestampReply();
   }

   public void setTimestampReplyBytes(byte[] value) {
      ((Response)this.getXmlObject()).setTimestampReply(value);
   }

   public byte[] getKmehrResponseBytes() {
      return ((Response)this.getXmlObject()).getKmehrResponse();
   }

   public void setKmehrResponseBytes(byte[] value) {
      ((Response)this.getXmlObject()).setKmehrResponse(value);
   }
}
