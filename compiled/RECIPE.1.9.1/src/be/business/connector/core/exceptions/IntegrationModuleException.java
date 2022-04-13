/**
 * Copyright (C) 2010 Recip-e
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package be.business.connector.core.exceptions;


import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import be.recipe.common.exceptions.RecipeException;
import be.recipe.services.core.LangageType;
import be.recipe.services.core.LocalisedString;
import be.recipe.services.core.PrescriptionStatus;
import be.recipe.common.exceptions.RecipeExceptionDetails;
import be.recipe.services.core.ResponseType;

/**
 * The Class IntegrationModuleException.
 */
public class IntegrationModuleException extends RuntimeException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The code. */
	private String code;
	
	/** The status updater. */
	private String statusUpdater;

	private PrescriptionStatus prescriptionStatus;
	/** The message code. */
	private String messageCode;

	/** The messages. */
	private List<LocalisedString> messages = new ArrayList<>();

	public IntegrationModuleException() {
		super();
	}

	/**
	 * Instantiates a new integration module exception.
	 *
	 * @param message
	 *            the message
	 * @param cause
	 *            the cause
	 */
	public IntegrationModuleException(final String message, final Throwable cause) {
		super(message, cause);
	}

	/**
	 * Instantiates a new integration module exception.
	 *
	 * @param code
	 *            the code
	 * @param message
	 *            the message
	 */
	public IntegrationModuleException(final String code, final String message) {
		super(message);
		this.code = code;
	}

	/**
	 * Instantiates a new integration module exception.
	 *
	 * @param code
	 *            the code
	 * @param message
	 *            the message
	 */
	public IntegrationModuleException(final String message, final ResponseType response) {
		super(message);
		this.code = response.getStatus().getCode();
		this.statusUpdater = response.getStatus().getStatusUpdater();
		if (response != null && response.getStatus() != null && response.getStatus().getPrescriptionStatus() != null) {
			this.prescriptionStatus = PrescriptionStatus.valueOf(response.getStatus().getPrescriptionStatus());
		}
		this.messageCode = response.getStatus().getMessageCode();
		this.messages.addAll(response.getStatus().getMessages());
	}
	
	/**
	 * Instantiates a new integration module exception.
	 *
	 * @param message
	 *            the message
	 */
	public IntegrationModuleException(final String message) {
		super(message);
	}

	/**
	 * Instantiates a new integration module exception.
	 *
	 * @param cause
	 *            the cause
	 */
	public IntegrationModuleException(final Throwable cause) {
		super(cause);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getLocalizedMessage() {
		return getMessage();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getMessage() {
		final String locale = getUserLocale();

		// Prescriber
		final Throwable cause = getCause();

		if (cause instanceof RecipeException) {
			RecipeException re = (RecipeException)cause;
			final RecipeExceptionDetails e = re.getFaultInfo();
			final RecipeExceptionDetails.ErrorMap list = e.getErrorMap();
			for (final RecipeExceptionDetails.ErrorMap.Entry entry : list.getEntries()) {
				if (entry.getKey().startsWith(locale)) {
					return entry.getValue().getMessage() + "\n" + entry.getValue().getResolution();
				}
			}
		}

		// Local error
		return super.getMessage();
	}

	/**
	 * Gets the user locale.
	 * 
	 * @return the user locale
	 */
	public static String getUserLocale() {
		String locale = Locale.getDefault().getLanguage();

		if (!locale.equalsIgnoreCase("nl") && !locale.equalsIgnoreCase("fr") && !locale.equalsIgnoreCase("en")) {
			Locale.setDefault(Locale.ENGLISH);
			locale = Locale.ENGLISH.getLanguage();
		}

		return locale;
	}

	/**
	 * Gets the code.
	 *
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Sets the code.
	 *
	 * @param code
	 *            the code to set
	 */
	public void setCode(final String code) {
		this.code = code;
	}

	/**
	 * Gets the status updater.
	 *
	 * @return the statusUpdater
	 */
	public String getStatusUpdater() {
		return statusUpdater;
	}

	/**
	 * Sets the status updater.
	 *
	 * @param statusUpdater
	 *            the statusUpdater to set
	 */
	public void setStatusUpdater(final String statusUpdater) {
		this.statusUpdater = statusUpdater;
	}

	/**
	 * 
	 * @return the messageCode
	 */
	public String getMessageCode() {
		return messageCode;
	}

	/**
	 * @param messageCode
	 *            the messageCode to set
	 */
	public void setMessageCode(final String messageCode) {
		this.messageCode = messageCode;
	}

	/**
	 * Gets the messages.
	 *
	 * @return the messages
	 */
	public List<LocalisedString> getMessages() {
		return messages;
	}

	/**
	 * Gets the messages.
	 *
	 * @return the messages
	 */
	public String getMessage(final LangageType language) {
		for (final LocalisedString ls : messages) {
			if (ls.getLang() == language) {
				return ls.getValue();
			}
		}
		return null;
	}

	/**
	 * Sets the messages.
	 *
	 * @param messages
	 *            the messages to set
	 */
	public void setMessages(final List<LocalisedString> messages) {
		//this.messages.addAll( messages);
		for(LocalisedString s : messages) {
			this.messages.add(s);
		}
	}

	/**
	 * @return the prescriptionStatus
	 */
	public PrescriptionStatus getPrescriptionStatus() {
		return prescriptionStatus;
	}

	/**
	 * @param prescriptionStatus
	 *            the prescriptionStatus to set
	 */
	public void setPrescriptionStatus(PrescriptionStatus prescriptionStatus) {
		this.prescriptionStatus = prescriptionStatus;
	}
}