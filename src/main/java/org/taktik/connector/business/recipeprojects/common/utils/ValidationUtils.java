package org.taktik.connector.business.recipeprojects.common.utils;

import java.nio.ByteBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import be.apb.gfddpp.common.constants.SupportedDataTypes;
import be.apb.gfddpp.common.utils.SingleMessageWrapper;
import be.apb.gfddpp.domain.PersonIdType;
import be.apb.gfddpp.helper.SingleMessageValidationHelper;
import be.apb.standards.gfddpp.constants.request.DateRangeTypes;
import be.apb.standards.smoa.schema.model.v1.DataLocationType;
import be.apb.standards.smoa.schema.model.v1.MaxSetPersonType;
import be.apb.standards.smoa.schema.model.v1.MaxSetProductType;
import be.apb.standards.smoa.schema.model.v1.MedicationHistoryType;
import be.apb.standards.smoa.schema.v1.PharmaceuticalCareEventType;
import be.apb.standards.smoa.schema.v1.SingleMessage;
import org.taktik.connector.business.recipeprojects.core.exceptions.IntegrationModuleException;
import org.taktik.connector.business.recipeprojects.core.utils.I18nHelper;
import org.taktik.connector.business.recipeprojects.core.utils.PropertyHandler;

public class ValidationUtils {

	private final static Logger LOG = Logger.getLogger(ValidationUtils.class);
	private final static SingleMessageValidationHelper smvh = new SingleMessageValidationHelper();

	public static void validateIncomingFieldsGetData(String patientIdType, String patientId, String dataType, String dateRange) throws IntegrationModuleException {

		if (StringUtils.isEmpty(patientId) || StringUtils.isEmpty(patientIdType)) {
			throw new IntegrationModuleException(I18nHelper.getLabel("error.validation.patientIdentifier"));
		}

		try {
			PersonIdType.valueOf(patientIdType);
		} catch (IllegalArgumentException e) {
			throw new IntegrationModuleException(I18nHelper.getLabel("error.validation.patientType"));
		}

		if (StringUtils.isEmpty(dataType) || !dataType.toLowerCase().equals(SupportedDataTypes.MEDICATION_HISTORY.getName())) {
			throw new IntegrationModuleException(I18nHelper.getLabel("error.validation.data.type"));
		}

		if (StringUtils.isEmpty(dateRange)) {
			throw new IntegrationModuleException(I18nHelper.getLabel("error.validation.date.range.empty"));
		}

		if (!StringUtils.equals(dateRange, DateRangeTypes.FULL.name()) && !StringUtils.equals(dateRange, DateRangeTypes.DEFAULT.name())) {

			if (dateRange.length() != 17 || dateRange.split("-").length != 2) {
				throw new IntegrationModuleException(I18nHelper.getLabel("error.validation.date.range.structure"));
			}

			String[] dates = dateRange.split("-");

			if (dates[0].length() != 8 || dates[1].length() != 8) {
				throw new IntegrationModuleException(I18nHelper.getLabel("error.validation.date.range.structure"));
			}

			if (!StringUtils.isNumeric(dates[0]) || !StringUtils.isNumeric(dates[1])) {
				throw new IntegrationModuleException(I18nHelper.getLabel("error.validation.date.range.notnumeric"));
			}

			if (Integer.parseInt(dates[0]) > Integer.parseInt(dates[1])) {
				throw new IntegrationModuleException(I18nHelper.getLabel("error.validation.date.range.startdate.larger.than.enddate"));
			}

			Calendar cal = Calendar.getInstance();
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
			String now = format.format(cal.getTime());

			if (Integer.parseInt(dates[0]) > Integer.parseInt(now) || Integer.parseInt(dates[1]) > Integer.parseInt(now)) {
				throw new IntegrationModuleException(I18nHelper.getLabel("error.validation.date.range.larger.than.now"));
			}
		}

	}

