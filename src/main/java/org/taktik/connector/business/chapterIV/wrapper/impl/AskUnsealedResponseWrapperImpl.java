package org.taktik.connector.business.chapterIV.wrapper.impl;

import be.cin.io.unsealed.medicaladvisoragreement.ask.v1.Response;
import org.taktik.connector.business.chapterIV.wrapper.UnsealedResponseWrapper;

public class AskUnsealedResponseWrapperImpl extends AbstractWrapper<Response> implements UnsealedResponseWrapper<Response> {
   private static final long serialVersionUID = 1564331269334064896L;

   public AskUnsealedResponseWrapperImpl(Response response) {
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
