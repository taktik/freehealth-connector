package be.business.connector.gfddpp.utils;

import be.apb.gfddpp.common.constants.SupportedDataTypes;
import be.apb.gfddpp.common.exceptions.GFDDPPException;
import be.apb.gfddpp.common.log.LogsGetDataParameters;
import be.apb.gfddpp.common.status.StatusResolver;
import be.apb.gfddpp.common.utils.JaxContextCentralizer;
import be.apb.gfddpp.common.utils.Operation;
import be.apb.gfddpp.domain.PersonIdType;
import be.apb.standards.gfddpp.constants.request.DateRangeTypes;
import be.apb.standards.gfddpp.patient.signature.PatientSignature;
import be.apb.standards.gfddpp.request.DataSpecificParametersGetData;
import be.apb.standards.gfddpp.request.DataSpecificParametersGetDataTypes;
import be.apb.standards.gfddpp.request.DataSpecificParametersGetStatus;
import be.apb.standards.gfddpp.request.DataSpecificParametersMedicationSchemeEntries;
import be.apb.standards.gfddpp.request.DataSpecificParametersMedicationSchemeTimestamps;
import be.apb.standards.gfddpp.request.DataSpecificParametersPharmacyDetails;
import be.apb.standards.gfddpp.request.GetDataRequestParameters;
import be.apb.standards.gfddpp.request.Motivation;
import be.apb.standards.gfddpp.request.ObjectFactory;
import be.apb.standards.gfddpp.request.PatientType;
import be.apb.standards.gfddpp.request.RequestorType;
import org.taktik.connector.business.recipeprojects.core.exceptions.IntegrationModuleEhealthException;
import org.taktik.connector.business.recipeprojects.core.exceptions.IntegrationModuleException;
import org.taktik.connector.business.recipeprojects.core.exceptions.IntegrationModuleRuntimeException;
import org.taktik.connector.business.recipeprojects.core.utils.EncryptionUtils;
import org.taktik.connector.business.recipeprojects.core.utils.Exceptionutils;
import org.taktik.connector.business.recipeprojects.core.utils.I18nHelper;
import org.taktik.connector.business.recipeprojects.core.utils.MarshallerHelper;
import org.taktik.connector.business.recipeprojects.core.utils.PropertyHandler;
import be.business.connector.gfddpp.domain.ThreeStateBoolean;
import be.ehealth.apb.gfddpp.services.tipsystem.AuthorizationParametersType;
import be.ehealth.apb.gfddpp.services.tipsystem.PatientConsentType;
import be.ehealth.apb.gfddpp.services.tipsystem.PatientSignatureType;
import be.ehealth.apb.gfddpp.services.tipsystem.RoutingParametersType;
import be.ehealth.apb.gfddpp.services.tipsystem.TherapeuticalRelationShipType;
import be.ehealth.technicalconnector.beid.BeIDInfo;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.service.sts.security.impl.BeIDCredential;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.List;
import javax.xml.bind.JAXBException;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.Charsets;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

public class RequestCreatorUtils {
   private static final Logger LOG = Logger.getLogger(RequestCreatorUtils.class);
   private static JaxContextCentralizer jaxContextCentralizer = JaxContextCentralizer.getInstance();

   public static RoutingParametersType createRoutingParameter(String tipId) {
      RoutingParametersType routingParametersType = new RoutingParametersType();
      routingParametersType.setTIPId(tipId);
      return routingParametersType;
   }

   public static AuthorizationParametersType createAuthorizationParameters(boolean requestPatientSignature, ThreeStateBoolean patientConsent, ThreeStateBoolean therapeuticalRelationShipTypeFlag, String patientid, EncryptionUtils encryptionUtils) throws IntegrationModuleException {
      AuthorizationParametersType authorizationParametersType = new AuthorizationParametersType();
      authorizationParametersType.setPatientConsent(createPatientConsent(requestPatientSignature, patientConsent, patientid, encryptionUtils));
      if (therapeuticalRelationShipTypeFlag != null) {
         authorizationParametersType.setTherapeuticalRelationShip(createTherapeuticalRelationShip(therapeuticalRelationShipTypeFlag));
      }

      return authorizationParametersType;
   }

