package org.taktik.connector.business.chapterIV.wrapper;

import be.cin.types.v1.CareReceiverIdType;
import org.joda.time.DateTime;

public interface SealedRequestWrapper<T> extends WrappedXmlObject<T> {
   CareReceiverIdType getCareReceiver();

   void setCareReceiver(CareReceiverIdType var1);

   DateTime getAgreementStartDate();

   void setAgreementStartDate(DateTime var1);

   String getUnsealKeyId();

   void setUnsealKeyId(String var1);

   byte[] getSealedContent();

   void setSealedContent(byte[] var1);
}
