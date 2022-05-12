package be.ehealth.businessconnector.chapterIV.builders.impl;

import be.ehealth.business.mycarenetdomaincommons.builders.RequestBuilderFactory;
import be.ehealth.business.mycarenetdomaincommons.domain.CommonInput;
import be.ehealth.business.mycarenetdomaincommons.domain.McnPackageInfo;
import be.ehealth.business.mycarenetdomaincommons.util.McnConfigUtil;
import be.ehealth.businessconnector.chapterIV.builders.CommonBuilder;
import be.ehealth.businessconnector.chapterIV.builders.KmehrBuilder;
import be.ehealth.businessconnector.chapterIV.domain.ChapterIVBuilderResponse;
import be.ehealth.businessconnector.chapterIV.domain.ChapterIVReferences;
import be.ehealth.businessconnector.chapterIV.exception.ChapterIVBusinessConnectorException;
import be.ehealth.businessconnector.chapterIV.exception.ChapterIVBusinessConnectorExceptionValues;
import be.ehealth.businessconnector.chapterIV.mappers.CommonInputMapper;
import be.ehealth.businessconnector.chapterIV.utils.ACLUtils;
import be.ehealth.businessconnector.chapterIV.utils.KeyDepotHelper;
import be.ehealth.businessconnector.chapterIV.validators.Chapter4XmlValidator;
import be.ehealth.businessconnector.chapterIV.validators.KmehrValidator;
import be.ehealth.businessconnector.chapterIV.wrapper.Chap4MedicalAdvisorAgreementRequestWrapper;
import be.ehealth.businessconnector.chapterIV.wrapper.SealedRequestWrapper;
import be.ehealth.businessconnector.chapterIV.wrapper.UnsealedRequestWrapper;
import be.ehealth.businessconnector.chapterIV.wrapper.factory.XmlObjectFactory;
import be.ehealth.businessconnector.chapterIV.wrapper.impl.WrappedObjectMarshallerHelper;
import be.ehealth.technicalconnector.config.impl.ConfigurationModuleBootstrap;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorExceptionValues;
import be.ehealth.technicalconnector.service.etee.Crypto;
import be.ehealth.technicalconnector.service.keydepot.KeyDepotManager;
import be.ehealth.technicalconnector.service.keydepot.KeyDepotManagerFactory;
import be.ehealth.technicalconnector.service.kgss.KgssManager;
import be.ehealth.technicalconnector.service.kgss.domain.KeyResult;
import be.ehealth.technicalconnector.utils.MarshallerHelper;
import be.ehealth.technicalconnector.utils.SessionUtil;
import be.ehealth.technicalconnector.utils.impl.JaxbContextFactory;
import be.fgov.ehealth.chap4.core.v1.CareReceiverIdType;
import be.fgov.ehealth.chap4.core.v1.CommonInputType;
import be.fgov.ehealth.chap4.core.v1.RecordCommonInputType;
import be.fgov.ehealth.chap4.core.v1.SecuredContentType;
import be.fgov.ehealth.etee.kgss._1_0.protocol.CredentialType;
import be.fgov.ehealth.medicalagreement.core.v1.Kmehrrequest;
import be.fgov.ehealth.standards.kmehr.id.v1.IDINSURANCEschemes;
import be.fgov.ehealth.standards.kmehr.id.v1.IDPATIENT;
import be.fgov.ehealth.standards.kmehr.id.v1.IDPATIENTschemes;
import be.fgov.ehealth.standards.kmehr.schema.v1.FolderType;
import be.fgov.ehealth.standards.kmehr.schema.v1.Kmehrmessage;
import be.fgov.ehealth.standards.kmehr.schema.v1.MemberinsuranceType;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.joda.time.DateTime;
import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Element;

public class CommonBuilderImpl implements CommonBuilder, ConfigurationModuleBootstrap.ModuleBootstrapHook {
   public static final String RESULT = "result";
   public static final String RESULT_SEALEDREQUEST = "sealedrequest";
   public static final String RESULT_COMMONINPUT = "commoninput";
   public static final String RESULT_RECORDCOMMONINPUT = "recordcommoninput";
   public static final String RESULT_CARERECEIVER = "carereceiver";
   public static final String RESULT_KMEHRMESSAGE = "kmehrmessage";
   public static final String RESULT_FOLDER = "folder";
   public static final String RESULT_REFERENCES = "references";
   private static final Logger LOG = LoggerFactory.getLogger(CommonBuilderImpl.class);
   private Chapter4XmlValidator chapter4XmlValidator;
   private KmehrBuilder kmehrBuilder;
   private KmehrValidator kmehrValidator;

