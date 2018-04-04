package org.taktik.connector.business.chapterIV.wrapper.impl;

import be.cin.io.unsealed.medicaladvisoragreement.ask.v1.Request;
import org.taktik.connector.business.chapterIV.wrapper.UnsealedRequestWrapper;

public class AskUnsealedRequestWrapperImpl extends AbstractWrapper<Request> implements UnsealedRequestWrapper<Request> {
   private static final long serialVersionUID = 4672149698241907719L;

   public AskUnsealedRequestWrapperImpl(Request request) {
      super(request);
   }

   public AskUnsealedRequestWrapperImpl() {
      super(new Request());
   }

   public byte[] getEtkHcp() {
      return ((Request)this.getXmlObject()).getEtkHcp();
   }

   public void setEtkHcp(byte[] value) {
      ((Request)this.getXmlObject()).setEtkHcp(value);
   }

   public byte[] getKmehrRequest() {
      return ((Request)this.getXmlObject()).getKmehrRequest();
   }

   public void setKmehrRequest(byte[] value) {
      ((Request)this.getXmlObject()).setKmehrRequest(value);
   }
}
