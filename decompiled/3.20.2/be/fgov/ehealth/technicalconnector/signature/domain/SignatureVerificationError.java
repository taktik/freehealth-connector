package be.fgov.ehealth.technicalconnector.signature.domain;

public enum SignatureVerificationError {
   OCSP_CHECKER_WAS_NOT_FOUND("Error indicating that the OCSPChecker was expecting to read a Ocsp Response in the Signer Informations of the message but could not be found."),
   CERTIFICATE_COULD_NOT_BE_VERIFIED("Error indicating that signing certificate could not be verified caused by an unknown reason."),
   CERTIFICATE_EXPIRED("Error indicating that signing certificate is expired."),
   CERTIFICATE_HAS_INVALID_KEYUSAGE("Error indicating that the signing certificate has an invalid key usage."),
   CERTIFICATE_NOT_YET_VALID("Error indicating that the signing certificate is not yet valid."),
   CERTIFICATE_REVOKED("Error indicating that the signing certificate has been revoked."),
   CERTIFICATE_STATUS_UNKNOWN("Error indicating that the status of the signing certificate could not be verified."),
   CERTIFICATE_CHAIN_NOT_TRUSTED("Error indicating that the signing certificate chain is not valid."),
   CERTIFICATE_CHAIN_COULD_NOT_BE_VERIFIED("Error indicating that the signing certificate chain corresponding is not valid."),
   XADES_SIGNEDPROPS_NOT_VALID("Error indicating that the signed props inside the xades are not valid."),
   XADES_SIGNEDPROPS_COULD_NOT_BE_VERIFIED("Error indicating that the signed props inside the xades could not be verified caused by an unkown reason."),
   XADES_SIGNEDPROPS_INVALID_SIGNINGTIME("Error indicating that the signed props inside the xades has an invalid signing time."),
   XADES_SIGNEDPROPS_DONT_HAVE_SIGNINGTIME("Error indicating that the signed props inside the xades don't have a signing time."),
   XADES_ENCAPSULATED_TIMESTAMP_NOT_FOUND("Error indication that there was no encapsulated timestamp in the xades-t."),
   XADES_ENCAPSULATED_TIMESTAMP_NOT_VERIFIED("Error indication that there was no encapsulated timestamp in the xades-t."),
   XADES_ENCAPSULATED_TIMESTAMP_NOT_VALID("Error indication that there was the encapsulated timestamp in the xades-t was invalid."),
   SIGNATURE_NOT_PRESENT("Error indicating that the signature there is no signature to verify."),
   SIGNATURE_COULD_NOT_BE_VERIFIED("Error indicating that the signature could not be verified caused by an unknown reason."),
   SIGNATURE_MANIFEST_IS_NOT_VALID("Error indicating that the manifest is not valid."),
   SIGNATURE_MANIFEST_REFERENCE_NOT_FOUND("Error indicating that the manifest reference could not be found."),
   SIGNATURE_MANIFEST_COULD_NOT_BE_VERIFIED("Error indicating that the manifest could not be verified caused by an unknown reason.");

   private String msg;

   private SignatureVerificationError(String msg) {
      this.msg = msg;
   }

   public String getMessage() {
      return this.msg;
   }

   public String getErrorName() {
      return this.name();
   }
}