   public static be.ehealth.apb.gfddpp.services.pcdh.AuthorizationParametersType createPCDHAuthorizationParameters(boolean requestPatientSignature, ThreeStateBoolean therapeuticRelationShip, String dGuid, String patientid, EncryptionUtils encryptionUtils) throws IntegrationModuleException {
      be.ehealth.apb.gfddpp.services.pcdh.AuthorizationParametersType authorizationParametersType = new be.ehealth.apb.gfddpp.services.pcdh.AuthorizationParametersType();
      authorizationParametersType.setPatientConsent(createPCDHPatientConsent(requestPatientSignature, dGuid, patientid, encryptionUtils));
      if (therapeuticRelationShip != null && therapeuticRelationShip.getBooleanValue() != null) {
         authorizationParametersType.setTherapeuticalRelationShip(createPCDHTherapeuticalRelationShip(therapeuticRelationShip));
      }

      return authorizationParametersType;
   }

   public static PatientConsentType createPatientConsent(boolean requestPatientSignature, ThreeStateBoolean patientConsent, String patientid, EncryptionUtils encryptionUtils) throws IntegrationModuleException {
      PatientConsentType patientConsentType = new PatientConsentType();
      if (patientConsent != null && patientConsent.getBooleanValue() != null) {
         patientConsentType.setPatientConsentFlag(patientConsent.getBooleanValue());
      }

      if (requestPatientSignature) {
         patientConsentType.setPatientSignature(createPatientSignature(patientid, encryptionUtils));
      }

      return patientConsentType;
   }

   public static be.ehealth.apb.gfddpp.services.pcdh.PatientConsentType createPCDHPatientConsent(boolean requestPatientSignature, String dGuid, String patientid, EncryptionUtils encryptionUtils) throws IntegrationModuleException {
      be.ehealth.apb.gfddpp.services.pcdh.PatientConsentType patientConsentType = new be.ehealth.apb.gfddpp.services.pcdh.PatientConsentType();
      patientConsentType.setPatientConsentFlag(false);
      if (requestPatientSignature) {
         patientConsentType.setPatientSignature(createPCDHPatientSignature(dGuid, patientid, encryptionUtils));
      }

      return patientConsentType;
   }

   public static PatientSignatureType createPatientSignature(String patientid, EncryptionUtils encryptionUtils) throws IntegrationModuleException {
      LOG.debug("********** Create strong patient signature for patient: " + patientid + " *************");
      PatientSignatureType patientSignatureType = new PatientSignatureType();
      byte[] patientSignature = null;
      XMLGregorianCalendar xgcal = createCurrentXmlGregorianCalendar();
      patientSignatureType.setDateTime(xgcal);
      SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
      String nISS = null;
      PrivateKey prvk = null;
      PublicKey pk = null;
      BeIDCredential signatureString;
      if (PropertyHandler.getInstance().hasProperty("patientsignature.mock.enable") && PropertyHandler.getInstance().hasProperty("patientsignature.mock.niss") && !StringUtils.isEmpty(PropertyHandler.getInstance().getProperty("patientsignature.mock.enable")) && PropertyHandler.getInstance().getProperty("patientsignature.mock.enable").equals("true") && !StringUtils.isEmpty(PropertyHandler.getInstance().getProperty("patientsignature.mock.niss"))) {
         pk = encryptionUtils.getPublicKey();
         prvk = encryptionUtils.getPrivateKey();
         nISS = PropertyHandler.getInstance().getProperty("patientsignature.mock.niss");
      } else {
         nISS = initNiss();

         try {
            signatureString = BeIDCredential.getInstance(nISS, "Signature");
            prvk = signatureString.getPrivateKey();
            pk = signatureString.getPublicKey();
         } catch (TechnicalConnectorException var14) {
            Exceptionutils.errorHandler(var14);
         }
      }

      if (!nISS.equals(patientid.trim())) {
         throw new IntegrationModuleException(I18nHelper.getLabel("validation.patient.signature.not.equal", new Object[]{patientid}));
      } else {
         signatureString = null;
         String signatureString = nISS + ";" + format.format(xgcal.toGregorianCalendar().getTime());

         byte[] patientSignature;
         try {
            patientSignature = Base64.encodeBase64(SignatureGenerator.sign(signatureString, prvk, "SHA1withRSA"));
         } catch (Exception var13) {
            throw new IntegrationModuleException(I18nHelper.getLabel("error.patient.signature.generation"), var13);
         }

         PatientSignature patientSig = new PatientSignature();
         patientSig.setPublickey(pk.getEncoded());
         patientSig.setSignature(patientSignature);

         try {
            byte[] ps = jaxContextCentralizer.toXml(PatientSignature.class, patientSig).getBytes(Charsets.UTF_8);
            patientSignatureType.setSignature(ps);
         } catch (GFDDPPException var12) {
            LOG.error("Exception in : createPatientSignature", var12);
            throw new IntegrationModuleException(StatusResolver.resolveMessage(StatusResolver.getLocalResourceBundle(), var12.getStatusCode(), var12.getPlaceHolders()), var12);
         }

         LOG.debug("********** Strong patient signature created *************");
         return patientSignatureType;
      }
   }

