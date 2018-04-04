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

package be.business.connector.common.performance.metric;

import be.apb.gfddpp.common.exceptions.GFDDPPException;
import be.apb.gfddpp.common.performancemetrics.MetricsUploadAppender;
import org.taktik.connector.business.recipeprojects.core.exceptions.IntegrationModuleException;
import org.taktik.connector.business.recipeprojects.common.services.pcdh.PcdhServiceImpl;
import org.taktik.connector.business.recipeprojects.common.services.recipe.RecipeTechnicalServiceImpl;
import be.ehealth.apb.gfddpp.services.pcdh.SystemError_Exception;
import be.ehealth.apb.gfddpp.services.pcdh.UploadPerformanceMetricRequestType;
import be.ehealth.technicalconnector.session.Session;
import be.fgov.ehealth.recipe.core.v1.SecuredContentType;
import be.fgov.ehealth.recipe.protocol.v1.UploadFileRequestType;
import org.apache.log4j.Logger;

public class MetricsUploadAppenderCommon extends MetricsUploadAppender {

	private static final Logger LOG = Logger.getLogger(MetricsUploadAppenderCommon.class);

	public static final String PROP_WSDL_EHEALTH_PCDH = "wsdl.ehealth.pcdh";

	/**
	 * Instantiates a new metrics upload appender executor.
	 * 
     * @throws be.apb.gfddpp.common.exceptions.GFDDPPException
	 */
	public MetricsUploadAppenderCommon() throws GFDDPPException {
		super();
	}

	/**
	 * Upload file.
	 * 
	 * @param compressed the compressed
	 * @throws IntegrationModuleException the integration module exception
	 * {@inheritDoc}
	 * @throws SystemError_Exception 
	 * @throws InterruptedException 
	 */
	@Override
	public void uploadFile(byte[] compressed) throws Exception {
		if (Session.getInstance().hasValidSession()) {
			LOG.info("A session is available for the metric upload ");

			UploadPerformanceMetricRequestType requestPCDH = buildPCDHRequest(compressed);
			UploadFileRequestType requestRecipe = buildRecipeRequest(compressed);
		
			PcdhServiceImpl.getInstance().uploadPerformanceMetric(requestPCDH);
			RecipeTechnicalServiceImpl.getInstance().uploadFileExecutor(requestRecipe);
		} else {
			LOG.info("No session  available for the metrics upload");
			//throw new IntegrationModuleException("Session is not ready");
		}
	}

	protected UploadFileRequestType buildRecipeRequest(byte[] compressed) {
		UploadFileRequestType request = new UploadFileRequestType();
		SecuredContentType content = new SecuredContentType();
		content.setSecuredContent(compressed);
		request.setSecuredUploadFileRequest(content);
		return request;
	}

	protected UploadPerformanceMetricRequestType buildPCDHRequest(byte[] compressed) {
		UploadPerformanceMetricRequestType request = new UploadPerformanceMetricRequestType();
		request.setUploadPerformanceMetricParamSealed(compressed);
		return request;
	}
}