	public static void validateIncomingFieldsGetDataTypes(String patientId, String patientIdType) throws IntegrationModuleException {

		if (StringUtils.isEmpty(patientId)) {
			throw new IntegrationModuleException(I18nHelper.getLabel("error.validation.patientIdentifier"));
		}
		if (StringUtils.isEmpty(patientIdType)) {
			throw new IntegrationModuleException(I18nHelper.getLabel("error.validation.patientType"));
		}
		try {
			PersonIdType.valueOf(patientIdType);
		} catch (IllegalArgumentException e) {
			throw new IntegrationModuleException(I18nHelper.getLabel("error.validation.patientType.unknown"));
		}
	}

	public static void validateIncomingFieldsGetPharmacyDetails(String patientIdType, String patientId, String dGuid, String motivationText, String motivationType) throws IntegrationModuleException {
		if (StringUtils.isEmpty(patientId)) {
			throw new IntegrationModuleException(I18nHelper.getLabel("error.validation.patientIdentifier"));
		}
		if (StringUtils.isEmpty(patientIdType)) {
			throw new IntegrationModuleException(I18nHelper.getLabel("error.validation.patientIdType"));
		}

		try {
			PersonIdType.valueOf(patientIdType);
		} catch (IllegalArgumentException e) {
			throw new IntegrationModuleException(I18nHelper.getLabel("error.validation.patientType"));
		}

		if (StringUtils.isEmpty(dGuid)) {
			throw new IntegrationModuleException(I18nHelper.getLabel("error.validation.dGuid"));
		}
		if (StringUtils.isEmpty(motivationText)) {
			throw new IntegrationModuleException(I18nHelper.getLabel("error.validation.motivation"));
		}
		if (StringUtils.isEmpty(motivationType)) {
			throw new IntegrationModuleException(I18nHelper.getLabel("error.validation.motivationType"));
		}
	}

	public static void validateIncomingFieldsGetStatusMessage(String sGuid, String dGuid) throws IntegrationModuleException {
		if (!StringUtils.isEmpty(dGuid) && StringUtils.isEmpty(sGuid)) {
			throw new IntegrationModuleException(I18nHelper.getLabel("error.validation.emptysguidbutnotemptydguid"));
		}
	}

	public static void validateExistingTipId(String tipId, PropertyHandler propertyHandler) throws IntegrationModuleException {
		if (tipId == null ||
			SystemServicesUtils.getInstance(propertyHandler).getEndpointOutOfSystemConfiguration(tipId, "TIP", "TIPService") == null) {
			throw new IntegrationModuleException(I18nHelper.getLabel("error.validation.invalid.tip.id"));
		}
		LOG.info("TIP ID :" + tipId + " validated");
	}

	
	
	

	public static void validateSingleMessagePatientMaxDataSet(SingleMessage singleMessageLocal) throws IntegrationModuleException {
		SingleMessageWrapper wrapper = new SingleMessageWrapper(singleMessageLocal);
		String patientId = null;
		for (Iterator<PharmaceuticalCareEventType> iterator = wrapper.getAllEventsOfType(PharmaceuticalCareEventType.class).iterator(); iterator.hasNext();) {
			PharmaceuticalCareEventType pharmaceuticalCareEventType = iterator.next();
			if (pharmaceuticalCareEventType.getMaxPatient() != null) {
				MaxSetPersonType maxSetPersonType = pharmaceuticalCareEventType.getMaxPatient().getIdentification();
				PersonIdType personIdType = null;
				if (maxSetPersonType != null) {
					personIdType = PersonIdType.valueOf(maxSetPersonType.getPersonId());
				} else {
					throw new IntegrationModuleException(I18nHelper.getLabel("error.validation.patient.id.not.filled.in"));
				}
				String pId = personIdType.getIdFrom(maxSetPersonType.getPersonId());

				if (StringUtils.isEmpty(pId)) {
					throw new IntegrationModuleException(I18nHelper.getLabel("error.validation.patient.id.not.filled.in"));
				} else if (patientId == null) {
					patientId = pId;
				} else if (!pId.equals(patientId)) {
					throw new IntegrationModuleException(I18nHelper.getLabel("error.validation.patient.id.not.equal.to.parameters"));
				}
			} else {
				throw new IntegrationModuleException(I18nHelper.getLabel("error.validation.patient.not.filled.in"));
			}
		}
	}

