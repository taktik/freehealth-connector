package org.taktik.connector.business.chapterIV.wrapper;

import be.fgov.ehealth.chap4.core.v1.CareReceiverIdType;
import be.fgov.ehealth.chap4.core.v1.CommonInputType;
import be.fgov.ehealth.chap4.core.v1.RecordCommonInputType;
import be.fgov.ehealth.chap4.core.v1.SecuredContentType;

public interface Chap4MedicalAdvisorAgreementRequestWrapper<T> extends WrappedXmlObject<T> {
   CommonInputType getCommonInput();

   void setCommonInput(CommonInputType var1);

   RecordCommonInputType getRecordCommonInput();

   void setRecordCommonInput(RecordCommonInputType var1);

   CareReceiverIdType getCareReceiver();

   void setCareReceiver(CareReceiverIdType var1);

   SecuredContentType getRequest();

   void setRequest(SecuredContentType var1);
}
