package be.business.connector.gfddpp.utils;

import be.apb.gfddpp.common.exceptions.GFDDPPException;
import be.apb.gfddpp.common.status.StatusCode;
import be.apb.gfddpp.common.status.StatusResolver;
import be.apb.gfddpp.common.utils.JaxContextCentralizer;
import be.apb.gfddpp.common.utils.SingleMessageWrapper;
import be.apb.gfddpp.common.utils.Utils;
import be.apb.gfddpp.domain.PersonIdType;
import be.apb.gfddpp.rtrn.registerdata.Dispensation;
import be.apb.gfddpp.rtrn.registerdata.Events;
import be.apb.gfddpp.rtrn.registerdata.Product;
import be.apb.gfddpp.validation.exception.SingleMessageValidationException;
import be.apb.standards.smoa.schema.id.v1.AbstractMedicinalProductIdType;
import be.apb.standards.smoa.schema.id.v1.AtcDddSystemIdType;
import be.apb.standards.smoa.schema.id.v1.BeRegistrationIdType;
import be.apb.standards.smoa.schema.id.v1.CnkIdType;
import be.apb.standards.smoa.schema.id.v1.EntityIdType;
import be.apb.standards.smoa.schema.id.v1.NihiiIdType;
import be.apb.standards.smoa.schema.model.v1.BvacDocument;
import be.apb.standards.smoa.schema.model.v1.DataLocationType;
import be.apb.standards.smoa.schema.model.v1.MaxSetPatient;
import be.apb.standards.smoa.schema.model.v1.MaxSetPersonType;
import be.apb.standards.smoa.schema.model.v1.MaxSetProductType;
import be.apb.standards.smoa.schema.model.v1.MedicationHistoryType;
import be.apb.standards.smoa.schema.model.v1.MinSetProductType;
import be.apb.standards.smoa.schema.model.v1.ObjectFactory;
import be.apb.standards.smoa.schema.model.v1.Pharmacy;
import be.apb.standards.smoa.schema.model.v1.ReferencePharmacyType;
import be.apb.standards.smoa.schema.v1.AbstractEventType;
import be.apb.standards.smoa.schema.v1.ArchivePrescriptionCommentEventType;
import be.apb.standards.smoa.schema.v1.ArchivePrescriptionEventType;
import be.apb.standards.smoa.schema.v1.BvacEventType;
import be.apb.standards.smoa.schema.v1.ContinuedPharmaceuticalCareDossierEvent;
import be.apb.standards.smoa.schema.v1.DataEntryRequest;
import be.apb.standards.smoa.schema.v1.HeaderType;
import be.apb.standards.smoa.schema.v1.MedicationHistoryEvent;
import be.apb.standards.smoa.schema.v1.MedicationSchemeEntriesRequest;
import be.apb.standards.smoa.schema.v1.PharmaceuticalCareEventType;
import be.apb.standards.smoa.schema.v1.SenderType;
import be.apb.standards.smoa.schema.v1.SingleMessage;
import be.business.connector.core.exceptions.IntegrationModuleException;
import be.business.connector.core.utils.EncryptionUtils;
import be.business.connector.core.utils.Exceptionutils;
import be.business.connector.core.utils.I18nHelper;
import be.business.connector.core.utils.PropertyHandler;
import be.business.connector.gfddpp.domain.ThreeStateBoolean;
import be.business.connector.projects.common.utils.ProductFilterUtils;
import be.business.connector.projects.common.utils.SystemServicesUtils;
import be.ehealth.apb.gfddpp.services.tipsystem.RoutingParametersType;
import be.ehealth.apb.gfddpp.services.tipsystem.SealedMessageRequestType;
import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import org.apache.commons.io.Charsets;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

public class SingleMessageInfo {
   private static final Logger LOG = Logger.getLogger(SingleMessageInfo.class);
   private static final String module = "SingleMessageInfo";
   private JaxContextCentralizer jaxContextCentralizer;
   private static final SingleMessageValidationXsd smvh = new SingleMessageValidationXsd();
   private boolean HasArchivePrescriptionEventType = false;
   private boolean HasArchivePrescriptionCommentEventType = false;
   private boolean HasPharmaceuticalCareEventType = false;
   private boolean HasBvacEventType = false;
   private boolean HasSyncEventType = false;
   private boolean HasMedicationHistoryEvent = false;
   private boolean HasMedicationSchemeEntriesRequest = false;
   private boolean HasContinuedPharmaceuticalCareDossierEvent = false;
   private String info = " ";
   private SingleMessageWrapper singleMessageWrapper = null;
   private SingleMessage singleMessageObject = null;
   private String eventtype = "";

   private JaxContextCentralizer getJaxContextCentralizer() {
      if (this.jaxContextCentralizer == null) {
         this.jaxContextCentralizer = JaxContextCentralizer.getInstance();
      }

      return this.jaxContextCentralizer;
   }

   public SingleMessageInfo() {
   }

   public SingleMessageInfo(byte[] singleMessage) throws IntegrationModuleException {
      String procedure = "SingleMessageInfo";
      if (singleMessage == null) {
         LOG.error("SingleMessageInfo=-----= ----- " + procedure + " =$=" + this.info + "error.validation.dataContent singleMessage == null");
         throw new IntegrationModuleException(I18nHelper.getLabel("error.validation.dataContent"));
      } else {
         try {
            SingleMessage lsingleMessageObject = (SingleMessage)this.getJaxContextCentralizer().toObject(SingleMessage.class, new String(singleMessage, Charsets.UTF_8));
            this.initSingleMessageInfo(lsingleMessageObject);
         } catch (GFDDPPException var4) {
            LOG.error("SingleMessageInfo=-----= ----- " + procedure + " =$=" + this.info + var4.getMessage());
            throw new IntegrationModuleException(var4.getLocalizedMessage(), var4);
         }
      }
   }

   public final void initSingleMessageInfo(SingleMessage singleMessageObject) throws IntegrationModuleException {
      String procedure = "InitSingleMessageInfo";
      this.setSingleMessageObject(singleMessageObject);
      List<AbstractEventType> types = this.singleMessageWrapper.getEvents();
      long tel = 0L;
      Iterator var7 = types.iterator();

      while(var7.hasNext()) {
         AbstractEventType type = (AbstractEventType)var7.next();
         ++tel;
         if (type instanceof ArchivePrescriptionEventType) {
            this.HasArchivePrescriptionEventType = true;
            this.info = this.info + " EventType:ArchivePrescriptionEventType ";
            this.HasSyncEventType = false;
            this.eventtype = "ArchivePrescriptionEventType";
         } else if (type instanceof ArchivePrescriptionCommentEventType) {
            this.HasArchivePrescriptionCommentEventType = true;
            this.info = this.info + " EventType:ArchivePrescriptionCommentEventType ";
            this.HasSyncEventType = false;
            this.eventtype = "ArchivePrescriptionCommentEventType";
         } else if (type instanceof PharmaceuticalCareEventType) {
            this.HasPharmaceuticalCareEventType = true;
            this.info = this.info + " EventType:PharmaceuticalCareEventType ";
            this.eventtype = "PharmaceuticalCareEventType";
         } else if (type instanceof BvacEventType) {
            this.HasBvacEventType = true;
            this.info = this.info + " EventType:BvacEventType ";
            this.HasSyncEventType = true;
            this.eventtype = "Bvac";
         } else if (type instanceof MedicationHistoryEvent) {
            this.HasMedicationHistoryEvent = true;
            this.info = this.info + " EventType:MedicationHistoryEvent ";
            this.eventtype = "MedicationHistoryEvent";
         } else if (type instanceof MedicationSchemeEntriesRequest) {
            this.HasMedicationSchemeEntriesRequest = true;
            this.info = this.info + " EventType:MedicationSchemeEntriesRequest ";
            this.eventtype = "MedicationSchemeEntriesRequest";
            this.HasSyncEventType = true;
         } else {
            if (!(type instanceof ContinuedPharmaceuticalCareDossierEvent)) {
               LOG.error("SingleMessageInfo=-----= ----- " + procedure + " =$=" + this.info + "error unknown EventType: " + type.getClass().toString());
               throw new IntegrationModuleException(I18nHelper.getLabel("error.validation.event.type"));
            }

            this.HasContinuedPharmaceuticalCareDossierEvent = true;
            this.info = this.info + " EventType:ContinuedPharmaceuticalCareDossierEvent ";
            this.eventtype = "ContinuedPharmaceuticalCareDossierEvent";
            this.HasSyncEventType = true;
         }
      }

      if (tel > 1L) {
         LOG.error("SingleMessageInfo=-----= ----- " + procedure + " =$=" + this.info + "more than 1 Event ");
         throw new IntegrationModuleException(I18nHelper.getLabel("EventType.Multiple"));
      } else {
         LOG.debug("SingleMessageInfo=-----= ----- " + procedure + " =$=" + this.info);
      }
   }

   private void setSingleMessageObject(SingleMessage singleMessageObject) {
      this.singleMessageObject = singleMessageObject;
      this.singleMessageWrapper = new SingleMessageWrapper(singleMessageObject);
   }

