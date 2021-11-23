/*
 * (C) 2021 Recip-e. All rights reserved.
 */
package be.recipe.common.wss4j.handler;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.xml.crypto.dsig.Reference;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import javax.xml.ws.handler.soap.SOAPMessageContext;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;
import org.apache.wss4j.common.WSEncryptionPart;
import org.apache.wss4j.common.crypto.Crypto;
import org.apache.wss4j.common.ext.WSSecurityException;
import org.apache.wss4j.dom.SOAPConstants;
import org.apache.wss4j.dom.message.WSSecHeader;
import org.apache.wss4j.dom.message.WSSecSignature;
import org.apache.wss4j.dom.util.WSSecurityUtil;
import org.w3c.dom.Element;

import be.ehealth.technicalconnector.config.domain.Duration;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorExceptionValues;
import be.ehealth.technicalconnector.handler.AbstractWsSecurityHandler;
import be.ehealth.technicalconnector.handler.AbstractWsSecurityHandler.SignedParts;
import be.ehealth.technicalconnector.handler.AbstractWsSecurityHandler.WSSecHeaderGeneratorStep1;
import be.ehealth.technicalconnector.handler.AbstractWsSecurityHandler.WSSecHeaderGeneratorStep2;
import be.ehealth.technicalconnector.handler.AbstractWsSecurityHandler.WSSecHeaderGeneratorStep3;
import be.ehealth.technicalconnector.handler.utils.WSSecurityCrypto;
import be.ehealth.technicalconnector.service.sts.security.Credential;
import be.ehealth.technicalconnector.service.sts.security.SAMLToken;
import be.ehealth.technicalconnector.service.sts.security.impl.SAMLHolderOfKeyToken;

/**
 * The Class {@link WSSecHeaderGeneratorWss4jImpl}.
 * 
 * @author <a href="mailto:bruno.casneuf@healthconnect.be">Bruno Casneuf</a>
 */
