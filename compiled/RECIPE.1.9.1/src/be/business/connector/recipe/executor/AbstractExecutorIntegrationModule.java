package be.business.connector.recipe.executor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import be.business.connector.common.StandaloneRequestorProvider;
import be.business.connector.common.module.AbstractIntegrationModule;
import be.business.connector.core.domain.KgssIdentifierType;
import be.business.connector.core.exceptions.IntegrationModuleException;
import be.business.connector.core.handlers.InsurabilityHandler;
import be.business.connector.core.utils.I18nHelper;
import be.business.connector.core.utils.IOUtils;
import be.business.connector.core.utils.MarshallerHelper;
import be.business.connector.recipe.executor.domain.GetPrescriptionForExecutorResult;
import be.business.connector.recipe.executor.domain.ListNotificationsItem;
import be.business.connector.recipe.utils.KmehrHelper;
import be.ehealth.technicalconnector.service.kgss.domain.KeyResult;
import be.fgov.ehealth.commons.core.v1.IdentifierType;
import be.fgov.ehealth.commons.core.v1.LocalisedString;
import be.fgov.ehealth.commons.core.v1.StatusType;
import be.fgov.ehealth.commons.protocol.v1.ResponseType;
import be.fgov.ehealth.etee.crypto.encrypt.EncryptionToken;
import be.fgov.ehealth.recipe.core.v4.SecuredContentType;
import be.recipe.services.executor.CreateFeedbackParam;
import be.recipe.services.executor.GetPrescriptionForExecutorParam;
import be.recipe.services.executor.GetPrescriptionForExecutorResultSealed;
import be.recipe.services.executor.GetPrescriptionStatusParam;
import be.recipe.services.executor.ListNotificationsParam;
import be.recipe.services.executor.ListNotificationsResult;
import be.recipe.services.executor.ListReservations;
import be.recipe.services.executor.ListReservationsParam;
import be.recipe.services.executor.ListReservationsResult;
import be.recipe.services.executor.ListReservationsResultItem;
import be.recipe.services.executor.MarkAsArchivedParam;
import be.recipe.services.executor.MarkAsDeliveredParam;
import be.recipe.services.executor.MarkAsUndeliveredParam;

/**
 * The Class AbstractExecutorIntegrationModule.
 */
public abstract class AbstractExecutorIntegrationModule extends AbstractIntegrationModule {

	/** The Constant LOG. */
	private static final Logger LOG = Logger.getLogger(AbstractExecutorIntegrationModule.class);

	/** The prescription cache. */
	private static Map<String, GetPrescriptionForExecutorResult> prescriptionCache;

	/**
	 * Instantiates a new abstract executor integration module.
	 *
	 * @throws IntegrationModuleException
	 *             the integration module exception
	 */
	public AbstractExecutorIntegrationModule() {
		super();
	}

	/**
	 * Gets the prescription cache.
	 *
	 * @return the prescription cache
	 */
	public static Map<String, GetPrescriptionForExecutorResult> getPrescriptionCache() {
		if (prescriptionCache == null) {
			prescriptionCache = new HashMap<>();
		}
		return prescriptionCache;
	}

	/**
	 * Gets the kmehr helper.
	 *
	 * @return the kmehr helper
	 */
	protected KmehrHelper getKmehrHelper() {
		return new KmehrHelper();
	}

	/**
	 * Gets the sealed revoke prescription param.
	 *
	 * @param rid
	 *            the rid
	 * @param reason
	 *            the reason
	 * @return the sealed revoke prescription param
	 * @throws IntegrationModuleException
	 *             the integration module exception
	 */
	protected byte[] getSealedRevokePrescriptionParam(final String rid, final String reason) {
		final be.recipe.services.executor.RevokePrescriptionParam param = new be.recipe.services.executor.RevokePrescriptionParam();
		param.setRid(rid);
		param.setReason(reason);
		param.setExecutorId(StandaloneRequestorProvider.getRequestorIdInformation());
		param.setSymmKey(getSymmKey().getEncoded());
		return sealForRecipe(param, be.recipe.services.executor.RevokePrescriptionParam.class);
	}