   public void validateRegisterData() throws IntegrationModuleException, GFDDPPException {
      String procedure = "validateRegisterData";
      if (this.HasMedicationHistoryEvent) {
         LOG.error("SingleMessageInfo=-----= ----- " + procedure + " =$=" + this.info + "error: error.validation.event.type");
         throw new IntegrationModuleException(I18nHelper.getLabel("error.validation.event.type"));
      } else {
         this.validateKnownEventType();
         this.validateMultipleEventTypes();
         this.validateEventTypesForRegisterData();
         this.validateEmptyHeader();
         this.validateSGuidEmptyOk();
         this.validateDGuidEmptyOk();
         this.validateSessionDateOk();
         this.validateSingleMessagePatientMaxDataSet();
         this.validateBvacEventType();
      }
   }

   public void validateRevokeData() throws IntegrationModuleException {
      String procedure = "validateRevokeData";
      if (this.HasMedicationHistoryEvent) {
         LOG.error("SingleMessageInfo=-----= ----- " + procedure + " =$=" + this.info + "error: error.validation.event.type");
         throw new IntegrationModuleException(I18nHelper.getLabel("error.validation.event.type"));
      } else {
         this.validateKnownEventType();
         this.validateMultipleEventTypes();
         this.validateEventTypesForRevokeData();
         this.validateEmptyHeader();
         this.validateSGuidFilledOk();
         this.validateDGuidFilledOk();
         this.validateMotivationOk();
         this.validateBvacEventType();
      }
   }

   public void validateUpdateData() throws IntegrationModuleException {
      String procedure = "validateUpdateData";
      if (this.HasMedicationHistoryEvent) {
         LOG.error("SingleMessageInfo=-----= ----- " + procedure + " =$=" + this.info + "error: error.validation.event.type");
         throw new IntegrationModuleException(I18nHelper.getLabel("error.validation.event.type"));
      } else {
         this.validateKnownEventType();
         this.validateMultipleEventTypes();
         this.validateEventTypesForUpdateData();
         this.validateEmptyHeader();
         this.validateSGuidFilledOk();
         this.validateDGuidFilledOk();
         this.validateSessionDateOk();
         this.validateMotivationOk();
         this.validateBvacEventType();
      }
   }

   public void validateKnownEventType() throws IntegrationModuleException {
      String procedure = "validateKnownEventType";
      if (!this.HasBvacEventType && !this.HasPharmaceuticalCareEventType && !this.HasArchivePrescriptionEventType && !this.HasArchivePrescriptionCommentEventType && !this.HasMedicationSchemeEntriesRequest && !this.HasContinuedPharmaceuticalCareDossierEvent) {
         LOG.error("SingleMessageInfo=-----= ----- " + procedure + " =$=" + this.info + "error: error.validation.event.type");
         throw new IntegrationModuleException(I18nHelper.getLabel("error.validation.event.type"));
      }
   }

   public void validateEventTypesForRegisterData() throws IntegrationModuleException {
      String procedure = "validateEventTypesForRegisterData";
      if (!this.HasPharmaceuticalCareEventType && !this.HasBvacEventType && !this.HasArchivePrescriptionEventType && !this.HasArchivePrescriptionCommentEventType && !this.HasMedicationSchemeEntriesRequest && !this.HasContinuedPharmaceuticalCareDossierEvent) {
         LOG.error("SingleMessageInfo=-----= ----- " + procedure + " =$=" + this.info + "error: error.validation.event.type");
         throw new IntegrationModuleException(I18nHelper.getLabel("error.validation.event.type.not.supported"));
      }
   }

   public void validateEventTypesForUpdateData() throws IntegrationModuleException {
      String procedure = "validateEventTypesForUpdateData";
      if (!this.HasBvacEventType && !this.HasArchivePrescriptionEventType && !this.HasArchivePrescriptionCommentEventType && !this.HasMedicationSchemeEntriesRequest && !this.HasContinuedPharmaceuticalCareDossierEvent) {
         if (!this.HasPharmaceuticalCareEventType) {
            LOG.error("SingleMessageInfo=-----= ----- " + procedure + " =$=" + this.info + "error: error.validation.event.type");
            throw new IntegrationModuleException(I18nHelper.getLabel("error.validation.event.type.not.supported"));
         }
      } else {
         LOG.error("SingleMessageInfo=-----= ----- " + procedure + " =$=" + this.info + "error: error.validation.event.type");
         throw new IntegrationModuleException(I18nHelper.getLabel("error.validation.event.type.not.supported"));
      }
   }

   public void validateEventTypesForRevokeData() throws IntegrationModuleException {
      String procedure = "validateEventTypesForRevokeData";
      if (!this.HasBvacEventType && !this.HasArchivePrescriptionEventType && !this.HasArchivePrescriptionCommentEventType && !this.HasMedicationSchemeEntriesRequest && !this.HasContinuedPharmaceuticalCareDossierEvent) {
         if (!this.HasPharmaceuticalCareEventType) {
            LOG.error("SingleMessageInfo=-----= ----- " + procedure + " =$=" + this.info + "error: error.validation.event.type");
            throw new IntegrationModuleException(I18nHelper.getLabel("error.validation.event.type.not.supported"));
         }
      } else {
         LOG.error("SingleMessageInfo=-----= ----- " + procedure + " =$=" + this.info + "error: error.validation.event.type");
         throw new IntegrationModuleException(I18nHelper.getLabel("error.validation.event.type.not.supported"));
      }
   }

   public void validateMultipleEventTypes() throws IntegrationModuleException {
      String procedure = "validateMultipleEventTypes";
      if (this.HasBvacEventType && this.HasPharmaceuticalCareEventType) {
         LOG.error("SingleMessageInfo=-----= ----- " + procedure + " =$=" + this.info + "error: bvacEventType.together.with.pharmaceuticalCareEventType");
         throw new IntegrationModuleException(I18nHelper.getLabel("bvacEventType.together.with.pharmaceuticalCareEventType"));
      }
   }

   public void validateSingleMessageXsd() throws IntegrationModuleException, GFDDPPException {
      String procedure = "validateSingleMessageXsd";
      byte[] singleMessage = this.getXmlBytes();

      try {
         synchronized(smvh) {
            smvh.assertValidSingleMessage(singleMessage);
         }
      } catch (SingleMessageValidationException var5) {
         LOG.error("SingleMessageInfo=-----= ----- " + procedure + " =$=" + this.info + "error: error.validation.event.type");
         Exceptionutils.errorHandler(var5);
      }

   }

   public void validateBvacEventType() throws IntegrationModuleException {
      String procedure = "ValidateBvacEventType";
      if (this.HasBvacEventType) {
         Iterator var3 = this.singleMessageWrapper.getAllEventsOfType(BvacEventType.class).iterator();

         while(var3.hasNext()) {
            BvacEventType bvacEventType = (BvacEventType)var3.next();
            String cbfa = null;
            if (bvacEventType.getPharmacyList() != null && bvacEventType.getPharmacyList().getPharmacy().size() > 0 && ((Pharmacy)bvacEventType.getPharmacyList().getPharmacy().get(0)).getBvacList() != null && ((Pharmacy)bvacEventType.getPharmacyList().getPharmacy().get(0)).getBvacList().getBvacDocument().size() > 0 && ((BvacDocument)((Pharmacy)bvacEventType.getPharmacyList().getPharmacy().get(0)).getBvacList().getBvacDocument().get(0)).getIdentification() != null && ((BvacDocument)((Pharmacy)bvacEventType.getPharmacyList().getPharmacy().get(0)).getBvacList().getBvacDocument().get(0)).getIdentification().getBarCode() != null) {
               cbfa = ((BvacDocument)((Pharmacy)bvacEventType.getPharmacyList().getPharmacy().get(0)).getBvacList().getBvacDocument().get(0)).getIdentification().getBarCode().getInsurer();
            }

            if (!this.isValidCbfa(cbfa)) {
               LOG.error("SingleMessageInfo=-----= error " + procedure + " =$=" + " => " + "error: bvacEventType.validate.cbfa");
               throw new IntegrationModuleException(I18nHelper.getLabel("bvacEventType.validate.cbfa"));
            }
         }
      }

   }

   private boolean isValidCbfa(String cbfa) {
      List<String> cbfas = SystemServicesUtils.getInstance().getAllCbfas();
      return cbfas.contains(cbfa);
   }

   public void validateSingleMessagePatientMaxDataSet() throws GFDDPPException, IntegrationModuleException {
      String procedure = "validateSingleMessagePatientMaxDataSet";
      String patientId = null;
      if (this.HasPharmaceuticalCareEventType) {
         Iterator iterator = this.singleMessageWrapper.getAllEventsOfType(PharmaceuticalCareEventType.class).iterator();

         while(iterator.hasNext()) {
            PharmaceuticalCareEventType pharmaceuticalCareEventType = (PharmaceuticalCareEventType)iterator.next();
            if (pharmaceuticalCareEventType.getMaxPatient() == null) {
               LOG.error("SingleMessageInfo=-----= error " + procedure + " =$=" + " => " + "error: COMMON_ERROR_PATIENT_MISSING error.validation.patient.id.not.filled.in");
               throw new GFDDPPException(StatusCode.COMMON_ERROR_PATIENT_MISSING);
            }

            MaxSetPersonType maxSetPersonType = pharmaceuticalCareEventType.getMaxPatient().getIdentification();
            PersonIdType personIdType = null;
            if (maxSetPersonType == null) {
               LOG.error("SingleMessageInfo=-----= error " + procedure + " =$=" + " => " + "error: COMMON_ERROR_PATIENT_MISSING error.validation.patient.id.not.filled.in");
               throw new GFDDPPException(StatusCode.COMMON_ERROR_PATIENT_MISSING, new String[]{PharmaceuticalCareEventType.class.getName()});
            }

            personIdType = PersonIdType.valueOf(maxSetPersonType.getPersonId());
            String pId = personIdType.getIdFrom(maxSetPersonType.getPersonId());
            if (StringUtils.isEmpty(pId)) {
               LOG.error("SingleMessageInfo=-----= error " + procedure + " =$=" + " => " + "error: COMMON_ERROR_PATIENT_MISSING error.validation.patient.id.not.filled.in");
               throw new GFDDPPException(StatusCode.COMMON_ERROR_PATIENT_MISSING);
            }

            if (patientId == null) {
               patientId = pId;
            } else if (!pId.equals(patientId)) {
               GFDDPPException e = new GFDDPPException(StatusCode.COMMON_ERROR_MULTIPLE_PATIENT, new String[]{patientId, pId});
               LOG.error("SingleMessageInfo=-----= error " + procedure + " =$=" + " => " + "error: COMMON_ERROR_MULTIPLE_PATIENT");
               throw e;
            }
         }
      }

   }

