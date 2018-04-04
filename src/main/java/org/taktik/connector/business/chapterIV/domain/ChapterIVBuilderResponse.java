package org.taktik.connector.business.chapterIV.domain;

import org.taktik.connector.business.chapterIV.wrapper.Chap4MedicalAdvisorAgreementRequestWrapper;
import org.taktik.connector.business.chapterIV.wrapper.SealedRequestWrapper;
import be.fgov.ehealth.chap4.core.v1.CareReceiverIdType;
import be.fgov.ehealth.chap4.core.v1.CommonInputType;
import be.fgov.ehealth.chap4.core.v1.RecordCommonInputType;
import be.fgov.ehealth.chap4.protocol.v1.AskChap4MedicalAdvisorAgreementRequest;
import be.fgov.ehealth.chap4.protocol.v1.ConsultChap4MedicalAdvisorAgreementRequest;
import be.fgov.ehealth.standards.kmehr.schema.v1.FolderType;
import be.fgov.ehealth.standards.kmehr.schema.v1.Kmehrmessage;
import java.io.Serializable;
import java.util.Map;

public class ChapterIVBuilderResponse implements Serializable {
   private static final long serialVersionUID = 302755650310894339L;
   private Map<String, Serializable> result;

   public ChapterIVBuilderResponse(Map<String, Serializable> result) {
      this.result = result;
   }

   private <T> T transform(String key, Class<T> clazz) {
      if (this.result.containsKey(key)) {
         Object resultObj = this.result.get(key);
         if (clazz.isInstance(resultObj)) {
            return (T) resultObj;
         }
      }

      return null;
   }

   public Kmehrmessage getKmerhMessage() {
      return (Kmehrmessage)this.transform("kmehrmessage", Kmehrmessage.class);
   }

   public ChapterIVReferences getChapterIVReferences() {
      return (ChapterIVReferences)this.transform("references", ChapterIVReferences.class);
   }

   public FolderType getFolder() {
      return (FolderType)this.transform("folder", FolderType.class);
   }

   public CareReceiverIdType getCareReceiver() {
      return (CareReceiverIdType)this.transform("carereceiver", CareReceiverIdType.class);
   }

   public RecordCommonInputType getRecordCommonInput() {
      return (RecordCommonInputType)this.transform("recordcommoninput", RecordCommonInputType.class);
   }

   public CommonInputType getCommonInput() {
      return (CommonInputType)this.transform("commoninput", CommonInputType.class);
   }

   public SealedRequestWrapper<?> getSealedRequest() {
      return (SealedRequestWrapper)this.transform("sealedrequest", SealedRequestWrapper.class);
   }

   public Chap4MedicalAdvisorAgreementRequestWrapper<?> getRequestWrapper() {
      return (Chap4MedicalAdvisorAgreementRequestWrapper)this.transform("result", Chap4MedicalAdvisorAgreementRequestWrapper.class);
   }

   public AskChap4MedicalAdvisorAgreementRequest getAskChap4MedicalAdvisorAgreementRequest() {
      Chap4MedicalAdvisorAgreementRequestWrapper<?> wrapper = this.getRequestWrapper();
      if (wrapper != null) {
         Object resultObj = wrapper.getXmlObject();
         if (resultObj instanceof AskChap4MedicalAdvisorAgreementRequest) {
            return (AskChap4MedicalAdvisorAgreementRequest)resultObj;
         }
      }

      return null;
   }

   public ConsultChap4MedicalAdvisorAgreementRequest getConsultChap4MedicalAdvisorAgreementRequest() {
      Chap4MedicalAdvisorAgreementRequestWrapper<?> wrapper = this.getRequestWrapper();
      if (wrapper != null) {
         Object resultObj = wrapper.getXmlObject();
         if (resultObj instanceof ConsultChap4MedicalAdvisorAgreementRequest) {
            return (ConsultChap4MedicalAdvisorAgreementRequest)resultObj;
         }
      }

      return null;
   }

   public int hashCode() {
      int resultFunction = 1;
      resultFunction = 31 * resultFunction + (this.result == null ? 0 : this.result.hashCode());
      return resultFunction;
   }

   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      } else if (obj == null) {
         return false;
      } else if (this.getClass() != obj.getClass()) {
         return false;
      } else {
         ChapterIVBuilderResponse other = (ChapterIVBuilderResponse)obj;
         if (this.result == null) {
            if (other.result != null) {
               return false;
            }
         } else if (!this.result.equals(other.result)) {
            return false;
         }

         return true;
      }
   }
}
