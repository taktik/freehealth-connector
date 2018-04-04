package be.ehealth.technicalconnector.exception;

public enum TechnicalConnectorExceptionValues {
   BUILDER_VALIDATION_EXCEPTION("error.builder.exception", "validation error while building object with builder {0} : {1}"),
   ERROR_INPUT("error.input", "Invalid input parameter. Reason: {0}"),
   ERROR_INPUT_PARAMETER_NULL("error.input.parameter.null", "Input parameter null"),
   ERROR_BUSINESS_CODE_REASON("error.business.code.reason", "Error while processing business call (code={0}): {1}"),
   INVALID_CERTIFICATE("invalid.certificate", "Invalid certificate"),
   INVALID_MYCARENET_INPUT_OBJECT("invalid.mycarenet.object", "Invalid mycarenet object : validation errors :  {0}"),
   NO_VALID_SESSION_WITH_ENCRYPTION("no.valid.session.with.encryption", "No valid session with encryption found"),
   NO_VALID_SESSION("no.valid.session", "No valid session found"),
   MALFORMED_URL("malformed.url", "Invalid url to {0} file"),
   MALFORMED_XML("malformed.xml", "Malformed XML"),
   STS_ERROR_RESPONSE("sts.error.response", "An error occurred while calling the eHealth SecureTokenService"),
   STS_ERROR_RESPONSE_STATUS("sts.error.response.status", "The response from the eHealth SecureTokenService contains the following status code: {0}"),
   ETK_SEAL_EXCEPTION("etk.seal.exception", "An error occurred while sealing the etk for identifierType: {0}, identifierApplicationID: {1},identifierValue: {2}"),
   PDF_XSL_NOT_FOUND("pdf.xsl.not.found", "The birthreport xsl file could not be found for the language: {0}"),
   ERROR_CREATING_SOAP_FAULT("error.creating.soap.fault", "Error while creating soap fault for messageId: {0}, clientId: {1}, exception:{2}"),
   HANDLER_ERROR("handler.error", "An error occured while instantiating the webservice security handler: {0}"),
   SAML_RESPONSE_EMPTY("saml.response.empty", "No SAML Assertion in the response, check parameters {0}"),
   ERROR_KEYSTORE_LOAD("error.keystore.load", "Error while loading the KeyStore"),
   ERROR_CRYPTO("error.crypto", "Error while trying to (un)seal: {0}"),
   ERROR_SIGNATURE_VALIDATION("error.crypto", "validation error while validating signature: {0}"),
   ERROR_CERTIFICATE_VALIDATION("error.crypto", "validation error while validating certificate: {0}"),
   ERROR_SIGNATURE("error.crypto", "Error while trying to create signature: {0}"),
   ERROR_KGSS("error.kgss", "Error while processing your KGSS call: {0}"),
   ERROR_WS("error.ws", "Error while executing web service call: {0}"),
   CORE_TECHNICAL("core.technical", "General error: {0}"),
   ERROR_COMPRESSION("error.compression", "Could not (de)compress the data"),
   SECURITY_NO_CERTIFICATE("security.nocertificate", "No X509 Certificate and/or Private Key provided"),
   ERROR_KEYSTORE_PASSWORD("error.keystore.password", "Incorrect password for the KeyStore. (path={0})"),
   ERROR_EID_READ("error.eid.read", "Cannot read eID, is device attached and eID inserted?"),
   ERROR_EID_LOGIN("error.eid.login", "Could not validate your login (PIN) for your eID"),
   ERROR_EID_RUNTIME("error.eid.runtime", "Could not locate eID runtime"),
   ERROR_CONFIG("error.config", "Configuration Error : {0}"),
   SAMLCONVERTER_ERROR("samlconverter.error", "Error occurred during conversion of a saml object"),
   ERROR_GENERAL("error.general", "General Error: {0}"),
   ERROR_TECHNICAL("error.technical", "Technical Connector error. {0}"),
   TARGETSERVICE_ERRORCODE_UNKNOWN("targetservice.errorcode.unknown", "TargetService error description: {0}"),
   ERROR_XML_INVALID("error.xml.invalid", "XML could not be validated against XSD. {0}"),
   VALIDATION_HUB_INTRA_ACKNOWLEDGE("validation.hub.intra.acknowledge", "Could not validate incoming message: {0}"),
   INVALID_PROPERTY("invalid.property", "Property not found in property file {0}"),
   INVALID_EHBOX_NEWS_NEWSTITLE("invalid.ehbox.news.newstitle", "News title can not be null."),
   ERROR_EHBOX_DOCUMENT_OUTPUTSTREAM("error.ehbox.document.outputstream", "Error occured while writing file {0}"),
   NOT_A_RECOGNISED_CARE_PROVIDER("error.notarecognisedcareprovider", "Could not find NIHII, are you a recognized care provider? Please check certificates. (STS)"),
   ERROR_NOTAPERSON("error.notaperson", "Could not verify that you are a person. Please check certificates. (STS)"),
   ERROR_ETK_NOTFOUND("error.etk.notfound", "Could not retrieve ETK"),
   ERROR_NOTFALLBACKKEYSTOREFOUND("error.nofallbackkeystorefound", "Could not locate your fallback session keystore"),
   CHARACTER_ENCODING_NOTSUPPORTED("character.encoding.unsupported", "The Character Encoding is not supported"),
   INVALID_MAPPING("invalid.mapping", "Invalid Mapping : {0}"),
   UNSUPPORTED_ENCODING_EXCEPTION("unsupported.encoding.exception", "Unsupported encoding exception : {0}"),
   INVALID_TOKEN("invalid.token", "Token is invalid : {0}"),
   INVALID_TIMESTAMP_TOKEN("invalid.timestamp.token", "Timestamp Token is invalid : {0}"),
   INVALID_CONFIG("invalid.config", "Configuration could not be validated : {0}"),
   INVALID_ATTRIBUTES_LENGTH("invalid_attributes_length", "Invalid attributes array length : {0}"),
   UNKNOWN_ERROR("unknown_error", "Unknown error occured : {0}"),
   ERROR_IOEXCEPTION("error.ioexception", "IOException occured while {0}"),
   NO_ENCRYPTIONKEYS("no.encryptionkeys", "Identifier is empty"),
   CALLBACKHANDLER_INSTANCIATION("callbackhandler.instanciation", "Problem while instanciating callback handler : {0}"),
   PROVIDER_INSTANCIATION("provider.instanciation", "Problem while instanciating Provider : {0} Reason: {1}"),
   HEADER_INSTANCIATION("header.instanciation", "Problem while instanciating header generator : {0}"),
   OID_NOTFOUND("oid.notfound", "OID could not be found : {0}"),
   INVALID_ISSUER("invalid.issuer", "Invalid issuer : {0}"),
   OID_VALUE_PARSING_ERROR("oid.value.parsing.error", "Error while parsing OID value : {0}"),
   NO_FILE_CONF("no.file.linux.conf", "No file exists for {0} configuration"),
   DLL_NOT_FOUND("dll.not.found", "DLL not found : {0}"),
   INCONSISTING_CONFIGURATION("inconsisting.configuration", "Inconsisting configuration, expecting value [{0}] but gets [{1}]"),
   ERROR_EIDPINLCALLBACKHANDLERFACTORY("error.EidPinCallBackHandlerFactory", "Couldn't instantiate KeyStore Class with name [{0}]"),
   STATUS_CHECKER_INSTANCIATION("statuschecker.instanciation", "Problem while instanciating status checker : {0}"),
   ID_GENERATOR_INSTANCIATION("idgenerator.instanciation", "Problem while instanciating id generator : {0}"),
   ERROR_EID_NULL("eid.null", "EID is not present"),
   PROPERTY_MISSING("property.missing", "The required property [{0}] is missing"),
   BEID_ERROR("beid.error", "Error while reading BeIDCard data : {0}"),
   BEID_ERROR_PHOTO("beid.error.photo", "Error while reading photo from beIdCard : {0}"),
   HARFILE("harfile.error", "An error occured while preparing HARfile : {0}"),
   ERROR_SAX_EXCEPTION("error.saxexception", "SAXException occured while {0}");

   private String errorCode;
   private String message;

   private TechnicalConnectorExceptionValues(String errorCode, String message) {
      this.errorCode = errorCode;
      this.message = message;
   }

   public String getErrorCode() {
      return this.errorCode;
   }

   public String getMessage() {
      return this.message;
   }

   private static class Constants {
      private static final String CAT_CRYPTO = "error.crypto";
   }
}