	public static void validateSingleMessagePatient(String patientid, SingleMessage singleMessageLocal) throws IntegrationModuleException {
		SingleMessageWrapper wrapper = new SingleMessageWrapper(singleMessageLocal);

		for (Iterator<MedicationHistoryType> iterator = wrapper.getAllMedicationHistoryEntries().iterator(); iterator.hasNext();) {
			MedicationHistoryType medicationHistoryType = iterator.next();
			if (medicationHistoryType.getMinPatient() != null) {
				PersonIdType personIdType = PersonIdType.valueOf(medicationHistoryType.getMinPatient().getPersonId());
				String pId = personIdType.getIdFrom(medicationHistoryType.getMinPatient().getPersonId());
				if (StringUtils.isEmpty(pId)) {
					throw new IntegrationModuleException(I18nHelper.getLabel("error.validation.patient.id.not.filled.in"));
				} else if (!pId.equals(patientid)) {
					throw new IntegrationModuleException(I18nHelper.getLabel("error.validation.patient.id.not.equal.to.parameters"));
				}
			} else {
				throw new IntegrationModuleException(I18nHelper.getLabel("error.validation.patient.not.filled.in"));
			}
		}

	}

	/**
	 * Fires an error whenever the motivation's "free text" is empty.
	 * 
	 * @param singleMessageObject
	 *            The object to be checked
	 * @throws IntegrationModuleException
	 *             The exception thrown when the text field is empty
	 */
	public static void validateMotivationIsProvided(SingleMessage singleMessageObject) throws IntegrationModuleException {
		SingleMessageWrapper wrapper = new SingleMessageWrapper(singleMessageObject);

		for (Iterator<MaxSetProductType> iterator = wrapper.getAllDispensedProducts().iterator(); iterator.hasNext();) {
			MaxSetProductType product = iterator.next();
			if (product.getMotivation() == null) {
				throw new IntegrationModuleException(I18nHelper.getLabel("error.validation.motivation.not.filled.in"));
			}
			if (StringUtils.isEmpty(product.getMotivation().getFreeText())) {
				throw new IntegrationModuleException(I18nHelper.getLabel("error.validation.motivation.not.filled.in"));
			}
		}
	}

	public static void validateDataTypesResult(SingleMessage smo) throws IntegrationModuleException {
		SingleMessageWrapper wrapper = new SingleMessageWrapper(smo);

		for (Iterator<DataLocationType> iterator = wrapper.getAllEntitiesOfType(DataLocationType.class).iterator(); iterator.hasNext();) {
			DataLocationType dlt = iterator.next();
			if (dlt.getLocation() == null) {
				throw new IntegrationModuleException(I18nHelper.getLabel("error.validation.empty.datatype.response"));
			}
			if (dlt.getLocation().isEmpty()) {
				throw new IntegrationModuleException(I18nHelper.getLabel("error.validation.empty.datatype.response"));
			}
		}
	}

	public static boolean isValidCbfa(List<String> cbfas, String cbfa) {
		if (cbfas.contains(cbfa)) {
			return true;
		} else {
			return false;
		}
	}

	public static void validatePatientId(String patientId) throws IntegrationModuleException {
		if(!INSZUtils.isValidINSZ(patientId)){
			throw new IntegrationModuleException(I18nHelper.getLabel("error.validation.patientid.incorrect"));
		}
	}

	public static void validateUTF_8Input(byte[] input) throws IntegrationModuleException{
		if ( true == isValidUTF8(input)){
			return ;
		} else {
			throw new IntegrationModuleException(
					I18nHelper.getLabel("error.validation.utf-8.in"));
		}
	}
	public static boolean isValidUTF8( byte[] input ) {

	    CharsetDecoder cs = Charset.forName("UTF-8").newDecoder();

	    try {
	        cs.decode(ByteBuffer.wrap(input));
	        return true;
	    }
	    catch(CharacterCodingException e){
	        return false;
	    }       
	}
}
