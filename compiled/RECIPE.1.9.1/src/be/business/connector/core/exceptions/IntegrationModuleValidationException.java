package be.business.connector.core.exceptions;

import java.util.Collections;
import java.util.List;

/**
 * The Class IntegrationModuleValidationException.
 */
public class IntegrationModuleValidationException extends RuntimeException {

	/** The validation errors. */
	private List<String> validationErrors;

	/**
	 * Instantiates a new integration module validation exception.
	 *
	 * @param message
	 *            the message
	 * @param validationError
	 *            the validation error
	 */
	public IntegrationModuleValidationException(final String message, final String validationError) {
		super(message);
		this.validationErrors = Collections.singletonList(validationError);
	}

	/**
	 * Instantiates a new integration module validation exception.
	 *
	 * @param message
	 *            the message
	 * @param validationErrors
	 *            the validation errors
	 */
	public IntegrationModuleValidationException(final String message, final List<String> validationErrors) {
		super(message);
		this.validationErrors = validationErrors;
	}

	public IntegrationModuleValidationException(final String validationError) {
		this.validationErrors = Collections.singletonList(validationError);
	}

	/**
	 * Gets the validation errors.
	 *
	 * @return the validationErrors
	 */
	public List<String> getValidationErrors() {
		return validationErrors;
	}

	/**
	 * Sets the validation errors.
	 *
	 * @param validationErrors
	 *            the validationErrors to set
	 */
	public void setValidationErrors(final List<String> validationErrors) {
		this.validationErrors = validationErrors;
	}
}