   public static be.ehealth.apb.gfddpp.services.pcdh.PatientSignatureType createPCDHPatientSignature(String dGuid, String patientid, EncryptionUtils encryptionUtils) throws IntegrationModuleException {
      LOG.debug("********** Create strong patient signature for patient: " + patientid + " *************");
      be.ehealth.apb.gfddpp.services.pcdh.PatientSignatureType patientSignatureType = new be.ehealth.apb.gfddpp.services.pcdh.PatientSignatureType();
      byte[] patientSignature = null;
      XMLGregorianCalendar xgcal = createCurrentXmlGregorianCalendar();
      patientSignatureType.setDateTime(xgcal);
      SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
      PrivateKey prvk = null;
      PublicKey pk = null;
      String nISS = null;
      BeIDCredential signatureString;
      if (PropertyHandler.getInstance().hasProperty("patientsignature.mock.enable") && PropertyHandler.getInstance().hasProperty("patientsignature.mock.niss") && !StringUtils.isEmpty(PropertyHandler.getInstance().getProperty("patientsignature.mock.enable")) && PropertyHandler.getInstance().getProperty("patientsignature.mock.enable").equals("true") && !StringUtils.isEmpty(PropertyHandler.getInstance().getProperty("patientsignature.mock.niss"))) {
         pk = encryptionUtils.getPublicKey();
         prvk = encryptionUtils.getPrivateKey();
         nISS = PropertyHandler.getInstance().getProperty("patientsignature.mock.niss");
      } else {
         nISS = initNiss();

         try {
            signatureString = BeIDCredential.getInstance(nISS, "Signature");
            prvk = signatureString.getPrivateKey();
            pk = signatureString.getPublicKey();
         } catch (TechnicalConnectorException var15) {
            Exceptionutils.errorHandler(var15);
         }
      }

      if (!nISS.equals(patientid.trim())) {
         throw new IntegrationModuleException(I18nHelper.getLabel("validation.patient.signature.not.equal", new Object[]{patientid}));
      } else {
         signatureString = null;
         String signatureString;
         if (dGuid == null) {
            signatureString = nISS + ";" + format.format(xgcal.toGregorianCalendar().getTime());
         } else {
            signatureString = dGuid + ";" + format.format(xgcal.toGregorianCalendar().getTime());
         }

         byte[] patientSignature;
         try {
            patientSignature = Base64.encodeBase64(SignatureGenerator.sign(signatureString, prvk, "SHA1withRSA"));
         } catch (Exception var14) {
            throw new IntegrationModuleException(I18nHelper.getLabel("error.patient.signature.generation"), var14);
         }

         PatientSignature patientSig = new PatientSignature();
         patientSig.setPublickey(pk.getEncoded());
         patientSig.setSignature(patientSignature);

         try {
            patientSignatureType.setSignature(jaxContextCentralizer.toXml(PatientSignature.class, patientSig).getBytes(Charsets.UTF_8));
         } catch (GFDDPPException var13) {
            var13.printStackTrace();
            throw new IntegrationModuleException(StatusResolver.resolveMessage(StatusResolver.getLocalResourceBundle(), var13.getStatusCode(), var13.getPlaceHolders()), var13);
         }

         LOG.debug("********** Strong patient signature created *************");
         return patientSignatureType;
      }
   }

