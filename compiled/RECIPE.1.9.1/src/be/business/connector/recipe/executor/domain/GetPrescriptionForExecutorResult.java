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

package be.business.connector.recipe.executor.domain;

public class GetPrescriptionForExecutorResult extends be.recipe.services.executor.GetPrescriptionForExecutorResultSealed {
	private byte[] sealedContent;
	private byte[] encryptionKey;
	private String insurabilityResponse;
	private String messageId;

	public GetPrescriptionForExecutorResult() {
		super();
	}

	public GetPrescriptionForExecutorResult(be.recipe.services.executor.GetPrescriptionForExecutorResultSealed root) {
		setCreationDate(root.getCreationDate());
		setEncryptionKeyId(root.getEncryptionKeyId());
		setFeedbackAllowed(root.isFeedbackAllowed());
		setPatientId(root.getPatientId());
		setPrescriberId(root.getPrescriberId());
		setPrescription(root.getPrescription());
		setPrescriptionType(root.getPrescriptionType());
		setRid(root.getRid());
		setTimestampingId(root.getTimestampingId());
		setExpirationDate(root.getExpirationDate());
	}

	public byte[] getSealedContent() {
		return sealedContent;
	}

	public void setSealedContent(byte[] sealedContent) {
		this.sealedContent = sealedContent;
	}

	public byte[] getEncryptionKey() {
		return encryptionKey;
	}

	public void setEncryptionKey(byte[] encryptionKey) {
		this.encryptionKey = encryptionKey;
	}

	public void setInsurabilityResponse(String insurabilityResponse) {
		this.insurabilityResponse = insurabilityResponse;
	}

	public String getInsurabilityResponse() {
		return insurabilityResponse;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	public String getMessageId() {
		return messageId;
	}
}
