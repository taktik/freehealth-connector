package be.business.connector.gfddpp.utils;

import be.apb.gfddpp.common.medicationscheme.status.MSStatusCode;
import be.apb.gfddpp.common.medicationscheme.status.MSStatusResolver;
import be.apb.standards.smoa.schema.v1.DataEntryResponse;
import be.apb.standards.smoa.schema.v1.Error;
import be.apb.standards.smoa.schema.v1.MedicationSchemeEntriesResponse;
import be.apb.standards.smoa.schema.v1.MedicationSchemeResponse;
import be.apb.standards.smoa.schema.v1.MedicationSchemeTimestampsResponse;
import be.apb.standards.smoa.schema.v1.MetaDataType;
import be.apb.standards.smoa.schema.v1.Organization;
import be.apb.standards.smoa.schema.v1.PaginationInfo;
import be.apb.standards.smoa.schema.v1.Person;
import be.apb.standards.smoa.schema.v1.PersonIdType;
import be.apb.standards.smoa.schema.v1.PersonIdentifier;
import org.taktik.connector.business.recipeprojects.core.exceptions.IntegrationModuleException;
import org.taktik.connector.business.recipeprojects.core.utils.I18nHelper;
import be.business.connector.gfddpp.domain.medicationscheme.DataEntry;
import be.business.connector.gfddpp.domain.medicationscheme.Node;
import be.business.connector.gfddpp.domain.medicationscheme.Source;
import be.business.connector.gfddpp.domain.medicationscheme.Status;
import be.business.connector.gfddpp.domain.medicationscheme.SubjectTimestamp;
import be.business.connector.gfddpp.domain.medicationscheme.UpdatedBy;
import be.business.connector.gfddpp.domain.medicationscheme.protocol.FetchDataEntriesResponse;
import be.business.connector.gfddpp.domain.medicationscheme.protocol.RetrieveTimestampsResponse;
import be.business.connector.gfddpp.tipsystem.threading.GeneralDecryptorThread;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Semaphore;
import net.sf.ehcache.Cache;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

public class MappingUtils {
   private static final Logger LOG = Logger.getLogger(MappingUtils.class);

   public static RetrieveTimestampsResponse mapMedicationSchemeTimestampsResponse(MedicationSchemeTimestampsResponse pcdhResponse) throws IntegrationModuleException {
      RetrieveTimestampsResponse response = new RetrieveTimestampsResponse();
      response.setCurrentDateTime(pcdhResponse.getCurrentDateTime().toGregorianCalendar());
      response.setClientMessageID(pcdhResponse.getClientMessageId());
      response.setServerMessageID(pcdhResponse.getServerMessageId());
      Iterator var3 = pcdhResponse.getSubjectTimestamp().iterator();

      while(var3.hasNext()) {
         MedicationSchemeTimestampsResponse.SubjectTimestamp st = (MedicationSchemeTimestampsResponse.SubjectTimestamp)var3.next();
         response.getSubjects().add(new SubjectTimestamp(st.getSubjectId(), st.getLastUpdated().toGregorianCalendar(), st.getVersion()));
      }

      MSStatusCode statusCode = mapStatusCode(pcdhResponse.getStatus().getCode());
      response.setStatus(new Status(statusCode.getCode(), MSStatusResolver.getStatusMessage(statusCode)));
      Iterator var4 = pcdhResponse.getStatus().getError().iterator();

      while(var4.hasNext()) {
         Error error = (Error)var4.next();
         MSStatusCode errorCode = mapStatusCode(error.getCode());
         response.getStatus().getErrors().add(new be.business.connector.gfddpp.domain.medicationscheme.Error(errorCode.getCode(), MSStatusResolver.getStatusMessage(errorCode), error.getReferenceType().name(), error.getReference()));
      }

      return response;
   }