	/**
	 * Gets the sealed mark as archived param.
	 *
	 * @param rid
	 *            the rid
	 * @return the sealed mark as archived param
	 * @throws IntegrationModuleException
	 *             the integration module exception
	 */
	public byte[] getSealedMarkAsArchivedParam(final String rid) {
		final MarkAsArchivedParam param = new MarkAsArchivedParam();
		param.setRid(rid);
		param.setExecutorId(StandaloneRequestorProvider.getRequestorIdInformation());
		param.setSymmKey(getSymmKey().getEncoded());
		return sealForRecipe(param, MarkAsArchivedParam.class);
	}

	/**
	 * Gets the sealed mark as delivered param.
	 *
	 * @param rid
	 *            the rid
	 * @return the sealed mark as delivered param
	 * @throws IntegrationModuleException
	 *             the integration module exception
	 */
	public byte[] getSealedMarkAsDeliveredParam(final String rid) {
		final MarkAsDeliveredParam param = new MarkAsDeliveredParam();
		param.setRid(rid);
		param.setExecutorId(StandaloneRequestorProvider.getRequestorIdInformation());
		param.setSymmKey(getSymmKey().getEncoded());
		return sealForRecipe(param, MarkAsDeliveredParam.class);
	}

	/**
	 * Gets the sealed data.
	 *
	 * @param rid
	 *            the rid
	 * 
	 * 
	 * @return the sealed mark as undelivered param
	 * @throws IntegrationModuleException
	 *             the integration module exception
	 */
	public byte[] getSealedMarkAsUnDeliveredParam(final String rid) {
		final MarkAsUndeliveredParam param = new MarkAsUndeliveredParam();
		param.setRid(rid);
		param.setExecutorId(StandaloneRequestorProvider.getRequestorIdInformation());
		param.setSymmKey(getSymmKey().getEncoded());
		return sealForRecipe(param, MarkAsUndeliveredParam.class);
	}

	/**
	 * Gets the sealed get prescription for executor param.
	 *
	 * @param rid
	 *            the rid
	 * @return the sealed get prescription for executor param
	 * @throws IntegrationModuleException
	 *             the integration module exception
	 */
	public byte[] getSealedGetPrescriptionForExecutorParam(final String rid) {
		return getSealedGetPrescriptionForExecutorParam(rid, null);
	}

	/**
	 * Gets the sealed get prescription for executor param.
	 *
	 * @param rid
	 *            the rid
	 * @param patientId
	 *            the patient id
	 * @return the sealed get prescription for executor param
	 * @throws IntegrationModuleException
	 *             the integration module exception
	 */
	protected byte[] getSealedGetPrescriptionForExecutorParam(final String rid, final String patientId) {
		final GetPrescriptionForExecutorParam param = new GetPrescriptionForExecutorParam();
		param.setRid(rid);
		param.setPatientId(patientId);
		param.setSymmKey(getSymmKey().getEncoded());
		param.setVersion(getPropertyHandler().getProperty("connector.version", "v2"));
		param.setExecutorId(StandaloneRequestorProvider.getRequestorIdInformation());
		return sealForRecipe(param, GetPrescriptionForExecutorParam.class);
	}

	/**
	 * Gets the sealed create feedback param.
	 *
	 * @param feedbackText
	 *            the feedback text
	 * @param etkRecipient
	 *            the etk recipient
	 * @param rid
	 *            the rid
	 * @param prescriberId
	 *            the prescriber id
	 * @return the sealed create feedback param
	 * @throws Exception
	 *             the exception
	 */
	protected byte[] getSealedCreateFeedbackParam(final byte[] feedbackText, final EncryptionToken etkRecipient, final String rid,
			final String prescriberId) throws Exception {
		byte[] message = IOUtils.compress(feedbackText);
		message = sealRequest(etkRecipient, message);
		final CreateFeedbackParam param = new CreateFeedbackParam();
		param.setRid(rid);
		param.setContent(message);
		param.setSymmKey(getSymmKey().getEncoded());
		param.setPrescriberId(prescriberId);
		param.setExecutorId(StandaloneRequestorProvider.getRequestorIdInformation());
		return sealForRecipe(param, CreateFeedbackParam.class);
	}

