package be.ehealth.businessconnector.chapterIV.wrapper;

import be.fgov.ehealth.chap4.core.v1.CommonOutputType;
import be.fgov.ehealth.chap4.core.v1.FaultType;
import be.fgov.ehealth.chap4.core.v1.RecordCommonOutputType;
import be.fgov.ehealth.chap4.core.v1.SecuredContentType;

public interface Chap4MedicalAdvisorAgreementResponseWrapper<T> extends ResponseTypeIf, WrappedXmlObject<T> {
   CommonOutputType getCommonOutput();

   void setCommonOutput(CommonOutputType var1);

   RecordCommonOutputType getRecordCommonOutput();

   void setRecordCommonOutput(RecordCommonOutputType var1);

   FaultType getReturnInfo();

   void setReturnInfo(FaultType var1);

   SecuredContentType getResponse();

   void setResponse(SecuredContentType var1);
}
