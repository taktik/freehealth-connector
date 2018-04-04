package be.business.connector.gfddpp.utils;

import be.apb.gfddpp.common.medicationscheme.status.MSStatusCode;
import be.apb.gfddpp.common.medicationscheme.status.MSStatusResolver;
import be.apb.gfddpp.common.medicationscheme.validator.MedicationSchemeValidator;
import be.apb.gfddpp.common.medicationscheme.validator.ValidatorException;
import be.apb.standards.smoa.schema.v1.AvailabilityStatus;
import be.apb.standards.smoa.schema.v1.FormatCode;
import org.taktik.connector.business.recipeprojects.core.exceptions.IntegrationModuleException;
import be.business.connector.gfddpp.domain.medicationscheme.DataEntry;
import be.business.connector.gfddpp.domain.medicationscheme.Error;
import be.business.connector.gfddpp.domain.medicationscheme.ReferenceType;
import be.business.connector.gfddpp.domain.medicationscheme.protocol.FetchDataEntriesRequest;
import be.business.connector.gfddpp.domain.medicationscheme.protocol.RetrieveTimestampsRequest;
import be.business.connector.gfddpp.domain.medicationscheme.protocol.StoreDataEntriesRequest;
import org.taktik.connector.business.recipeprojects.common.utils.INSZUtils;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.xml.sax.SAXException;

public class GFDDPPValidationUtils {
   private static final Logger LOG = Logger.getLogger(GFDDPPValidationUtils.class);
   private static final String AVAILABILITY_STATUS_KEY = "availabilityStatus";
   private static final String FORMAT_CODE_KEY = "formatCode";
   private static DocumentBuilder sourceParser;

   private static DocumentBuilder getSourceParser() throws IntegrationModuleException {
      if (sourceParser == null) {
         try {
            DocumentBuilderFactory pce = DocumentBuilderFactory.newInstance();
            pce.setNamespaceAware(true);
            sourceParser = pce.newDocumentBuilder();
         } catch (ParserConfigurationException var1) {
            throw new IntegrationModuleException("XML/XSD parser could not be set up.", var1);
         }
      }

      return sourceParser;
   }

   public static List<Error> validate(RetrieveTimestampsRequest retrieveTimestampsRequest) {
      List<Error> errors = new ArrayList();
      if (retrieveTimestampsRequest != null && retrieveTimestampsRequest.getSubjectIDs().size() > 0) {
         if (retrieveTimestampsRequest.getSubjectIDs().size() > 10) {
            errors.add(createError(MSStatusCode.MEDICATION_SCHEME_INVALID_SUBJECTID_COUNT, ReferenceType.VALIDATION_ERROR, Integer.toString(errors.size() + 1), Integer.valueOf(0), Integer.valueOf(10)));
         }

         Iterator var3 = retrieveTimestampsRequest.getSubjectIDs().iterator();

         while(true) {
            String subjectId;
            do {
               if (!var3.hasNext()) {
                  if (retrieveTimestampsRequest.getPersonInformation() != null) {
                     errors.add(createError(MSStatusCode.MEDICATION_SCHEME_PERSON_INFORMATION_SPECIFIED, ReferenceType.VALIDATION_ERROR, Integer.toString(errors.size() + 1)));
                  }

                  if (retrieveTimestampsRequest.getOrganizationInformation() != null) {
                     errors.add(createError(MSStatusCode.MEDICATION_SCHEME_ORGANIZATION_INFORMATION_SPECIFIED, ReferenceType.VALIDATION_ERROR, Integer.toString(errors.size() + 1)));
                  }

                  return errors;
               }

               subjectId = (String)var3.next();
            } while(subjectId != null && INSZUtils.isValidINSZ(subjectId));

            errors.add(createError(MSStatusCode.MEDICATION_SCHEME_INVALID_INSZ, ReferenceType.SUBJECT_SSIN, subjectId));
         }
      } else {
         errors.add(createError(MSStatusCode.MEDICATION_SCHEME_NO_DATA, ReferenceType.VALIDATION_ERROR, Integer.toString(errors.size() + 1)));
         return errors;
      }
   }