   public static FetchDataEntriesResponse mapMedicationSchemeEntriesResponse(MedicationSchemeEntriesResponse pcdhResponse, Cache kgssCache, int threadLimit) throws IntegrationModuleException {
      FetchDataEntriesResponse response = new FetchDataEntriesResponse();
      response.setClientMessageID(pcdhResponse.getClientMessageId());
      response.setServerMessageID(pcdhResponse.getServerMessageId());
      response.setSubjectID(pcdhResponse.getSubjectId());
      response.setStatus(new Status());
      response.getStatus().setMessage(pcdhResponse.getStatus().getMessage());
      MSStatusCode resultStatusCode = mapStatusCode(pcdhResponse.getStatus().getCode());
      response.getStatus().setCode(resultStatusCode.getCode());
      Iterator var6 = pcdhResponse.getStatus().getError().iterator();

      while(var6.hasNext()) {
         Error error = (Error)var6.next();
         MSStatusCode errorCode = mapStatusCode(error.getCode());
         response.getStatus().getErrors().add(new be.business.connector.gfddpp.domain.medicationscheme.Error(errorCode.getCode(), MSStatusResolver.getStatusMessage(errorCode), error.getReferenceType().name(), error.getReference()));
      }

      if (!MSStatusCode.MEDICATION_SCHEME_SUCCESS.equals(resultStatusCode)) {
         return response;
      } else {
         MedicationSchemeResponse medicationScheme = pcdhResponse.getMedicationScheme();
         if (medicationScheme == null) {
            throw new IntegrationModuleException(I18nHelper.getLabel("error.no.medication.node"));
         } else {
            response.setLastUpdated(medicationScheme.getLastUpdated().toGregorianCalendar());
            response.setVersion(medicationScheme.getVersion());
            Node medicationNode = new Node();
            response.getNodes().add(medicationNode);
            medicationNode.setName("medication-scheme");
            medicationNode.setVersion(medicationScheme.getVersion());
            medicationNode.setUpdatedBy(new UpdatedBy());
            if (medicationScheme.getUpdatedBy().getOrganization() != null) {
               Organization pcdhOrganization = medicationScheme.getUpdatedBy().getOrganization();
               be.business.connector.gfddpp.domain.medicationscheme.Organization organization = new be.business.connector.gfddpp.domain.medicationscheme.Organization();
               organization.setName(pcdhOrganization.getName());
               organization.setActorIdValue(pcdhOrganization.getOrganizationId());
               organization.setActorIdSource(Source.fromValue(pcdhOrganization.getOrganizationIdType().name()));
               organization.setRole(pcdhOrganization.getRole().name());
               medicationNode.getUpdatedBy().setOrganization(organization);
            }

            if (medicationScheme.getUpdatedBy().getPerson() != null) {
               Person pcdhperson = medicationScheme.getUpdatedBy().getPerson();
               be.business.connector.gfddpp.domain.medicationscheme.Person person = new be.business.connector.gfddpp.domain.medicationscheme.Person();
               person.setFirstName(pcdhperson.getFirstName());
               person.setLastName(pcdhperson.getLastName());
               Iterator var10 = pcdhperson.getPersonId().iterator();

               while(var10.hasNext()) {
                  PersonIdentifier personId = (PersonIdentifier)var10.next();
                  if (PersonIdType.NIHII.equals(personId.getIdType())) {
                     person.setNihii(personId.getIdValue());
                  }

                  if (PersonIdType.INSS.equals(personId.getIdType())) {
                     person.setSsin(personId.getIdValue());
                  }
               }

               person.setRole(pcdhperson.getRole().name());
               medicationNode.getUpdatedBy().setPerson(person);
            }

            PaginationInfo pcdhPaginationInfo = medicationScheme.getPaginationInfo();
            medicationNode.setPagination(new be.business.connector.gfddpp.domain.medicationscheme.PaginationInfo());
            medicationNode.getPagination().setUri(DataEntryURI.readAllURI(pcdhResponse.getSubjectId()).toString());
            medicationNode.getPagination().setPaginationSize(pcdhPaginationInfo.getPaginationSize());
            medicationNode.getPagination().setPaginationIndex(pcdhPaginationInfo.getPaginationIndex());
            medicationNode.getPagination().setRecordCount(pcdhPaginationInfo.getRecordCount());
            List<DataEntry> dataEntries = new ArrayList();
            medicationNode.setDataEntries(dataEntries);
            Map<DataEntryResponse, GeneralDecryptorThread> decryptorThreads = doDecryptions(medicationScheme.getDataEntry(), kgssCache, threadLimit);

            DataEntry dataEntry;
            for(Iterator var11 = medicationScheme.getDataEntry().iterator(); var11.hasNext(); dataEntries.add(dataEntry)) {
               DataEntryResponse pcdhDataEntry = (DataEntryResponse)var11.next();
               dataEntry = new DataEntry();
               dataEntry.setNodeVersion(medicationNode.getVersion());
               dataEntry.setDataEntryURI(DataEntryURI.readURI(pcdhResponse.getSubjectId(), pcdhDataEntry.getDataEntryId(), pcdhDataEntry.getDataEntryVersion().intValue()).toString());
               dataEntry.setBusinessData(((GeneralDecryptorThread)decryptorThreads.get(pcdhDataEntry)).getDecoded());
               dataEntry.getMetadata().put("sguid", medicationScheme.getSguid());
               dataEntry.getMetadata().put("dguid", pcdhDataEntry.getDguid());
               dataEntry.getMetadata().put("tguid", pcdhDataEntry.getDataEntryId());
               dataEntry.getMetadata().put("availabilityStatus", pcdhDataEntry.getAvailabilityStatus().name());
               dataEntry.getMetadata().put("formatCode", pcdhDataEntry.getFormatCode().name());
               if (pcdhDataEntry.getAuthor() != null) {
                  if (pcdhDataEntry.getAuthor().getOrganization() != null) {
                     Organization organization = pcdhDataEntry.getAuthor().getOrganization();
                     addIfNotBlank(dataEntry.getMetadata(), "authorName", organization.getName());
                     dataEntry.getMetadata().put("authorOrganizationId", organization.getOrganizationId());
                     dataEntry.getMetadata().put("authorOrganizationIdSource", organization.getOrganizationIdType().name());
                     dataEntry.getMetadata().put("authorRole", organization.getRole().name());
                  }

                  if (pcdhDataEntry.getAuthor().getPerson() != null) {
                     Person person = pcdhDataEntry.getAuthor().getPerson();
                     addIfNotBlank(dataEntry.getMetadata(), "authorFirstName", person.getFirstName());
                     addIfNotBlank(dataEntry.getMetadata(), "authorLastName", person.getLastName());
                     Iterator var15 = person.getPersonId().iterator();

                     while(var15.hasNext()) {
                        PersonIdentifier personIdentifier = (PersonIdentifier)var15.next();
                        dataEntry.getMetadata().put("author" + personIdentifier.getIdType(), personIdentifier.getIdValue());
                     }

                     dataEntry.getMetadata().put("authorRole", person.getRole().name());
                  }
               }

               if (pcdhDataEntry.getMetaDataList() != null) {
                  List<MetaDataType> metaDataList = pcdhDataEntry.getMetaDataList().getMetaData();

                  for(int i = 0; i < metaDataList.size(); ++i) {
                     dataEntry.getMetadata().put(((MetaDataType)metaDataList.get(i)).getKey(), ((MetaDataType)metaDataList.get(i)).getValue());
                  }
               }
            }

            return response;
         }
      }
   }