	/**
	 * Gets the sealed list notifications param.
	 *
	 * @param readFlag
	 *            the read flag
	 * @return the sealed list notifications param
	 * @throws IntegrationModuleException
	 *             the integration module exception
	 */
	public byte[] getSealedListNotificationsParam(final boolean readFlag) {
		final ListNotificationsParam param = new ListNotificationsParam();
		param.setSymmKey(getSymmKey().getEncoded());
		param.setReadFlag(readFlag);
		param.setExecutorId(StandaloneRequestorProvider.getRequestorIdInformation());
		return sealForRecipe(param, ListNotificationsParam.class);
	}

	/**
	 * Gets the sealed data.
	 *
	 * @param getPrescriptionStatusParam
	 *            the get prescription status param
	 * @return the sealed data
	 * @throws IntegrationModuleException
	 *             the integration module exception
	 */
	public byte[] getSealedData(final GetPrescriptionStatusParam getPrescriptionStatusParam) {
		getPrescriptionStatusParam.setSymmKey(getSymmKey().getEncoded());
		return sealForRecipe(getPrescriptionStatusParam, GetPrescriptionStatusParam.class);
	}

	/**
	 * Seal for recipe.
	 *
	 * @param <T>
	 *            the generic type
	 * @param data
	 *            the data
	 * @param type
	 *            the type
	 * @return the byte[]
	 * @throws IntegrationModuleException
	 *             the integration module exception
	 */
	protected <T> byte[] sealForRecipe(final T data, final Class<T> type) {
		final MarshallerHelper<Object, T> helper = new MarshallerHelper<>(Object.class, type);
		final EncryptionToken etkRecipe = getEtkHelper().getRecipe_ETK().get(0);
		return sealRequest(etkRecipe, helper.toXMLByteArray(data));
	}

	/**
	 * Creates the get prescription for executor result.
	 *
	 * @param getPrescriptionForExecutorResultSealed
	 *            the sealed executor response
	 * @return the gets the prescription for executor result
	 * @throws IntegrationModuleException
	 *             the integration module exception
	 */
	protected be.business.connector.recipe.executor.domain.GetPrescriptionForExecutorResult createGetPrescriptionForExecutorResult(
			final GetPrescriptionForExecutorResultSealed getPrescriptionForExecutorResultSealed) {
		final String requestorIdInformation = StandaloneRequestorProvider.getRequestorIdInformation();
		final String requestorTypeInformation = StandaloneRequestorProvider.getRequestorTypeInformation();
		final KeyResult key = getKeyFromKgss(getPrescriptionForExecutorResultSealed.getEncryptionKeyId(),
				getEtkHelper().getEtks(KgssIdentifierType.NIHII_PHARMACY, requestorIdInformation).get(0).getEncoded());
		final byte[] unsealedPrescription = unsealWithSymKey(getPrescriptionForExecutorResultSealed, key, requestorIdInformation,
				requestorTypeInformation);
		final be.business.connector.recipe.executor.domain.GetPrescriptionForExecutorResult finalResult = new be.business.connector.recipe.executor.domain.GetPrescriptionForExecutorResult(
				getPrescriptionForExecutorResultSealed);
		finalResult.setSealedContent(getPrescriptionForExecutorResultSealed.getPrescription());
		finalResult.setPrescription(unsealedPrescription);
		finalResult.setEncryptionKey(key.getSecretKey().getEncoded());
		finalResult.setInsurabilityResponse(InsurabilityHandler.getInsurability());
		finalResult.setMessageId(InsurabilityHandler.getMessageId());
		return finalResult;
	}

