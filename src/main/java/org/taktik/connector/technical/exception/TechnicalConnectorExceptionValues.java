package org.taktik.connector.technical.exception;

import org.springframework.http.HttpStatus;

public enum TechnicalConnectorExceptionValues {
   BUILDER_VALIDATION_EXCEPTION("error.builder.exception", "validation error while building object with builder {0} : {1}", HttpStatus.UNPROCESSABLE_ENTITY),
   ERROR_INPUT("error.input", "Invalid input parameter. Reason: {0}", HttpStatus.UNPROCESSABLE_ENTITY),
   ERROR_INPUT_PARAMETER_NULL("error.input.parameter.null", "Input parameter null", HttpStatus.UNPROCESSABLE_ENTITY),
   ERROR_BUSINESS_CODE_REASON("error.business.code.reason", "Error while processing business call (code={0}): {1}", HttpStatus.INTERNAL_SERVER_ERROR),
   INVALID_CERTIFICATE("invalid.certificate", "Invalid certificate", HttpStatus.EXPECTATION_FAILED),
   INVALID_MYCARENET_INPUT_OBJECT("invalid.mycarenet.object", "Invalid mycarenet object : validation errors :  {0}", HttpStatus.UNPROCESSABLE_ENTITY),
   NO_VALID_SESSION_WITH_ENCRYPTION("no.valid.session.with.encryption", "No valid session with encryption found", HttpStatus.FORBIDDEN),
   NO_VALID_SESSION("no.valid.session", "No valid session found", HttpStatus.FORBIDDEN),
   MALFORMED_URL("malformed.url", "Invalid url to {0} file", HttpStatus.BAD_REQUEST),
   MALFORMED_XML("malformed.xml", "Malformed XML", HttpStatus.UNPROCESSABLE_ENTITY),
   STS_ERROR_RESPONSE("sts.error.response", "An error occurred while calling the eHealth SecureTokenService", HttpStatus.INTERNAL_SERVER_ERROR),
   STS_ERROR_RESPONSE_STATUS("sts.error.response.status", "The response from the eHealth SecureTokenService contains the following status code: {0}", HttpStatus.INTERNAL_SERVER_ERROR),
   ETK_SEAL_EXCEPTION("etk.seal.exception", "An error occurred while sealing the etk for identifierType: {0}, identifierApplicationID: {1},identifierValue: {2}", HttpStatus.INTERNAL_SERVER_ERROR),
   PDF_XSL_NOT_FOUND("pdf.xsl.not.found", "The birthreport xsl file could not be found for the language: {0}", HttpStatus.INTERNAL_SERVER_ERROR),
   ERROR_CREATING_SOAP_FAULT("error.creating.soap.fault", "Error while creating soap fault for messageId: {0}, clientId: {1}, exception:{2}", HttpStatus.INTERNAL_SERVER_ERROR),
   HANDLER_ERROR("handler.error", "An error occured while instantiating the webservice security handler: {0}", HttpStatus.INTERNAL_SERVER_ERROR),
   SAML_RESPONSE_EMPTY("saml.response.empty", "No SAML Assertion in the response, check parameters {0}", HttpStatus.INTERNAL_SERVER_ERROR),
   ERROR_KEYSTORE_LOAD("error.keystore.load", "Error while loading the KeyStore", HttpStatus.INTERNAL_SERVER_ERROR),
   ERROR_CRYPTO("error.crypto", "Error while trying to (un)seal: {0}", HttpStatus.INTERNAL_SERVER_ERROR),
   ERROR_SIGNATURE_VALIDATION("error.crypto", "validation error while validating signature: {0}", HttpStatus.INTERNAL_SERVER_ERROR),
   ERROR_CERTIFICATE_VALIDATION("error.crypto", "validation error while validating certificate: {0}", HttpStatus.INTERNAL_SERVER_ERROR),
   ERROR_SIGNATURE("error.crypto", "Error while trying to create signature: {0}", HttpStatus.INTERNAL_SERVER_ERROR),
   ERROR_KGSS("error.kgss", "Error while processing your KGSS call: {0}", HttpStatus.INTERNAL_SERVER_ERROR),
   ERROR_WS("error.ws", "Error while executing web service call: {0}", HttpStatus.INTERNAL_SERVER_ERROR),
   CORE_TECHNICAL("core.technical", "General error: {0}", HttpStatus.INTERNAL_SERVER_ERROR),
   ERROR_COMPRESSION("error.compression", "Could not (de)compress the data", HttpStatus.INTERNAL_SERVER_ERROR),
   SECURITY_NO_CERTIFICATE("security.nocertificate", "No X509 Certificate and/or Private Key provided", HttpStatus.INTERNAL_SERVER_ERROR),
   ERROR_KEYSTORE_PASSWORD("error.keystore.password", "Incorrect password for the KeyStore. (path={0})", HttpStatus.INTERNAL_SERVER_ERROR),
   ERROR_EID_READ("error.eid.read", "Cannot read eID, is device attached and eID inserted?", HttpStatus.INTERNAL_SERVER_ERROR),
   ERROR_EID_LOGIN("error.eid.login", "Could not validate your login (PIN) for your eID", HttpStatus.INTERNAL_SERVER_ERROR),
   ERROR_EID_RUNTIME("error.eid.runtime", "Could not locate eID runtime", HttpStatus.INTERNAL_SERVER_ERROR),
   ERROR_CONFIG("error.config", "Configuration Error : {0}", HttpStatus.INTERNAL_SERVER_ERROR),
   SAMLCONVERTER_ERROR("samlconverter.error", "Error occurred during conversion of a saml object", HttpStatus.INTERNAL_SERVER_ERROR),
   ERROR_GENERAL("error.general", "General Error: {0}", HttpStatus.INTERNAL_SERVER_ERROR),
   ERROR_TECHNICAL("error.technical", "Technical Connector error. {0}", HttpStatus.INTERNAL_SERVER_ERROR),
   TARGETSERVICE_ERRORCODE_UNKNOWN("targetservice.errorcode.unknown", "TargetService error description: {0}", HttpStatus.INTERNAL_SERVER_ERROR),
   ERROR_XML_INVALID("error.xml.invalid", "XML could not be validated against XSD. {0}", HttpStatus.INTERNAL_SERVER_ERROR),
   VALIDATION_HUB_INTRA_ACKNOWLEDGE("validation.hub.intra.acknowledge", "Could not validate incoming message: {0}", HttpStatus.INTERNAL_SERVER_ERROR),
   INVALID_PROPERTY("invalid.property", "Property not found in property file {0}", HttpStatus.INTERNAL_SERVER_ERROR),
   INVALID_EHBOX_NEWS_NEWSTITLE("invalid.ehbox.news.newstitle", "News title can not be null.", HttpStatus.INTERNAL_SERVER_ERROR),
   ERROR_EHBOX_DOCUMENT_OUTPUTSTREAM("error.ehbox.document.outputstream", "Error occured while writing file {0}", HttpStatus.INTERNAL_SERVER_ERROR),
   NOT_A_RECOGNISED_CARE_PROVIDER("error.notarecognisedcareprovider", "Could not find NIHII, are you a recognized care provider? Please check certificates. (STS)", HttpStatus.INTERNAL_SERVER_ERROR),
   ERROR_NOTAPERSON("error.notaperson", "Could not verify that you are a person. Please check certificates. (STS)", HttpStatus.INTERNAL_SERVER_ERROR),
   ERROR_ETK_NOTFOUND("error.etk.notfound", "Could not retrieve ETK", HttpStatus.FORBIDDEN),
   ERROR_NOTFALLBACKKEYSTOREFOUND("error.nofallbackkeystorefound", "Could not locate your fallback session keystore", HttpStatus.INTERNAL_SERVER_ERROR),
   CHARACTER_ENCODING_NOTSUPPORTED("character.encoding.unsupported", "The Character Encoding is not supported", HttpStatus.INTERNAL_SERVER_ERROR),
   INVALID_MAPPING("invalid.mapping", "Invalid Mapping : {0}", HttpStatus.INTERNAL_SERVER_ERROR),
   UNSUPPORTED_ENCODING_EXCEPTION("unsupported.encoding.exception", "Unsupported encoding exception : {0}", HttpStatus.INTERNAL_SERVER_ERROR),
   INVALID_TOKEN("invalid.token", "Token is invalid : {0}", HttpStatus.INTERNAL_SERVER_ERROR),
   INVALID_TIMESTAMP_TOKEN("invalid.timestamp.token", "Timestamp Token is invalid : {0}", HttpStatus.INTERNAL_SERVER_ERROR),
   INVALID_CONFIG("invalid.config", "Configuration could not be validated : {0}", HttpStatus.INTERNAL_SERVER_ERROR),
   INVALID_ATTRIBUTES_LENGTH("invalid_attributes_length", "Invalid attributes array length : {0}", HttpStatus.INTERNAL_SERVER_ERROR),
   UNKNOWN_ERROR("unknown_error", "Unknown error occured : {0}", HttpStatus.INTERNAL_SERVER_ERROR),
   ERROR_IOEXCEPTION("error.ioexception", "IOException occured while {0}", HttpStatus.INTERNAL_SERVER_ERROR),
   NO_ENCRYPTIONKEYS("no.encryptionkeys", "Identifier is empty", HttpStatus.INTERNAL_SERVER_ERROR),
   CALLBACKHANDLER_INSTANCIATION("callbackhandler.instanciation", "Problem while instanciating callback handler : {0}", HttpStatus.INTERNAL_SERVER_ERROR),
   PROVIDER_INSTANCIATION("provider.instanciation", "Problem while instanciating Provider : {0} Reason: {1}", HttpStatus.INTERNAL_SERVER_ERROR),
   HEADER_INSTANCIATION("header.instanciation", "Problem while instanciating header generator : {0}", HttpStatus.INTERNAL_SERVER_ERROR),
   OID_NOTFOUND("oid.notfound", "OID could not be found : {0}", HttpStatus.INTERNAL_SERVER_ERROR),
   INVALID_ISSUER("invalid.issuer", "Invalid issuer : {0}", HttpStatus.INTERNAL_SERVER_ERROR),
   OID_VALUE_PARSING_ERROR("oid.value.parsing.error", "Error while parsing OID value : {0}", HttpStatus.INTERNAL_SERVER_ERROR),
   NO_FILE_CONF("no.file.linux.conf", "No file exists for {0} configuration", HttpStatus.INTERNAL_SERVER_ERROR),
   DLL_NOT_FOUND("dll.not.found", "DLL not found : {0}", HttpStatus.INTERNAL_SERVER_ERROR),
   INCONSISTING_CONFIGURATION("inconsisting.configuration", "Inconsisting configuration, expecting value [{0}] but gets [{1}]", HttpStatus.INTERNAL_SERVER_ERROR),
   ERROR_EIDPINLCALLBACKHANDLERFACTORY("error.EidPinCallBackHandlerFactory", "Couldn't instantiate KeyStore Class with name [{0}]", HttpStatus.INTERNAL_SERVER_ERROR),
   STATUS_CHECKER_INSTANCIATION("statuschecker.instanciation", "Problem while instanciating status checker : {0}", HttpStatus.INTERNAL_SERVER_ERROR),
   ID_GENERATOR_INSTANCIATION("idgenerator.instanciation", "Problem while instanciating id generator : {0}", HttpStatus.INTERNAL_SERVER_ERROR),
   ERROR_EID_NULL("eid.null", "EID is not present", HttpStatus.INTERNAL_SERVER_ERROR),
   PROPERTY_MISSING("property.missing", "The required property [{0}] is missing", HttpStatus.INTERNAL_SERVER_ERROR),
   BEID_ERROR("beid.error", "Error while reading BeIDCard data : {0}", HttpStatus.INTERNAL_SERVER_ERROR),
   BEID_ERROR_PHOTO("beid.error.photo", "Error while reading photo from beIdCard : {0}", HttpStatus.INTERNAL_SERVER_ERROR),
   HARFILE("harfile.error", "An error occured while preparing HARfile : {0}", HttpStatus.INTERNAL_SERVER_ERROR),
   ERROR_SAX_EXCEPTION("error.saxexception", "SAXException occured while {0}", HttpStatus.INTERNAL_SERVER_ERROR),
   INVALID_PROPERTY_VALUE("invalid.property.value", "Property value {0} is not valid: {1}", HttpStatus.INTERNAL_SERVER_ERROR),
   UNEXPECTED_ERROR("error.unexpected", "Unexpected connector error", HttpStatus.INTERNAL_SERVER_ERROR);

   private String errorCode;
   private String message;
   private HttpStatus httpStatus;

   private TechnicalConnectorExceptionValues(String errorCode, String message, HttpStatus httpStatus) {
      this.errorCode = errorCode;
      this.message = message;
      this.httpStatus = httpStatus;
   }

   public String getErrorCode() {
      return this.errorCode;
   }

   public String getMessage() {
      return this.message;
   }

   public HttpStatus getHttpStatus() {
      return httpStatus;
   }

   private static class Constants {
      private static final String CAT_CRYPTO = "error.crypto";
   }
}
