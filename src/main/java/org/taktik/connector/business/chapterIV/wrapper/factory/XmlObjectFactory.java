package org.taktik.connector.business.chapterIV.wrapper.factory;

import org.taktik.connector.business.chapterIV.wrapper.Chap4MedicalAdvisorAgreementRequestWrapper;
import org.taktik.connector.business.chapterIV.wrapper.SealedRequestWrapper;
import org.taktik.connector.business.chapterIV.wrapper.UnsealedRequestWrapper;

public interface XmlObjectFactory {
   SealedRequestWrapper createSealedRequest();

   UnsealedRequestWrapper createUnsealedRequest();

   Chap4MedicalAdvisorAgreementRequestWrapper createChap4MedicalAdvisorAgreementRequest();

   String getSubtypeNameToRetrieveCredentialTypeProperties();
}