   public String validateNihiiPharmacyId(String pharmacyIdOriginal) throws IntegrationModuleException {
      String procedure = "validateNihiiPharmacyId";
      String compareNihiiPharmacy = null;
      PharmaceuticalCareEventType pharmaceuticalCareEventType;
      Iterator var5;
      if (this.HasPharmaceuticalCareEventType) {
         var5 = this.singleMessageWrapper.getAllEventsOfType(PharmaceuticalCareEventType.class).iterator();

         while(var5.hasNext()) {
            pharmaceuticalCareEventType = (PharmaceuticalCareEventType)var5.next();
            if (pharmaceuticalCareEventType.getPharmacyId() == null || StringUtils.isEmpty(pharmaceuticalCareEventType.getPharmacyId().getNihiiPharmacyNumber())) {
               LOG.error("SingleMessageInfo=-----= error " + procedure + " =$=" + " => " + "error: pharmaceuticalCareEventType.pharmacy.nihii.not.found");
               throw new IntegrationModuleException(I18nHelper.getLabel("pharmaceuticalCareEventType.pharmacy.nihii.not.found"));
            }

            compareNihiiPharmacy = pharmaceuticalCareEventType.getPharmacyId().getNihiiPharmacyNumber();
            if (!pharmacyIdOriginal.equals(compareNihiiPharmacy)) {
               LOG.error("SingleMessageInfo=-----= error " + procedure + " =$=" + " => " + "error: validate.nihii.pharmaceuticalCareEventType.not.the.same");
               throw new IntegrationModuleException(I18nHelper.getLabel("validate.nihii.pharmaceuticalCareEventType.not.the.same"));
            }
         }
      }

      if (this.HasBvacEventType) {
         pharmaceuticalCareEventType = null;
         Iterator var6 = this.singleMessageWrapper.getAllEventsOfType(BvacEventType.class).iterator();

         while(var6.hasNext()) {
            BvacEventType bvacEventType = (BvacEventType)var6.next();
            if (bvacEventType.getPharmacyList() == null || bvacEventType.getPharmacyList().getPharmacy().size() <= 0 || ((Pharmacy)bvacEventType.getPharmacyList().getPharmacy().get(0)).getIdentification() == null || !StringUtils.isNotBlank(((Pharmacy)bvacEventType.getPharmacyList().getPharmacy().get(0)).getIdentification().getId())) {
               LOG.error("SingleMessageInfo=-----= error " + procedure + " =$=" + " => " + "error: bvacEventType.pharmacy.nihii.not.found");
               throw new IntegrationModuleException(I18nHelper.getLabel("bvacEventType.pharmacy.nihii.not.found"));
            }

            String nihiiPharmacy = ((Pharmacy)bvacEventType.getPharmacyList().getPharmacy().get(0)).getIdentification().getId();
            if (StringUtils.isEmpty(compareNihiiPharmacy)) {
               compareNihiiPharmacy = nihiiPharmacy;
            } else if (!compareNihiiPharmacy.equals(nihiiPharmacy)) {
               LOG.error("SingleMessageInfo=-----= error " + procedure + " =$=" + " => " + "error: validate.nihii.bvacEventType.not.the.same");
               throw new IntegrationModuleException(I18nHelper.getLabel("validate.nihii.bvacEventType.not.the.same"));
            }
         }
      }

      if (this.HasArchivePrescriptionEventType) {
         var5 = this.singleMessageWrapper.getAllEventsOfType(ArchivePrescriptionEventType.class).iterator();

         while(var5.hasNext()) {
            ArchivePrescriptionEventType archivePrescriptionEventType = (ArchivePrescriptionEventType)var5.next();
            if (archivePrescriptionEventType.getExecutorId() == null || StringUtils.isEmpty(archivePrescriptionEventType.getExecutorId().getValue())) {
               LOG.error("SingleMessageInfo=-----= error " + procedure + " =$=" + " => " + "error: archivePrescriptionEventType.pharmacy.nihii.not.found");
               throw new IntegrationModuleException(I18nHelper.getLabel("archivePrescriptionEventType.pharmacy.nihii.not.found"));
            }

            compareNihiiPharmacy = archivePrescriptionEventType.getExecutorId().getValue();
            if (!pharmacyIdOriginal.equals(compareNihiiPharmacy)) {
               LOG.error("SingleMessageInfo=-----= error " + procedure + " =$=" + " => " + "error: validate.nihii.archivePrescriptionEventType.not.the.same");
               throw new IntegrationModuleException(I18nHelper.getLabel("validate.nihii.archivePrescriptionEventType.not.the.same"));
            }
         }
      }

      if (this.HasArchivePrescriptionCommentEventType) {
         var5 = this.singleMessageWrapper.getAllEventsOfType(ArchivePrescriptionCommentEventType.class).iterator();

         while(var5.hasNext()) {
            ArchivePrescriptionCommentEventType apcet = (ArchivePrescriptionCommentEventType)var5.next();
            if (apcet.getExecutorId() == null || StringUtils.isEmpty(apcet.getExecutorId().getValue())) {
               LOG.error("SingleMessageInfo=-----= error " + procedure + " =$=" + " => " + "error: archivePrescriptionCommentEventType.pharmacy.nihii.not.found");
               throw new IntegrationModuleException(I18nHelper.getLabel("archivePrescriptionCommentEventType.pharmacy.nihii.not.found"));
            }

            compareNihiiPharmacy = apcet.getExecutorId().getValue();
            if (!pharmacyIdOriginal.equals(compareNihiiPharmacy)) {
               LOG.error("SingleMessageInfo=-----= error " + procedure + " =$=" + " => " + "error: validate.nihii.archivePrescriptionCommentEventType.not.the.same");
               throw new IntegrationModuleException(I18nHelper.getLabel("validate.nihii.archivePrescriptionCommentEventType.not.the.same"));
            }
         }
      }

      if (this.HasContinuedPharmaceuticalCareDossierEvent) {
         var5 = this.singleMessageWrapper.getAllEventsOfType(ContinuedPharmaceuticalCareDossierEvent.class).iterator();

         while(var5.hasNext()) {
            ContinuedPharmaceuticalCareDossierEvent apcet = (ContinuedPharmaceuticalCareDossierEvent)var5.next();
            if (apcet.getPharmacyId() == null || StringUtils.isEmpty(apcet.getPharmacyId().getNihiiPharmacyNumber())) {
               LOG.error("SingleMessageInfo=-----= error " + procedure + " =$=" + " => " + "error: ContinuedPharmaceuticalCareDossierEvent.pharmacy.nihii.not.found");
               throw new IntegrationModuleException(I18nHelper.getLabel("ContinuedPharmaceuticalCareDossierEvent.pharmacy.nihii.not.found"));
            }

            compareNihiiPharmacy = apcet.getPharmacyId().getNihiiPharmacyNumber();
            if (!pharmacyIdOriginal.equals(compareNihiiPharmacy)) {
               LOG.error("SingleMessageInfo=-----= error " + procedure + " =$=" + " => " + "error: validate.nihii.ContinuedPharmaceuticalCareDossierEvent.not.the.same");
               throw new IntegrationModuleException(I18nHelper.getLabel("validate.nihii.ContinuedPharmaceuticalCareDossierEvent.not.the.same"));
            }
         }
      }

      if (this.HasMedicationSchemeEntriesRequest || this.HasArchivePrescriptionEventType || this.HasArchivePrescriptionCommentEventType) {
         compareNihiiPharmacy = pharmacyIdOriginal;
      }

      if (compareNihiiPharmacy == null) {
         LOG.error("SingleMessageInfo=-----= error " + procedure + " =$=" + " => " + "error: pharmacy.nihii.not.found");
         throw new IntegrationModuleException(I18nHelper.getLabel("pharmacy.nihii.not.found"));
      } else {
         return compareNihiiPharmacy;
      }
   }

