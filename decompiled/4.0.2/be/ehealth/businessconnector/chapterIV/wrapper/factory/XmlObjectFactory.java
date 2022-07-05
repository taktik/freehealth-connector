package be.ehealth.businessconnector.chapterIV.wrapper.factory;

import be.ehealth.businessconnector.chapterIV.wrapper.Chap4MedicalAdvisorAgreementRequestWrapper;
import be.ehealth.businessconnector.chapterIV.wrapper.SealedRequestWrapper;
import be.ehealth.businessconnector.chapterIV.wrapper.UnsealedRequestWrapper;

public interface XmlObjectFactory {
   SealedRequestWrapper createSealedRequest();

   UnsealedRequestWrapper createUnsealedRequest();

   Chap4MedicalAdvisorAgreementRequestWrapper createChap4MedicalAdvisorAgreementRequest();

   String getSubtypeNameToRetrieveCredentialTypeProperties();
}
