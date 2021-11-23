package be.business.connector.projects.common.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.perf4j.aop.Profiled;

import be.business.connector.core.exceptions.IntegrationModuleException;
import be.business.connector.core.exceptions.IntegrationModuleValidationException;
import be.business.connector.core.utils.I18nHelper;

/**
 * The Class ValidationUtils.
 */
public class ValidationUtils {

	/** The Constant PHARMACY_NIHII. */
	private static final String PHARMACY_NIHII = "-PHARMACY.NIHII";

	/** The Constant LOCKED. */
	public static final String LOCKED = "locked";

	/**
	 * Private constructor for {@link ValidationUtils}.
	 */
	private ValidationUtils() {
	}

	/**
	 * Validate patient id not blank.
	 *
	 * @param patientId
	 *            the patient id
	 */
	public static void validatePatientIdNotBlank(final String patientId) {
		if (StringUtils.isBlank(patientId)) {
			throw new IntegrationModuleException("Patient ID is 0.");
		}
	}

	/**
	 * Validate patient id.
	 *
	 * @param patientId
	 *            the patient id
	 */
	@Profiled(logFailuresSeparately = true, tag = "0.ValidationUtils#validatePatientId", logger = "org.perf4j.TimingLogger_Common")
	public static void validatePatientId(final String patientId) {
		if (!INSZUtils.isValidINSZ(patientId)) {
			throw new IntegrationModuleException(I18nHelper.getLabel("error.validation.patientid.incorrect"));
		}
	}

	/**
	 * Validate mandate holder id.
	 *
	 * @param mandateHolderId
	 *            the mandate holder id
	 * @param optional
	 *            the optional
	 */
	public static void validateMandateHolderId(final String mandateHolderId, final boolean optional) {
		if (optional) {
			if (!StringUtils.isEmpty(mandateHolderId) && !INSZUtils.isValidINSZ(mandateHolderId)) {
				throw new IntegrationModuleException(I18nHelper.getLabel("error.validation.mandateholderid.incorrect"));
			}
		} else {
			if (StringUtils.isEmpty(mandateHolderId) || !INSZUtils.isValidINSZ(mandateHolderId)) {
				throw new IntegrationModuleException(I18nHelper.getLabel("error.validation.mandateholderid.incorrect"));
			}
		}
	}

	/**
	 * Validate expiration date.
	 *
	 * @param expirationDateText
	 *            the expiration date text
	 * @throws IntegrationModuleValidationException
	 *             the integration module validation exception
	 */
	public static void validateExpirationDate(final String expirationDateText) throws IntegrationModuleValidationException {

		if (expirationDateText == null) {
			final String label = I18nHelper.getLabel("error.validation.expirationdate1", null);
			throw new IntegrationModuleValidationException(label, label);
		}

		final Calendar expirationDate = Calendar.getInstance();

		try {
			final Date date = new SimpleDateFormat("yyyy-MM-dd").parse(expirationDateText);
			expirationDate.setTime(date);
		} catch (final Exception ex) {
			final String label = I18nHelper.getLabel("error.validation.expirationdate4", new Object[] { expirationDateText });
			throw new IntegrationModuleValidationException(label, label);
		}
		final Calendar maxDate = Calendar.getInstance();
		maxDate.add(Calendar.DAY_OF_YEAR, 364);
		if (expirationDate.after(maxDate)) {
			final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			final String label = I18nHelper.getLabel("error.validation.expirationdate2", new Object[] { formatter.format(maxDate.getTime()) });
			throw new IntegrationModuleValidationException(label, label);
		}
		final Calendar now = Calendar.getInstance();
		now.set(Calendar.HOUR_OF_DAY, 0);
		now.set(Calendar.MINUTE, 0);
		now.set(Calendar.SECOND, 0);
		now.set(Calendar.MILLISECOND, 0);
		if (expirationDate.before(now)) {
			final String label = I18nHelper.getLabel("error.validation.expirationdate3", null);
			throw new IntegrationModuleValidationException(label, label);
		}
	}

	/**
	 * Validate visi.
	 *
	 * @param vision
	 *            the vision
	 * @param patient
	 *            the patient
	 */
	@Profiled(logFailuresSeparately = true, tag = "0.ValidationUtils#validateVisi", logger = "org.perf4j.TimingLogger_Common")
	public static void validateVisi(final String vision, final boolean patient) {
		if (patient) {
			if (vision == null || vision.equals("") || vision.equalsIgnoreCase("null") || vision.equalsIgnoreCase(LOCKED)
					|| vision.endsWith(PHARMACY_NIHII)) {
				if (vision != null && vision.endsWith(PHARMACY_NIHII)) {
					final String executorNbr = vision.replaceAll(PHARMACY_NIHII, "");
					if (!StringUtils.isNumeric(executorNbr)) {
						// not numeric number
						throw new IntegrationModuleValidationException(
								I18nHelper.getLabel("error.validation.vision.invalid.message", new Object[] { vision }));
					}
				}
			} else {
				throw new IntegrationModuleValidationException(
						I18nHelper.getLabel("error.validation.vision.invalid.message", new Object[] { vision }));
			}
		} else {
			if (vision == null || vision.equals("") || vision.equalsIgnoreCase("null") || vision.equalsIgnoreCase(LOCKED)) {
				// ok
			} else {
				throw new IntegrationModuleValidationException(
						I18nHelper.getLabel("error.validation.vision.invalid.prescriber.message", new Object[] { vision }));
			}
		}
	}
}