   /** @deprecated */
   @Deprecated
   public CommonBuilderImpl(Crypto crypto, Chapter4XmlValidator chapter4XmlValidator, KmehrValidator kmehrValidator, KmehrBuilder kmehrBuilder) throws TechnicalConnectorException {
      this(chapter4XmlValidator, kmehrValidator, kmehrBuilder);
   }

   public CommonBuilderImpl() {
      LOG.debug("constructor needed for ModuleBootstrapHook");
   }

   public CommonBuilderImpl(Chapter4XmlValidator chapter4XmlValidator, KmehrValidator kmehrValidator, KmehrBuilder kmehrBuilder) throws TechnicalConnectorException {
      this.chapter4XmlValidator = chapter4XmlValidator;
      this.kmehrValidator = kmehrValidator;
      this.kmehrBuilder = kmehrBuilder;
   }

   public ChapterIVBuilderResponse createAgreementRequest(FolderType folder, boolean isTest, ChapterIVReferences references, XmlObjectFactory xmlObjectFactory, DateTime agreementStartDate) throws TechnicalConnectorException, ChapterIVBusinessConnectorException {
      if (agreementStartDate == null) {
         throw new ChapterIVBusinessConnectorException(ChapterIVBusinessConnectorExceptionValues.INPUT_PARAM_NULL, "input parameter agreementStartDate was null");
      } else {
         this.chapter4XmlValidator.validate(folder);
         Kmehrmessage message = this.generateKmehrMessage(folder, references.getKmehrIdSuffix());
         CareReceiverIdType careReceiver = this.createCareReceiver(message);
         RecordCommonInputType recordCommonInput = this.createRecordCommonInput(references.getRecordCommonInputId());
         CommonInputType commonInput = this.createCommonInput(isTest, references.getCommonReference(), references.getCommonNIPReference());
         SealedRequestWrapper sealedRequest = this.createAndValidateSealedRequest(message, careReceiver, xmlObjectFactory, agreementStartDate);
         Chap4MedicalAdvisorAgreementRequestWrapper<?> resultWrapper = this.buildAndValidateAgreementRequest(xmlObjectFactory, careReceiver, recordCommonInput, commonInput, sealedRequest);
         Map<String, Serializable> result = new HashMap();
         result.put("references", references);
         result.put("folder", folder);
         result.put("kmehrmessage", message);
         result.put("carereceiver", careReceiver);
         result.put("recordcommoninput", recordCommonInput);
         result.put("commoninput", commonInput);
         result.put("sealedrequest", sealedRequest);
         result.put("result", resultWrapper);
         return new ChapterIVBuilderResponse(result);
      }
   }

   private Chap4MedicalAdvisorAgreementRequestWrapper buildAndValidateAgreementRequest(XmlObjectFactory xmlObjectFactory, CareReceiverIdType careReceiver, RecordCommonInputType recordCommonInput, CommonInputType commonInput, SealedRequestWrapper sealedRequest) throws TechnicalConnectorException, ChapterIVBusinessConnectorException {
      Chap4MedicalAdvisorAgreementRequestWrapper agreementRequest = xmlObjectFactory.createChap4MedicalAdvisorAgreementRequest();
      agreementRequest.setCareReceiver(careReceiver);
      agreementRequest.setRecordCommonInput(recordCommonInput);
      agreementRequest.setCommonInput(commonInput);
      agreementRequest.setRequest(this.marshallAndEncryptSealedRequest(sealedRequest));
      this.chapter4XmlValidator.validate(agreementRequest.getXmlObject());
      return agreementRequest;
   }

   private Kmehrmessage generateKmehrMessage(FolderType folder, String generatedKmehrIdSuffix) throws TechnicalConnectorException, ChapterIVBusinessConnectorException {
      Kmehrmessage message = new Kmehrmessage();
      message.setHeader(this.kmehrBuilder.generateHeader(generatedKmehrIdSuffix));
      message.getFolders().add(folder);
      this.chapter4XmlValidator.validate(message);
      return message;
   }