   public void validateMedicationHistoryPatient(String patientid) throws IntegrationModuleException {
      String procedure = "validateMedicationHistoryPatient";
      SingleMessageWrapper wrapper = this.singleMessageWrapper;
      Iterator iterator = wrapper.getAllMedicationHistoryEntries().iterator();

      while(iterator.hasNext()) {
         MedicationHistoryType medicationHistoryType = (MedicationHistoryType)iterator.next();
         if (medicationHistoryType.getMinPatient() == null) {
            LOG.error("SingleMessageInfo=-----= error " + procedure + " =$=" + " => " + "error: error.validation.patient.not.filled.in");
            throw new IntegrationModuleException(I18nHelper.getLabel("error.validation.patient.not.filled.in"));
         }

         PersonIdType personIdType = PersonIdType.valueOf(medicationHistoryType.getMinPatient().getPersonId());
         String pId = personIdType.getIdFrom(medicationHistoryType.getMinPatient().getPersonId());
         if (StringUtils.isEmpty(pId)) {
            LOG.error("SingleMessageInfo=-----= error " + procedure + " =$=" + " => " + "error: error.validation.patient.id.not.filled.in");
            throw new IntegrationModuleException(I18nHelper.getLabel("error.validation.patient.id.not.filled.in"));
         }

         if (!pId.equals(patientid)) {
            LOG.error("SingleMessageInfo=-----= error " + procedure + " =$=" + " => " + "error: error.validation.patient.id.not.equal.to.parameters");
            throw new IntegrationModuleException(I18nHelper.getLabel("error.validation.patient.id.not.equal.to.parameters") + " " + pId + " != " + patientid);
         }
      }

   }

   public void validateGetDataTypesResult() throws IntegrationModuleException {
      String procedure = "validateGetDataTypesResult";
      SingleMessageWrapper wrapper = this.singleMessageWrapper;
      Iterator iterator = wrapper.getAllEntitiesOfType(DataLocationType.class).iterator();

      while(iterator.hasNext()) {
         DataLocationType dlt = (DataLocationType)iterator.next();
         if (dlt.getLocation() == null) {
            LOG.error("SingleMessageInfo=-----= error " + procedure + " =$=" + " => " + "error: error.validation.empty.datatype.response");
            throw new IntegrationModuleException(I18nHelper.getLabel("error.validation.empty.datatype.response"));
         }

         if (dlt.getLocation().isEmpty()) {
            LOG.error("SingleMessageInfo=-----= error " + procedure + " =$=" + " => " + "error: error.validation.empty.datatype.response");
            throw new IntegrationModuleException(I18nHelper.getLabel("error.validation.empty.datatype.response"));
         }
      }

   }

   public void validateEmptyHeader() throws IntegrationModuleException {
      String procedure = "validateEmptyHeader";
      SingleMessageWrapper wrapper = this.singleMessageWrapper;
      if (wrapper.getHeader() != null && wrapper.getHeader().getMessageCreateDate() != null) {
         LOG.error("SingleMessageInfo=-----= ----- " + procedure + " =$=" + this.info + "error.validation.header.message.create.date.filled.in");
         throw new IntegrationModuleException(I18nHelper.getLabel("error.validation.header.message.create.date.filled.in"));
      } else if (wrapper.getHeader() != null && !StringUtils.isEmpty(wrapper.getHeader().getMessageID())) {
         LOG.error("SingleMessageInfo=-----= ----- " + procedure + " =$=" + this.info + "error.validation.header.message.id.filled.in");
         throw new IntegrationModuleException(I18nHelper.getLabel("error.validation.header.message.id.filled.in"));
      } else if (wrapper.getHeader() != null && wrapper.getHeader().getSender() != null) {
         LOG.error("SingleMessageInfo=-----= ----- " + procedure + " =$=" + this.info + "error.validation.header.sender.filled.in");
         throw new IntegrationModuleException(I18nHelper.getLabel("error.validation.header.sender.filled.in"));
      } else if (wrapper.getHeader() != null && !StringUtils.isEmpty(wrapper.getHeader().getVersion())) {
         LOG.error("SingleMessageInfo=-----= ----- " + procedure + " =$=" + this.info + "error.validation.header.version.filled.in");
         throw new IntegrationModuleException(I18nHelper.getLabel("error.validation.header.version.filled.in"));
      }
   }

   public void validateSingleMessageHeaderFilled(String NihiiPharmacyId) throws IntegrationModuleException {
      String procedure = "validateSingleMessageHeaderFilled";
      SingleMessageWrapper wrapper = this.singleMessageWrapper;
      HeaderType ht = wrapper.getHeader();
      if (ht != null) {
         if (ht.getMessageCreateDate() == null) {
            LOG.error("SingleMessageInfo=-----= ----- " + procedure + " =$=" + this.info + "error.validation.header.message.create.date.filled.in");
            throw new IntegrationModuleException(I18nHelper.getLabel("error.validation.header.message.create.date.filled.in"));
         }

         if (StringUtils.isEmpty(ht.getMessageID())) {
            LOG.error("SingleMessageInfo=-----= ----- " + procedure + " =$=" + this.info + "error.validation.header.message.id.filled.in");
            throw new IntegrationModuleException(I18nHelper.getLabel("error.validation.header.message.id.filled.in"));
         }

         if (ht.getSender() == null) {
            LOG.error("SingleMessageInfo=-----= ----- " + procedure + " =$=" + this.info + "error.validation.header.sender.filled.in");
            throw new IntegrationModuleException(I18nHelper.getLabel("error.validation.header.sender.filled.in"));
         }

         if (!this.getHeaderPharmacyId().equals(NihiiPharmacyId)) {
            LOG.error("SingleMessageInfo=-----= ----- " + procedure + " =$=" + this.info + "error.validation.header.sender.filled.in" + " " + NihiiPharmacyId);
            throw new IntegrationModuleException(I18nHelper.getLabel("error.validation.header.sender.filled.in"));
         }

         if (StringUtils.isEmpty(ht.getVersion())) {
            LOG.error("SingleMessageInfo=-----= ----- " + procedure + " =$=" + this.info + "error.validation.header.version.filled.in");
            throw new IntegrationModuleException(I18nHelper.getLabel("error.validation.header.version.filled.in"));
         }
      }

   }

   public void validateSGuidEmptyOk() throws IntegrationModuleException {
      String procedure = "validateSGuidEmptyOk";
      SingleMessageWrapper wrapper = this.singleMessageWrapper;
      List<PharmaceuticalCareEventType> pharmaceuticalCareEventTypes = wrapper.getAllEventsOfType(PharmaceuticalCareEventType.class);
      Iterator iterator = pharmaceuticalCareEventTypes.iterator();

      while(iterator.hasNext()) {
         PharmaceuticalCareEventType pharmaceuticalCareEventType = (PharmaceuticalCareEventType)iterator.next();
         if (!StringUtils.isEmpty(pharmaceuticalCareEventType.getId())) {
            LOG.error("SingleMessageInfo=-----= ----- " + procedure + " =$=" + this.info + "error: error.validation.sguid.filled.in");
            throw new IntegrationModuleException(I18nHelper.getLabel("error.validation.sguid.filled.in"));
         }
      }

      Iterator var6 = wrapper.getAllMedicationSchemeEntryRequests().iterator();

      while(var6.hasNext()) {
         MedicationSchemeEntriesRequest medicationSchemeEntriesRequest = (MedicationSchemeEntriesRequest)var6.next();
         if (StringUtils.isNotBlank(medicationSchemeEntriesRequest.getId())) {
            LOG.error("SingleMessageInfo=-----= ----- " + procedure + " =$=" + this.info + "error: error.validation.sguid.filled.in");
            throw new IntegrationModuleException(I18nHelper.getLabel("error.validation.sguid.filled.in"));
         }
      }

   }

   public void validateSGuidFilledOk() throws IntegrationModuleException {
      String procedure = "validateSGuidFilledOK";
      SingleMessageWrapper wrapper = this.singleMessageWrapper;
      List<PharmaceuticalCareEventType> pharmaceuticalCareEventTypes = wrapper.getAllEventsOfType(PharmaceuticalCareEventType.class);
      Iterator iterator = pharmaceuticalCareEventTypes.iterator();

      while(iterator.hasNext()) {
         PharmaceuticalCareEventType pharmaceuticalCareEventType = (PharmaceuticalCareEventType)iterator.next();
         if (StringUtils.isEmpty(pharmaceuticalCareEventType.getId())) {
            LOG.error("SingleMessageInfo=-----= ----- " + procedure + " =$=" + this.info + "error.validation.sguid.not.filled.in");
            throw new IntegrationModuleException(I18nHelper.getLabel("error.validation.sguid.not.filled.in"));
         }
      }

      iterator = wrapper.getAllMedicationHistoryEntries().iterator();

      while(iterator.hasNext()) {
         MedicationHistoryType medicationHistoryType = (MedicationHistoryType)iterator.next();
         if (StringUtils.isEmpty(medicationHistoryType.getSessionID())) {
            LOG.error("SingleMessageInfo=-----= ----- " + procedure + " =$=" + this.info + "error: error.validation.sguid.not.filled.in");
            throw new IntegrationModuleException(I18nHelper.getLabel("error.validation.sguid.not.filled.in"));
         }
      }

      Iterator var8 = wrapper.getAllMedicationSchemeEntryRequests().iterator();

      while(var8.hasNext()) {
         MedicationSchemeEntriesRequest medicationSchemeEntryRequest = (MedicationSchemeEntriesRequest)var8.next();
         if (StringUtils.isBlank(medicationSchemeEntryRequest.getId())) {
            LOG.error("SingleMessageInfo=-----= ----- " + procedure + " =$=" + this.info + "error: error.validation.sguid.not.filled.in");
            throw new IntegrationModuleException(I18nHelper.getLabel("error.validation.sguid.not.filled.in"));
         }
      }

   }