   public static List<Error> validate(FetchDataEntriesRequest getMedicationEntries) {
      List<Error> errors = new ArrayList();
      if (getMedicationEntries == null) {
         errors.add(createError(MSStatusCode.MEDICATION_SCHEME_NO_DATA, ReferenceType.VALIDATION_ERROR, Integer.toString(errors.size() + 1)));
      } else {
         String subjectId = getMedicationEntries.getSubjectID();
         if (StringUtils.isBlank(subjectId)) {
            errors.add(createError(MSStatusCode.MEDICATION_SCHEME_NO_INSZ, ReferenceType.VALIDATION_ERROR, Integer.toString(errors.size() + 1)));
         }

         if (StringUtils.isNotBlank(subjectId) && !INSZUtils.isValidINSZ(subjectId)) {
            errors.add(createError(MSStatusCode.MEDICATION_SCHEME_INVALID_INSZ, ReferenceType.SUBJECT_SSIN, subjectId));
         }

         if (getMedicationEntries.getVersion() != null) {
            errors.add(createError(MSStatusCode.MEDICATION_SCHEME_VERSION_SPECIFIED, ReferenceType.VALIDATION_ERROR, Integer.toString(errors.size() + 1)));
         }

         if (getMedicationEntries.getBreakTheGlass() != null) {
            errors.add(createError(MSStatusCode.MEDICATION_SCHEME_BREAK_THE_GLASS_SPECIFIED, ReferenceType.VALIDATION_ERROR, Integer.toString(errors.size() + 1)));
         }

         if (!getMedicationEntries.includeBusinessData()) {
            errors.add(createError(MSStatusCode.MEDICATION_SCHEME_REQUEST_NO_BUSINESSDATA, ReferenceType.VALIDATION_ERROR, Integer.toString(errors.size() + 1)));
         }

         if (getMedicationEntries.getSearchCriteria() != null) {
            errors.add(createError(MSStatusCode.MEDICATION_SCHEME_SEARCHCRITERIA_SPECIFIED, ReferenceType.VALIDATION_ERROR, Integer.toString(errors.size() + 1)));
         }

         if (getMedicationEntries.getPersonInformation() != null) {
            errors.add(createError(MSStatusCode.MEDICATION_SCHEME_PERSON_INFORMATION_SPECIFIED, ReferenceType.VALIDATION_ERROR, Integer.toString(errors.size() + 1)));
         }

         if (getMedicationEntries.getOrganizationInformation() != null) {
            errors.add(createError(MSStatusCode.MEDICATION_SCHEME_ORGANIZATION_INFORMATION_SPECIFIED, ReferenceType.VALIDATION_ERROR, Integer.toString(errors.size() + 1)));
         }

         if (getMedicationEntries.getPagination() != null) {
            if (getMedicationEntries.getPagination().getPaginationIndex() <= 0) {
               errors.add(createError(MSStatusCode.MEDICATION_SCHEME_INVALID_PAGINATION_INDEX, ReferenceType.VALIDATION_ERROR, Integer.toString(errors.size() + 1)));
            }

            DataEntryURI uri = new DataEntryURI(getMedicationEntries.getPagination().getUri());
            if (!uri.isReadAllURI()) {
               errors.add(createError(MSStatusCode.MEDICATION_SCHEME_INVALID_FETCH_DATA_URI, ReferenceType.VALIDATION_ERROR, Integer.toString(errors.size() + 1)));
            }

            if (StringUtils.isNotBlank(subjectId) && !subjectId.equals(uri.getSubjectId())) {
               errors.add(createError(MSStatusCode.MEDICATION_SCHEME_SUBJECT_ID_MISMATCH, ReferenceType.VALIDATION_ERROR, Integer.toString(errors.size() + 1)));
            }
         }
      }

      return errors;
   }

