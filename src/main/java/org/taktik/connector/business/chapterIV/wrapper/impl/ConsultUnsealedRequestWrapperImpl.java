package org.taktik.connector.business.chapterIV.wrapper.impl;

import be.cin.io.unsealed.medicaladvisoragreement.consult.v1.Request;
import org.taktik.connector.business.chapterIV.wrapper.UnsealedRequestWrapper;

public class ConsultUnsealedRequestWrapperImpl extends AbstractWrapper<Request> implements UnsealedRequestWrapper<Request> {
   private static final long serialVersionUID = 8222168066460776895L;

   public ConsultUnsealedRequestWrapperImpl() {
      super(new Request());
   }

   public ConsultUnsealedRequestWrapperImpl(Request request) {
      super(request);
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