   public void validateDGuidEmptyOk() throws IntegrationModuleException {
      String procedure = "validateDGuidEmptyOk";
      SingleMessageWrapper wrapper = this.singleMessageWrapper;
      Iterator iterator = wrapper.getAllDispensedProducts().iterator();

      while(iterator.hasNext()) {
         MaxSetProductType product = (MaxSetProductType)iterator.next();
         if (!StringUtils.isEmpty(product.getDispensationGUID())) {
            LOG.error("SingleMessageInfo=-----= ----- " + procedure + " =$=" + this.info + "error: error.validation.dguid.filled.in");
            throw new IntegrationModuleException(I18nHelper.getLabel("error.validation.dguid.filled.in"));
         }
      }

      Iterator var8 = wrapper.getAllMedicationSchemeEntryRequests().iterator();

      while(var8.hasNext()) {
         MedicationSchemeEntriesRequest medicationSchemeEntryRequest = (MedicationSchemeEntriesRequest)var8.next();
         Iterator var6 = medicationSchemeEntryRequest.getDataEntry().iterator();

         while(var6.hasNext()) {
            DataEntryRequest dataEntryRequest = (DataEntryRequest)var6.next();
            if (StringUtils.isNotBlank(dataEntryRequest.getDguid())) {
               LOG.error("SingleMessageInfo=-----= ----- " + procedure + " =$=" + this.info + "error: error.validation.dguid.filled.in");
               throw new IntegrationModuleException(I18nHelper.getLabel("error.validation.dguid.filled.in"));
            }
         }
      }

   }

   public void validateDGuidFilledOk() throws IntegrationModuleException {
      String procedure = "validateDGuidFilledOk";
      SingleMessageWrapper wrapper = this.singleMessageWrapper;
      boolean validationFailure = false;
      Iterator iterator = wrapper.getAllDispensedProducts().iterator();

      while(iterator.hasNext()) {
         MaxSetProductType product = (MaxSetProductType)iterator.next();
         if (StringUtils.isEmpty(product.getDispensationGUID())) {
            validationFailure = true;
         }
      }

      iterator = wrapper.getAllMedicationHistoryEntries().iterator();

      while(iterator.hasNext()) {
         MedicationHistoryType medicationHistoryType = (MedicationHistoryType)iterator.next();
         if (medicationHistoryType.getEntityId() != null) {
            String dGuid = ((EntityIdType)medicationHistoryType.getEntityId()).getId();
            if (StringUtils.isEmpty(dGuid)) {
               validationFailure = true;
            }
         } else {
            validationFailure = true;
         }
      }

      Iterator var10 = wrapper.getAllMedicationSchemeEntryRequests().iterator();

      while(var10.hasNext()) {
         MedicationSchemeEntriesRequest medicationSchemeEntryRequest = (MedicationSchemeEntriesRequest)var10.next();
         Iterator var7 = medicationSchemeEntryRequest.getDataEntry().iterator();

         while(var7.hasNext()) {
            DataEntryRequest dataEntryRequest = (DataEntryRequest)var7.next();
            if (StringUtils.isBlank(dataEntryRequest.getDguid())) {
               validationFailure = true;
            }
         }
      }

      if (validationFailure) {
         LOG.error("SingleMessageInfo=-----= ----- " + procedure + " =$=" + this.info + "error: error.validation.dguid.not.filled.in");
         throw new IntegrationModuleException(I18nHelper.getLabel("error.validation.dguid.not.filled.in"));
      }
   }

   public void validateSessionDateEmpty() throws IntegrationModuleException {
      String procedure = "validateSessionDateEmpty";
      SingleMessageWrapper wrapper = new SingleMessageWrapper(this.singleMessageObject);
      List<PharmaceuticalCareEventType> pharmaceuticalCareEventTypes = wrapper.getAllEventsOfType(PharmaceuticalCareEventType.class);
      if (this.HasPharmaceuticalCareEventType) {
         Iterator iterator = pharmaceuticalCareEventTypes.iterator();

         while(iterator.hasNext()) {
            PharmaceuticalCareEventType pharmaceuticalCareEventType = (PharmaceuticalCareEventType)iterator.next();
            if (pharmaceuticalCareEventType.getSessionDateTime() != null) {
               LOG.error("SingleMessageInfo=-----= ----- " + procedure + " =$=" + this.info + "error: error.validation.session.date.filled.in");
               throw new IntegrationModuleException(I18nHelper.getLabel("error.validation.session.date.filled.in"));
            }
         }
      }

   }

   public void validateSessionDateOk() throws IntegrationModuleException {
      String procedure = "validateSessionDateOk";
      XMLGregorianCalendar sessionDateTime = null;
      if (this.HasPharmaceuticalCareEventType) {
         Iterator var4 = this.singleMessageWrapper.getAllEventsOfType(PharmaceuticalCareEventType.class).iterator();

         while(var4.hasNext()) {
            PharmaceuticalCareEventType pharmaceuticalCareEventType = (PharmaceuticalCareEventType)var4.next();
            if (pharmaceuticalCareEventType.getSessionDateTime() == null) {
               LOG.error("SingleMessageInfo=-----= ----- " + procedure + " =$=" + this.info + "error: pharmaceuticalCareEventType.session.date.time.not.found");
               throw new IntegrationModuleException(I18nHelper.getLabel("pharmaceuticalCareEventType.session.date.time.not.found"));
            }

            sessionDateTime = pharmaceuticalCareEventType.getSessionDateTime();
            Date sessionDateTimeDate = sessionDateTime.toGregorianCalendar().getTime();
            Calendar validationDate = Calendar.getInstance();
            validationDate.setTime(new Date());
            validationDate.add(11, 2);
            if (sessionDateTimeDate.after(validationDate.getTime())) {
               LOG.error("SingleMessageInfo=-----= ----- " + procedure + " =$=" + this.info + "error: pharmaceuticalCareEventType.session.date.time.in.future");
               throw new IntegrationModuleException(I18nHelper.getLabel("pharmaceuticalCareEventType.session.date.time.in.future"));
            }
         }
      }

   }

   public void validateMedicationHistorySessionDateFilledOk() throws IntegrationModuleException {
      String procedure = "validateMedicationHistorySessionDateFilledOk";
      SingleMessageWrapper wrapper = this.singleMessageWrapper;
      Iterator iterator = wrapper.getAllMedicationHistoryEntries().iterator();

      while(iterator.hasNext()) {
         MedicationHistoryType medicationHistoryType = (MedicationHistoryType)iterator.next();
         if (medicationHistoryType.getDeliveryDate() == null) {
            LOG.error("SingleMessageInfo=-----= ----- " + procedure + " =$=" + this.info + "error: error.validation.delivery.date.not.filled.in");
            throw new IntegrationModuleException(I18nHelper.getLabel("error.validation.delivery.date.not.filled.in"));
         }
      }

   }

   public void validateMotivationOk() throws IntegrationModuleException {
      String procedure = "validateMotivationOk";
      SingleMessageWrapper wrapper = this.singleMessageWrapper;
      Iterator iterator = wrapper.getAllDispensedProducts().iterator();

      while(iterator.hasNext()) {
         MaxSetProductType product = (MaxSetProductType)iterator.next();
         if (product.getMotivation() == null) {
            LOG.error("SingleMessageInfo=-----= ----- " + procedure + " =$=" + this.info + "error: error.validation.motivation.not.filled.in");
            throw new IntegrationModuleException(I18nHelper.getLabel("error.validation.motivation.not.filled.in"));
         }

         if (StringUtils.isEmpty(product.getMotivation().getFreeText())) {
            LOG.error("SingleMessageInfo=-----= ----- " + procedure + " =$=" + this.info + "error: error.validation.motivation.not.filled.in");
            throw new IntegrationModuleException(I18nHelper.getLabel("error.validation.motivation.not.filled.in"));
         }
      }

   }

   public void setHeaderNihiiPharmacyId(String NihiiPharmacyId) throws IntegrationModuleException {
      this.singleMessageObject.getUnsigned().setHeader(this.createHeaderType(NihiiPharmacyId));
      this.setSingleMessageObject(this.singleMessageObject);
   }

   private HeaderType createHeaderType(String compareNihiiPharmacy) throws IntegrationModuleException {
      String procedure = "createHeaderType";
      HeaderType header = new HeaderType();
      header.setSender(this.createSenderType(compareNihiiPharmacy));
      header.setMessageID(Utils.generateGUID(compareNihiiPharmacy));
      header.setMessageCreateDate(this.getXMLGregorianCalendarNow());

      try {
         header.setVersion(SingleMessageWrapper.getSMCVersion());
         return header;
      } catch (GFDDPPException var5) {
         LOG.error("SingleMessageInfo=-----= ----- " + procedure + " =$=" + this.info + "error: " + var5.getStatusCode());
         throw new IntegrationModuleException(StatusResolver.resolveMessage(StatusResolver.getLocalResourceBundle(), var5.getStatusCode(), var5.getPlaceHolders()), var5);
      }
   }

   private XMLGregorianCalendar getXMLGregorianCalendarNow() throws IntegrationModuleException {
      String procedure = "getXMLGregorianCalendarNow";

      try {
         GregorianCalendar gregorianCalendar = new GregorianCalendar();
         DatatypeFactory datatypeFactory = DatatypeFactory.newInstance();
         XMLGregorianCalendar now = datatypeFactory.newXMLGregorianCalendar(gregorianCalendar);
         return now;
      } catch (DatatypeConfigurationException var5) {
         LOG.error("SingleMessageInfo=-----= ----- " + procedure + " =$=" + this.info + "error: error.technical");
         throw new IntegrationModuleException(I18nHelper.getLabel("error.technical"));
      }
   }