   protected KeyResult getUnknownKey(String subTypeName) throws TechnicalConnectorException {
      List<CredentialType> acl = ACLUtils.createAclChapterIV(subTypeName);
      if (KeyDepotManagerFactory.getKeyDepotManager().getETK(KeyDepotManager.EncryptionTokenType.ENCRYPTION) == null) {
         LOG.debug("\t## EncryptionETK is null");
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_ETK_NOTFOUND, new Object[]{"EncryptionETK is undefined"});
      } else {
         byte[] systemETK = KeyDepotManagerFactory.getKeyDepotManager().getETK(KeyDepotManager.EncryptionTokenType.ENCRYPTION).getEtk().getEncoded();
         KeyResult unknownKey = KgssManager.getInstance().getNewKeyFromKgss(acl, systemETK);
         return unknownKey;
      }
   }

   protected final RecordCommonInputType createRecordCommonInput(String inputReference) throws ChapterIVBusinessConnectorException {
      if (inputReference == null) {
         throw new ChapterIVBusinessConnectorException(ChapterIVBusinessConnectorExceptionValues.INPUT_PARAM_NULL, "inputReference");
      } else {
         RecordCommonInputType recordCommonInput = new RecordCommonInputType();
         recordCommonInput.setInputReference(new BigDecimal(inputReference));
         return recordCommonInput;
      }
   }

   protected final CommonInputType createCommonInput(boolean isTest, String commonReference, String commonNIPReference) throws ChapterIVBusinessConnectorException, TechnicalConnectorException {
      McnPackageInfo packageInfo = McnConfigUtil.retrievePackageInfo("chapterIV");
      CommonInput commonInput = RequestBuilderFactory.getCommonBuilder("chapterIV").createCommonInput(packageInfo, isTest, commonReference);
      return ((CommonInputMapper)Mappers.getMapper(CommonInputMapper.class)).map(commonInput);
   }

   protected final CareReceiverIdType createCareReceiver(Kmehrmessage message) {
      CareReceiverIdType careReceiver = new CareReceiverIdType();
      List<IDPATIENT> patientIds = ((FolderType)message.getFolders().get(0)).getPatient().getIds();
      this.addSsinToCareReceiver(careReceiver, patientIds);
      MemberinsuranceType insurancymembership = ((FolderType)message.getFolders().get(0)).getPatient().getInsurancymembership();
      if (insurancymembership != null) {
         this.addMutualityToCareReceiver(careReceiver, insurancymembership);
         this.addRegNrWithMutToCareReceiver(careReceiver, insurancymembership);
      }

      return careReceiver;
   }

   private void addRegNrWithMutToCareReceiver(CareReceiverIdType careReceiver, MemberinsuranceType insurancymembership) {
      if (insurancymembership.getMembership() != null) {
         Object obj = insurancymembership.getMembership();
         if (obj instanceof Element) {
            Element el = (Element)obj;
            careReceiver.setRegNrWithMut(el.getTextContent());
         }
      }

   }

   private void addMutualityToCareReceiver(CareReceiverIdType careReceiver, MemberinsuranceType insurancymembership) {
      if (this.hasIdOfTypeIdInsurance(insurancymembership)) {
         careReceiver.setMutuality(insurancymembership.getId().getValue());
      }

   }

   private boolean hasIdOfTypeIdInsurance(MemberinsuranceType insurancymembership) {
      return insurancymembership.getId() != null && insurancymembership.getId().getS().compareTo(IDINSURANCEschemes.ID_INSURANCE) == 0;
   }

   private void addSsinToCareReceiver(CareReceiverIdType careReceiver, List<IDPATIENT> patientIds) {
      Iterator var3 = patientIds.iterator();

      while(var3.hasNext()) {
         IDPATIENT idpatient = (IDPATIENT)var3.next();
         if (this.itsAFilledPatientId(idpatient)) {
            careReceiver.setSsin(idpatient.getValue());
         }
      }

   }

   private boolean itsAFilledPatientId(IDPATIENT idpatient) {
      return idpatient.getS().compareTo(IDPATIENTschemes.ID_PATIENT) == 0 && idpatient.getValue() != null && !idpatient.getValue().isEmpty();
   }

   private SecuredContentType marshallAndEncryptSealedRequest(SealedRequestWrapper request) throws TechnicalConnectorException {
      byte[] marshalledContent = WrappedObjectMarshallerHelper.toXMLByteArray(request);
      if (marshalledContent != null) {
         LOG.debug("securedContent : " + new String(marshalledContent));
      }

      byte[] sealedKnown = SessionUtil.getEncryptionCrypto().seal(Crypto.SigningPolicySelector.WITH_NON_REPUDIATION, KeyDepotHelper.getChapterIVEncryptionToken(), marshalledContent);
      SecuredContentType securedContent = new SecuredContentType();
      securedContent.setSecuredContent(sealedKnown);
      return securedContent;
   }

   private SealedRequestWrapper createAndValidateSealedRequest(Kmehrmessage message, CareReceiverIdType careReceiver, XmlObjectFactory xmlObjectFactory, DateTime agreementStartDate) throws TechnicalConnectorException, ChapterIVBusinessConnectorException {
      try {
         KeyResult unknownKey = this.getUnknownKey(xmlObjectFactory.getSubtypeNameToRetrieveCredentialTypeProperties());
         SealedRequestWrapper request = xmlObjectFactory.createSealedRequest();
         request.setAgreementStartDate(agreementStartDate);
         request.setCareReceiver(this.mapToCinCareReceiverIdType(careReceiver));
         request.setSealedContent(this.getSealedContent(message, unknownKey, xmlObjectFactory));
         request.setUnsealKeyId(unknownKey.getKeyId());
         this.chapter4XmlValidator.validate(request.getXmlObject());
         return request;
      } catch (UnsupportedEncodingException var7) {
         LOG.debug("\t## The Character Encoding is not supported : throwing technical connector exception");
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.CHARACTER_ENCODING_NOTSUPPORTED, var7, new Object[0]);
      }
   }

   private be.cin.types.v1.CareReceiverIdType mapToCinCareReceiverIdType(CareReceiverIdType careReceiver) {
      be.cin.types.v1.CareReceiverIdType mappedCareReceiver = new be.cin.types.v1.CareReceiverIdType();
      mappedCareReceiver.setMutuality(careReceiver.getMutuality());
      mappedCareReceiver.setRegNrWithMut(careReceiver.getRegNrWithMut());
      mappedCareReceiver.setSsin(careReceiver.getSsin());
      return mappedCareReceiver;
   }

   protected byte[] getSealedContent(Kmehrmessage message, KeyResult unknownKey, XmlObjectFactory xmlObjectFactory) throws UnsupportedEncodingException, TechnicalConnectorException, ChapterIVBusinessConnectorException {
      UnsealedRequestWrapper request = this.createAndValidateUnsealedRequest(message, xmlObjectFactory);
      return SessionUtil.getEncryptionCrypto().seal(Crypto.SigningPolicySelector.WITH_NON_REPUDIATION, unknownKey, WrappedObjectMarshallerHelper.toXMLByteArray(request));
   }

   private UnsealedRequestWrapper createAndValidateUnsealedRequest(Kmehrmessage message, XmlObjectFactory xmlObjectFactory) throws ChapterIVBusinessConnectorException, TechnicalConnectorException {
      UnsealedRequestWrapper request = xmlObjectFactory.createUnsealedRequest();
      request.setEtkHcp(KeyDepotManagerFactory.getKeyDepotManager().getETK(KeyDepotManager.EncryptionTokenType.ENCRYPTION).getEtk().getEncoded());
      request.setKmehrRequest(this.createAndValidateKmehrRequestXmlByteArray(message));
      this.chapter4XmlValidator.validate(request.getXmlObject());
      return request;
   }

   private byte[] createAndValidateKmehrRequestXmlByteArray(Kmehrmessage message) throws TechnicalConnectorException, ChapterIVBusinessConnectorException {
      Kmehrrequest kmehrrequest = this.createKmehrRequest(message);
      this.chapter4XmlValidator.validate(kmehrrequest);
      MarshallerHelper<Kmehrrequest, Kmehrrequest> kmehrMarshallHelper = new MarshallerHelper(Kmehrrequest.class, Kmehrrequest.class);
      return kmehrMarshallHelper.toXMLByteArray(kmehrrequest);
   }

   private Kmehrrequest createKmehrRequest(Kmehrmessage message) throws ChapterIVBusinessConnectorException {
      Kmehrrequest kmehrrequest = new Kmehrrequest();
      kmehrrequest.setKmehrmessage(message);
      return kmehrrequest;
   }

   public void bootstrap() {
      JaxbContextFactory.initJaxbContext(Kmehrmessage.class);
      JaxbContextFactory.initJaxbContext(Kmehrrequest.class);
      JaxbContextFactory.initJaxbContext(CommonInputType.class);
   }
}