   public static TherapeuticalRelationShipType createTherapeuticalRelationShip(ThreeStateBoolean therapeuticalRelationShipTypeFlag) {
      TherapeuticalRelationShipType therapeuticalRelationShipType = new TherapeuticalRelationShipType();
      therapeuticalRelationShipType.setTherapeuticalRelationShipFlag(therapeuticalRelationShipTypeFlag.getBooleanValue().booleanValue());
      return therapeuticalRelationShipType;
   }

   public static be.ehealth.apb.gfddpp.services.pcdh.TherapeuticalRelationShipType createPCDHTherapeuticalRelationShip(ThreeStateBoolean therapeuticalRelationShipTypeFlag) {
      be.ehealth.apb.gfddpp.services.pcdh.TherapeuticalRelationShipType therapeuticalRelationShipType = new be.ehealth.apb.gfddpp.services.pcdh.TherapeuticalRelationShipType();
      therapeuticalRelationShipType.setTherapeuticalRelationShipFlag(therapeuticalRelationShipTypeFlag.getBooleanValue().booleanValue());
      return therapeuticalRelationShipType;
   }

   public static String createGetDataParams(PatientType patient, String requestorId, String requestorType, boolean excludeOwnData, String range, String dataType) throws DatatypeConfigurationException, GFDDPPException {
      GetDataRequestParameters getDataRequestParameters = new GetDataRequestParameters();
      getDataRequestParameters.setPatient(patient);
      getDataRequestParameters.setRequestor(createRequestor(requestorId, requestorType));
      if (dataType != null) {
         getDataRequestParameters.setDataType(dataType.toLowerCase());
      }

      DataSpecificParametersGetData getDataSpecificParam = new DataSpecificParametersGetData();
      getDataSpecificParam.setExcludeOwnData(excludeOwnData);
      DatatypeFactory dataTypeFactory = DatatypeFactory.newInstance();
      GregorianCalendar gregorianCalendar = new GregorianCalendar();
      if (StringUtils.equals(range, DateRangeTypes.FULL.name())) {
         getDataSpecificParam.setDateRangeType(DateRangeTypes.FULL.name());
      } else if (StringUtils.equals(range, DateRangeTypes.DEFAULT.name())) {
         getDataSpecificParam.setDateRangeType(DateRangeTypes.DEFAULT.name());
      } else {
         getDataSpecificParam.setDateRangeType(DateRangeTypes.CUSTOM.name());
         String[] splitedRange = range.split("-");
         gregorianCalendar.set(Integer.parseInt(splitedRange[0].substring(0, 4)), Integer.parseInt(splitedRange[0].substring(4, 6)) - 1, Integer.parseInt(splitedRange[0].substring(6)));
         getDataSpecificParam.setStartDate(dataTypeFactory.newXMLGregorianCalendar(gregorianCalendar));
         gregorianCalendar.set(Integer.parseInt(splitedRange[1].substring(0, 4)), Integer.parseInt(splitedRange[1].substring(4, 6)) - 1, Integer.parseInt(splitedRange[1].substring(6)));
         getDataSpecificParam.setEndDate(dataTypeFactory.newXMLGregorianCalendar(gregorianCalendar));
      }

      getDataRequestParameters.setDataSpecificParametersGetData(getDataSpecificParam);
      LogsGetDataParameters.logs(getDataRequestParameters, LogsGetDataParameters.IntermediateMessage.REQUEST_CREATED, Operation.GETDATA);
      return JaxContextCentralizer.getInstance().toXml(GetDataRequestParameters.class, getDataRequestParameters);
   }