	/**
	 * Creates the list notification items.
	 *
	 * @param sealedExecutorResponse
	 *            the sealed executor response
	 * @return the list
	 * @throws IntegrationModuleException
	 *             the integration module exception
	 */
	protected List<be.recipe.services.executor.ListNotificationsItem> createListNotificationItems(final byte[] sealedExecutorResponse) {

		final MarshallerHelper<ListNotificationsResult, Object> marshaller = new MarshallerHelper<>(ListNotificationsResult.class, Object.class);
		final ListNotificationsResult result = marshaller.unsealWithSymmKey(sealedExecutorResponse, getSymmKey());
		final List<be.recipe.services.executor.ListNotificationsItem> items = result.getNotifications();

		for (int i = 0; i < items.size(); i++) {
			final ListNotificationsItem item = new ListNotificationsItem(items.get(i));
			if (item != null && item.getContent() != null) {
				try {
					final byte[] bytes = unsealNotiffeed(item.getContent());
					if (bytes != null){
						item.setContent(IOUtils.decompress(bytes));
					}
				} catch (final IOException e) {
					item.setLinkedException(e);
				}
			}
			items.set(i, item);
		}
		return items;
	}

	/**
	 * Check status.
	 *
	 * @param responseType
	 *            the response
	 * @throws IntegrationModuleException
	 *             the integration module exception
	 */
	public void checkStatus(final ResponseType responseType) {
		if (!EHEALTH_SUCCESS_CODE_100.equals(responseType.getStatus().getCode())
				&& !EHEALTH_SUCCESS_CODE_200.equals(responseType.getStatus().getCode())) {
			LOG.error("Error Status received : " + responseType.getStatus().getCode());
			throw new IntegrationModuleException(getLocalisedMsg(responseType.getStatus()));
		}
	}

	protected void checkResponseStatus(be.fgov.ehealth.commons.protocol.v2.StatusResponseType statusType) {
		be.fgov.ehealth.commons.protocol.v2.ObjectFactory of = new be.fgov.ehealth.commons.protocol.v2.ObjectFactory();
		if (!"urn:be:fgov:ehealth:2.0:status:Success".equals(statusType.getStatus().getStatusCode())) {
			LOG.error("Error Status received : " + statusType.getStatus().getStatusCode());
			throw new IntegrationModuleException(statusType.getStatus().getStatusMessage());
		}
	}

	/**
	 * Check status.
	 *
	 * @param responseType
	 *            the response
	 * @throws IntegrationModuleException
	 *             the integration module exception
	 */
	public void checkStatus(final be.recipe.services.core.ResponseType responseType) {
		if (!EHEALTH_SUCCESS_CODE_100.equals(responseType.getStatus().getCode())
				&& !EHEALTH_SUCCESS_CODE_200.equals(responseType.getStatus().getCode())) {
			LOG.error("Error Status received : " + responseType.getStatus().getCode());
			throw new IntegrationModuleException(getLocalisedMsg(responseType.getStatus()), responseType);
		}
	}

	/**
	 * Gets the localised msg.
	 *
	 * @param status
	 *            the status
	 * @return the localised msg
	 */
	private String getLocalisedMsg(final StatusType status) {
		final String locale = IntegrationModuleException.getUserLocale();
		for (final LocalisedString msg : status.getMessages()) {
			if (msg.getLang() != null && locale.equalsIgnoreCase(msg.getLang().value())) {
				return msg.getValue();
			}
		}
		if (status.getMessages().size() > 0) {
			return status.getMessages().get(0).getValue();
		}
		return status.getCode();
	}

	/**
	 * Gets the localised msg.
	 *
	 * @param status
	 *            the status
	 * @return the localised msg
	 */
	private String getLocalisedMsg(final be.recipe.services.core.StatusType status) {
		final String locale = IntegrationModuleException.getUserLocale();
		for (final be.recipe.services.core.LocalisedString msg : status.getMessages()) {
			if (msg.getLang() != null && locale.equalsIgnoreCase(msg.getLang().value())) {
				return msg.getValue();
			}
		}
		if (status.getMessages().size() > 0) {
			return status.getMessages().get(0).getValue();
		}
		return status.getCode();
	}