   private static void addIfNotBlank(Map<String, String> dataMap, String key, String value) {
      if (StringUtils.isNotBlank(value)) {
         dataMap.put(key, value);
      }

   }

   public static MSStatusCode mapStatusCode(String statusCode) throws IntegrationModuleException {
      try {
         return MSStatusCode.valueOf(statusCode);
      } catch (IllegalArgumentException var1) {
         throw new IntegrationModuleException(I18nHelper.getLabel("error.validation.status.code"));
      }
   }

   private static Map<DataEntryResponse, GeneralDecryptorThread> doDecryptions(List<DataEntryResponse> dataEntries, Cache kgssCache, int threadLimit) throws IntegrationModuleException {
      Semaphore availableThreads = new Semaphore(threadLimit, true);
      Map<DataEntryResponse, GeneralDecryptorThread> decryptorThreads = new HashMap();
      Iterator var6 = dataEntries.iterator();

      while(var6.hasNext()) {
         DataEntryResponse dataEntryResponse = (DataEntryResponse)var6.next();
         if (dataEntryResponse.getEncryptedData() == null) {
            throw new IntegrationModuleException(I18nHelper.getLabel("error.no.business.data", new Object[]{dataEntryResponse.getDguid()}));
         }

         String encryptionKeyId = dataEntryResponse.getEncryptedData().getEncryptionKeyId();
         byte[] encryptedData = dataEntryResponse.getEncryptedData().getData();
         GeneralDecryptorThread businessDataThread = new GeneralDecryptorThread(availableThreads, encryptedData, encryptionKeyId, kgssCache);
         decryptorThreads.put(dataEntryResponse, businessDataThread);
      }

      var6 = decryptorThreads.values().iterator();

      while(var6.hasNext()) {
         GeneralDecryptorThread decryptorThread = (GeneralDecryptorThread)var6.next();
         availableThreads.acquireUninterruptibly();
         decryptorThread.start();
      }

      try {
         availableThreads.acquireUninterruptibly(threadLimit);
      } catch (IllegalArgumentException var10) {
         LOG.debug("Incorrect Thread configuration : " + var10);
      }

      return decryptorThreads;
   }
}