   public static String createGetDataTypesParams(PatientType patient, String requestorId, String requestorType) throws JAXBException, DatatypeConfigurationException {
      MarshallerHelper<Object, GetDataRequestParameters> helper = new MarshallerHelper(Object.class, GetDataRequestParameters.class);
      GetDataRequestParameters getDataRequestParameters = new GetDataRequestParameters();
      getDataRequestParameters.setPatient(patient);
      getDataRequestParameters.setRequestor(createRequestor(requestorId, requestorType));
      getDataRequestParameters.setDataType("dataTypes");
      DataSpecificParametersGetDataTypes getDataSpecificParam = new DataSpecificParametersGetDataTypes();
      getDataRequestParameters.setDataSpecificParametersGetDataTypes(getDataSpecificParam);
      LogsGetDataParameters.logs(getDataRequestParameters, LogsGetDataParameters.IntermediateMessage.REQUEST_CREATED, Operation.GETDATATYPES);
      return helper.marsh(getDataRequestParameters);
   }

   public static String createRequestMedicationSchemeTimestamps(String requestorId, String requestorType, List<String> subjectIds, String clientMessageId) throws JAXBException {
      MarshallerHelper<Object, GetDataRequestParameters> helper = new MarshallerHelper(Object.class, GetDataRequestParameters.class);
      GetDataRequestParameters getDataRequestParameters = new GetDataRequestParameters();
      getDataRequestParameters.setRequestor(createRequestor(requestorId, requestorType));
      getDataRequestParameters.setDataType(SupportedDataTypes.MEDICATION_SCHEME_TIMESTAMPS.getName());
      DataSpecificParametersMedicationSchemeTimestamps specificParameters = new DataSpecificParametersMedicationSchemeTimestamps();
      if (StringUtils.isNotBlank(clientMessageId)) {
         specificParameters.setClientMessageId(clientMessageId);
      }

      specificParameters.getPatientINSS().addAll(subjectIds);
      getDataRequestParameters.setDataSpecificParametersMedicationSchemeTimestamps(specificParameters);
      LogsGetDataParameters.logs(getDataRequestParameters, LogsGetDataParameters.IntermediateMessage.REQUEST_CREATED, Operation.MEDICATIONSCHEME_GET_TIMESTAMPS);
      return helper.marsh(getDataRequestParameters);
   }

   public static String createRequestMedicationSchemeEntries(String requestorId, String requestorType, String patientId, int paginationIndex, String clientMessageId) throws JAXBException {
      MarshallerHelper<Object, GetDataRequestParameters> helper = new MarshallerHelper(Object.class, GetDataRequestParameters.class);
      GetDataRequestParameters getDataRequestParameters = new GetDataRequestParameters();
      getDataRequestParameters.setRequestor(createRequestor(requestorId, requestorType));
      getDataRequestParameters.setPatient(createPatientType(patientId, PersonIdType.INSS.name()));
      getDataRequestParameters.setDataType(SupportedDataTypes.MEDICATION_SCHEME_ENTRIES.getName());
      DataSpecificParametersMedicationSchemeEntries specificParameters = new DataSpecificParametersMedicationSchemeEntries();
      if (StringUtils.isNotBlank(clientMessageId)) {
         specificParameters.setClientMessageId(clientMessageId);
      }

      specificParameters.setPaginationIndex(paginationIndex);
      getDataRequestParameters.setDataSpecificParametersMedicationSchemeEntries(specificParameters);
      LogsGetDataParameters.logs(getDataRequestParameters, LogsGetDataParameters.IntermediateMessage.REQUEST_CREATED, Operation.MEDICATIONSCHEME_GET_ENTRIES);
      return helper.marsh(getDataRequestParameters);
   }

   public static AuthorizationParametersType createAuthorizationRevokeParameters() {
      AuthorizationParametersType authorizationParametersType = new AuthorizationParametersType();
      PatientConsentType patientConsentType = new PatientConsentType();
      patientConsentType.setPatientConsentFlag(false);
      authorizationParametersType.setPatientConsent(patientConsentType);
      return authorizationParametersType;
   }

   public static PatientType createPatientType(String patientId, String patientIdType) {
      ObjectFactory o = new ObjectFactory();
      PatientType patient = o.createPatientType();
      patient.setPatientId(patientId);
      patient.setPatientIdType(patientIdType);
      return patient;
   }