   private SenderType createSenderType(String compareNihiiPharmacy) {
      ObjectFactory objectFactory = new ObjectFactory();
      SenderType senderType = new SenderType();
      ReferencePharmacyType referencePharmacyType = new ReferencePharmacyType();
      NihiiIdType nihiiIdType = new NihiiIdType();
      nihiiIdType.setNihiiPharmacyNumber(compareNihiiPharmacy);
      referencePharmacyType.setPharmacyId(nihiiIdType);
      senderType.setAbstractPharmacy(objectFactory.createRefPharmacy(referencePharmacyType));
      return senderType;
   }

   public void setFilterProducts(ProductFilterUtils productFilterUtils, PropertyHandler propertyHandler) throws IntegrationModuleException {
      String procedure = "setFilterProducts";
      if (this.HasPharmaceuticalCareEventType) {
         LOG.debug("SingleMessageInfo=-----= ----- " + procedure + " =$=" + this.info + "== Filtering products out of single message");
         SingleMessage updatedSingleMessage = this.singleMessageObject;
         updatedSingleMessage = productFilterUtils.filterProducts(this.singleMessageObject, propertyHandler);
         this.setSingleMessageObject(updatedSingleMessage);
      }

   }

   public void setGuids() throws IntegrationModuleException {
      this.setGuids((String)null);
   }

   public void setGuids(String nihiiPharmacyNumber) throws IntegrationModuleException {
      String procedure = "setGuids";
      SingleMessageWrapper wrapper = this.singleMessageWrapper;
      Iterator var5;
      String sGuid;
      String dGuid;
      if (this.HasPharmaceuticalCareEventType) {
         if (nihiiPharmacyNumber == null || nihiiPharmacyNumber.isEmpty()) {
            nihiiPharmacyNumber = wrapper.getPharmaceuticalCareEventTypePharmacyId((PharmaceuticalCareEventType)wrapper.getAllEventsOfType(PharmaceuticalCareEventType.class).get(0));
         }

         var5 = wrapper.getAllEventsOfType(PharmaceuticalCareEventType.class).iterator();

         while(var5.hasNext()) {
            PharmaceuticalCareEventType pharmaceuticalCareEventType = (PharmaceuticalCareEventType)var5.next();
            sGuid = Utils.generateGUID(String.valueOf(System.currentTimeMillis()) + nihiiPharmacyNumber);
            LOG.debug("SingleMessageInfo=-----= ----- " + procedure + " =$=" + this.info + "Generated SGUID: " + sGuid);
            pharmaceuticalCareEventType.setId(sGuid);
         }

         var5 = wrapper.getAllDispensedForSamePrescriptionProducts().iterator();

         String dGuid;
         AbstractMedicinalProductIdType abstractMedicinalProductIdType;
         while(var5.hasNext()) {
            PharmaceuticalCareEventType.DispensedForSamePrescription.Product product = (PharmaceuticalCareEventType.DispensedForSamePrescription.Product)var5.next();
            abstractMedicinalProductIdType = product.getDescription().getProductCode();
            dGuid = this.getProductId(abstractMedicinalProductIdType, product);
            dGuid = Utils.generateGUID(dGuid + nihiiPharmacyNumber);
            LOG.debug("SingleMessageInfo=-----= ----- " + procedure + " =$=" + this.info + "Generated DGUID: " + dGuid);
            product.setDispensationGUID(dGuid);
         }

         var5 = wrapper.getAllDispensedWithoutPrescriptionProducts().iterator();

         while(var5.hasNext()) {
            MaxSetProductType product = (MaxSetProductType)var5.next();
            abstractMedicinalProductIdType = product.getDescription().getProductCode();
            dGuid = this.getProductMaxSetProductTypeId(abstractMedicinalProductIdType, product);
            dGuid = Utils.generateGUID(dGuid + nihiiPharmacyNumber);
            LOG.debug("SingleMessageInfo=-----= ----- " + procedure + " =$=" + this.info + "Generated DGUID: " + dGuid);
            product.setDispensationGUID(dGuid);
         }
      }

      if (this.HasBvacEventType) {
         if (nihiiPharmacyNumber == null || nihiiPharmacyNumber.isEmpty()) {
            nihiiPharmacyNumber = wrapper.getBvacEventTypePharmacyId((BvacEventType)wrapper.getAllEventsOfType(BvacEventType.class).get(0));
         }

         var5 = wrapper.getAllEventsOfType(BvacEventType.class).iterator();

         while(var5.hasNext()) {
            BvacEventType bvacEventType = (BvacEventType)var5.next();
            sGuid = Utils.generateGUID(String.valueOf(System.currentTimeMillis()) + nihiiPharmacyNumber);
            LOG.debug("SingleMessageInfo=-----= ----- " + procedure + " =$=" + this.info + "Generated SGUID: " + sGuid);
            dGuid = Utils.generateGUID(String.valueOf(System.currentTimeMillis()) + nihiiPharmacyNumber);
            LOG.debug("SingleMessageInfo=-----= ----- " + procedure + " =$=" + this.info + "Generated DGUID: " + dGuid);
            bvacEventType.setId(sGuid);
            if (bvacEventType.getPharmacyList() != null && bvacEventType.getPharmacyList().getPharmacy().size() > 0 && ((Pharmacy)bvacEventType.getPharmacyList().getPharmacy().get(0)).getBvacList() != null && ((Pharmacy)bvacEventType.getPharmacyList().getPharmacy().get(0)).getBvacList().getBvacDocument().size() > 0 && ((BvacDocument)((Pharmacy)bvacEventType.getPharmacyList().getPharmacy().get(0)).getBvacList().getBvacDocument().get(0)).getIdentification() != null) {
               ((BvacDocument)((Pharmacy)bvacEventType.getPharmacyList().getPharmacy().get(0)).getBvacList().getBvacDocument().get(0)).getIdentification().setId(dGuid);
            }
         }
      }

      if (this.HasArchivePrescriptionEventType) {
         if (nihiiPharmacyNumber == null || nihiiPharmacyNumber.isEmpty()) {
            nihiiPharmacyNumber = wrapper.getArchivePrescriptionEventTypePharmacyId((ArchivePrescriptionEventType)wrapper.getAllEventsOfType(ArchivePrescriptionEventType.class).get(0));
         }

         var5 = wrapper.getAllEventsOfType(ArchivePrescriptionEventType.class).iterator();

         while(var5.hasNext()) {
            ArchivePrescriptionEventType apet = (ArchivePrescriptionEventType)var5.next();
            sGuid = Utils.generateGUID(String.valueOf(System.currentTimeMillis()) + nihiiPharmacyNumber);
            LOG.debug("SingleMessageInfo=-----= ----- " + procedure + " =$=" + this.info + "Generated SGUID: " + sGuid);
            dGuid = Utils.generateGUID(String.valueOf(System.currentTimeMillis()) + nihiiPharmacyNumber);
            LOG.debug("SingleMessageInfo=-----= ----- " + procedure + " =$=" + this.info + "Generated DGUID: " + dGuid);
            apet.setId(sGuid);
            apet.setSguid(sGuid);
            apet.setDguid(dGuid);
         }
      }

      if (this.HasArchivePrescriptionCommentEventType) {
         if (nihiiPharmacyNumber == null || nihiiPharmacyNumber.isEmpty()) {
            nihiiPharmacyNumber = wrapper.getArchivePrescriptionCommentEventTypePharmacyId((ArchivePrescriptionCommentEventType)wrapper.getAllEventsOfType(ArchivePrescriptionCommentEventType.class).get(0));
         }

         var5 = wrapper.getAllEventsOfType(ArchivePrescriptionCommentEventType.class).iterator();

         while(var5.hasNext()) {
            ArchivePrescriptionCommentEventType apcet = (ArchivePrescriptionCommentEventType)var5.next();
            sGuid = Utils.generateGUID(String.valueOf(System.currentTimeMillis()) + nihiiPharmacyNumber);
            LOG.debug("SingleMessageInfo=-----= ----- " + procedure + " =$=" + this.info + "Generated SGUID: " + sGuid);
            dGuid = Utils.generateGUID(String.valueOf(System.currentTimeMillis()) + nihiiPharmacyNumber);
            LOG.debug("SingleMessageInfo=-----= ----- " + procedure + " =$=" + this.info + "Generated DGUID: " + dGuid);
            apcet.setId(sGuid);
            apcet.setSguid(sGuid);
            apcet.setDguid(dGuid);
         }
      }

      if (this.HasMedicationSchemeEntriesRequest) {
         nihiiPharmacyNumber = StringUtils.defaultString(nihiiPharmacyNumber);
         var5 = wrapper.getAllMedicationSchemeEntryRequests().iterator();

         while(var5.hasNext()) {
            MedicationSchemeEntriesRequest medicationSchemeEntriesRequest = (MedicationSchemeEntriesRequest)var5.next();
            sGuid = Utils.generateGUID(String.valueOf(System.currentTimeMillis()) + nihiiPharmacyNumber);
            LOG.debug("SingleMessageInfo=-----= ----- " + procedure + " =$=" + this.info + "Generated SGUID: " + sGuid);
            medicationSchemeEntriesRequest.setId(sGuid);
            Iterator var18 = medicationSchemeEntriesRequest.getDataEntry().iterator();

            while(var18.hasNext()) {
               DataEntryRequest dataEntryRequest = (DataEntryRequest)var18.next();
               String dGuid = Utils.generateGUID(String.valueOf(System.currentTimeMillis()) + nihiiPharmacyNumber);
               LOG.debug("SingleMessageInfo=-----= ----- " + procedure + " =$=" + this.info + "Generated DGUID: " + dGuid);
               dataEntryRequest.setDguid(dGuid);
            }
         }
      }

      if (this.HasContinuedPharmaceuticalCareDossierEvent) {
         nihiiPharmacyNumber = StringUtils.defaultString(nihiiPharmacyNumber);
         var5 = wrapper.getAllEventsOfType(ContinuedPharmaceuticalCareDossierEvent.class).iterator();

         while(var5.hasNext()) {
            ContinuedPharmaceuticalCareDossierEvent event = (ContinuedPharmaceuticalCareDossierEvent)var5.next();
            sGuid = Utils.generateGUID(String.valueOf(System.currentTimeMillis()) + nihiiPharmacyNumber);
            LOG.debug("SingleMessageInfo=-----= ----- " + procedure + " =$=" + this.info + "Generated SGUID: " + sGuid);
            event.setId(sGuid);
            dGuid = Utils.generateGUID(String.valueOf(System.currentTimeMillis()) + nihiiPharmacyNumber);
            LOG.debug("SingleMessageInfo=-----= ----- " + procedure + " =$=" + this.info + "Generated DGUID: " + dGuid);
            event.setDguid(dGuid);
         }
      }

      this.setSingleMessageObject(this.singleMessageObject);
   }

