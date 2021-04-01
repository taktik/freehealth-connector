package be.ehealth.businessconnector.chapterIV.wrapper;

public interface UnsealedResponseWrapper<T> extends WrappedXmlObject<T> {
   byte[] getTimestampReplyBytes();

   void setTimestampReplyBytes(byte[] var1);

   byte[] getKmehrResponseBytes();

   void setKmehrResponseBytes(byte[] var1);
}