   public static String createRequestPharmacyDetails(String requestorId, String requestorType, PatientType patient, String dGuid, String motivationType, String motivationText) throws JAXBException {
      MarshallerHelper<Object, GetDataRequestParameters> helper = new MarshallerHelper(Object.class, GetDataRequestParameters.class);
      GetDataRequestParameters getDataRequestParameters = new GetDataRequestParameters();
      getDataRequestParameters.setPatient(patient);
      getDataRequestParameters.setRequestor(createRequestor(requestorId, requestorType));
      getDataRequestParameters.setDataType("pharmacydetails");
      DataSpecificParametersPharmacyDetails dataSpecificParametersPharmacyDetails = new DataSpecificParametersPharmacyDetails();
      dataSpecificParametersPharmacyDetails.setDGuid(dGuid);
      Motivation mvt = new Motivation();
      mvt.setMotivationtext(motivationText);
      mvt.setMotivationtype(motivationType);
      dataSpecificParametersPharmacyDetails.setMotivation(mvt);
      getDataRequestParameters.setDataSpecificParametersPharmacyDetails(dataSpecificParametersPharmacyDetails);
      LogsGetDataParameters.logs(getDataRequestParameters, LogsGetDataParameters.IntermediateMessage.REQUEST_CREATED, Operation.GETPHARMACYDETAILS);
      return helper.marsh(getDataRequestParameters);
   }

   private static RequestorType createRequestor(String requestorId, String requestorType) {
      RequestorType type = new RequestorType();
      type.setRequestorId(requestorId);
      type.setRequestorType(requestorType);
      return type;
   }

   public static String createRequestGetStatus(String requestorId, String requestorType, String sGuid, String dGuid) throws JAXBException {
      MarshallerHelper<Object, GetDataRequestParameters> helper = new MarshallerHelper(Object.class, GetDataRequestParameters.class);
      GetDataRequestParameters getDataRequestParameters = new GetDataRequestParameters();
      getDataRequestParameters.setRequestor(createRequestor(requestorId, requestorType));
      getDataRequestParameters.setDataType("StatusMessage");
      DataSpecificParametersGetStatus dataSpecificParametersGetStatus = new DataSpecificParametersGetStatus();
      if (!StringUtils.isEmpty(sGuid)) {
         dataSpecificParametersGetStatus.setSGUID(sGuid);
      }

      if (!StringUtils.isEmpty(dGuid)) {
         dataSpecificParametersGetStatus.setDGUID(dGuid);
      }

      getDataRequestParameters.setVersion("1.0");
      getDataRequestParameters.setDataSpecificParametersGetStatus(dataSpecificParametersGetStatus);
      LogsGetDataParameters.logs(getDataRequestParameters, LogsGetDataParameters.IntermediateMessage.REQUEST_CREATED, Operation.GETSTATUS);
      return helper.marsh(getDataRequestParameters);
   }

   private static XMLGregorianCalendar createCurrentXmlGregorianCalendar() throws IntegrationModuleException {
      GregorianCalendar gcal = (GregorianCalendar)GregorianCalendar.getInstance();
      XMLGregorianCalendar xgcal = null;

      try {
         xgcal = DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal);
         return xgcal;
      } catch (DatatypeConfigurationException var3) {
         var3.printStackTrace();
         throw new IntegrationModuleException(I18nHelper.getLabel("error.technical"));
      }
   }

   private static String initNiss() throws IntegrationModuleEhealthException, IntegrationModuleRuntimeException, IntegrationModuleException {
      BeIDInfo beIDInfo = null;

      try {
         beIDInfo = BeIDInfo.getInstance("test");
      } catch (TechnicalConnectorException var2) {
         if (StringUtils.contains(var2.getMessage().toLowerCase(), "EID is not present".toLowerCase())) {
            throw new IntegrationModuleException(I18nHelper.getLabel("error.eid.read"), var2);
         }

         Exceptionutils.errorHandler(var2);
      }

      return beIDInfo.getIdentity().getNationalNumber();
   }
}