	/**
	 * Creates the identifier type.
	 *
	 * @param id
	 *            the id
	 * @param type
	 *            the type
	 * @return the identifier type
	 */
	protected IdentifierType createIdentifierType(final String id, final String type) {
		final IdentifierType ident = new IdentifierType();
		ident.setId(id);
		ident.setType(type);
		return ident;
	}

	private SecuredContentType getSealedData(ListReservations listReservations) {
		final SecuredContentType sct = new SecuredContentType();
		sct.setSecuredContent(sealForRecipe(listReservations, ListReservations.class));
		return sct;
	}

	/**
	 * Write reservations on disk.
	 *
	 * @param listReservationsParam
	 *            the data
	 * @param listReservationsResult
	 *            the unsealed result
	 * @param lastSyncDate
	 *            the last sync date
	 * @throws IntegrationModuleException
	 *             the integration module exception
	 */
	protected void writeReservationsOnDisk(final ListReservationsParam listReservationsParam, final ListReservationsResult listReservationsResult,
			final Calendar lastSyncDate) {
		if(!listReservationsResult.isHasMoreResults()){
			writeLastDateToDisk(listReservationsParam, lastSyncDate);
		}
		writeRidsToDisk(listReservationsResult);

	}

	/**
	 * Write rids to disk.
	 *
	 * @param listReservationsResult
	 *            the unsealed result
	 * @throws IntegrationModuleException
	 *             the integration module exception
	 */
	private void writeRidsToDisk(final ListReservationsResult listReservationsResult) {
		final String path = getPropertyHandler().getProperty("reservation.path");
		final File folder = new File(path);
		for (final ListReservationsResultItem item : listReservationsResult.getItems()) {
			try {
				final DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
				final String creationDate = df.format(item.getCreationDate().getTime());
				final String dateToPrintToFile = creationDate;
				if (!folder.exists()) {
					folder.mkdirs();
				}
				final File file = new File(folder + "/" + item.getRid() + ".txt");
				if (!file.exists()) {
					file.createNewFile();

				}
				final FileWriter fw = new FileWriter(file.getAbsoluteFile());
				final BufferedWriter bw = new BufferedWriter(fw);
				bw.write(dateToPrintToFile);
				bw.close();
			} catch (final IOException e) {
				throw new IntegrationModuleException(I18nHelper.getLabel("error.connection.executor"), e);
			}
		}
	}

	/**
	 * Write last date to disk.
	 *
	 * @param listReservationsParam
	 *            the data
	 * @param lastSyncDate
	 *            the last sync date
	 * @throws IntegrationModuleException
	 *             the integration module exception
	 */
	protected void writeLastDateToDisk(final ListReservationsParam listReservationsParam, final Calendar lastSyncDate) {
		try {
			final String path = getPropertyHandler().getProperty("reservation.path");
			final File filePath = new File(path);
			if (!filePath.exists()) {
				filePath.mkdirs();
			}
			final File file = new File(path + "/lastReservationSyncDate.txt");
			file.createNewFile();
			try (final BufferedWriter bw = new BufferedWriter(new FileWriter(file.getAbsoluteFile()))) {
				final DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
				final String date = df.format(lastSyncDate.getTime());
				bw.write(date);
				bw.flush();
			}
		} catch (final IOException e) {
			throw new IntegrationModuleException(I18nHelper.getLabel("error.connection.executor"), e);
		}
	}

	/**
	 * Read last date to disk.
	 *
	 * @return the calendar
	 * @throws IntegrationModuleException
	 *             the integration module exception
	 */
	protected Calendar readLastDateToDisk() {
		try {
			final String path = getPropertyHandler().getProperty("reservation.path");
			final DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
			final File file = new File(path + "/lastReservationSyncDate.txt");
			if (!file.exists()) {
				return null;
			}
			try (final BufferedReader br = new BufferedReader(new FileReader(file))) {
				final String st = br.readLine();
				if (st == null) {
					return null;
				}
				final Date date = df.parse(st);
				final Calendar calendar = Calendar.getInstance();
				calendar.setTime(date);
				return calendar;
			}
		} catch (final Exception e) {
			throw new IntegrationModuleException(I18nHelper.getLabel("error.connection.executor"), e);
		}
	}
}