   public SealedMessageRequestType setCommonData(EncryptionUtils encryptionUtils, String tipId, boolean requestPatientSignature, ThreeStateBoolean patientConsent) throws IntegrationModuleException, GFDDPPException, UnsupportedEncodingException, SingleMessageValidationException {
      SingleMessageWrapper wrapper = this.singleMessageWrapper;
      SealedMessageRequestType sealedMessageRequestType = new SealedMessageRequestType();
      sealedMessageRequestType.setRoutingParameters(this.createRoutingParameter(tipId));
      if (this.HasPharmaceuticalCareEventType) {
         MaxSetPatient patient = ((PharmaceuticalCareEventType)wrapper.getAllEventsOfType(PharmaceuticalCareEventType.class).get(0)).getMaxPatient();
         PersonIdType personIdType = PersonIdType.valueOf(patient.getIdentification().getPersonId());
         String patientid = personIdType.getIdFrom(patient.getIdentification().getPersonId());
         sealedMessageRequestType.setAuthorizationParameters(RequestCreatorUtils.createAuthorizationParameters(requestPatientSignature, patientConsent, (ThreeStateBoolean)null, patientid, encryptionUtils));
      }

      sealedMessageRequestType.setSingleMessageSealed(this.getXmlBytes());
      return sealedMessageRequestType;
   }

   private RoutingParametersType createRoutingParameter(String tipId) {
      RoutingParametersType routingParametersType = new RoutingParametersType();
      routingParametersType.setTIPId(tipId);
      return routingParametersType;
   }

   private String getProductId(AbstractMedicinalProductIdType abstractMedicinalProductIdType, PharmaceuticalCareEventType.DispensedForSamePrescription.Product product) {
      String productKey = null;
      if (abstractMedicinalProductIdType instanceof CnkIdType) {
         CnkIdType cnkIdType = (CnkIdType)product.getDescription().getProductCode();
         productKey = cnkIdType.getCnk();
      } else if (abstractMedicinalProductIdType instanceof BeRegistrationIdType) {
         BeRegistrationIdType beRegistrationIdType = (BeRegistrationIdType)product.getDescription().getProductCode();
         productKey = String.valueOf(beRegistrationIdType.getCti());
      } else if (abstractMedicinalProductIdType instanceof AtcDddSystemIdType) {
         AtcDddSystemIdType atcDddSystemIdType = (AtcDddSystemIdType)product.getDescription().getProductCode();
         if (!StringUtils.isEmpty(atcDddSystemIdType.getAtc())) {
            productKey = atcDddSystemIdType.getAtc();
         } else if (!StringUtils.isEmpty(atcDddSystemIdType.getDdd())) {
            productKey = atcDddSystemIdType.getDdd();
         }
      }

      return productKey;
   }

   private String getProductMaxSetProductTypeId(AbstractMedicinalProductIdType abstractMedicinalProductIdType, MaxSetProductType product) {
      String productKey = null;
      if (abstractMedicinalProductIdType instanceof CnkIdType) {
         CnkIdType cnkIdType = (CnkIdType)product.getDescription().getProductCode();
         productKey = cnkIdType.getCnk();
      } else if (abstractMedicinalProductIdType instanceof BeRegistrationIdType) {
         BeRegistrationIdType beRegistrationIdType = (BeRegistrationIdType)product.getDescription().getProductCode();
         productKey = String.valueOf(beRegistrationIdType.getCti());
      } else if (abstractMedicinalProductIdType instanceof AtcDddSystemIdType) {
         AtcDddSystemIdType atcDddSystemIdType = (AtcDddSystemIdType)product.getDescription().getProductCode();
         if (!StringUtils.isEmpty(atcDddSystemIdType.getAtc())) {
            productKey = atcDddSystemIdType.getAtc();
         } else if (!StringUtils.isEmpty(atcDddSystemIdType.getDdd())) {
            productKey = atcDddSystemIdType.getDdd();
         }
      }

      return productKey;
   }

   public String getEvents() throws IntegrationModuleException, GFDDPPException {
      SingleMessageWrapper wrapper = this.singleMessageWrapper;
      Events events = new Events();
      Iterator var5;
      if (this.HasBvacEventType) {
         be.apb.gfddpp.rtrn.registerdata.BvacEventType returnBvacEventType = new be.apb.gfddpp.rtrn.registerdata.BvacEventType();
         var5 = wrapper.getAllEventsOfType(BvacEventType.class).iterator();

         while(var5.hasNext()) {
            BvacEventType bvacEventType = (BvacEventType)var5.next();
            returnBvacEventType.setSguid(bvacEventType.getId());
            if (bvacEventType.getPharmacyList() != null && bvacEventType.getPharmacyList().getPharmacy().size() > 0 && ((Pharmacy)bvacEventType.getPharmacyList().getPharmacy().get(0)).getBvacList() != null && ((Pharmacy)bvacEventType.getPharmacyList().getPharmacy().get(0)).getBvacList().getBvacDocument().size() > 0 && ((BvacDocument)((Pharmacy)bvacEventType.getPharmacyList().getPharmacy().get(0)).getBvacList().getBvacDocument().get(0)).getIdentification() != null) {
               be.apb.gfddpp.rtrn.registerdata.BvacDocument bvacDocument = new be.apb.gfddpp.rtrn.registerdata.BvacDocument();
               bvacDocument.setDguid(((BvacDocument)((Pharmacy)bvacEventType.getPharmacyList().getPharmacy().get(0)).getBvacList().getBvacDocument().get(0)).getIdentification().getId());
               returnBvacEventType.setBvacDocument(bvacDocument);
            }
         }

         events.setBvacEventType(returnBvacEventType);
      } else if (this.HasPharmaceuticalCareEventType) {
         be.apb.gfddpp.rtrn.registerdata.PharmaceuticalCareEventType pharmCareEventType = new be.apb.gfddpp.rtrn.registerdata.PharmaceuticalCareEventType();
         List<Dispensation> productsList = pharmCareEventType.getDispensation();
         Iterator var23 = wrapper.getAllEventsOfType(PharmaceuticalCareEventType.class).iterator();

         while(var23.hasNext()) {
            PharmaceuticalCareEventType pharmaceuticalCareEventType = (PharmaceuticalCareEventType)var23.next();
            Iterator var8 = wrapper.getAllDispensedForSamePrescriptions().iterator();

            Iterator var10;
            Dispensation products;
            MinSetProductType.Description description;
            Product prdct;
            while(var8.hasNext()) {
               PharmaceuticalCareEventType.DispensedForSamePrescription dispensedForSamePrescription = (PharmaceuticalCareEventType.DispensedForSamePrescription)var8.next();

               for(var10 = dispensedForSamePrescription.getProduct().iterator(); var10.hasNext(); productsList.add(products)) {
                  PharmaceuticalCareEventType.DispensedForSamePrescription.Product product = (PharmaceuticalCareEventType.DispensedForSamePrescription.Product)var10.next();
                  products = new Dispensation();
                  products.setDguid(product.getDispensationGUID());
                  description = product.getDescription();
                  if (description.getProductCode() != null) {
                     prdct = new Product();
                     prdct.setProductId(ProductFilterUtils.getId(description.getProductCode()));
                     products.setProduct(prdct);
                  } else if (description.getMagistralPreparation() != null) {
                     products.setMagistral(ProductFilterUtils.getId(description.getMagistralPreparation()));
                  }

                  if (StringUtils.isBlank(pharmCareEventType.getSguid())) {
                     pharmCareEventType.setSguid(pharmaceuticalCareEventType.getId());
                  }

                  if (StringUtils.isNotEmpty(product.getLocalReference())) {
                     products.setLocalReference(product.getLocalReference());
                  }
               }
            }

            var8 = wrapper.getAllDispensedWithoutPrescriptions().iterator();

            while(var8.hasNext()) {
               PharmaceuticalCareEventType.DispensedWithoutPrescription dispensedWithoutPrescription = (PharmaceuticalCareEventType.DispensedWithoutPrescription)var8.next();

               for(var10 = dispensedWithoutPrescription.getProduct().iterator(); var10.hasNext(); productsList.add(products)) {
                  MaxSetProductType maxSetProductType = (MaxSetProductType)var10.next();
                  products = new Dispensation();
                  products.setDguid(maxSetProductType.getDispensationGUID());
                  description = maxSetProductType.getDescription();
                  if (description.getProductCode() != null) {
                     prdct = new Product();
                     prdct.setProductId(ProductFilterUtils.getId(description.getProductCode()));
                     products.setProduct(prdct);
                  } else if (description.getMagistralPreparation() != null) {
                     products.setMagistral(ProductFilterUtils.getId(description.getMagistralPreparation()));
                  }

                  if (StringUtils.isBlank(pharmCareEventType.getSguid())) {
                     pharmCareEventType.setSguid(pharmaceuticalCareEventType.getId());
                  }

                  if (StringUtils.isNotEmpty(maxSetProductType.getLocalReference())) {
                     products.setLocalReference(maxSetProductType.getLocalReference());
                  }
               }
            }
         }

         events.setPharmaceuticalCareEventType(pharmCareEventType);
      } else if (this.HasArchivePrescriptionEventType) {
         be.apb.gfddpp.rtrn.registerdata.ArchivePrescriptionEventType prescriptionEventType = new be.apb.gfddpp.rtrn.registerdata.ArchivePrescriptionEventType();
         var5 = wrapper.getAllEventsOfType(ArchivePrescriptionEventType.class).iterator();

         while(var5.hasNext()) {
            ArchivePrescriptionEventType archivePrescriptionEventType = (ArchivePrescriptionEventType)var5.next();
            prescriptionEventType.setSguid(archivePrescriptionEventType.getId());
            prescriptionEventType.setDguid(archivePrescriptionEventType.getDguid());
            prescriptionEventType.setRid(archivePrescriptionEventType.getRid());
         }

         events.setArchivePrescriptionEventType(prescriptionEventType);
      } else if (this.HasArchivePrescriptionCommentEventType) {
         be.apb.gfddpp.rtrn.registerdata.ArchivePrescriptionCommentEventType apcet = new be.apb.gfddpp.rtrn.registerdata.ArchivePrescriptionCommentEventType();
         var5 = wrapper.getAllEventsOfType(ArchivePrescriptionCommentEventType.class).iterator();

         while(var5.hasNext()) {
            ArchivePrescriptionCommentEventType archivePrescriptionCommentEventType = (ArchivePrescriptionCommentEventType)var5.next();
            apcet.setSguid(archivePrescriptionCommentEventType.getId());
            apcet.setDguid(archivePrescriptionCommentEventType.getDguid());
            apcet.setRid(archivePrescriptionCommentEventType.getRid());
         }

         events.setArchivePrescriptionCommentEventType(apcet);
      } else if (this.HasContinuedPharmaceuticalCareDossierEvent) {
         be.apb.gfddpp.rtrn.registerdata.ContinuedPharmaceuticalCareDossierEvent apcet = new be.apb.gfddpp.rtrn.registerdata.ContinuedPharmaceuticalCareDossierEvent();
         var5 = wrapper.getAllEventsOfType(ContinuedPharmaceuticalCareDossierEvent.class).iterator();

         while(var5.hasNext()) {
            ContinuedPharmaceuticalCareDossierEvent event = (ContinuedPharmaceuticalCareDossierEvent)var5.next();
            apcet.setSguid(event.getId());
            apcet.setDguid(event.getDguid());
         }

         events.setContinuedPharmaceuticalCareDossierEvent(apcet);
      }

      return JaxContextCentralizer.getInstance().toXml(Events.class, events);
   }