   public static List<Error> validate(StoreDataEntriesRequest storeDataEntriesRequest) throws IntegrationModuleException {
      List<Error> errors = new ArrayList();
      if (storeDataEntriesRequest == null) {
         errors.add(createError(MSStatusCode.MEDICATION_SCHEME_NO_DATA, ReferenceType.VALIDATION_ERROR, Integer.toString(errors.size() + 1)));
         return errors;
      } else {
         String subjectId = storeDataEntriesRequest.getSubjectID();
         if (StringUtils.isBlank(subjectId)) {
            errors.add(createError(MSStatusCode.MEDICATION_SCHEME_NO_INSZ, ReferenceType.VALIDATION_ERROR, Integer.toString(errors.size() + 1)));
         }

         if (StringUtils.isNotBlank(subjectId) && !INSZUtils.isValidINSZ(subjectId)) {
            errors.add(createError(MSStatusCode.MEDICATION_SCHEME_INVALID_INSZ, ReferenceType.SUBJECT_SSIN, subjectId));
         }

         if (storeDataEntriesRequest.getPersonInformation() != null) {
            errors.add(createError(MSStatusCode.MEDICATION_SCHEME_PERSON_INFORMATION_SPECIFIED, ReferenceType.VALIDATION_ERROR, Integer.toString(errors.size() + 1)));
         }

         if (storeDataEntriesRequest.getOrganizationInformation() != null) {
            errors.add(createError(MSStatusCode.MEDICATION_SCHEME_ORGANIZATION_INFORMATION_SPECIFIED, ReferenceType.VALIDATION_ERROR, Integer.toString(errors.size() + 1)));
         }

         if (CollectionUtils.isEmpty(storeDataEntriesRequest.getDataEntries())) {
            errors.add(createError(MSStatusCode.MEDICATION_SCHEME_NO_DATAENTRIES, ReferenceType.VALIDATION_ERROR, Integer.toString(errors.size() + 1)));
            return errors;
         } else {
            boolean detectedMissingReference = false;
            Integer nodeVersionOfFirstEntry = ((DataEntry)storeDataEntriesRequest.getDataEntries().get(0)).getNodeVersion();
            boolean differentNodeVersionsDetected = false;
            Iterator var7 = storeDataEntriesRequest.getDataEntries().iterator();

            while(true) {
               while(var7.hasNext()) {
                  DataEntry dataEntry = (DataEntry)var7.next();
                  if (StringUtils.isBlank(dataEntry.getReference())) {
                     if (!detectedMissingReference) {
                        errors.add(createError(MSStatusCode.MEDICATION_SCHEME_NO_DATA_ENTRY_REFERENCE, ReferenceType.VALIDATION_ERROR, Integer.toString(errors.size() + 1)));
                        detectedMissingReference = true;
                     }
                  } else {
                     if (!differentNodeVersionsDetected && (nodeVersionOfFirstEntry == null && dataEntry.getNodeVersion() != null || nodeVersionOfFirstEntry != null && !nodeVersionOfFirstEntry.equals(dataEntry.getNodeVersion()))) {
                        errors.add(createError(MSStatusCode.MEDICATION_SCHEME_MULTIPLE_NODE_VERSIONS, ReferenceType.VALIDATION_ERROR, Integer.toString(errors.size() + 1)));
                        differentNodeVersionsDetected = true;
                     }

                     Map<String, String> metaData = dataEntry.getMetadata();
                     if (!metaData.containsKey("availabilityStatus")) {
                        errors.add(createError(MSStatusCode.MEDICATION_SCHEME_NO_AVAILABILITY_STATUS, ReferenceType.CORRELATION_ID, dataEntry.getReference()));
                     } else if (!EnumUtils.isValidEnum(AvailabilityStatus.class, ((String)metaData.get("availabilityStatus")).toUpperCase())) {
                        errors.add(createError(MSStatusCode.MEDICATION_SCHEME_INVALID_AVAILABILITY_STATUS, ReferenceType.CORRELATION_ID, dataEntry.getReference(), EnumUtils.getEnumList(AvailabilityStatus.class)));
                     }

                     Source businessDataSource = null;
                     if (dataEntry.getBusinessData() != null && dataEntry.getBusinessData().length != 0) {
                        businessDataSource = parseDocument(dataEntry.getBusinessData());
                        if (businessDataSource == null) {
                           errors.add(createError(MSStatusCode.MEDICATION_SCHEME_UNPARSEABLE_BUSINESSDATA, ReferenceType.CORRELATION_ID, dataEntry.getReference()));
                        }
                     } else {
                        errors.add(createError(MSStatusCode.MEDICATION_SCHEME_NO_BUSINESSDATA, ReferenceType.CORRELATION_ID, dataEntry.getReference()));
                     }

                     FormatCode formatCode = null;
                     if (!metaData.containsKey("formatCode")) {
                        errors.add(createError(MSStatusCode.MEDICATION_SCHEME_NO_FORMAT_CODE, ReferenceType.CORRELATION_ID, dataEntry.getReference()));
                     } else {
                        try {
                           formatCode = FormatCode.valueOf(((String)metaData.get("formatCode")).toUpperCase());
                        } catch (Exception var14) {
                           errors.add(createError(MSStatusCode.MEDICATION_SCHEME_INVALID_FORMAT_CODE, ReferenceType.CORRELATION_ID, dataEntry.getReference(), EnumUtils.getEnumList(FormatCode.class)));
                        }
                     }

                     if (formatCode != null && businessDataSource != null) {
                        try {
                           List<String> validationErrors = MedicationSchemeValidator.validateMedicationScheme(businessDataSource, formatCode);
                           Iterator var13 = validationErrors.iterator();

                           while(var13.hasNext()) {
                              String validationError = (String)var13.next();
                              errors.add(createError(MSStatusCode.MEDICATION_SCHEME_INVALID_BUSINESSDATA, ReferenceType.CORRELATION_ID, dataEntry.getReference(), validationError));
                           }
                        } catch (ValidatorException var15) {
                           throw new IntegrationModuleException(var15.getMessage(), var15.getCause());
                        }
                     }

                     DataEntryURI uri = new DataEntryURI(dataEntry.getDataEntryURI());
                     if (!uri.isCreateURI() && !uri.isUpdateURI()) {
                        errors.add(createError(MSStatusCode.MEDICATION_SCHEME_INVALID_STORE_DATA_URI, ReferenceType.CORRELATION_ID, dataEntry.getReference()));
                     } else if (StringUtils.isNotBlank(subjectId) && !subjectId.equals(uri.getSubjectId())) {
                        errors.add(createError(MSStatusCode.MEDICATION_SCHEME_SUBJECT_ID_MISMATCH, ReferenceType.CORRELATION_ID, dataEntry.getReference()));
                     }
                  }
               }

               return errors;
            }
         }
      }
   }

   private static Source parseDocument(byte[] data) throws IntegrationModuleException {
      ByteArrayInputStream bais = null;

      try {
         bais = new ByteArrayInputStream(data);
         DOMSource var4 = new DOMSource(getSourceParser().parse(bais));
         return var4;
      } catch (IOException var8) {
         LOG.error("Failed to read input file", var8);
      } catch (SAXException var9) {
         LOG.error("Failed to parse input file to w3c structure", var9);
      } finally {
         IOUtils.closeQuietly((InputStream)bais);
      }

      return null;
   }

   private static Error createError(MSStatusCode code, ReferenceType referenceType, String reference, Object... context) {
      return new Error(code.getCode(), MSStatusResolver.getStatusMessage(code, context), referenceType.name(), reference);
   }
}