public class WSSecHeaderGeneratorWss4jImpl implements AbstractWsSecurityHandler.WSSecHeaderGeneratorStep0,
		AbstractWsSecurityHandler.WSSecHeaderGeneratorStep1, AbstractWsSecurityHandler.WSSecHeaderGeneratorStep2,
		AbstractWsSecurityHandler.WSSecHeaderGeneratorStep3, AbstractWsSecurityHandler.WSSecHeaderGeneratorStep4 {

	private static final String UNABLE_TO_INSERT_SECURITY_HEADER = "unable to insert security header.";

	private static final String HTTP_DOCS_OASIS_OPEN_ORG_WSS_OASIS_WSS_SAML_TOKEN_PROFILE_1_0_SAML_ASSERTION_ID = "http://docs.oasis-open.org/wss/oasis-wss-saml-token-profile-1.0#SAMLAssertionID";

	private static final String HTTP_WWW_W3_ORG_2000_09_XMLDSIG_RSA_SHA1 = "http://www.w3.org/2000/09/xmldsig#rsa-sha1";

	/** The soap part. */
	private SOAPPart soapPart;

	/** The ws sec header. */
	private WSSecHeader wsSecurityHeader;

	/** The sign. */
	private WSSecSignature wsSecuritySignature;

	/** The ws sec time stamp. */
	private be.recipe.common.wss4j.handler.WSSecTimestamp wsSecurityTimestamp;

	/** The assertion id. */
	private String assertionId;

	/** The credential. */
	private Credential credential;

	public WSSecHeaderGeneratorWss4jImpl() {
		System.out.println();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void sign(final SignedParts... parts) throws TechnicalConnectorException {
		try {
			if ((this.credential instanceof SAMLHolderOfKeyToken) && (StringUtils.isNotEmpty(this.assertionId))) {
				this.wsSecuritySignature.setSignatureAlgorithm(HTTP_WWW_W3_ORG_2000_09_XMLDSIG_RSA_SHA1);
				this.wsSecuritySignature.setKeyIdentifierType(12);
				this.wsSecuritySignature.setCustomTokenValueType(HTTP_DOCS_OASIS_OPEN_ORG_WSS_OASIS_WSS_SAML_TOKEN_PROFILE_1_0_SAML_ASSERTION_ID);
				this.wsSecuritySignature.setCustomTokenId(this.assertionId);
			} else {
				this.wsSecuritySignature.setKeyIdentifierType(1);
			}
			final Crypto crypto = new WSSecurityCrypto(this.credential.getPrivateKey(), this.credential.getCertificate());
			this.wsSecuritySignature.prepare(crypto);

			if ((!(this.credential instanceof SAMLHolderOfKeyToken)) || (!(StringUtils.isNotEmpty(this.assertionId)))) {
				this.wsSecuritySignature.appendBSTElementToHeader();
			}

			final List<Reference> referenceList = this.wsSecuritySignature.addReferencesToSign(generateReferencesToSign(parts));
			if (!(referenceList.isEmpty()))
				this.wsSecuritySignature.computeSignature(referenceList, false, null);
		} catch (final WSSecurityException e) {
			throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.HANDLER_ERROR,
					new Object[] { UNABLE_TO_INSERT_SECURITY_HEADER, e });
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public WSSecHeaderGeneratorStep3 withSAMLToken(final SAMLToken token) throws TechnicalConnectorException {
		this.credential = token;
		final Element assertionElement = token.getAssertion();
		final Element importedAssertionElement = (Element) this.soapPart.importNode(assertionElement, true);
		final Element securityHeaderElement = this.wsSecurityHeader.getSecurityHeaderElement();
		securityHeaderElement.appendChild(importedAssertionElement);
		this.assertionId = assertionElement.getAttribute("AssertionID");
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public WSSecHeaderGeneratorStep3 withBinarySecurityToken(final Credential credential) throws TechnicalConnectorException {
		this.credential = credential;
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public WSSecHeaderGeneratorStep2 withTimeStamp(final Duration duration) {
		this.wsSecurityTimestamp = new be.recipe.common.wss4j.handler.WSSecTimestamp(wsSecurityHeader);
		this.wsSecurityTimestamp.setTimeToLive((int) duration.convert(TimeUnit.SECONDS));
		this.wsSecurityTimestamp.build();
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public WSSecHeaderGeneratorStep2 withTimeStamp(final long timeToLive, final TimeUnit timeUnit) {
		withTimeStamp(new Duration(timeToLive, timeUnit));
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public WSSecHeaderGeneratorStep1 on(final SOAPMessage message) throws TechnicalConnectorException {
		try {
			Validate.notNull(message);
			this.soapPart = message.getSOAPPart();
			this.wsSecurityHeader = new WSSecHeader(this.soapPart);
			this.wsSecurityHeader.insertSecurityHeader();
			this.wsSecuritySignature = new WSSecSignature(wsSecurityHeader);
			return this;
		} catch (final WSSecurityException e) {
			throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.HANDLER_ERROR,
					new Object[] { UNABLE_TO_INSERT_SECURITY_HEADER, e });
		}
	}

	/**
	 * Generate references to sign.
	 *
	 * @param parts
	 *            the parts
	 * @return the list
	 */
	protected List<WSEncryptionPart> generateReferencesToSign(final SignedParts[] parts) {
		final List<WSEncryptionPart> signParts = new ArrayList<>();
		for (final SignedParts part : parts) {
			switch (part) {
			case BODY:
				Validate.notNull(this.wsSecurityTimestamp);
				signParts.add(new WSEncryptionPart(this.wsSecurityTimestamp.getId()));
				break;
			case TIMESTAMP:
				final SOAPConstants soapConstants = WSSecurityUtil.getSOAPConstants(this.soapPart.getDocumentElement());
				signParts.add(new WSEncryptionPart(soapConstants.getBodyQName().getLocalPart(), soapConstants.getEnvelopeURI(), "Content"));
				break;
			case SAML_ASSERTION:
				Validate.notNull(this.assertionId);
				signParts.add(new WSEncryptionPart(this.assertionId));
				break;
			case BST:
				signParts.add(new WSEncryptionPart(this.wsSecuritySignature.getBSTTokenId()));
			}

		}

		return signParts;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AbstractWsSecurityHandler.WSSecHeaderGeneratorStep1 on(final SOAPMessageContext ctx) throws TechnicalConnectorException {
		Validate.notNull(ctx);
		return on(ctx.getMessage());
	}
}