   public String getLogInfoGuids() {
      String info = " ";
      SingleMessageWrapper wrapper = this.singleMessageWrapper;

      try {
         info = info + "Version:" + wrapper.getVersion();
      } catch (Exception var11) {
      }

      try {
         info = info + " PharmacyId:" + wrapper.getHeaderPharmacyId();
      } catch (Exception var10) {
      }

      List medicationHistoryEvents;
      Iterator var5;
      Iterator var7;
      if (this.HasPharmaceuticalCareEventType) {
         try {
            medicationHistoryEvents = wrapper.getAllEventsOfType(PharmaceuticalCareEventType.class);
            var5 = medicationHistoryEvents.iterator();

            while(var5.hasNext()) {
               PharmaceuticalCareEventType event = (PharmaceuticalCareEventType)var5.next();
               info = info + "EventType:PharmaceuticalCareEventType";
               info = info + " sguid:" + event.getId();
               MaxSetProductType product;
               if (event.getDispensedWithoutPrescription() != null) {
                  for(var7 = event.getDispensedWithoutPrescription().getProduct().iterator(); var7.hasNext(); info = info + " dguid:" + product.getDispensationGUID()) {
                     product = (MaxSetProductType)var7.next();
                  }
               }

               var7 = event.getDispensedForSamePrescription().iterator();

               while(var7.hasNext()) {
                  PharmaceuticalCareEventType.DispensedForSamePrescription dispensedForSamePrescription = (PharmaceuticalCareEventType.DispensedForSamePrescription)var7.next();

                  PharmaceuticalCareEventType.DispensedForSamePrescription.Product product;
                  for(Iterator var9 = dispensedForSamePrescription.getProduct().iterator(); var9.hasNext(); info = info + " dguid:" + product.getDispensationGUID()) {
                     product = (PharmaceuticalCareEventType.DispensedForSamePrescription.Product)var9.next();
                  }
               }
            }
         } catch (Exception var16) {
         }
      }

      if (this.HasBvacEventType) {
         try {
            medicationHistoryEvents = wrapper.getAllEventsOfType(BvacEventType.class);
            var5 = medicationHistoryEvents.iterator();

            while(var5.hasNext()) {
               BvacEventType event = (BvacEventType)var5.next();
               info = info + "EventType:BvacEventType";

               BvacDocument bvacDocument;
               for(var7 = ((Pharmacy)event.getPharmacyList().getPharmacy().get(0)).getBvacList().getBvacDocument().iterator(); var7.hasNext(); info = info + "dguid:" + bvacDocument.getIdentification().getId()) {
                  bvacDocument = (BvacDocument)var7.next();
               }
            }
         } catch (Exception var15) {
         }
      }

      if (this.HasArchivePrescriptionEventType) {
         try {
            medicationHistoryEvents = wrapper.getAllEventsOfType(ArchivePrescriptionEventType.class);

            ArchivePrescriptionEventType event;
            for(var5 = medicationHistoryEvents.iterator(); var5.hasNext(); info = info + " dguid:" + event.getDguid()) {
               event = (ArchivePrescriptionEventType)var5.next();
               info = info + "EventType:ArchivePrescriptionEventType";
               info = info + "sguid:" + event.getSguid();
            }
         } catch (Exception var14) {
         }
      }

      if (this.HasArchivePrescriptionCommentEventType) {
         try {
            medicationHistoryEvents = wrapper.getAllEventsOfType(ArchivePrescriptionCommentEventType.class);

            ArchivePrescriptionCommentEventType event;
            for(var5 = medicationHistoryEvents.iterator(); var5.hasNext(); info = info + " dguid:" + event.getDguid()) {
               event = (ArchivePrescriptionCommentEventType)var5.next();
               info = info + "EventType:ArchivePrescriptionCommentEventType";
               info = info + "sguid:" + event.getSguid();
            }
         } catch (Exception var13) {
         }
      }

      if (this.HasMedicationHistoryEvent) {
         try {
            medicationHistoryEvents = wrapper.getAllEventsOfType(MedicationHistoryEvent.class);
            var5 = medicationHistoryEvents.iterator();

            while(var5.hasNext()) {
               MedicationHistoryEvent event = (MedicationHistoryEvent)var5.next();
               info = info + "EventType:MedicationHistoryEvent";
               info = info + " sguid:" + event.getId();

               MedicationHistoryType entity;
               for(var7 = event.getMedicationHistoryEntity().iterator(); var7.hasNext(); info = info + " dguid:" + ((EntityIdType)entity.getEntityId()).getId()) {
                  entity = (MedicationHistoryType)var7.next();
               }
            }
         } catch (Exception var12) {
         }
      }

      return info;
   }

   public String getInfo() {
      return this.info;
   }

   public boolean getHasSyncEventType() {
      return this.HasSyncEventType;
   }

   public boolean getHasMedicationHistoryEvent() {
      return this.HasMedicationHistoryEvent;
   }

   public boolean getHasMedicationSchemeEntriesRequest() {
      return this.HasMedicationSchemeEntriesRequest;
   }

   public String getMessageID() {
      return this.singleMessageWrapper.getMessageID();
   }

   public String getEventType() {
      return this.eventtype;
   }

   public String getHeaderPharmacyId() {
      return this.singleMessageWrapper.getHeaderPharmacyId();
   }

   public SingleMessageWrapper getWrapper() {
      return this.singleMessageWrapper;
   }

   public byte[] getXmlBytes() throws GFDDPPException {
      String s = this.getJaxContextCentralizer().toXml(SingleMessage.class, this.singleMessageObject);
      byte[] ret = s.getBytes(Charsets.UTF_8);
      return ret;
   }

   public String getXmlString() throws GFDDPPException {
      return this.getJaxContextCentralizer().toXml(SingleMessage.class, this.singleMessageObject);
   }
}
