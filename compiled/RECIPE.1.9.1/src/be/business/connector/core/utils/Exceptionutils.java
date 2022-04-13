package be.business.connector.core.utils;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;

import be.business.connector.core.exceptions.IntegrationModuleEhealthException;
import be.business.connector.core.exceptions.IntegrationModuleException;
import be.business.connector.core.exceptions.IntegrationModuleRuntimeException;
import be.business.connector.core.exceptions.IntegrationModuleValidationException;

/**
 * The Class {@link Exceptionutils}.
 */
public class Exceptionutils {

	/**
	 * Instantiates a new {@link Exceptionutils}.
	 */
	private Exceptionutils() {
	}

	/** The Constant LOG. */
	private static final Logger LOG = Logger.getLogger(Exceptionutils.class);

	/**
	 * Error handler.
	 *
	 * @param t
	 *            the t
	 */
	public static void errorHandler(final Throwable t) {
		if (t instanceof IntegrationModuleRuntimeException) {
			LOG.error("", t);
			throw (IntegrationModuleRuntimeException) t;
		} else if (t instanceof IntegrationModuleEhealthException) {
			LOG.error("", t);
			throw (IntegrationModuleEhealthException) t;
		} else if (t.getCause() instanceof IntegrationModuleEhealthException) {
			LOG.error("", t);
			throw (IntegrationModuleEhealthException) t.getCause();
		} else if (t instanceof IntegrationModuleValidationException) {
			final List<String> list = ((IntegrationModuleValidationException) t).getValidationErrors();
			if (!CollectionUtils.isEmpty(list)) {
				LOG.info("***************** ValidationErrors begin *****************");
				for (String error : list) {
					LOG.info("ValidationError occured: " + error);
				}
				LOG.info("***************** ValidationErrors  end  *****************");
			}
			throw (IntegrationModuleValidationException) t;
		} else if (t instanceof IntegrationModuleException) {
			LOG.error("", t);
			throw (IntegrationModuleException) t;
		} else {
			LOG.error("", t);
			throw new IntegrationModuleException(I18nHelper.getLabel("error.technical"), t);
		}
	}

	/**
	 * Error handler.
	 *
	 * @param t
	 *            the t
	 * @param errorMsg
	 *            the error msg
	 */
	public static void errorHandler(Throwable t, String errorMsg) {
		if (t instanceof IntegrationModuleRuntimeException) {
			LOG.error("", t);
			throw (IntegrationModuleRuntimeException) t;
		} else if (t instanceof IntegrationModuleEhealthException) {
			LOG.error("", t);
			throw (IntegrationModuleEhealthException) t;
		} else if (t.getCause() instanceof IntegrationModuleEhealthException) {
			LOG.error("", t);
			throw (IntegrationModuleEhealthException) t.getCause();
		} else if (t instanceof IntegrationModuleValidationException) {
			final List<String> list = ((IntegrationModuleValidationException) t).getValidationErrors();
			if (!CollectionUtils.isEmpty(list)) {
				LOG.info("***************** ValidationErrors begin *****************");
				for (String error : list) {
					LOG.info("ValidationError occured: " + error);
				}
				LOG.info("***************** ValidationErrors  end  *****************");
			}
			throw (IntegrationModuleValidationException) t;
		} else if (t instanceof IntegrationModuleException) {
			LOG.error("", t);
			throw (IntegrationModuleException) t;
		} else {
			LOG.error("", t);
			throw new IntegrationModuleException(I18nHelper.getLabel(errorMsg), t);
		}
	}
}