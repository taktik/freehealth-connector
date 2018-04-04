package org.taktik.connector.business.chapterIV.wrapper;

public interface UnsealedRequestWrapper<T> extends WrappedXmlObject<T> {
   byte[] getEtkHcp();

   void setEtkHcp(byte[] var1);

   byte[] getKmehrRequest();

   void setKmehrRequest(byte[] var1);
}
