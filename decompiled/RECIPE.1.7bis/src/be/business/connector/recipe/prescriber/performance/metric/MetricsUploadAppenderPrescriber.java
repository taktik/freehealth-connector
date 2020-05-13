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

package org.taktik.connector.business.recipe.prescriber.performance.metric;

import be.apb.gfddpp.common.exceptions.GFDDPPException;
import be.apb.gfddpp.helper.PropertyHandler;
import be.business.connector.common.performance.metric.MetricsUploadAppenderCommon;
import org.taktik.connector.business.recipeprojects.core.exceptions.IntegrationModuleException;
import org.taktik.connector.business.recipeprojects.core.utils.STSHelper;
import org.taktik.connector.business.recipeprojects.common.services.recipe.RecipeTechnicalServiceImpl;
import be.ehealth.technicalconnector.exception.SessionManagementException;
import be.ehealth.technicalconnector.session.Session;
import be.fgov.ehealth.recipe.protocol.v1.UploadFileRequestType;
import org.apache.log4j.Logger;
import org.apache.log4j.component.helpers.MessageFormatter;

public class MetricsUploadAppenderPrescriber extends MetricsUploadAppenderCommon {

	private static Logger LOG = Logger.getLogger(MetricsUploadAppenderPrescriber.class);
	/**
	 * Instantiates a new metrics upload appender executor.
	 * 
	 * @throws IntegrationModuleException
	 *             the integration module exception
	 */
	public MetricsUploadAppenderPrescriber() throws GFDDPPException {
		super();
	}

	/**
	 * Upload file.
	 * 
	 * @param compressed
	 *            the compressed
	 * @throws IntegrationModuleException
	 *             the integration module exception {@inheritDoc}
	 */
	@Override
	public void uploadFile(byte[] compressed) throws Exception {
		PropertyHandler propertyHandler = PropertyHandler.getInstance();
		String nihii;
		try{
			nihii = STSHelper.getNihii(Session.getInstance().getSession().getSAMLToken().getAssertion());
		} catch (Throwable t) {
			LOG.warn("Nihii in session not found: ", t);
			nihii = propertyHandler.getProperty("metrics.select.nihii");
			LOG.debug("NIHII found in proerperty file is [" + nihii + "]");
		}
		boolean metricsFilteringEnabled = Boolean.parseBoolean(propertyHandler.getProperty("metrics.select.nihii.enable", "false"));
		String groupOfNihiiEnabled = propertyHandler.getProperty("metrics.select.nihii.group");
		if (metricsFilteringEnabled) {
			if (nihii.endsWith(groupOfNihiiEnabled)) {
				uploadMetrics(compressed);
			} else {
				LOG.debug(MessageFormatter.format("Info will not be logged because the nihii {} does not ends up with {}", nihii, groupOfNihiiEnabled));
			}
		} else {
			uploadMetrics(compressed);
		}
	}

	private void uploadMetrics(byte[] compressed) throws SessionManagementException, IntegrationModuleException {
		if (Session.getInstance().hasValidSession()) {
			UploadFileRequestType request = buildRecipeRequest(compressed);
			RecipeTechnicalServiceImpl.getInstance().uploadFilePrescriber(request);
		} else {
			LOG.info("No session  available for the metrics upload");
		}
	}
}
